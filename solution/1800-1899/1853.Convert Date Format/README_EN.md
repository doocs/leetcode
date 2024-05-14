# [1853. Convert Date Format 🔒](https://leetcode.com/problems/convert-date-format)

[中文文档](/solution/1800-1899/1853.Convert%20Date%20Format/README.md)

<!-- tags:Database -->

<!-- difficulty:Easy -->

## Description

<p>Table: <code>Days</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| day         | date |
+-------------+------+
day is the column with unique values for this table.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to convert each date in <code>Days</code> into a string formatted as <code>&quot;day_name, month_name day, year&quot;</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Days table:
+------------+
| day        |
+------------+
| 2022-04-12 |
| 2021-08-09 |
| 2020-06-26 |
+------------+
<strong>Output:</strong> 
+-------------------------+
| day                     |
+-------------------------+
| Tuesday, April 12, 2022 |
| Monday, August 9, 2021  |
| Friday, June 26, 2020   |
+-------------------------+
<strong>Explanation:</strong> Please note that the output is case-sensitive.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT DATE_FORMAT(day, '%W, %M %e, %Y') AS day FROM Days;
```

<!-- tabs:end -->

<!-- end -->
