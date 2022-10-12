# [1440. Evaluate Boolean Expression](https://leetcode.com/problems/evaluate-boolean-expression)

[中文文档](/solution/1400-1499/1440.Evaluate%20Boolean%20Expression/README.md)

## Description

<p>Table <code>Variables</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| name          | varchar |
| value         | int     |
+---------------+---------+
name is the primary key for this table.
This table contains the stored variables and their values.
</pre>

<p>&nbsp;</p>

<p>Table <code>Expressions</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| left_operand  | varchar |
| operator      | enum    |
| right_operand | varchar |
+---------------+---------+
(left_operand, operator, right_operand) is the primary key for this table.
This table contains a boolean expression that should be evaluated.
operator is an enum that takes one of the values (&#39;&lt;&#39;, &#39;&gt;&#39;, &#39;=&#39;)
The values of left_operand and right_operand are guaranteed to be in the Variables table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to evaluate the boolean expressions in <code>Expressions</code> table.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Variables table:
+------+-------+
| name | value |
+------+-------+
| x    | 66    |
| y    | 77    |
+------+-------+
Expressions table:
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
<strong>Output:</strong> 
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
<strong>Explanation:</strong> 
As shown, you need to find the value of each boolean expression in the table using the variables table.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
