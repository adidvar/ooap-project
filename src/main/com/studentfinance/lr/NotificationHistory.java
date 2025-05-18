package main.com.studentfinance.lr;

import main.com.studentfinance.model.Notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationHistory {
    private final Map<String, NotificationMemento> notificationHistory = new HashMap<>();

    public void saveNotification(String id, Notification notification) {
        System.out.println("MEMENTO: Caretaker saving notification with ID: " + id);
        notificationHistory.put(id, notification.saveToMemento());
    }

    public void restoreNotification(String id, Notification notification) {
        if (notificationHistory.containsKey(id)) {
            System.out.println("MEMENTO: Caretaker restoring notification with ID: " + id);
            notification.restoreFromMemento(notificationHistory.get(id));
        } else {
            System.out.println("MEMENTO: No saved state found for notification with ID: " + id);
        }
    }
}
