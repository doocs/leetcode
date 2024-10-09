---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3278. Find Candidates for Data Scientist Position II 🔒](https://leetcode.com/problems/find-candidates-for-data-scientist-position-ii)

[中文文档](/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README.md)

## Description

<!-- description:start -->

<p>Table: <font face="monospace"><code>Candidates</code></font></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| candidate_id | int     | 
| skill        | varchar |
| proficiency  | int     |
+--------------+---------+
(candidate_id, skill) is the unique key for this table.
Each row includes candidate_id, skill, and proficiency level (1-5).
</pre>

<p>Table: <font face="monospace"><code>Projects</code></font></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| project_id   | int     | 
| skill        | varchar |
| importance   | int     |
+--------------+---------+
(project_id, skill) is the primary key for this table.
Each row includes project_id, required skill, and its importance (1-5) for the project.
</pre>

<p>Leetcode is staffing for multiple data science projects. Write a solution to find the <strong>best candidate</strong> for<strong> each project</strong> based on the following criteria:</p>

<ol>
	<li>Candidates must have <strong>all</strong> the skills required for a project.</li>
	<li>Calculate a <strong>score</strong> for each candidate-project pair as follows:
	<ul>
		<li><strong>Start</strong> with <code>100</code> points</li>
		<li><strong>Add</strong> <code>10</code> points for each skill where <strong>proficiency &gt; importance</strong></li>
		<li><strong>Subtract</strong> <code>5</code> points for each skill where <strong>proficiency &lt; importance</strong></li>
	</ul>
	</li>
</ol>

<p>Include only the top candidate (highest score) for each project. If there&rsquo;s a <strong>tie</strong>, choose the candidate with the <strong>lower</strong> <code>candidate_id</code>. If there is <strong>no suitable candidate</strong> for a project, <strong>do not return</strong>&nbsp;that project.</p>

<p>Return a result table ordered by <code>project_id</code> in ascending order.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>Candidates</code> table:</p>

<pre class="example-io">
+--------------+-----------+-------------+
| candidate_id | skill     | proficiency |
+--------------+-----------+-------------+
| 101          | Python    | 5           |
| 101          | Tableau   | 3           |
| 101          | PostgreSQL| 4           |
| 101          | TensorFlow| 2           |
| 102          | Python    | 4           |
| 102          | Tableau   | 5           |
| 102          | PostgreSQL| 4           |
| 102          | R         | 4           |
| 103          | Python    | 3           |
| 103          | Tableau   | 5           |
| 103          | PostgreSQL| 5           |
| 103          | Spark     | 4           |
+--------------+-----------+-------------+
</pre>

<p><code>Projects</code> table:</p>

<pre class="example-io">
+-------------+-----------+------------+
| project_id  | skill     | importance |
+-------------+-----------+------------+
| 501         | Python    | 4          |
| 501         | Tableau   | 3          |
| 501         | PostgreSQL| 5          |
| 502         | Python    | 3          |
| 502         | Tableau   | 4          |
| 502         | R         | 2          |
+-------------+-----------+------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+--------------+-------+
| project_id  | candidate_id | score |
+-------------+--------------+-------+
| 501         | 101          | 105   |
| 502         | 102          | 130   |
+-------------+--------------+-------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For Project 501, Candidate 101 has the highest score of 105. All other candidates have the same score but Candidate 101 has the lowest candidate_id among them.</li>
	<li>For Project 502, Candidate 102 has the highest score of 130.</li>
</ul>

<p>The output table is ordered by project_id in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Equi-Join + Group Statistics + Window Function

We can perform an equi-join of the `Candidates` table and the `Projects` table on the `skill` column, counting the number of matched skills and calculating the total score for each candidate in each project, which is recorded in table `S`.

Next, we count the required number of skills for each project, recording the results in table `T`.

Then, we perform an equi-join of tables `S` and `T` on the `project_id` column, filtering out candidates whose number of matched skills equals the required number of skills, and recording them in table `P`. We calculate the rank (`rk`) for each candidate within each project.

Finally, we filter out the candidates with rank $rk = 1$ for each project, identifying them as the best candidates.

<!-- tabs:start -->

#### MySQL

```sql
WITH
    S AS (
        SELECT
            candidate_id,
            project_id,
            COUNT(*) matched_skills,
            SUM(
                CASE
                    WHEN proficiency > importance THEN 10
                    WHEN proficiency < importance THEN -5
                    ELSE 0
                END
            ) + 100 AS score
        FROM
            Candidates
            JOIN Projects USING (skill)
        GROUP BY 1, 2
    ),
    T AS (
        SELECT project_id, COUNT(1) required_skills
        FROM Projects
        GROUP BY 1
    ),
    P AS (
        SELECT
            project_id,
            candidate_id,
            score,
            RANK() OVER (
                PARTITION BY project_id
                ORDER BY score DESC, candidate_id
            ) rk
        FROM
            S
            JOIN T USING (project_id)
        WHERE matched_skills = required_skills
    )
SELECT project_id, candidate_id, score
FROM P
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
