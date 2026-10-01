---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [181. Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers)

[中文文档](/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Employee</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này cho biết ID của một nhân viên, tên, mức lương và ID của quản lý của nhân viên đó.
</pre>

<p>&nbsp;</p>

<p>Viết một lời giải để tìm các nhân viên có mức lương cao hơn quản lý của họ.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được thể hiện trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Employee:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
<strong>Đầu ra:</strong>
+----------+
| Employee |
+----------+
| Joe      |
+----------+
<strong>Giải thích:</strong> Joe là nhân viên duy nhất có mức lương cao hơn quản lý của mình.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Self-Join + Lọc có điều kiện

<!-- thinking:start -->

> **Tư duy**
>
> Nhân viên và quản lý dùng chung một bảng; $\textit{managerId}$ trỏ tới hàng của quản lý. Phép self-join sắp xếp mỗi nhân viên cùng với quản lý tương ứng, sau đó giữ lại các tên có mức lương lớn hơn.

<!-- thinking:end -->

Chúng ta có thể tìm mức lương của nhân viên và mức lương của quản lý bằng cách self-join bảng `Employee`, sau đó lọc ra những nhân viên có mức lương cao hơn mức lương của quản lý.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def find_employees(employee: pd.DataFrame) -> pd.DataFrame:
    merged = employee.merge(
        employee, left_on="managerId", right_on="id", suffixes=("", "_manager")
    )
    result = merged[merged["salary"] > merged["salary_manager"]][["name"]]
    result.columns = ["Employee"]
    return result
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT e1.name Employee
FROM
    Employee e1
    JOIN Employee e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
