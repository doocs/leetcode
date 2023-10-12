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
In SQL, name is the primary key for this table.
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
In SQL, (left_operand, operator, right_operand) is the primary key for this table.
This table contains a boolean expression that should be evaluated.
operator is an enum that takes one of the values (&#39;&lt;&#39;, &#39;&gt;&#39;, &#39;=&#39;)
The values of left_operand and right_operand are guaranteed to be in the Variables table.
</pre>

<p>&nbsp;</p>

<p>Evaluate the boolean expressions in <code>Expressions</code> table.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

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

**Solution 1: Equi-Join + CASE Expression**

We can associate each row in the `Expressions` table with two rows in the `Variables` table using an equi-join, where the conditions for the association are `left_operand = name` and `right_operand = name`. Then, we can use a `CASE` expression to determine the value of the boolean expression. If the `operator` is `=`, we check if the two values are equal. If the `operator` is `>`, we check if the left value is greater than the right value. If the `operator` is `<`, we check if the left value is less than the right value. If the condition is true, the boolean expression evaluates to `true`, otherwise it evaluates to `false`.

<!-- tabs:start -->

### **SQL**

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
