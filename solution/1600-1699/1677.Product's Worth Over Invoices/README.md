---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1677.Product%27s%20Worth%20Over%20Invoices/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1677. 发票中的产品金额 🔒](https://leetcode.cn/problems/products-worth-over-invoices)

[English Version](/solution/1600-1699/1677.Product%27s%20Worth%20Over%20Invoices/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>Product</code> 表：</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| name        | varchar |
+-------------+---------+
product_id 是这张表的主键
表中含有产品 id 、产品名称。产品名称都是小写的英文字母，产品名称都是唯一的
</pre>

<p><code>Invoice </code>表：</p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| invoice_id  | int  |
| product_id  | int  |
| rest        | int  |
| paid        | int  |
| canceled    | int  |
| refunded    | int  |
+-------------+------+
invoice_id 发票 id ，是这张表的主键
product_id 产品 id
rest 应缴款项
paid 已支付金额
canceled 已取消金额
refunded 已退款金额
</pre>

<p> </p>

<p>要求写一个SQL查询，对于所有产品，返回每个产品的产品名称，以及全部发票累计的总应缴款项、总已支付金额、总已取消金额、总已退款金额。</p>

<p>查询结果按 <code>product_name</code> 排序</p>

<p>示例：</p>

<pre>
Product 表：
+------------+-------+
| product_id | name  |
+------------+-------+
| 0          | ham   |
| 1          | bacon |
+------------+-------+
Invoice table:
+------------+------------+------+------+----------+----------+
| invoice_id | product_id | rest | paid | canceled | refunded |
+------------+------------+------+------+----------+----------+
| 23         | 0          | 2    | 0    | 5        | 0        |
| 12         | 0          | 0    | 4    | 0        | 3        |
| 1          | 1          | 1    | 1    | 0        | 1        |
| 2          | 1          | 1    | 0    | 1        | 1        |
| 3          | 1          | 0    | 1    | 1        | 1        |
| 4          | 1          | 1    | 1    | 1        | 0        |
+------------+------------+------+------+----------+----------+
Result 表：
+-------+------+------+----------+----------+
| name  | rest | paid | canceled | refunded |
+-------+------+------+----------+----------+
| bacon | 3    | 3    | 3        | 3        |
| ham   | 2    | 4    | 5        | 3        |
+-------+------+------+----------+----------+
- bacon 的总应缴款项为 1 + 1 + 0 + 1 = 3
- bacon 的总已支付金额为 1 + 0 + 1 + 1 = 3
- bacon 的总已取消金额为 0 + 1 + 1 + 1 = 3
- bacon 的总已退款金额为 1 + 1 + 1 + 0 = 3
- ham 的总应缴款项为 2 + 0 = 2
- ham 的总已支付金额为 0 + 4 = 4
- ham 的总已取消金额为 5 + 0 = 5
- ham 的总已退款金额为 0 + 3 = 3
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    name,
    IFNULL(SUM(rest), 0) AS rest,
    IFNULL(SUM(paid), 0) AS paid,
    IFNULL(SUM(canceled), 0) AS canceled,
    IFNULL(SUM(refunded), 0) AS refunded
FROM
    Product
    LEFT JOIN Invoice USING (product_id)
GROUP BY product_id
ORDER BY name;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
