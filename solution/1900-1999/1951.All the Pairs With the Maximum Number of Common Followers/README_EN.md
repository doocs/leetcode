---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1951.All%20the%20Pairs%20With%20the%20Maximum%20Number%20of%20Common%20Followers/README_EN.md
tags:
    - Database
---

# [1951. All the Pairs With the Maximum Number of Common Followers 🔒](https://leetcode.com/problems/all-the-pairs-with-the-maximum-number-of-common-followers)

[中文文档](/solution/1900-1999/1951.All%20the%20Pairs%20With%20the%20Maximum%20Number%20of%20Common%20Followers/README.md)

## Description

<p>Table: <code>Relations</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| follower_id | int  |
+-------------+------+
(user_id, follower_id) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that the user with ID follower_id is following the user with ID user_id.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find all the pairs of users with the maximum number of common followers. In other words, if the maximum number of common followers between any two users is <code>maxCommon</code>, then you have to return all pairs of users that have <code>maxCommon</code> common followers.</p>

<p>The result table should contain the pairs <code>user1_id</code> and <code>user2_id</code> where <code>user1_id &lt; user2_id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Relations table:
+---------+-------------+
| user_id | follower_id |
+---------+-------------+
| 1       | 3           |
| 2       | 3           |
| 7       | 3           |
| 1       | 4           |
| 2       | 4           |
| 7       | 4           |
| 1       | 5           |
| 2       | 6           |
| 7       | 5           |
+---------+-------------+
<strong>Output:</strong> 
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 7        |
+----------+----------+
<strong>Explanation:</strong> 
Users 1 and 2 have two common followers (3 and 4).
Users 1 and 7 have three common followers (3, 4, and 5).
Users 2 and 7 have two common followers (3 and 4).
Since the maximum number of common followers between any two users is 3, we return all pairs of users with three common followers, which is only the pair (1, 7). We return the pair as (1, 7), not as (7, 1).
Note that we do not have any information about the users that follow users 3, 4, and 5, so we consider them to have 0 followers.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            r1.user_id AS user1_id,
            r2.user_id AS user2_id,
            RANK() OVER (ORDER BY COUNT(1) DESC) AS rk
        FROM
            Relations AS r1
            JOIN Relations AS r2 ON r1.follower_id = r2.follower_id AND r1.user_id < r2.user_id
        GROUP BY r1.user_id, r2.user_id
    )
SELECT
    user1_id,
    user2_id
FROM t
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- end -->
