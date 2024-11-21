package controller;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final CustomerDAO customerDAO;
    private final Scanner scanner;

    public CustomerController() {
        customerDAO = new CustomerDAO();
        scanner = new Scanner(System.in);
    }

    // Запит на введення даних нового клієнта
    public Customer getCustomerInput() {
        Customer customer = new Customer();
        System.out.print("Введіть прізвище: ");
        customer.setSurname(scanner.nextLine());
        System.out.print("Введіть ім'я: ");
        customer.setName(scanner.nextLine());
        System.out.print("Введіть по батькові: ");
        customer.setFathername(scanner.nextLine());
        System.out.print("Введіть адресу: ");
        customer.setAddress(scanner.nextLine());
        System.out.print("Введіть номер телефону: ");
        customer.setPhoneNumber(scanner.nextLine());
        System.out.print("Введіть номер картки: ");
        customer.setCardNumber(scanner.nextLine());
        System.out.print("Введіть баланс бонусів: ");
        customer.setBonusBalance(scanner.nextDouble());
        scanner.nextLine();  // to consume newline
        return customer;
    }

    // Виведення всіх клієнтів
    public void displayAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Немає клієнтів.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    // Виведення клієнта за ID
    public void displayCustomerById(Long id) {
        Customer customer = customerDAO.getCustomer(id);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Клієнт не знайдений.");
        }
    }

    // Обробка введення ID для видалення клієнта
    public Long getCustomerIdForDeletion() {
        System.out.print("Введіть ID клієнта для видалення: ");
        return scanner.nextLong();
    }

    // Метод для збереження або оновлення клієнта
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    // Метод для видалення клієнта
    public void deleteCustomer(Long id) {
        customerDAO.deleteCustomer(id);
    }
}
