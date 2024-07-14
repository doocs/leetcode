---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0619.Biggest%20Single%20Number/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [619. 只出现一次的最大数字](https://leetcode.cn/problems/biggest-single-number)

[English Version](/solution/0600-0699/0619.Biggest%20Single%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>MyNumbers</code> 表：</p>

<div class="original__bRMd">
<div>
<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| num         | int  |
+-------------+------+
该表可能包含重复项（换句话说，在SQL中，该表没有主键）。
这张表的每一行都含有一个整数。
</pre>

<p>&nbsp;</p>

<p><strong>单一数字</strong> 是在 <code>MyNumbers</code> 表中只出现一次的数字。</p>

<p>找出最大的 <strong>单一数字</strong> 。如果不存在 <strong>单一数字</strong> ，则返回&nbsp;<code>null</code> 。</p>

<p>查询结果如下例所示。</p>
<ptable> </ptable>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
MyNumbers 表：
+-----+
| num |
+-----+
| 8   |
| 8   |
| 3   |
| 3   |
| 1   |
| 4   |
| 5   |
| 6   |
+-----+
<strong>输出：</strong>
+-----+
| num |
+-----+
| 6   |
+-----+
<strong>解释：</strong>单一数字有 1、4、5 和 6 。
6 是最大的单一数字，返回 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
MyNumbers table:
+-----+
| num |
+-----+
| 8   |
| 8   |
| 7   |
| 7   |
| 3   |
| 3   |
| 3   |
+-----+
<strong>输出：</strong>
+------+
| num  |
+------+
| null |
+------+
<strong>解释：</strong>输入的表中不存在单一数字，所以返回 null 。
</pre>
</div>
</div>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组 + 子查询

我们可以先将 `MyNumbers` 表按照 `num` 进行分组统计，找出只出现一次的数字，然后使用子查询找出最大的数字即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT MAX(num) AS num
FROM
    (
        SELECT num
        FROM MyNumbers
        GROUP BY 1
        HAVING COUNT(1) = 1
    ) AS t;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：分组 + `CASE` 表达式

与方法一类似，我们可以先将 `MyNumbers` 表按照 `num` 进行分组统计，然后使用 `CASE` 表达式，找出只出现一次的数字，然后按数字降序排序，取第一个即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN COUNT(1) = 1 THEN num
        ELSE NULL
    END AS num
FROM MyNumbers
GROUP BY num
ORDER BY 1 DESC
LIMIT 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
