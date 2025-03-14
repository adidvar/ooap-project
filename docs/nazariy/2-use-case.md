## Use-Case Template

## Назва Use-Case
**Отримання нагадувань про майбутні платежі**

## Опис
**Мета:** Користувач хоче отримувати нагадування про майбутні платежі, щоб не забувати оплачувати рахунки вчасно.
**Очікуваний результат:** Користувач отримує сповіщення про заплановані платежі, що допомагає уникати прострочених оплат.

## Актори
- **Primary Actor:** Студент (користувач)
- **Secondary Actors:** Система сповіщень

## Передумови
- Користувач зареєстрований у додатку та авторизований.
- Доступна функціональність для додавання транзакцій.
- Користувач має встановлені категорії для доходів та витрат.

## Основний потік (Main Flow)
1. Користувач відкриває додаток та входить у розділ фінансів.
2. Користувач додає нові доходи та витрати, вказуючи суму, категорію та дату, або це робить застосунок автоматично.
3. На головному екрані користувач бачить баланс та загальну витрату за місяць.
4. Якщо витрати перевищують запланований бюджет, система надсилає сповіщення користувачу.

## Альтернативні потоки (Alternative Flows)
### (AF1) Користувач редагує або видаляє транзакцію
1. Користувач вибирає транзакцію зі списку та редагує або видаляє її.
2. Система оновлює інформацію в списку та перераховує баланс.

### (AF2) Користувач скасовує додавання транзакції
1. Користувач скасовує введення нової транзакції.
2. Система не зберігає транзакцію, користувач повертається до головного меню.

## Виключні ситуації (Exceptions)
### (E1) Некоректні дані введено для транзакції (наприклад, від’ємна сума)
- Додаток показує повідомлення про помилку і не дозволяє зберегти транзакцію.

### (E2) Проблеми з мережею при збереженні транзакцій
- Якщо немає з'єднання з сервером, додаток відображає відповідне повідомлення та дозволяє повторити спробу пізніше.

## Результат (Postconditions)
- Транзакція збережена та відображена у списку.
- Користувач отримує сповіщення, якщо витрати перевищують бюджет.
