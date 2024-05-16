---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1412.Find%20the%20Quiet%20Students%20in%20All%20Exams/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1412. 查找成绩处于中游的学生 🔒](https://leetcode.cn/problems/find-the-quiet-students-in-all-exams)

[English Version](/solution/1400-1499/1412.Find%20the%20Quiet%20Students%20in%20All%20Exams/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Student</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| student_id          | int     |
| student_name        | varchar |
+---------------------+---------+
student_id 是该表主键(具有唯一值的列)。
student_name 学生名字。</pre>

<p>&nbsp;</p>

<p>表: <code>Exam</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| exam_id       | int     |
| student_id    | int     |
| score         | int     |
+---------------+---------+
(exam_id, student_id) 是该表主键(具有唯一值的列的组合)。
学生 student_id 在测验 exam_id 中得分为 score。
</pre>

<p>&nbsp;</p>

<p>成绩处于中游的学生是指至少参加了一次测验,&nbsp;且得分既不是最高分也不是最低分的学生。</p>

<p>编写解决方案，找出在 <strong>所有</strong> 测验中都处于中游的学生 <code>(student_id, student_name)</code>。不要返回从来没有参加过测验的学生。</p>

<p>返回结果表按照&nbsp;<code>student_id</code>&nbsp;排序。</p>

<p>返回结果格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Student 表：
+-------------+---------------+
| student_id  | student_name  |
+-------------+---------------+
| 1           | Daniel        |
| 2           | Jade          |
| 3           | Stella        |
| 4           | Jonathan      |
| 5           | Will          |
+-------------+---------------+
Exam 表：
+------------+--------------+-----------+
| exam_id    | student_id   | score     |
+------------+--------------+-----------+
| 10         |     1        |    70     |
| 10         |     2        |    80     |
| 10         |     3        |    90     |
| 20         |     1        |    80     |
| 30         |     1        |    70     |
| 30         |     3        |    80     |
| 30         |     4        |    90     |
| 40         |     1        |    60     |
| 40         |     2        |    70     |
| 40         |     4        |    80     |
+------------+--------------+-----------+
<strong>输出：</strong>
+-------------+---------------+
| student_id  | student_name  |
+-------------+---------------+
| 2           | Jade          |
+-------------+---------------+
<strong>解释：</strong>
对于测验 1: 学生 1 和 3 分别获得了最低分和最高分。
对于测验 2: 学生 1 既获得了最高分, 也获得了最低分。
对于测验 3 和 4: 学生 1 和 4 分别获得了最低分和最高分。
学生 2 和 5 没有在任一场测验中获得了最高分或者最低分。
因为学生 5 从来没有参加过任何测验, 所以他被排除于结果表。
由此, 我们仅仅返回学生 2 的信息。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用 RANK() 窗口函数 + 分组聚合

我们可以使用 `RANK()` 窗口函数来计算每个学生在每场考试中的正序排名 $rk1$ 和倒序排序 $rk2$，得到表 $T$。

接下来，我们将表 $T$ 与表 $Student$ 进行内连接，然后按照学生编号进行分组聚合，得到每个学生在所有考试中的正序排名为 $1$ 的次数 $cnt1$ 和倒序排名为 $1$ 的次数 $cnt2$。如果 $cnt1$ 和 $cnt2$ 都为 $0$，则说明该学生在所有考试中都处于中游。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            student_id,
            RANK() OVER (
                PARTITION BY exam_id
                ORDER BY score
            ) AS rk1,
            RANK() OVER (
                PARTITION BY exam_id
                ORDER BY score DESC
            ) AS rk2
        FROM Exam
    )
SELECT student_id, student_name
FROM
    T
    JOIN Student USING (student_id)
GROUP BY 1
HAVING SUM(rk1 = 1) = 0 AND SUM(rk2 = 1) = 0
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
