package main.com.studentfinance.service;

public interface ExternalNotificationService {
    void pushAlert(String userId, String alertText, int priority);
    boolean checkServiceStatus();
}
