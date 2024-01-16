# [1142. User Activity for the Past 30 Days II](https://leetcode.com/problems/user-activity-for-the-past-30-days-ii)

[中文文档](/solution/1100-1199/1142.User%20Activity%20for%20the%20Past%2030%20Days%20II/README.md)

## Description

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

<p>Write a solution to find the average number of sessions per user for a period of <code>30</code> days ending <code>2019-07-27</code> inclusively, <strong>rounded to 2 decimal places</strong>. The sessions we want to count for a user are those with at least one activity in that time period.</p>

<p>The&nbsp;result format is in the following example.</p>

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
| 3       | 5          | 2019-07-21    | open_session  |
| 3       | 5          | 2019-07-21    | scroll_down   |
| 3       | 5          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+
<strong>Output:</strong> 
+---------------------------+ 
| average_sessions_per_user |
+---------------------------+ 
| 1.33                      |
+---------------------------+
<strong>Explanation:</strong> User 1 and 2 each had 1 session in the past 30 days while user 3 had 2 sessions so the average is (1 + 1 + 2) / 3 = 1.33.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            COUNT(DISTINCT session_id) AS sessions
        FROM Activity
        WHERE activity_date <= '2019-07-27' AND DATEDIFF('2019-07-27', activity_date) < 30
        GROUP BY user_id
    )
SELECT IFNULL(ROUND(AVG(sessions), 2), 0) AS average_sessions_per_user
FROM T;
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```sql
SELECT
    IFNULL(
        ROUND(COUNT(DISTINCT session_id) / COUNT(DISTINCT user_id), 2),
        0
    ) AS average_sessions_per_user
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) < 30;
```

<!-- tabs:end -->

<!-- end -->
