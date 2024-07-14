---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2230.The%20Users%20That%20Are%20Eligible%20for%20Discount/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2230. 查找可享受优惠的用户 🔒](https://leetcode.cn/problems/the-users-that-are-eligible-for-discount)

[English Version](/solution/2200-2299/2230.The%20Users%20That%20Are%20Eligible%20for%20Discount/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>Purchases</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| user_id     | int      |
| time_stamp  | datetime |
| amount      | int      |
+-------------+----------+
(user_id, time_stamp)是此表的主键（不同值的列的组合）。
每一行都包含有关购买时间和用户 ID user_id 以及购买的数量的信息。
</pre>

<p>如果用户在包含时间间隔 <code>[startDate，endDate]</code> 内购买了至少&nbsp;<code>minAmount</code>&nbsp;数量的商品，则有资格享受折扣。要将日期转换为时间，两个日期都应视为一天的 <strong>开始</strong>（例如，<code>endDate = 2022-03-05</code>&nbsp;应该被认为是 <code>2022-03-05 00:00:00</code>）。</p>

<p>编写一个解决方案来查询符合折扣条件的用户的 ID。</p>

<p>返回结果表，以&nbsp;<code>user_id</code>&nbsp;排序。</p>

<p>查询结果格式如下例所示。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Purchases 表：
+---------+---------------------+--------+
| user_id | time_stamp          | amount |
+---------+---------------------+--------+
| 1       | 2022-04-20 09:03:00 | 4416   |
| 2       | 2022-03-19 19:24:02 | 678    |
| 3       | 2022-03-18 12:03:09 | 4523   |
| 3       | 2022-03-30 09:43:42 | 626    |
+---------+---------------------+--------+
startDate = 2022-03-08, endDate = 2022-03-20, minAmount = 1000
<strong>输出：</strong>
+---------+
| user_id |
+---------+
| 3       |
+---------+
<strong>解释：</strong>
在三个用户中，只有用户 3 有资格享受折扣。
- 用户 1 有一次至少购买了 minAmount 的数量，但不在时间间隔内。
- 用户 2 在时间间隔内有一次购买，但少于 minAmount 数量。
- 用户 3 是唯一满足这两个条件的用户。
</pre>

<strong>重要提示：</strong>这个问题基本上与 <a href="https://leetcode.cn/problems/the-number-of-users-that-are-eligible-for-discount/description/">有资格享受折扣的用户数量</a> 相同。

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
CREATE PROCEDURE getUserIDs(startDate DATE, endDate DATE, minAmount INT)
BEGIN
    # Write your MySQL query statement below.
    SELECT DISTINCT user_id
    FROM Purchases
    WHERE amount >= minAmount AND time_stamp BETWEEN startDate AND endDate
    ORDER BY user_id;
END;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
