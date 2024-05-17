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

<pre>+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| user_id     | int      |
| time_stamp  | datetime |
| amount      | int      |
+-------------+----------+
(user_id，time _ stamp)是此表的主键。
每一行都包含有关购买时间和用户user _ ID 以及购买的数量的信息。
</pre>

<p>如果用户在包含时间间隔[ startDate，endDate ]内购买了至少最少数量的商品，则有资格享受折扣。</p>

<p>编写一个 SQL 查询来报告符合折扣条件的用户的 id。</p>

<p>返回符合条件的用户 ，按照id 排序的结果表。</p>

<p>查询结果格式如下例所示。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 
Purchases table:
+---------+---------------------+--------+
| user_id | time_stamp          | amount |
+---------+---------------------+--------+
| 1       | 2022-04-20 09:03:00 | 4416   |
| 2       | 2022-03-19 19:24:02 | 678    |
| 3       | 2022-03-18 12:03:09 | 4523   |
| 3       | 2022-03-30 09:43:42 | 626    |
+---------+---------------------+--------+
startDate = 2022-03-08, endDate = 2022-03-20, minAmount = 1000
<strong>输出:</strong> 
+---------+
| user_id |
+---------+
| 3       |
+---------+
说明:
在三个用户中，只有用户3有资格享受折扣。
- 用户1有一次至少购买了 minAmount的数量 ，但不是在时间间隔内。
- 用户2在时间间隔内有一次购买，但少于 minAmount 数量。
- 用户3是唯一满足这两个条件的用户。

重要提示: 这个问题基本上与有资格享受折扣的用户数量相同。
</pre>

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
