# [3059. 找到所有不同的邮件域名](https://leetcode.cn/problems/find-all-unique-email-domains)

[English Version](/solution/3000-3099/3059.Find%20All%20Unique%20Email%20Domains/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：使用 `SUBSTRING_INDEX` 函数 + 分组统计

我们先筛选出所有以 `.com` 结尾的邮箱，然后使用 `SUBSTRING_INDEX` 函数提取出邮箱的域名，最后使用 `GROUP BY` 统计每个域名的个数。

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
