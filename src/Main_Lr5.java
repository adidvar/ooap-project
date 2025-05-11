import main.com.studentfinance.factory.BudgetAlertFactory;
import main.com.studentfinance.factory.NotificationFactory;
import main.com.studentfinance.factory.PaymentReminderFactory;
import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;
import main.com.studentfinance.service.NotificationManager;

import java.util.Calendar;

public class Main_Lr5 {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦІЯ ПАТЕРНІВ ПРОЕКТУВАННЯ ===\n");

        System.out.println("1. ПАТЕРН 'ФАБРИЧНИЙ МЕТОД':");
        demonstrateFactoryMethodPattern();

        System.out.println("\n2. ПАТЕРН 'БУДІВЕЛЬНИК':");
        demonstrateBuilderPattern();

        System.out.println("\n3. ПАТЕРН 'ОДИНАК':");
        demonstrateSingletonPattern();
    }

    /**
     * Демонстрація роботи патерну "Фабричний метод"
     * Показує створення різних типів сповіщень через фабрики
     */
    private static void demonstrateFactoryMethodPattern() {
        System.out.println("Створення фабрик для різних типів сповіщень:");
        NotificationFactory paymentFactory = new PaymentReminderFactory();
        NotificationFactory budgetFactory = new BudgetAlertFactory();

        System.out.println("- PaymentReminderFactory створена");
        System.out.println("- BudgetAlertFactory створена");

        System.out.println("\nСтворення сповіщень через фабрики:");

        Notification paymentNotification = paymentFactory.prepareNotification(
                "Оплатіть рахунок за навчання до кінця тижня",
                NotificationType.PAYMENT_REMINDER
        );

        System.out.println("- Створено сповіщення про оплату: " + paymentNotification.getMessage());
        System.out.println("  Тип: " + paymentNotification.getType());
        System.out.println("  Статус: " + paymentNotification.getStatus());

        Notification budgetNotification = budgetFactory.prepareNotification(
                "Ви перевищили місячний бюджет на харчування",
                NotificationType.BUDGET_ALERT
        );

        System.out.println("- Створено сповіщення про бюджет: " + budgetNotification.getMessage());
        System.out.println("  Тип: " + budgetNotification.getType());
        System.out.println("  Статус: " + budgetNotification.getStatus());

        System.out.println("\nДемонстрація поліморфізму через фабричний метод:");
        boolean result1 = paymentNotification.send();
        boolean result2 = budgetNotification.send();

        System.out.println("- Відправка сповіщення про оплату: " + (result1 ? "успішно" : "не вдалося"));
        System.out.println("- Відправка сповіщення про бюджет: " + (result2 ? "успішно" : "не вдалося"));
    }

    /**
     * Демонстрація роботи патерну "Будівельник"
     * Показує різні способи конструювання об'єктів Payment
     */
    private static void demonstrateBuilderPattern() {
        System.out.println("Створення платежів за допомогою патерну 'Будівельник':");

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH, 7);

        // Створення першого платежу з мінімальними обов'язковими полями
        Payment payment1 = new Payment.PaymentBuilder()
                .amount(450.0)
                .dueDate(cal1.getTime())
                .build();

        System.out.println("1) Створено платіж з мінімальними параметрами:");
        System.out.println("   - Сума: " + payment1.getAmount());
        System.out.println("   - Дата оплати: " + payment1.getDueDate());
        System.out.println("   - Опис: " + (payment1.getDescription().isEmpty() ? "[не вказано]" : payment1.getDescription()));
        System.out.println("   - Сплачено: " + payment1.isPaid());
        System.out.println("   - Періодичний: " + payment1.isRecurring());

        // Створення другого платежу з усіма можливими параметрами
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, 14);

        Payment payment2 = new Payment.PaymentBuilder()
                .amount(1500.0)
                .dueDate(cal2.getTime())
                .description("Оплата за гуртожиток")
                .isPaid(false)
                .isRecurring(true)
                .build();

        System.out.println("\n2) Створено платіж з усіма параметрами:");
        System.out.println("   - Сума: " + payment2.getAmount());
        System.out.println("   - Дата оплати: " + payment2.getDueDate());
        System.out.println("   - Опис: " + payment2.getDescription());
        System.out.println("   - Сплачено: " + payment2.isPaid());
        System.out.println("   - Періодичний: " + payment2.isRecurring());

        // Демонстрація циклу створення платежу
        payment2.create();
    }

    /**
     * Демонстрація роботи патерну "Одинак"
     * Показує, що завжди отримуємо один і той же екземпляр NotificationManager
     */
    private static void demonstrateSingletonPattern() {
        System.out.println("Отримання екземплярів класу NotificationManager:");

        // Отримання першого екземпляру
        NotificationManager manager1 = NotificationManager.getInstance();
        System.out.println("- Отримано перший екземпляр: " + manager1);

        // Отримання другого екземпляру
        NotificationManager manager2 = NotificationManager.getInstance();
        System.out.println("- Отримано другий екземпляр: " + manager2);

        // Перевірка, чи це той самий об'єкт
        boolean isSameInstance = (manager1 == manager2);
        System.out.println("- Це один і той самий об'єкт? " + isSameInstance);

        System.out.println("\nДемонстрація роботи з екземпляром синглтону:");

        // Створення тестового студента і платежу для демонстрації
        Student student = new Student("S001", "Іван Петренко", "ivan@example.com", "hashedPassword");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);

        Payment payment = new Payment.PaymentBuilder()
                .amount(750.0)
                .dueDate(cal.getTime())
                .description("Оплата за навчання")
                .build();

        // Додання платежу до студента (викликає NotificationManager.getInstance())
        student.addPayment(payment);

        System.out.println("- Створені сповіщення для платежу: " + payment.getNotifications().size());

        // Демонстрація роботи методів синглтону
        System.out.println("- Виклик методу синглтона для відправки сповіщення:");

        if (!payment.getNotifications().isEmpty()) {
            Notification notification = payment.getNotifications().getFirst();
            boolean success = manager1.sendNotification(notification);
            System.out.println("  Результат відправки: " +
                    (success ? "успішно" : "не вдалося") +
                    " (Статус: " + notification.getStatus() + ")");
        }
    }
}
