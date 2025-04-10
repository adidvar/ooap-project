# Розрахунок оцінки класів з атрибутами та операціями
$$
S_{cls} = \frac{\sqrt{Op} + \sqrt{Atr}}{0,3 * (Op + Art)}
$$

| Class               |   Operations |   Attributes |   $S_{cls}$ |
|:--------------------|-------------:|-------------:|------------:|
| Student             |            1 |            4 |      2.0000 |
| Balance             |            3 |            3 |      1.9245 |
| Category            |            3 |            4 |      1.7772 |
| Payment             |            4 |            4 |      1.6667 |
| Notification        |            4 |            5 |      1.5689 |
| NotificationType    |            0 |            0 |      0.0000 |
| FinancialGoal       |            3 |            5 |      1.6534 |
| Transaction         |            5 |            7 |      1.3561 |
| TransactionType     |            0 |            0 |      0.0000 |
| ConsultationRequest |            3 |            5 |      1.6534 |
| ConsultationStatus  |            0 |            0 |      0.0000 |
| Budget              |            4 |            6 |      1.4832 |
| BudgetType          |            0 |            0 |      0.0000 |
| Currency            |            2 |            3 |      2.0975 |
| FinanceManager      |            5 |            0 |      1.4907 |
| NotificationManager |            5 |            0 |      1.4907 |
| GoalManager         |            5 |            0 |      1.4907 |
| ImportManager       |            6 |            0 |      1.3608 |
| BudgetManager       |            3 |            0 |      1.9245 |
| AnalyticsManager    |            5 |            0 |      1.4907 |

# Розрахунок оцінки діаграми класів
$$
S = \frac{\sum S_{Obj} + \sum S_{Lnk}}{1 + Obj + \sqrt{T_{Obj}} + T_{Lnk}}
$$


## Формула

$$
S = \frac{\sum S_{Obj} + \sum S_{Lnk}}{1 + Obj + \sqrt{T_{Obj}} + T_{Lnk}}
$$

- **S** – оцінка діаграми  
- **S<sub>Obj</sub>** – оцінка для сутностей діаграми  
- **S<sub>Lnk</sub>** – оцінка для зв'язків діаграми  
- **Obj** – кількість сутностей на діаграмі  
- **T<sub>Obj</sub>** – кількість типів сутностей  
- **T<sub>Lnk</sub>** – кількість типів зв'язків  

## Оцінка сутностей (S<sub>Obj</sub>)

Усі сутності є класами, окрім 5 перерахованих перерахувань (**<<enumeration>>**), які не є повноцінними класами з методами. Однак вони також є сутностями.

| Сутність                | Тип           | Оцінка |
|-------------------------|----------------|--------|
| Student                 | class          | 5      |
| Balance                 | class          | 5      |
| Category                | class          | 5      |
| Payment                 | class          | 5      |
| Notification            | class          | 5      |
| FinancialGoal           | class          | 5      |
| Transaction             | class          | 5      |
| ConsultationRequest     | class          | 5      |
| Budget                  | class          | 5      |
| Currency                | class          | 5      |
| FinanceManager          | class          | 5      |
| NotificationManager     | class          | 5      |
| GoalManager             | class          | 5      |
| ImportManager           | class          | 5      |
| BudgetManager           | class          | 5      |
| AnalyticsManager        | class          | 5      |
| NotificationType        | enumeration    | 0      |
| TransactionType         | enumeration    | 0      |
| ConsultationStatus      | enumeration    | 0      |
| BudgetType              | enumeration    | 0      |

**Разом сутностей (Obj):** 20  
**Оцінка сутностей (∑S<sub>Obj</sub>):** 15 × 5 + 5 × 0 = **75**  
**Кількість типів сутностей (T<sub>Obj</sub>):** 2 (class, enumeration)

---

## Оцінка зв’язків (S<sub>Lnk</sub>)

### Типи зв’язків та їх кількість:

| Тип зв’язку      | Кількість | Оцінка одиниці | Сума |
|------------------|-----------|----------------|------|
| Композиція       | 7         | 3              | 21   |
| Агрегація        | 9         | 2              | 18   |
| Асоціація        | 13        | 1              | 13   |

**Разом зв’язків:** 29  
**Оцінка зв’язків (∑S<sub>Lnk</sub>):** 21 + 18 + 13 = **52**  
**Кількість типів зв’язків (T<sub>Lnk</sub>):** 3 (composition, aggregation, association)

---

## Розрахунок

Підставимо в формулу:

- ∑S<sub>Obj</sub> = 75  
- ∑S<sub>Lnk</sub> = 52  
- Obj = 20  
- T<sub>Obj</sub> = 2  
- T<sub>Lnk</sub> = 3  

$$
S = \frac{75 + 52}{1 + 20 + \sqrt{2} + 3} = \frac{127}{1 + 20 + 1.4142 + 3} = \frac{127}{25.4142} ≈ \mathbf{5.0}
$$

---

## Висновок

Оцінка діаграми класів:

> **S ≈ 5.0**

