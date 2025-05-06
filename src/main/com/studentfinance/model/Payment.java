package main.com.studentfinance.model;

import java.util.Date;
import java.util.List;

public class Payment {
    private Double amount;
    private Date dueDate;
    private Boolean isPaid;
    private String description;
    private String recurrence;  // может быть преобразовано в enum RecurrenceType
    private List<Notification> notifications;

    public void create() {
        // Implementation
    }

    public void markAsPaid() {
        // Implementation
    }

    public void delete() {
        // Implementation
    }

    public void setRecurring() {
        // Implementation
    }

    // getters, setters, constructors
}