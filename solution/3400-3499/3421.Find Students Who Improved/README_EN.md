---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3421.Find%20Students%20Who%20Improved/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3421. Find Students Who Improved](https://leetcode.com/problems/find-students-who-improved)

[中文文档](/solution/3400-3499/3421.Find%20Students%20Who%20Improved/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Scores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student_id  | int     |
| subject     | varchar |
| score       | int     |
| exam_date   | varchar |
+-------------+---------+
(student_id, subject, exam_date) is the primary key for this table.
Each row contains information about a student&#39;s score in a specific subject on a particular exam date. score is between 0 and 100 (inclusive).
</pre>

<p>Write a solution to find the <strong>students who have shown improvement</strong>. A student is considered to have shown improvement if they meet <strong>both</strong> of these conditions:</p>

<ul>
	<li>Have taken exams in the <strong>same subject</strong> on at least two different dates</li>
	<li>Their <strong>latest score</strong> in that subject is <strong>higher</strong> than their <strong>first score</strong></li>
</ul>

<p>Return <em>the result table</em>&nbsp;<em>ordered by</em> <code>student_id,</code> <code>subject</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Scores table:</p>

<pre class="example-io">
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
+------------+----------+-------+------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+----------+-------------+--------------+
| student_id | subject  | first_score | latest_score |
+------------+----------+-------------+--------------+
| 101        | Math     | 70          | 85           |
| 102        | Math     | 80          | 85           |
| 104        | Physics  | 75          | 85           |
+------------+----------+-------------+--------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Student 101 in Math: Improved from 70 to 85</li>
	<li>Student 101 in Physics: No improvement (dropped from 65 to 60)</li>
	<li>Student 102 in Math: Improved from 80 to 85</li>
	<li>Student 103 in Math: Only one exam, not eligible</li>
	<li>Student 104 in Physics: Improved from 75 to 85</li>
</ul>

<p>Result table is ordered by student_id, subject.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Function + Subquery + Conditional Filtering

First, we use the window function `ROW_NUMBER()` to calculate the ranking of each student's exam date in each subject, separately calculating the first and most recent exam rankings for each student in each subject.

Then, we use a subquery `JOIN` operation to join the scores of the first and most recent exams together. Finally, we filter out the students whose most recent exam scores are higher than their first exam scores according to the problem requirements.

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
