---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2837.Total%20Traveled%20Distance/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2837. Total Traveled Distance 🔒](https://leetcode.com/problems/total-traveled-distance)

[中文文档](/solution/2800-2899/2837.Total%20Traveled%20Distance/README.md)

## Description

<!-- description:start -->

<p>Table: <code><font face="monospace">Users</font></code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| name        | varchar |
+-------------+---------+
<code>user_id</code> is the column with unique values for this table.
Each row of this table contains user id and name.
</pre>

<p>Table: <code>Rides</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| ride_id      | int  |
| user_id      | int  | 
| distance     | int  |
+--------------+------+
ride_id is the column of unique values for this table.
Each row of this table contains ride id, user id, and traveled distance.
</pre>

<p>Write a solution to calculate the <code>distance</code> traveled by <strong>each user</strong>. If there is a user&nbsp;who hasn&#39;t completed any rides, then their <code>distance</code> should be considered&nbsp;as <code>0</code>. Output the <code>user_id</code>, <code>name</code> and total traveled <code>distance</code>.</p>

<p>Return<em> the result table ordered by </em><code>user_id</code><em> in <strong>ascending</strong> order.</em></p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+---------+---------+
| user_id | name    |
+---------+---------+
| 17      | Addison |
| 14      | Ethan   |
| 4       | Michael |
| 2       | Avery   |
| 10      | Eleanor |
+---------+---------+
Rides table:
+---------+---------+----------+
| ride_id | user_id | distance |
+---------+---------+----------+
| 72      | 17      | 160      |
| 42      | 14      | 161      |
| 45      | 4       | 59       |
| 32      | 2       | 197      |
| 15      | 4       | 357      |
| 56      | 2       | 196      |
| 10      | 14      | 25       |
+---------+---------+----------+
<strong>Output:</strong> 
+---------+---------+-------------------+
| user_id | name    | traveled distance |
+---------+---------+-------------------+
| 2       | Avery   | 393               |
| 4       | Michael | 416               |
| 10      | Eleanor | 0                 |
| 14      | Ethan   | 186               |
| 17      | Addison | 160               |
+---------+---------+-------------------+
<strong>Explanation:</strong> 
-  User id 2 completed two journeys of 197 and 196, resulting in a combined travel distance of 393.
-  User id 4 completed two journeys of 59 and 357, resulting in a combined travel distance of 416.
-  User id 14 completed two journeys of 161 and 25, resulting in a combined travel distance of 186.
-  User id 16 completed only one journey of 160.
-  User id 10 did not complete any journeys, thus the total travel distance remains at 0.
Returning the table orderd by user_id in ascending order.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Left Join + Group By Sum

We can use a left join to connect the two tables, and then use group by sum to calculate the total distance for each user. Note that if a user has not completed any rides, their distance should be considered as $0$.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT user_id, name, IFNULL(SUM(distance), 0) AS 'traveled distance'
FROM
    Users
    LEFT JOIN Rides USING (user_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
