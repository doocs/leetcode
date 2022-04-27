# [603. Consecutive Available Seats](https://leetcode.com/problems/consecutive-available-seats)

[中文文档](/solution/0600-0699/0603.Consecutive%20Available%20Seats/README.md)

## Description

<p>Table: <code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
seat_id is an auto-increment primary key column for this table.
Each row of this table indicates whether the i<sup>th</sup> seat is free or not. 1 means free while 0 means occupied.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report all the consecutive available seats in the cinema.</p>

<p>Return the result table <strong>ordered</strong> by <code>seat_id</code> <strong>in ascending order</strong>.</p>

<p>The test cases are generated so that more than two seats are consecutively available.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Cinema table:
+---------+------+
| seat_id | free |
+---------+------+
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |
+---------+------+
<strong>Output:</strong> 
+---------+
| seat_id |
+---------+
| 3       |
| 4       |
| 5       |
+---------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT c1.seat_id
FROM   Cinema c1,
       Cinema c2
WHERE  ( ( c1.seat_id = c2.seat_id + 1 )
          OR ( c1.seat_id = c2.seat_id - 1 ) )
       AND ( c1.free = 1
             AND c2.free = 1 )
GROUP BY seat_id;
```

<!-- tabs:end -->
