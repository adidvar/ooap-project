package main.com.studentfinance.model;

import java.util.Date;

public interface NotificationComponent {
    boolean send();
    String getMessage();
    Date getTriggerDate();
}
