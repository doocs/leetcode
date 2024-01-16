# [1821. 寻找今年具有正收入的客户](https://leetcode.cn/problems/find-customers-with-positive-revenue-this-year)

[English Version](/solution/1800-1899/1821.Find%20Customers%20With%20Positive%20Revenue%20this%20Year/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Customers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| customer_id  | int  |
| year         | int  |
| revenue      | int  |
+--------------+------+
(customer_id, year) 是该表的主键（具有唯一值的列的组合）。
这个表包含客户 ID 和不同年份的客户收入。
注意，这个收入可能是负数。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案来报告 2021 年具有 <strong>正收入</strong> 的客户。</p>

<p>可以以&nbsp;<strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>Input:</strong>
Customers
+-------------+------+---------+
| customer_id | year | revenue |
+-------------+------+---------+
| 1           | 2018 | 50      |
| 1           | 2021 | 30      |
| 1           | 2020 | 70      |
| 2           | 2021 | -50     |
| 3           | 2018 | 10      |
| 3           | 2016 | 50      |
| 4           | 2021 | 20      |
+-------------+------+---------+

<strong>Output:</strong>
+-------------+
| customer_id |
+-------------+
| 1           |
| 4           |
+-------------+
客户 1 在 2021 年的收入等于 30 。
客户 2 在 2021 年的收入等于 -50 。
客户 3 在 2021 年没有收入。
客户 4 在 2021 年的收入等于 20 。
因此，只有客户 1 和 4 在 2021 年有正收入。</pre>

## 解法

### 方法一：WHERE 子句

我们可以直接使用 `WHERE` 子句来筛选出 `year` 为 `2021` 且 `revenue` 大于 $0$ 的客户。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM Customers
WHERE year = '2021' AND revenue > 0;
```

<!-- tabs:end -->

<!-- end -->
