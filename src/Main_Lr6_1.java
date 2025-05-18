import main.com.studentfinance.decorator.LoggingDecorator;
import main.com.studentfinance.decorator.RetryDecorator;
import main.com.studentfinance.factory.NotificationFactory;
import main.com.studentfinance.model.*;
import main.com.studentfinance.service.NotificationManager;

import java.util.Calendar;

public class Main_Lr6_1 {
    public static void main(String[] args) {
        // Створюємо студента
        Student student = new Student("S001", "Іван Петренко", "ivan@example.com", "+380501234567");

        // Створюємо платіж через Builder Pattern
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);

        Payment payment1 = new Payment.PaymentBuilder()
                .description("Оплата за навчання")
                .amount(5000.0)
                .dueDate(calendar.getTime())
                .build();

        // Додаємо платіж студенту, що автоматично створює нагадування
        student.addPaymentPrimary(payment1);

        // Додаємо другий платіж з іншою датою
        calendar.add(Calendar.DATE, 14);
        Payment payment2 = new Payment.PaymentBuilder()
                .description("Оплата за гуртожиток")
                .amount(1500.0)
                .dueDate(calendar.getTime())
                .build();

        student.addPaymentPrimary(payment2);

        // Показуємо інформацію на дашборді
        student.viewDashboard();

        // Демонстрація ручного створення декорованих нотифікацій
        System.out.println("\nДемонстрація ручного створення декорованих нотифікацій:");

        // Створюємо базове сповіщення
        NotificationComponent basicNotification =
                NotificationFactory.createBasicNotification(
                        "Звичайне сповіщення", NotificationType.INFO);

        // Додаємо логування
        NotificationComponent loggedNotification =
                new LoggingDecorator(basicNotification);

        // Додаємо функцію повторних спроб
        NotificationComponent retriableLoggedNotification =
                new RetryDecorator(loggedNotification, 3, 30);

        // Відправляємо декороване сповіщення
        NotificationManager.getInstance().sendNotification(retriableLoggedNotification);

        // Демонстрація композитного шаблону
        System.out.println("\nДемонстрація композитного шаблону:");

        // Створюємо групу сповіщень для студента
        CompositeNotification studentNotifications =
                NotificationFactory.createGroupedNotifications("Сповіщення для " + student.getName());

        // Додаємо різні типи сповіщень до групи
        NotificationComponent paymentConfirmation =
                NotificationFactory.createLoggedNotification(
                        "Підтвердження оплати отримано", NotificationType.PAYMENT);

        NotificationComponent budgetAlert =
                NotificationFactory.createBudgetAlert(
                        "Вашу квоту бюджету перевищено", "Місячний бюджет", 2000.0, 2500.0);

        // Додаємо сповіщення до групи
        studentNotifications.addNotification(paymentConfirmation);
        studentNotifications.addNotification(budgetAlert);

        // Відправляємо всю групу сповіщень одночасно
        NotificationManager.getInstance().sendNotification(studentNotifications);

        // Демонстрація сценарію з переглядом нагадувань
        System.out.println("\nДемонстрація перевірки термінів оплати:");
        NotificationManager.getInstance().checkDueDates();

        // Демонстрація сповіщення про платіж в день оплати
        System.out.println("\nСимуляція дня оплати для першого платежу:");
        NotificationComponent dueDateReminder =
                NotificationFactory.createPaymentReminder(
                        "Сьогодні останній день для оплати", payment1, 0);

        NotificationManager.getInstance().sendNotification(dueDateReminder);

        // Демонстрація, що сповіщення не відправляється, якщо платіж вже оплачено
        System.out.println("\nДемонстрація обробки оплаченого платежу:");
        payment1.markAsPaid();
        NotificationManager.getInstance().sendNotification(dueDateReminder);
    }
}
