# [1204. Last Person to Fit in the Bus](https://leetcode.com/problems/last-person-to-fit-in-the-bus)

[中文文档](/solution/1200-1299/1204.Last%20Person%20to%20Fit%20in%20the%20Bus/README.md)

## Description

<p>Table: <code>Queue</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| person_id   | int     |
| person_name | varchar |
| weight      | int     |
| turn        | int     |
+-------------+---------+
person_id is the primary key column for this table.
This table has the information about all people waiting for a bus.
The person_id and turn columns will contain all numbers from 1 to n, where n is the number of rows in the table.
turn determines the order of which the people will board the bus, where turn=1 denotes the first person to board and turn=n denotes the last person to board.
weight is the weight of the person in kilograms.
</pre>

<p>&nbsp;</p>

<p>There is a queue of people waiting to board a bus. However, the bus has a weight limit of <code>1000</code><strong> kilograms</strong>, so there may be some people who cannot board.</p>

<p>Write an SQL query to find the <code>person_name</code> of the <strong>last person</strong> that can fit on the bus without exceeding the weight limit. The test cases are generated such that the first person does not exceed the weight limit.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Queue table:
+-----------+-------------+--------+------+
| person_id | person_name | weight | turn |
+-----------+-------------+--------+------+
| 5         | Alice       | 250    | 1    |
| 4         | Bob         | 175    | 5    |
| 3         | Alex        | 350    | 2    |
| 6         | John Cena   | 400    | 3    |
| 1         | Winston     | 500    | 6    |
| 2         | Marie       | 200    | 4    |
+-----------+-------------+--------+------+
<strong>Output:</strong> 
+-------------+
| person_name |
+-------------+
| John Cena   |
+-------------+
<strong>Explanation:</strong> The folowing table is ordered by the turn for simplicity.
+------+----+-----------+--------+--------------+
| Turn | ID | Name      | Weight | Total Weight |
+------+----+-----------+--------+--------------+
| 1    | 5  | Alice     | 250    | 250          |
| 2    | 3  | Alex      | 350    | 600          |
| 3    | 6  | John Cena | 400    | 1000         | (last person to board)
| 4    | 2  | Marie     | 200    | 1200         | (cannot board)
| 5    | 4  | Bob       | 175    | ___          |
| 6    | 1  | Winston   | 500    | ___          |
+------+----+-----------+--------+--------------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
