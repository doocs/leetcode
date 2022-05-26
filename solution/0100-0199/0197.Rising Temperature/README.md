# [197. 上升的温度](https://leetcode.cn/problems/rising-temperature)

[English Version](/solution/0100-0199/0197.Rising%20Temperature/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<div class="original__bRMd">
<div>
<p>表：&nbsp;<code>Weather</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id 是这个表的主键
该表包含特定日期的温度信息</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 <code>id</code> 。</p>

<p>返回结果 <strong>不要求顺序</strong> 。</p>

<p>查询结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Weather 表：</code>
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
<strong>输出：</strong>
+----+
| id |
+----+
| 2  |
| 4  |
+----+
<strong>解释：</strong>
2015-01-02 的温度比前一天高（10 -&gt; 25）
2015-01-04 的温度比前一天高（20 -&gt; 30）</pre>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
select w1.Id
from
    Weather w1,
    Weather w2
where
    DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 and w1.Temperature > w2.Temperature
```

```sql
SELECT
	w2.id AS Id
FROM
	weather AS w1
	JOIN weather AS w2 ON DATE_ADD( w1.recordDate, INTERVAL 1 DAY) = w2.recordDate
WHERE
	w1.temperature < w2.temperature
```

<!-- tabs:end -->
