---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1355.Activity%20Participants/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1355. Activity Participants 🔒](https://leetcode.com/problems/activity-participants)

[中文文档](/solution/1300-1399/1355.Activity%20Participants/README.md)

## Description

<p>Table: <code>Friends</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
| activity      | varchar |
+---------------+---------+
id is the id of the friend and the primary key for this table in SQL.
name is the name of the friend.
activity is the name of the activity which the friend takes part in.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Activities</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
In SQL, id is the primary key for this table.
name is the name of the activity.
</pre>

<p>&nbsp;</p>

<p>Find the names of all the activities with neither the maximum nor the minimum number of participants.</p>

<p>Each activity in the <code>Activities</code> table is performed by any person in the table Friends.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Friends table:
+------+--------------+---------------+
| id   | name         | activity      |
+------+--------------+---------------+
| 1    | Jonathan D.  | Eating        |
| 2    | Jade W.      | Singing       |
| 3    | Victor J.    | Singing       |
| 4    | Elvis Q.     | Eating        |
| 5    | Daniel A.    | Eating        |
| 6    | Bob B.       | Horse Riding  |
+------+--------------+---------------+
Activities table:
+------+--------------+
| id   | name         |
+------+--------------+
| 1    | Eating       |
| 2    | Singing      |
| 3    | Horse Riding |
+------+--------------+
<strong>Output:</strong> 
+--------------+
| activity     |
+--------------+
| Singing      |
+--------------+
<strong>Explanation:</strong> 
Eating activity is performed by 3 friends, maximum number of participants, (Jonathan D. , Elvis Q. and Daniel A.)
Horse Riding activity is performed by 1 friend, minimum number of participants, (Bob B.)
Singing is performed by 2 friends (Victor J. and Jade W.)
</pre>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT activity, COUNT(1) AS cnt
        FROM Friends
        GROUP BY activity
    )
SELECT activity
FROM t
WHERE cnt > (SELECT MIN(cnt) FROM t) AND cnt < (SELECT MAX(cnt) FROM t);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
