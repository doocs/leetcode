# [1069. Product Sales Analysis II](https://leetcode.com/problems/product-sales-analysis-ii)

[中文文档](/solution/1000-1099/1069.Product%20Sales%20Analysis%20II/README.md)

## Description

<p>Table:&nbsp;<code>Sales</code></p>

<pre>

+-------------+-------+

| Column Name | Type  |

+-------------+-------+

| sale_id     | int   |

| product_id  | int   |

| year        | int   |

| quantity    | int   |

| price       | int   |

+-------------+-------+

sale_id is the primary key of this table.

product_id is a foreign key to <code>Product</code> table.

Note that the price is per unit.

</pre>

<p>Table:&nbsp;<code>Product</code></p>

<pre>

+--------------+---------+

| Column Name  | Type    |

+--------------+---------+

| product_id   | int     |

| product_name | varchar |

+--------------+---------+

product_id is the primary key of this table.

</pre>

<p>&nbsp;</p>

<p>Write an SQL query that reports the total quantity sold for every product id.</p>

<p>The query result format is in the following example:</p>

<pre>

<code>Sales</code> table:

+---------+------------+------+----------+-------+

| sale_id | product_id | year | quantity | price |

+---------+------------+------+----------+-------+ 

| 1       | 100        | 2008 | 10       | 5000  |

| 2       | 100        | 2009 | 12       | 5000  |

| 7       | 200        | 2011 | 15       | 9000  |

+---------+------------+------+----------+-------+



Product table:

+------------+--------------+

| product_id | product_name |

+------------+--------------+

| 100        | Nokia        |

| 200        | Apple        |

| 300        | Samsung      |

+------------+--------------+



Result table:

+--------------+----------------+

| product_id   | total_quantity |

+--------------+----------------+

| 100          | 22             |

| 200          | 15             |

+--------------+----------------+</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    sum(quantity) AS total_quantity
FROM
    Sales
GROUP BY
    product_id;
```

<!-- tabs:end -->
