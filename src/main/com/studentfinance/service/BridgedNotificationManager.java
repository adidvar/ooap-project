package main.com.studentfinance.service;

import main.com.studentfinance.factory.BridgedNotificationFactory;
import main.com.studentfinance.implementator.NotificationAbstraction;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

public class BridgedNotificationManager {
    private static BridgedNotificationManager instance;

    private BridgedNotificationManager() {
    }

    public static BridgedNotificationManager getInstance() {
        if (instance == null) {
            instance = new BridgedNotificationManager();
        }
        return instance;
    }

    // Метод для створення і планування нагадувань про платіж
    public void schedulePaymentReminders(Payment payment, Student student) {
        // Визначаємо канал доставки на основі налаштувань користувача
        // (в реальній системі це могло б бути отримано з профілю користувача)
        String primaryChannel = "push";  // основний канал
        String backupChannel = "email";  // резервний канал

        // Створюємо сповіщення для різних дат

        // Нагадування за 3 дні до терміну оплати через основний канал
        NotificationAbstraction reminder3DaysBefore =
                BridgedNotificationFactory.createPaymentNotification(
                        payment, student.getEmail(), primaryChannel);

        // Створюємо нагадування в день оплати через резервний канал
        NotificationAbstraction reminderOnDueDate =
                BridgedNotificationFactory.createPaymentNotification(
                        payment, student.getEmail(), backupChannel);

        // Зберігаємо зв'язок між платежем і нагадуваннями
        // В реальній системі тут був би код для додавання сповіщень до планувальника
        System.out.println("Заплановано нагадування через " + primaryChannel +
                " за 3 дні до терміну оплати");
        System.out.println("Заплановано нагадування через " + backupChannel +
                " в день оплати");

        // Додаємо нагадування до платежу
        payment.addNotification(reminder3DaysBefore);
        payment.addNotification(reminderOnDueDate);
    }

    // Метод для відправки сповіщення
    public boolean sendNotification(NotificationAbstraction notification) {
        return notification.send();
    }

    // Метод для перевірки термінів оплати і відправки нагадувань
    public void checkDueDates() {
        System.out.println("Перевірка термінів оплати та відправлення сповіщень...");
        // В реальній системі тут був би код для перевірки всіх платежів
    }
}
