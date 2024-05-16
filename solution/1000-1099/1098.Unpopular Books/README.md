---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1098.Unpopular%20Books/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1098. 小众书籍 🔒](https://leetcode.cn/problems/unpopular-books)

[English Version](/solution/1000-1099/1098.Unpopular%20Books/README_EN.md)

## 题目描述

<!-- description:start -->

<p>书籍表&nbsp;<code>Books</code>：</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| book_id        | int     |
| name           | varchar |
| available_from | date    |
+----------------+---------+
book_id 是这个表的主键（具有唯一值的列）。
</pre>

<p>订单表&nbsp;<code>Orders</code>：</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| order_id       | int     |
| book_id        | int     |
| quantity       | int     |
| dispatch_date  | date    |
+----------------+---------+
order_id 是这个表的主键（具有唯一值的列）。
book_id  是 Books 表的外键（reference 列）。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，筛选出过去一年中订单总量&nbsp;<strong>少于 </strong><code>10</code><strong> 本&nbsp;</strong>的&nbsp;<strong>书籍</strong>，并且&nbsp;<strong>不考虑&nbsp;</strong>上架距今销售&nbsp;<strong>不满一个月</strong> 的书籍<strong>&nbsp;</strong>。<strong>假设今天是</strong>&nbsp;<code>2019-06-23</code>&nbsp;<strong>。</strong></p>

<p>返回结果表 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Books 表：
+---------+--------------------+----------------+
| book_id | name               | available_from |
+---------+--------------------+----------------+
| 1       | "Kalila And Demna" | 2010-01-01     |
| 2       | "28 Letters"       | 2012-05-12     |
| 3       | "The Hobbit"       | 2019-06-10     |
| 4       | "13 Reasons Why"   | 2019-06-01     |
| 5       | "The Hunger Games" | 2008-09-21     |
+---------+--------------------+----------------+
Orders 表：
+----------+---------+----------+---------------+
| order_id | book_id | quantity | dispatch_date |
+----------+---------+----------+---------------+
| 1        | 1       | 2        | 2018-07-26    |
| 2        | 1       | 1        | 2018-11-05    |
| 3        | 3       | 8        | 2019-06-11    |
| 4        | 4       | 6        | 2019-06-05    |
| 5        | 4       | 5        | 2019-06-20    |
| 6        | 5       | 9        | 2009-02-02    |
| 7        | 5       | 8        | 2010-04-13    |
+----------+---------+----------+---------------+
<strong>输出：</strong>
+-----------+--------------------+
| book_id   | name               |
+-----------+--------------------+
| 1         | "Kalila And Demna" |
| 2         | "28 Letters"       |
| 5         | "The Hunger Games" |
+-----------+--------------------+
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT book_id, name
FROM
    Books
    LEFT JOIN Orders USING (book_id)
WHERE available_from < '2019-05-23'
GROUP BY 1
HAVING SUM(IF(dispatch_date >= '2018-06-23', quantity, 0)) < 10;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
