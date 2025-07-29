---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0584.Find%20Customer%20Referee/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [584. Find Customer Referee](https://leetcode.com/problems/find-customer-referee)

[中文文档](/solution/0500-0599/0584.Find%20Customer%20Referee/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Customer</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| referee_id  | int     |
+-------------+---------+
In SQL, id is the primary key column for this table.
Each row of this table indicates the id of a customer, their name, and the id of the customer who referred them.
</pre>

<p>&nbsp;</p>

<p>Find the names of the customer that are either:</p>

<ol>
	<li><strong>referred by</strong>&nbsp;any&nbsp;customer with&nbsp;<code>id != 2</code>.</li>
	<li><strong>not referred by</strong> any customer.</li>
</ol>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Customer table:
+----+------+------------+
| id | name | referee_id |
+----+------+------------+
| 1  | Will | null       |
| 2  | Jane | null       |
| 3  | Alex | 2          |
| 4  | Bill | null       |
| 5  | Zack | 1          |
| 6  | Mark | 2          |
+----+------+------------+
<strong>Output:</strong> 
+------+
| name |
+------+
| Will |
| Jane |
| Bill |
| Zack |
+------+
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Conditional Filtering

We can directly filter out the customer names whose `referee_id` is not `2`. Note that the customers whose `referee_id` is `NULL` should also be filtered out.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT name
FROM Customer
WHERE IFNULL(referee_id, 0) != 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
