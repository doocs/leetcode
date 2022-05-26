# [1407. Top Travellers](https://leetcode.com/problems/top-travellers)

[中文文档](/solution/1400-1499/1407.Top%20Travellers/README.md)

## Description

<p>Table: <code>Users</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id is the primary key for this table.
name is the name of the user.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Rides</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| user_id       | int     |
| distance      | int     |
+---------------+---------+
id is the primary key for this table.
user_id is the id of the user who traveled the distance &quot;distance&quot;.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the distance traveled by each user.</p>

<p>Return the result table ordered by <code>travelled_distance</code> in <strong>descending order</strong>, if two or more users traveled the same distance, order them by their <code>name</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+------+-----------+
| id   | name      |
+------+-----------+
| 1    | Alice     |
| 2    | Bob       |
| 3    | Alex      |
| 4    | Donald    |
| 7    | Lee       |
| 13   | Jonathan  |
| 19   | Elvis     |
+------+-----------+
Rides table:
+------+----------+----------+
| id   | user_id  | distance |
+------+----------+----------+
| 1    | 1        | 120      |
| 2    | 2        | 317      |
| 3    | 3        | 222      |
| 4    | 7        | 100      |
| 5    | 13       | 312      |
| 6    | 19       | 50       |
| 7    | 7        | 120      |
| 8    | 19       | 400      |
| 9    | 7        | 230      |
+------+----------+----------+
<strong>Output:</strong> 
+----------+--------------------+
| name     | travelled_distance |
+----------+--------------------+
| Elvis    | 450                |
| Lee      | 450                |
| Bob      | 317                |
| Jonathan | 312                |
| Alex     | 222                |
| Alice    | 120                |
| Donald   | 0                  |
+----------+--------------------+
<strong>Explanation:</strong> 
Elvis and Lee traveled 450 miles, Elvis is the top traveler as his name is alphabetically smaller than Lee.
Bob, Jonathan, Alex, and Alice have only one ride and we just order them by the total distances of the ride.
Donald did not have any rides, the distance traveled by him is 0.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT name,
    COALESCE(SUM(distance), 0) AS travelled_distance
FROM Users AS u
    LEFT JOIN Rides AS r ON u.id = r.user_id
GROUP BY
    name
ORDER BY
    travelled_distance DESC,
    name;
```

<!-- tabs:end -->
