package main.com.studentfinance.model;

import jdk.jfr.Category;

import java.util.Date;
import java.util.Map;

public class Budget {
    private String id;
    private Double totalAmount;
    private Map<Category, Double> allocations;
    private Date startDate;
    private Date endDate;
    private BudgetType type;

    public void create() {
        // Implementation
    }

    public void edit() {
        // Implementation
    }

    public Double calculateSpent() {
        // Implementation
        return 0.0;
    }

    public Double calculateRemaining() {
        // Implementation
        return 0.0;
    }

    // getters, setters, constructors
}
