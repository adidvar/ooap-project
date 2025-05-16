package main.com.studentfinance.decorator;

import main.com.studentfinance.model.NotificationComponent;

import java.util.Date;

abstract class NotificationDecorator implements NotificationComponent {
    protected NotificationComponent wrappedNotification;

    public NotificationDecorator(NotificationComponent notification) {
        this.wrappedNotification = notification;
    }

    @Override
    public boolean send() {
        return wrappedNotification.send();
    }

    @Override
    public String getMessage() {
        return wrappedNotification.getMessage();
    }

    @Override
    public Date getTriggerDate() {
        return wrappedNotification.getTriggerDate();
    }
}
