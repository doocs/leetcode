---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1126.Active%20Businesses/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1126. 查询活跃业务 🔒](https://leetcode.cn/problems/active-businesses)

[English Version](/solution/1100-1199/1126.Active%20Businesses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>事件表：<code>Events</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| business_id   | int     |
| event_type    | varchar |
| occurrences   | int     | 
+---------------+---------+
(business_id, event_type) 是这个表的主键（具有唯一值的列的组合）。
表中的每一行记录了某种类型的事件在某些业务中多次发生的信息。
</pre>

<p>&nbsp;</p>

<p><strong>平均活动</strong> 是指有特定 <code>event_type</code> 的具有该事件的所有公司的 <code>occurrences</code>&nbsp;的均值。</p>

<p><strong>活跃业务</strong> 是指具有&nbsp;<strong>多个</strong> <code>event_type</code>&nbsp;的业务，它们的 <code>occurrences</code> <strong>严格大于</strong> 该事件的平均活动次数。</p>

<p>写一个解决方案，找到所有 <strong>活跃业务</strong>。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Events table:
+-------------+------------+-------------+
| business_id | event_type | occurrences |
+-------------+------------+-------------+
| 1           | reviews    | 7           |
| 3           | reviews    | 3           |
| 1           | ads        | 11          |
| 2           | ads        | 7           |
| 3           | ads        | 6           |
| 1           | page views | 3           |
| 2           | page views | 12          |
+-------------+------------+-------------+
<strong>输出：</strong>
+-------------+
| business_id |
+-------------+
| 1           |
+-------------+ 
<strong>解释：</strong>
每次活动的平均活动可计算如下:
- 'reviews': (7+3)/2 = 5
- 'ads': (11+7+6)/3 = 8
- 'page views': (3+12)/2 = 7.5
id=1 的业务有 7 个 'reviews' 事件(多于 5 个)和 11 个 'ads' 事件(多于 8 个)，所以它是一个活跃的业务。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 活跃商家指在多于一种事件类型上，出现次数超过该类型全局均值。先按 `event_type` 聚合 `AVG(occurences)`，再与原表按类型连接，筛出高于均值的行，最后按商家分组并要求计数大于 $1$。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT business_id
FROM
    EVENTS AS t1
    JOIN (
        SELECT
            event_type,
            AVG(occurences) AS occurences
        FROM EVENTS
        GROUP BY event_type
    ) AS t2
        ON t1.event_type = t2.event_type
WHERE t1.occurences > t2.occurences
GROUP BY business_id
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一用派生表算均值再连接。窗口函数 `AVG(...) OVER (PARTITION BY event_type)` 可在同一层给每行打上是否超均值的标记，随后过滤并分组，少一次显式 JOIN。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            business_id,
            occurences > AVG(occurences) OVER (PARTITION BY event_type) AS mark
        FROM Events
    )
SELECT business_id
FROM T
WHERE mark = 1
GROUP BY 1
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
