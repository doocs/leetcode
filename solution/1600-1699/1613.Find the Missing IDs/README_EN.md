# [1613. Find the Missing IDs](https://leetcode.com/problems/find-the-missing-ids)

[中文文档](/solution/1600-1699/1613.Find%20the%20Missing%20IDs/README.md)

## Description

<p>Table: <code>Customers</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| customer_id   | int     |
| customer_name | varchar |
+---------------+---------+
customer_id is the primary key for this table.
Each row of this table contains the name and the id customer.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the missing customer IDs. The missing IDs are ones that are not in the <code>Customers</code> table but are in the range between <code>1</code> and the <strong>maximum</strong> <code>customer_id</code> present in the table.</p>

<p><strong>Notice</strong> that the maximum <code>customer_id</code> will not exceed <code>100</code>.</p>

<p>Return the result table ordered by <code>ids</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customers table:
+-------------+---------------+
| customer_id | customer_name |
+-------------+---------------+
| 1           | Alice         |
| 4           | Bob           |
| 5           | Charlie       |
+-------------+---------------+
<strong>Output:</strong> 
+-----+
| ids |
+-----+
| 2   |
| 3   |
+-----+
<strong>Explanation:</strong> 
The maximum customer_id present in the table is 5, so in the range [1,5], IDs 2 and 3 are missing from the table.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
