# [615. Average Salary Departments VS Company](https://leetcode.com/problems/average-salary-departments-vs-company)

[中文文档](/solution/0600-0699/0615.Average%20Salary%20Departments%20VS%20Company/README.md)

## Description

<p>Table: <code>Salary</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| employee_id | int  |
| amount      | int  |
| pay_date    | date |
+-------------+------+
id is the primary key column for this table.
Each row of this table indicates the salary of an employee in one month.
employee_id is a foreign key from the Employee table.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Employee</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| employee_id   | int  |
| department_id | int  |
+---------------+------+
employee_id is the primary key column for this table.
Each row of this table indicates the department of an employee.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the comparison result <strong>(higher/lower/same)</strong> of the average salary of employees in a department to the company&#39;s average salary.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Salary table:
+----+-------------+--------+------------+
| id | employee_id | amount | pay_date   |
+----+-------------+--------+------------+
| 1  | 1           | 9000   | 2017/03/31 |
| 2  | 2           | 6000   | 2017/03/31 |
| 3  | 3           | 10000  | 2017/03/31 |
| 4  | 1           | 7000   | 2017/02/28 |
| 5  | 2           | 6000   | 2017/02/28 |
| 6  | 3           | 8000   | 2017/02/28 |
+----+-------------+--------+------------+
Employee table:
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
| 1           | 1             |
| 2           | 2             |
| 3           | 2             |
+-------------+---------------+
<strong>Output:</strong> 
+-----------+---------------+------------+
| pay_month | department_id | comparison |
+-----------+---------------+------------+
| 2017-02   | 1             | same       |
| 2017-03   | 1             | higher     |
| 2017-02   | 2             | same       |
| 2017-03   | 2             | lower      |
+-----------+---------------+------------+
<strong>Explanation:</strong> 
In March, the company&#39;s average salary is (9000+6000+10000)/3 = 8333.33...
The average salary for department &#39;1&#39; is 9000, which is the salary of employee_id &#39;1&#39; since there is only one employee in this department. So the comparison result is &#39;higher&#39; since 9000 &gt; 8333.33 obviously.
The average salary of department &#39;2&#39; is (6000 + 10000)/2 = 8000, which is the average of employee_id &#39;2&#39; and &#39;3&#39;. So the comparison result is &#39;lower&#39; since 8000 &lt; 8333.33.

With he same formula for the average salary comparison in February, the result is &#39;same&#39; since both the department &#39;1&#39; and &#39;2&#39; have the same average salary with the company, which is 7000.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```

```

<!-- tabs:end -->
