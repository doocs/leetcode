# [177. Nth Highest Salary](https://leetcode.com/problems/nth-highest-salary)

[中文文档](/solution/0100-0199/0177.Nth%20Highest%20Salary/README.md)

## Description

<p>Write a SQL query to get the <em>n</em><sup>th</sup> highest salary from the <code>Employee</code> table.</p>

<pre>
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
</pre>

<p>For example, given the above Employee table, the <em>n</em><sup>th</sup> highest salary where <em>n</em> = 2 is <code>200</code>. If there is no <em>n</em><sup>th</sup> highest salary, then the query should return <code>null</code>.</p>

<pre>
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
</pre>


## Solutions

<!-- tabs:start -->

### **SQL**

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT (
          SELECT DISTINCT Salary
          FROM Employee
          ORDER BY Salary DESC
          LIMIT 1 OFFSET N
      )
  );
END
```

<!-- tabs:end -->
