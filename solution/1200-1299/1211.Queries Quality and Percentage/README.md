# [1211. 查询结果的质量和占比](https://leetcode.cn/problems/queries-quality-and-percentage)

[English Version](/solution/1200-1299/1211.Queries%20Quality%20and%20Percentage/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>查询表 <code>Queries</code>：&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| query_name  | varchar |
| result      | varchar |
| position    | int     |
| rating      | int     |
+-------------+---------+
此表没有主键，并可能有重复的行。
此表包含了一些从数据库中收集的查询信息。
&ldquo;位置&rdquo;（<code>position</code>）列的值为 1 到 500 。
&ldquo;评分&rdquo;（<code>rating</code>）列的值为 1 到 5 。评分小于 3 的查询被定义为质量很差的查询。
</pre>

<p>&nbsp;</p>

<p>将查询结果的质量 <code>quality</code> 定义为：</p>

<blockquote>
<p>各查询结果的评分与其位置之间比率的平均值。</p>
</blockquote>

<p>将劣质查询百分比&nbsp;<code>poor_query_percentage</code> 为：</p>

<blockquote>
<p>评分小于 3 的查询结果占全部查询结果的百分比。</p>
</blockquote>

<p>编写一组 SQL 来查找每次查询的<code>名称</code>(<code>query_name</code>)、<code>质量</code>(<code>quality</code>) 和&nbsp;<code>劣质查询百分比</code>(<code>poor_query_percentage</code>)。</p>

<p><code>质量</code>(<code>quality</code>) 和<code>劣质查询百分比</code>(<code>poor_query_percentage</code>) 都应四舍五入到小数点后两位。</p>

<p>查询结果格式如下所示：</p>

<pre>
Queries table:
+------------+-------------------+----------+--------+
| query_name | result            | position | rating |
+------------+-------------------+----------+--------+
| Dog        | Golden Retriever  | 1        | 5      |
| Dog        | German Shepherd   | 2        | 5      |
| Dog        | Mule              | 200      | 1      |
| Cat        | Shirazi           | 5        | 2      |
| Cat        | Siamese           | 3        | 3      |
| Cat        | Sphynx            | 7        | 4      |
+------------+-------------------+----------+--------+

Result table:
+------------+---------+-----------------------+
| query_name | quality | poor_query_percentage |
+------------+---------+-----------------------+
| Dog        | 2.50    | 33.33                 |
| Cat        | 0.66    | 33.33                 |
+------------+---------+-----------------------+

Dog 查询结果的质量为 ((5 / 1) + (5 / 2) + (1 / 200)) / 3 = 2.50
Dog 查询结果的劣质查询百分比为 (1 / 3) * 100 = 33.33

Cat 查询结果的质量为 ((2 / 5) + (3 / 3) + (4 / 7)) / 3 = 0.66
Cat 查询结果的劣质查询百分比为 (1 / 3) * 100 = 33.33
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
