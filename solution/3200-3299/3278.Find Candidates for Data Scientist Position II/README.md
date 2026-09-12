---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3278. 寻找数据科学家职位的候选人 II 🔒](https://leetcode.cn/problems/find-candidates-for-data-scientist-position-ii)

[English Version](/solution/3200-3299/3278.Find%20Candidates%20for%20Data%20Scientist%20Position%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Candidates</code></font></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| candidate_id | int     | 
| skill        | varchar |
| proficiency  | int     |
+--------------+---------+
是这张表的主键（有不同值的列）。 
每一行包括 candidate_id 和技能，以及熟练程度（1-5）。
</pre>

<p>表：<font face="monospace"><code>Projects</code></font></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| project_id   | int     | 
| skill        | varchar |
| importance   | int     |
+--------------+---------+
(project_id, skill) 是这张表的主键。
每一行包括 project_id，所需技能，以及项目的重要性（1-5）。
</pre>

<p>Leetcode 正在为多个数据科学项目招聘人员。编写一个解决方案来根据以下条件为 <strong>每一个项目</strong> 找到 <strong>最佳候选人</strong>：</p>

<ol>
	<li>候选人必须拥有项目所需的 <strong>所有</strong>&nbsp;技能。</li>
	<li>为每个候选人-项目对计算如下的 <strong>分数</strong>：
	<ul>
		<li>从&nbsp;<code>100</code>&nbsp;分<strong>&nbsp;开始。</strong></li>
		<li>对于每一个技能，当 <b>熟练程度 &gt; 重要性 加</b>&nbsp;<code>10</code>&nbsp;分。</li>
		<li>对于每一个技能，当&nbsp;<strong>熟练程度 &lt; 重要性 减</strong>&nbsp;<code>5</code>&nbsp;分。</li>
		<li>如果候选人的技能熟练程度 <strong>等于</strong> 项目的技能重要性，则分数保持不变</li>
	</ul>
	</li>
</ol>

<p>仅包括每个项目的最佳候选人（最高分）。如果 <strong>相同</strong>，选择有 <strong>更小</strong>&nbsp;<code>candidate_id</code>&nbsp;的候选人。如果一个项目 <strong>没有适合的候选人</strong>，<strong>不要返回 </strong>那个项目。</p>

<p>返回结果表以&nbsp;<code>project_id</code>&nbsp;升序排序。</p>

<p>输出格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>Candidates</code> 表：</p>

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

<p><code>Projects</code> 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------+-------+
| project_id  | candidate_id | score |
+-------------+--------------+-------+
| 501         | 101          | 105   |
| 502         | 102          | 130   |
+-------------+--------------+-------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于项目 501, 候选人 101 有最高的 105 分。所有其他的候选人有相同的分数，但候选人 101 有比他们更小的 candidate_id。</li>
	<li>对于项目 502，候选人&nbsp;102 有最高的 130&nbsp;分。</li>
</ul>

<p>输出表以 project_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组统计 + 窗口函数

<!-- thinking:start -->

> **思考**
>
> 候选人必须覆盖项目全部技能，分数由熟练度与重要性比较加减后再加 $100$，每项目取分数最高且编号较小者。连接后分组即可同时得到匹配数与分数。
>
> `Candidates` 与 `Projects` 按技能等值连接，分组得到匹配数与分数；再与项目所需技能数对齐，筛「匹配数相等」者，窗口按分数、编号排名，取 $rk=1$。

<!-- thinking:end -->

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
