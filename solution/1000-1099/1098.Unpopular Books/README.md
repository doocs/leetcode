# [1098. 小众书籍](https://leetcode.cn/problems/unpopular-books)

[English Version](/solution/1000-1099/1098.Unpopular%20Books/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>书籍表&nbsp;<code>Books</code>：</p>

<pre>+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| book_id        | int     |
| name           | varchar |
| available_from | date    |
+----------------+---------+
book_id 是这个表的主键。
</pre>

<p>订单表&nbsp;<code>Orders</code>：</p>

<pre>+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| order_id       | int     |
| book_id        | int     |
| quantity       | int     |
| dispatch_date  | date    |
+----------------+---------+
order_id 是这个表的主键。
book_id  是 Books 表的外键。
</pre>

<p>&nbsp;</p>

<p>你需要写一段 SQL 命令，筛选出过去一年中订单总量&nbsp;<strong>少于10本&nbsp;</strong>的&nbsp;<strong>书籍&nbsp;</strong>。</p>

<p>注意：<strong>不考虑&nbsp;</strong>上架（available from）距今&nbsp;<strong>不满一个月</strong> 的书籍。并且&nbsp;<strong>假设今天是</strong>&nbsp;<strong>2019-06-23&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p>下面是样例输出结果：</p>

<pre>Books 表：
+---------+--------------------+----------------+
| book_id | name               | available_from |
+---------+--------------------+----------------+
| 1       | &quot;Kalila And Demna&quot; | 2010-01-01     |
| 2       | &quot;28 Letters&quot;       | 2012-05-12     |
| 3       | &quot;The Hobbit&quot;       | 2019-06-10     |
| 4       | &quot;13 Reasons Why&quot;   | 2019-06-01     |
| 5       | &quot;The Hunger Games&quot; | 2008-09-21     |
+---------+--------------------+----------------+

Orders 表：
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

Result 表：
+-----------+--------------------+
| book_id   | name               |
+-----------+--------------------+
| 1         | &quot;Kalila And Demna&quot; |
| 2         | &quot;28 Letters&quot;       |
| 5         | &quot;The Hunger Games&quot; |
+-----------+--------------------+
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
