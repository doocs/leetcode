---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3204.Bitwise%20User%20Permissions%20Analysis/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3204. Bitwise User Permissions Analysis 🔒](https://leetcode.com/problems/bitwise-user-permissions-analysis)

[中文文档](/solution/3200-3299/3204.Bitwise%20User%20Permissions%20Analysis/README.md)

## Description

<!-- description:start -->

<p>Table: <code>user_permissions</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| permissions | int     |
+-------------+---------+
user_id is the primary key.
Each row of this table contains the user ID and their permissions encoded as an integer.
</pre>

<p>Consider that each bit in the <code>permissions</code> integer represents a different access level or feature that a user has.</p>

<p>Write a solution to calculate the following:</p>

<ul>
	<li>common_perms: The access level granted to <strong>all users</strong>. This is computed using a <strong>bitwise AND</strong> operation on the <code>permissions</code> column.</li>
	<li>any_perms: The access level granted to <strong>any user</strong>. This is computed using a <strong>bitwise OR</strong> operation on the <code>permissions</code> column.</li>
</ul>

<p>Return <em>the result table in <strong>any</strong> order</em>.</p>

<p>The result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>user_permissions table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------+
| common_perms | any_perms   |
+--------------+-------------+
| 0            | 15          |
+--------------+-------------+
    </pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>common_perms:</strong> Represents the bitwise AND result of all permissions:

    <ul>
    	<li>For user 1 (5): 5 (binary 0101)</li>
    	<li>For user 2 (12): 12 (binary 1100)</li>
    	<li>For user 3 (7): 7 (binary 0111)</li>
    	<li>For user 4 (3): 3 (binary 0011)</li>
    	<li>Bitwise AND: 5 &amp; 12 &amp; 7 &amp; 3 = 0 (binary 0000)</li>
    </ul>
    </li>
    <li><strong>any_perms:</strong> Represents the bitwise OR result of all permissions:
    <ul>
    	<li>Bitwise OR: 5 | 12 | 7 | 3 = 15 (binary 1111)</li>
    </ul>
    </li>

</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bitwise Operations

We can use the `BIT_AND` and `BIT_OR` functions to calculate `common_perms` and `any_perms`.

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
