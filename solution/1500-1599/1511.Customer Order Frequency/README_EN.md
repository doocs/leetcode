# [1511. Customer Order Frequency](https://leetcode.com/problems/customer-order-frequency)

[中文文档](/solution/1500-1599/1511.Customer%20Order%20Frequency/README.md)

## Description

<p>Table: <code>Customers</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| customer_id   | int     |

| name          | varchar |

| country       | varchar |

+---------------+---------+

customer_id is the primary key for this table.

This table contains information of the customers in the company.

</pre>

<p>&nbsp;</p>

<p>Table: <code>Product</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| product_id    | int     |

| description   | varchar |

| price         | int     |

+---------------+---------+

product_id is the primary key for this table.

This table contains information of the products in the company.

price is the product cost.</pre>

<p>&nbsp;</p>

<p>Table: <code>Orders</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| order_id      | int     |

| customer_id   | int     |

| product_id    | int     |

| order_date    | date    |

| quantity      | int     |

+---------------+---------+

order_id is the primary key for this table.

This table contains information on customer orders.

customer_id is the id of the customer who bought &quot;quantity&quot; products with id &quot;product_id&quot;.

Order_date is the date in format (&#39;YYYY-MM-DD&#39;) when the order was shipped.</pre>

<p>&nbsp;</p>

<p>Write an SQL query to&nbsp;report the&nbsp;customer_id and customer_name of customers who have spent at least $100 in each month of June and July 2020.</p>

<p>Return the result table in any order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>

<pre>

<code>Customers</code>

+--------------+-----------+-------------+

| customer_id  | name &nbsp;    | country &nbsp; &nbsp; |

+--------------+-----------+-------------+

| 1    &nbsp;       | Winston  &nbsp;| USA        &nbsp;|

| 2          &nbsp; | Jonathan  | Peru       &nbsp;|

| 3          &nbsp; | Moustafa &nbsp;| Egypt      &nbsp;|

+--------------+-----------+-------------+



<code>Product</code>

+--------------+-------------+-------------+

| product_id   | description | price   &nbsp; &nbsp; |

+--------------+-------------+-------------+

| 10   &nbsp;       | LC Phone &nbsp;  | 300        &nbsp;|

| 20         &nbsp; | LC T-Shirt  | 10         &nbsp;|

| 30         &nbsp; | LC Book    &nbsp;| 45         &nbsp;|

| 40           | LC Keychain&nbsp;| 2         &nbsp; |

+--------------+-------------+-------------+



<code>Orders</code>

+--------------+-------------+-------------+-------------+-----------+

| order_id     | customer_id | product_id  | order_date  | quantity  |

+--------------+-------------+-------------+-------------+-----------+

| 1    &nbsp;       | 1        &nbsp;  | 10         &nbsp;| 2020-06-10  | 1         |

| 2          &nbsp; | 1           | 20         &nbsp;| 2020-07-01  | 1         |

| 3          &nbsp; | 1           | 30         &nbsp;| 2020-07-08  | 2         |

| 4    &nbsp;       | 2        &nbsp;  | 10         &nbsp;| 2020-06-15  | 2         |

| 5          &nbsp; | 2           | 40         &nbsp;| 2020-07-01  | 10        |

| 6          &nbsp; | 3           | 20         &nbsp;| 2020-06-24  | 2         |

| 7    &nbsp;       | 3        &nbsp;  | 30         &nbsp;| 2020-06-25  | 2         |

| 9          &nbsp; | 3           | 30         &nbsp;| 2020-05-08  | 3         |

+--------------+-------------+-------------+-------------+-----------+



Result table:

+--------------+------------+

| customer_id  | name       |  

+--------------+------------+

| 1            | Winston    |

+--------------+------------+ 

Winston spent $300 (300 * 1) in June and $100 ( 10 * 1 + 45 * 2) in July 2020.

Jonathan spent $600 (300 * 2) in June and $20 ( 2 * 10) in July 2020.

Moustafa spent $110 (10 * 2 + 45 * 2) in June and $0 in July 2020.

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    c.customer_id AS CUSTOMER_ID,
    c.name AS NAME
FROM
    Customers c, Product p, Orders o
WHERE
    c.customer_id = o.customer_id
AND p.product_id = o.product_id
GROUP BY c.customer_id
HAVING sum(if(month(o.order_date)=6, price*quantity, 0)) >= 100
AND sum(if(month(o.order_date)=7, price*quantity, 0)) >= 100;
```

<!-- tabs:end -->
