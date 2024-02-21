# [1729. 求关注者的数量](https://leetcode.cn/problems/find-followers-count)

[English Version](/solution/1700-1799/1729.Find%20Followers%20Count/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Followers</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| follower_id | int  |
+-------------+------+
(user_id, follower_id) 是这个表的主键（具有唯一值的列的组合）。
该表包含一个关注关系中关注者和用户的编号，其中关注者关注用户。</pre>

<p>&nbsp;</p>

<p>编写解决方案，对于每一个用户，返回该用户的关注者数量。</p>

<p>按&nbsp;<code>user_id</code>&nbsp;的顺序返回结果表。</p>

<p>查询结果的格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Followers 表：
+---------+-------------+
| user_id | follower_id |
+---------+-------------+
| 0       | 1           |
| 1       | 0           |
| 2       | 0           |
| 2       | 1           |
+---------+-------------+
<strong>输出：</strong>
+---------+----------------+
| user_id | followers_count|
+---------+----------------+
| 0       | 1              |
| 1       | 1              |
| 2       | 2              |
+---------+----------------+
<strong>解释：</strong>
0 的关注者有 {1}
1 的关注者有 {0}
2 的关注者有 {0,1}</pre>

## 解法

### 方法一：分组统计

我们可以直接对 `Followers` 表按照 `user_id` 进行分组，然后使用 `COUNT` 函数统计每个用户的关注者数量即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT user_id, COUNT(1) AS followers_count
FROM Followers
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
