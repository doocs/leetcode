# [601. Human Traffic of Stadium](https://leetcode.com/problems/human-traffic-of-stadium)

[中文文档](/solution/0600-0699/0601.Human%20Traffic%20of%20Stadium/README.md)

## Description

<p>Table: <code>Stadium</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| visit_date    | date    |
| people        | int     |
+---------------+---------+
visit_date is the column with unique values for this table.
Each row of this table contains the visit date and visit id to the stadium with the number of people during the visit.
As the id increases, the date increases as well.
</pre>

<p>&nbsp;</p>

<p>Write a solution to display the records with three or more rows with <strong>consecutive</strong> <code>id</code>&#39;s, and the number of people is greater than or equal to 100 for each.</p>

<p>Return the result table ordered by <code>visit_date</code> in <strong>ascending order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Stadium table:
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 1    | 2017-01-01 | 10        |
| 2    | 2017-01-02 | 109       |
| 3    | 2017-01-03 | 150       |
| 4    | 2017-01-04 | 99        |
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |
+------+------------+-----------+
<strong>Output:</strong> 
+------+------------+-----------+
| id   | visit_date | people    |
+------+------------+-----------+
| 5    | 2017-01-05 | 145       |
| 6    | 2017-01-06 | 1455      |
| 7    | 2017-01-07 | 199       |
| 8    | 2017-01-09 | 188       |
+------+------------+-----------+
<strong>Explanation:</strong> 
The four rows with ids 5, 6, 7, and 8 have consecutive ids and each of them has &gt;= 100 people attended. Note that row 8 was included even though the visit_date was not the next day after row 7.
The rows with ids 2 and 3 are not included because we need at least three consecutive ids.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            *,
            id - (ROW_NUMBER() OVER (ORDER BY id)) AS rk
        FROM Stadium
        WHERE people >= 100
    ),
    T AS (SELECT *, COUNT(1) OVER (PARTITION BY rk) AS cnt FROM S)
SELECT id, visit_date, people
FROM T
WHERE cnt >= 3
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
