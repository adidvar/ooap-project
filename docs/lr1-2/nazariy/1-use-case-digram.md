@startuml
actor "Студент" as Student

rectangle "Додаток" {
    usecase "Створити фінансову ціль" as UC1
    usecase "Редагувати фінансову ціль" as UC2
    usecase "Скасувати створення цілі" as UC3
    usecase "Відстежувати прогрес" as UC4
    UC1 --> (Перевірка коректності даних) : <<include>>
    UC1 --> (Збереження цілі) : <<include>>
    UC4 --> (Отримання оновленої інформації) : <<include>>
}

Student --> UC1 : Створює ціль
Student --> UC2 : Редагує ціль
Student --> UC3 : Скасовує ціль
Student --> UC4 : Переглядає прогрес

@enduml
