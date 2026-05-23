package com.app.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.app")
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DataSource getDbConnection(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        // url, username, password, driver class
        dataSource.setUrl("jdbc:mysql://localhost:3306/vechiledb");
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManager(DataSource dataSource)
    {
        LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.app.model"); // <- instead of individual class we give package only once

        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // JPA need this

        //Setting remaining hibernate property
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        //Attach property
        emf.setJpaProperties(properties);

        return emf;

    }
    @Bean
    public JpaTransactionManager jpaTransactionManager(LocalContainerEntityManagerFactoryBean emf){
        JpaTransactionManager jpaTransactionManager =new JpaTransactionManager();

        jpaTransactionManager.setEntityManagerFactory(emf.getObject());

        return jpaTransactionManager;
    }



}
