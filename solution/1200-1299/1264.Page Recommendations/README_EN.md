---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1264.Page%20Recommendations/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1264. Page Recommendations 🔒](https://leetcode.com/problems/page-recommendations)

[中文文档](/solution/1200-1299/1264.Page%20Recommendations/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
(user1_id, user2_id) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that there is a friendship relation between user1_id and user2_id.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Likes</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| page_id     | int     |
+-------------+---------+
(user_id, page_id) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that user_id likes page_id.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to recommend pages to the user with <code>user_id = 1</code> using the pages that your friends liked. It should not recommend pages you already liked.</p>

<p>Return result table in <strong>any order</strong> without duplicates.</p>

<p>The&nbsp;result format is in the following example.</p>

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
| 1        | 4        |
| 2        | 3        |
| 2        | 4        |
| 2        | 5        |
| 6        | 1        |
+----------+----------+
Likes table:
+---------+---------+
| user_id | page_id |
+---------+---------+
| 1       | 88      |
| 2       | 23      |
| 3       | 24      |
| 4       | 56      |
| 5       | 11      |
| 6       | 33      |
| 2       | 77      |
| 3       | 77      |
| 6       | 88      |
+---------+---------+
<strong>Output:</strong> 
+------------------+
| recommended_page |
+------------------+
| 23               |
| 24               |
| 56               |
| 33               |
| 77               |
+------------------+
<strong>Explanation:</strong> 
User one is friend with users 2, 3, 4 and 6.
Suggested pages are 23 from user 2, 24 from user 3, 56 from user 3 and 33 from user 6.
Page 77 is suggested from both user 2 and user 3.
Page 88 is not suggested because user 1 already likes it.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union + Equi-Join + Subquery

First, we query all users who are friends with `user_id = 1` and record them in the `T` table. Then, we query all pages that users in the `T` table like, and finally exclude the pages that `user_id = 1` likes.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT user1_id AS user_id FROM Friendship WHERE user2_id = 1
        UNION
        SELECT user2_id AS user_id FROM Friendship WHERE user1_id = 1
    )
SELECT DISTINCT page_id AS recommended_page
FROM
    T
    JOIN Likes USING (user_id)
WHERE page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT DISTINCT page_id AS recommended_page
FROM Likes
WHERE
    user_id IN (
        SELECT user1_id AS user_id FROM Friendship WHERE user2_id = 1
        UNION ALL
        SELECT user2_id AS user_id FROM Friendship WHERE user1_id = 1
    )
    AND page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
