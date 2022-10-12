# [1821. Find Customers With Positive Revenue this Year](https://leetcode.com/problems/find-customers-with-positive-revenue-this-year)

[中文文档](/solution/1800-1899/1821.Find%20Customers%20With%20Positive%20Revenue%20this%20Year/README.md)

## Description

<p>Table: <code>Customers</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| customer_id  | int  |
| year         | int  |
| revenue      | int  |
+--------------+------+
(customer_id, year) is the primary key for this table.
This table contains the customer ID and the revenue of customers in different years.
Note that this revenue can be negative.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the customers with <strong>postive revenue</strong> in the year 2021.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+-------------+------+---------+
| customer_id | year | revenue |
+-------------+------+---------+
| 1           | 2018 | 50      |
| 1           | 2021 | 30      |
| 1           | 2020 | 70      |
| 2           | 2021 | -50     |
| 3           | 2018 | 10      |
| 3           | 2016 | 50      |
| 4           | 2021 | 20      |
+-------------+------+---------+
<strong>Output:</strong> 
+-------------+
| customer_id |
+-------------+
| 1           |
| 4           |
+-------------+
<strong>Explanation:</strong> 
Customer 1 has revenue equal to 30 in the year 2021.
Customer 2 has revenue equal to -50 in the year 2021.
Customer 3 has no revenue in the year 2021.
Customer 4 has revenue equal to 20 in the year 2021.
Thus only customers 1 and 4 have positive revenue in the year 2021.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    customer_id
FROM
    Customers
WHERE
    year = '2021'
AND revenue > 0;
```

<!-- tabs:end -->
