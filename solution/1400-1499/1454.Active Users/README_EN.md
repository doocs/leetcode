---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1454.Active%20Users/README_EN.md
tags:
    - Database
---

# [1454. Active Users 🔒](https://leetcode.com/problems/active-users)

[中文文档](/solution/1400-1499/1454.Active%20Users/README.md)

## Description

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

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH t AS
    (SELECT *,
		 SUM(id) over(partition by id
    ORDER BY  login_date range interval 4 day preceding)/id cnt
    FROM
        (SELECT DISTINCT *
        FROM Accounts
        JOIN Logins using(id) ) tt )
    SELECT DISTINCT id,
		 name
FROM t
WHERE cnt=5;
```

<!-- tabs:end -->

<!-- end -->
