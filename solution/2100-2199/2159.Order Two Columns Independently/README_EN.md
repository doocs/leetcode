# [2159. Order Two Columns Independently](https://leetcode.com/problems/order-two-columns-independently)

[中文文档](/solution/2100-2199/2159.Order%20Two%20Columns%20Independently/README.md)

## Description

<p>Table: <code>Data</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| first_col   | int  |
| second_col  | int  |
+-------------+------+
This table may contain duplicate rows.
</pre>

<p>&nbsp;</p>

<p>Write a solution to independently:</p>

<ul>
	<li>order <code>first_col</code> in <strong>ascending order</strong>.</li>
	<li>order <code>second_col</code> in <strong>descending order</strong>.</li>
</ul>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Data table:
+-----------+------------+
| first_col | second_col |
+-----------+------------+
| 4         | 2          |
| 2         | 3          |
| 3         | 1          |
| 1         | 4          |
+-----------+------------+
<strong>Output:</strong> 
+-----------+------------+
| first_col | second_col |
+-----------+------------+
| 1         | 4          |
| 2         | 3          |
| 3         | 2          |
| 4         | 1          |
+-----------+------------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            first_col,
            ROW_NUMBER() OVER (ORDER BY first_col) AS rk
        FROM Data
    ),
    T AS (
        SELECT
            second_col,
            ROW_NUMBER() OVER (ORDER BY second_col DESC) AS rk
        FROM Data
    )
SELECT first_col, second_col
FROM
    S
    JOIN T USING (rk);
```

<!-- tabs:end -->
