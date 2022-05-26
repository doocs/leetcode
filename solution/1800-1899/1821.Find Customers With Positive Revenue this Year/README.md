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
(customer_id, year) 是这个表的主键。
这个表包含客户 ID 和不同年份的客户收入。
注意，这个收入可能是负数。
</pre>

<p> </p>

<p>写一个 SQL 查询来查询 2021 年具有 <strong>正收入</strong> 的客户。</p>

<p>可以按 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下例。</p>

<p> </p>

<pre>
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

Result table:
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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM
    Customers
WHERE
    year = '2021'
AND revenue > 0;
```

<!-- tabs:end -->
