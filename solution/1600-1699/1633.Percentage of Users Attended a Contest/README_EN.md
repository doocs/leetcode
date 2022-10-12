# [1633. Percentage of Users Attended a Contest](https://leetcode.com/problems/percentage-of-users-attended-a-contest)

[中文文档](/solution/1600-1699/1633.Percentage%20of%20Users%20Attended%20a%20Contest/README.md)

## Description

<p>Table: <code>Users</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| user_name   | varchar |
+-------------+---------+
user_id is the primary key for this table.
Each row of this table contains the name and the id of a user.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Register</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| contest_id  | int     |
| user_id     | int     |
+-------------+---------+
(contest_id, user_id) is the primary key for this table.
Each row of this table contains the id of a user and the contest they registered into.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the percentage of the users registered in each contest rounded to <strong>two decimals</strong>.</p>

<p>Return the result table ordered by <code>percentage</code> in <strong>descending order</strong>. In case of a tie, order it by <code>contest_id</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+---------+-----------+
| user_id | user_name |
+---------+-----------+
| 6       | Alice     |
| 2       | Bob       |
| 7       | Alex      |
+---------+-----------+
Register table:
+------------+---------+
| contest_id | user_id |
+------------+---------+
| 215        | 6       |
| 209        | 2       |
| 208        | 2       |
| 210        | 6       |
| 208        | 6       |
| 209        | 7       |
| 209        | 6       |
| 215        | 7       |
| 208        | 7       |
| 210        | 2       |
| 207        | 2       |
| 210        | 7       |
+------------+---------+
<strong>Output:</strong> 
+------------+------------+
| contest_id | percentage |
+------------+------------+
| 208        | 100.0      |
| 209        | 100.0      |
| 210        | 100.0      |
| 215        | 66.67      |
| 207        | 33.33      |
+------------+------------+
<strong>Explanation:</strong> 
All the users registered in contests 208, 209, and 210. The percentage is 100% and we sort them in the answer table by contest_id in ascending order.
Alice and Alex registered in contest 215 and the percentage is ((2/3) * 100) = 66.67%
Bob registered in contest 207 and the percentage is ((1/3) * 100) = 33.33%
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
