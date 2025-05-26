package main.com.studentfinance.factory;

import main.com.studentfinance.model.Notification;
import main.com.studentfinance.model.NotificationType;

public class PaymentReminderNotification extends Notification {

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
