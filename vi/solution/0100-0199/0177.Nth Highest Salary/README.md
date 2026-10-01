---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [177. Nth Highest Salary](https://leetcode.com/problems/nth-highest-salary)

[中文文档](/solution/0100-0199/0177.Nth%20Highest%20Salary/README.md)

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

<p>Viết lời giải để tìm mức lương <strong>phân biệt</strong> cao thứ <code>n<sup>th</sup></code> từ bảng <code>Employee</code>. Nếu có ít hơn <code>n</code> mức lương phân biệt, hãy trả về <code>null</code>.</p>

<p>Định dạng kết quả được thể hiện trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
<strong>Đầu ra:</strong>
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
<strong>Đầu ra:</strong>
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Khái quát bài toán “cao thứ hai” thành bài toán cao thứ $N$: sắp xếp các mức lương phân biệt theo thứ tự giảm dần, sau đó lấy phần tử ở chỉ số $N-1$. Với $N<1$ hoặc có ít hơn $N$ giá trị phân biệt, kết quả là $\textit{NULL}$. Trong SQL, $\textit{LIMIT}\,1\,\textit{OFFSET}\,N-1$ tìm hàng đó; truy vấn bên ngoài chuyển kết quả rỗng thành $\textit{NULL}$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def nth_highest_salary(employee: pd.DataFrame, N: int) -> pd.DataFrame:
    if N < 1:
        return pd.DataFrame({"getNthHighestSalary(" + str(N) + ")": [None]})
    unique_salaries = employee.salary.unique()
    if len(unique_salaries) < N:
        return pd.DataFrame([np.NaN], columns=[f"getNthHighestSalary({N})"])
    else:
        salary = sorted(unique_salaries, reverse=True)[N - 1]
        return pd.DataFrame([salary], columns=[f"getNthHighestSalary({N})"])
```

#### MySQL

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
  RETURN (
      # Viết câu lệnh truy vấn MySQL của bạn bên dưới.
      SELECT (
          SELECT DISTINCT salary
          FROM Employee
          ORDER BY salary DESC
          LIMIT 1 OFFSET N
      )
  );
END
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
