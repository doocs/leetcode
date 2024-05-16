---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2346.Compute%20the%20Rank%20as%20a%20Percentage/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2346. 以百分比计算排名 🔒](https://leetcode.cn/problems/compute-the-rank-as-a-percentage)

[English Version](/solution/2300-2399/2346.Compute%20the%20Rank%20as%20a%20Percentage/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Students</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| student_id    | int  |
| department_id | int  |
| mark          | int  |
+---------------+------+
student_id 包含唯一值。
该表的每一行都表示一个学生的 ID，该学生就读的院系 ID，以及他们的考试分数。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，以百分比的形式报告每个学生在其部门的排名，其中排名的百分比使用以下公式计算:</p>

<p><code>(student_rank_in_the_department - 1) * 100 / (the_number_of_students_in_the_department - 1)</code>。&nbsp;<code>percentage</code> 应该&nbsp;<strong>四舍五入到小数点后两位</strong>。&nbsp;</p>

<p><code>student_rank_in_the_department</code>&nbsp;由<b>&nbsp;</b><code>mark</code>&nbsp;的降序决定，<code>mark</code> 最高的学生是&nbsp; <code>rank 1</code>。如果两个学生得到相同的分数，他们也会得到相同的排名。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Students 表:
+------------+---------------+------+
| student_id | department_id | mark |
+------------+---------------+------+
| 2          | 2             | 650  |
| 8          | 2             | 650  |
| 7          | 1             | 920  |
| 1          | 1             | 610  |
| 3          | 1             | 530  |
+------------+---------------+------+
<strong>输出:</strong> 
+------------+---------------+------------+
| student_id | department_id | percentage |
+------------+---------------+------------+
| 7          | 1             | 0.0        |
| 1          | 1             | 50.0       |
| 3          | 1             | 100.0      |
| 2          | 2             | 0.0        |
| 8          | 2             | 0.0        |
+------------+---------------+------------+
<strong>解释:</strong> 
对于院系 1:
 - 学生 7:percentage = (1 - 1)* 100 / (3 - 1) = 0.0
 - 学生 1:percentage = (2 - 1)* 100 / (3 - 1) = 50.0
 - 学生 3:percentage = (3 - 1)* 100 / (3 - 1) = 100.0
对于院系 2:
 - 学生 2: percentage = (1 - 1) * 100 / (2 - 1) = 0.0
 - 学生 8: percentage = (1 - 1) * 100 / (2 - 1) = 0.0
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

注意空值判断。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    student_id,
    department_id,
    IFNULL(
        ROUND(
            (
                RANK() OVER (
                    PARTITION BY department_id
                    ORDER BY mark DESC
                ) - 1
            ) * 100 / (COUNT(1) OVER (PARTITION BY department_id) - 1),
            2
        ),
        0
    ) AS percentage
FROM Students;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
