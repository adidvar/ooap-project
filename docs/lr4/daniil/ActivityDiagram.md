```mermaid
%% Activity Diagram for "Reminder for Bill Payments"
flowchart TD
    A[Початок] --> B[Student створює Payment]
    B --> C{Дата оплати задана?}
    C -- Ні --> Z1[Показати помилку] --> Z2[Кінець]
    C -- Так --> D[Payment передається до NotificationManager]
    D --> E[Створити Notification з dueDate - 3 дні]
    E --> F[Створити Notification з dueDate]
    F --> G{Час надсилання настав?}
    G -- Ні --> G
    G -- Так --> H[Надіслати Notification]
    H --> I{Інтернет з'єднання доступне?}
    I -- Ні --> J[Позначити як isDelivered = false]
    J --> K[Запланувати повторну спробу через 1 год]
    K --> G
    I -- Так --> L[Позначити як isDelivered = true]
    L --> M[Student отримує Notification]
    M --> N[Кінець]

    style A fill:#dfd
    style N fill:#fdd
    style Z2 fill:#fdd

