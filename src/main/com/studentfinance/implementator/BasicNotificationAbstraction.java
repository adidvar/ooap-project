package main.com.studentfinance.implementator;

import main.com.studentfinance.model.NotificationType;

public class BasicNotificationAbstraction extends NotificationAbstraction {
    protected String message;
    protected String recipient;
    protected NotificationType type;

    public BasicNotificationAbstraction(NotificationImplementor implementor,
                                        String message,
                                        String recipient,
                                        NotificationType type) {
        super(implementor);
        this.message = message;
        this.recipient = recipient;
        this.type = type;
    }

    @Override
    public boolean send() {
        return implementor.sendNotification(message, recipient, type);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }
}
