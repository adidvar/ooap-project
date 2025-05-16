package main.com.studentfinance.service;

public class ThirdPartyNotificationService implements ExternalNotificationService {
    @Override
    public void pushAlert(String userId, String alertText, int priority) {
        System.out.println("Зовнішній сервіс: Відправка сповіщення користувачу " + userId);
        System.out.println("Пріоритет: " + priority);
        System.out.println("Текст: " + alertText);
    }

    @Override
    public boolean checkServiceStatus() {
        // В реальному додатку тут був би запит для перевірки статусу API
        return true;
    }
}
