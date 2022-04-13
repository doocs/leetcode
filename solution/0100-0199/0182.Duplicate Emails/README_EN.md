# [182. Duplicate Emails](https://leetcode.com/problems/duplicate-emails)

[中文文档](/solution/0100-0199/0182.Duplicate%20Emails/README.md)

## Description

<p>Table: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report all the duplicate emails.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Person table:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>Output:</strong> 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>Explanation:</strong> a@b.com is repeated two times.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
SELECT Email
FROM Person
GROUP BY Email
HAVING count(Email) > 1;
```

```sql
SELECT DISTINCT p1.email
FROM person AS p1,
    person AS p2
WHERE p1.id != p2.id
    AND p1.email = p2.email;
```

<!-- tabs:end -->
