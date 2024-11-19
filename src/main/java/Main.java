import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgress";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public static void printMenu() {
        System.out.println("Menu - Choose Operation:");
        System.out.println("1  - Show All Customers");
        System.out.println("2  - Add Customer");
        System.out.println("3  - Update Customer");
        System.out.println("4  - Delete Customer");
        System.out.println("5  - Find Customer by ID");
        System.out.println("6  - Show Customers by Name");
        System.out.println("7  - Show Customers with Card Number in Range");
        System.out.println("8  - Show Customers with Zero Bonus Balance");
        System.out.println("9  - Exit");
        System.out.print("\nInput here --> ");
    }

    public static int menu() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public void run() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            CustomerDAO customerDAO = new CustomerDAO();
            CustomerDAOManager customerDAOManager = new CustomerDAOManager(customerDAO);

            boolean close = false;
            while (!close) {
                switch (menu()) {
                    case 1:
                        customerDAOManager.showAllCustomers();
                        pressEnterToContinue();
                        break;
                    case 2:
                        customerDAOManager.addCustomer();
                        pressEnterToContinue();
                        break;
                    case 3:
                        customerDAOManager.updateCustomer();
                        pressEnterToContinue();
                        break;
                    case 4:
                        customerDAOManager.deleteCustomer();
                        pressEnterToContinue();
                        break;
                    case 5:
                        customerDAOManager.findCustomerById();
                        pressEnterToContinue();
                        break;
                    case 6:
                        customerDAOManager.showCustomersByName();
                        pressEnterToContinue();
                        break;
                    case 7:
                        customerDAOManager.showCustomersByCardNumberRange();
                        pressEnterToContinue();
                        break;
                    case 8:
                        customerDAOManager.showCustomersWithZeroBonusBalance();
                        pressEnterToContinue();
                        break;
                    case 9:
                        close = true;
                        break;
                    default:
                        System.out.println("Invalid number");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
