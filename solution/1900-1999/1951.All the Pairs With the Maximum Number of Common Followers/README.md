# [1951. All the Pairs With the Maximum Number of Common Followers](https://leetcode-cn.com/problems/all-the-pairs-with-the-maximum-number-of-common-followers)

[English Version](/solution/1900-1999/1951.All%20the%20Pairs%20With%20the%20Maximum%20Number%20of%20Common%20Followers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Relations</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| follower_id | int  |
+-------------+------+
(user_id, follower_id) is the primary key for this table.
Each row of this table indicates that the user with ID follower_id is following the user with ID user_id.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all the pairs of users with the maximum number of common followers. In other words, if the maximum number of common followers between any two users is <code>maxCommon</code>, then you have to return any pair of users that have <code>maxCommon</code> common followers.</p>

<p>The result table should contain the pairs <code>user1_id</code> and <code>user2_id</code> where <code>user1_id &lt; user2_id</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example:</p>

<p>&nbsp;</p>

<pre>
Relations table:
+---------+-------------+
| user_id | follower_id |
+---------+-------------+
| 1       | 3           |
| 2       | 3           |
| 7       | 3           |
| 1       | 4           |
| 2       | 4           |
| 7       | 4           |
| 1       | 5           |
| 2       | 6           |
| 7       | 5           |
+---------+-------------+

Result table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 7        |
+----------+----------+

Users 1 and 2 have 2 common followers (3 and 4).
Users 1 and 7 have 3 common followers (3, 4, and 5).
Users 2 and 7 have 2 common followers (3 and 4).
Since the maximum number of common followers between any two users is 3, we return any pair of friends with 3 common followers which is only the pair (1, 7) here. We return the pair as [1, 7] not as [7, 1].
Note that we do not have any information about the users that follow users 3, 4, and 5, so we consider them with 0 followers.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
