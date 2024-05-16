---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2989.Class%20Performance/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2989. Class Performance 🔒](https://leetcode.com/problems/class-performance)

[中文文档](/solution/2900-2999/2989.Class%20Performance/README.md)

## Description

<p>Table: <code>Scores</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| assignment1  | int     |
| assignment2  | int     |
| assignment3  | int     |
+--------------+---------+
student_id is column of unique values for this table.
This table contains student_id, student_name, assignment1, assignment2, and assignment3.
</pre>

<p>Write a solution to calculate the <strong>difference</strong> in the <strong>total score</strong> (sum of all <code>3</code> assignments) between the <strong>highest score</strong> obtained by students and the <strong>lowest score</strong> obtained by them.</p>

<p>Return <em>the result table in <strong>any</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Scores table:
+------------+--------------+-------------+-------------+-------------+
| student_id | student_name | assignment1 | assignment2 | assignment3 |
+------------+--------------+-------------+-------------+-------------+
| 309        | Owen         | 88          | 47          | 87          |
| 321        | Claire       | 98          | 95          | 37          |     
| 338        | Julian       | 100         | 64          | 43          |  
| 423        | Peyton       | 60          | 44          | 47          |  
| 896        | David        | 32          | 37          | 50          | 
| 235        | Camila       | 31          | 53          | 69          | 
+------------+--------------+-------------+-------------+-------------+
<strong>Output</strong>
+---------------------+
| difference_in_score | 
+---------------------+
| 111                 | 
+---------------------+
<strong>Explanation</strong>
- student_id 309 has a total score of 88 + 47 + 87 = 222.
- student_id 321 has a total score of 98 + 95 + 37 = 230.
- student_id 338 has a total score of 100 + 64 + 43 = 207.
- student_id 423 has a total score of 60 + 44 + 47 = 151.
- student_id 896 has a total score of 32 + 37 + 50 = 119.
- student_id 235 has a total score of 31 + 53 + 69 = 153.
student_id 321 has the highest score of 230, while student_id 896 has the lowest score of 119. Therefore, the difference between them is 111.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: Maximum and Minimum

We can use the `MAX` and `MIN` functions to get the maximum and minimum sums of `assignment1`, `assignment2`, and `assignment3`, respectively. Then, subtract the minimum from the maximum.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    MAX(assignment1 + assignment2 + assignment3) - MIN(
        assignment1 + assignment2 + assignment3
    ) AS difference_in_score
FROM Scores;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
