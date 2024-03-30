# [3059. 找到所有不同的邮件域名](https://leetcode.cn/problems/find-all-unique-email-domains)

[English Version](/solution/3000-3099/3059.Find%20All%20Unique%20Email%20Domains/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Emails</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是这张表的主键（有不同值的列）。
这张表的每一行包含一个电子邮件地址。电子邮件地址不包含大写字母。
</pre>

<p>编写一个解决方案来找到所有 <strong>不同的电子邮件域名</strong> 并且计数与每个域名相关联的 <strong>记录</strong>。<strong>只考虑</strong> 以 <strong>.com</strong> <strong>结尾</strong> 的域名。</p>

<p>返回结果表以 email_domains <strong>升序</strong> 排列。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入：</strong> 
Emails 表：
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
<strong>输出：</strong> 
+--------------+-------+
| email_domain | count |
+--------------+-------+
| outlook.com  | 2     |
| yahoo.com    | 1     |  
+--------------+-------+
<strong>解释：</strong> 
- 以“.com”结束的合法域名只有“outlook.com”和“yahoo.com”，数量分别为 2 和 1。
输出表以 email_domains 升序排列。
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
