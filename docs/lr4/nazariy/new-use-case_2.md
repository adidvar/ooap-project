# Уточнений опис варіанту використання

## Use-Case: Отримання нагадувань про майбутні платежі

### Опис
**Мета:** Користувач хоче отримувати нагадування про майбутні платежі, щоб не забувати оплачувати рахунки вчасно.

**Очікуваний результат:** Користувач отримує сповіщення про заплановані платежі, що допомагає уникати прострочених оплат.

### Актори
* **Primary Actor:** Студент (користувач)
* **Secondary Actors:** Система сповіщень

### Передумови
* Користувач зареєстрований у додатку та авторизований
* Користувач має налаштовані параметри отримання сповіщень
* Існує хоча б один несплачений платіж із датою оплати в майбутньому

### Основний потік (Main Flow)
1. Користувач створює новий платіж через PaymentController, вказуючи суму, дату оплати та опис
2. Система зберігає новий платіж у базі даних
3. PaymentController викликає NotificationManager.schedulePaymentReminder для створення нагадування
4. NotificationManager створює об'єкт Notification з типом PAYMENT_REMINDER та планує його відправлення
5. Коли настає дата нагадування, система викликає метод NotificationManager.checkDueDates
6. NotificationManager отримує всі нагадування, які мають бути надіслані
7. Для кожного нагадування NotificationManager викликає метод send()
8. Система відправляє сповіщення користувачу
9. Нагадування позначається як доставлене

### Альтернативні потоки (Alternative Flows)
#### AF1: Користувач скасовує нагадування
1. Після отримання нагадування користувач вирішує скасувати його
2. Користувач вибирає опцію "Скасувати нагадування" 
3. Система викликає метод NotificationManager.cancelNotification
4. Notification.cancel() позначає нагадування як скасоване
5. Система підтверджує скасування нагадування

#### AF2: Користувач відкладає нагадування
1. Після отримання нагадування користувач вирішує відкласти його
2. Користувач вибирає опцію "Відкласти" та вказує тривалість
3. Система викликає метод Notification.snooze(Duration)
4. Нагадування перепланується на новий час
5. Система підтверджує відкладення нагадування

### Виключні ситуації (Exceptions)
#### E1: Проблеми з мережею при надсиланні сповіщень
1. Якщо виникає помилка при надсиланні сповіщення, система інкрементує лічильник спроб
2. Якщо кількість спроб не перевищує максимальну, система планує повторну спробу
3. Якщо досягнуто максимальну кількість спроб, система логує помилку

### Результат (Postconditions)
* Користувач отримує нагадування про майбутні платежі вчасно
* Нагадування позначається як доставлене після успішного надсилання
* Користувач може переглянути історію надісланих нагадувань
* У разі скасування або відкладення, нагадування оновлює свій стан відповідно
