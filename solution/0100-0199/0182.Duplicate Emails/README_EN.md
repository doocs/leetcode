---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0182.Duplicate%20Emails/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [182. Duplicate Emails](https://leetcode.com/problems/duplicate-emails)

[中文文档](/solution/0100-0199/0182.Duplicate%20Emails/README.md)

## Description

<!-- description:start -->

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

<p>Write a solution to report all the duplicate emails. Note that it&#39;s guaranteed that the email&nbsp;field is not NULL.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Group By + Having

We can use the `GROUP BY` statement to group the data by the `email` field, and then use the `HAVING` statement to filter out the `email` addresses that appear more than once.

<!-- tabs:start -->

```python
import pandas as pd


def duplicate_emails(person: pd.DataFrame) -> pd.DataFrame:
    results = pd.DataFrame()

    results = person.loc[person.duplicated(subset=["email"]), ["email"]]

    return results.drop_duplicates()
```

```sql
# Write your MySQL query statement below
SELECT email
FROM Person
GROUP BY 1
HAVING COUNT(1) > 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Self-Join

We can use a self-join to join the `Person` table with itself, and then filter out the records where the `id` is different but the `email` is the same.

<!-- tabs:start -->

```sql
SELECT DISTINCT p1.email
FROM
    person AS p1,
    person AS p2
WHERE p1.id != p2.id AND p1.email = p2.email;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
