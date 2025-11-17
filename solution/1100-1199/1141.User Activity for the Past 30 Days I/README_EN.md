---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1141.User%20Activity%20for%20the%20Past%2030%20Days%20I/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1141. User Activity for the Past 30 Days I](https://leetcode.com/problems/user-activity-for-the-past-30-days-i)

[中文文档](/solution/1100-1199/1141.User%20Activity%20for%20the%20Past%2030%20Days%20I/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Activity</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
This table may have duplicate rows.
The activity_type column is an ENUM (category) of type (&#39;open_session&#39;, &#39;end_session&#39;, &#39;scroll_down&#39;, &#39;send_message&#39;).
The table shows the user activities for a social media website. 
Note that each session belongs to exactly one user.
</pre>

<p>&nbsp;</p>

<p>Write a solution to find the daily active user count for a period of <code>30</code> days ending <code>2019-07-27</code> inclusively. A user was active on someday if they made at least one activity on that day.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>Note: <strong>Any</strong> activity from (<code>&#39;open_session&#39;</code>, <code>&#39;end_session&#39;</code>, <code>&#39;scroll_down&#39;</code>, <code>&#39;send_message&#39;</code>) will be considered valid activity for a user to be considered active on a day.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Activity table:
+---------+------------+---------------+---------------+
| user_id | session_id | activity_date | activity_type |
+---------+------------+---------------+---------------+
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+
<strong>Output:</strong> 
+------------+--------------+ 
| day        | active_users |
+------------+--------------+ 
| 2019-07-20 | 2            |
| 2019-07-21 | 2            |
+------------+--------------+ 
<strong>Explanation:</strong> Note that we do not care about days with zero active users.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT activity_date AS day, COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE activity_date <= '2019-07-27' AND DATEDIFF('2019-07-27', activity_date) < 30
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
