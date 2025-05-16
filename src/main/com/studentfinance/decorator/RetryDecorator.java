package main.com.studentfinance.decorator;

import main.com.studentfinance.model.NotificationComponent;

public class RetryDecorator extends NotificationDecorator {
    private int maxRetries;
    private int retryDelayMinutes;

    public RetryDecorator(NotificationComponent notification, int maxRetries, int retryDelayMinutes) {
        super(notification);
        this.maxRetries = maxRetries;
        this.retryDelayMinutes = retryDelayMinutes;
    }

    @Override
    public boolean send() {
        boolean sent = wrappedNotification.send();
        int attempts = 1;

        // Симуляція повторних спроб відправки
        while (!sent && attempts < maxRetries) {
            System.out.println("Спроба #" + (attempts + 1) + " відправки сповіщення через "
                    + retryDelayMinutes + " хвилин...");

            // В реальному додатку тут був би таймер
            // Для демонстрації просто виводимо інформацію
            attempts++;
            sent = wrappedNotification.send();
        }

        return sent;
    }
}
