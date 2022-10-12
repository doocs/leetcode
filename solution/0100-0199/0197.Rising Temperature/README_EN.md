# [197. Rising Temperature](https://leetcode.com/problems/rising-temperature)

[中文文档](/solution/0100-0199/0197.Rising%20Temperature/README.md)

## Description

<p>Table: <code>Weather</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id is the primary key for this table.
This table contains information about the temperature on a certain day.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all dates&#39; <code>Id</code> with higher temperatures compared to its previous dates (yesterday).</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Weather table:
+----+------------+-------------+
| id | recordDate | temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
<strong>Output:</strong> 
+----+
| id |
+----+
| 2  |
| 4  |
+----+
<strong>Explanation:</strong> 
In 2015-01-02, the temperature was higher than the previous day (10 -&gt; 25).
In 2015-01-04, the temperature was higher than the previous day (20 -&gt; 30).
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
select w1.Id
from
    Weather w1,
    Weather w2
where
    DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 and w1.Temperature > w2.Temperature
```

```sql
SELECT
	w2.id AS Id
FROM
	weather AS w1
	JOIN weather AS w2 ON DATE_ADD( w1.recordDate, INTERVAL 1 DAY) = w2.recordDate
WHERE
	w1.temperature < w2.temperature
```

<!-- tabs:end -->
