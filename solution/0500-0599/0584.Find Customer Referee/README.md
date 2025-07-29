---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0584.Find%20Customer%20Referee/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [584. 寻找用户推荐人](https://leetcode.cn/problems/find-customer-referee)

[English Version](/solution/0500-0599/0584.Find%20Customer%20Referee/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Customer</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| referee_id  | int     |
+-------------+---------+
在 SQL 中，id 是该表的主键列。
该表的每一行表示一个客户的 id、姓名以及推荐他们的客户的 id。</pre>

<p>找出以下客户的姓名：</p>

<ol>
	<li><strong>被任何</strong>&nbsp;<code>id != 2</code>&nbsp;的用户推荐。</li>
	<li><strong>没有被</strong>&nbsp;任何用户推荐。</li>
</ol>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b> 
Customer 表:
+----+------+------------+
| id | name | referee_id |
+----+------+------------+
| 1  | Will | null       |
| 2  | Jane | null       |
| 3  | Alex | 2          |
| 4  | Bill | null       |
| 5  | Zack | 1          |
| 6  | Mark | 2          |
+----+------+------------+
<b>输出：</b>
+------+
| name |
+------+
| Will |
| Jane |
| Bill |
| Zack |
+------+</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：条件过滤

我们可以直接筛选出 `referee_id` 不为 `2` 的客户姓名。注意，`referee_id` 为 `NULL` 的客户也应该被筛选出来。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT name
FROM Customer
WHERE IFNULL(referee_id, 0) != 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
