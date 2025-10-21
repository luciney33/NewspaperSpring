package newspaperoot.dao.jdbc;

import lombok.Data;
import newspaperoot.dao.utilities.DBconnectionPool;
import newspaperoot.dao.ArticleRepository;
import newspaperoot.dao.jdbc.mappers.MapRStoArticleEntity;
import newspaperoot.dao.model.ArticleEntity;
import newspaperoot.dao.utilities.Constantes;
import newspaperoot.dao.utilities.Queries;
import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.Error.DatabaseError;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class JdbcArticleRepository implements ArticleRepository {
    private final DBconnectionPool db;
    private final MapRStoArticleEntity mapper;


    public JdbcArticleRepository(DBconnectionPool db, MapRStoArticleEntity mapRStoArticleEntity) {
        this.db = db;
        this.mapper = mapRStoArticleEntity;
    }

    @Override
    public List<ArticleEntity> getAll() {
        List<ArticleEntity> articles = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement myStatement = con.createStatement()) {
            ResultSet articleRS = myStatement.executeQuery(Queries.SelectFrom);
            if (!articleRS.isBeforeFirst()) {
                System.out.println("No hay artículos en la BD");
            }
            while (articleRS.next()) {
                articles.add(mapper.mapRS(articleRS));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
        return articles;
    }

    @Override
    public ArticleEntity get(int id) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.SelectGet)) {
            ps.setInt(1, id);
            ResultSet articleRS = ps.executeQuery();
            if (articleRS.next()) {
                return mapper.mapRS(articleRS);
            }

        } catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
        return null;
    }

    @Override
    public int save(ArticleEntity article) {
        int rowsAffected=0;
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.SelectSave, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, article.getName());
            ps.setInt(2, article.getNPaperId());
            ps.setInt(3, article.getType().getId());
            rowsAffected= ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int auto_id = rs.getInt(1);
                article.setId(auto_id);
                System.out.println("The id of the new row is "+auto_id);
            }
        } catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int delete(ArticleEntity article, boolean confirmation) {
        try (Connection con = db.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(Queries.DeleteArticle);
                 PreparedStatement ps2 = con.prepareStatement(Queries.DeleteArticle2)) {

                if (confirmation) {
                    ps2.setInt(1, article.getId());
                    ps2.executeUpdate();
                }

                ps1.setInt(1, article.getId());
                int rowsAffected = ps1.executeUpdate();
                con.commit();
                return rowsAffected;

            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
                throw new DatabaseError(Constantes.DB_ERROR + article.getId());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
    }


    @Override
    public int update(ArticleEntity article, String newName) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.Update)) {
            ps.setString(1, newName);
            ps.setInt(2, article.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseError(Constantes.DB_ERROR2 + article.getId());
            }

            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppError(e.getMessage());
        }
    }
}
