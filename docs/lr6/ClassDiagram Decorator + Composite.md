classDiagram
direction BT
class BasicNotification {
  + BasicNotification(String) 
  # NotificationType type
  # Date triggerDate
  # String id
  # String message
  # boolean internetAvailable
  # NotificationStatus status
  # boolean isDelivered
}
class BudgetAlertDecorator {
  + BudgetAlertDecorator(NotificationComponent, String, double, double) 
  - String budgetName
  - double threshold
  - double currentAmount
}
class CompositeNotification {
  + CompositeNotification(String) 
  - String groupName
  - List~NotificationComponent~ childNotifications
  - Date triggerDate
}
class NotificationFactory {
  + NotificationFactory() 
}
class LoggingDecorator {
  + LoggingDecorator(NotificationComponent) 
}
class NotificationComponent {
<<Interface>>

}
class NotificationDecorator {
  + NotificationDecorator(NotificationComponent) 
  # NotificationComponent wrappedNotification
}
class NotificationManager {
  - NotificationManager() 
  - NotificationManager instance
  - NotificationFactory budgetAlertFactory
  - NotificationFactory paymentReminderFactory
}
class NotificationStatus {
<<enumeration>>
  + NotificationStatus() 
  +  PENDING
  +  SCHEDULED
  +  SENT
  +  ARCHIVED
  +  FAILED
  +  SENDING
  +  SNOOZED
  +  DELIVERED
  +  RETRY_SCHEDULED
  +  CANCELLED
  +  CANCELED
  +  CREATED
}
class NotificationType {
<<enumeration>>
  + NotificationType() 
  +  PAYMENT_REMINDER
  +  INFO
  +  ALERT
  +  PAYMENT
  +  BUDGET_ALERT
  +  WARNING
}
class Payment {
  - Payment(PaymentBuilder) 
  - List~Notification~ notifications
  - Date dueDate
  - boolean isRecurring
  - double amount
  - boolean isPaid
  - List~NotificationComponent~ compNotifications
  - String id
  - String description
}
class PaymentBuilder {
  + PaymentBuilder() 
  - double amount
  - boolean isRecurring
  - String description
  - String id
  - Date dueDate
  - boolean isPaid
}
class PaymentReminderDecorator {
  + PaymentReminderDecorator(NotificationComponent, Payment, int) 
  - Payment payment
  - int daysBeforeDue
}
class RetryDecorator {
  + RetryDecorator(NotificationComponent, int, int) 
  - int retryDelayMinutes
  - int maxRetries
}
class Student {
  + Student(String, String, String, String) 
  - List~Payment~ payments
  - String name
  - String email
  - String id
  - String passwordHash
}

BasicNotification  ..>  NotificationComponent 
BasicNotification "1" *--> "status 1" NotificationStatus 
BasicNotification "1" *--> "type 1" NotificationType 
BudgetAlertDecorator  -->  NotificationDecorator 
CompositeNotification  ..>  NotificationComponent 
CompositeNotification "1" *--> "childNotifications *" NotificationComponent
NotificationFactory  ..>  BasicNotification : «create»
NotificationFactory  ..>  BudgetAlertDecorator : «create»
NotificationFactory  ..>  CompositeNotification : «create»
NotificationFactory  ..>  LoggingDecorator : «create»
NotificationFactory  ..>  PaymentReminderDecorator : «create»
NotificationFactory  ..>  RetryDecorator : «create»
LoggingDecorator  -->  NotificationDecorator 
NotificationDecorator  ..>  NotificationComponent 
NotificationDecorator "1" *--> "wrappedNotification 1" NotificationComponent 
Payment "1" *--> "compNotifications *" NotificationComponent 
Payment  -->  PaymentBuilder 
PaymentBuilder  ..>  Payment : «create»
PaymentReminderDecorator  -->  NotificationDecorator 
PaymentReminderDecorator "1" *--> "payment 1" Payment 
RetryDecorator  -->  NotificationDecorator 
Student "1" *--> "payments *" Payment 
