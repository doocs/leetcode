# [3059. Find All Unique Email Domains](https://leetcode.com/problems/find-all-unique-email-domains)

[中文文档](/solution/3000-3099/3059.Find%20All%20Unique%20Email%20Domains/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Emails</code></p>

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

<p>Write a solution to find all <strong>unique email domains</strong> and count the number of <strong>individuals</strong> associated with each domain. <strong>Consider only</strong> those domains that <strong>end</strong> with <strong>.com</strong>.</p>

<p>Return <em>the result table orderd by email domains in </em><strong>ascending</strong><em> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Emails table:
+-----+-----------------------+
| id  | email                 |
+-----+-----------------------+
| 336 | hwkiy@test.edu        |
| 489 | adcmaf@outlook.com    |
| 449 | vrzmwyum@yahoo.com    |
| 95  | tof@test.edu          |
| 320 | jxhbagkpm@example.org |
| 411 | zxcf@outlook.com      |
+----+------------------------+
<strong>Output:</strong> 
+--------------+-------+
| email_domain | count |
+--------------+-------+
| outlook.com  | 2     |
| yahoo.com    | 1     |  
+--------------+-------+
<strong>Explanation:</strong> 
- The valid domains ending with &quot;.com&quot; are only &quot;outlook.com&quot; and &quot;yahoo.com&quot;, with respective counts of 2 and 1.
Output table is ordered by email_domains in ascending order.
</pre>

## Solutions

### Solution 1: Using `SUBSTRING_INDEX` Function + Grouping Statistics

First, we filter out all emails ending with `.com`, then use the `SUBSTRING_INDEX` function to extract the domain name of the email. Finally, we use `GROUP BY` to count the number of each domain.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT SUBSTRING_INDEX(email, '@', -1) AS email_domain, COUNT(1) AS count
FROM Emails
WHERE email LIKE '%.com'
GROUP BY 1
ORDER BY 1;
```

```python
import pandas as pd


def find_unique_email_domains(emails: pd.DataFrame) -> pd.DataFrame:
    emails["email_domain"] = emails["email"].str.split("@").str[-1]
    emails = emails[emails["email"].str.contains(".com")]
    return (
        emails.groupby("email_domain")
        .size()
        .reset_index(name="count")
        .sort_values(by="email_domain")
    )
```

<!-- tabs:end -->

<!-- end -->
