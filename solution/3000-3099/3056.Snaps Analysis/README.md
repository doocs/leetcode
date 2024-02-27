# [3056. Snaps Analysis](https://leetcode.cn/problems/snaps-analysis)

[English Version](/solution/3000-3099/3056.Snaps%20Analysis/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Activities</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| activity_id   | int     |
| user_id       | int     |
| activity_type | enum    |
| time_spent    | decimal |
+---------------+---------+
activity_id is column of unique values for this table.
activity_type is an ENUM (category) type of (&#39;send&#39;, &#39;open&#39;). 
This table contains activity id, user id, activity type and time spent.
</pre>

<p>Table: <code>Age</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| age_bucket  | enum |
+-------------+------+
user_id is the column of unique values for this table.
age_bucket is an ENUM (category) type of (&#39;21-25&#39;, &#39;26-30&#39;, &#39;31-35&#39;). 
This table contains user id and age group.</pre>

<p>Write a solution to calculate the <strong>percentage</strong> of the total time spent on <strong>sending</strong> and <strong>opening snaps</strong> for <strong>each age group</strong>. Precentage should be <strong>rounded</strong> to <code>2</code> decimal places.</p>

<p>Return <em>the result table </em><em>in <strong>any</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Activities table:
+-------------+---------+---------------+------------+
| activity_id | user_id | activity_type | time_spent |
+-------------+---------+---------------+------------+
| 7274        | 123     | open          | 4.50       | 
| 2425        | 123     | send          | 3.50       | 
| 1413        | 456     | send          | 5.67       | 
| 2536        | 456     | open          | 3.00       | 
| 8564        | 456     | send          | 8.24       | 
| 5235        | 789     | send          | 6.24       | 
| 4251        | 123     | open          | 1.25       | 
| 1435        | 789     | open          | 5.25       | 
+-------------+---------+---------------+------------+
Age table:
+---------+------------+
| user_id | age_bucket | 
+---------+------------+
| 123     | 31-35      | 
| 789     | 21-25      | 
| 456     | 26-30      | 
+---------+------------+
<strong>Output:</strong> 
+------------+-----------+-----------+
| age_bucket | send_perc | open_perc |
+------------+-----------+-----------+
| 31-35      | 37.84     | 62.16     |
| 26-30      | 82.26     | 17.74     |
| 21-25      | 54.31     | 45.69     |
+------------+-----------+-----------+
<strong>Explanation:</strong> 
For age group 31-35:
  - There is only one user belonging to this group with the user ID 123.
  - The total time spent on sending snaps by this user is 3.50, and the time spent on opening snaps is 4.50 + 1.25 = 5.75.
  - The overall time spent by this user is 3.50 + 5.75 = 9.25.
  - Therefore, the sending snap percentage will be (3.50 / 9.25) * 100 = 37.84, and the opening snap percentage will be (5.75 / 9.25) * 100 = 62.16.
For age group 26-30: 
  - There is only one user belonging to this group with the user ID 456. 
  - The total time spent on sending snaps by this user is 5.67 + 8.24 = 13.91, and the time spent on opening snaps is 3.00. 
  - The overall time spent by this user is 13.91 + 3.00 = 16.91. 
  - Therefore, the sending snap percentage will be (13.91 / 16.91) * 100 = 82.26, and the opening snap percentage will be (3.00 / 16.91) * 100 = 17.74.
For age group 21-25: 
  - There is only one user belonging to this group with the user ID 789. 
  - The total time spent on sending snaps by this user is 6.24, and the time spent on opening snaps is 5.25. 
  - The overall time spent by this user is 6.24 + 5.25 = 11.49. 
  - Therefore, the sending snap percentage will be (6.24 / 11.49) * 100 = 54.31, and the opening snap percentage will be (5.25 / 11.49) * 100 = 45.69.
All percentages in output table rounded to the two decimal places.
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql

```

<!-- tabs:end -->

<!-- end -->
