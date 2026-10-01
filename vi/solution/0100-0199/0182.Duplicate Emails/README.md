---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [182. Duplicate Emails](https://leetcode.com/problems/duplicate-emails)

[中文文档](/solution/0100-0199/0182.Duplicate%20Emails/README.md)

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
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này chứa một email. Các email sẽ không chứa chữ cái viết hoa.
</pre>

<p>&nbsp;</p>

<p>Hãy viết một lời giải để báo cáo tất cả các email trùng lặp. Lưu ý rằng trường email&nbsp;được đảm bảo không phải là NULL.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được trình bày trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Person table:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>Đầu ra:</strong>
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>Giải thích:</strong> a@b.com được lặp lại hai lần.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: GROUP BY + HAVING

<!-- thinking:start -->

> **Tư duy**
>
> Tìm các email xuất hiện nhiều hơn một lần. Nhóm theo $\textit{email}$ và giữ lại các nhóm có $\textit{HAVING}\,\textit{COUNT}>1$. Đó chính là “tần suất lớn hơn một”.

<!-- thinking:end -->

Chúng ta có thể sử dụng câu lệnh `GROUP BY` để nhóm dữ liệu theo trường `email`, sau đó sử dụng câu lệnh `HAVING` để lọc các địa chỉ `email` xuất hiện nhiều hơn một lần.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def duplicate_emails(person: pd.DataFrame) -> pd.DataFrame:
    results = pd.DataFrame()

    results = person.loc[person.duplicated(subset=["email"]), ["email"]]

    return results.drop_duplicates()
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT email
FROM Person
GROUP BY 1
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tự nối

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 thực hiện phép tổng hợp. Một phép tự nối trên cùng một email và các $\textit{id}$ khác nhau cũng chứng minh rằng email xuất hiện hai lần; sau đó loại bỏ các giá trị trùng lặp. Không cần $\textit{GROUP BY}$.

<!-- thinking:end -->

Chúng ta có thể sử dụng phép tự nối để nối bảng `Person` với chính nó, sau đó lọc các bản ghi trong đó `id` khác nhau nhưng `email` giống nhau.

<!-- tabs:start -->

#### MySQL

```sql
SELECT DISTINCT p1.email
FROM
    person AS p1,
    person AS p2
WHERE p1.id != p2.id AND p1.email = p2.email;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
