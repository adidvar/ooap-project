package main.com.studentfinance.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student {
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private Balance balance;
    private List<Transaction> transactions;
    private List<Budget> budgets;
    private List<FinancialGoal> financialGoals;
    private List<Payment> payments;
    private List<ConsultationRequest> consultationRequests;

    public void viewDashboard() {
        // Implementation
    }

    // getters, setters, constructors
}