# [607. Sales Person](https://leetcode.com/problems/sales-person)

[中文文档](/solution/0600-0699/0607.Sales%20Person/README.md)

## Description

<p><b>Description</b></p>

<p>Given three tables: <code>salesperson</code>, <code>company</code>, <code>orders</code>.<br />
Output all the <b>names</b> in the table <code>salesperson</code>, who didn&rsquo;t have sales to company &#39;RED&#39;.</p>

<p><b>Example</b><br />
<b>Input</b></p>

<p>Table: <code>salesperson</code></p>

<pre>
+----------+------+--------+-----------------+-----------+
| sales_id | name | salary | commission_rate | hire_date |
+----------+------+--------+-----------------+-----------+
|   1      | John | 100000 |     6           | 4/1/2006  |
|   2      | Amy  | 120000 |     5           | 5/1/2010  |
|   3      | Mark | 65000  |     12          | 12/25/2008|
|   4      | Pam  | 25000  |     25          | 1/1/2005  |
|   5      | Alex | 50000  |     10          | 2/3/2007  |
+----------+------+--------+-----------------+-----------+
</pre>

The table <code>salesperson</code> holds the salesperson information. Every salesperson has a <b>sales_id</b> and a <b>name</b>.

<p>Table: <code>company</code></p>

<pre>
+---------+--------+------------+
| com_id  |  name  |    city    |
+---------+--------+------------+
|   1     |  RED   |   Boston   |
|   2     | ORANGE |   New York |
|   3     | YELLOW |   Boston   |
|   4     | GREEN  |   Austin   |
+---------+--------+------------+
</pre>

The table <code>company</code> holds the company information. Every company has a <b>com_id</b> and a <b>name</b>.

<p>Table: <code>orders</code></p>

<pre>
+----------+------------+---------+----------+--------+
| order_id | order_date | com_id  | sales_id | amount |
+----------+------------+---------+----------+--------+
| 1        |   1/1/2014 |    3    |    4     | 100000 |
| 2        |   2/1/2014 |    4    |    5     | 5000   |
| 3        |   3/1/2014 |    1    |    1     | 50000  |
| 4        |   4/1/2014 |    1    |    4     | 25000  |
+----------+----------+---------+----------+--------+
</pre>

The table <code>orders</code> holds the sales record information, salesperson and customer company are represented by <b>sales_id</b> and <b>com_id</b>.

<p><b>output</b></p>

<pre>
+------+
| name | 
+------+
| Amy  | 
| Mark | 
| Alex |
+------+
</pre>

<p><b>Explanation</b></p>

<p>According to order &#39;3&#39; and &#39;4&#39; in table <code>orders</code>, it is easy to tell only salesperson &#39;John&#39; and &#39;Pam&#39; have sales to company &#39;RED&#39;,<br />
so we need to output all the other <b>names</b> in the table <code>salesperson</code>.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT name
FROM salesperson
WHERE sales_id
NOT IN (
    SELECT s.sales_id FROM orders o
    INNER JOIN salesperson s ON o.sales_id = s.sales_id
    INNER JOIN company c ON o.com_id = c.com_id
    WHERE c.name = 'RED'
);
```

<!-- tabs:end -->
