---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [183. Customers Who Never Order](https://leetcode.com/problems/customers-who-never-order)

[中文文档](/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Customers</code></p>

<pre>
+-------------+---------+
| Tên cột     | Kiểu    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này cho biết ID và tên của một khách hàng.
</pre>

<p>&nbsp;</p>

<p>Bảng: <code>Orders</code></p>

<pre>
+-------------+------+
| Tên cột     | Kiểu |
+-------------+------+
| id          | int  |
| customerId  | int  |
+-------------+------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
customerId là khóa ngoại (các cột tham chiếu) của ID từ bảng Customers.
Mỗi hàng của bảng này cho biết ID của một đơn hàng và ID của khách hàng đã đặt đơn hàng đó.
</pre>

<p>&nbsp;</p>

<p>Hãy viết một lời giải để tìm tất cả khách hàng chưa từng đặt bất kỳ đơn hàng nào.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Customers:
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Bảng Orders:
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
<strong>Đầu ra:</strong>
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: NOT IN

<!-- thinking:start -->

> **Tư duy**
>
> Những khách hàng không có đơn hàng là những khách hàng có $\textit{id}$ nằm ngoài tập $\textit{customerId}$. $\textit{NOT IN}$ chính là phép lấy phần bù của tập đó. Hãy chú ý đến ngữ nghĩa của danh sách rỗng khi dùng $\textit{NOT IN}$ trên một số engine.

<!-- thinking:end -->

Liệt kê tất cả ID khách hàng của các đơn hàng hiện có, rồi dùng `NOT IN` để tìm những khách hàng không nằm trong danh sách.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def find_customers(customers: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    # Chọn các khách hàng có 'id' không xuất hiện trong cột 'customerId' của DataFrame orders.
    df = customers[~customers["id"].isin(orders["customerId"])]

    # Tạo một DataFrame chỉ chứa cột 'name' và đổi tên cột đó thành 'Customers'.
    df = df[["name"]].rename(columns={"name": "Customers"})

    return df
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT name AS Customers
FROM Customers
WHERE
    id NOT IN (
        SELECT customerId
        FROM Orders
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: LEFT JOIN

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 trở nên bất tiện với một subquery lớn. Một phép left join với orders, chỉ giữ lại các hàng có $\textit{customerId}$ là null, tránh được bẫy $\textit{NOT IN}$/$\textit{NULL}$ và có thể sử dụng một join plan.

<!-- thinking:end -->

Sử dụng `LEFT JOIN` để nối các bảng và trả về dữ liệu trong đó `CustomerId` là `NULL`.

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT name AS Customers
FROM
    Customers AS c
    LEFT JOIN Orders AS o ON c.id = o.customerId
WHERE o.id IS NULL;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
