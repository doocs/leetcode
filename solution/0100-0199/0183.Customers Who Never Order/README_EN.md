# [183. Customers Who Never Order](https://leetcode.com/problems/customers-who-never-order)

[中文文档](/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README.md)

## Description

<p>Table: <code>Customers</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table indicates the ID and name of a customer.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Orders</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| customerId  | int  |
+-------------+------+
id is the primary key column for this table.
customerId is a foreign key of the ID from the Customers table.
Each row of this table indicates the ID of an order and the ID of the customer who ordered it.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report all customers who never order anything.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Orders table:
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
<strong>Output:</strong> 
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
select Name as Customers
from Customers
where id not in (
        select CustomerId
        from Orders
    );
```

```sql
SELECT
    c.Name AS Customers
FROM
    customers AS c
    LEFT JOIN orders AS o ON c.Id  = o.CustomerId
WHERE
    o.CustomerId IS NULL;
```

<!-- tabs:end -->
