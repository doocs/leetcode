# [2889. Reshape Data Pivot](https://leetcode.com/problems/reshape-data-pivot)

[中文文档](/solution/2800-2899/2889.Reshape%20Data%20Pivot/README.md)

## Description

<pre>
DataFrame <code>weather</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| city        | object |
| month       | object |
| temperature | int    |
+-------------+--------+
</pre>

<p>Write a solution to <strong>pivot</strong> the data so that each row represents temperatures for a specific month, and each city is a separate column.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<pre>
<strong class="example">Example 1:</strong>
<strong>Input:</strong>
+--------------+----------+-------------+
| city         | month    | temperature |
+--------------+----------+-------------+
| Jacksonville | January  | 13          |
| Jacksonville | February | 23          |
| Jacksonville | March    | 38          |
| Jacksonville | April    | 5           |
| Jacksonville | May      | 34          |
| ElPaso       | January  | 20          |
| ElPaso       | February | 6           |
| ElPaso       | March    | 26          |
| ElPaso       | April    | 2           |
| ElPaso       | May      | 43          |
+--------------+----------+-------------+
<strong>Output:</strong><code>
+----------+--------+--------------+
| month    | ElPaso | Jacksonville |
+----------+--------+--------------+
| April    | 2      | 5            |
| February | 6      | 23           |
| January  | 20     | 13           |
| March    | 26     | 38           |
| May      | 43     | 34           |
+----------+--------+--------------+</code>
<strong>Explanation:
</strong>The table is pivoted, each column represents a city, and each row represents a specific month.</pre>

## Solutions

<!-- tabs:start -->

### **Pandas**

```python

```

### **...**

```

```

<!-- tabs:end -->
