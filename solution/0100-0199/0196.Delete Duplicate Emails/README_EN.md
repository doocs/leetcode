---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

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
id is the primary key (column with unique values) for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.
</pre>

<p>&nbsp;</p>

<p>Write a solution to<strong> delete</strong> all duplicate emails, keeping only one unique email with the smallest <code>id</code>.</p>

<p>For SQL users, please note that you are supposed to write a <code>DELETE</code> statement and not a <code>SELECT</code> one.</p>

<p>For Pandas users, please note that you are supposed to modify <code>Person</code> in place.</p>

<p>After running your script, the answer shown is the <code>Person</code> table. The driver will first compile and run your piece of code and then show the <code>Person</code> table. The final order of the <code>Person</code> table <strong>does not matter</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
import pandas as pd


# Modify Person in place
def delete_duplicate_emails(person: pd.DataFrame) -> None:
    # Sort the rows based on id (Ascending order)
    person.sort_values(by="id", ascending=True, inplace=True)
    # Drop the duplicates based on email.
    person.drop_duplicates(subset="email", keep="first", inplace=True)
```

```sql
# Write your MySQL query statement below
DELETE FROM Person
WHERE id NOT IN (SELECT MIN(id) FROM (SELECT * FROM Person) AS p GROUP BY email);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
DELETE FROM Person
WHERE
    id NOT IN (
        SELECT id
        FROM
            (
                SELECT
                    id,
                    ROW_NUMBER() OVER (
                        PARTITION BY email
                        ORDER BY id
                    ) AS rk
                FROM Person
            ) AS p
        WHERE rk = 1
    );
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

```sql
DELETE p2
FROM
    person AS p1
    JOIN person AS p2 ON p1.email = p2.email
WHERE
    p1.id < p2.id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
