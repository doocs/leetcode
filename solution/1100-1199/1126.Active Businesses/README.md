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
此表的主键是 (business_id, event_type)。
表中的每一行记录了某种类型的事件在某些业务中多次发生的信息。
</pre>

<p>&nbsp;</p>

<p>写一段 SQL 来查询所有活跃的业务。</p>

<p>如果一个业务的某个事件类型的发生次数大于此事件类型在所有业务中的平均发生次数，并且该业务至少有两个这样的事件类型，那么该业务就可被看做是活跃业务。</p>

<p>查询结果格式如下所示：</p>

<pre>
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

结果表
+-------------+
| business_id |
+-------------+
| 1           |
+-------------+ 
&#39;reviews&#39;、 &#39;ads&#39; 和 &#39;page views&#39; 的总平均发生次数分别是 (7+3)/2=5, (11+7+6)/3=8, (3+12)/2=7.5。
id 为 1 的业务有 7 个 &#39;reviews&#39; 事件（大于 5）和 11 个 &#39;ads&#39; 事件（大于 8），所以它是活跃业务。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
