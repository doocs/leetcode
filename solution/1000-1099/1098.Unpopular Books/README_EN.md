# [1098. Unpopular Books](https://leetcode.com/problems/unpopular-books)

[中文文档](/solution/1000-1099/1098.Unpopular%20Books/README.md)

## Description

<p>Table:&nbsp;<code>Books</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| book_id        | int     |
| name           | varchar |
| available_from | date    |
+----------------+---------+
book_id is the primary key of this table.
</pre>

<p>Table:&nbsp;<code>Orders</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| order_id       | int     |
| book_id        | int     |
| quantity       | int     |
| dispatch_date  | date    |
+----------------+---------+
order_id is the primary key of this table.
book_id is a foreign key to the Books table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query that reports the&nbsp;<strong>books</strong> that have sold <strong>less than 10</strong> copies in the last year, excluding books that have been available for less than 1 month from today. <strong>Assume today is 2019-06-23</strong>.</p>

<p>The query result format is in the following example:</p>

<pre>
Books table:
+---------+--------------------+----------------+
| book_id | name               | available_from |
+---------+--------------------+----------------+
| 1       | &quot;Kalila And Demna&quot; | 2010-01-01     |
| 2       | &quot;28 Letters&quot;       | 2012-05-12     |
| 3       | &quot;The Hobbit&quot;       | 2019-06-10     |
| 4       | &quot;13 Reasons Why&quot;   | 2019-06-01     |
| 5       | &quot;The Hunger Games&quot; | 2008-09-21     |
+---------+--------------------+----------------+

Orders table:
+----------+---------+----------+---------------+
| order_id | book_id | quantity | dispatch_date |
+----------+---------+----------+---------------+
| 1        | 1       | 2        | 2018-07-26    |
| 2        | 1       | 1        | 2018-11-05    |
| 3        | 3       | 8        | 2019-06-11    |
| 4        | 4       | 6        | 2019-06-05    |
| 5        | 4       | 5        | 2019-06-20    |
| 6        | 5       | 9        | 2009-02-02    |
| 7        | 5       | 8        | 2010-04-13    |
+----------+---------+----------+---------------+

Result table:
+-----------+--------------------+
| book_id   | name               |
+-----------+--------------------+
| 1         | &quot;Kalila And Demna&quot; |
| 2         | &quot;28 Letters&quot;       |
| 5         | &quot;The Hunger Games&quot; |
+-----------+--------------------+
</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
