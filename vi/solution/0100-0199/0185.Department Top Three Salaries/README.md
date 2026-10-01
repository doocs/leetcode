---
comments: true
difficulty: Hard
tags:
    - Database
---

<!-- problem:start -->

# [185. Department Top Three Salaries](https://leetcode.com/problems/department-top-three-salaries)

[中文文档](/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README.md)

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
departmentId là khóa ngoại (cột tham chiếu) của ID từ bảng <code>Department </code>.
Mỗi hàng của bảng này cho biết ID, tên và mức lương của một nhân viên. Bảng cũng chứa ID phòng ban của nhân viên đó.
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
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này cho biết ID của một phòng ban và tên của phòng ban đó.
</pre>

<p>&nbsp;</p>

<p>Các lãnh đạo của một công ty muốn biết ai kiếm được nhiều tiền nhất trong mỗi phòng ban của công ty. Một <strong>người có thu nhập cao</strong> trong một phòng ban là nhân viên có mức lương thuộc <strong>ba mức lương duy nhất cao nhất</strong> của phòng ban đó.</p>

<p>Hãy viết một lời giải để tìm những nhân viên là <strong>người có thu nhập cao</strong> trong mỗi phòng ban.</p>

<p>Trả về bảng kết quả <strong>theo bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Employee:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
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
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+
<strong>Giải thích:</strong>
Trong phòng ban IT:
- Max nhận mức lương duy nhất cao nhất
- Randy và Joe cùng nhận mức lương duy nhất cao thứ hai
- Will nhận mức lương duy nhất cao thứ ba

Trong phòng ban Sales:
- Henry nhận mức lương cao nhất
- Sam nhận mức lương cao thứ hai
- Không có mức lương cao thứ ba vì chỉ có hai nhân viên
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li>Không có nhân viên nào có cùng <strong>chính xác</strong> tên, mức lương <em>và</em> phòng ban.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Ba mức lương phân biệt cao nhất trong mỗi phòng ban, bao gồm cả các nhân viên đồng hạng. Một phép đếm tương quan các mức lương phân biệt cao hơn một cách nghiêm ngặt trong cùng phòng ban, nhỏ hơn $3$, cho biết hàng đó thuộc ba mức lương cao nhất; sau đó nối với các phòng ban. Không cần hiện thực hóa một bảng xếp hạng.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def top_three_salaries(
    employee: pd.DataFrame, department: pd.DataFrame
) -> pd.DataFrame:
    salary_cutoff = (
        employee.drop_duplicates(["salary", "departmentId"])
        .groupby("departmentId")["salary"]
        .nlargest(3)
        .groupby("departmentId")
        .min()
    )
    employee["Department"] = department.set_index("id")["name"][
        employee["departmentId"]
    ].values
    employee["cutoff"] = salary_cutoff[employee["departmentId"]].values
    return employee[employee["salary"] >= employee["cutoff"]].rename(
        columns={"name": "Employee", "salary": "Salary"}
    )[["Department", "Employee", "Salary"]]
```

#### MySQL

```sql
SELECT
    Department.NAME AS Department,
    Employee.NAME AS Employee,
    Salary
FROM
    Employee,
    Department
WHERE
    Employee.DepartmentId = Department.Id
    AND (
        SELECT
            COUNT(DISTINCT e2.Salary)
        FROM Employee AS e2
        WHERE e2.Salary > Employee.Salary AND Employee.DepartmentId = e2.DepartmentId
    ) < 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 quét lại phòng ban cho từng nhân viên. $\textit{DENSE\_RANK}$ được phân vùng theo phòng ban và sắp xếp theo mức lương giảm dần, sau đó $\textit{rk}\le 3$ sẽ là ba nhóm mức lương cao nhất, bao gồm cả các nhân viên đồng hạng.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
WITH
    T AS (
        SELECT
            *,
            DENSE_RANK() OVER (
                PARTITION BY departmentId
                ORDER BY salary DESC
            ) AS rk
        FROM Employee
    )
SELECT d.name AS Department, t.name AS Employee, salary AS Salary
FROM
    T AS t
    JOIN Department AS d ON t.departmentId = d.id
WHERE rk <= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
