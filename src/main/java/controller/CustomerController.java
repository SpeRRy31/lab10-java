package controller;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final CustomerDAO customerDAO;

    public CustomerController() {
        customerDAO = new CustomerDAO();
    }

    public void displayMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Додати клієнта");
        System.out.println("2. Отримати клієнта за ID");
        System.out.println("3. Отримати всіх клієнтів");
        System.out.println("4. Видалити клієнта");
        System.out.println("5. Отримати клієнтів за ім'ям");
        System.out.println("6. Отримати клієнтів за діапазоном карток");
        System.out.println("7. Отримати клієнтів з пустим бонусним рахунком");
        System.out.println("8. Оновити дані клієнта");
        System.out.println("9. Вихід");
        System.out.print("Виберіть опцію: ");
    }

    public void addCustomer() {
        Customer customer = getCustomerInput();
        saveCustomer(customer);
        System.out.println("Клієнта додано.");
    }

    public void getCustomerById(Scanner scanner) {
        System.out.print("Введіть ID клієнта: ");
        Long id = scanner.nextLong();
        displayCustomerById(id);
    }

    public void getCustomersByName(Scanner scanner) {
        System.out.print("Введіть ім'я клієнта: ");
        String name = scanner.nextLine();
        List<Customer> customersByName = getCustomersByName(name);
        displayCustomersList(customersByName);
    }

    public void getCustomersByCardNumberRange(Scanner scanner) {
        System.out.print("Введіть початковий номер картки: ");
        String startCard = scanner.nextLine();
        System.out.print("Введіть кінцевий номер картки: ");
        String endCard = scanner.nextLine();
        List<Customer> customersByCardNumber = getCustomersByCardNumberRange(startCard, endCard);
        displayCustomersList(customersByCardNumber);
    }

    public void getCustomersWithEmptyBonusBalance() {
        List<Customer> customersWithEmptyBonus = customerDAO.getCustomersWithEmptyBonusBalance();
        displayCustomersList(customersWithEmptyBonus);
    }

    public void updateCustomer(Scanner scanner) {
        System.out.print("Введіть ID клієнта для оновлення: ");
        Long updateId = scanner.nextLong();
        scanner.nextLine();  // consume newline
        Customer updatedCustomer = getCustomerInput();
        updateCustomer(updateId, updatedCustomer);
        System.out.println("Дані клієнта оновлено.");
    }

    public void deleteCustomer(Scanner scanner) {
        Long deleteId = getCustomerIdForDeletion(scanner);
        deleteCustomer(deleteId);
        System.out.println("Клієнта видалено.");
    }

    public void displayCustomerById(Long id) {
        Customer customer = customerDAO.getCustomer(id);
        if (customer != null) {
            System.out.println("ID: " + customer.getId());
            System.out.println("Ім'я: " + customer.getName());
            System.out.println("Прізвище: " + customer.getSurname());
            System.out.println("Адреса: " + customer.getAddress());
            System.out.println("Телефон: " + customer.getPhoneNumber());
            System.out.println("Номер картки: " + customer.getCardNumber());
            System.out.println("Бонусний рахунок: " + customer.getBonusBalance());
        } else {
            System.out.println("Клієнта з таким ID не знайдено.");
        }
    }

    public void displayAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        displayCustomersList(customers);
    }

    public void displayCustomersList(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Не знайдено клієнтів.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer.getId() + ": " + customer.getName() + " " + customer.getSurname());
                System.out.println("\t" + customer);
            }
        }
    }

    public Customer getCustomerInput() {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer();
        System.out.print("Введіть ім'я: ");
        customer.setName(scanner.nextLine());

        System.out.print("Введіть прізвище: ");
        customer.setSurname(scanner.nextLine());

        System.out.print("Введіть по-батькові: ");
        customer.setFathername(scanner.nextLine());

        System.out.print("Введіть адресу: ");
        customer.setAddress(scanner.nextLine());

        System.out.print("Введіть номер телефону: ");
        customer.setPhoneNumber(scanner.nextLine());

        System.out.print("Введіть номер картки: ");
        customer.setCardNumber(scanner.nextLine());

        System.out.print("Введіть бонусний баланс: ");
        customer.setBonusBalance(scanner.nextDouble());

        return customer;
    }

    // Фільтрація за ім'ям
    public List<Customer> getCustomersByName(String name) {
        return customerDAO.getCustomersByName(name);
    }

    // Фільтрація за діапазоном карток
    public List<Customer> getCustomersByCardNumberRange(String startCard, String endCard) {
        return customerDAO.getCustomersByCardNumberRange(startCard, endCard);
    }

    // Оновити дані клієнта за ID
    public void updateCustomer(Long id, Customer updatedCustomer) {
        customerDAO.updateCustomerById(id, updatedCustomer);
    }

    // Отримати ID клієнта для видалення
    public Long getCustomerIdForDeletion(Scanner scanner) {
        System.out.print("Введіть ID клієнта для видалення: ");
        return scanner.nextLong();
    }

    // Збереження клієнта
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    // Видалити клієнта
    public void deleteCustomer(Long id) {
        customerDAO.deleteCustomer(id);
    }
}