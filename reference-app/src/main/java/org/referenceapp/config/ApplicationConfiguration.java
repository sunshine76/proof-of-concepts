package org.referenceapp.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.referenceapp.util.LogExecutionMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by schinta6 on 12/23/15.
 */
@Configuration
@ComponentScan("org.referenceapp")
@EnableJpaRepositories(basePackages = "org.referenceapp.repository")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationConfiguration {

    @Bean
    @LogExecutionMetrics
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().addScript("classpath:h2/schema.sql")
                .addScript("classpath:h2/data.sql")
                .setType(EmbeddedDatabaseType.H2).build();

    }

    @Bean
    @LogExecutionMetrics
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.referenceapp.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    @LogExecutionMetrics
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
