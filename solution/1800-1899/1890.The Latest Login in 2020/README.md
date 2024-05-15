---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1890.The%20Latest%20Login%20in%202020/README.md
tags:
    - 数据库
---

# [1890. 2020 年最后一次登录](https://leetcode.cn/problems/the-latest-login-in-2020)

[English Version](/solution/1800-1899/1890.The%20Latest%20Login%20in%202020/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Logins</code></p>

<pre>
+----------------+----------+
| 列名           | 类型      |
+----------------+----------+
| user_id        | int      |
| time_stamp     | datetime |
+----------------+----------+
(user_id, time_stamp) 是这个表的主键(具有唯一值的列的组合)。
每一行包含的信息是user_id 这个用户的登录时间。
</pre>

<p>&nbsp;</p>

<p>编写解决方案以获取在 <code>2020</code> 年登录过的所有用户的本年度 <strong>最后一次 </strong>登录时间。结果集 <strong>不</strong> 包含 <code>2020</code> 年没有登录过的用户。</p>

<p>返回的结果集可以按 <strong>任意顺序 </strong>排列。</p>

<p>返回结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Logins 表:
+---------+---------------------+
| user_id | time_stamp          |
+---------+---------------------+
| 6       | 2020-06-30 15:06:07 |
| 6       | 2021-04-21 14:06:06 |
| 6       | 2019-03-07 00:18:15 |
| 8       | 2020-02-01 05:10:53 |
| 8       | 2020-12-30 00:46:50 |
| 2       | 2020-01-16 02:49:50 |
| 2       | 2019-08-25 07:59:08 |
| 14      | 2019-07-14 09:00:00 |
| 14      | 2021-01-06 11:59:59 |
+---------+---------------------+
<strong>输出：</strong>
+---------+---------------------+
| user_id | last_stamp          |
+---------+---------------------+
| 6       | 2020-06-30 15:06:07 |
| 8       | 2020-12-30 00:46:50 |
| 2       | 2020-01-16 02:49:50 |
+---------+---------------------+
<strong>解释：</strong>
6号用户登录了3次，但是在2020年仅有一次，所以结果集应包含此次登录。
8号用户在2020年登录了2次，一次在2月，一次在12月，所以，结果集应该包含12月的这次登录。
2号用户登录了2次，但是在2020年仅有一次，所以结果集应包含此次登录。
14号用户在2020年没有登录，所以结果集不应包含。</pre>

## 解法

### 方法一：分组求最大值

我们可以先筛选出 2020 年的登录记录，并且按照 `user_id` 分组，然后利用 `max` 函数求出每个用户的最大登录时间。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT user_id, MAX(time_stamp) AS last_stamp
FROM Logins
WHERE YEAR(time_stamp) = 2020
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
