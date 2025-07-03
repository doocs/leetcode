---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1412.Find%20the%20Quiet%20Students%20in%20All%20Exams/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1412. Find the Quiet Students in All Exams 🔒](https://leetcode.com/problems/find-the-quiet-students-in-all-exams)

[中文文档](/solution/1400-1499/1412.Find%20the%20Quiet%20Students%20in%20All%20Exams/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Student</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| student_id          | int     |
| student_name        | varchar |
+---------------------+---------+
student_id is the primary key (column with unique values) for this table.
student_name is the name of the student.</pre>

<p>&nbsp;</p>

<p>Table: <code>Exam</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| exam_id       | int     |
| student_id    | int     |
| score         | int     |
+---------------+---------+
(exam_id, student_id) is the primary key (combination of columns with unique values) for this table.
Each row of this table indicates that the student with student_id had a score points in the exam with id exam_id.
</pre>

<p>&nbsp;</p>

<p>A <strong>quiet student</strong> is the one who took at least one exam and did not score the highest or the lowest score.</p>

<p>Write a solution&nbsp;to report the students <code>(student_id, student_name)</code> being quiet in all exams. Do not return the student who has never taken any exam.</p>

<p>Return the result table <strong>ordered</strong> by <code>student_id</code>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Student table:
+-------------+---------------+
| student_id  | student_name  |
+-------------+---------------+
| 1           | Daniel        |
| 2           | Jade          |
| 3           | Stella        |
| 4           | Jonathan      |
| 5           | Will          |
+-------------+---------------+
Exam table:
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
<strong>Output:</strong> 
+-------------+---------------+
| student_id  | student_name  |
+-------------+---------------+
| 2           | Jade          |
+-------------+---------------+
<strong>Explanation:</strong> 
For exam 1: Student 1 and 3 hold the lowest and high scores respectively.
For exam 2: Student 1 hold both highest and lowest score.
For exam 3 and 4: Student 1 and 4 hold the lowest and high scores respectively.
Student 2 and 5 have never got the highest or lowest in any of the exams.
Since student 5 is not taking any exam, he is excluded from the result.
So, we only return the information of Student 2.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Using RANK() Window Function + Group By

We can use the `RANK()` window function to calculate the ascending rank $rk1$ and descending rank $rk2$ of each student in each exam, and obtain the table $T$.

Next, we can perform an inner join between the table $T$ and the table $Student$, and then group by student ID to obtain the number of times each student has a rank of $1$ in ascending order $cnt1$ and descending order $cnt2$ in all exams. If both $cnt1$ and $cnt2$ are $0$, it means that the student is in the middle of the pack in all exams.

<!-- tabs:start -->

#### MySQL

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
