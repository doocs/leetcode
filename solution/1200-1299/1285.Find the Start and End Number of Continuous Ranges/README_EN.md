# [1285. Find the Start and End Number of Continuous Ranges](https://leetcode.com/problems/find-the-start-and-end-number-of-continuous-ranges)

[中文文档](/solution/1200-1299/1285.Find%20the%20Start%20and%20End%20Number%20of%20Continuous%20Ranges/README.md)

## Description

<p>Table: <code>Logs</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| log_id        | int     |
+---------------+---------+
log_id is the primary key for this table.
Each row of this table contains the ID in a log Table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the start and end number of continuous ranges in the table <code>Logs</code>.</p>

<p>Return the result table ordered by <code>start_id</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Logs table:
+------------+
| log_id     |
+------------+
| 1          |
| 2          |
| 3          |
| 7          |
| 8          |
| 10         |
+------------+
<strong>Output:</strong> 
+------------+--------------+
| start_id   | end_id       |
+------------+--------------+
| 1          | 3            |
| 7          | 8            |
| 10         | 10           |
+------------+--------------+
<strong>Explanation:</strong> 
The result table should contain all ranges in table Logs.
From 1 to 3 is contained in the table.
From 4 to 6 is missing in the table
From 7 to 8 is contained in the table.
Number 9 is missing from the table.
Number 10 is contained in the table.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
  MIN(log_id) AS start_id,
  MAX(log_id) AS end_id
FROM (SELECT
  log_id,
  log_id - ROW_NUMBER() OVER (ORDER BY log_id) AS rk
FROM Logs) t
GROUP BY rk;
```

<!-- tabs:end -->
