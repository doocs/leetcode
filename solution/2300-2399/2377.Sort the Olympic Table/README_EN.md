# [2377. Sort the Olympic Table 🔒](https://leetcode.com/problems/sort-the-olympic-table)

[中文文档](/solution/2300-2399/2377.Sort%20the%20Olympic%20Table/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Olympic</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| country       | varchar |
| gold_medals   | int     |
| silver_medals | int     |
| bronze_medals | int     |
+---------------+---------+
In SQL, country is the primary key for this table.
Each row in this table shows a country name and the number of gold, silver, and bronze medals it won in the Olympic games.
</pre>

<p>&nbsp;</p>

<p>The Olympic table is sorted according to the following rules:</p>

<ul>
	<li>The country with more gold medals comes first.</li>
	<li>If there is a tie in the gold medals, the country with more silver medals comes first.</li>
	<li>If there is a tie in the silver medals, the country with more bronze medals comes first.</li>
	<li>If there is a tie in the bronze medals, the countries with the tie are sorted in ascending order lexicographically.</li>
</ul>

<p>Write a solution to sort the Olympic table.</p>

<p>The result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Olympic table:
+-------------+-------------+---------------+---------------+
| country     | gold_medals | silver_medals | bronze_medals |
+-------------+-------------+---------------+---------------+
| China       | 10          | 10            | 20            |
| South Sudan | 0           | 0             | 1             |
| USA         | 10          | 10            | 20            |
| Israel      | 2           | 2             | 3             |
| Egypt       | 2           | 2             | 2             |
+-------------+-------------+---------------+---------------+
<strong>Output:</strong> 
+-------------+-------------+---------------+---------------+
| country     | gold_medals | silver_medals | bronze_medals |
+-------------+-------------+---------------+---------------+
| China       | 10          | 10            | 20            |
| USA         | 10          | 10            | 20            |
| Israel      | 2           | 2             | 3             |
| Egypt       | 2           | 2             | 2             |
| South Sudan | 0           | 0             | 1             |
+-------------+-------------+---------------+---------------+
<strong>Explanation:</strong> 
The tie between China and USA is broken by their lexicographical names. Since &quot;China&quot; is lexicographically smaller than &quot;USA&quot;, it comes first.
Israel comes before Egypt because it has more bronze medals.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT *
FROM Olympic
ORDER BY 2 DESC, 3 DESC, 4 DESC, 1;
```

<!-- tabs:end -->

<!-- end -->
