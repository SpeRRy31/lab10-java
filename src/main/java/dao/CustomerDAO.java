package dao;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    public List<Customer> getCustomersByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE name = :name", Customer.class);
        query.setParameter("name", name);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    // Список покупців за діапазоном номерів карток
    public List<Customer> getCustomersByCardNumberRange(String startCard, String endCard) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE cardNumber BETWEEN :startCard AND :endCard", Customer.class);
        query.setParameter("startCard", startCard);
        query.setParameter("endCard", endCard);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    // Кількість та список покупців з пустим бонусним рахунком
    public List<Customer> getCustomersWithEmptyBonusBalance() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE bonusBalance = 0", Customer.class);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    // Оновлення даних покупця за id
    public void updateCustomerById(Long id, Customer updatedCustomer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Завантаження існуючого покупця
        Customer customer = session.get(Customer.class, id);
        if (customer != null) {
            customer.setName(updatedCustomer.getName());
            customer.setSurname(updatedCustomer.getSurname());
            customer.setFathername(updatedCustomer.getFathername());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            customer.setCardNumber(updatedCustomer.getCardNumber());
            customer.setBonusBalance(updatedCustomer.getBonusBalance());
            session.update(customer);
        }

        transaction.commit();
        session.close();
    }
}
