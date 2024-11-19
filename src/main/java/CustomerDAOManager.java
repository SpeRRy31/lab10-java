import java.util.List;
import java.util.Scanner;

public class CustomerDAOManager {
    private final CustomerDAO customerDAO;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerDAOManager(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void showAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        customers.forEach(System.out::println);
    }

    public void addCustomer() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Surname: ");
        String surname = scanner.next();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Fathername: ");
        String fathername = scanner.next();
        System.out.print("Enter Address: ");
        String address = scanner.next();
        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Enter Card Number: ");
        long cardNumber = scanner.nextLong();
        System.out.print("Enter Bonus Balance: ");
        double bonusBalance = scanner.nextDouble();

        Customer customer = new Customer(id, surname, name, fathername, address, phoneNumber, cardNumber, bonusBalance);
        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    public void updateCustomer() {
        System.out.print("Enter Customer ID to update: ");
        int id = scanner.nextInt();
        Customer customer = customerDAO.getCustomerById(id);

        if (customer != null) {
            System.out.print("Enter new Surname: ");
            customer.setSurname(scanner.next());
            System.out.print("Enter new Name: ");
            customer.setName(scanner.next());
            System.out.print("Enter new Fathername: ");
            customer.setFathername(scanner.next());
            System.out.print("Enter new Address: ");
            customer.setAddress(scanner.next());
            System.out.print("Enter new Phone Number: ");
            customer.setPhoneNumber(scanner.nextLong());
            System.out.print("Enter new Card Number: ");
            customer.setCardNumber(scanner.nextLong());
            System.out.print("Enter new Bonus Balance: ");
            customer.setBonusBalance(scanner.nextDouble());

            customerDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int id = scanner.nextInt();
        customerDAO.deleteCustomer(id);
        System.out.println("Customer deleted successfully.");
    }

    public void findCustomerById() {
        System.out.print("Enter Customer ID to find: ");
        int id = scanner.nextInt();
        Customer customer = customerDAO.getCustomerById(id);

        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void showCustomersByName() {
        System.out.print("Enter Name to search for: ");
        String name = scanner.next();
        // Implement method in CustomerDAO to search by name and use it here
    }

    public void showCustomersByCardNumberRange() {
        System.out.print("Enter Minimum Card Number: ");
        long min = scanner.nextLong();
        System.out.print("Enter Maximum Card Number: ");
        long max = scanner.nextLong();
        // Implement method in CustomerDAO to search by card number range and use it here
    }

    public void showCustomersWithZeroBonusBalance() {
        // Implement method in CustomerDAO to find customers with zero bonus balance and use it here
    }
}
