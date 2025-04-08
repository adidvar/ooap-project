```mermaid
classDiagram
%% Entity classes

class Student {
- String id
- String name
- String email
+ viewBalance()
+ setPaymentReminder()
+ createFinancialGoal()
+ importTransactions()
+ requestConsultation()
  }

class Balance {
- Double amount
- Date lastUpdated
+ updateBalance()
+ getBalance()
  }

class Category {
- String id
- String name
- String type
+ create()
+ edit()
+ delete()
  }

class PaymentManager { }

class Payment {
- Double amount
- Date dueDate
- Boolean isPaid
- String description
+ create()
+ markAsPaid()
+ delete()
  }

class Notification {
- String id
- String message
- Date triggerDate
- Boolean isDelivered
+ create()
+ send()
+ cancel()
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
+ create()
+ edit()
+ delete()
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
+ createRequest()
+ setResponse()
  }

%% Control classes
class FinanceManager {
+ updateBalance(Transaction)
+ getFinancesByCategory()
+ getMonthlyFinances()
  }

class NotificationManager {
+ scheduleNotification(Payment)
+ checkDueDates()
+ sendNotification(Notification)
  }

class GoalManager {
+ createGoal(FinancialGoal)
+ trackProgress(FinancialGoal)
+ calculateRequiredSavings()
  }

class ImportManager {
+ importTransactions(File)
+ addTransaction(Transaction)
+ deleteTransaction(Transaction)
+ editTransaction(Transaction)
  }

%% Relationships

Transaction *-- Category
Transaction *-- TransactionType

FinanceManager o-- Transaction

PaymentManager *-- Payment

NotificationManager *-- Notification

Payment o-- Notification
FinancialGoal o-- Notification

GoalManager o-- FinancialGoal

ImportManager o-- Transaction


Student *-- Balance
Student o-- ConsultationRequest
Student o-- PaymentManager
Student o-- GoalManager
Student o-- NotificationManager
Student o-- FinanceManager
Student o-- ImportManager

