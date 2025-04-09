# Аналіз діаграми класів системи управління фінансами студентів методом Аббота

## Іменники (потенційні класи/об'єкти)

| Іменник | Опис | Роль у системі |
|---------|------|----------------|
| Student | Студент - користувач системи | Основна сутність, що взаємодіє з системою |
| Balance | Баланс користувача | Сутність для збереження фінансового стану студента |
| Category | Категорія доходів/витрат | Класифікація для транзакцій |
| Payment | Платіж (запланований/регулярний) | Сутність для управління платежами |
| Notification | Сповіщення системи | Механізм інформування користувача |
| FinancialGoal | Фінансова ціль | Сутність для відстеження прогресу до фінансових цілей |
| Transaction | Транзакція (дохід/витрата) | Основна фінансова операція в системі |
| ConsultationRequest | Запит на консультацію | Сутність для комунікації з фінансовими консультантами |
| Budget | Бюджет користувача | План розподілу коштів за періодами та категоріями |
| Currency | Валюта | Сутність для роботи з різними валютами |
| FinanceManager | Менеджер фінансів | Керуючий клас для фінансових операцій |
| NotificationManager | Менеджер сповіщень | Керуючий клас для роботи зі сповіщеннями |
| GoalManager | Менеджер цілей | Керуючий клас для фінансових цілей |
| ImportManager | Менеджер імпорту | Керуючий клас для імпорту/експорту даних |
| BudgetManager | Менеджер бюджету | Керуючий клас для управління бюджетами |
| AnalyticsManager | Менеджер аналітики | Керуючий клас для генерації звітів та аналізу |

## Дієслова (потенційні методи/операції)

| Дієслово | Опис | Клас, до якого належить |
|----------|------|-------------------------|
| viewDashboard | Перегляд інформаційної панелі | Student |
| updateBalance | Оновлення балансу | Balance |
| convertCurrency | Конвертація валюти | Balance |
| create | Створення об'єкта | Category, Payment, FinancialGoal, Transaction, Budget, Notification |
| edit | Редагування об'єкта | Category, Transaction, Budget |
| delete | Видалення об'єкта | Category, Transaction, Payment |
| markAsPaid | Позначення платежу як оплаченого | Payment |
| setRecurring | Встановлення повторюваності | Payment |
| send | Надсилання сповіщення | Notification |
| cancel | Скасування сповіщення | Notification |
| snooze | Відкладення сповіщення | Notification |
| updateProgress | Оновлення прогресу цілі | FinancialGoal |
| calculatePercentage | Розрахунок відсотка виконання | FinancialGoal |
| addAttachment | Додавання вкладення | Transaction |
| categorize | Категоризація транзакції | Transaction |
| createRequest | Створення запиту на консультацію | ConsultationRequest |
| setResponse | Встановлення відповіді | ConsultationRequest |
| updateStatus | Оновлення статусу | ConsultationRequest |
| calculateSpent | Розрахунок витрачених коштів | Budget |
| calculateRemaining | Розрахунок залишку бюджету | Budget |
| getExchangeRate | Отримання курсу обміну | Currency |
| convert | Конвертація між валютами | Currency |
| getFinancesByCategory | Отримання фінансів за категоріями | FinanceManager |
| generateFinancialSummary | Створення фінансового резюме | FinanceManager |
| predictFutureExpenses | Прогнозування майбутніх витрат | FinanceManager |
| scheduleNotification | Планування сповіщення | NotificationManager |
| checkDueDates | Перевірка строків платежів | NotificationManager |
| getUserPreferences | Отримання налаштувань користувача | NotificationManager |
| trackProgress | Відстеження прогресу цілі | GoalManager |
| analyzeGoalFeasibility | Аналіз досяжності цілі | GoalManager |
| importTransactions | Імпорт транзакцій | ImportManager |
| exportTransactions | Експорт транзакцій | ImportManager |
| detectDuplicates | Виявлення дублікатів | ImportManager |
| trackBudgetProgress | Відстеження виконання бюджету | BudgetManager |
| suggestBudgetAdjustments | Пропозиції щодо корекції бюджету | BudgetManager |
| generateSpendingReport | Генерація звіту про витрати | AnalyticsManager |
| identifySpendingPatterns | Виявлення шаблонів витрат | AnalyticsManager |
| suggestOptimizations | Пропозиції з оптимізації | AnalyticsManager |

## Прикметники (потенційні атрибути)

| Прикметник | Опис | Клас, до якого належить |
|------------|------|-------------------------|
| id | Ідентифікатор об'єкта | Student, Category, Payment, Notification, FinancialGoal, Transaction, ConsultationRequest, Budget |
| name | Ім'я/назва | Student, Category |
| email | Електронна пошта | Student |
| passwordHash | Хеш паролю | Student |
| amount | Сума/кількість коштів | Balance, Payment, FinancialGoal, Transaction, Budget |
| lastUpdated | Дата останнього оновлення | Balance |
| type | Тип об'єкта | Category, Transaction, Notification, Budget |
| icon | Іконка категорії | Category |
| dueDate | Дата платежу | Payment |
| isPaid | Статус оплати | Payment |
| description | Опис | Payment, Transaction |
| recurrence | Тип повторення | Payment |
| message | Повідомлення | Notification |
| triggerDate | Дата спрацювання | Notification |
| isDelivered | Статус доставки | Notification |
| title | Заголовок | FinancialGoal |
| targetAmount | Цільова сума | FinancialGoal |
| currentAmount | Поточна сума | FinancialGoal |
| deadline | Кінцевий термін | FinancialGoal |
| date | Дата | Transaction |
| attachmentURL | Посилання на вкладення | Transaction |
| question | Питання | ConsultationRequest |
| timestamp | Часова мітка | ConsultationRequest |
| response | Відповідь | ConsultationRequest |
| status | Статус | ConsultationRequest |
| totalAmount | Загальна сума | Budget |
| allocations | Розподіл за категоріями | Budget |
| startDate | Дата початку | Budget |
| endDate | Дата закінчення | Budget |
| code | Код валюти | Currency |
| symbol | Символ валюти | Currency |
| exchangeRate | Курс обміну | Currency |

## Перерахування (Enum типи)

| Перерахування | Значення |
|---------------|----------|
| NotificationType | PAYMENT_REMINDER, BUDGET_ALERT |
| TransactionType | INCOME, EXPENSE, TRANSFER |
| ConsultationStatus | PENDING, ANSWERED, CLOSED |
| BudgetType | WEEKLY, MONTHLY, SEMESTER, CUSTOM |

## Класифікація класів за шаблонними категоріями

### Класи-сутності (Entity)
- Student
- Balance
- Category
- Payment
- Notification
- FinancialGoal
- Transaction
- ConsultationRequest
- Budget
- Currency

### Керуючі класи (Control)
- FinanceManager
- NotificationManager
- GoalManager
- ImportManager
- BudgetManager
- AnalyticsManager

### Переліки (Enumeration)
- NotificationType
- TransactionType
- ConsultationStatus
- BudgetType
