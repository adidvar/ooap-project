```mermaid
classDiagram
direction BT
class AnalyticsManager {
  + AnalyticsManager() 
  + suggestOptimizations() List~String~
  + generateSavingsReport() Map~String, Object~
  + compareBudgetActual() Map~Category, Double~
  + identifySpendingPatterns() List~String~
  + generateSpendingReport() Map~String, Object~
}
class Balance {
  + Balance() 
  + convertCurrency() void
  + updateBalance() void
   Double balance
}
class BasicNotification {
  + BasicNotification(String) 
  # Date triggerDate
  # NotificationType type
  # String message
  # boolean internetAvailable
  + send() boolean
   boolean internetAvailable
   Date triggerDate
   String message
   NotificationType type
}
class BasicNotificationAbstraction {
  + BasicNotificationAbstraction(NotificationImplementor, String, String, NotificationType) 
  # String recipient
  # String message
  + send() boolean
   String message
   String recipient
}
class BridgedNotificationFactory {
  + BridgedNotificationFactory() 
  + createBasicNotification(String, String, NotificationType, String) NotificationAbstraction
  - getImplementor(String) NotificationImplementor
  + createPaymentNotification(Payment, String, String) NotificationAbstraction
}
class BridgedNotificationManager {
  - BridgedNotificationManager() 
  - BridgedNotificationManager instance
  + sendNotification(NotificationAbstraction) boolean
  + checkDueDates() void
  + schedulePaymentReminders(Payment, Student) void
   BridgedNotificationManager instance
}
class Budget {
  + Budget() 
  + edit() void
  + calculateRemaining() Double
  + calculateSpent() Double
  + create() void
}
class BudgetAlertDecorator {
  + BudgetAlertDecorator(NotificationComponent, String, double, double) 
  + send() boolean
   String message
}
class BudgetAlertFactory {
  + BudgetAlertFactory() 
  + createNotification(String, NotificationType) Notification
}
class BudgetAlertNotification {
  + BudgetAlertNotification(String) 
  + send() boolean
}
class BudgetManager {
  + BudgetManager() 
  + trackBudgetProgress() Map~Category, Double~
  + analyzeBudgetEfficiency() Map~String, Object~
  + createBudget(Budget) void
  + suggestBudgetAdjustments() List~String~
}
class BudgetType {
<<enumeration>>
  + BudgetType() 
  + valueOf(String) BudgetType
  + values() BudgetType[]
}
class Category {
  + Category() 
  + delete() void
  + edit() void
  + create() void
}
class CompositeNotification {
  + CompositeNotification(String) 
  - Date triggerDate
  + send() boolean
  + removeNotification(NotificationComponent) void
  + addNotification(NotificationComponent) void
   Date triggerDate
   String message
}
class ConsultationRequest {
  + ConsultationRequest() 
  - String response
  + createRequest() void
  + updateStatus(ConsultationStatus) void
   String response
}
class ConsultationStatus {
<<enumeration>>
  + ConsultationStatus() 
  + valueOf(String) ConsultationStatus
  + values() ConsultationStatus[]
}
class Currency {
  + Currency() 
  - Double exchangeRate
  + convert(Currency, Double) Double
   Double exchangeRate
}
class EmailNotificationImplementor {
  + EmailNotificationImplementor() 
  + sendNotification(String, String, NotificationType) boolean
   boolean deliveryChannelAvailable
}
class ExternalNotificationAdapter {
  + ExternalNotificationAdapter(ExternalNotificationService) 
  + sendNotification(String, String, NotificationType) boolean
  - convertTypeToPriority(NotificationType) int
   boolean deliveryChannelAvailable
}
class ExternalNotificationService {
<<Interface>>
  + checkServiceStatus() boolean
  + pushAlert(String, String, int) void
}
class FinanceManager {
  + FinanceManager() 
  + generateFinancialSummary() String
  + updateBalance(Transaction) void
  + predictFutureExpenses() Map~Date, Double~
   Map~Date, Double~ monthlyFinances
   Map~Category, Double~ financesByCategory
}
class FinancialGoal {
  + FinancialGoal() 
  + create() void
  + updateProgress() void
  + calculatePercentage() double
}
class GoalManager {
  + GoalManager() 
  + createGoal(FinancialGoal) void
  + trackProgress(FinancialGoal) void
  + calculateRequiredSavings() Double
  + suggestGoals() List~FinancialGoal~
  + analyzeGoalFeasibility() Map~String, Object~
}
class ImportManager {
  + ImportManager() 
  + importTransactions(File) List~Transaction~
  + editTransaction(Transaction) void
  + addTransaction(Transaction) void
  + exportTransactions(String) File
  + deleteTransaction(Transaction) void
  + detectDuplicates() List~Transaction~
}
class LegacyMessenger {
  + LegacyMessenger() 
  + sendMsg(String, String) void
   boolean systemUp
}
class LegacyNotificationAdapter {
  + LegacyNotificationAdapter() 
  + sendNotification(String, String, NotificationType) boolean
   boolean deliveryChannelAvailable
}
class LoggingDecorator {
  + LoggingDecorator(NotificationComponent) 
  + send() boolean
}
class Notification {
  + Notification(String) 
  # String id
  # NotificationType type
  # NotificationStatus status
  # String message
  # Date triggerDate
  # boolean isDelivered
  + snooze(Duration) void
  + send() boolean
  + schedule(Date) void
  + create() void
  + archive() void
  + cancel() void
  + retry() void
   boolean internetAvailable
   boolean isDelivered
   NotificationStatus status
   String message
   NotificationType type
   Date triggerDate
   String id
}
class NotificationAbstraction {
  + NotificationAbstraction(NotificationImplementor) 
  # NotificationImplementor implementor
  + send() boolean
   NotificationImplementor implementor
   String message
}
class NotificationComponent {
<<Interface>>
  + send() boolean
   Date triggerDate
   String message
}
class NotificationDecorator {
  + NotificationDecorator(NotificationComponent) 
  + send() boolean
   Date triggerDate
   String message
}
class NotificationFactory {
  + NotificationFactory() 
  + createComprehensivePaymentReminder(String, Payment, int) NotificationComponent
  + createRetryableNotification(String, NotificationType, int, int) NotificationComponent
  + createBudgetAlert(String, String, double, double) NotificationComponent
  + createNotification(String, NotificationType) Notification
  + createGroupedNotifications(String) CompositeNotification
  + createPaymentReminder(String, Payment, int) NotificationComponent
  + createBasicNotification(String, NotificationType) NotificationComponent
  + createLoggedNotification(String, NotificationType) NotificationComponent
  + prepareNotification(String, NotificationType) Notification
}
class NotificationImplementor {
<<Interface>>
  + sendNotification(String, String, NotificationType) boolean
   boolean deliveryChannelAvailable
}
class NotificationManager {
  - NotificationManager() 
  - NotificationManager instance
  + scheduleNotification(Payment) void
  + schedulePaymentReminders(Payment) void
  + getUserPreferences() void
  + setUserPreferences() void
  + checkDueDates() void
  + sendNotification(Notification) boolean
  + sendNotification(NotificationComponent) boolean
  + sendPaymentReminder(Student) void
   NotificationManager instance
}
class NotificationStatus {
<<enumeration>>
  + NotificationStatus() 
  + values() NotificationStatus[]
  + valueOf(String) NotificationStatus
}
class NotificationType {
<<enumeration>>
  + NotificationType() 
  + values() NotificationType[]
  + valueOf(String) NotificationType
}
class Payment {
  + Payment(String, double, Date) 
  - Payment(PaymentBuilder) 
  - Date dueDate
  - double amount
  - String description
  - String id
  - boolean isRecurring
  - boolean isPaid
  - List~Notification~ notifications
  + setRecurring() void
  + delete() void
  + markAsPaid() void
  + isDueInDays(int) boolean
  + addNotification(NotificationAbstraction) void
  + addNotification(NotificationComponent) void
  + addNotification(Notification) void
  + create() void
   String description
   double amount
   boolean isRecurring
   List~Notification~ notifications
   Date dueDate
   boolean isPaid
   String id
}
class PaymentBuilder {
  + PaymentBuilder() 
  + description(String) PaymentBuilder
  + isRecurring(boolean) PaymentBuilder
  + isPaid(boolean) PaymentBuilder
  + build() Payment
  + amount(double) PaymentBuilder
  + dueDate(Date) PaymentBuilder
}
class PaymentNotificationAbstraction {
  + PaymentNotificationAbstraction(NotificationImplementor, Payment, String) 
  + send() boolean
   String message
}
class PaymentReminderDecorator {
  + PaymentReminderDecorator(NotificationComponent, Payment, int) 
  + send() boolean
   String message
}
class PaymentReminderFactory {
  + PaymentReminderFactory() 
  + createNotification(String, NotificationType) Notification
}
class PaymentReminderNotification {
  + PaymentReminderNotification(String) 
  + send() boolean
}
class PushNotificationImplementor {
  + PushNotificationImplementor() 
  + sendNotification(String, String, NotificationType) boolean
   boolean deliveryChannelAvailable
}
class RetryDecorator {
  + RetryDecorator(NotificationComponent, int, int) 
  + send() boolean
}
class SMSNotificationImplementor {
  + SMSNotificationImplementor() 
  + sendNotification(String, String, NotificationType) boolean
   boolean deliveryChannelAvailable
}
class Student {
  + Student(String, String, String) 
  + Student(String, String, String, String) 
  - List~Payment~ payments
  - String id
  - String name
  - String email
  + addPayment(Payment) void
  + addPaymentPrimary(Payment) void
  + viewDashboard() void
   String name
   List~Payment~ payments
   String email
   String id
}
class ThirdPartyNotificationService {
  + ThirdPartyNotificationService() 
  + checkServiceStatus() boolean
  + pushAlert(String, String, int) void
}
class Transaction {
  + Transaction() 
  + create() void
  + delete() void
  + addAttachment() void
  + edit() void
  + categorize() void
}
class TransactionType {
<<enumeration>>
  + TransactionType() 
  + values() TransactionType[]
  + valueOf(String) TransactionType
}

AnalyticsManager "1" *--> "budgetManager 1" BudgetManager 
AnalyticsManager "1" *--> "financeManager 1" FinanceManager 
AnalyticsManager "1" *--> "goalManager 1" GoalManager 
BasicNotification  ..>  NotificationComponent 
BasicNotification "1" *--> "status 1" NotificationStatus 
BasicNotification "1" *--> "type 1" NotificationType 
BasicNotificationAbstraction  -->  NotificationAbstraction 
BasicNotificationAbstraction "1" *--> "type 1" NotificationType 
BridgedNotificationFactory  ..>  BasicNotificationAbstraction : «create»
BridgedNotificationFactory  ..>  EmailNotificationImplementor : «create»
BridgedNotificationFactory  ..>  ExternalNotificationAdapter : «create»
BridgedNotificationFactory  ..>  LegacyNotificationAdapter : «create»
BridgedNotificationFactory  ..>  PaymentNotificationAbstraction : «create»
BridgedNotificationFactory  ..>  PushNotificationImplementor : «create»
BridgedNotificationFactory  ..>  SMSNotificationImplementor : «create»
BridgedNotificationFactory  ..>  ThirdPartyNotificationService : «create»
Budget "1" *--> "type 1" BudgetType 
Budget "1" *--> "allocations *" Category 
BudgetAlertDecorator  -->  NotificationDecorator 
BudgetAlertFactory  ..>  BudgetAlertNotification : «create»
BudgetAlertFactory  -->  NotificationFactory 
BudgetAlertNotification  -->  Notification 
BudgetManager "1" *--> "budgets *" Budget 
BudgetManager "1" *--> "financeManager 1" FinanceManager 
CompositeNotification  ..>  NotificationComponent 
CompositeNotification "1" *--> "childNotifications *" NotificationComponent 
ConsultationRequest "1" *--> "status 1" ConsultationStatus 
EmailNotificationImplementor  ..>  NotificationImplementor 
ExternalNotificationAdapter "1" *--> "externalService 1" ExternalNotificationService 
ExternalNotificationAdapter  ..>  NotificationImplementor 
FinanceManager "1" *--> "balances *" Balance 
FinanceManager "1" *--> "defaultCurrency 1" Currency 
FinanceManager "1" *--> "transactions *" Transaction 
FinancialGoal "1" *--> "notifications *" Notification 
GoalManager "1" *--> "financeManager 1" FinanceManager 
GoalManager "1" *--> "goals *" FinancialGoal 
ImportManager "1" *--> "transactions *" Transaction 
LegacyNotificationAdapter  -->  LegacyMessenger 
LegacyNotificationAdapter "1" *--> "legacyMessenger 1" LegacyMessenger 
LegacyNotificationAdapter  ..>  LegacyMessenger : «create»
LegacyNotificationAdapter  ..>  NotificationImplementor 
LoggingDecorator  -->  NotificationDecorator 
Notification "1" *--> "status 1" NotificationStatus 
Notification "1" *--> "type 1" NotificationType 
NotificationAbstraction "1" *--> "implementor 1" NotificationImplementor 
NotificationDecorator  ..>  NotificationComponent 
NotificationDecorator "1" *--> "wrappedNotification 1" NotificationComponent 
NotificationFactory  ..>  BasicNotification : «create»
NotificationFactory  ..>  BudgetAlertDecorator : «create»
NotificationFactory  ..>  CompositeNotification : «create»
NotificationFactory  ..>  LoggingDecorator : «create»
NotificationFactory  ..>  PaymentReminderDecorator : «create»
NotificationFactory  ..>  RetryDecorator : «create»
NotificationManager  ..>  BudgetAlertFactory : «create»
NotificationManager "1" *--> "paymentReminderFactory 1" NotificationFactory 
NotificationManager  ..>  PaymentReminderFactory : «create»
Payment "1" *--> "notifications *" Notification 
Payment "1" *--> "absNotifications *" NotificationAbstraction 
Payment "1" *--> "compNotifications *" NotificationComponent 
Payment  -->  PaymentBuilder 
PaymentBuilder  ..>  Payment : «create»
PaymentNotificationAbstraction  -->  NotificationAbstraction 
PaymentNotificationAbstraction "1" *--> "payment 1" Payment 
PaymentReminderDecorator  -->  NotificationDecorator 
PaymentReminderDecorator "1" *--> "payment 1" Payment 
PaymentReminderFactory  -->  NotificationFactory 
PaymentReminderFactory  ..>  PaymentReminderNotification : «create»
PaymentReminderNotification  -->  Notification 
PushNotificationImplementor  ..>  NotificationImplementor 
RetryDecorator  -->  NotificationDecorator 
SMSNotificationImplementor  ..>  NotificationImplementor 
Student "1" *--> "payments *" Payment 
ThirdPartyNotificationService  ..>  ExternalNotificationService 
Transaction "1" *--> "category 1" Category 
Transaction "1" *--> "type 1" TransactionType 
