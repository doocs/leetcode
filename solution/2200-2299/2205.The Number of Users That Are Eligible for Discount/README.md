# [2205. The Number of Users That Are Eligible for Discount](https://leetcode.cn/problems/the-number-of-users-that-are-eligible-for-discount)

[English Version](/solution/2200-2299/2205.The%20Number%20of%20Users%20That%20Are%20Eligible%20for%20Discount/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Purchases</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| user_id     | int      |
| time_stamp  | datetime |
| amount      | int      |
+-------------+----------+
(user_id, time_stamp) is the primary key for this table.
Each row contains information about the purchase time and the amount paid for the user with ID user_id.
</pre>

<p>&nbsp;</p>

<p>A user is eligible for a discount if they had a purchase in the inclusive interval of time <code>[startDate, endDate]</code> with at least <code>minAmount</code> amount. To convert the dates to times, both dates should be considered as the <strong>start</strong> of the day (i.e., <code>endDate = 2022-03-05</code> should be considered as the time <code>2022-03-05 00:00:00</code>).</p>

<p>Write an SQL query to report the number of users that are eligible for a discount.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
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
<strong>Output:</strong> 
+----------+
| user_cnt |
+----------+
| 1        |
+----------+
<strong>Explanation:</strong>
Out of the three users, only User 3 is eligible for a discount.
 - User 1 had one purchase with at least minAmount amount, but not within the time interval.
 - User 2 had one purchase within the time interval, but with less than minAmount amount.
 - User 3 is the only user who had a purchase that satisfies both conditions.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
