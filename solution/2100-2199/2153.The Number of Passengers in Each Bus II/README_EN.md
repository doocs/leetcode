# [2153. The Number of Passengers in Each Bus II](https://leetcode.com/problems/the-number-of-passengers-in-each-bus-ii)

[中文文档](/solution/2100-2199/2153.The%20Number%20of%20Passengers%20in%20Each%20Bus%20II/README.md)

## Description

<p>Table: <code>Buses</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| bus_id       | int  |
| arrival_time | int  |
| capacity     | int  |
+--------------+------+
bus_id is the primary key column for this table.
Each row of this table contains information about the arrival time of a bus at the LeetCode station and its capacity (the number of empty seats it has).
No two buses will arrive at the same time and all bus capacities will be positive integers.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Passengers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| passenger_id | int  |
| arrival_time | int  |
+--------------+------+
passenger_id is the primary key column for this table.
Each row of this table contains information about the arrival time of a passenger at the LeetCode station.
</pre>

<p>&nbsp;</p>

<p>Buses and passengers arrive at the LeetCode station. If a bus arrives at the station at a time <code>t<sub>bus</sub></code> and a passenger arrived at a time <code>t<sub>passenger</sub></code> where <code>t<sub>passenger</sub> &lt;= t<sub>bus</sub></code> and the passenger did not catch any bus, the passenger will use that bus. In addition, each bus has a capacity. If at the moment the bus arrives at the station there are more passengers waiting than its capacity <code>capacity</code>, only <code>capacity</code> passengers will use the bus.</p>

<p>Write an SQL query to report the number of users that used each bus.</p>

<p>Return the result table ordered by <code>bus_id</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Buses table:
+--------+--------------+----------+
| bus_id | arrival_time | capacity |
+--------+--------------+----------+
| 1      | 2            | 1        |
| 2      | 4            | 10       |
| 3      | 7            | 2        |
+--------+--------------+----------+
Passengers table:
+--------------+--------------+
| passenger_id | arrival_time |
+--------------+--------------+
| 11           | 1            |
| 12           | 1            |
| 13           | 5            |
| 14           | 6            |
| 15           | 7            |
+--------------+--------------+
<strong>Output:</strong> 
+--------+----------------+
| bus_id | passengers_cnt |
+--------+----------------+
| 1      | 1              |
| 2      | 1              |
| 3      | 2              |
+--------+----------------+
<strong>Explanation:</strong> 
- Passenger 11 arrives at time 1.
- Passenger 12 arrives at time 1.
- Bus 1 arrives at time 2 and collects passenger 11 as it has one empty seat.

- Bus 2 arrives at time 4 and collects passenger 12 as it has ten empty seats.

- Passenger 12 arrives at time 5.
- Passenger 13 arrives at time 6.
- Passenger 14 arrives at time 7.
- Bus 3 arrives at time 7 and collects passengers 12 and 13 as it has two empty seats.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
