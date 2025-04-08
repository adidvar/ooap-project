```mermaid
classDiagram
%% Entity classes

class Student {
- String id
- String name
- String email
+ viewBalance()
+ addExpense()
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

class Expense {
- Double amount
- Date date
- Category category
- String description
+ create()
+ edit()
+ delete()
  }

class Category {
- String id
- String name
- String type
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
class ExpenseManager {
+ addExpense(Expense)
+ updateBalance(Transaction)
+ getExpensesByCategory()
+ getMonthlyExpenses()
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
+ importTransactions()
  }

%% Relationships
Student *-- Balance
Student o-- Expense
Student o-- Payment
Student o-- FinancialGoal
Student o-- Notification
Student o-- Transaction
Student o-- ConsultationRequest

Expense *-- Category
Transaction *-- Category
Transaction *-- TransactionType

ExpenseManager o-- Expense
ExpenseManager o-- Transaction

NotificationManager o-- Payment
NotificationManager o-- Notification

GoalManager o-- FinancialGoal

ImportManager o-- Transaction

Payment o-- Notification
FinancialGoal o-- Notification
