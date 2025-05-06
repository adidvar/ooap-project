package main.com.studentfinance.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private List<Payment> payments;

    public Student(String id, String name, String email, String passwordHash) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.payments = new ArrayList<>();
    }

    public void viewDashboard() {
        System.out.println("Student " + name + " viewing dashboard");
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.create(); // This will trigger notification creation via NotificationManager
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Payment> getPayments() {
        return new ArrayList<>(payments);
    }
}