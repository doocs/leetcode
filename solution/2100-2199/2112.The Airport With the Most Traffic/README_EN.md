# [2112. The Airport With the Most Traffic](https://leetcode.com/problems/the-airport-with-the-most-traffic)

[中文文档](/solution/2100-2199/2112.The%20Airport%20With%20the%20Most%20Traffic/README.md)

## Description

<p>Table: <code>Flights</code></p>

<pre>
+-------------------+------+
| Column Name       | Type |
+-------------------+------+
| departure_airport | int  |
| arrival_airport   | int  |
| flights_count     | int  |
+-------------------+------+
(departure_airport, arrival_airport) is the primary key column for this table.
Each row of this table indicates that there were flights_count flights that departed from departure_airport and arrived at arrival_airport.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the ID of the airport with the <strong>most traffic</strong>. The airport with the most traffic is the airport that has the largest total number of flights that either departed from or arrived at the airport. If there is more than one airport with the most traffic, report them all.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Flights table:
+-------------------+-----------------+---------------+
| departure_airport | arrival_airport | flights_count |
+-------------------+-----------------+---------------+
| 1                 | 2               | 4             |
| 2                 | 1               | 5             |
| 2                 | 4               | 5             |
+-------------------+-----------------+---------------+
<strong>Output:</strong> 
+------------+
| airport_id |
+------------+
| 2          |
+------------+
<strong>Explanation:</strong> 
Airport 1 was engaged with 9 flights (4 departures, 5 arrivals).
Airport 2 was engaged with 14 flights (10 departures, 4 arrivals).
Airport 4 was engaged with 5 flights (5 arrivals).
The airport with the most traffic is airport 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
Flights table:
+-------------------+-----------------+---------------+
| departure_airport | arrival_airport | flights_count |
+-------------------+-----------------+---------------+
| 1                 | 2               | 4             |
| 2                 | 1               | 5             |
| 3                 | 4               | 5             |
| 4                 | 3               | 4             |
| 5                 | 6               | 7             |
+-------------------+-----------------+---------------+
<strong>Output:</strong> 
+------------+
| airport_id |
+------------+
| 1          |
| 2          |
| 3          |
| 4          |
+------------+
<strong>Explanation:</strong> 
Airport 1 was engaged with 9 flights (4 departures, 5 arrivals).
Airport 2 was engaged with 9 flights (5 departures, 4 arrivals).
Airport 3 was engaged with 9 flights (5 departures, 4 arrivals).
Airport 4 was engaged with 9 flights (4 departures, 5 arrivals).
Airport 5 was engaged with 7 flights (7 departures).
Airport 6 was engaged with 7 flights (7 arrivals).
The airports with the most traffic are airports 1, 2, 3, and 4.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
