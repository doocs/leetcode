---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0627.Swap%20Sex%20of%20Employees/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [627. 变更性别](https://leetcode.cn/problems/swap-sex-of-employees)

[English Version](/solution/0600-0699/0627.Swap%20Sex%20of%20Employees/README_EN.md)

## 题目描述

<!-- description:start -->

<div class="original__bRMd">
<div>
<p><code>Salary</code> 表：</p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| name        | varchar  |
| sex         | ENUM     |
| salary      | int      |
+-------------+----------+
id 是这个表的主键（具有唯一值的列）。
sex 这一列的值是 ENUM 类型，只能从 ('m', 'f') 中取。
本表包含公司雇员的信息。
</pre>

<p>&nbsp;</p>

<p>请你编写一个解决方案来交换所有的 <code>'f'</code> 和 <code>'m'</code> （即，将所有 <code>'f'</code> 变为 <code>'m'</code> ，反之亦然），仅使用 <strong>单个 update 语句</strong> ，且不产生中间临时表。</p>

<p>注意，你必须仅使用一条 update 语句，且 <strong>不能</strong> 使用 select 语句。</p>

<p>结果如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Salary 表：
+----+------+-----+--------+
| id | name | sex | salary |
+----+------+-----+--------+
| 1  | A    | m   | 2500   |
| 2  | B    | f   | 1500   |
| 3  | C    | m   | 5500   |
| 4  | D    | f   | 500    |
+----+------+-----+--------+
<strong>输出：</strong>
+----+------+-----+--------+
| id | name | sex | salary |
+----+------+-----+--------+
| 1  | A    | f   | 2500   |
| 2  | B    | m   | 1500   |
| 3  | C    | f   | 5500   |
| 4  | D    | m   | 500    |
+----+------+-----+--------+
<strong>解释：</strong>
(1, A) 和 (3, C) 从 'm' 变为 'f' 。
(2, B) 和 (4, D) 从 'f' 变为 'm' 。</pre>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用单个 update 语句交换性别

根据题目要求，我们只需要使用一条 update 语句来交换所有员工的性别。我们可以使用 SQL 中的条件表达式来实现这一点。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
UPDATE Salary
SET sex = IF(sex = 'f', 'm', 'f');
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
