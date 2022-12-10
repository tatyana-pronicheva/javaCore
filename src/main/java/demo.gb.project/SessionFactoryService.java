package demo.gb.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryService {

    public static Session currentSession;

    public static Session openCurrentSession() {
         currentSession= getSessionFactory().openSession();
        return currentSession;
    }

    public static void closeCurrentSession() {
        currentSession.close();
    }


    static SessionFactory getSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = meta.getSessionFactoryBuilder().build();
        return sessionFactory;
    }
}
