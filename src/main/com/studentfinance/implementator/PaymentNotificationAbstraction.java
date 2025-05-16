package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;
import main.com.studentfinance.model.Payment;

public class PaymentNotificationAbstraction extends NotificationAbstraction {
    protected Payment payment;
    protected String recipient;

    public PaymentNotificationAbstraction(NotificationImplementor implementor,
                                          Payment payment,
                                          String recipient) {
        super(implementor);
        this.payment = payment;
        this.recipient = recipient;
    }

    @Override
    public boolean send() {
        if (payment.isPaid()) {
            System.out.println("Платіж вже оплачено, сповіщення не відправляється.");
            return true;
        }

        String message = "Нагадування про оплату: " + payment.getDescription() +
                " на суму " + payment.getAmount() +
                " до " + payment.getDueDate();

        return implementor.sendNotification(message, recipient, NotificationType.PAYMENT);
    }

    @Override
    public String getMessage() {
        return "Нагадування про оплату: " + payment.getDescription();
    }
}
