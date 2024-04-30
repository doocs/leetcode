# [1440. 计算布尔表达式的值 🔒](https://leetcode.cn/problems/evaluate-boolean-expression)

[English Version](/solution/1400-1499/1440.Evaluate%20Boolean%20Expression/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表 <code>Variables</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| name          | varchar |
| value         | int     |
+---------------+---------+
在 SQL 中，name 是该表主键.
该表包含了存储的变量及其对应的值.
</pre>

<p>&nbsp;</p>

<p>表 <code>Expressions</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| left_operand  | varchar |
| operator      | enum    |
| right_operand | varchar |
+---------------+---------+
在 SQL 中，(left_operand, operator, right_operand) 是该表主键.
该表包含了需要计算的布尔表达式.
operator 是枚举类型, 取值于('&lt;', '&gt;', '=')
left_operand 和 right_operand 的值保证存在于 Variables 表单中.
</pre>

<p>&nbsp;</p>

<p>计算表 <code>Expressions</code>&nbsp;中的布尔表达式。</p>

<p>返回的结果表 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Variables 表:
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

<strong>输出:</strong>
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
<strong>解释：</strong>
如上所示, 你需要通过使用 Variables 表来找到 Expressions 表中的每一个布尔表达式的值.
</pre>

## 解法

### 方法一：等值连接 + CASE 表达式

我们可以通过等值连接，将 `Expressions` 表中的每一行与 `Variables` 表中的两行进行关联，关联的条件是 `left_operand = name` 和 `right_operand = name`，然后通过 `CASE` 表达式来判断布尔表达式的值。如果 `operator` 为 `=`，则判断两个值是否相等；如果 `operator` 为 `>`，则判断左值是否大于右值；如果 `operator` 为 `<`，则判断左值是否小于右值。若是，那么布尔表达式的值为 `true`，否则为 `false`。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    left_operand,
    operator,
    right_operand,
    CASE
        WHEN (
            (operator = '=' AND v1.value = v2.value)
            OR (operator = '>' AND v1.value > v2.value)
            OR (operator = '<' AND v1.value < v2.value)
        ) THEN 'true'
        ELSE 'false'
    END AS value
FROM
    Expressions AS e
    JOIN Variables AS v1 ON e.left_operand = v1.name
    JOIN Variables AS v2 ON e.right_operand = v2.name;
```

<!-- tabs:end -->

<!-- end -->
