# [1693. 每天的领导和合伙人](https://leetcode.cn/problems/daily-leads-and-partners)

[English Version](/solution/1600-1699/1693.Daily%20Leads%20and%20Partners/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>DailySales</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| date_id     | date    |
| make_name   | varchar |
| lead_id     | int     |
| partner_id  | int     |
+-------------+---------+
该表没有主键(具有唯一值的列)。它可能包含重复项。
该表包含日期、产品的名称，以及售给的领导和合伙人的编号。
名称只包含小写英文字母。</pre>

<p>&nbsp;</p>

<p>对于每一个&nbsp;<code>date_id</code>&nbsp;和&nbsp;<code>make_name</code>，找出&nbsp;<strong>不同&nbsp;</strong>的&nbsp;<code>lead_id</code>&nbsp;以及&nbsp;<strong>不同&nbsp;</strong>的&nbsp;<code>partner_id</code>&nbsp;的数量。</p>

<p>按 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
DailySales 表：
+-----------+-----------+---------+------------+
| date_id   | make_name | lead_id | partner_id |
+-----------+-----------+---------+------------+
| 2020-12-8 | toyota    | 0       | 1          |
| 2020-12-8 | toyota    | 1       | 0          |
| 2020-12-8 | toyota    | 1       | 2          |
| 2020-12-7 | toyota    | 0       | 2          |
| 2020-12-7 | toyota    | 0       | 1          |
| 2020-12-8 | honda     | 1       | 2          |
| 2020-12-8 | honda     | 2       | 1          |
| 2020-12-7 | honda     | 0       | 1          |
| 2020-12-7 | honda     | 1       | 2          |
| 2020-12-7 | honda     | 2       | 1          |
+-----------+-----------+---------+------------+
<strong>输出：</strong>
+-----------+-----------+--------------+-----------------+
| date_id   | make_name | unique_leads | unique_partners |
+-----------+-----------+--------------+-----------------+
| 2020-12-8 | toyota    | 2            | 3               |
| 2020-12-7 | toyota    | 1            | 2               |
| 2020-12-8 | honda     | 2            | 2               |
| 2020-12-7 | honda     | 3            | 2               |
+-----------+-----------+--------------+-----------------+
<strong>解释：</strong>
在 2020-12-8，丰田（toyota）有领导者 = [0, 1] 和合伙人 = [0, 1, 2] ，同时本田（honda）有领导者 = [1, 2] 和合伙人 = [1, 2]。
在 2020-12-7，丰田（toyota）有领导者 = [0] 和合伙人 = [1, 2] ，同时本田（honda）有领导者 = [0, 1, 2] 和合伙人 = [1, 2]。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分组统计**

我们可以使用 `GROUP BY` 语句，按照 `date_id` 和 `make_name` 字段进行分组，然后使用 `COUNT(DISTINCT)` 函数，统计 `lead_id` 和 `partner_id` 的不同值的数量。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    date_id,
    make_name,
    count(DISTINCT lead_id) AS unique_leads,
    count(DISTINCT partner_id) AS unique_partners
FROM DailySales
GROUP BY 1, 2;
```

<!-- tabs:end -->
