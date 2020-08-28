package com.pj.media.util;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

public class MysqlHibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            // load from different directory

            Configuration configuration = new Configuration();
            configuration.configure("/mySql.hibernate.cfg.xml");
            
            Reflections reflections = new Reflections("com.learning.model");
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);
            for (Class<?> clazz : classes) {
                configuration.addAnnotatedClass(clazz);
                
            }
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
            return factory;

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
