# [3140. Consecutive Available Seats II 🔒](https://leetcode.com/problems/consecutive-available-seats-ii)

[中文文档](/solution/3100-3199/3140.Consecutive%20Available%20Seats%20II/README.md)

<!-- tags: -->

## Description

<p>Table: <code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
seat_id is an auto-increment column for this table.
Each row of this table indicates whether the i<sup>th</sup> seat is free or not. 1 means free while 0 means occupied.
</pre>

<p>Write a solution to find the <strong>length</strong> of&nbsp;<strong>longest consecutive sequence</strong> of <strong>available</strong> seats in the cinema.</p>

<p>Note:</p>

<ul>
	<li>There will always be <strong>at most</strong> <strong>one</strong> longest consecutive sequence.</li>
	<li>If there are <strong>multiple</strong>&nbsp;consecutive sequences with the <strong>same length</strong>, include all of them in the output.</li>
</ul>

<p>Return <em>the result table <strong>ordered</strong> by</em> <code>first_seat_id</code> <em><strong>in ascending order</strong></em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Cinema table:</p>

<pre class="example-io">
+---------+------+
| seat_id | free |
+---------+------+
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |
+---------+------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-----------------+----------------+-----------------------+
| first_seat_id   | last_seat_id   | consecutive_seats_len |
+-----------------+----------------+-----------------------+
| 3               | 5              | 3                     |
+-----------------+----------------+-----------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Longest consecutive sequence of available seats starts from seat 3 and ends at seat 5 with a length of 3.</li>
</ul>
Output table is ordered by first_seat_id in ascending order.</div>

## Solutions

### Solution 1: Using Window Function

First, we find all the vacant seats, and then group the seats. The grouping is based on the seat number minus its ranking. In this way, consecutive vacant seats will be grouped together. Then we find the minimum seat number, maximum seat number, and length of consecutive seats in each group. Finally, we find the group with the longest length of consecutive seats, and output the minimum seat number, maximum seat number, and length of consecutive seats in this group.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            seat_id - (RANK() OVER (ORDER BY seat_id)) AS gid
        FROM Cinema
        WHERE free = 1
    ),
    P AS (
        SELECT
            MIN(seat_id) AS first_seat_id,
            MAX(seat_id) AS last_seat_id,
            COUNT(1) AS consecutive_seats_len
        FROM T
        GROUP BY gid
    ),
    S AS (
        SELECT
            *,
            RANK() OVER (ORDER BY consecutive_seats_len DESC) AS rk
        FROM P
    )
SELECT first_seat_id, last_seat_id, consecutive_seats_len
FROM S
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
