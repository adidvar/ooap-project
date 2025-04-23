```mermaid
sequenceDiagram

actor Scheduler
participant NotificationController
participant NotificationManager
participant Payment
participant Notification

    Scheduler->>NotificationController: НагадатиУДеньОплати(платіжID)
    NotificationController->>NotificationManager: scheduleNotification(платіжID)
    NotificationManager->>Payment: getPaymentById(платіжID)
    Payment-->>NotificationManager: Payment
    NotificationManager->>Notification: new Notification(Payment)
    Notification-->>NotificationManager: статус
    NotificationManager-->>NotificationController: результат
    NotificationController-->>Scheduler: результат
