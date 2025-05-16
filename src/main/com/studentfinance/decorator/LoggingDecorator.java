package main.com.studentfinance.decorator;

import main.com.studentfinance.model.NotificationComponent;

public class LoggingDecorator extends NotificationDecorator {
    public LoggingDecorator(NotificationComponent notification) {
        super(notification);
    }

    @Override
    public boolean send() {
        System.out.println("LOG: Підготовка до відправки сповіщення: " + wrappedNotification.getMessage());
        boolean result = wrappedNotification.send();
        System.out.println("LOG: Результат відправки: " + (result ? "успішно" : "не вдалося"));
        return result;
    }
}
