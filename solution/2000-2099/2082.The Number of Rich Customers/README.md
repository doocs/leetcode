# [2082. 富有客户的数量](https://leetcode.cn/problems/the-number-of-rich-customers)

[English Version](/solution/2000-2099/2082.The%20Number%20of%20Rich%20Customers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表： <code>Store</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| bill_id     | int  |
| customer_id | int  |
| amount      | int  |
+-------------+------+
bill_id 是这个表的主键。
每一行包含一个订单的金额及相关客户的信息。
</pre>

<p>&nbsp;</p>

<p>写一条 SQL 语句，查询<strong>至少有一个</strong>订单的金额<strong>严格大于</strong> <code>500</code> 的客户的数量。</p>

<p>查询结果格式如下示例所示：</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入：</strong>
Store 表:
+---------+-------------+--------+
| bill_id | customer_id | amount |
+---------+-------------+--------+
| 6       | 1           | 549    |
| 8       | 1           | 834    |
| 4       | 2           | 394    |
| 11      | 3           | 657    |
| 13      | 3           | 257    |
+---------+-------------+--------+
<strong>输出：</strong> 
+------------+
| rich_count |
+------------+
| 2          |
+------------+
<strong>解释：</strong>
客户 1 有 2 个订单金额严格大于 500。
客户 2 没有任何订单金额严格大于 500。
客户 3 有 1 个订单金额严格大于 500。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT
    COUNT(DISTINCT(customer_id)) AS rich_count
FROM
    Store
WHERE
    amount > 500;
```

<!-- tabs:end -->
