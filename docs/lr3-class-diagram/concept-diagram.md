```mermaid
classDiagram
    class Student
    class Balance
    class Category
    class Transaction
    class TransactionType {
    <<enumeration>>
    }
    class Payment
    class Budget
    class BudgetType {
    <<enumeration>>
    }
    class FinancialGoal
    class Notification
    class NotificationType {
    <<enumeration>>
    }
    class ConsultationRequest
    class ConsultationStatus {
    <<enumeration>>
    }
    class Currency

    %% Зв'язки між сутностями з типами асоціацій
    Student "1" -- "1" Balance : має
    Student "1" -- "0..*" ConsultationRequest : створює
    Student "1" -- "0..*" Transaction : здійснює
    Student "1" -- "0..*" Budget : планує
    Student "1" -- "0..*" FinancialGoal : встановлює
    Student "1" -- "0..*" Payment : оплачує

    Transaction "0..*" -- "1" Category : належить до
    Transaction ..> TransactionType : має тип
    Transaction "0..*" -- "1" Currency : виражена в

    Budget ..> BudgetType : має тип
    Budget "1" -- "0..*" Category : включає

    Notification ..> NotificationType : має тип

    ConsultationRequest ..> ConsultationStatus : має статус

    FinancialGoal "0..*" -- "1" Currency : виражена в

    Payment "1" -- "0..*" Notification : генерує
    FinancialGoal "1" -- "0..*" Notification : генерує
    Budget "1" -- "0..*" Notification : генерує
