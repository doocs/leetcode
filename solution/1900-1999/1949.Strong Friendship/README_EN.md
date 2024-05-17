---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1949.Strong%20Friendship/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1949. Strong Friendship 🔒](https://leetcode.com/problems/strong-friendship)

[中文文档](/solution/1900-1999/1949.Strong%20Friendship/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Friendship</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user1_id    | int  |
| user2_id    | int  |
+-------------+------+
(user1_id, user2_id) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that the users user1_id and user2_id are friends.
Note that user1_id &lt; user2_id.
</pre>

<p>&nbsp;</p>

<p>A friendship between a pair of friends <code>x</code> and <code>y</code> is <strong>strong</strong> if <code>x</code> and <code>y</code> have <strong>at least three</strong> common friends.</p>

<p>Write a solution to find all the <strong>strong friendships</strong>.</p>

<p>Note that the result table should not contain duplicates with <code>user1_id &lt; user2_id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 1        | 3        |
| 2        | 3        |
| 1        | 4        |
| 2        | 4        |
| 1        | 5        |
| 2        | 5        |
| 1        | 7        |
| 3        | 7        |
| 1        | 6        |
| 3        | 6        |
| 2        | 6        |
+----------+----------+
<strong>Output:</strong> 
+----------+----------+---------------+
| user1_id | user2_id | common_friend |
+----------+----------+---------------+
| 1        | 2        | 4             |
| 1        | 3        | 3             |
+----------+----------+---------------+
<strong>Explanation:</strong> 
Users 1 and 2 have 4 common friends (3, 4, 5, and 6).
Users 1 and 3 have 3 common friends (2, 6, and 7).
We did not include the friendship of users 2 and 3 because they only have two common friends (1 and 6).
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *
        FROM Friendship
        UNION ALL
        SELECT
            user2_id,
            user1_id
        FROM Friendship
    )
SELECT
    t1.user1_id,
    t1.user2_id,
    COUNT(1) AS common_friend
FROM
    t AS t1
    JOIN t AS t2 ON t1.user2_id = t2.user1_id
    JOIN t AS t3 ON t1.user1_id = t3.user1_id
WHERE t3.user2_id = t2.user2_id AND t1.user1_id < t1.user2_id
GROUP BY t1.user1_id, t1.user2_id
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
