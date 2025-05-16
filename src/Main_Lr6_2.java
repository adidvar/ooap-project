import main.com.studentfinance.factory.BridgedNotificationFactory;
import main.com.studentfinance.implementator.BasicNotificationAbstraction;
import main.com.studentfinance.implementator.NotificationAbstraction;
import main.com.studentfinance.implementator.SMSNotificationImplementor;
import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;
import main.com.studentfinance.service.BridgedNotificationManager;

import java.util.Calendar;

public class Main_Lr6_2 {
    public static void main(String[] args) {
        // Створюємо студента
        Student student = new Student("S001", "Марія Шевченко", "maria@example.com");

        // Створюємо платіж
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7); // Через 7 днів

        Payment payment = new Payment("Оплата за навчання", 5000.0, calendar.getTime());

        // Додаємо платіж студенту, що автоматично створює нагадування
        student.addPayment(payment);

        // Демонстрація створення сповіщень з різними реалізаціями
        System.out.println("\nДемонстрація створення сповіщень з різними реалізаціями:");

        // Створюємо звичайне Push-сповіщення
        NotificationAbstraction pushNotification =
                BridgedNotificationFactory.createBasicNotification(
                        "Важливе оголошення", student.getEmail(), NotificationType.INFO, "push");

        // Відправляємо Push-сповіщення
        System.out.println("\nВідправлення Push-сповіщення:");
        BridgedNotificationManager.getInstance().sendNotification(pushNotification);

        // Створюємо сповіщення через Email
        NotificationAbstraction emailNotification =
                BridgedNotificationFactory.createBasicNotification(
                        "Зміна розкладу", student.getEmail(), NotificationType.WARNING, "email");

        // Відправляємо Email-сповіщення
        System.out.println("\nВідправлення Email-сповіщення:");
        BridgedNotificationManager.getInstance().sendNotification(emailNotification);

        // Створюємо сповіщення через зовнішній сервіс (використання Adapter)
        NotificationAbstraction externalNotification =
                BridgedNotificationFactory.createBasicNotification(
                        "Терміновий виклик", student.getEmail(), NotificationType.ALERT, "external");

        // Відправляємо сповіщення через зовнішній сервіс
        System.out.println("\nВідправлення сповіщення через зовнішній сервіс:");
        BridgedNotificationManager.getInstance().sendNotification(externalNotification);

        // Створюємо сповіщення через стару систему (інший приклад Adapter)
        NotificationAbstraction legacyNotification =
                BridgedNotificationFactory.createBasicNotification(
                        "Нагадування про збори", student.getEmail(), NotificationType.INFO, "legacy");

        // Відправляємо сповіщення через стару систему
        System.out.println("\nВідправлення сповіщення через стару систему:");
        BridgedNotificationManager.getInstance().sendNotification(legacyNotification);

        // Демонстрація зміни реалізації під час виконання
        System.out.println("\nДемонстрація зміни реалізації під час виконання:");

        // Зміна реалізації з Email на SMS
        if (emailNotification instanceof BasicNotificationAbstraction) {
            System.out.println("Зміна каналу доставки з Email на SMS");
            ((BasicNotificationAbstraction) emailNotification).setImplementor(new SMSNotificationImplementor());
            BridgedNotificationManager.getInstance().sendNotification(emailNotification);
        }

        // Демонстрація сповіщення про платіж
        System.out.println("\nДемонстрація сповіщення про платіж:");
        NotificationAbstraction paymentNotification =
                BridgedNotificationFactory.createPaymentNotification(payment, student.getEmail(), "push");

        BridgedNotificationManager.getInstance().sendNotification(paymentNotification);

        // Позначаємо платіж як оплачений і перевіряємо поведінку
        System.out.println("\nДемонстрація поведінки після оплати:");
        payment.markAsPaid();
        BridgedNotificationManager.getInstance().sendNotification(paymentNotification);
    }
}
