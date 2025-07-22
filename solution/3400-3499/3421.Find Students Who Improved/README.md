---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3421.Find%20Students%20Who%20Improved/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3421. 查找进步的学生](https://leetcode.cn/problems/find-students-who-improved)

[English Version](/solution/3400-3499/3421.Find%20Students%20Who%20Improved/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Scores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student_id  | int     |
| subject     | varchar |
| score       | int     |
| exam_date   | varchar |
+-------------+---------+
(student_id, subject, exam_date) 是这张表的主键。
每一行包含有关学生在特定考试日期特定科目成绩的信息。分数范围从 0 到 100（包括边界）。
</pre>

<p>编写一个解决方案来查找 <strong>进步的学生</strong>。如果 <strong>同时</strong> 满足以下两个条件，则该学生被认为是进步的：</p>

<ul>
	<li>在 <strong>同一科目</strong> 至少参加过两个不同日期的考试。</li>
	<li>他们在该学科<strong> 最近的分数 </strong>比他们 第一次该学科考试的分数更高。</li>
</ul>

<p>返回结果表以&nbsp;<code>student_id</code>，<code>subject</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Scores 表：</p>

<pre>
+------------+----------+-------+------------+
| student_id | subject  | score | exam_date  |
+------------+----------+-------+------------+
| 101        | Math     | 70    | 2023-01-15 |
| 101        | Math     | 85    | 2023-02-15 |
| 101        | Physics  | 65    | 2023-01-15 |
| 101        | Physics  | 60    | 2023-02-15 |
| 102        | Math     | 80    | 2023-01-15 |
| 102        | Math     | 85    | 2023-02-15 |
| 103        | Math     | 90    | 2023-01-15 |
| 104        | Physics  | 75    | 2023-01-15 |
| 104        | Physics  | 85    | 2023-02-15 |
+------------+----------+-------+------------+</pre>

<p><strong>出：</strong></p>

<pre class="example-io">
+------------+----------+-------------+--------------+
| student_id | subject  | first_score | latest_score |
+------------+----------+-------------+--------------+
| 101        | Math     | 70          | 85           |
| 102        | Math     | 80          | 85           |
| 104        | Physics  | 75          | 85           |
+------------+----------+-------------+--------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>学生 101 的数学：从 70 分进步到 85 分。</li>
	<li>学生 101 的物理：没有进步（从 65 分退步到 60分）</li>
	<li>学生 102 的数学：从 80 进步到 85 分。</li>
	<li>学生 103 的数学：只有一次考试，不符合资格。</li>
	<li>学生 104 的物理：从 75 分进步到 85 分。</li>
</ul>

<p>结果表以 student_id，subject 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + 子连接 + 条件过滤

首先，我们使用窗口函数 `ROW_NUMBER()` 计算每个学生在每个科目中的考试日期的排名，分别计算出每个学生在每个科目中的第一次考试和最近一次考试的排名。

然后，我们使用子连接 `JOIN` 操作将第一次考试和最近一次考试的分数连接在一起，最后根据题目要求筛选出最近一次考试的分数比第一次考试的分数高的学生。

<!-- tabs:start -->

#### MySQL

```sql
WITH
    RankedScores AS (
        SELECT
            student_id,
            subject,
            score,
            exam_date,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, subject
                ORDER BY exam_date ASC
            ) AS rn_first,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, subject
                ORDER BY exam_date DESC
            ) AS rn_latest
        FROM Scores
    ),
    FirstAndLatestScores AS (
        SELECT
            f.student_id,
            f.subject,
            f.score AS first_score,
            l.score AS latest_score
        FROM
            RankedScores f
            JOIN RankedScores l ON f.student_id = l.student_id AND f.subject = l.subject
        WHERE f.rn_first = 1 AND l.rn_latest = 1
    )
SELECT
    *
FROM FirstAndLatestScores
WHERE latest_score > first_score
ORDER BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
