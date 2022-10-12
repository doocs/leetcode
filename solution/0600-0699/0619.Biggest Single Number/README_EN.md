# [619. Biggest Single Number](https://leetcode.com/problems/biggest-single-number)

[中文文档](/solution/0600-0699/0619.Biggest%20Single%20Number/README.md)

## Description

<p>Table: <code>MyNumbers</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| num         | int  |
+-------------+------+
There is no primary key for this table. It may contain duplicates.
Each row of this table contains an integer.
</pre>

<p>&nbsp;</p>

<p>A <strong>single number</strong> is a number that appeared only once in the <code>MyNumbers</code> table.</p>

<p>Write an SQL query to report the largest <strong>single number</strong>. If there is no <strong>single number</strong>, report <code>null</code>.</p>

<p>The query result format is in the following example.</p>
<ptable> </ptable>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
MyNumbers table:
+-----+
| num |
+-----+
| 8   |
| 8   |
| 3   |
| 3   |
| 1   |
| 4   |
| 5   |
| 6   |
+-----+
<strong>Output:</strong> 
+-----+
| num |
+-----+
| 6   |
+-----+
<strong>Explanation:</strong> The single numbers are 1, 4, 5, and 6.
Since 6 is the largest single number, we return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
MyNumbers table:
+-----+
| num |
+-----+
| 8   |
| 8   |
| 7   |
| 7   |
| 3   |
| 3   |
| 3   |
+-----+
<strong>Output:</strong> 
+------+
| num  |
+------+
| null |
+------+
<strong>Explanation:</strong> There are no single numbers in the input table so we return null.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT MAX(a.num) AS num
FROM (
	SELECT num
	FROM MyNumbers
	GROUP BY num
	HAVING count(*) = 1
) a;
```

<!-- tabs:end -->
