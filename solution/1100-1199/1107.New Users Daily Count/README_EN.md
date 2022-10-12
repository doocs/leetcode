# [1107. New Users Daily Count](https://leetcode.com/problems/new-users-daily-count)

[中文文档](/solution/1100-1199/1107.New%20Users%20Daily%20Count/README.md)

## Description

<p>Table: <code>Traffic</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| activity      | enum    |
| activity_date | date    |
+---------------+---------+
There is no primary key for this table, it may have duplicate rows.
The activity column is an ENUM type of (&#39;login&#39;, &#39;logout&#39;, &#39;jobs&#39;, &#39;groups&#39;, &#39;homepage&#39;).
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to reports for every date within at most <code>90</code> days from today, the number of users that logged in for the first time on that date. Assume today is <code>2019-06-30</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Traffic table:
+---------+----------+---------------+
| user_id | activity | activity_date |
+---------+----------+---------------+
| 1       | login    | 2019-05-01    |
| 1       | homepage | 2019-05-01    |
| 1       | logout   | 2019-05-01    |
| 2       | login    | 2019-06-21    |
| 2       | logout   | 2019-06-21    |
| 3       | login    | 2019-01-01    |
| 3       | jobs     | 2019-01-01    |
| 3       | logout   | 2019-01-01    |
| 4       | login    | 2019-06-21    |
| 4       | groups   | 2019-06-21    |
| 4       | logout   | 2019-06-21    |
| 5       | login    | 2019-03-01    |
| 5       | logout   | 2019-03-01    |
| 5       | login    | 2019-06-21    |
| 5       | logout   | 2019-06-21    |
+---------+----------+---------------+
<strong>Output:</strong> 
+------------+-------------+
| login_date | user_count  |
+------------+-------------+
| 2019-05-01 | 1           |
| 2019-06-21 | 2           |
+------------+-------------+
<strong>Explanation:</strong> 
Note that we only care about dates with non zero user count.
The user with id 5 first logged in on 2019-03-01 so he&#39;s not counted on 2019-06-21.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
