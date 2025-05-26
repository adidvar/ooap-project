package main.com.studentfinance.lr;

import main.com.studentfinance.model.Payment;

public interface PaymentObserver {
    void update(Payment payment);
}
