# [595. Big Countries](https://leetcode.com/problems/big-countries)

[中文文档](/solution/0500-0599/0595.Big%20Countries/README.md)

## Description

<p>Table: <code>World</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| name        | varchar |
| continent   | varchar |
| area        | int     |
| population  | int     |
| gdp         | bigint  |
+-------------+---------+
name is the primary key column for this table.
Each row of this table gives information about the name of a country, the continent to which it belongs, its area, the population, and its GDP value.
</pre>

<p>&nbsp;</p>

<p>A country is <strong>big</strong> if:</p>

<ul>
	<li>it has an area of at least&nbsp;three million (i.e., <code>3000000 km<sup>2</sup></code>), or</li>
	<li>it has a population of at least&nbsp;twenty-five million (i.e., <code>25000000</code>).</li>
</ul>

<p>Write an SQL query to report the name, population, and area of the <strong>big countries</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
World table:
+-------------+-----------+---------+------------+--------------+
| name        | continent | area    | population | gdp          |
+-------------+-----------+---------+------------+--------------+
| Afghanistan | Asia      | 652230  | 25500100   | 20343000000  |
| Albania     | Europe    | 28748   | 2831741    | 12960000000  |
| Algeria     | Africa    | 2381741 | 37100000   | 188681000000 |
| Andorra     | Europe    | 468     | 78115      | 3712000000   |
| Angola      | Africa    | 1246700 | 20609294   | 100990000000 |
+-------------+-----------+---------+------------+--------------+
<strong>Output:</strong> 
+-------------+------------+---------+
| name        | population | area    |
+-------------+------------+---------+
| Afghanistan | 25500100   | 652230  |
| Algeria     | 37100000   | 2381741 |
+-------------+------------+---------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT name,
        population,
        area
FROM world
WHERE area > 3000000
        OR population > 25000000;
```

<!-- tabs:end -->
