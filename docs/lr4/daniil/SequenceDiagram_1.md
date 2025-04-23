```mermaid
sequenceDiagram

actor Student
participant PaymentController
participant PaymentService
participant Payment

    Student->>PaymentController: ДодатиЗапланованийПлатіж(сума, дата, опис)
    PaymentController->>PaymentService: createPayment(сума, дата, опис)
    PaymentService->>Payment: new Payment(сума, дата, опис)
    Payment-->>PaymentService: paymentId
    PaymentService-->>PaymentController: paymentId
    PaymentController-->>Student: підтвердження збереження (paymentId)
