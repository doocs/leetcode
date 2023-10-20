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

**Solution 1: Window Function**

We can use the window functions `LAG` and `LEAD` to determine whether the previous row and the next row of the current row are equal to the `num` of the current row. If the `num` of the current row is equal to the `num` of the previous row, we set the field $a$ to $1$, otherwise we set it to $0$; if the `num` of the current row is equal to the `num` of the next row, we set the field $b$ to $1$, otherwise we set it to $0$.

Finally, we only need to filter out the rows where $a = 1$ and $b = 1$, which are the numbers that appear at least three times in a row. Note that we need to use the `DISTINCT` keyword to remove duplicates from the results.

We can also group the numbers by using the `IF` function to determine whether the `num` of the current row is equal to the `num` of the previous row. If they are equal, we set it to $0$, otherwise we set it to $1$. Then, we use the window function `SUM` to calculate the prefix sum, which is the grouping identifier. Finally, we only need to group by the grouping identifier and filter out the numbers with a row count greater than or equal to $3$ in each group. Similarly, we need to use the `DISTINCT` keyword to remove duplicates from the results.

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            num = (LAG(num) OVER (ORDER BY id)) AS a,
            num = (LEAD(num) OVER (ORDER BY id)) AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = 1 AND b = 1;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER (ORDER BY id)), 0, 1) AS st
        FROM Logs
    ),
    S AS (
        SELECT *, SUM(st) OVER (ORDER BY id) AS p
        FROM T
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM S
GROUP BY p
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->
