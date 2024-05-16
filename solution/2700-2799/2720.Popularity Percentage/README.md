---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2720.Popularity%20Percentage/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2720. 受欢迎度百分比 🔒](https://leetcode.cn/problems/popularity-percentage)

[English Version](/solution/2700-2799/2720.Popularity%20Percentage/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Friends</code></p>

<pre>
+-------------+------+
| 列名        | 类型  |
+-------------+------+
| user1       | int  |
| user2       | int  |
+-------------+------+
(user1, user2) 是该表的主键(具有唯一值的列)。 
每一行包含关于朋友关系的信息，其中 user1 和 user2 是朋友。 
</pre>

<p>编写一条 SQL 查询，找出 Meta/Facebook 平台上每个用户的受欢迎度的百分比。受欢迎度百分比定义为用户拥有的朋友总数除以平台上的总用户数，然后乘以 100，并&nbsp;<strong>四舍五入保留 2 位小数&nbsp;</strong>。</p>

<p>返回按照 <code>user1</code> <strong>升序</strong> 排序的结果表。</p>

<p>查询结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>&nbsp;
Friends 表:
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
<b>输出：</b>
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
<b>解释：</b>
平台上总共有 9 个用户。
- 用户 "1" 与 2、3、4、5 和 6 是朋友。因此，用户 1 的受欢迎度百分比计算为（5/9）* 100 = 55.56。
- 用户 "2" 与 1、6 和 7 是朋友。因此，用户 2 的受欢迎度百分比计算为（3/9）* 100 = 33.33。
- 用户 "3" 与 1、8 和 9 是朋友。因此，用户 3 的受欢迎度百分比计算为（3/9）* 100 = 33.33。
- 用户 "4" 与 1 是朋友。因此，用户 4 的受欢迎度百分比计算为（1/9）* 100 = 11.11。
- 用户 "5" 与 1 是朋友。因此，用户 5 的受欢迎度百分比计算为（1/9）* 100 = 11.11。
- 用户 "6" 与 1 和 2 是朋友。因此，用户 6 的受欢迎度百分比计算为（2/9）* 100 = 22.22。
- 用户 "7" 与 2 是朋友。因此，用户 7 的受欢迎度百分比计算为（1/9）* 100 = 11.11。
- 用户 "8" 与 3 是朋友。因此，用户 8 的受欢迎度百分比计算为（1/9）* 100 = 11.11。
- 用户 "9" 与 3 是朋友。因此，用户 9 的受欢迎度百分比计算为（1/9）* 100 = 11.11。 
user1 按升序排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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

<!-- solution:end -->

<!-- problem:end -->
