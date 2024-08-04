---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1454.Active%20Users/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1454. Active Users 🔒](https://leetcode.com/problems/active-users)

[中文文档](/solution/1400-1499/1454.Active%20Users/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Accounts</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id is the primary key (column with unique values) for this table.
This table contains the account id and the user name of each account.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Logins</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| login_date    | date    |
+---------------+---------+
This table may contain duplicate rows.
This table contains the account id of the user who logged in and the login date. A user may log in multiple times in the day.
</pre>

<p>&nbsp;</p>

<p><strong>Active users</strong> are those who logged in to their accounts for five or more consecutive days.</p>

<p>Write a solution to find the id and the name of <strong>active users</strong>.</p>

<p>Return the result table <strong>ordered</strong> by <code>id</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Accounts table:
+----+----------+
| id | name     |
+----+----------+
| 1  | Winston  |
| 7  | Jonathan |
+----+----------+
Logins table:
+----+------------+
| id | login_date |
+----+------------+
| 7  | 2020-05-30 |
| 1  | 2020-05-30 |
| 7  | 2020-05-31 |
| 7  | 2020-06-01 |
| 7  | 2020-06-02 |
| 7  | 2020-06-02 |
| 7  | 2020-06-03 |
| 1  | 2020-06-07 |
| 7  | 2020-06-10 |
+----+------------+
<strong>Output:</strong> 
+----+----------+
| id | name     |
+----+----------+
| 7  | Jonathan |
+----+----------+
<strong>Explanation:</strong> 
User Winston with id = 1 logged in 2 times only in 2 different days, so, Winston is not an active user.
User Jonathan with id = 7 logged in 7 times in 6 different days, five of them were consecutive days, so, Jonathan is an active user.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you write a general solution if the active users are those who logged in to their accounts for <code>n</code> or more consecutive days?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Using Window Functions

First, we join the `Logins` table and the `Accounts` table, and remove duplicates to get the temporary table `T`.

Then, we use the window function `ROW_NUMBER()` to calculate the base login date `g` for each user `id`. If a user logs in for 5 consecutive days, their `g` values are the same.

Finally, we group by `id` and `g` to count the number of logins for each user. If the number of logins is greater than or equal to 5, then the user is considered active.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT *
        FROM
            Logins
            JOIN Accounts USING (id)
    ),
    P AS (
        SELECT
            *,
            DATE_SUB(
                login_date,
                INTERVAL ROW_NUMBER() OVER (
                    PARTITION BY id
                    ORDER BY login_date
                ) DAY
            ) g
        FROM T
    )
SELECT DISTINCT id, name
FROM P
GROUP BY id, g
HAVING COUNT(*) >= 5
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
