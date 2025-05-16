package main.com.studentfinance.decorator;

import main.com.studentfinance.model.NotificationComponent;

public class BudgetAlertDecorator extends NotificationDecorator {
    private String budgetName;
    private double threshold;
    private double currentAmount;

    public BudgetAlertDecorator(NotificationComponent notification,
                                String budgetName,
                                double threshold,
                                double currentAmount) {
        super(notification);
        this.budgetName = budgetName;
        this.threshold = threshold;
        this.currentAmount = currentAmount;
    }

    @Override
    public boolean send() {
        boolean shouldSend = currentAmount >= threshold;

        if (shouldSend) {
            System.out.println("ВАЖЛИВО: Сповіщення про перевищення бюджету " + budgetName);
            return wrappedNotification.send();
        } else {
            System.out.println("Бюджет " + budgetName + " в межах норми, сповіщення не відправлено");
            return true; // Не відправляємо, але вважаємо успішним
        }
    }

    @Override
    public String getMessage() {
        return "БЮДЖЕТНЕ СПОВІЩЕННЯ: " + budgetName + " - " +
                wrappedNotification.getMessage() +
                " (Поточна сума: " + currentAmount + ", поріг: " + threshold + ")";
    }
}
