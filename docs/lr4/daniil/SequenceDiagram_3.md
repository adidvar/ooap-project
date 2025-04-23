```mermaid
sequenceDiagram

actor SystemScheduler
participant NotificationController
participant NotificationManager
participant Notification

    SystemScheduler->>NotificationController: НадіслатиСповіщення(платіжID, тип)
    NotificationController->>NotificationManager: sendNotification(платіжID, тип)
    NotificationManager->>Notification: new Notification(платіжID, тип)
    Notification-->>NotificationManager: статус відправки
    NotificationManager-->>NotificationController: статус, час
    NotificationController-->>SystemScheduler: статус, час
