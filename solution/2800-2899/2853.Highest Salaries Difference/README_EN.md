# [2853. Highest Salaries Difference](https://leetcode.com/problems/highest-salaries-difference)

[中文文档](/solution/2800-2899/2853.Highest%20Salaries%20Difference/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **SQL**

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
