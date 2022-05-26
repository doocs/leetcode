# [180. Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers)

[中文文档](/solution/0100-0199/0180.Consecutive%20Numbers/README.md)

## Description

<p>Table: <code>Logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
id is the primary key for this table.
id is an autoincrement column.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all numbers that appear at least three times consecutively.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Logs table:
+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
<strong>Output:</strong> 
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
<strong>Explanation:</strong> 1 is the only number that appears consecutively for at least three times.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
select distinct(Num) as ConsecutiveNums from Logs Curr where
    Num = (select Num from Logs where id = Curr.id - 1) and
    Num = (select Num from Logs where id = Curr.id - 2)
```

```sql
# Write your MySQL query statement below
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM
    logs AS l1,
    logs AS l2,
    logs AS l3
WHERE
    l1.id = l2.id - 1
    AND
    l2.id = l3.id - 1
    AND
    l1.num = l2.num
    AND
    l2.num = l3.num
```

<!-- tabs:end -->
