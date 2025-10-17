package newspaperoot.dao.jdbc.mappers;

import newspaperoot.dao.model.ReaderEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapRStoReaderEntity {
    public ReaderEntity mapRS(ResultSet rs) throws SQLException {
        return new ReaderEntity(
                rs.getInt("id_reader"),rs.getString("name"), rs.getDate("birth_date").toLocalDate());
    }
}
