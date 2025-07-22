---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3436.Find%20Valid%20Emails/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3436. 查找合法邮箱](https://leetcode.cn/problems/find-valid-emails)

[English Version](/solution/3400-3499/3436.Find%20Valid%20Emails/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Users</code></p>

<pre>
+-----------------+---------+
| Column Name     | Type    |
+-----------------+---------+
| user_id         | int     |
| email           | varchar |
+-----------------+---------+
(user_id) 是这张表的唯一主键。
每一行包含用户的唯一 ID 和邮箱地址。
</pre>

<p>编写一个解决方案来查找所有 <b>合法邮箱地址</b>。一个合法的邮箱地址符合下述条件：</p>

<ul>
	<li>只包含一个&nbsp;<code>@</code>&nbsp;符号。</li>
	<li>以&nbsp;<code>.com</code>&nbsp;结尾。</li>
	<li><code>@</code>&nbsp;符号前面的部分只包含&nbsp;<strong>字母数字</strong>&nbsp;字符和&nbsp;<strong>下划线</strong>。</li>
	<li><code>@</code>&nbsp;符号后面与&nbsp;<code>.com</code>&nbsp;前面的部分 包含 <strong>只有字母&nbsp;</strong>的域名。</li>
</ul>

<p>返回结果表以&nbsp;<code>user_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Users 表：</p>

<pre class="example-io">
+---------+---------------------+
| user_id | email               |
+---------+---------------------+
| 1       | alice@example.com   |
| 2       | bob_at_example.com  |
| 3       | charlie@example.net |
| 4       | david@domain.com    |
| 5       | eve@invalid         |
+---------+---------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+-------------------+
| user_id | email             |
+---------+-------------------+
| 1       | alice@example.com |
| 4       | david@domain.com  |
+---------+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>alice@example.com</strong>&nbsp;是合法的因为它包含一个&nbsp;<code>@</code>，alice 是只有字母数字的，并且&nbsp;example.com 以字母开始并以 .com&nbsp;结束。</li>
	<li><strong>bob_at_example.com</strong>&nbsp;是不合法的因为它包含下划线但没有&nbsp;<code>@</code>。</li>
	<li><strong>charlie@example.net</strong>&nbsp;是不合法的因为域名没有以&nbsp;<code>.com</code>&nbsp;结尾。</li>
	<li><strong>david@domain.com</strong>&nbsp;是合法的因为它满足所有条件。</li>
	<li><strong>eve@invalid</strong>&nbsp;是不合法的因为域名没有以&nbsp;<code>.com</code>&nbsp;结尾。</li>
</ul>

<p>结果表以 user_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：正则表达式

我们可以使用正则表达式，通过 `REGEXP` 来匹配符合条件的邮箱地址。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT user_id, email
FROM Users
WHERE email REGEXP '^[A-Za-z0-9_]+@[A-Za-z][A-Za-z0-9]*\\.com$'
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_valid_emails(users: pd.DataFrame) -> pd.DataFrame:
    email_pattern = r"^[A-Za-z0-9_]+@[A-Za-z][A-Za-z0-9]*\.com$"
    valid_emails = users[users["email"].str.match(email_pattern)]
    valid_emails = valid_emails.sort_values(by="user_id")
    return valid_emails
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
