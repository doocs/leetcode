# [2922. Market Analysis III](https://leetcode.com/problems/market-analysis-iii)

[中文文档](/solution/2900-2999/2922.Market%20Analysis%20III/README.md)

## Description

<p>Table: <code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| seller_id      | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
seller_id is column of unique values for this table.
This table contains seller id, join date, and favorite brand of sellers.
</pre>

<p>Table: <code>Items</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
item_id is the column of unique values for this table.
This table contains item id and item brand.</pre>

<p>Table: <code>Orders</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| seller_id     | int     |
+---------------+---------+
order_id is the column of unique values for this table.
item_id is a foreign key to the Items table.
seller_id is a foreign key to the Users table.
This table contains order id, order date, item id and seller id.</pre>

<p>Write a solution to find the <strong>top seller</strong> who has sold the highest number of<strong> unique</strong> items with a <strong>different</strong> brand than their favorite brand. If there are multiple sellers with the same highest count, return all of them.</p>

<p>Return <em>the result table ordered by</em> <code>seller_id</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+-----------+------------+----------------+
| seller_id | join_date  | favorite_brand |
+-----------+------------+----------------+
| 1         | 2019-01-01 | Lenovo         |
| 2         | 2019-02-09 | Samsung        |
| 3         | 2019-01-19 | LG             |
+-----------+------------+----------------+
Orders table:
+----------+------------+---------+-----------+
| order_id | order_date | item_id | seller_id |
+----------+------------+---------+-----------+
| 1        | 2019-08-01 | 4       | 2         |
| 2        | 2019-08-02 | 2       | 3         |
| 3        | 2019-08-03 | 3       | 3         |
| 4        | 2019-08-04 | 1       | 2         |
| 5        | 2019-08-04 | 4       | 2         |
+----------+------------+---------+-----------+
Items table:
+---------+------------+
| item_id | item_brand |
+---------+------------+
| 1       | Samsung    |
| 2       | Lenovo     |
| 3       | LG         |
| 4       | HP         |
+---------+------------+
<strong>Output:</strong> 
+-----------+-----------+
| seller_id | num_items |
+-----------+-----------+
| 2         | 1         |
| 3         | 1         |
+-----------+-----------+
<strong>Explanation:</strong> 
- The user with seller_id 2 has sold three items, but only two of them are not marked as a favorite. We will include a unique count of 1 because both of these items are identical.
- The user with seller_id 3 has sold two items, but only one of them is not marked as a favorite. We will include just that non-favorite item in our count.
Since seller_ids 2 and 3 have the same count of one item each, they both will be displayed in the output.</pre>

## Solutions

**Solution 1: Equijoin + Grouping + Subquery**

We can use equijoin to connect the `Orders` table and the `Users` table according to `seller_id`, then connect `Items` according to `item_id`, and filter out the records where `item_brand` is not equal to `favorite_brand`. Then, group by `seller_id` and count the number of `item_id` corresponding to each `seller_id`. Finally, use a subquery to find the `seller_id` with the most `item_id`.

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT seller_id, COUNT(DISTINCT item_id) AS num_items
        FROM
            Orders
            JOIN Users USING (seller_id)
            JOIN Items USING (item_id)
        WHERE item_brand != favorite_brand
        GROUP BY 1
    )
SELECT seller_id, num_items
FROM T
WHERE num_items = (SELECT MAX(num_items) FROM T)
ORDER BY 1;
```

<!-- tabs:end -->
