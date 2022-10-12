# [579. Find Cumulative Salary of an Employee](https://leetcode.com/problems/find-cumulative-salary-of-an-employee)

[中文文档](/solution/0500-0599/0579.Find%20Cumulative%20Salary%20of%20an%20Employee/README.md)

## Description

<p>Table: <code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| month       | int  |
| salary      | int  |
+-------------+------+
(id, month) is the primary key for this table.
Each row in the table indicates the salary of an employee in one month during the year 2020.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to calculate the <strong>cumulative salary summary</strong> for every employee in a single unified table.</p>

<p>The <strong>cumulative salary summary</strong> for an employee can be calculated as follows:</p>

<ul>
	<li>For each month that the employee worked, <strong>sum</strong> up the salaries in <strong>that month</strong> and the <strong>previous two months</strong>. This is their <strong>3-month sum</strong> for that month. If an employee did not work for the company in previous months, their effective salary for those months is <code>0</code>.</li>
	<li>Do <strong>not</strong> include the 3-month sum for the <strong>most recent month</strong> that the employee worked for in the summary.</li>
	<li>Do <strong>not</strong> include the 3-month sum for any month the employee <strong>did not work</strong>.</li>
</ul>

<p>Return the result table ordered by <code>id</code> in <strong>ascending order</strong>. In case of a tie, order it by <code>month</code> in <strong>descending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Employee table:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 1  | 1     | 20     |
| 2  | 1     | 20     |
| 1  | 2     | 30     |
| 2  | 2     | 30     |
| 3  | 2     | 40     |
| 1  | 3     | 40     |
| 3  | 3     | 60     |
| 1  | 4     | 60     |
| 3  | 4     | 70     |
| 1  | 7     | 90     |
| 1  | 8     | 90     |
+----+-------+--------+
<strong>Output:</strong> 
+----+-------+--------+
| id | month | Salary |
+----+-------+--------+
| 1  | 7     | 90     |
| 1  | 4     | 130    |
| 1  | 3     | 90     |
| 1  | 2     | 50     |
| 1  | 1     | 20     |
| 2  | 1     | 20     |
| 3  | 3     | 100    |
| 3  | 2     | 40     |
+----+-------+--------+
<strong>Explanation:</strong> 
Employee &#39;1&#39; has five salary records excluding their most recent month &#39;8&#39;:
- 90 for month &#39;7&#39;.
- 60 for month &#39;4&#39;.
- 40 for month &#39;3&#39;.
- 30 for month &#39;2&#39;.
- 20 for month &#39;1&#39;.
So the cumulative salary summary for this employee is:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 1  | 7     | 90     |  (90 + 0 + 0)
| 1  | 4     | 130    |  (60 + 40 + 30)
| 1  | 3     | 90     |  (40 + 30 + 20)
| 1  | 2     | 50     |  (30 + 20 + 0)
| 1  | 1     | 20     |  (20 + 0 + 0)
+----+-------+--------+
Note that the 3-month sum for month &#39;7&#39; is 90 because they did not work during month &#39;6&#39; or month &#39;5&#39;.

Employee &#39;2&#39; only has one salary record (month &#39;1&#39;) excluding their most recent month &#39;2&#39;.
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 2  | 1     | 20     |  (20 + 0 + 0)
+----+-------+--------+

Employee &#39;3&#39; has two salary records excluding their most recent month &#39;4&#39;:
- 60 for month &#39;3&#39;.
- 40 for month &#39;2&#39;.
So the cumulative salary summary for this employee is:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 3  | 3     | 100    |  (60 + 40 + 0)
| 3  | 2     | 40     |  (40 + 0 + 0)
+----+-------+--------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
