# [2720. Popularity Percentage](https://leetcode.com/problems/popularity-percentage)

[中文文档](/solution/2700-2799/2720.Popularity%20Percentage/README.md)

## Description

<p>Table: <code>Friends</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user1       | int  |
| user2       | int  |
+-------------+------+
(user1, user2) is the primary key (combination of unique values) of this table.
Each row contains information about friendship where user1 and user2 are friends.
</pre>

<p>Write a solution to find the popularity percentage for each user on Meta/Facebook. The popularity percentage is defined as the total number of friends the user has divided by the total number of users on the platform, then converted into a percentage by multiplying by 100, <strong>rounded to 2 decimal places</strong>.</p>

<p>Return <em>the result table ordered by</em> <code>user1</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>&nbsp;
Friends table:
+-------+-------+
| user1 | user2 | 
+-------+-------+
| 2 &nbsp; &nbsp; | 1 &nbsp; &nbsp; | 
| 1 &nbsp; &nbsp; | 3 &nbsp; &nbsp; | 
| 4 &nbsp; &nbsp; | 1 &nbsp; &nbsp; | 
| 1 &nbsp; &nbsp; | 5 &nbsp; &nbsp; | 
| 1 &nbsp; &nbsp; | 6 &nbsp; &nbsp; |
| 2 &nbsp; &nbsp; | 6 &nbsp; &nbsp; | 
| 7 &nbsp; &nbsp; | 2 &nbsp; &nbsp; | 
| 8 &nbsp; &nbsp; | 3&nbsp; &nbsp; &nbsp;| 
| 3 &nbsp; &nbsp; | 9 &nbsp; &nbsp; |  
+-------+-------+
<strong>Output:</strong>&nbsp;
+-------+-----------------------+
| user1 | percentage_popularity |
+-------+-----------------------+
| 1     | 55.56 &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 2     | 33.33 &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|
| 3     | 33.33   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 4     | 11.11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 5     | 11.11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 6     | 22.22 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 7     | 11.11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 8     | 11.11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
| 9     | 11.11 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |
+-------+-----------------------+
<strong>Explanation:</strong>&nbsp;
There are total 9 users on the platform.
- User &quot;1&quot; has friendships with 2, 3, 4, 5 and 6. Therefore, the percentage popularity for user 1 would be calculated as (5/9) * 100 = 55.56.
- User &quot;2&quot; has friendships with 1, 6 and 7. Therefore, the percentage popularity for user 2 would be calculated as (3/9) * 100 = 33.33.
- User &quot;3&quot; has friendships with 1, 8 and 9. Therefore, the percentage popularity for user 3 would be calculated as (3/9) * 100 = 33.33.
- User &quot;4&quot; has friendships with 1. Therefore, the percentage popularity for user 4 would be calculated as (1/9) * 100 = 11.11.
- User &quot;5&quot; has friendships with 1. Therefore, the percentage popularity for user 5 would be calculated as (1/9) * 100 = 11.11.
- User &quot;6&quot; has friendships with 1 and 2. Therefore, the percentage popularity for user 6 would be calculated as (2/9) * 100 = 22.22.
- User &quot;7&quot; has friendships with 2. Therefore, the percentage popularity for user 7 would be calculated as (1/9) * 100 = 11.11.
- User &quot;8&quot; has friendships with 3. Therefore, the percentage popularity for user 8 would be calculated as (1/9) * 100 = 11.11.
- User &quot;9&quot; has friendships with 3. Therefore, the percentage popularity for user 9 would be calculated as (1/9) * 100 = 11.11.
user1 is sorted in ascending order.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    F AS (
        SELECT * FROM Friends
        UNION
        SELECT user2, user1 FROM Friends
    ),
    T AS (SELECT COUNT(DISTINCT user1) AS cnt FROM F)
SELECT DISTINCT
    user1,
    ROUND(
        (COUNT(1) OVER (PARTITION BY user1)) * 100 / (SELECT cnt FROM T),
        2
    ) AS percentage_popularity
FROM F
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
