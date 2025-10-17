package newspaperoot.dao.jdbc;

import lombok.Data;
import newspaperoot.common.DBconnectionPool;
import newspaperoot.dao.TypeRepository;
import newspaperoot.dao.jdbc.mappers.MapRStoTypeEntity;
import newspaperoot.dao.model.TypeEntity;
import newspaperoot.dao.utilities.Queries;
import newspaperoot.domain.Error.AppError;
import newspaperoot.domain.Error.DatabaseError;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class JdbcTypeRepository implements TypeRepository {
    private final DBconnectionPool db;
    private final MapRStoTypeEntity mapper;

    public JdbcTypeRepository(DBconnectionPool db, MapRStoTypeEntity mapper) {
        this.db = db;
        this.mapper = mapper;
    }

    @Override
    public List<TypeEntity> getAllTypes() {
        List<TypeEntity> types = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(Queries.SelectFromType);
            while (rs.next()) {
                types.add(mapper.mapRS(rs));
            }

        }catch (SQLException e) {
            throw new DatabaseError(e.getMessage());
        }catch (Exception e) {
            throw new AppError(e.getMessage());
        }
        return types;
    }
}
