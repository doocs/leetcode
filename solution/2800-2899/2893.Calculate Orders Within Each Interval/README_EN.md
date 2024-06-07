---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2893.Calculate%20Orders%20Within%20Each%20Interval/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2893. Calculate Orders Within Each Interval 🔒](https://leetcode.com/problems/calculate-orders-within-each-interval)

[中文文档](/solution/2800-2899/2893.Calculate%20Orders%20Within%20Each%20Interval/README.md)

## Description

<!-- description:start -->

<p>Table: <code><font face="monospace">Orders</font></code></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| minute      | int  | 
| order_count | int  |
+-------------+------+
minute is the primary key for this table.
Each row of this table contains the minute and number of orders received during that specific minute. The total number of rows will be a multiple of 6.
</pre>

<p>Write a query to calculate <strong>total</strong> <strong>orders</strong><b> </b>within each <strong>interval</strong>. Each interval is defined as a combination of <code>6</code> minutes.</p>

<ul>
	<li>Minutes <code>1</code> to <code>6</code> fall within interval <code>1</code>, while minutes <code>7</code> to <code>12</code> belong to interval <code>2</code>, and so forth.</li>
</ul>

<p>Return<em> the result table ordered by <strong>interval_no</strong> in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
+--------+-------------+
| minute | order_count | 
+--------+-------------+
| 1      | 0           |
| 2      | 2           | 
| 3      | 4           | 
| 4      | 6           | 
| 5      | 1           | 
| 6      | 4           | 
| 7      | 1           | 
| 8      | 2           | 
| 9      | 4           | 
| 10     | 1           | 
| 11     | 4           | 
| 12     | 6           | 
+--------+-------------+
<strong>Output:</strong> 
+-------------+--------------+
| interval_no | total_orders | 
+-------------+--------------+
| 1           | 17           | 
| 2           | 18           |    
+-------------+--------------+
<strong>Explanation:</strong> 
- Interval number 1 comprises minutes from 1 to 6. The total orders in these six minutes are (0 + 2 + 4 + 6 + 1 + 4) = 17.
- Interval number 2 comprises minutes from 7 to 12. The total orders in these six minutes are (1 + 2 + 4 + 1 + 4 + 6) = 18.
Returning table orderd by interval_no in ascending order.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            minute,
            SUM(order_count) OVER (
                ORDER BY minute
                ROWS 5 PRECEDING
            ) AS total_orders
        FROM Orders
    )
SELECT minute / 6 AS interval_no, total_orders
FROM T
WHERE minute % 6 = 0;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    FLOOR((minute + 5) / 6) AS interval_no,
    SUM(order_count) AS total_orders
FROM Orders
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
