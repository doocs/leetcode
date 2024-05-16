---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2004. The Number of Seniors and Juniors to Join the Company 🔒](https://leetcode.com/problems/the-number-of-seniors-and-juniors-to-join-the-company)

[中文文档](/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README.md)

## Description

<p>Table: <code>Candidates</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| employee_id | int  |
| experience  | enum |
| salary      | int  |
+-------------+------+
employee_id is the column with unique values for this table.
experience is an ENUM (category) type of values (&#39;Senior&#39;, &#39;Junior&#39;).
Each row of this table indicates the id of a candidate, their monthly salary, and their experience.
</pre>

<p>&nbsp;</p>

<p>A company wants to hire new employees. The budget of the company for the salaries is <code>$70000</code>. The company&#39;s criteria for hiring are:</p>

<ol>
	<li>Hiring the largest number of seniors.</li>
	<li>After hiring the maximum number of seniors, use the remaining budget to hire the largest number of juniors.</li>
</ol>

<p>Write a solution to find the number of seniors and juniors hired under the mentioned criteria.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 20000  |
| 11          | Senior     | 20000  |
| 13          | Senior     | 50000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>Output:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 2                   |
| Junior     | 2                   |
+------------+---------------------+
<strong>Explanation:</strong> 
We can hire 2 seniors with IDs (2, 11). Since the budget is $70000 and the sum of their salaries is $40000, we still have $30000 but they are not enough to hire the senior candidate with ID 13.
We can hire 2 juniors with IDs (1, 9). Since the remaining budget is $30000 and the sum of their salaries is $20000, we still have $10000 but they are not enough to hire the junior candidate with ID 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 80000  |
| 11          | Senior     | 80000  |
| 13          | Senior     | 80000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>Output:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 0                   |
| Junior     | 3                   |
+------------+---------------------+
<strong>Explanation:</strong> 
We cannot hire any seniors with the current budget as we need at least $80000 to hire one senior.
We can hire all three juniors with the remaining budget.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    s AS (
        SELECT
            employee_id,
            SUM(salary) OVER (ORDER BY salary) AS cur
        FROM Candidates
        WHERE experience = 'Senior'
    ),
    j AS (
        SELECT
            employee_id,
            IFNULL(
                SELECT
                    MAX(cur)
                FROM s
                WHERE cur <= 70000,
                0
            ) + SUM(salary) OVER (ORDER BY salary) AS cur
        FROM Candidates
        WHERE experience = 'Junior'
    )
SELECT
    'Senior' AS experience,
    COUNT(employee_id) AS accepted_candidates
FROM s
WHERE cur <= 70000
UNION ALL
SELECT
    'Junior' AS experience,
    COUNT(employee_id) AS accepted_candidates
FROM j
WHERE cur <= 70000;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
