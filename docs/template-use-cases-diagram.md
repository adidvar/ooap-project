# Use-Case Diagram

```mermaid
graph TD
    User[Користувач] -->|Додає витрати| ExpenseManagement(Управління витратами)
    User -->|Налаштовує платежі| PaymentReminder(Нагадування про оплату)
    User -->|Експортує дані| DataExport(Експорт фінансових даних)
    
    PaymentReminder -->|Надсилає сповіщення| NotificationSystem[Система Сповіщень]
    DataExport -->|Генерує звіт| ExportSystem[Система Експорту]
