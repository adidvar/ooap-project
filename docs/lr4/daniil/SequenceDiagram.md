```mermaid
sequenceDiagram
    actor Student

    participant PaymentController
    participant NotificationController
    participant PaymentRepository
    participant NotificationManager

    %% 1. Додати запланований платіж
    Student->>PaymentController: додатиПлатіж(сума, дата, опис)
    PaymentController->>PaymentRepository: save(платіж)
    PaymentRepository-->>PaymentController: paymentId
    PaymentController-->>Student: підтвердження(paymentId)

    %% 2. Перевірити наближення платежів
    Student->>NotificationController: перевіритиНаближення()
    NotificationController->>NotificationManager: checkDueDates()
    NotificationManager->>PaymentRepository: findDueIn(3 дні)
    PaymentRepository-->>NotificationManager: списокПлатежів
    NotificationManager-->>NotificationController: списокID
    NotificationController-->>Student: списокID

    %% 3. Надіслати сповіщення
    Student->>NotificationController: надіслатиСповіщення(id, тип)
    NotificationController->>NotificationManager: sendNotification(id, тип)
    NotificationManager-->>NotificationController: статус, час
    NotificationController-->>Student: статус, час

    %% 4. Нагадати у день оплати
    Student->>NotificationController: нагадатиУДеньОплати(id)
    NotificationController->>NotificationManager: scheduleNotification(id)
    NotificationManager-->>NotificationController: статус
    NotificationController-->>Student: статус

