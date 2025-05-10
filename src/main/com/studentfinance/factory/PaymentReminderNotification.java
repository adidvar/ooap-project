package main.com.studentfinance.factory;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;

public class PaymentReminderNotification extends Notification {
    private final NotificationType type;

    public PaymentReminderNotification(String message) {
        super(message);
        this.type = NotificationType.PAYMENT_REMINDER;
    }

    @Override
    public boolean send() {
        System.out.println("Sending payment reminder notification");
        return super.send();
    }
}
