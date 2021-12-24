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
This table contains information about the temperature in a certain day.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all dates&#39; <code>id</code>&nbsp;with higher temperature compared to its previous dates (yesterday).</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example:</p>

<pre>
<code>Weather</code>
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+

Result table:
+----+
| id |
+----+
| 2  |
| 4  |
+----+
In 2015-01-02, temperature was higher than the previous day (10 -&gt; 25).
In 2015-01-04, temperature was higher than the previous day (20 -&gt; 30).
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select w1.Id
from
    Weather w1,
    Weather w2
where
    DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 and w1.Temperature > w2.Temperature
```

<!-- tabs:end -->
