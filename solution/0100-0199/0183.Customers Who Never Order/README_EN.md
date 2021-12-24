# [183. Customers Who Never Order](https://leetcode.com/problems/customers-who-never-order)

[中文文档](/solution/0100-0199/0183.Customers%20Who%20Never%20Order/README.md)

## Description

<p>Suppose that a website contains two tables, the <code>Customers</code> table and the <code>Orders</code> table. Write a SQL query to find all customers who never order anything.</p>

<p>Table: <code>Customers</code>.</p>

<pre>

+----+-------+

| Id | Name  |

+----+-------+

| 1  | Joe   |

| 2  | Henry |

| 3  | Sam   |

| 4  | Max   |

+----+-------+

</pre>

<p>Table: <code>Orders</code>.</p>

<pre>

+----+------------+

| Id | CustomerId |

+----+------------+

| 1  | 3          |

| 2  | 1          |

+----+------------+

</pre>

<p>Using the above tables as example, return the following:</p>

<pre>

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

```
select Name as Customers from Customers
    where id not in (select CustomerId from Orders)
```

<!-- tabs:end -->
