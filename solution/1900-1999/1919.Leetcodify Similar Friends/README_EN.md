# [1919. Leetcodify Similar Friends](https://leetcode.com/problems/leetcodify-similar-friends)

[中文文档](/solution/1900-1999/1919.Leetcodify%20Similar%20Friends/README.md)

## Description

<p>Table: <code>Listens</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| song_id     | int     |
| day         | date    |
+-------------+---------+
There is no primary key for this table. It may contain duplicates.
Each row of this table indicates that the user user_id listened to the song song_id on the day day.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
(user1_id, user2_id) is the primary key for this table.
Each row of this table indicates that the users user1_id and user2_id are friends.
Note that user1_id &lt; user2_id.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the similar friends of Leetcodify users. A user <code>x</code> and user <code>y</code> are&nbsp;similar friends if:</p>

<ul>
	<li>Users <code>x</code> and <code>y</code> are friends, and</li>
	<li>Users <code>x</code> and <code>y</code> listened to the same three or more different songs <strong>on the same day</strong>.</li>
</ul>

<p>Return the result table in <strong>any order</strong>. Note that you must return the similar pairs of friends the same way they were represented in the input (i.e., always <code>user1_id &lt; user2_id</code>).</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Listens table:
+---------+---------+------------+
| user_id | song_id | day        |
+---------+---------+------------+
| 1       | 10      | 2021-03-15 |
| 1       | 11      | 2021-03-15 |
| 1       | 12      | 2021-03-15 |
| 2       | 10      | 2021-03-15 |
| 2       | 11      | 2021-03-15 |
| 2       | 12      | 2021-03-15 |
| 3       | 10      | 2021-03-15 |
| 3       | 11      | 2021-03-15 |
| 3       | 12      | 2021-03-15 |
| 4       | 10      | 2021-03-15 |
| 4       | 11      | 2021-03-15 |
| 4       | 13      | 2021-03-15 |
| 5       | 10      | 2021-03-16 |
| 5       | 11      | 2021-03-16 |
| 5       | 12      | 2021-03-16 |
+---------+---------+------------+
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 2        | 4        |
| 2        | 5        |
+----------+----------+
<strong>Output:</strong> 
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
+----------+----------+
<strong>Explanation:</strong> 
Users 1 and 2 are friends, and they listened to songs 10, 11, and 12 on the same day. They are similar friends.
Users 1 and 3 listened to songs 10, 11, and 12 on the same day, but they are not friends.
Users 2 and 4 are friends, but they did not listen to the same three different songs.
Users 2 and 5 are friends and listened to songs 10, 11, and 12, but they did not listen to them on the same day.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
