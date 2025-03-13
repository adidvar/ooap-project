# Use-Case Diagram

```mermaid
graph TD
    User[Користувач] -->|Отримує фінансову консультацію| ChatbotConsultant(Фінансовий чат-бот)

    ChatbotConsultant -->|Визначає терміни| DefineTerms(Визначення термінів)
    ChatbotConsultant -->|Надає приклади фінансових явищ| FinancialExamples(Приклади фінансових явищ)
    ChatbotConsultant -->|Рекомендує поради| FinancialAdvice(Фінансові поради)

    User -->|Надсилає запит| ChatbotConsultant
    ChatbotConsultant -->|Надає відповідь| User
