# [619. Biggest Single Number](https://leetcode.com/problems/biggest-single-number)

[中文文档](/solution/0600-0699/0619.Biggest%20Single%20Number/README.md)

<!-- tags:Database -->

<!-- difficulty:Easy -->

## Description

<p>Table: <code>MyNumbers</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| num         | int  |
+-------------+------+
This table may contain duplicates (In other words, there is no primary key for this table in SQL).
Each row of this table contains an integer.
</pre>

<p>&nbsp;</p>

<p>A <strong>single number</strong> is a number that appeared only once in the <code>MyNumbers</code> table.</p>

<p>Find the largest <strong>single number</strong>. If there is no <strong>single number</strong>, report <code>null</code>.</p>

<p>The result format is in the following example.</p>
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

### Solution 1: Grouping and Subquery

We can first group the `MyNumbers` table by `num` and count the number of occurrences of each number. Then, we can use a subquery to find the maximum number among the numbers that appear only once.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT MAX(num) AS num
FROM
    (
        SELECT num
        FROM MyNumbers
        GROUP BY 1
        HAVING COUNT(1) = 1
    ) AS t;
```

<!-- tabs:end -->

### Solution 2: Grouping and `CASE` Expression

Similar to Solution 1, we can first group the `MyNumbers` table by `num` and count the number of occurrences of each number. Then, we can use a `CASE` expression to find the numbers that appear only once, sort them in descending order by number, and take the first one.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN COUNT(1) = 1 THEN num
        ELSE NULL
    END AS num
FROM MyNumbers
GROUP BY num
ORDER BY 1 DESC
LIMIT 1;
```

<!-- tabs:end -->

<!-- end -->
