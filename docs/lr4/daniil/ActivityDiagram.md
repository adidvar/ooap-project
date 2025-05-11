```mermaid
%% Activity Diagram for "Reminder for Bill Payments"
flowchart TD
    A[Початок] --> B[Student створює Payment]
    B --> C{ }
    C -- [Дата оплати не задана] --> Z1[Показати помилку] --> R[Кінець]
    C -- [Дата оплати задана] --> D[Payment передається до NotificationManager]
    D --> E[Створити Notification з dueDate - 3 дні]
    E --> F[Створити Notification з dueDate]
    F --> Q{ }
    Q --> G[Перевірити дату платежу]
    G --> P{ }
    P -- [Час надсилання не настав] --> Q
    P -- [Час надсилання настав] --> H[Надіслати Notification]
    H --> I{ }
    I -- [Інтернет з'єднання не доступне] --> J[Позначити якisDelivered = false]
    J --> K[Запланувати повторну спробу через 1 год]
    K --> Q
    I -- [Інтернет з'єднання доступне] --> L[Позначити як isDelivered = true]
    L --> M[Student отримує Notification]
    M --> R{ }
    R --> N[Кінець]

    style A fill:#dfd
    style N fill:#fdd
