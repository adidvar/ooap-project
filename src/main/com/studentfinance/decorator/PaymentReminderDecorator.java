package main.com.studentfinance.decorator;

import main.com.studentfinance.model.NotificationComponent;
import main.com.studentfinance.model.Payment;

import java.util.Calendar;
import java.util.Date;

public class PaymentReminderDecorator extends NotificationDecorator {
    private Payment payment;
    private int daysBeforeDue;

    public PaymentReminderDecorator(NotificationComponent notification, Payment payment, int daysBeforeDue) {
        super(notification);
        this.payment = payment;
        this.daysBeforeDue = daysBeforeDue;
    }

    @Override
    public boolean send() {
        if (payment.isPaid()) {
            System.out.println("Платіж вже оплачено, нагадування скасовано");
            return true; // Вважаємо успішним, хоча нічого не відправляємо
        }

        // Перевіряємо, чи дата нагадування відповідає кількості днів до терміну оплати
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payment.getDueDate());
        calendar.add(Calendar.DATE, -daysBeforeDue);
        Date reminderDate = calendar.getTime();

        // Перевіряємо, чи потрібно відправляти сьогодні
        Calendar today = Calendar.getInstance();
        Calendar reminder = Calendar.getInstance();
        reminder.setTime(reminderDate);

        boolean isSameDay = today.get(Calendar.YEAR) == reminder.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == reminder.get(Calendar.DAY_OF_YEAR);

        if (isSameDay) {
            System.out.println("Відправка нагадування про платіж: " + payment.getDescription());
            return wrappedNotification.send();
        } else {
            System.out.println("Сьогодні не день для нагадування про цей платіж");
            return true; // Не відправляємо, але вважаємо успішним
        }
    }

    @Override
    public String getMessage() {
        return "НАГАДУВАННЯ ПРО ПЛАТІЖ: " + payment.getDescription() +
                " на суму " + payment.getAmount() +
                " до " + payment.getDueDate();
    }
}
