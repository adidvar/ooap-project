package main.com.studentfinance.implementator;

public abstract class NotificationAbstraction {
    protected NotificationImplementor implementor;

    public NotificationAbstraction(NotificationImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract boolean send();
    public abstract String getMessage();

    // Перемикання реалізації під час виконання
    public void setImplementor(NotificationImplementor implementor) {
        this.implementor = implementor;
    }
}
