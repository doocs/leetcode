---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1454.Active%20Users/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1454. 活跃用户 🔒](https://leetcode.cn/problems/active-users)

[English Version](/solution/1400-1499/1454.Active%20Users/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表 <code>Accounts</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表主键（具有唯一值的列）
该表包含账户 id 和账户的用户名.
</pre>

<p>&nbsp;</p>

<p>表 <code>Logins</code>:</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| login_date    | date    |
+---------------+---------+
该表可能包含重复项.
该表包含登录用户的账户 id 和登录日期. 用户也许一天内登录多次.
</pre>

<p>&nbsp;</p>

<p><strong>活跃用户</strong> 是指那些至少连续&nbsp;5 天登录账户的用户。</p>

<p>编写解决方案,&nbsp; 找到 <strong>活跃用户</strong> 的 id 和 name。</p>

<p>返回的结果表按照 <code>id</code> <strong>排序&nbsp;</strong>。</p>

<p>结果表格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Accounts 表:
+----+----------+
| id | name     |
+----+----------+
| 1  | Winston  |
| 7  | Jonathan |
+----+----------+

Logins 表:
+----+------------+
| id | login_date |
+----+------------+
| 7  | 2020-05-30 |
| 1  | 2020-05-30 |
| 7  | 2020-05-31 |
| 7  | 2020-06-01 |
| 7  | 2020-06-02 |
| 7  | 2020-06-02 |
| 7  | 2020-06-03 |
| 1  | 2020-06-07 |
| 7  | 2020-06-10 |
+----+------------+
<strong>输出：</strong>
+----+----------+
| id | name     |
+----+----------+
| 7  | Jonathan |
+----+----------+
<strong>解释：</strong>
id = 1 的用户 Winston 仅仅在不同的 2 天内登录了 2 次, 所以, Winston 不是活跃用户.
id = 7 的用户 Jonathon 在不同的 6 天内登录了 7 次, , 6 天中有 5 天是连续的, 所以, Jonathan 是活跃用户.
</pre>

<p>&nbsp;</p>

<p><strong>进阶问题:</strong><br />
如果活跃用户是那些至少连续&nbsp;<code>n</code>&nbsp;天登录账户的用户,&nbsp;你能否写出通用的解决方案?</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一: 使用窗口函数

我们先将 `Logins` 表和 `Accounts` 表连接起来，并且去重，得到临时表 `T`。

然后我们使用窗口函数 `ROW_NUMBER()`，计算出每个用户 `id` 的登录日期的基准日期 `g`，如果用户连续登录 5 天，那么他们的 `g` 值是相同的。

最后，我们按照 `id` 和 `g` 进行分组，统计每个用户的登录次数，如果登录次数大于等于 5，那么这个用户就是活跃用户。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT *
        FROM
            Logins
            JOIN Accounts USING (id)
    ),
    P AS (
        SELECT
            *,
            DATE_SUB(
                login_date,
                INTERVAL ROW_NUMBER() OVER (
                    PARTITION BY id
                    ORDER BY login_date
                ) DAY
            ) g
        FROM T
    )
SELECT DISTINCT id, name
FROM P
GROUP BY id, g
HAVING COUNT(*) >= 5
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
