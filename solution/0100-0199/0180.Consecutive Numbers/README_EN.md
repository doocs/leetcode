# [180. Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers)

[中文文档](/solution/0100-0199/0180.Consecutive%20Numbers/README.md)

## Description

<p>Table: <code>Logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
In SQL, id is the primary key for this table.
id is an autoincrement column.
</pre>

<p>&nbsp;</p>

<p>Find all numbers that appear at least three times consecutively.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Logs table:
+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
<strong>Output:</strong> 
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
<strong>Explanation:</strong> 1 is the only number that appears consecutively for at least three times.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            CASE
                WHEN (LAG(num) OVER (ORDER BY id)) = num THEN 0
                ELSE 1
            END AS mark
        FROM Logs
    ),
    p AS (SELECT num, SUM(mark) OVER (ORDER BY id) AS gid FROM t)
SELECT DISTINCT num AS ConsecutiveNums
FROM p
GROUP BY gid
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->
