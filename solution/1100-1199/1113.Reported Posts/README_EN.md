---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [1113. Reported Posts 🔒](https://leetcode.com/problems/reported-posts)

[中文文档](/solution/1100-1199/1113.Reported%20Posts/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Actions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| post_id       | int     |
| action_date   | date    | 
| action        | enum    |
| extra         | varchar |
+---------------+---------+
This table may have duplicate rows.
The action column is an ENUM (category) type of (&#39;view&#39;, &#39;like&#39;, &#39;reaction&#39;, &#39;comment&#39;, &#39;report&#39;, &#39;share&#39;).
The extra column has optional information about the action, such as a reason for the report or a type of reaction.</pre>

<p>&nbsp;</p>

<p>Write a solution to report&nbsp;the number of posts reported yesterday for each report reason. Assume today is <code>2019-07-05</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Actions table:
+---------+---------+-------------+--------+--------+
| user_id | post_id | action_date | action | extra  |
+---------+---------+-------------+--------+--------+
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 4       | 2019-07-04  | view   | null   |
| 2       | 4       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-04  | view   | null   |
| 5       | 2       | 2019-07-04  | report | racism |
| 5       | 5       | 2019-07-04  | view   | null   |
| 5       | 5       | 2019-07-04  | report | racism |
+---------+---------+-------------+--------+--------+
<strong>Output:</strong> 
+---------------+--------------+
| report_reason | report_count |
+---------------+--------------+
| spam          | 1            |
| racism        | 2            |
+---------------+--------------+
<strong>Explanation:</strong> Note that we only care about report reasons with non-zero number of reports.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Count distinct posts per `extra` (report reason) on the given date with `action = 'report'`. Filter date and action first, then `GROUP BY extra` and `COUNT(DISTINCT post_id)` so a post reported twice is counted once.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT extra AS report_reason, COUNT(DISTINCT post_id) AS report_count
FROM Actions
WHERE action_date = '2019-07-04' AND action = 'report'
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
