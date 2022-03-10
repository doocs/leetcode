# [1699. Number of Calls Between Two Persons](https://leetcode.com/problems/number-of-calls-between-two-persons)

[中文文档](/solution/1600-1699/1699.Number%20of%20Calls%20Between%20Two%20Persons/README.md)

## Description

<p>Table: <code>Calls</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| from_id     | int     |
| to_id       | int     |
| duration    | int     |
+-------------+---------+
This table does not have a primary key, it may contain duplicates.
This table contains the duration of a phone call between from_id and to_id.
from_id != to_id
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the number of calls and the total call duration between each pair of distinct persons <code>(person1, person2)</code> where <code>person1 &lt; person2</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Calls table:
+---------+-------+----------+
| from_id | to_id | duration |
+---------+-------+----------+
| 1       | 2     | 59       |
| 2       | 1     | 11       |
| 1       | 3     | 20       |
| 3       | 4     | 100      |
| 3       | 4     | 200      |
| 3       | 4     | 200      |
| 4       | 3     | 499      |
+---------+-------+----------+
<strong>Output:</strong> 
+---------+---------+------------+----------------+
| person1 | person2 | call_count | total_duration |
+---------+---------+------------+----------------+
| 1       | 2       | 2          | 70             |
| 1       | 3       | 1          | 20             |
| 3       | 4       | 4          | 999            |
+---------+---------+------------+----------------+
<strong>Explanation:</strong> 
Users 1 and 2 had 2 calls and the total duration is 70 (59 + 11).
Users 1 and 3 had 1 call and the total duration is 20.
Users 3 and 4 had 4 calls and the total duration is 999 (100 + 200 + 200 + 499).
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    from_id AS person1,
    to_id AS person2,
    COUNT(1) AS call_count,
    SUM(duration) AS total_duration
FROM
    Calls
GROUP BY
    LEAST(from_id, to_id),
    GREATEST(from_id, to_id);
```

<!-- tabs:end -->
