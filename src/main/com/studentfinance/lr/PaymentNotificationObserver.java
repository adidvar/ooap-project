package main.com.studentfinance.lr;

import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

public class PaymentNotificationObserver implements PaymentObserver {
    private final NotificationMediator mediator;

    public PaymentNotificationObserver(NotificationMediator mediator) {
        this.mediator = mediator;
        this.mediator.registerObserver(this);
        System.out.println("OBSERVER+MEDIATOR: PaymentNotificationObserver created and registered with mediator");
    }

    @Override
    public void update(Payment payment) {
        // This method is called whenever a payment is created or updated
        if (!payment.isPaid()) {
            System.out.println("OBSERVER+MEDIATOR: Observer received update about payment: " + payment.getId());
            Student dummyStudent = new Student("S001", "John Doe", "john@example.com");
            System.out.println("OBSERVER+MEDIATOR: Observer communicating with mediator to schedule reminders");
            mediator.schedulePaymentReminders(payment, dummyStudent);
        } else {
            System.out.println("OBSERVER+MEDIATOR: Observer received update that payment " +
                    payment.getId() + " is now paid - no reminders needed");
        }
    }
}
