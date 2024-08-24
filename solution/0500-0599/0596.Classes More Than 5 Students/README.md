---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0596.Classes%20More%20Than%205%20Students/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [596. 超过 5 名学生的课](https://leetcode.cn/problems/classes-more-than-5-students)

[English Version](/solution/0500-0599/0596.Classes%20More%20Than%205%20Students/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Courses</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student     | varchar |
| class       | varchar |
+-------------+---------+
(student, class)是该表的主键（不同值的列的组合）。
该表的每一行表示学生的名字和他们注册的班级。
</pre>

<p>&nbsp;</p>

<p>查询&nbsp;<strong>至少有 5 个学生</strong> 的所有班级。</p>

<p>以 <strong>任意顺序 </strong>返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Courses table:
+---------+----------+
| student | class    |
+---------+----------+
| A       | Math     |
| B       | English  |
| C       | Math     |
| D       | Biology  |
| E       | Math     |
| F       | Computer |
| G       | Math     |
| H       | Math     |
| I       | Math     |
+---------+----------+
<strong>输出:</strong> 
+---------+ 
| class &nbsp; | 
+---------+ 
| Math &nbsp; &nbsp;| 
+---------+
<strong>解释: </strong>
-数学课有 6 个学生，所以我们包括它。
-英语课有 1 名学生，所以我们不包括它。
-生物课有 1 名学生，所以我们不包括它。
-计算机课有 1 个学生，所以我们不包括它。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们可以使用 `GROUP BY` 语句，按照班级分组，然后使用 `HAVING` 语句，筛选出学生数量大于等于 $5$ 的班级。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT class
FROM Courses
GROUP BY 1
HAVING COUNT(1) >= 5;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
