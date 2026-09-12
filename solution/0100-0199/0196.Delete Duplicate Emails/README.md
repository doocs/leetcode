---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [196. 删除重复的电子邮箱](https://leetcode.cn/problems/delete-duplicate-emails)

[English Version](/solution/0100-0199/0196.Delete%20Duplicate%20Emails/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是该表的主键列(具有唯一值的列)。
该表的每一行包含一封电子邮件。电子邮件将不包含大写字母。
</pre>

<p>&nbsp;</p>

<p>编写解决方案<strong> 删除</strong> 所有重复的电子邮件，只保留一个具有最小 <code>id</code> 的唯一电子邮件。</p>

<p>（对于 SQL 用户，请注意你应该编写一个 <code>DELETE</code> 语句而不是 <code>SELECT</code> 语句。）</p>

<p>（对于 Pandas 用户，请注意你应该直接修改 <code>Person</code> 表。）</p>

<p>运行脚本后，显示的答案是 <code>Person</code> 表。驱动程序将首先编译并运行您的代码片段，然后再显示 <code>Person</code> 表。<code>Person</code> 表的最终顺序 <strong>无关紧要</strong> 。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
<strong>输出:</strong> 
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
<strong>解释:</strong> john@example.com重复两次。我们保留最小的Id = 1。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 同一邮箱只留 $\textit{id}$ 最小的那一行。按邮箱分组取 $\textit{MIN}(\textit{id})$，删除不在这个集合里的行。子查询再包一层是为了让 MySQL 允许对正在删除的表做聚合。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


# Modify Person in place
def delete_duplicate_emails(person: pd.DataFrame) -> None:
    # Sort the rows based on id (Ascending order)
    person.sort_values(by="id", ascending=True, inplace=True)
    # Drop the duplicates based on email.
    person.drop_duplicates(subset="email", keep="first", inplace=True)
```

#### MySQL

```sql
# Write your MySQL query statement below
DELETE FROM Person
WHERE id NOT IN (SELECT MIN(id) FROM (SELECT * FROM Person) AS p GROUP BY email);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一用聚合子查询。窗口 $\textit{ROW\_NUMBER}$ 按邮箱分区、按 $\textit{id}$ 排序，编号大于 $1$ 的即重复行，删除它们，语义更直观。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

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

### 方法三

<!-- thinking:start -->

> **思考**
>
> 不必先算出保留集合。自连接同一邮箱且 $p1.\textit{id}<p2.\textit{id}$，直接删掉 $\textit{id}$ 较大的那一侧，一条 $\textit{DELETE}\,\textit{JOIN}$ 完成。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

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
