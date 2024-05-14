# [2228. 7 天内两次购买的用户 🔒](https://leetcode.cn/problems/users-with-two-purchases-within-seven-days)

[English Version](/solution/2200-2299/2228.Users%20With%20Two%20Purchases%20Within%20Seven%20Days/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| purchase_id   | int  |
| user_id       | int  |
| purchase_date | date |
+---------------+------+
purchase_id 包含唯一值。
该表包含用户从某个零售商购买的日期的日志。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，获取&nbsp;<strong>最多&nbsp;</strong>间隔 <code>7</code> 天进行两次购买的用户的 id。</p>

<p data-group="1-1">返回<em>按 <code>user_id</code>&nbsp;排序的结果表。</em></p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Purchases 表:
+-------------+---------+---------------+
| purchase_id | user_id | purchase_date |
+-------------+---------+---------------+
| 4           | 2       | 2022-03-13    |
| 1           | 5       | 2022-02-11    |
| 3           | 7       | 2022-06-19    |
| 6           | 2       | 2022-03-20    |
| 5           | 7       | 2022-06-19    |
| 2           | 2       | 2022-06-08    |
+-------------+---------+---------------+
<strong>输出:</strong> 
+---------+
| user_id |
+---------+
| 2       |
| 7       |
+---------+
<strong>解释:</strong> 
用户 2 在 2022-03-13 和 2022-03-20 有两次购买。由于第二次购买是在第一次购买后的 7 天内，我们添加了他们的 ID。
用户 5 只购买了 1 次。
用户 7 在同一天有两次购买，所以我们添加了他们的 ID。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            user_id,
            DATEDIFF(
                purchase_date,
                LAG(purchase_date, 1) OVER (
                    PARTITION BY user_id
                    ORDER BY purchase_date
                )
            ) AS d
        FROM Purchases
    )
SELECT DISTINCT user_id
FROM t
WHERE d <= 7
ORDER BY user_id;
```

<!-- tabs:end -->

<!-- end -->
