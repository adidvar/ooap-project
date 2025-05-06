package main.com.studentfinance.model;

import main.com.studentfinance.service.NotificationManager;

import java.util.*;

public class Payment {
    private final String id;
    private final double amount;
    private final Date dueDate;
    private boolean isPaid;
    private final String description;
    private boolean isRecurring;
    private List<Notification> notifications;

    // Private constructor to enforce the use of builder
    private Payment(PaymentBuilder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.dueDate = builder.dueDate;
        this.isPaid = builder.isPaid;
        this.description = builder.description;
        this.isRecurring = builder.isRecurring;
        this.notifications = new ArrayList<>();
    }

    public void create() {
        System.out.println("Payment created: " + this.description + " for " + this.amount);
        // Automatically schedule notifications via NotificationManager
        assert NotificationManager.getInstance() != null;
        NotificationManager.getInstance().scheduleNotification(this);
    }

    public void markAsPaid() {
        this.isPaid = true;
        System.out.println("Payment marked as paid: " + this.description);
    }

    public void delete() {
        System.out.println("Payment deleted: " + this.description);
        // Cancel all related notifications
        for (Notification notification : notifications) {
            notification.cancel();
        }
    }

    public void setRecurring() {
        this.isRecurring = true;
        System.out.println("Payment set as recurring: " + this.description);
    }

    public boolean isDueInDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date futureDate = cal.getTime();

        return !dueDate.after(futureDate) && !isPaid;
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    // Getters
    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }

    /**
     * Builder class for Payment
     */
    public static class PaymentBuilder {
        private final String id;
        private double amount;
        private Date dueDate;
        private boolean isPaid = false;
        private String description = "";
        private boolean isRecurring = false;

        public PaymentBuilder() {
            this.id = UUID.randomUUID().toString();
        }

        public PaymentBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder dueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public PaymentBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PaymentBuilder isPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public PaymentBuilder isRecurring(boolean isRecurring) {
            this.isRecurring = isRecurring;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
