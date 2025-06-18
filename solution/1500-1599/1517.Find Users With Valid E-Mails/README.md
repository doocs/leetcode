---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1517.Find%20Users%20With%20Valid%20E-Mails/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1517. 查找拥有有效邮箱的用户](https://leetcode.cn/problems/find-users-with-valid-e-mails)

[English Version](/solution/1500-1599/1517.Find%20Users%20With%20Valid%20E-Mails/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Users</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| name          | varchar |
| mail          | varchar |
+---------------+---------+
user_id 是该表的主键（具有唯一值的列）。
该表包含了网站已注册用户的信息。有一些电子邮件是无效的。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，以查找具有有效电子邮件的用户。</p>

<p>一个有效的电子邮件具有前缀名称和域，其中：</p>

<ol>
	<li>&nbsp;<strong>前缀</strong> 名称是一个字符串，可以包含字母（大写或小写），数字，下划线 <code>'_'</code> ，点 <code>'.'</code> 和/或破折号 <code>'-'</code> 。前缀名称 <strong>必须</strong> 以字母开头。</li>
	<li><strong>域</strong> 为 <code>'@leetcode.com'</code> 。</li>
</ol>

<p>以任何顺序返回结果表。</p>

<p>结果的格式如以下示例所示：</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
Users 表:
+---------+-----------+-------------------------+
| user_id | name      | mail                    |
+---------+-----------+-------------------------+
| 1       | Winston   | winston@leetcode.com    |
| 2       | Jonathan  | jonathanisgreat         |
| 3       | Annabelle | bella-@leetcode.com     |
| 4       | Sally     | sally.come@leetcode.com |
| 5       | Marwan    | quarz#2020@leetcode.com |
| 6       | David     | david69@gmail.com       |
| 7       | Shapiro   | .shapo@leetcode.com     |
+---------+-----------+-------------------------+
<b>输出：</b>
+---------+-----------+-------------------------+
| user_id | name      | mail                    |
+---------+-----------+-------------------------+
| 1       | Winston   | winston@leetcode.com    |
| 3       | Annabelle | bella-@leetcode.com     |
| 4       | Sally     | sally.come@leetcode.com |
+---------+-----------+-------------------------+
<b>解释：</b>
用户 2 的电子邮件没有域。 
用户 5 的电子邮件带有不允许的 '#' 符号。
用户 6 的电子邮件没有 leetcode 域。 
用户 7 的电子邮件以点开头。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：REGEXP 正则匹配

我们可以使用正则表达式来匹配有效的电子邮件格式。正则表达式可以确保前缀名称符合要求，并且域名是固定的 `@leetcode.com`。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT *
FROM Users
WHERE mail REGEXP '^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\\.com$' AND BINARY mail LIKE '%@leetcode.com';
```

#### Pandas

```python
import pandas as pd


def valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    pattern = r"^[A-Za-z][A-Za-z0-9_.-]*@leetcode\.com$"
    mask = users["mail"].str.match(pattern, flags=0, na=False)
    return users.loc[mask, ["user_id", "name", "mail"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
