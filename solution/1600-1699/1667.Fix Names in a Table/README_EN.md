# [1667. Fix Names in a Table](https://leetcode.com/problems/fix-names-in-a-table)

[中文文档](/solution/1600-1699/1667.Fix%20Names%20in%20a%20Table/README.md)

## Description

<p>Table: <code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| name           | varchar |
+----------------+---------+
user_id is the primary key for this table.
This table contains the ID and the name of the user. The name consists of only lowercase and uppercase characters.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to fix the names so that only the first character is uppercase and the rest are lowercase.</p>

<p>Return the result table ordered by <code>user_id</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Users table:
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | aLice |
| 2       | bOB   |
+---------+-------+
<strong>Output:</strong> 
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | Alice |
| 2       | Bob   |
+---------+-------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

MySQL

```sql
SELECT
    user_id,
    CONCAT(UPPER(LEFT(name, 1)), LOWER(SUBSTRING(name, 2))) AS name
FROM
    users
ORDER BY
    user_id;
```

SQL Server

```sql
SELECT
    user_id,
    CONCAT(
        UPPER(LEFT(name, 1)),
        LOWER(SUBSTRING(name, 2, DATALENGTH(name)))
    ) AS name
FROM
    users
ORDER BY
    user_id;
```

<!-- tabs:end -->
