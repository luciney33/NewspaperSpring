package newspaperoot.dao.jdbc.mappers;

import newspaperoot.dao.model.ArticleEntity;
import newspaperoot.dao.model.TypeEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapRStoArticleEntity {

    public ArticleEntity mapRS(ResultSet rs) throws SQLException {
        return new ArticleEntity(
                rs.getInt("id_article"),
                rs.getString("name_article"),
            new TypeEntity(rs.getInt("id_type"), rs.getString("name")),
                rs.getInt("id_newspaper")
                );
    }

}
