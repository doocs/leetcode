# [1613. 找到遗失的 ID](https://leetcode-cn.com/problems/find-the-missing-ids)

[English Version](/solution/1600-1699/1613.Find%20the%20Missing%20IDs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id 是该表主键.
该表第一行包含了顾客的名字和id.
</pre>

<p> </p>

<p>写一个 SQL 语句, 找到所有遗失的顾客id. 遗失的顾客id是指那些不在 <code>Customers</code> 表中, 值却处于 <code>1</code> 和表中<strong>最大</strong> <code>customer_id</code> 之间的id.</p>

<p><strong>注意: </strong>最大的 <code>customer_id</code> 值不会超过 <code>100</code>.</p>

<p>返回结果按 <code>ids</code> <strong>升序</strong>排列</p>

<p>查询结果格式如下例所示.</p>

<p> </p>

<pre>
<code>Customers</code> 表:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 1           | Alice         |
| 4           | Bob           |
| 5           | Charlie       |
+-------------+---------------+

Result 表:
+-----+
| <code>ids </code>|
+-----+
| 2   |
| 3   |
+-----+
表中最大的customer_id是5, 所以在范围[1,5]内, ID2和3从表中遗失.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
