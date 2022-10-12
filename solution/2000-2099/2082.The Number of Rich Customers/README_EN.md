# [2082. The Number of Rich Customers](https://leetcode.com/problems/the-number-of-rich-customers)

[中文文档](/solution/2000-2099/2082.The%20Number%20of%20Rich%20Customers/README.md)

## Description

<p>Table: <code>Store</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| bill_id     | int  |
| customer_id | int  |
| amount      | int  |
+-------------+------+
bill_id is the primary key for this table.
Each row contains information about the amount of one bill and the customer associated with it.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the number of customers who had <strong>at least one</strong> bill with an amount <strong>strictly greater</strong> than <code>500</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Store table:
+---------+-------------+--------+
| bill_id | customer_id | amount |
+---------+-------------+--------+
| 6       | 1           | 549    |
| 8       | 1           | 834    |
| 4       | 2           | 394    |
| 11      | 3           | 657    |
| 13      | 3           | 257    |
+---------+-------------+--------+
<strong>Output:</strong> 
+------------+
| rich_count |
+------------+
| 2          |
+------------+
<strong>Explanation:</strong> 
Customer 1 has two bills with amounts strictly greater than 500.
Customer 2 does not have any bills with an amount strictly greater than 500.
Customer 3 has one bill with an amount strictly greater than 500.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    COUNT(DISTINCT(customer_id)) AS rich_count
FROM
    Store
WHERE
    amount > 500;
```

<!-- tabs:end -->
