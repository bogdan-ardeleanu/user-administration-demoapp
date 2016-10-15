package x.y.z.demo.config.datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.vendor.Database;

import javax.sql.DataSource;
import java.sql.Connection;


@Configuration
@PropertySource("classpath:h2.properties")
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            DatabasePopulatorUtils.execute(populator(), dataSource);

        return dataSource;
    }


//    private DatabasePopulator populator() {
//        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//        databasePopulator.setContinueOnError(false);
//        databasePopulator.addScripts(
//                schemaScript,
//                h2utilScript,
//                pump0,
//                pump1/*,
//                pump2*/
//        );
//
//        return databasePopulator;
//    }

    public Database getDatabaseType() {
        return Database.H2;
    }


}
