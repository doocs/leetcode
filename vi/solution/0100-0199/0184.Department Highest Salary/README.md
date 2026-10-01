---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [184. Department Highest Salary](https://leetcode.com/problems/department-highest-salary)

[中文文档](/solution/0100-0199/0184.Department%20Highest%20Salary/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Employee</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
departmentId là khóa ngoại (tham chiếu các cột) của ID từ bảng <code>Department </code>.
Mỗi hàng của bảng này cho biết ID, tên và mức lương của một nhân viên. Bảng này cũng chứa ID của phòng ban của họ.
</pre>

<p>&nbsp;</p>

<p>Bảng: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này. Đảm bảo rằng tên phòng ban không phải là <code>NULL.</code>
Mỗi hàng của bảng này cho biết ID và tên của một phòng ban.
</pre>

<p>&nbsp;</p>

<p>Hãy viết lời giải để tìm những nhân viên có mức lương cao nhất trong từng phòng ban.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng bảng kết quả được thể hiện trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Employee:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Bảng Department:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
<strong>Đầu ra:</strong>
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
<strong>Giải thích:</strong> Max và Jim đều có mức lương cao nhất trong phòng ban IT, còn Henry có mức lương cao nhất trong phòng ban Sales.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Equi-Join + Truy vấn con

<!-- thinking:start -->

> **Tư duy**
>
> Những nhân viên được trả lương cao nhất trong mỗi phòng ban, bao gồm cả các trường hợp hòa. Tính $\textit{MAX}(\textit{salary})$ cho mỗi phòng ban, nối nhân viên với phòng ban và giữ lại những hàng có mức lương bằng mức tối đa đó.

<!-- thinking:end -->

Chúng ta có thể sử dụng equi-join để nối bảng `Employee` và bảng `Department` dựa trên `Employee.departmentId = Department.id`, sau đó sử dụng một subquery để tìm mức lương cao nhất cho từng phòng ban. Cuối cùng, chúng ta có thể sử dụng mệnh đề `WHERE` để lọc những nhân viên có mức lương cao nhất trong từng phòng ban.

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT d.name AS department, e.name AS employee, salary
FROM
    Employee AS e
    JOIN Department AS d ON e.departmentId = d.id
WHERE
    (d.id, salary) IN (
        SELECT departmentId, MAX(salary)
        FROM Employee
        GROUP BY 1
    );
```

### Pandas

```python
import pandas as pd


def department_highest_salary(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    # Gộp hai bảng dựa trên departmentId và id của phòng ban
    merged = employee.merge(department, left_on='departmentId', right_on='id')

    # Tìm mức lương tối đa cho mỗi phòng ban
    max_salaries = merged.groupby('departmentId')['salary'].transform('max')

    # Lọc những nhân viên có mức lương cao nhất trong phòng ban của họ
    top_earners = merged[merged['salary'] == max_salaries]

    # Chọn các cột cần thiết và đổi tên chúng
    result = top_earners[['name_y', 'name_x', 'salary']].copy()
    result.columns = ['Department', 'Employee', 'Salary']

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Equi-Join + Hàm cửa sổ

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 cần một subquery và một phép join. $\textit{RANK}$ được phân vùng theo phòng ban và sắp xếp theo mức lương giảm dần sẽ đánh dấu mọi mức lương cao nhất (bao gồm cả các trường hợp hòa) với hạng $1$ chỉ trong một lượt duyệt.

<!-- thinking:end -->

Chúng ta có thể sử dụng equi-join để nối bảng `Employee` và bảng `Department` dựa trên `Employee.departmentId = Department.id`, sau đó sử dụng hàm cửa sổ `rank()`, hàm này gán hạng cho mỗi nhân viên trong từng phòng ban dựa trên mức lương của họ. Cuối cùng, chúng ta có thể chọn những hàng có hạng bằng $1$ cho mỗi phòng ban.

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
WITH
    T AS (
        SELECT
            d.name AS department,
            e.name AS employee,
            salary,
            RANK() OVER (
                PARTITION BY d.name
                ORDER BY salary DESC
            ) AS rk
        FROM
            Employee AS e
            JOIN Department AS d ON e.departmentId = d.id
    )
SELECT department, employee, salary
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
