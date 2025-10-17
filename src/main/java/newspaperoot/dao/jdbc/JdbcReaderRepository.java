package newspaperoot.dao.jdbc;
import newspaperoot.common.DBconnectionPool;
import newspaperoot.dao.ReaderRepository;
import newspaperoot.dao.jdbc.mappers.MapRStoReaderEntity;
import newspaperoot.dao.model.CredentialEntity;
import newspaperoot.dao.model.ReaderEntity;
import newspaperoot.dao.utilities.Queries;
import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.Error.DatabaseError;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcReaderRepository implements ReaderRepository {
    private final DBconnectionPool db;
    private MapRStoReaderEntity mapper;

    public JdbcReaderRepository(DBconnectionPool db, MapRStoReaderEntity mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<ReaderEntity> getAll() {
        List<ReaderEntity> readers = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement smt = con.createStatement()) {
            ResultSet readerRS = smt.executeQuery(Queries.SelectFromReader);
            while (readerRS.next()) {
                readers.add(mapper.mapRS(readerRS));
            }

        } catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        } catch (Exception e) {
            throw new AppError(e.getMessage());
        }
        return readers;
    }

    @Override
    public ReaderEntity getReaderById(int id) {
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(Queries.SelectReaderID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ReaderEntity(
                        rs.getInt("id_reader"),
                        rs.getString("name"),
                        rs.getDate("birth_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        } catch (Exception e) {
            throw new AppError(e.getMessage());
        }
        return null;
    }

    @Override
    public int save(ReaderEntity reader, CredentialEntity credential, boolean confirmation) {
        try (Connection con = db.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psReader = con.prepareStatement(Queries.InsertReader);
                 PreparedStatement psCredential = con.prepareStatement(Queries.InsertReaderCredential)) {

                psReader.setInt(1, reader.getId());
                psReader.setString(2, reader.getName());
                psReader.setDate(3, Date.valueOf(reader.getBirthday()));
                psReader.executeUpdate();

                if (confirmation) {
                    psCredential.setString(1, credential.getUsername());
                    psCredential.setString(2, credential.getPassword());
                    psCredential.setInt(3, reader.getId());
                    psCredential.executeUpdate();
                }

                con.commit();
                return 1;

            } catch (SQLException e) {
                con.rollback();
                throw new DatabaseError("Error inserting reader and credentials: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DatabaseError("DB connection error: " + e.getMessage());
        }
    }

    @Override
    public int delete(ReaderEntity reader, boolean confirmation) {
        try (Connection con = db.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psReader = con.prepareStatement(Queries.DeleteReader);
                 PreparedStatement psCredential = con.prepareStatement(Queries.DeleteReader2)) {

                if (confirmation) {
                    psCredential.setInt(1, reader.getId());
                    psCredential.executeUpdate();
                }

                psReader.setInt(1, reader.getId());
                int rowsAffected = psReader.executeUpdate();

                if (rowsAffected == 0) {
                    throw new DatabaseError("No reader found with id: " + reader.getId());
                }

                con.commit();
                return rowsAffected;

            } catch (SQLException e) {
                con.rollback();
                throw new DatabaseError("Error deleting reader: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new DatabaseError("DB connection error: " + e.getMessage());
        }
    }
}
