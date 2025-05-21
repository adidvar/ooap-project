```mermaid
sequenceDiagram
    participant Client
    participant Proxy
    participant LoggingDecorator
    participant SimpleSender

    Client->>Proxy: send()
    Proxy->>LoggingDecorator: send()
    LoggingDecorator->>SimpleSender: send()
```
