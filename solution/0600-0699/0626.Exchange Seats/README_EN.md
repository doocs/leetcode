---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0626.Exchange%20Seats/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [626. Exchange Seats](https://leetcode.com/problems/exchange-seats)

[中文文档](/solution/0600-0699/0626.Exchange%20Seats/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Seat</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| student     | varchar |
+-------------+---------+
id is the primary key (unique value) column for this table.
Each row of this table indicates the name and the ID of a student.
The ID sequence always starts from 1 and increments continuously.
</pre>

<p>&nbsp;</p>

<p>Write a solution to swap the seat id of every two consecutive students. If the number of students is odd, the id of the last student is not swapped.</p>

<p>Return the result table ordered by <code>id</code> <strong>in ascending order</strong>.</p>

<p>The result format is in the following example.</p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Adjacent odd/even seats swap, and a leftover last seat stays. A self-join can fetch the partner's name.
>
> `(id+1)^1-1` maps an odd id to the next even and an even id to the previous odd. `COALESCE` keeps the original name when the join misses.

<!-- thinking:end -->


<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    Seat AS s1
    LEFT JOIN Seat AS s2 ON (s1.id + 1) ^ 1 - 1 = s2.id
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> Instead of joining, rewrite `id`: odd (not last) plus one, even minus one, last odd unchanged, then sort by the new id.

<!-- thinking:end -->


<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    id + (
        CASE
            WHEN id % 2 = 1
            AND id != (SELECT MAX(id) FROM Seat) THEN 1
            WHEN id % 2 = 0 THEN -1
            ELSE 0
        END
    ) AS id,
    student
FROM Seat
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- thinking:start -->

> **Thinking**
>
> XOR-flipping the $0$-based index and taking `RANK` over that order swaps each pair in one expression; a leftover last row keeps its relative place.

<!-- thinking:end -->


<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    RANK() OVER (ORDER BY (id - 1) ^ 1) AS id,
    student
FROM Seat;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 4

<!-- thinking:start -->

> **Thinking**
>
> Method 2 probes `MAX(id)` with a subquery. Comparing `ROW_NUMBER()` with a window `COUNT` detects the last row without a second scan.

<!-- thinking:end -->


<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN id & 1 = 0 THEN id - 1
        WHEN ROW_NUMBER() OVER (ORDER BY id) != COUNT(id) OVER () THEN id + 1
        ELSE id
    END AS id,
    student
FROM Seat
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
