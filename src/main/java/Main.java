import controller.CustomerController;

import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class Main {

    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {
            // Отримуємо SessionFactory з HibernateUtil
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction(); // Початок транзакції

            // Логіка для роботи з базою даних (можна перевірити підключення, зробити тестовий запит)
            System.out.println("Підключення до бази даних успішно ініціалізовано.");
            // Тестовий запит або інші операції (за бажанням)

            // Завершуємо транзакцію
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Якщо виникла помилка, виконуємо rollback
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Закриваємо сесію після завершення
            }
        }




        Scanner scanner = new Scanner(System.in);
        CustomerController customerController = new CustomerController();

        while (true) {
            // Виведення меню в Main
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
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    customerController.addCustomer();
                    break;
                case 2:
                    customerController.getCustomerById(scanner);
                    break;
                case 3:
                    customerController.displayAllCustomers();
                    break;
                case 4:
                    customerController.deleteCustomer(scanner);
                    break;
                case 5:
                    customerController.getCustomersByName(scanner);
                    break;
                case 6:
                    customerController.getCustomersByCardNumberRange(scanner);
                    break;
                case 7:
                    customerController.getCustomersWithEmptyBonusBalance();
                    break;
                case 8:
                    customerController.updateCustomer(scanner);
                    break;
                case 9:
                    System.out.println("Вихід.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
