# [3058. Friends With No Mutual Friends](https://leetcode.com/problems/friends-with-no-mutual-friends)

[中文文档](/solution/3000-3099/3058.Friends%20With%20No%20Mutual%20Friends/README.md)

<!-- tags: -->

## Description

<p>Table: <code>Friends</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id1    | int  |
| user_id2    | int  |
+-------------+------+
(user_id1, user_id2) is the primary key (combination of columns with unique values) for this table.
Each row contains user id1, user id2, both of whom are friends with each other.
</pre>

<p>Write a solution to find <strong>all</strong> <strong>pairs</strong> of users who are friends with each other and have <strong>no mutual</strong> friends.</p>

<p>Return <em>the result table ordered by </em><code>user_id1,</code> <code>user_id2</code><em> in <strong>ascending</strong></em><em><strong> </strong>order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Friends table:
+----------+----------+
| user_id1 | user_id2 | 
+----------+----------+
| 1        | 2        | 
| 2        | 3        | 
| 2        | 4        | 
| 1        | 5        | 
| 6        | 7        | 
| 3        | 4        | 
| 2        | 5        | 
| 8        | 9        | 
+----------+----------+
<strong>Output:</strong> 
+----------+----------+
| user_id1 | user_id2 | 
+----------+----------+
| 6        | 7        | 
| 8        | 9        | 
+----------+----------+
<strong>Explanation:</strong> 
- Users 1 and 2 are friends with each other, but they share a mutual friend with user ID 5, so this pair is not included.
- Users 2 and 3 are friends, they both share a mutual friend with user ID 4, resulting in exclusion, similarly for users 2 and 4 who share a mutual friend with user ID 3, hence not included.
- Users 1 and 5 are friends with each other, but they share a mutual friend with user ID 2, so this pair is not included.
- Users 6 and 7, as well as users 8 and 9, are friends with each other, and they don&#39;t have any mutual friends, hence included.
- Users 3 and 4 are friends with each other, but their mutual connection with user ID 2 means they are not included, similarly for users 2 and 5 are friends but are excluded due to their mutual connection with user ID 1.
Output table is ordered by user_id1 in ascending order.</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql

```

<!-- tabs:end -->

<!-- end -->
