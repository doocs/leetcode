# [196. Delete Duplicate Emails](https://leetcode.com/problems/delete-duplicate-emails)

[中文文档](/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README.md)

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

<p>Write an SQL query to <strong>delete</strong> all the duplicate emails, keeping only one unique email with the smallest <code>id</code>. Note that you are supposed to write a<code> DELETE</code> statement and not a <code>SELECT</code> one.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Person table:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
<strong>Output:</strong> 
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
<strong>Explanation:</strong> john@example.com is repeated two times. We keep the row with the smallest Id = 1.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
delete from Person
where Id not in (
        select min(Id)
        from (
                select *
                from Person
            ) as p
        group by p.Email
    )
```

```sql
# Write your MySQL query statement below
DELETE p1
FROM Person p1, Person p2
WHERE p1.email = p2.email and p1.id > p2.id
```

<!-- tabs:end -->
