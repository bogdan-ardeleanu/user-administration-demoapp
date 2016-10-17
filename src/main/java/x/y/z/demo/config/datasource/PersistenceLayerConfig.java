package x.y.z.demo.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(order = 100)
@EnableJpaRepositories(
        basePackages = {"x.y.z.demo.app.repository"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@PropertySource("classpath:jpa.properties")
public class PersistenceLayerConfig {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private Environment env;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        // hibernate vendor
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(dataSourceConfig.getDatabaseType());
        vendorAdapter.setGenerateDdl(true);

        // compose entityManager
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJtaDataSource(dataSourceConfig.dataSource());
        factoryBean.setPersistenceUnitName("demo-pu");

        // scan packages
        factoryBean.setPackagesToScan("x.y.z.demo.app.entity");

        // JPA properties
        factoryBean.setJpaProperties(getEntityManagerFactoryJpaProperties());

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    public Properties getEntityManagerFactoryJpaProperties() {
        // JPA properties
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("jpa.hibernate.ddl-auto"));
        return jpaProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }
}
