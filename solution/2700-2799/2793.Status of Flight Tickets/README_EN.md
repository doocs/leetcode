---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2793.Status%20of%20Flight%20Tickets/README_EN.md
---

# [2793. Status of Flight Tickets 🔒](https://leetcode.com/problems/status-of-flight-tickets)

[中文文档](/solution/2700-2799/2793.Status%20of%20Flight%20Tickets/README.md)

## Description

<p>Table: <code><font face="monospace">Flights</font></code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| flight_id   | int  |
| capacity    | int  |
+-------------+------+
<code>flight_id</code> column contains distinct values.
Each row of this table contains flight id and capacity.
</pre>

<p>Table: <code>Passengers</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| passenger_id | int      |
| flight_id    | int      |
| booking_time | datetime |
+--------------+----------+
passenger_id column contains distinct values.
booking_time column contains distinct values.
Each row of this table contains passenger id, booking time, and their flight id.
</pre>

<p>Passengers book tickets for flights in advance. If a passenger books a ticket for a flight and there are still empty seats available on the flight, the passenger&#39;s ticket will be <strong>confirmed</strong>. However, the passenger will be on a <strong>waitlist</strong> if the flight is already at full capacity.</p>

<p>Write a solution to determine the current status of flight tickets for each passenger.</p>

<p>Return the result table ordered by <code>passenger_id</code> <em>in <strong>ascending order</strong>.</em></p>

<p>The result format is in the following example.</p>

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
+--------------+-----------+---------------------+
| passenger_id | flight_id | booking_time        |
+--------------+-----------+---------------------+
| 101          | 1         | 2023-07-10 16:30:00 |
| 102          | 1         | 2023-07-10 17:45:00 |
| 103          | 1         | 2023-07-10 12:00:00 |
| 104          | 2         | 2023-07-05 13:23:00 |
| 105          | 2         | 2023-07-05 09:00:00 |
| 106          | 3         | 2023-07-08 11:10:00 |
| 107          | 3         | 2023-07-08 09:10:00 |
+--------------+-----------+---------------------+
<strong>Output:</strong> 
+--------------+-----------+
| passenger_id | Status    |
+--------------+-----------+
| 101          | Confirmed | 
| 102          | Waitlist  | 
| 103          | Confirmed | 
| 104          | Confirmed | 
| 105          | Confirmed | 
| 106          | Waitlist  | 
| 107          | Confirmed | 
+--------------+-----------+
<strong>Explanation:</strong> 
- Flight 1 has a capacity of 2 passengers. Passenger 101 and Passenger 103 were the first to book tickets, securing the available seats. Therefore, their bookings are confirmed. However, Passenger 102 was the third person to book a ticket for this flight, which means there are no more available seats. Passenger 102 is now placed on the waitlist, 
- Flight 2 has a capacity of 2 passengers, Flight 2 has exactly two passengers who booked tickets,  Passenger 104 and Passenger 105. Since the number of passengers who booked tickets matches the available seats, both bookings are confirmed.
- Flight 3 has a capacity of 1 passenger. Passenger 107 booked earlier and secured the only available seat, confirming their booking. Passenger 106, who booked after Passenger 107, is on the waitlist.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    passenger_id,
    IF(
        (
            RANK() OVER (
                PARTITION BY flight_id
                ORDER BY booking_time
            )
        ) <= capacity,
        'Confirmed',
        'Waitlist'
    ) AS Status
FROM
    Passengers
    JOIN Flights USING (flight_id)
ORDER BY passenger_id;
```

<!-- tabs:end -->

<!-- end -->
