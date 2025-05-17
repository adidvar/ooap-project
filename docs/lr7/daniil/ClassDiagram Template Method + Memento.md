classDiagram
direction BT
class Notification {
  + Notification(String, Date) 
  + Notification(String) 
  # String id
  # String message
  # Date triggerDate
  # boolean isDelivered
  # NotificationType type
  # NotificationStatus status
  + paymentReminderNotification() PaymentReminderNotification
  + archive() void
  + send() boolean
  + saveToMemento() NotificationMemento
  + schedule(Date) void
  + create() void
  + restoreFromMemento(NotificationMemento) void
  + snooze(Duration) void
  + cancel() void
  + retry() void
   NotificationStatus status
   String message
   boolean internetAvailable
   NotificationType type
   boolean isDelivered
   Date triggerDate
   String id
}
class NotificationHistory {
  + NotificationHistory() 
  + restoreNotification(String, Notification) void
  + saveNotification(String, Notification) void
}
class NotificationManager {
  - NotificationManager() 
  - NotificationManager instance
  + registerObserver(PaymentObserver) void
  + sendNotification(NotificationTemplate) void
  + schedulePaymentReminders(Payment, Student) void
  + checkDueDates() void
   NotificationManager instance
}
class NotificationMemento {
  + NotificationMemento(String, Date, boolean) 
  - Date triggerDate
  - String message
  - boolean isDelivered
   Date triggerDate
   String message
   boolean isDelivered
}
class NotificationTemplate {
  + NotificationTemplate() 
  + processNotification() boolean
  # sendNotification() boolean
  # prepareNotification() void
  # logNotification(boolean) void
}
class PaymentReminderNotification {
  + PaymentReminderNotification(String, String, Date) 
  # sendNotification() boolean
  # prepareNotification() void
}
class UrgentPaymentReminderNotification {
  + UrgentPaymentReminderNotification(String, String, Date) 
  # sendNotification() boolean
  # prepareNotification() void
  # logNotification(boolean) void
}

Notification  ..>  NotificationMemento : «create»
Notification  ..>  PaymentReminderNotification : «create»
NotificationHistory "1" *--> "notificationHistory *" NotificationMemento 
NotificationManager  ..>  Notification : «create»
NotificationManager  ..>  NotificationHistory : «create»
NotificationManager "1" *--> "notificationHistory 1" NotificationHistory 
PaymentReminderNotification  -->  NotificationTemplate 
UrgentPaymentReminderNotification  -->  NotificationTemplate 
