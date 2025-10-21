package newspaperoot.dao.utilities;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import newspaperoot.common.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBconnectionPool {
    private HikariDataSource hikariDataSource;


    public DBconnectionPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(Configuration.getInstance().getProperty("urlDB"));
        hikariConfig.setUsername(Configuration.getInstance().getProperty("user_name"));
        hikariConfig.setPassword(Configuration.getInstance().getProperty("password"));
        hikariConfig.setDriverClassName(Configuration.getInstance().getProperty("driver"));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
    public void closePool() {
        if (hikariDataSource != null && !hikariDataSource.isClosed()) {
            hikariDataSource.close();
        }
    }
}
