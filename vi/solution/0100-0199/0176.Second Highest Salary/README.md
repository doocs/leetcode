---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [176. Second Highest Salary](https://leetcode.com/problems/second-highest-salary)

[中文文档](/solution/0100-0199/0176.Second%20Highest%20Salary/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này chứa thông tin về mức lương của một nhân viên.
</pre>

<p>&nbsp;</p>

<p>Viết lời giải để tìm mức lương <strong>phân biệt</strong> cao thứ hai trong bảng <code>Employee</code>. Nếu không có mức lương cao thứ hai, hãy trả về <code>null (return&nbsp;None in Pandas)</code>.</p>

<p>Định dạng kết quả được thể hiện trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Employee:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
<strong>Đầu ra:</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Employee:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
<strong>Đầu ra:</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sử dụng truy vấn con và LIMIT

<!-- thinking:start -->

> **Tư duy**
>
> Mức lương cao thứ hai là giá trị thứ hai trong các mức lương distinct theo thứ tự giảm dần, hoặc $\textit{NULL}$ nếu có ít hơn hai giá trị. $\textit{ORDER BY}$ cùng với $\textit{LIMIT}\,1\,\textit{OFFSET}\,1$ chọn vị trí đó; truy vấn bên ngoài giữ lại một hàng $\textit{NULL}$ duy nhất khi offset không có kết quả.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    # Bỏ các giá trị salary trùng lặp để tránh tính các giá trị trùng lặp thành các thứ hạng salary riêng biệt
    unique_salaries = employee["salary"].drop_duplicates()

    # Sắp xếp các salary duy nhất theo thứ tự giảm dần và lấy salary cao thứ hai
    second_highest = (
        unique_salaries.nlargest(2).iloc[-1] if len(unique_salaries) >= 2 else None
    )

    # Nếu salary cao thứ hai không tồn tại (ví dụ có ít hơn hai salary duy nhất), trả về None
    if second_highest is None:
        return pd.DataFrame({"SecondHighestSalary": [None]})

    # Tạo một DataFrame với salary cao thứ hai
    result_df = pd.DataFrame({"SecondHighestSalary": [second_highest]})

    return result_df
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1, 1
    ) AS SecondHighestSalary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Sử dụng hàm `MAX()`

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 cần sắp xếp và offset. Giá trị lớn nhất trong các mức lương nhỏ hơn giá trị lớn nhất toàn cục chính là mức lương cao thứ hai; $\textit{MAX}$ trên một tập rỗng vốn đã trả về $\textit{NULL}$, nên không cần $\textit{LIMIT}$.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3: Sử dụng `IFNULL()` và hàm cửa sổ

<!-- thinking:start -->

> **Tư duy**
>
> Sau khi loại bỏ các giá trị trùng lặp, $\textit{DENSE_RANK}$ đánh số các mức lương theo thứ tự giảm dần và chúng ta giữ lại hạng $2$. Các giá trị bằng nhau ở đầu không chiếm vị trí thứ hai, phù hợp với “cao thứ $k$” và có thể mở rộng một cách tự nhiên.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH T AS (SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rk FROM Employee)
SELECT (SELECT DISTINCT salary FROM T WHERE rk = 2) AS SecondHighestSalary;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
