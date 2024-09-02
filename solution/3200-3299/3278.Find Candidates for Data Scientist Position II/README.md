---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3278. Find Candidates for Data Scientist Position II 🔒](https://leetcode.cn/problems/find-candidates-for-data-scientist-position-ii)

[English Version](/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README_EN.md)

## 题目描述

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

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组统计 + 窗口函数

我们可以将表 `Candidates` 和表 `Projects` 通过 `skill` 列进行等值连接，统计每个候选人在每个项目中匹配的技能数量、总分数，记录在表 `S` 中。

然后我们再次统计每个项目所需的技能数量，记录在表 `T` 中。

接着我们将表 `S` 和表 `T` 通过 `project_id` 列进行等值连接，筛选出匹配的技能数量等于所需技能数量的候选人，记录在表 `P` 中，并计算每个项目的候选人排名，字段为 `rk`。

最后我们筛选出每个项目的排名为 1 的候选人，即为最佳候选人。

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
