package test;

import main.com.studentfinance.lr.*;
import main.com.studentfinance.model.Payment;
import main.com.studentfinance.model.Student;

import java.util.Calendar;
import java.util.Date;

public class Main_Lr7 {
    public static void main(String[] args) {
        // Initialize the mediator (Singleton)
        System.out.println("\n=== INITIALIZING MEDIATOR (SINGLETON) ===");
        NotificationMediator mediator = NotificationManager.getInstance();

        // Create the observer and register with mediator
        System.out.println("\n=== CREATING OBSERVER AND CONNECTING WITH MEDIATOR ===");
        PaymentObserver observer = new PaymentNotificationObserver(mediator);

        // Create a student
        System.out.println("\n=== CREATING STUDENT AND PAYMENT ===");
        Student student = new Student("S001", "John Doe", "john@example.com");

        // Create a payment (due in 3 days from now)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dueDate = cal.getTime();

        Payment payment = new Payment("P001", 100.0, dueDate, "Monthly Subscription");

        // Add observer to payment
        System.out.println("\n=== CONNECTING OBSERVER TO PAYMENT (OBSERVER PATTERN) ===");
        payment.addObserver(observer);

        // Student adds payment
        student.addPayment(payment);

        // Create payment (this triggers the observer pattern)
        System.out.println("\n=== CREATING PAYMENT - TRIGGERS OBSERVER PATTERN ===");
        payment.create();

        // Simulate time passing - check due dates
        System.out.println("\n=== DEMONSTRATION OF MEMENTO PATTERN ===");
        System.out.println("Simulating time passing - checking due dates and restoring from memento");
        ((NotificationManager)mediator).checkDueDates();

        // Simulate sending notifications using Template Method pattern
        System.out.println("\n=== DEMONSTRATION OF TEMPLATE METHOD PATTERN ===");
        System.out.println("Sending notifications using Template Method pattern");

        // Create early reminder notification using Template Method
        NotificationTemplate earlyReminder = new PaymentReminderNotification(
                student.getEmail(),
                "Payment reminder: " + payment.getDescription() + " due in 3 days",
                payment.getDueDate()
        );
        mediator.sendNotification(earlyReminder);

        // Create urgent reminder notification using Template Method
        NotificationTemplate urgentReminder = new UrgentPaymentReminderNotification(
                student.getEmail(),
                "URGENT: " + payment.getDescription() + " payment due TODAY",
                payment.getDueDate()
        );
        mediator.sendNotification(urgentReminder);

        // Complete the payment
        System.out.println("\n=== MARKING PAYMENT AS PAID - TRIGGERS OBSERVER PATTERN AGAIN ===");
        payment.markAsPaid();
    }
}
