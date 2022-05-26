# [1811. Find Interview Candidates](https://leetcode.com/problems/find-interview-candidates)

[中文文档](/solution/1800-1899/1811.Find%20Interview%20Candidates/README.md)

## Description

<p>Table: <code>Contests</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| contest_id   | int  |
| gold_medal   | int  |
| silver_medal | int  |
| bronze_medal | int  |
+--------------+------+
contest_id is the primary key for this table.
This table contains the LeetCode contest ID and the user IDs of the gold, silver, and bronze medalists.
It is guaranteed that any consecutive contests have consecutive IDs and that no ID is skipped.</pre>

<p>&nbsp;</p>

<p>Table: <code>Users</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| mail        | varchar |
| name        | varchar |
+-------------+---------+
user_id is the primary key for this table.
This table contains information about the users.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the <code>name</code> and the <code>mail</code> of all <strong>interview candidates</strong>. A user is an <strong>interview candidate</strong> if <strong>at least one</strong> of these two conditions is true:</p>

<ul>
	<li>The user won <strong>any</strong> medal in <strong>three or more consecutive</strong> contests.</li>
	<li>The user won the <strong>gold</strong> medal in <strong>three or more different</strong> contests (not necessarily consecutive).</li>
</ul>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Contests table:
+------------+------------+--------------+--------------+
| contest_id | gold_medal | silver_medal | bronze_medal |
+------------+------------+--------------+--------------+
| 190        | 1          | 5            | 2            |
| 191        | 2          | 3            | 5            |
| 192        | 5          | 2            | 3            |
| 193        | 1          | 3            | 5            |
| 194        | 4          | 5            | 2            |
| 195        | 4          | 2            | 1            |
| 196        | 1          | 5            | 2            |
+------------+------------+--------------+--------------+
Users table:
+---------+--------------------+-------+
| user_id | mail               | name  |
+---------+--------------------+-------+
| 1       | sarah@leetcode.com | Sarah |
| 2       | bob@leetcode.com   | Bob   |
| 3       | alice@leetcode.com | Alice |
| 4       | hercy@leetcode.com | Hercy |
| 5       | quarz@leetcode.com | Quarz |
+---------+--------------------+-------+
<strong>Output:</strong> 
+-------+--------------------+
| name  | mail               |
+-------+--------------------+
| Sarah | sarah@leetcode.com |
| Bob   | bob@leetcode.com   |
| Alice | alice@leetcode.com |
| Quarz | quarz@leetcode.com |
+-------+--------------------+
<strong>Explanation:</strong> 
Sarah won 3 gold medals (190, 193, and 196), so we include her in the result table.
Bob won a medal in 3 consecutive contests (190, 191, and 192), so we include him in the result table.
    - Note that he also won a medal in 3 other consecutive contests (194, 195, and 196).
Alice won a medal in 3 consecutive contests (191, 192, and 193), so we include her in the result table.
Quarz won a medal in 5 consecutive contests (190, 191, 192, 193, and 194), so we include them in the result table.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>What if the first condition changed to be &quot;any medal in <code>n</code><strong> or more</strong> consecutive contests&quot;? How would you change your solution to get the interview candidates? Imagine that <code>n</code> is the parameter of a stored procedure.</li>
	<li>Some users may not participate in every contest but still perform well in the ones they do. How would you change your solution to only consider contests where the user <strong>was a participant</strong>? Suppose the registered users for each contest are given in another table.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
