```mermaid
classDiagram
direction BT
class BasicNotificationAbstraction {
  + BasicNotificationAbstraction(NotificationImplementor, String, String, NotificationType) 
  # NotificationType type
  # String recipient
  # String message
}
class BridgedNotificationFactory {
  + BridgedNotificationFactory() 
}
class BridgedNotificationManager {
  - BridgedNotificationManager() 
  - BridgedNotificationManager instance
}
class EmailNotificationImplementor {
  + EmailNotificationImplementor() 
}
class ExternalNotificationAdapter {
  + ExternalNotificationAdapter(ExternalNotificationService) 
  - ExternalNotificationService externalService
}
class ExternalNotificationService {
<<Interface>>

}
class LegacyMessenger {
  + LegacyMessenger() 
}
class LegacyNotificationAdapter {
  + LegacyNotificationAdapter() 
  - LegacyMessenger legacyMessenger
}
class NotificationAbstraction {
  + NotificationAbstraction(NotificationImplementor) 
  # NotificationImplementor implementor
}
class NotificationImplementor {
<<Interface>>

}
class Payment {
  + Payment(String, double, Date) 
  - Payment(PaymentBuilder) 
  - List~Notification~ notifications
  - List~NotificationComponent~ compNotifications
  - Date dueDate
  - boolean isRecurring
  - double amount
  - boolean isPaid
  - List~NotificationAbstraction~ absNotifications
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
class PaymentNotificationAbstraction {
  + PaymentNotificationAbstraction(NotificationImplementor, Payment, String) 
  # String recipient
  # Payment payment
}
class PushNotificationImplementor {
  + PushNotificationImplementor() 
}
class SMSNotificationImplementor {
  + SMSNotificationImplementor() 
}
class Student {
  + Student(String, String, String) 
  + Student(String, String, String, String) 
  - List~Payment~ payments
  - String name
  - String email
  - String id
  - String passwordHash
}
class ThirdPartyNotificationService {
  + ThirdPartyNotificationService() 
}

BasicNotificationAbstraction  -->  NotificationAbstraction 
BridgedNotificationFactory  ..>  BasicNotificationAbstraction : «create»
BridgedNotificationFactory  ..>  EmailNotificationImplementor : «create»
BridgedNotificationFactory  ..>  ExternalNotificationAdapter : «create»
BridgedNotificationFactory  ..>  LegacyNotificationAdapter : «create»
BridgedNotificationFactory  ..>  PaymentNotificationAbstraction : «create»
BridgedNotificationFactory  ..>  PushNotificationImplementor : «create»
BridgedNotificationFactory  ..>  SMSNotificationImplementor : «create»
BridgedNotificationFactory  ..>  ThirdPartyNotificationService : «create»
EmailNotificationImplementor  ..>  NotificationImplementor 
ExternalNotificationAdapter "1" *--> "externalService 1" ExternalNotificationService 
ExternalNotificationAdapter  ..>  NotificationImplementor 
LegacyNotificationAdapter  -->  LegacyMessenger 
LegacyNotificationAdapter "1" *--> "legacyMessenger 1" LegacyMessenger 
LegacyNotificationAdapter  ..>  LegacyMessenger : «create»
LegacyNotificationAdapter  ..>  NotificationImplementor 
NotificationAbstraction "1" *--> "implementor 1" NotificationImplementor 
Payment "1" *--> "absNotifications *" NotificationAbstraction 
Payment  -->  PaymentBuilder 
PaymentBuilder  ..>  Payment : «create»
PaymentNotificationAbstraction  -->  NotificationAbstraction 
PaymentNotificationAbstraction "1" *--> "payment 1" Payment 
PushNotificationImplementor  ..>  NotificationImplementor 
SMSNotificationImplementor  ..>  NotificationImplementor 
Student "1" *--> "payments *" Payment 
ThirdPartyNotificationService  ..>  ExternalNotificationService 
