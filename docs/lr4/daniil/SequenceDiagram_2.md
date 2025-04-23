```mermaid
sequenceDiagram

actor Student
participant NotificationController
participant NotificationManager
participant PaymentRepository

    Student->>NotificationController: ПеревіритиНаближенняПлатежів()
    NotificationController->>NotificationManager: checkDueDates()
    NotificationManager->>PaymentRepository: findPaymentsDueIn3Days()
    PaymentRepository-->>NotificationManager: список платежів
    NotificationManager-->>NotificationController: список ID
    NotificationController-->>Student: список ID для сповіщення
