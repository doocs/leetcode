# [626. Exchange Seats](https://leetcode.com/problems/exchange-seats)

[中文文档](/solution/0600-0699/0626.Exchange%20Seats/README.md)

## Description

<p>Table: <code>Seat</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| student     | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table indicates the name and the ID of a student.
id is a continuous increment.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to swap the seat id of every two consecutive students. If the number of students is odd, the id of the last student is not swapped.</p>

<p>Return the result table ordered by <code>id</code> <strong>in ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Seat table:
+----+---------+
| id | student |
+----+---------+
| 1  | Abbot   |
| 2  | Doris   |
| 3  | Emerson |
| 4  | Green   |
| 5  | Jeames  |
+----+---------+
<strong>Output:</strong> 
+----+---------+
| id | student |
+----+---------+
| 1  | Doris   |
| 2  | Abbot   |
| 3  | Green   |
| 4  | Emerson |
| 5  | Jeames  |
+----+---------+
<strong>Explanation:</strong> 
Note that if the number of students is odd, there is no need to change the last one&#39;s seat.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
	s1.id,
	COALESCE ( s2.student, s1.student ) AS student
FROM
	seat s1
	LEFT JOIN seat s2 ON ( s1.id + 1 ) ^ 1 - 1 = s2.id
ORDER BY
	s1.id;
```

```sql
SELECT
    id + (
        CASE
            WHEN id % 2 = 1 AND id != (SELECT MAX(id) FROM seat) THEN 1
			WHEN id % 2 = 0 THEN -1
			ELSE 0
		END
    ) AS id,
    student
FROM
    seat
ORDER BY
	id;
```

<!-- tabs:end -->
