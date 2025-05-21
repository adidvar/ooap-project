```mermaid
graph TD
    A[Client App]
    B[NotificationFacade]
    C[EmailAdapter]
    D[SmsAdapter]
    E[LegacyEmailService]
    F[LegacySmsService]

    A --> B
    B --> C
    B --> D
    C --> E
    D --> F
```
