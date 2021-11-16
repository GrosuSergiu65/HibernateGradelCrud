package md.hibernate.practice.crud;

import md.hibernate.practice.HibernateUtil;
import md.hibernate.practice.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends HibernateUtil {

    public static void updateEmployeeById(Session session, int id, String userName) {
        User user = session.get(User.class, id);
        if (user != null) {
            user.setUsername(userName);
        } else {
            System.out.println("User doesn't exist with provided ID");
        }
    }

    public static User getUserById(Session session, int id) {
        User user = session.get(User.class, id);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User doesn't exist with provided Id");
        }
        return user;
    }

    public List<User> getUsersList() {

        Session session = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String queryStr = "FROM User";
            Query query = session.createQuery(queryStr);
            userList = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception cause) {
                System.out.println("We get problem collecting all users" + cause);
            }
        }
        return userList;
    }


    public static void deleteEmployee(User user) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            System.out.println("deleted employee: " + user.getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception cause) {
                System.out.println("We delete user" + cause);
            }
        }
    }

}