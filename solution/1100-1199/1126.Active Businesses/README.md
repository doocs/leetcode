# [1126. 查询活跃业务](https://leetcode.cn/problems/active-businesses)

[English Version](/solution/1100-1199/1126.Active%20Businesses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>事件表：<code>Events</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| business_id   | int     |
| event_type    | varchar |
| occurences    | int     | 
+---------------+---------+
(business_id, event_type) 是这个表的主键（具有唯一值的列的组合）。
表中的每一行记录了某种类型的事件在某些业务中多次发生的信息。
</pre>

<p>&nbsp;</p>

<p><strong>平均活动</strong> 是指有特定 <code>event_type</code> 的具有该事件的所有公司的 <code>occurences</code>&nbsp;的均值。</p>

<p><strong>活跃业务</strong> 是指具有&nbsp;<strong>多个</strong> <code>event_type</code>&nbsp;的业务，它们的 <code>occurences</code> <strong>严格大于</strong> 该事件的平均活动次数。</p>

<p>写一个解决方案，找到所有 <strong>活跃业务</strong>。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Events table:
+-------------+------------+------------+
| business_id | event_type | occurences |
+-------------+------------+------------+
| 1           | reviews    | 7          |
| 3           | reviews    | 3          |
| 1           | ads        | 11         |
| 2           | ads        | 7          |
| 3           | ads        | 6          |
| 1           | page views | 3          |
| 2           | page views | 12         |
+-------------+------------+------------+
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

## 解法

### 方法一

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

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

<!-- end -->
