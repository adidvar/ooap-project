```mermaid
sequenceDiagram
    participant Client
    participant Proxy
    participant LoggingDecorator
    participant PriorityDecorator
    participant SimpleSender

    Client->>Proxy: send()
    Proxy->>LoggingDecorator: send()
    LoggingDecorator->>PriorityDecorator: send()
    PriorityDecorator->>SimpleSender: send()
```
