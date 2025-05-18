package main.com.studentfinance.model;

import main.com.studentfinance.implementator.NotificationAbstraction;
import main.com.studentfinance.lr.PaymentObserver;
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
    private List<NotificationComponent> compNotifications;
    private List<NotificationAbstraction> absNotifications;
    private final List<PaymentObserver> observers = new ArrayList<>();

    // Private constructor to enforce the use of builder
    private Payment(PaymentBuilder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.dueDate = builder.dueDate;
        this.isPaid = builder.isPaid;
        this.description = builder.description;
        this.isRecurring = builder.isRecurring;
        this.notifications = new ArrayList<>();
        this.compNotifications = new ArrayList<>();
        this.absNotifications = new ArrayList<>();
    }

    public Payment(String description, double amount, Date dueDate) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = false;
        this.isRecurring = false;
        this.notifications = new ArrayList<>();
        this.compNotifications = new ArrayList<>();
        this.absNotifications = new ArrayList<>();
    }

    public Payment(String id, double amount, Date dueDate, String description) {
        System.out.println("OBSERVER: Created observable Payment object with ID: " + id);
        this.id = id;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.isPaid = false;
        this.isRecurring = false;

        this.notifications = new ArrayList<>();
        this.compNotifications = new ArrayList<>();
        this.absNotifications = new ArrayList<>();
    }

//    public void create() {
//        System.out.println("Payment created: " + this.description + " for " + this.amount);
//        NotificationManager.getInstance().scheduleNotification(this);
//        notifyObservers();
//    }
//
//    public void markAsPaid() {
//        this.isPaid = true;
//        System.out.println("Payment marked as paid: " + this.description);
//        notifyObservers();
//    }

    public void create() {
        System.out.println("OBSERVER: Payment created: " + id + " for amount: " + amount);
        notifyObservers();
    }

    public void markAsPaid() {
        this.isPaid = true;
        System.out.println("OBSERVER: Payment " + id + " marked as paid - notifying observers");
        notifyObservers();
    }

    public void addObserver(PaymentObserver observer) {
        System.out.println("OBSERVER: Observer added to Payment " + id);
        observers.add(observer);
    }

    public void removeObserver(PaymentObserver observer) {
        System.out.println("OBSERVER: Observer removed from Payment " + id);
        observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println("OBSERVER: Payment " + id + " is notifying all " + observers.size() + " observers");
        for (PaymentObserver observer : observers) {
            observer.update(this);
        }
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

    public void addNotification(NotificationAbstraction notification) {
        this.absNotifications.add(notification);
    }

    public void addNotification(NotificationComponent notification) {
        this.compNotifications.add(notification);
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
