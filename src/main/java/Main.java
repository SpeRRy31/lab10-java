import controller.CustomerController;
import model.Customer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerController customerController = new CustomerController();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати клієнта");
            System.out.println("2. Отримати клієнта за ID");
            System.out.println("3. Отримати всіх клієнтів");
            System.out.println("4. Видалити клієнта");
            System.out.println("5. Вихід");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    Customer customer = customerController.getCustomerInput();
                    customerController.saveCustomer(customer);
                    System.out.println("Клієнта додано.");
                    break;
                case 2:
                    System.out.print("Введіть ID клієнта: ");
                    Long id = scanner.nextLong();
                    customerController.displayCustomerById(id);
                    break;
                case 3:
                    customerController.displayAllCustomers();
                    break;
                case 4:
                    Long deleteId = customerController.getCustomerIdForDeletion();
                    customerController.deleteCustomer(deleteId);
                    System.out.println("Клієнта видалено.");
                    break;
                case 5:
                    System.out.println("Вихід.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
