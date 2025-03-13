# Use-Case Diagram

```mermaid
graph TD
    User[Користувач] -->|Імпортує банківські транзакції| TransactionImport(Імпорт транзакцій)

    TransactionImport -->|Завантажує файл| FileUpload(Завантаження файлу)
    TransactionImport -->|Перевіряє структуру файлу| FileValidation(Перевірка файлу)
    TransactionImport -->|Обробляє транзакції| TransactionProcessing(Обробка транзакцій)
    TransactionImport -->|Зберігає дані| DataStorage(Збереження даних)

    FileUpload -->|Передає дані| SystemProcessor[Система Обробки]
    FileValidation -->|Перевіряє відповідність| SystemProcessor
    TransactionProcessing -->|Категоризує транзакції| SystemProcessor
    DataStorage -->|Зберігає в базу| Database[База даних]
