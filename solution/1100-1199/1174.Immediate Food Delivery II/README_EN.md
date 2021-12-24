# [1174. Immediate Food Delivery II](https://leetcode.com/problems/immediate-food-delivery-ii)

[中文文档](/solution/1100-1199/1174.Immediate%20Food%20Delivery%20II/README.md)

## Description

<p>Table: <code>Delivery</code></p>

<pre>
+-----------------------------+---------+
| Column Name                 | Type    |
+-----------------------------+---------+
| delivery_id                 | int     |
| customer_id                 | int     |
| order_date                  | date    |
| customer_pref_delivery_date | date    |
+-----------------------------+---------+
delivery_id is the primary key of this table.
The table holds information about food delivery to customers that make orders at some date and specify a preferred delivery date (on the same order date or after it).
</pre>

<p>&nbsp;</p>

<p>If the preferred delivery date of the customer is the same as the&nbsp;order date&nbsp;then the order is called <em>immediate</em>&nbsp;otherwise it&#39;s called <em>scheduled</em>.</p>

<p>The&nbsp;<em>first order</em>&nbsp;of a customer is the order with the earliest order date that customer made. It is guaranteed that a customer has exactly one first order.</p>

<p>Write an SQL query to find the&nbsp;percentage of immediate orders in the first orders of all customers,&nbsp;<strong>rounded to 2 decimal places</strong>.</p>

<p>The query result format is in the following example:</p>

<pre>
Delivery table:
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 2           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-12                  |
| 4           | 3           | 2019-08-24 | 2019-08-24                  |
| 5           | 3           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
| 7           | 4           | 2019-08-09 | 2019-08-09                  |
+-------------+-------------+------------+-----------------------------+

Result table:
+----------------------+
| immediate_percentage |
+----------------------+
| 50.00                |
+----------------------+
The customer id 1 has a first order with delivery id 1 and it is scheduled.
The customer id 2 has a first order with delivery id 2 and it is immediate.
The customer id 3 has a first order with delivery id 5 and it is scheduled.
The customer id 4 has a first order with delivery id 7 and it is immediate.
Hence, half the customers have immediate first orders.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
