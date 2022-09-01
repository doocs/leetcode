# [2175. 世界排名的变化](https://leetcode.cn/problems/the-change-in-global-rankings)

[English Version](/solution/2100-2199/2175.The%20Change%20in%20Global%20Rankings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>TeamPoints</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| team_id     | int     |
| name        | varchar |
| points      | int     |
+-------------+---------+
team_id 是这张表的主键。
这张表的每一行均包含了一支国家队的 ID，它所代表的国家，以及它在全球排名中的得分。没有两支队伍代表同一个国家。
</pre>

<p>&nbsp;</p>

<p>表：<code>PointsChange</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| team_id       | int  |
| points_change | int  |
+---------------+------+
team_id 是这张表的主键。
这张表的每一行均包含了一支国家队的 ID 以及它在世界排名中的得分的变化。
分数的变化分以下情况：
- 0:代表分数没有改变
- 正数:代表分数增加
- 负数:代表分数降低
TeamPoints 表中出现的每一个 team_id 均会在这张表中出现。
</pre>

<p>&nbsp;</p>

<p>国家队的全球排名是按<strong> 降序排列</strong> 所有队伍的得分后所得出的排名。如果两支队伍得分相同，我们将按其名称的 <strong>字典顺序 </strong>排列以打破平衡。</p>

<p>每支国家队的分数应根据其相应的 <code>points_change</code> 进行更新。</p>

<p>编写一条 SQL 查询来计算在分数更新后，每个队伍的全球排名的变化。</p>

<p>以<strong> 任意顺序 </strong>返回结果。</p>

<p>查询结果的格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
TeamPoints 表：
+---------+-------------+--------+
| team_id | name        | points |
+---------+-------------+--------+
| 3       | Algeria     | 1431   |
| 1       | Senegal     | 2132   |
| 2       | New Zealand | 1402   |
| 4       | Croatia     | 1817   |
+---------+-------------+--------+
PointsChange 表：
+---------+---------------+
| team_id | points_change |
+---------+---------------+
| 3       | 399           |
| 2       | 0             |
| 4       | 13            |
| 1       | -22           |
+---------+---------------+
<strong>输出：</strong>
+---------+-------------+-----------+
| team_id | name        | rank_diff |
+---------+-------------+-----------+
| 1       | Senegal     | 0         |
| 4       | Croatia     | -1        |
| 3       | Algeria     | 1         |
| 2       | New Zealand | 0         |
+---------+-------------+-----------+
<strong>解释：</strong>
世界排名如下所示：
+---------+-------------+--------+------+
| team_id | name        | points | rank |
+---------+-------------+--------+------+
| 1       | Senegal     | 2132   | 1    |
| 4       | Croatia     | 1817   | 2    |
| 3       | Algeria     | 1431   | 3    |
| 2       | New Zealand | 1402   | 4    |
+---------+-------------+--------+------+
在更新分数后，世界排名变为下表：
+---------+-------------+--------+------+
| team_id | name        | points | rank |
+---------+-------------+--------+------+
| 1       | Senegal     | 2110   | 1    |
| 3       | Algeria     | 1830   | 2    |
| 4       | Croatia     | 1830   | 3    |
| 2       | New Zealand | 1402   | 4    |
+---------+-------------+--------+------+
由于在更新分数后，Algeria 和 Croatia 的得分相同，因此根据字典顺序对它们进行排序。
Senegal 丢失了22分但他们的排名没有改变。
Croatia 获得了13分但是他们的排名下降了1名。
Algeria 获得399分，排名上升了1名。
New Zealand 没有获得或丢失分数，他们的排名也没有发生变化。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
