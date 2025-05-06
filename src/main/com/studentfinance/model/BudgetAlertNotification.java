package main.com.studentfinance.model;

class BudgetAlertNotification extends Notification {
    public BudgetAlertNotification(String message) {
        super(message);
        this.type = NotificationType.BUDGET_ALERT;
    }

    @Override
    public boolean send() {
        System.out.println("Sending budget alert notification");
        return super.send();
    }
}

