---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1264.Page%20Recommendations/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1264. 页面推荐 🔒](https://leetcode.cn/problems/page-recommendations)

[English Version](/solution/1200-1299/1264.Page%20Recommendations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>朋友关系列表：&nbsp;<code>Friendship</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user1_id      | int     |
| user2_id      | int     |
+---------------+---------+
(user1_id, user2_id) 是这张表具有唯一值的列的组合。
这张表的每一行代表着 user1_id 和 user2_id 之间存在着朋友关系。
</pre>

<p>&nbsp;</p>

<p>喜欢列表：&nbsp;<code>Likes</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| page_id     | int     |
+-------------+---------+
(user_id, page_id) 是这张表具有唯一值的列的组合。
这张表的每一行代表着 user_id 喜欢 page_id。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，向<code>user_id</code> = 1 的用户，推荐其朋友们喜欢的页面。不要推荐该用户已经喜欢的页面。</p>

<p>以 <strong>任意顺序</strong> 返回结果，其中不应当包含重复项。</p>

<p>返回结果的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Friendship table:
+----------+----------+
| user1_id | user2_id |
+----------+----------+
| 1        | 2        |
| 1        | 3        |
| 1        | 4        |
| 2        | 3        |
| 2        | 4        |
| 2        | 5        |
| 6        | 1        |
+----------+----------+
 
Likes table:
+---------+---------+
| user_id | page_id |
+---------+---------+
| 1       | 88      |
| 2       | 23      |
| 3       | 24      |
| 4       | 56      |
| 5       | 11      |
| 6       | 33      |
| 2       | 77      |
| 3       | 77      |
| 6       | 88      |
+---------+---------+

<strong>输出：</strong>
+------------------+
| recommended_page |
+------------------+
| 23               |
| 24               |
| 56               |
| 33               |
| 77               |
+------------------+
<strong>解释：</strong>
用户1 同 用户2, 3, 4, 6 是朋友关系。
推荐页面为： 页面23 来自于 用户2, 页面24 来自于 用户3, 页面56 来自于 用户3 以及 页面33 来自于 用户6。
页面77 同时被 用户2 和 用户3 推荐。
页面88 没有被推荐，因为 用户1 已经喜欢了它。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：合并 + 等值连接 + 子查询

<!-- thinking:start -->

> **思考**
>
> 推荐页是好友喜欢而自己未喜欢的页面。好友关系无向，先把与用户 $1$ 相连的两端并成好友表，再连接 $Likes$，最后用子查询排除用户 $1$ 已喜欢的 $page\_id$。

<!-- thinking:end -->

我们先查出所有与 `user_id = 1` 的用户是朋友的用户，记录在 `T` 表中，然后再查出所有在 `T` 表中的用户喜欢的页面，最后排除掉 `user_id = 1` 喜欢的页面即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT user1_id AS user_id FROM Friendship WHERE user2_id = 1
        UNION
        SELECT user2_id AS user_id FROM Friendship WHERE user1_id = 1
    )
SELECT DISTINCT page_id AS recommended_page
FROM
    T
    JOIN Likes USING (user_id)
WHERE page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一先物化好友表再连接。亦可把好友子查询直接写在 $Likes.user\_id$ 的 $IN$ 中，少一张 CTE，语义相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT DISTINCT page_id AS recommended_page
FROM Likes
WHERE
    user_id IN (
        SELECT user1_id AS user_id FROM Friendship WHERE user2_id = 1
        UNION ALL
        SELECT user2_id AS user_id FROM Friendship WHERE user1_id = 1
    )
    AND page_id NOT IN (SELECT page_id FROM Likes WHERE user_id = 1);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
