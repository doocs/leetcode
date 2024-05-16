---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1132.Reported%20Posts%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1132. 报告的记录 II 🔒](https://leetcode.cn/problems/reported-posts-ii)

[English Version](/solution/1100-1199/1132.Reported%20Posts%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>动作表：&nbsp;<code>Actions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| post_id       | int     |
| action_date   | date    |
| action        | enum    |
| extra         | varchar |
+---------------+---------+
这张表可能存在重复的行。
action 列的类型是 ENUM，可能的值为 ('view', 'like', 'reaction', 'comment', 'report', 'share')。
extra 列拥有一些可选信息，例如：报告理由（a reason for report）或反应类型（a type of reaction）等。</pre>

<p>&nbsp;</p>

<p>移除表：&nbsp;<code>Removals</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| post_id       | int     |
| remove_date   | date    | 
+---------------+---------+
这张表的主键是 post_id（具有唯一值的列）。
这张表的每一行表示一个被移除的帖子，原因可能是由于被举报或被管理员审查。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，统计在被报告为垃圾广告的帖子中，被移除的帖子的每日平均占比，<strong>四舍五入到小数点后 2 位</strong>。</p>

<p>结果的格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Actions table:
+---------+---------+-------------+--------+--------+
| user_id | post_id | action_date | action | extra  |
+---------+---------+-------------+--------+--------+
| 1       | 1       | 2019-07-01  | view   | null   |
| 1       | 1       | 2019-07-01  | like   | null   |
| 1       | 1       | 2019-07-01  | share  | null   |
| 2       | 2       | 2019-07-04  | view   | null   |
| 2       | 2       | 2019-07-04  | report | spam   |
| 3       | 4       | 2019-07-04  | view   | null   |
| 3       | 4       | 2019-07-04  | report | spam   |
| 4       | 3       | 2019-07-02  | view   | null   |
| 4       | 3       | 2019-07-02  | report | spam   |
| 5       | 2       | 2019-07-03  | view   | null   |
| 5       | 2       | 2019-07-03  | report | racism |
| 5       | 5       | 2019-07-03  | view   | null   |
| 5       | 5       | 2019-07-03  | report | racism |
+---------+---------+-------------+--------+--------+
Removals table:
+---------+-------------+
| post_id | remove_date |
+---------+-------------+
| 2       | 2019-07-20  |
| 3       | 2019-07-18  |
+---------+-------------+
<strong>输出：</strong>
+-----------------------+
| average_daily_percent |
+-----------------------+
| 75.00                 |
+-----------------------+
<strong>解释：</strong>
2019-07-04 的垃圾广告移除率是 50%，因为有两张帖子被报告为垃圾广告，但只有一个得到移除。
2019-07-02 的垃圾广告移除率是 100%，因为有一张帖子被举报为垃圾广告并得到移除。
其余几天没有收到垃圾广告的举报，因此平均值为：(50 + 100) / 2 = 75%
注意，输出仅需要一个平均值即可，我们并不关注移除操作的日期。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            COUNT(DISTINCT t2.post_id) / COUNT(DISTINCT t1.post_id) * 100 AS percent
        FROM
            Actions AS t1
            LEFT JOIN Removals AS t2 ON t1.post_id = t2.post_id
        WHERE extra = 'spam'
        GROUP BY action_date
    )
SELECT ROUND(AVG(percent), 2) AS average_daily_percent
FROM T;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
