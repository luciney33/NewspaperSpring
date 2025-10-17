package newspaperoot.dao.jdbc.mappers;

import newspaperoot.dao.model.NewspaperEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapRStoNewspaperEntity {
    public NewspaperEntity mapRS(ResultSet rs) throws SQLException {
        return new NewspaperEntity(
                rs.getInt("id_newspaper"),rs.getString("name"), rs.getDate("date").toLocalDate());
    }
}
