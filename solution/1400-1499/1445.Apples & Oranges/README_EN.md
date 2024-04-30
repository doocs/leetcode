# [1445. Apples & Oranges 🔒](https://leetcode.com/problems/apples-oranges)

[中文文档](/solution/1400-1499/1445.Apples%20%26%20Oranges/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Sales</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| sale_date     | date    |
| fruit         | enum    | 
| sold_num      | int     | 
+---------------+---------+
(sale_date, fruit) is the primary key (combination of columns with unique values) of this table.
This table contains the sales of &quot;apples&quot; and &quot;oranges&quot; sold each day.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the difference between the number of <strong>apples</strong> and <strong>oranges</strong> sold each day.</p>

<p>Return the result table <strong>ordered</strong> by <code>sale_date</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sales table:
+------------+------------+-------------+
| sale_date  | fruit      | sold_num    |
+------------+------------+-------------+
| 2020-05-01 | apples     | 10          |
| 2020-05-01 | oranges    | 8           |
| 2020-05-02 | apples     | 15          |
| 2020-05-02 | oranges    | 15          |
| 2020-05-03 | apples     | 20          |
| 2020-05-03 | oranges    | 0           |
| 2020-05-04 | apples     | 15          |
| 2020-05-04 | oranges    | 16          |
+------------+------------+-------------+
<strong>Output:</strong> 
+------------+--------------+
| sale_date  | diff         |
+------------+--------------+
| 2020-05-01 | 2            |
| 2020-05-02 | 0            |
| 2020-05-03 | 20           |
| 2020-05-04 | -1           |
+------------+--------------+
<strong>Explanation:</strong> 
Day 2020-05-01, 10 apples and 8 oranges were sold (Difference  10 - 8 = 2).
Day 2020-05-02, 15 apples and 15 oranges were sold (Difference 15 - 15 = 0).
Day 2020-05-03, 20 apples and 0 oranges were sold (Difference 20 - 0 = 20).
Day 2020-05-04, 15 apples and 16 oranges were sold (Difference 15 - 16 = -1).
</pre>

## Solutions

### Solution 1: Group By + Sum

We can group the data by date, and then use the `sum` function to calculate the difference in sales between apples and oranges for each day. If it is an apple, we represent it with a positive number, and if it is an orange, we represent it with a negative number. Finally, we sort the data by date.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    sale_date,
    SUM(IF(fruit = 'apples', sold_num, -sold_num)) AS diff
FROM Sales
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
