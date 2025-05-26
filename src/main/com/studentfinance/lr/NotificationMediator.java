package main.com.studentfinance.lr;

import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

public interface NotificationMediator {
    void registerObserver(PaymentObserver observer);
    void schedulePaymentReminders(Payment payment, Student student);
    void sendNotification(NotificationTemplate notification);
}
