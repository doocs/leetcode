# [586. Customer Placing the Largest Number of Orders](https://leetcode.com/problems/customer-placing-the-largest-number-of-orders)

[中文文档](/solution/0500-0599/0586.Customer%20Placing%20the%20Largest%20Number%20of%20Orders/README.md)

## Description

<p>Table: <code>Orders</code></p>

<pre>
+-----------------+----------+
| Column Name     | Type     |
+-----------------+----------+
| order_number    | int      |
| customer_number | int      |
+-----------------+----------+
order_number is the primary key for this table.
This table contains information about the order ID and the customer ID.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the <code>customer_number</code> for the customer who has placed <strong>the largest number of orders</strong>.</p>

<p>The test cases are generated so that <strong>exactly one customer</strong> will have placed more orders than any other customer.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Orders table:
+--------------+-----------------+
| order_number | customer_number |
+--------------+-----------------+
| 1            | 1               |
| 2            | 2               |
| 3            | 3               |
| 4            | 3               |
+--------------+-----------------+
<strong>Output:</strong> 
+-----------------+
| customer_number |
+-----------------+
| 3               |
+-----------------+
<strong>Explanation:</strong> 
The customer with number 3 has two orders, which is greater than either customer 1 or 2 because each of them only has one order. 
So the result is customer_number 3.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if more than one customer has the largest number of orders, can you find all the <code>customer_number</code> in this case?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    customer_number
FROM
    Orders
GROUP BY customer_number
ORDER BY COUNT(customer_number) DESC
LIMIT 1;
```

SQL Server

```sql
SELECT TOP 1
    customer_number
FROM
    orders
GROUP BY customer_number
ORDER BY COUNT(customer_number) DESC;
```

<!-- tabs:end -->
