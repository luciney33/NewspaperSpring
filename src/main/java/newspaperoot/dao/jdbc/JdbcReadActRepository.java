package newspaperoot.dao.jdbc;

import lombok.Data;
import newspaperoot.common.DBconnectionPool;
import newspaperoot.dao.ReaderActivityRepository;
import newspaperoot.dao.jdbc.mappers.MapRStoReadActEntity;
import newspaperoot.dao.model.ReadActivityEntity;
import newspaperoot.dao.utilities.Constantes;
import newspaperoot.dao.utilities.Queries;
import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.Error.DatabaseError;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class JdbcReadActRepository implements ReaderActivityRepository {
    private final DBconnectionPool db;
    private final MapRStoReadActEntity mapper;

    public JdbcReadActRepository(DBconnectionPool db, MapRStoReadActEntity mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<ReadActivityEntity> getReadersArticle(int idArticle) {
        List<ReadActivityEntity> readers = new ArrayList<>();
        try (Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(Queries.SelectReaderACT)) {
            ps.setInt(1, idArticle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readers.add(mapper.mapRS(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return readers;
    }

    @Override
    public int addRating(ReadActivityEntity readActivityEntity) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.SelectAddRating)) {
            ps.setInt(1,readActivityEntity.getRating());
            ps.setInt(2,readActivityEntity.getId());
            ps.setInt(3,readActivityEntity.getIdArticle());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseError(Constantes.DB_ERROR2 + readActivityEntity.getId());
            }

        }catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        }catch (Exception e) {
            throw new AppError(e.getMessage());
        }
        return 0;
    }
    @Override
    public int updateRating(ReadActivityEntity readActivity) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.UpdateRating)) {

            ps.setInt(1, readActivity.getRating());
            ps.setInt(2, readActivity.getId());
            ps.setInt(3, readActivity.getIdArticle());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseError("No read activity found for reader id " + readActivity.getId() + " and article id " + readActivity.getIdArticle());
            }
            return rowsAffected;

        } catch (SQLException e) {
            throw new DatabaseError("Error updating rating: " + e.getMessage());
        }
    }

    @Override
    public int deleteRating(int idReader, int idArticle) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.DeleteRating)) {

            ps.setInt(1, idReader);
            ps.setInt(2, idArticle);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseError("No read activity found for reader id " + idReader + " and article id " + idArticle);
            }
            return rowsAffected;

        } catch (SQLException e) {
            throw new DatabaseError("Error deleting rating: " + e.getMessage());
        }
    }

}
