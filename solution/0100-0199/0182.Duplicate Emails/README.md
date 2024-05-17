---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0182.Duplicate%20Emails/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [182. 查找重复的电子邮箱](https://leetcode.cn/problems/duplicate-emails)

[English Version](/solution/0100-0199/0182.Duplicate%20Emails/README_EN.md)

## 题目描述

<!-- description:start -->

<p><meta charset="UTF-8" /></p>

<p>表:&nbsp;<code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是该表的主键（具有唯一值的列）。
此表的每一行都包含一封电子邮件。电子邮件不包含大写字母。
</pre>

<p>&nbsp;</p>

<p>编写解决方案来报告所有重复的电子邮件。 请注意，可以保证电子邮件字段不为 NULL。</p>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>输出:</strong> 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>解释:</strong> a@b.com 出现了两次。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们可以使用 `GROUP BY` 语句，按照 `email` 字段进行分组，然后使用 `HAVING` 语句，筛选出现次数大于 $1$ 的 `email`。

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

### 方法二：自连接

我们可以使用自连接的方法，将 `Person` 表自身连接一次，然后筛选出 `id` 不同，但 `email` 相同的记录。

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
