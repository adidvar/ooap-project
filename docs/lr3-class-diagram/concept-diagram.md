classDiagram
    class Student
    class Balance
    class Category
    class Transaction
    class TransactionType
    class Payment
    class Budget
    class BudgetType
    class FinancialGoal
    class Notification
    class NotificationType
    class ConsultationRequest
    class ConsultationStatus
    class Currency

    %% Зв'язки між сутностями з типами асоціацій
    Student "1" -- "1" Balance : має
    Student "1" -- "0..*" ConsultationRequest : створює
    Student "1" -- "0..*" Transaction : здійснює
    Student "1" -- "0..*" Budget : планує
    Student "1" -- "0..*" FinancialGoal : встановлює
    Student "1" -- "0..*" Payment : оплачує

    Transaction "0..*" -- "1" Category : належить до
    Transaction "0..*" -- "1" TransactionType : має тип
    Transaction "0..*" -- "1" Currency : виражена в

    Budget "1" -- "1" BudgetType : має тип
    Budget "1" -- "0..*" Category : включає

    Notification "0..*" -- "1" NotificationType : має тип

    ConsultationRequest "0..*" -- "1" ConsultationStatus : має статус

    FinancialGoal "0..*" -- "1" Currency : виражена в

    Payment "1" -- "0..*" Notification : генерує
    FinancialGoal "1" -- "0..*" Notification : генерує
    Budget "1" -- "0..*" Notification : генерує
