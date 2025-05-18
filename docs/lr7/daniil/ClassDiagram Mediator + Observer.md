```mermaid
classDiagram
direction BT
class NotificationManager {
  - NotificationManager() 
  - NotificationManager instance
  + schedulePaymentReminders(Payment, Student) void
  + checkDueDates() void
  + sendNotification(NotificationTemplate) void
  + registerObserver(PaymentObserver) void
   NotificationManager instance
}
class NotificationMediator {
<<Interface>>
  + schedulePaymentReminders(Payment, Student) void
  + sendNotification(NotificationTemplate) void
  + registerObserver(PaymentObserver) void
}
class Payment {
  + Payment(String, double, Date, String) 
  + Payment(String, double, Date) 
  - Payment(PaymentBuilder) 
  - Date dueDate
  - boolean isRecurring
  - double amount
  - List~Notification~ notifications
  - String id
  - boolean isPaid
  - String description
  + addObserver(PaymentObserver) void
  + notifyObservers() void
  + create() void
  + delete() void
  + addNotification(NotificationAbstraction) void
  + removeObserver(PaymentObserver) void
  + addNotification(NotificationComponent) void
  + markAsPaid() void
  + setRecurring() void
  + addNotification(Notification) void
  + isDueInDays(int) boolean
   String description
   boolean isRecurring
   List~Notification~ notifications
   Date dueDate
   boolean isPaid
   String id
   double amount
}
class PaymentBuilder {
  + PaymentBuilder() 
  + amount(double) PaymentBuilder
  + isRecurring(boolean) PaymentBuilder
  + build() Payment
  + description(String) PaymentBuilder
  + dueDate(Date) PaymentBuilder
  + isPaid(boolean) PaymentBuilder
}
class PaymentNotificationObserver {
  + PaymentNotificationObserver(NotificationMediator) 
  + update(Payment) void
}
class PaymentObserver {
<<Interface>>
  + update(Payment) void
}

NotificationManager  ..>  NotificationMediator 
NotificationManager "1" *--> "observers *" PaymentObserver 
Payment "1" *--> "observers *" PaymentObserver 
Payment  -->  PaymentBuilder 
PaymentBuilder  ..>  Payment : «create»
PaymentNotificationObserver "1" *--> "mediator 1" NotificationMediator 
PaymentNotificationObserver  ..>  PaymentObserver 
