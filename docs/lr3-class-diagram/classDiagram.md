```mermaid
classDiagram
    %% Entity classes
    class Student {
        - String id
        - String name
        - String email
        - String passwordHash
        + viewDashboard()
        + addPayment(Payment): void
    }

    class Balance {
        - Double amount
        - Date lastUpdated
        - Currency currency
        + updateBalance()
        + getBalance()
        + convertCurrency()
    }

    class Category {
        - String id
        - String name
        - String type
        - String icon
        + create()
        + edit()
        + delete()
    }

    class Payment {
        - Double amount
        - Date dueDate
        - Boolean isPaid
        - String description
        + create()
        + markAsPaid()
        + delete()
        + setRecurring()
        + isDueInDays(int days): boolean
    }

    class Notification {
        - String id
        - String message
        - Date triggerDate
        - Boolean isDelivered
        - NotificationType type
        - NotificationStatus status
        + create()
        + send(): boolean
        + cancel()
        + schedule(Date triggerDate)
        + retry()
        + getStatus(): NotificationStatus
        + snooze(Duration)
    }
    
    class NotificationStatus {
      <<enumeration>>
      CREATED
      SCHEDULED
      SNOOZED
      DELIVERED
      FAILED
      CANCELED
      RETRY_SCHEDULED
    }


    class NotificationType {
        <<enumeration>>
        PAYMENT_REMINDER
        BUDGET_ALERT
    }

    class FinancialGoal {
        - String id
        - String title
        - Double targetAmount
        - Double currentAmount
        - Date deadline
        + create()
        + updateProgress()
        + calculatePercentage()
    }


    class Transaction {
        - String id
        - Double amount
        - Date date
        - Category category
        - String description
        - TransactionType type
        - String attachmentURL
        + create()
        + edit()
        + delete()
        + addAttachment()
        + categorize()
    }

    class TransactionType {
        <<enumeration>>
        INCOME
        EXPENSE
        TRANSFER
    }

    class ConsultationRequest {
        - String id
        - String question
        - Date timestamp
        - String response
        - ConsultationStatus status
        + createRequest()
        + setResponse()
        + updateStatus()
    }

    class ConsultationStatus {
        <<enumeration>>
        PENDING
        ANSWERED
        CLOSED
    }

    class Budget {
        - String id
        - Double totalAmount
        - Map~Category,Double~ allocations
        - Date startDate
        - Date endDate
        - BudgetType type
        + create()
        + edit()
        + calculateSpent()
        + calculateRemaining()
    }

    class BudgetType {
        <<enumeration>>
        WEEKLY
        MONTHLY
        SEMESTER
        CUSTOM
    }

    class Currency {
        - String code
        - String symbol
        - Double exchangeRate
        + getExchangeRate()
        + convert(Currency, Double)
    }

    %% Control classes


    class FinanceManager {
        + updateBalance(Transaction)
        + getFinancesByCategory()
        + getMonthlyFinances()
        + generateFinancialSummary()
        + predictFutureExpenses()
    }

    class NotificationManager {
        + scheduleNotification(Payment)
        + checkDueDates()
        + sendNotification(Notification)
        + getUserPreferences()
        + setUserPreferences()
        + sendPaymentReminder(Student): void
    }

    class GoalManager {
        + createGoal(FinancialGoal)
        + trackProgress(FinancialGoal)
        + calculateRequiredSavings()
        + suggestGoals()
        + analyzeGoalFeasibility()
    }

    class ImportManager {
        + importTransactions(File)
        + exportTransactions(Format)
        + addTransaction(Transaction)
        + deleteTransaction(Transaction)
        + editTransaction(Transaction)
        + detectDuplicates()
    }

    class BudgetManager {
        + createBudget(Budget)
        + trackBudgetProgress()
        + suggestBudgetAdjustments()
        + analyzeBudgetEfficiency()
    }

    class AnalyticsManager {
        + generateSpendingReport()
        + generateSavingsReport()
        + compareBudgetActual()
        + identifySpendingPatterns()
        + suggestOptimizations()
    }

    %% Relationships
    Transaction *-- Category
    Transaction -- Currency
    FinancialGoal -- Currency
    Budget o-- Category

    Student *-- Balance
    Student o-- ConsultationRequest
    Student o-- Transaction
    Student o-- Budget
    Student o-- FinancialGoal
    Student o-- Payment

    FinanceManager o-- Transaction
    FinanceManager o-- Balance
    FinanceManager -- Currency

    BudgetManager o-- Budget
    BudgetManager -- FinanceManager

    NotificationManager *-- Notification
    NotificationManager -- Payment
    NotificationManager -- FinancialGoal
    NotificationManager -- Budget

    Payment o-- Notification
    FinancialGoal o-- Notification

    GoalManager o-- FinancialGoal
    GoalManager -- FinanceManager

    ImportManager o-- Transaction

    AnalyticsManager -- FinanceManager
    AnalyticsManager -- BudgetManager
    AnalyticsManager -- GoalManager

    %% System access points
    Student -- FinanceManager : uses
    Student -- NotificationManager : uses
    Student -- GoalManager : uses
    Student -- ImportManager : uses
    Student -- BudgetManager : uses
    Student -- AnalyticsManager : uses

