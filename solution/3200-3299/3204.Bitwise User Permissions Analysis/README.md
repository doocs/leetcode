---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3204.Bitwise%20User%20Permissions%20Analysis/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3204. 按位用户权限分析 🔒](https://leetcode.cn/problems/bitwise-user-permissions-analysis)

[English Version](/solution/3200-3299/3204.Bitwise%20User%20Permissions%20Analysis/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>user_permissions</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| permissions | int     |
+-------------+---------+
user_id 是主键。
这张表的每一行包含用户 ID 和他们的权限，编码为一个整数。
</pre>

<p><code>permissions</code>&nbsp;整数中的每一个二进制位代表一个用户拥有的一个不同的访问级别或功能。</p>

<p>编写一个解决方案来计算以下内容：</p>

<ul>
	<li>common_perms：授予 <strong>所有用户</strong> 的访问级别。在&nbsp;<code>permissions</code>&nbsp;列上使用 <strong>按位与&nbsp;</strong>操作来计算。</li>
	<li>any_perms：授予 <strong>任一用户</strong> 的访问级别。在&nbsp;<code>permissions</code>&nbsp;列上使用 <strong>按位或 </strong>操作来计算。</li>
</ul>

<p>以&nbsp;<strong>任意&nbsp;</strong>顺序返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>user_permissions 表：</p>

<pre class="example-io">
+---------+-------------+
| user_id | permissions |
+---------+-------------+
| 1       | 5           |
| 2       | 12          |
| 3       | 7           |
| 4       | 3           |
+---------+-------------+
 </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------+
| common_perms | any_perms   |
+--------------+-------------+
| 0            | 15          |
+--------------+-------------+
    </pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>common_perms:</strong> 代表所有权限的按位与结果：

    <ul>
    	<li>对于用户 1 (5): 5 (二进制 0101)</li>
    	<li>对于用户 2 (12): 12 (二进制 1100)</li>
    	<li>对于用户 3 (7): 7 (二进制 0111)</li>
    	<li>对于用户 4 (3): 3 (二进制 0011)</li>
    	<li>按位与：5 &amp; 12 &amp; 7 &amp; 3 = 0 (二进制 0000)</li>
    </ul>
    </li>
    <li><strong>any_perms:</strong> 代表所有权限的按位或结果：
    <ul>
    	<li>按位或：5 | 12 | 7 | 3 = 15 (二进制 1111)</li>
    </ul>
    </li>

</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们可以使用 `BIT_AND` 和 `BIT_OR` 函数来计算 `common_perms` 和 `any_perms`。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    BIT_AND(permissions) AS common_perms,
    BIT_OR(permissions) AS any_perms
FROM user_permissions;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
