# [614. Second Degree Follower](https://leetcode.com/problems/second-degree-follower)

[中文文档](/solution/0600-0699/0614.Second%20Degree%20Follower/README.md)

## Description

<p>Table: <code>Follow</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| followee    | varchar |
| follower    | varchar |
+-------------+---------+
(followee, follower) is the primary key column for this table.
Each row of this table indicates that the user follower follows the user followee on a social network.
There will not be a user following themself.
</pre>

<p>&nbsp;</p>

<p>A <strong>second-degree follower</strong> is a user who:</p>

<ul>
	<li>follows at least one user, and</li>
	<li>is followed by at least one user.</li>
</ul>

<p>Write an SQL query to report the <strong>second-degree users</strong> and the number of their followers.</p>

<p>Return the result table <strong>ordered</strong> by <code>follower</code> <strong>in alphabetical order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Follow table:
+----------+----------+
| followee | follower |
+----------+----------+
| Alice    | Bob      |
| Bob      | Cena     |
| Bob      | Donald   |
| Donald   | Edward   |
+----------+----------+
<strong>Output:</strong> 
+----------+-----+
| follower | num |
+----------+-----+
| Bob      | 2   |
| Donald   | 1   |
+----------+-----+
<strong>Explanation:</strong> 
User Bob has 2 followers. Bob is a second-degree follower because he follows Alice, so we include him in the result table.
User Donald has 1 follower. Donald is a second-degree follower because he follows Bob, so we include him in the result table.
User Alice has 1 follower. Alice is not a second-degree follower because she does not follow anyone, so we don not include her in the result table.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
