```mermaid
graph TD
    A[Client]
    B[SenderProxy]
    C[LoggingDecorator]
    D[PriorityDecorator]
    E[SimpleSender]

    A --> B
    B --> C
    C --> D
    D --> E
```
