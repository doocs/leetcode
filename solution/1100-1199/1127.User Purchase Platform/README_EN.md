---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1127.User%20Purchase%20Platform/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1127. User Purchase Platform 🔒](https://leetcode.com/problems/user-purchase-platform)

[中文文档](/solution/1100-1199/1127.User%20Purchase%20Platform/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Spending</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| spend_date  | date    |
| platform    | enum    | 
| amount      | int     |
+-------------+---------+
The table logs the history of the spending of users that make purchases from an online shopping website that has a desktop and a mobile application.
(user_id, spend_date, platform) is the primary key (combination of columns with unique values) of this table.
The platform column is an ENUM (category) type of (&#39;desktop&#39;, &#39;mobile&#39;).
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the total number of users and the total amount spent using the mobile only, the desktop only, and both mobile and desktop together for each date.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Spending table:
+---------+------------+----------+--------+
| user_id | spend_date | platform | amount |
+---------+------------+----------+--------+
| 1       | 2019-07-01 | mobile   | 100    |
| 1       | 2019-07-01 | desktop  | 100    |
| 2       | 2019-07-01 | mobile   | 100    |
| 2       | 2019-07-02 | mobile   | 100    |
| 3       | 2019-07-01 | desktop  | 100    |
| 3       | 2019-07-02 | desktop  | 100    |
+---------+------------+----------+--------+
<strong>Output:</strong> 
+------------+----------+--------------+-------------+
| spend_date | platform | total_amount | total_users |
+------------+----------+--------------+-------------+
| 2019-07-01 | desktop  | 100          | 1           |
| 2019-07-01 | mobile   | 100          | 1           |
| 2019-07-01 | both     | 200          | 1           |
| 2019-07-02 | desktop  | 100          | 1           |
| 2019-07-02 | mobile   | 100          | 1           |
| 2019-07-02 | both     | 0            | 0           |
+------------+----------+--------------+-------------+ 
<strong>Explanation:</strong> 
On 2019-07-01, user 1 purchased using <strong>both</strong> desktop and mobile, user 2 purchased using mobile <strong>only</strong> and user 3 purchased using desktop <strong>only</strong>.
On 2019-07-02, user 2 purchased using mobile <strong>only</strong>, user 3 purchased using desktop <strong>only</strong> and no one purchased using <strong>both</strong> platforms.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT DISTINCT spend_date, 'desktop' AS platform FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'mobile' FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'both' FROM Spending
    ),
    T AS (
        SELECT
            user_id,
            spend_date,
            SUM(amount) AS amount,
            IF(COUNT(platform) = 1, platform, 'both') AS platform
        FROM Spending
        GROUP BY 1, 2
    )
SELECT
    p.*,
    IFNULL(SUM(amount), 0) AS total_amount,
    COUNT(t.user_id) AS total_users
FROM
    P AS p
    LEFT JOIN T AS t USING (spend_date, platform)
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
