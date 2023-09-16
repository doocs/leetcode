# [2853. Highest Salaries Difference](https://leetcode.cn/problems/highest-salaries-difference)

[English Version](/solution/2800-2899/2853.Highest%20Salaries%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code><font face="monospace">Salaries</font></code></p>

<pre>
+-------------+---------+ 
| Column Name | Type    | 
+-------------+---------+ 
| emp_name    | varchar | 
| department  | varchar | 
| salary      | int     |
+-------------+---------+
(emp_name, department) is the primary key for this table.
Each row of this table contains emp_name, department and salary. There will be <strong>at least one</strong> entry for the engineering and marketing departments.
</pre>

<p>Write an SQL query to calculate the difference between the <strong>highest</strong> salaries in the <strong>marketing</strong> and <strong>engineering</strong> <code>department</code>. Output the absolute difference in salaries.</p>

<p>Return<em> the result table.</em></p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Salaries table:
+----------+-------------+--------+
| emp_name | department  | salary |
+----------+-------------+--------+
| Kathy    | Engineering | 50000  |
| Roy      | Marketing   | 30000  |
| Charles  | Engineering | 45000  |
| Jack     | Engineering | 85000  | 
| Benjamin | Marketing   | 34000  |
| Anthony  | Marketing   | 42000  |
| Edward   | Engineering | 102000 |
| Terry    | Engineering | 44000  |
| Evelyn   | Marketing   | 53000  |
| Arthur   | Engineering | 32000  |
+----------+-------------+--------+
<strong>Output:</strong> 
+-------------------+
| salary_difference | 
+-------------------+
| 49000             | 
+-------------------+
<strong>Explanation:</strong> 
- The Engineering and Marketing departments have the highest salaries of 102,000 and 53,000, respectively. Resulting in an absolute difference of 49,000.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：GROUP BY 分组**

我们可以先分别计算出每个部门的最高工资，然后再计算两个最高工资的差值。

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT max(s) - min(s) AS salary_difference
FROM
    (
        SELECT max(salary) AS s
        FROM Salaries
        GROUP BY department
    ) AS t;
```

<!-- tabs:end -->
