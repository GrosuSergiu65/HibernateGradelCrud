package md.hibernate.practice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();

        } catch (Throwable cause) {
            System.err.println("Initial SessionFactory creation failed." + cause);
            throw new ExceptionInInitializerError(cause);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
