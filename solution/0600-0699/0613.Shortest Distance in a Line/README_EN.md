# [613. Shortest Distance in a Line 🔒](https://leetcode.com/problems/shortest-distance-in-a-line)

[中文文档](/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README.md)

<!-- tags:Database -->

<!-- difficulty:Easy -->

## Description

<p>Table: <code>Point</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
+-------------+------+
In SQL, x is the primary key column for this table.
Each row of this table indicates the position of a point on the X-axis.
</pre>

<p>&nbsp;</p>

<p>Find the shortest distance between any two points from the <code>Point</code> table.</p>

<p>The result format is in the following example.</p>

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
<p><strong>Follow up:</strong> How could you optimize your solution if the <code>Point</code> table is ordered <strong>in ascending order</strong>?</p>

## Solutions

### Solution 1: Self-Join

We can use a self-join to join each point in the table with the larger points, and then calculate the distance between the two points. Finally, we can take the minimum distance.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT MIN(p2.x - p1.x) AS shortest
FROM
    Point AS p1
    JOIN Point AS p2 ON p1.x < p2.x;
```

<!-- tabs:end -->

### Solution 2: Window Function

We can use a window function to sort the points in the table by their $x$ values, and then calculate the distance between adjacent points. Finally, we can take the minimum distance.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT x - LAG(x) OVER (ORDER BY x) AS shortest
FROM Point
ORDER BY 1
LIMIT 1, 1;
```

<!-- tabs:end -->

<!-- end -->
