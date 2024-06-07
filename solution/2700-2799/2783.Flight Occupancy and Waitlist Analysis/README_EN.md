---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2783.Flight%20Occupancy%20and%20Waitlist%20Analysis/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2783. Flight Occupancy and Waitlist Analysis 🔒](https://leetcode.com/problems/flight-occupancy-and-waitlist-analysis)

[中文文档](/solution/2700-2799/2783.Flight%20Occupancy%20and%20Waitlist%20Analysis/README.md)

## Description

<!-- description:start -->

<p>Table: <code><font face="monospace">Flights</font></code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| flight_id   | int  |
| capacity    | int  |
+-------------+------+
<code>flight_id</code> is the column with unique values for this table.
Each row of this table contains flight id and its capacity.
</pre>

<p>Table: <code>Passengers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| passenger_id | int  |
| flight_id    | int  |
+--------------+------+
passenger_id is the column with unique values for this table.
Each row of this table contains passenger id and flight id.
</pre>

<p>Passengers book tickets for flights in advance. If a passenger books a ticket for a flight and there are still empty seats available on the flight, the passenger ticket will be <strong>confirmed</strong>. However, the passenger will be on a <strong>waitlist</strong> if the flight is already at full capacity.</p>

<p>Write a solution to report the number of passengers who successfully booked a flight (got a seat) and the number of passengers who are on the waitlist for each flight.</p>

<p>Return the result table ordered by<em> </em><code>flight_id</code>&nbsp;in <strong>ascending</strong><em><strong>&nbsp;order</strong>.</em></p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Flights table:
+-----------+----------+
| flight_id | capacity |
+-----------+----------+
| 1         | 2        |
| 2         | 2        |
| 3         | 1        |
+-----------+----------+
Passengers table:
+--------------+-----------+
| passenger_id | flight_id |
+--------------+-----------+
| 101          | 1         |
| 102          | 1         |
| 103          | 1         |
| 104          | 2         |
| 105          | 2         |
| 106          | 3         |
| 107          | 3         |
+--------------+-----------+
<strong>Output:</strong> 
+-----------+------------+--------------+
| flight_id | booked_cnt | waitlist_cnt |
+-----------+------------+--------------+
| 1         | 2          | 1            |
| 2         | 2          | 0            |
| 3         | 1          | 1            |
+-----------+------------+--------------+
<strong>Explanation:</strong> 
- Flight 1 has a capacity of 2. As there are 3 passengers who have booked tickets, only 2 passengers can get a seat. Therefore, 2 passengers are successfully booked, and 1 passenger is on the waitlist.
- Flight 2 has a capacity of 2. Since there are exactly 2 passengers who booked tickets, everyone can secure a seat. As a result, 2 passengers successfully booked their seats and there are no passengers on the waitlist.
- Flight 3 has a capacity of 1. As there are 2 passengers who have booked tickets, only 1 passenger can get a seat. Therefore, 1 passenger is successfully booked, and 1 passenger is on the waitlist.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    flight_id,
    LEAST(COUNT(passenger_id), capacity) AS booked_cnt,
    GREATEST(COUNT(passenger_id) - capacity, 0) AS waitlist_cnt
FROM
    Flights
    LEFT JOIN Passengers USING (flight_id)
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
