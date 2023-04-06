# [2474. Customers With Strictly Increasing Purchases](https://leetcode.com/problems/customers-with-strictly-increasing-purchases)

[中文文档](/solution/2400-2499/2474.Customers%20With%20Strictly%20Increasing%20Purchases/README.md)

## Description

<p>Table: <code>Orders</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| order_id     | int  |
| customer_id  | int  |
| order_date   | date |
| price        | int  |
+--------------+------+
order_id is the primary key for this table.
Each row contains the id of an order, the id of customer that ordered it, the date of the order, and its price.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of the customers with the <strong>total purchases</strong> strictly increasing yearly.</p>

<ul>
	<li>The <strong>total purchases</strong> of a customer in one year is the sum of the prices of their orders in that year. If for some year the customer did not make any order, we consider the total purchases <code>0</code>.</li>
	<li>The first year to consider for each customer is the year of their <strong>first order</strong>.</li>
	<li>The last year to consider for each customer is the year of their <strong>last order</strong>.</li>
</ul>

<p>Return the result table <strong>in any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
+----------+-------------+------------+-------+
| order_id | customer_id | order_date | price |
+----------+-------------+------------+-------+
| 1        | 1           | 2019-07-01 | 1100  |
| 2        | 1           | 2019-11-01 | 1200  |
| 3        | 1           | 2020-05-26 | 3000  |
| 4        | 1           | 2021-08-31 | 3100  |
| 5        | 1           | 2022-12-07 | 4700  |
| 6        | 2           | 2015-01-01 | 700   |
| 7        | 2           | 2017-11-07 | 1000  |
| 8        | 3           | 2017-01-01 | 900   |
| 9        | 3           | 2018-11-07 | 900   |
+----------+-------------+------------+-------+
<strong>Output:</strong> 
+-------------+
| customer_id |
+-------------+
| 1           |
+-------------+
<strong>Explanation:</strong> 
Customer 1: The first year is 2019 and the last year is 2022
  - 2019: 1100 + 1200 = 2300
  - 2020: 3000
  - 2021: 3100
  - 2022: 4700
  We can see that the total purchases are strictly increasing yearly, so we include customer 1 in the answer.

Customer 2: The first year is 2015 and the last year is 2017
  - 2015: 700
  - 2016: 0
  - 2017: 1000
  We do not include customer 2 in the answer because the total purchases are not strictly increasing. Note that customer 2 did not make any purchases in 2016.

Customer 3: The first year is 2017, and the last year is 2018
  - 2017: 900
  - 2018: 900
 We do not include customer 3 in the answer because the total purchases are not strictly increasing.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
