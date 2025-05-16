package main.com.studentfinance.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String id;
    private final String name;
    private final String email;
    private final String passwordHash;
    private final List<Payment> payments;

    public Student(String id, String name, String email, String passwordHash) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.payments = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.create();
    }

    public void viewDashboard() {
        System.out.println("Dashboard для студента: " + name);
        System.out.println("Email: " + email);
        System.out.println("Кількість платежів: " + payments.size());

        // Виводимо інформацію про платежі
        for (Payment payment : payments) {
            System.out.println(" - " + payment.getDescription() +
                    ": " + payment.getAmount() +
                    " до " + payment.getDueDate() +
                    (payment.isPaid() ? " (оплачено)" : " (не оплачено)"));
        }
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