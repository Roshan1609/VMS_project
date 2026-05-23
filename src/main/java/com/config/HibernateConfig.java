package com.config;

import com.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            Configuration configuration=new Configuration();
            configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/VechileDB?createDatabaseIfNotExist=true");
            configuration.setProperty("hibernate.connection.user","root");
            configuration.setProperty("hibernate.connection.password","root123");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

            configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");

            configuration.setProperty("hibernate.hbm2ddl.auto","update");

            configuration.addAnnotatedClass(Policy.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Officer.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(User.class);


            sessionFactory=configuration.buildSessionFactory();
        }
    return sessionFactory;
    }
    public void closeFactory(){
        sessionFactory.close();
    }
}
