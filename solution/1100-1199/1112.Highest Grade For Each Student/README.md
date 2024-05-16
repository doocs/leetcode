---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1112.Highest%20Grade%20For%20Each%20Student/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1112. 每位学生的最高成绩 🔒](https://leetcode.cn/problems/highest-grade-for-each-student)

[English Version](/solution/1100-1199/1112.Highest%20Grade%20For%20Each%20Student/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Enrollments</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| course_id     | int     |
| grade         | int     |
+---------------+---------+
(student_id, course_id) 是该表的主键（具有唯一值的列的组合）。
grade 不会为 NULL。</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出每位学生获得的最高成绩和它所对应的科目，若科目成绩并列，取&nbsp;<code>course_id</code>&nbsp;最小的一门。查询结果需按&nbsp;<code>student_id</code>&nbsp;增序进行排序。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Enrollments 表：
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 2          | 2         | 95    |
| 2          | 3         | 95    |
| 1          | 1         | 90    |
| 1          | 2         | 99    |
| 3          | 1         | 80    |
| 3          | 2         | 75    |
| 3          | 3         | 82    |
+------------+-----------+-------+
<strong>输出：</strong>
+------------+-------------------+
| student_id | course_id | grade |
+------------+-----------+-------+
| 1          | 2         | 99    |
| 2          | 2         | 95    |
| 3          | 3         | 82    |
+------------+-----------+-------+</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：RANK() OVER() 窗口函数

我们可以使用 `RANK() OVER()` 窗口函数，按照每个学生的成绩降序排列，如果成绩相同，按照课程号升序排列，然后取每个学生排名为 $1$ 的记录。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY student_id
                ORDER BY grade DESC, course_id
            ) AS rk
        FROM Enrollments
    )
SELECT student_id, course_id, grade
FROM T
WHERE rk = 1
ORDER BY student_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：子查询

我们可以先查询每个学生的最高成绩，然后再查询每个学生的最高成绩对应的最小课程号。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT student_id, MIN(course_id) AS course_id, grade
FROM Enrollments
WHERE
    (student_id, grade) IN (
        SELECT student_id, MAX(grade) AS grade
        FROM Enrollments
        GROUP BY 1
    )
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
