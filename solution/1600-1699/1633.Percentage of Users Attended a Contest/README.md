# [1633. 各赛事的用户注册率](https://leetcode.cn/problems/percentage-of-users-attended-a-contest)

[English Version](/solution/1600-1699/1633.Percentage%20of%20Users%20Attended%20a%20Contest/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用户表：&nbsp;<code>Users</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| user_name   | varchar |
+-------------+---------+
user_id 是该表的主键。
该表中的每行包括用户 ID 和用户名。</pre>

<p>&nbsp;</p>

<p>注册表：&nbsp;<code>Register</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| contest_id  | int     |
| user_id     | int     |
+-------------+---------+
(contest_id, user_id) 是该表的主键。
该表中的每行包含用户的 ID 和他们注册的赛事。</pre>

<p>&nbsp;</p>

<p>写一条 SQL 语句，查询各赛事的用户注册百分率，保留两位小数。</p>

<p>返回的结果表按&nbsp;<code>percentage</code>&nbsp;的&nbsp;<strong>降序&nbsp;</strong>排序，若相同则按&nbsp;<code>contest_id</code>&nbsp;的&nbsp;<strong>升序&nbsp;</strong>排序。</p>

<p>查询结果如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>
Users</code> 表：
+---------+-----------+
| user_id | user_name |
+---------+-----------+
| 6       | Alice     |
| 2       | Bob       |
| 7       | Alex      |
+---------+-----------+

<code>Register</code> 表：
+------------+---------+
| contest_id | user_id |
+------------+---------+
| 215        | 6       |
| 209        | 2       |
| 208        | 2       |
| 210        | 6       |
| 208        | 6       |
| 209        | 7       |
| 209        | 6       |
| 215        | 7       |
| 208        | 7       |
| 210        | 2       |
| 207        | 2       |
| 210        | 7       |
+------------+---------+
<strong>输出：</strong>
+------------+------------+
| contest_id | percentage |
+------------+------------+
| 208        | 100.0      |
| 209        | 100.0      |
| 210        | 100.0      |
| 215        | 66.67      |
| 207        | 33.33      |
+------------+------------+
<strong>解释：</strong>
所有用户都注册了 208、209 和 210 赛事，因此这些赛事的注册率为 100% ，我们按 contest_id 的降序排序加入结果表中。
Alice 和 Alex 注册了 215 赛事，注册率为 ((2/3) * 100) = 66.67%
Bob 注册了 207 赛事，注册率为 ((1/3) * 100) = 33.33%</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
