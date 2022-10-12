# [1543. Fix Product Name Format](https://leetcode.com/problems/fix-product-name-format)

[中文文档](/solution/1500-1599/1543.Fix%20Product%20Name%20Format/README.md)

## Description

<p>Table: <code>Sales</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| sale_id      | int     |
| product_name | varchar |
| sale_date    | date    |
+--------------+---------+
sale_id is the primary key for this table.
Each row of this table contains the product name and the date it was sold.
</pre>

<p>&nbsp;</p>

<p>Since table Sales was filled manually in the year <code>2000</code>, <code>product_name</code> may contain leading and/or trailing white spaces, also they are case-insensitive.</p>

<p>Write an SQL query to report</p>

<ul>
	<li><code>product_name</code> in lowercase without leading or trailing white spaces.</li>
	<li><code>sale_date</code> in the format <code>(&#39;YYYY-MM&#39;)</code>.</li>
	<li><code>total</code> the number of times the product was sold in this month.</li>
</ul>

<p>Return the result table ordered by <code>product_name</code> in <strong>ascending order</strong>. In case of a tie, order it by <code>sale_date</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Sales table:
+---------+--------------+------------+
| sale_id | product_name | sale_date  |
+---------+--------------+------------+
| 1       | LCPHONE      | 2000-01-16 |
| 2       | LCPhone      | 2000-01-17 |
| 3       | LcPhOnE      | 2000-02-18 |
| 4       | LCKeyCHAiN   | 2000-02-19 |
| 5       | LCKeyChain   | 2000-02-28 |
| 6       | Matryoshka   | 2000-03-31 |
+---------+--------------+------------+
<strong>Output:</strong> 
+--------------+-----------+-------+
| product_name | sale_date | total |
+--------------+-----------+-------+
| lckeychain   | 2000-02   | 2     |
| lcphone      | 2000-01   | 2     |
| lcphone      | 2000-02   | 1     |
| matryoshka   | 2000-03   | 1     |
+--------------+-----------+-------+
<strong>Explanation:</strong> 
In January, 2 LcPhones were sold. Please note that the product names are not case sensitive and may contain spaces.
In February, 2 LCKeychains and 1 LCPhone were sold.
In March, one matryoshka was sold.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
