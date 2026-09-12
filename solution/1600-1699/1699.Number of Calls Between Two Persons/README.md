---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [1699. 两人之间的通话次数 🔒](https://leetcode.cn/problems/number-of-calls-between-two-persons)

[English Version](/solution/1600-1699/1699.Number%20of%20Calls%20Between%20Two%20Persons/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Calls</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| from_id     | int     |
| to_id       | int     |
| duration    | int     |
+-------------+---------+
该表没有主键(具有唯一值的列)，它可能包含重复项。
该表包含 from_id 与 to_id 间的一次电话的时长。
from_id != to_id
</pre>

<p>&nbsp;</p>

<p>编写解决方案，统计每一对用户&nbsp;<code>(person1, person2)</code>&nbsp;之间的通话次数和通话总时长，其中&nbsp;<code>person1 &lt; person2</code>&nbsp;。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Calls 表：
+---------+-------+----------+
| from_id | to_id | duration |
+---------+-------+----------+
| 1       | 2     | 59       |
| 2       | 1     | 11       |
| 1       | 3     | 20       |
| 3       | 4     | 100      |
| 3       | 4     | 200      |
| 3       | 4     | 200      |
| 4       | 3     | 499      |
+---------+-------+----------+
<strong>输出：</strong>
+---------+---------+------------+----------------+
| person1 | person2 | call_count | total_duration |
+---------+---------+------------+----------------+
| 1       | 2       | 2          | 70             |
| 1       | 3       | 1          | 20             |
| 3       | 4       | 4          | 999            |
+---------+---------+------------+----------------+
<strong>解释：</strong>
用户 1 和 2 打过 2 次电话，总时长为 70 (59 + 11)。
用户 1 和 3 打过 1 次电话，总时长为 20。
用户 3 和 4 打过 4 次电话，总时长为 999 (100 + 200 + 200 + 499)。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组求和统计

<!-- thinking:start -->

> **思考**
>
> 通话无向，$(a,b)$ 与 $(b,a)$ 应算作同一对。把较小编号定为 $\texttt{person1}$、较大者为 $\texttt{person2}$ 再分组。
>
> $\texttt{IF}$ 规范化两端，按两列 $\texttt{GROUP BY}$，统计次数与时长之和。

<!-- thinking:end -->

我们可以用 `if` 函数或者 `least` 和 `greatest` 函数来将 `from_id` 和 `to_id` 转换成 `person1` 和 `person2`，然后按照 `person1` 和 `person2` 分组求和统计即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    IF(from_id < to_id, from_id, to_id) AS person1,
    IF(from_id < to_id, to_id, from_id) AS person2,
    COUNT(1) AS call_count,
    SUM(duration) AS total_duration
FROM Calls
GROUP BY 1, 2;
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    LEAST(from_id, to_id) AS person1,
    GREATEST(from_id, to_id) AS person2,
    COUNT(1) AS call_count,
    SUM(duration) AS total_duration
FROM Calls
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
