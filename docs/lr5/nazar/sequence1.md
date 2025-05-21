```mermaid
sequenceDiagram
    participant Client
    participant Facade
    participant EmailAdapter
    participant LegacyEmailService

    Client->>Facade: notifyAll()
    Facade->>EmailAdapter: send()
    EmailAdapter->>LegacyEmailService: sendEmail()
```
