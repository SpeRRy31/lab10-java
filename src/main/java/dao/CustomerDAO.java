package dao;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CustomerDAO {

    public void saveCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Customer getCustomer(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Customer.class, id);
        } finally {
            session.close();
        }
    }

    public List<Customer> getAllCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from Customer", Customer.class).list();
        } finally {
            session.close();
        }
    }

    public void deleteCustomer(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
