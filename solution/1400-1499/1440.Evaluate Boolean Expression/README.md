# [1440. 计算布尔表达式的值](https://leetcode.cn/problems/evaluate-boolean-expression)

[English Version](/solution/1400-1499/1440.Evaluate%20Boolean%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表 <code>Variables</code>:</p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| name          | varchar |
| value         | int     |
+---------------+---------+
name 是该表主键.
该表包含了存储的变量及其对应的值.
</pre>

<p>&nbsp;</p>

<p>表 <code>Expressions</code>:</p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| left_operand  | varchar |
| operator      | enum    |
| right_operand | varchar |
+---------------+---------+
(left_operand, operator, right_operand) 是该表主键.
该表包含了需要计算的布尔表达式.
operator 是枚举类型, 取值于(&#39;&lt;&#39;, &#39;&gt;&#39;, &#39;=&#39;)
left_operand 和 right_operand 的值保证存在于 Variables 表单中.
</pre>

<p>&nbsp;</p>

<p>写一个 SQL 查询,&nbsp; 以计算表 <code>Expressions</code>&nbsp;中的布尔表达式.</p>

<p>返回的结果表没有顺序要求.</p>

<p>查询结果格式如下例所示.</p>

<pre>Variables 表:
+------+-------+
| name | value |
+------+-------+
| x    | 66    |
| y    | 77    |
+------+-------+

Expressions 表:
+--------------+----------+---------------+
| left_operand | operator | right_operand |
+--------------+----------+---------------+
| x            | &gt;        | y             |
| x            | &lt;        | y             |
| x            | =        | y             |
| y            | &gt;        | x             |
| y            | &lt;        | x             |
| x            | =        | x             |
+--------------+----------+---------------+

Result 表:
+--------------+----------+---------------+-------+
| left_operand | operator | right_operand | value |
+--------------+----------+---------------+-------+
| x            | &gt;        | y             | false |
| x            | &lt;        | y             | true  |
| x            | =        | y             | false |
| y            | &gt;        | x             | true  |
| y            | &lt;        | x             | false |
| x            | =        | x             | true  |
+--------------+----------+---------------+-------+
如上所示, 你需要通过使用 Variables 表来找到 Expressions 表中的每一个布尔表达式的值.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
