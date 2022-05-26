# [1949. Strong Friendship](https://leetcode.com/problems/strong-friendship)

[中文文档](/solution/1900-1999/1949.Strong%20Friendship/README.md)

## Description

<p>Table: <code>Friendship</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user1_id    | int  |
| user2_id    | int  |
+-------------+------+
(user1_id, user2_id) is the primary key for this table.
Each row of this table indicates that the users user1_id and user2_id are friends.
Note that user1_id &lt; user2_id.
</pre>

<p>&nbsp;</p>

<p>A friendship between a pair of friends <code>x</code> and <code>y</code> is <strong>strong</strong> if <code>x</code> and <code>y</code> have <strong>at least three</strong> common friends.</p>

<p>Write an SQL query to find all the <strong>strong friendships</strong>.</p>

<p>Note that the result table should not contain duplicates with <code>user1_id &lt; user2_id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 1        | 3        |
| 2        | 3        |
| 1        | 4        |
| 2        | 4        |
| 1        | 5        |
| 2        | 5        |
| 1        | 7        |
| 3        | 7        |
| 1        | 6        |
| 3        | 6        |
| 2        | 6        |
+----------+----------+
<strong>Output:</strong> 
+----------+----------+---------------+
| user1_id | user2_id | common_friend |
+----------+----------+---------------+
| 1        | 2        | 4             |
| 1        | 3        | 3             |
+----------+----------+---------------+
<strong>Explanation:</strong> 
Users 1 and 2 have 4 common friends (3, 4, 5, and 6).
Users 1 and 3 have 3 common friends (2, 6, and 7).
We did not include the friendship of users 2 and 3 because they only have two common friends (1 and 6).
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
