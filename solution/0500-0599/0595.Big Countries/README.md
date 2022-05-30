# [595. 大的国家](https://leetcode.cn/problems/big-countries)

[English Version](/solution/0500-0599/0595.Big%20Countries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>World</code> 表：</p>

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| name        | varchar |
| continent   | varchar |
| area        | int     |
| population  | int     |
| gdp         | int     |
+-------------+---------+
name 是这张表的主键。
这张表的每一行提供：国家名称、所属大陆、面积、人口和 GDP 值。
</pre>

<p>&nbsp;</p>

<p>如果一个国家满足下述两个条件之一，则认为该国是 <strong>大国</strong> ：</p>

<ul>
	<li>面积至少为 300 万平方公里（即，<code>3000000 km<sup>2</sup></code>），或者</li>
	<li>人口至少为 2500 万（即 <code>25000000</code>）</li>
</ul>

<p>编写一个 SQL 查询以报告 <strong>大国</strong> 的国家名称、人口和面积。</p>

<p>按 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
World 表：
+-------------+-----------+---------+------------+--------------+
| name        | continent | area    | population | gdp          |
+-------------+-----------+---------+------------+--------------+
| Afghanistan | Asia      | 652230  | 25500100   | 20343000000  |
| Albania     | Europe    | 28748   | 2831741    | 12960000000  |
| Algeria     | Africa    | 2381741 | 37100000   | 188681000000 |
| Andorra     | Europe    | 468     | 78115      | 3712000000   |
| Angola      | Africa    | 1246700 | 20609294   | 100990000000 |
+-------------+-----------+---------+------------+--------------+
<strong>输出：</strong>
+-------------+------------+---------+
| name        | population | area    |
+-------------+------------+---------+
| Afghanistan | 25500100   | 652230  |
| Algeria     | 37100000   | 2381741 |
+-------------+------------+---------+
</pre>
</div>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT name,
        population,
        area
FROM world
WHERE area > 3000000
        OR population > 25000000;
```

<!-- tabs:end -->
