# [2205. 有资格享受折扣的用户数量 🔒](https://leetcode.cn/problems/the-number-of-users-that-are-eligible-for-discount)

[English Version](/solution/2200-2299/2205.The%20Number%20of%20Users%20That%20Are%20Eligible%20for%20Discount/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Purchases</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| user_id     | int      |
| time_stamp  | datetime |
| amount      | int      |
+-------------+----------+
(user_id, time_stamp) 是该表的主键。
每一行都包含 ID 为 user_id 的用户的购买时间和支付金额的信息。
</pre>

<p>&nbsp;</p>

<p>如果用户在时间间隔 <code>[startDate, endDate]</code> 内购买了至少 <code>minAmount</code> 金额的商品，则有资格获得折扣。若要将日期转换为时间，两个日期都应该被视为一天的&nbsp;<strong>开始</strong> (即 <code>endDate = 2022-03-05</code>&nbsp;应该被视为时间 <code>2022-03-05 00:00:00</code>)。</p>

<p>编写一个 SQL 来查询有资格享受折扣的用户数量。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Purchases 表:
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
+----------+
| user_cnt |
+----------+
| 1        |
+----------+
<strong>解释:</strong>
在这三个用户中，只有用户 3 有资格享受折扣。
 - 用户 1 有一次购买的金额大于 minAmount，但不在时间间隔内。
 - 用户 2 在时间间隔内有一次购买，但金额小于 minAmount。
 - 用户 3 是唯一一个购买行为同时满足这两个条件的用户。</pre>

## 解法

### 方法一：使用 count(distinct) 函数

注意需要判断的是单次购买金额是否大于等于 `minAmount`，而不是累计购买金额是否大于等于 `minAmount`。

<!-- tabs:start -->

```sql
CREATE FUNCTION getUserIDs(startDate DATE, endDate DATE, minAmount INT) RETURNS INT
BEGIN
  RETURN (
      SELECT COUNT(DISTINCT user_id) AS user_cnt
      FROM Purchases
      WHERE time_stamp BETWEEN startDate AND endDate AND amount >= minAmount
  );
END
```

<!-- tabs:end -->

<!-- end -->
