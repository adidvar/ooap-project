```mermaid
flowchart TD
    Start([Початок]) --> UserCreatesPayment[Користувач створює платіж]
    UserCreatesPayment --> SetPaymentDetails[Вказує деталі платежу (сума, дата, опис)]
    SetPaymentDetails --> SavePayment[Система зберігає платіж]

    SavePayment --> CheckDueDate{Дата оплати\nу майбутньому?}
    CheckDueDate -->|Так| ScheduleReminderViaManager[PaymentController -> NotificationManager.schedulePaymentReminder()]
    CheckDueDate -->|Ні| EndPaymentProcess([Завершення процесу створення платежу])

    ScheduleReminderViaManager --> FactoryCreatesNotification[NotificationManager -> NotificationFactory.createNotification()]
    FactoryCreatesNotification --> BuilderConstructsNotification[NotificationFactory -> Notification.Builder.build()]
    BuilderConstructsNotification --> InitialStateScheduled[Notification.state = SCHEDULED]
    InitialStateScheduled --> ManagerSchedules[NotificationManager зберігає та планує сповіщення]
    ManagerSchedules --> EndPaymentProcess


    %% Окремий потік для обробки сповіщень
    SchedulerTrigger --> CheckDueNotifications[NotificationManager.checkDueNotifications()]
    CheckDueNotifications --> GetPendingNotifications{Знайти сповіщення\n(state=PENDING, triggerDate<now)}
    GetPendingNotifications -->|Немає| WaitForNextCheck([Очікування наступної перевірки])
    GetPendingNotifications -->|Є сповіщення| ForEachNotification{Для кожного сповіщення}

    ForEachNotification --> ChangeStateToDelivering[Notification.state = DELIVERING]
    ChangeStateToDelivering --> AttemptSend[NotificationManager -> notification.send()]
    AttemptSend --> DeliveryResult{Успішна доставка?}

    DeliveryResult -->|Так| ChangeStateToDelivered[Notification.state = DELIVERED]
    ChangeStateToDelivered --> MarkManagerDelivered[NotificationManager оновлює статус]
    MarkManagerDelivered --> EndNotificationLoop([Завершення обробки цього сповіщення])
    EndNotificationLoop --> GetPendingNotifications


    DeliveryResult -->|Ні| ChangeStateToFailed[Notification.state = FAILED]
    ChangeStateToFailed --> RetryLogic{Ліміт спроб\nвичерпано?}
    RetryLogic -->|Ні| IncrementRetry[notification.incrementRetryCount()]
    IncrementRetry --> ChangeStateToPending[Notification.state = PENDING]
    ChangeStateToPending --> ManagerReschedules[NotificationManager переплановує]
    ManagerReschedules --> EndNotificationLoop

    RetryLogic -->|Так| ChangeStateToPermFailed[Notification.state = PERMANENTLY_FAILED]
    ChangeStateToPermFailed --> LogError[Логування помилки]
    LogError --> EndNotificationLoop


    %% Потік користувацьких дій зі сповіщенням (приклад)
    UserNotificationReceived --> UserAction{Дія користувача?}
    UserAction -->|Скасування| CancelNotificationFlow[Користувач -> NotificationManager.cancelNotification()]
    CancelNotificationFlow --> ChangeStateToCancelled[Notification.state = CANCELLED]
    ChangeStateToCancelled --> EndAction([Завершення дії])

    UserAction -->|Відкладення| SnoozeNotificationFlow[Користувач -> NotificationManager.snoozeNotification()]
    SnoozeNotificationFlow --> UpdateTriggerDate[Оновити triggerDate]
    UpdateTriggerDate --> ChangeStateToSnoozed[Notification.state = SNOOZED]
    ChangeStateToSnoozed --> EndAction

    UserAction -->|Перегляд| ViewDetails[Перегляд деталей]
    ViewDetails --> EndAction

    WaitForNextCheck --> SchedulerTrigger
```
