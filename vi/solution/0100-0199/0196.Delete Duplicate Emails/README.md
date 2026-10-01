---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [196. Delete Duplicate Emails](https://leetcode.com/problems/delete-duplicate-emails)

[中文文档](/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id là khóa chính (cột có các giá trị không trùng lặp) của bảng này.
Mỗi hàng của bảng này chứa một email. Các email sẽ không chứa chữ cái viết hoa.
</pre>

<p>&nbsp;</p>

<p>Hãy viết lời giải để <strong>xóa</strong> tất cả email trùng lặp, chỉ giữ lại một email duy nhất có <code>id</code> nhỏ nhất.</p>

<p>Đối với người dùng SQL, lưu ý rằng bạn cần viết một câu lệnh <code>DELETE</code>, không phải câu lệnh <code>SELECT</code>.</p>

<p>Đối với người dùng Pandas, lưu ý rằng bạn cần sửa đổi <code>Person</code> tại chỗ.</p>

<p>Sau khi chạy script, kết quả hiển thị là bảng <code>Person</code>. Trình điều khiển trước tiên sẽ biên dịch và chạy đoạn code của bạn, sau đó hiển thị bảng <code>Person</code>. Thứ tự cuối cùng của bảng <code>Person</code> <strong>không quan trọng</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Person table:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
<strong>Đầu ra:</strong>
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
<strong>Giải thích:</strong> john@example.com xuất hiện hai lần. Chúng ta giữ lại hàng có Id nhỏ nhất = 1.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Giữ lại $\textit{id}$ nhỏ nhất cho mỗi email. $\textit{MIN}(\textit{id})$ được nhóm theo email chính là tập hợp các hàng được giữ lại; xóa phần còn lại. Lớp bọc truy vấn con bổ sung cho phép MySQL tổng hợp một bảng đang bị xóa.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


# Sửa Person tại chỗ
def delete_duplicate_emails(person: pd.DataFrame) -> None:
    # Sắp xếp các hàng theo id (thứ tự tăng dần)
    person.sort_values(by="id", ascending=True, inplace=True)
    # Loại bỏ các bản sao dựa trên email.
    person.drop_duplicates(subset="email", keep="first", inplace=True)
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
DELETE FROM Person
WHERE id NOT IN (SELECT MIN(id) FROM (SELECT * FROM Person) AS p GROUP BY email);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng truy vấn con tổng hợp. $\textit{ROW\_NUMBER}$ được phân vùng theo email và sắp xếp theo $\textit{id}$ để đánh dấu các bản sao bằng các số lớn hơn $1$; xóa các hàng đó.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
DELETE FROM Person
WHERE
    id NOT IN (
        SELECT id
        FROM
            (
                SELECT
                    id,
                    ROW_NUMBER() OVER (
                        PARTITION BY email
                        ORDER BY id
                    ) AS rk
                FROM Person
            ) AS p
        WHERE rk = 1
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3

<!-- thinking:start -->

> **Tư duy**
>
> Không cần tính trước tập hợp các hàng được giữ lại. Tự JOIN trên cùng email với $p1.\textit{id}<p2.\textit{id}$ rồi xóa $\textit{id}$ lớn hơn. Chỉ cần một $\textit{DELETE}\,\textit{JOIN}$.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
DELETE p2
FROM
    person AS p1
    JOIN person AS p2 ON p1.email = p2.email
WHERE
    p1.id < p2.id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
