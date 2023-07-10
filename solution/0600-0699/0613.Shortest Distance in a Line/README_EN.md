# [613. Shortest Distance in a Line](https://leetcode.com/problems/shortest-distance-in-a-line)

[中文文档](/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README.md)

## Description

<p>Table: <code>Point</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
+-------------+------+
x is the primary key column for this table.
Each row of this table indicates the position of a point on the X-axis.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the shortest distance between any two points from the <code>Point</code> table.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Point table:
+----+
| x  |
+----+
| -1 |
| 0  |
| 2  |
+----+
<strong>Output:</strong> 
+----------+
| shortest |
+----------+
| 1        |
+----------+
<strong>Explanation:</strong> The shortest distance is between points -1 and 0 which is |(-1) - 0| = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> How could you optimize your query if the <code>Point</code> table is ordered <strong>in ascending order</strong>?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    min(abs(p1.x - p2.x)) AS shortest
FROM
    Point AS p1
    JOIN Point AS p2 ON p1.x != p2.x;
```

```sql
# Write your MySQL query statement below
SELECT x - lag(x) OVER (ORDER BY x) AS shortest
FROM Point
ORDER BY 1
LIMIT 1, 1;
```

<!-- tabs:end -->
