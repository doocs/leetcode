---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [3172. 第二天验证 🔒](https://leetcode.cn/problems/second-day-verification)

[English Version](/solution/3100-3199/3172.Second%20Day%20Verification/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>emails</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| email_id    | int      |
| user_id     | int      |
| signup_date | datetime |
+-------------+----------+
(email_id, user_id) 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含 email ID，user ID 和注册日期。
</pre>

<p>表：<code>texts</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     | 
+---------------+----------+
| text_id       | int      |
| email_id      | int      |
| signup_action | enum     |
| action_date   | datetime |
+---------------+----------+
(text_id, email_id) 是这张表的主键（有不同值的列的组合）。
signup_action 是 ('Verified', 'Not Verified') 的枚举类型。
这张表的每一行包含 text ID，email ID，注册操作和操作日期。
</pre>

<p>编写一个解决方案来找到&nbsp;<strong>第二天验证注册</strong>&nbsp;的用户 ID。</p>

<p>返回结果表以&nbsp;<code>user_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p>emails 表：</p>

<pre class="example-io">
+----------+---------+---------------------+
| email_id | user_id | signup_date         |
+----------+---------+---------------------+
| 125      | 7771    | 2022-06-14 09:30:00|
| 433      | 1052    | 2022-07-09 08:15:00|
| 234      | 7005    | 2022-08-20 10:00:00|
+----------+---------+---------------------+
</pre>

<p>texts 表：</p>

<pre class="example-io">
+---------+----------+--------------+---------------------+
| text_id | email_id | signup_action| action_date         |
+---------+----------+--------------+---------------------+
| 1       | 125      | Verified     | 2022-06-15 08:30:00|
| 2       | 433      | Not Verified | 2022-07-10 10:45:00|
| 4       | 234      | Verified     | 2022-08-21 09:30:00|
+---------+----------+--------------+---------------------+
    </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+
| user_id |
+---------+
| 7005    |
| 7771    |
+---------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>user_id 为 7005 的用户在 2022-08-20 10:00:00 注册并且在第二天验证。</li>
	<li>user_id 为 7771 的用户在 2022-06-14 09:30:00 注册并且在第二天验证。</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双表关联

<!-- thinking:start -->

> **思考**
>
> 要找注册次日即验证成功的用户。两表分别存邮箱与短信，条件分散在连接与日期差上。
>
> 内连 $email\_id$，并要求 $DATEDIFF=1$ 且操作为 `Verified`，一次即可筛出。
>
> 选出 $user\_id$ 并升序排列。无需分组或窗口。

<!-- thinking:end -->

我们可以通过内连接两个表，然后根据 `DATEDIFF` 函数计算出注册日期和操作日期的差值是否等于 1，以及注册操作是否为 `Verified`，来筛选出满足条件的用户 ID。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT user_id
FROM
    Emails AS e
    JOIN texts AS t
        ON e.email_id = t.email_id
        AND DATEDIFF(action_date, signup_date) = 1
        AND signup_action = 'Verified'
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
