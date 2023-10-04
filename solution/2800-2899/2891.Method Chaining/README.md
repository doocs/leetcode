# [2891. Method Chaining](https://leetcode.cn/problems/method-chaining)

[English Version](/solution/2800-2899/2891.Method%20Chaining/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<pre>
DataFrame <code>animals</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| name        | object |
| species     | object |
| age         | int    |
| weight      | int    |
+-------------+--------+
</pre>

<p>Write a solution to list the names of animals that weigh <strong>strictly more than</strong> <code>100</code> kilograms.</p>

<p>Return the&nbsp;animals sorted by weight in <strong>descending order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
DataFrame animals:
+----------+---------+-----+--------+
| name     | species | age | weight |
+----------+---------+-----+--------+
| Tatiana  | Snake   | 98  | 464    |
| Khaled   | Giraffe | 50  | 41     |
| Alex     | Leopard | 6   | 328    |
| Jonathan | Monkey  | 45  | 463    |
| Stefan   | Bear    | 100 | 50     |
| Tommy    | Panda   | 26  | 349    |
+----------+---------+-----+--------+
<strong>Output:</strong> 
+----------+
| name     |
+----------+
| Tatiana  |
| Jonathan |
| Tommy    |
| Alex     |
+----------+
<strong>Explanation:</strong> 
All animals weighing more than 100 should be included in the results table.
Tatiana&#39;s weight is 464, Jonathan&#39;s weight is 463, Tommy&#39;s weight is 349, and Alex&#39;s weight is 328.
The results should be sorted in descending order of weight.</pre>

<p>&nbsp;</p>
<p>In Pandas, <strong>method chaining</strong> enables us to&nbsp;perform operations on a DataFrame without breaking up each operation into a separate line or creating multiple temporary variables.&nbsp;</p>

<p>Can you complete this&nbsp;task in just <strong>one line </strong>of code using method chaining?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Pandas**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **...**

```

```

<!-- tabs:end -->
