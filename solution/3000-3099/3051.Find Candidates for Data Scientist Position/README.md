---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3051.Find%20Candidates%20for%20Data%20Scientist%20Position/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3051. 寻找数据科学家职位的候选人 🔒](https://leetcode.cn/problems/find-candidates-for-data-scientist-position)

[English Version](/solution/3000-3099/3051.Find%20Candidates%20for%20Data%20Scientist%20Position/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Candidates</code></font></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| candidate_id | int     | 
| skill        | varchar |
+--------------+---------+
(candidate_id, skill) 是这张表的主键（有不同值的列）。
每一行包括 candidate_id 和 skill。
</pre>

<p>编写一个查询来找到最适合数据科学家职位的 <strong>候选人</strong>。应聘者必须精通 <strong>Python</strong>，<strong>Tableau </strong>和&nbsp;<strong>PostgreSQL</strong>。</p>

<p>返回结果表，以<em>&nbsp;</em><code>candidate_id</code>&nbsp;<strong>升序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Candidates 表：
+---------------+--------------+
| candidate_id  | skill        | 
+---------------+--------------+
| 123           | Python       |
| 234           | R            | 
| 123           | Tableau      | 
| 123           | PostgreSQL   | 
| 234           | PowerBI      | 
| 234           | SQL Server   | 
| 147           | Python       | 
| 147           | Tableau      | 
| 147           | Java         |
| 147           | PostgreSQL   |
| 256           | Tableau      |
| 102           | DataAnalysis |
+---------------+--------------+
<strong>输出：</strong> 
+--------------+
| candidate_id |  
+--------------+
| 123          |  
| 147          | 
+--------------+
<strong>解释：</strong> 
- 候选人 123 和 147 具备数据科学家职位必要的 Python，Tableau 和 PostgreSQL 技能。
- 候选人 234 和 102 不具备该职位所需的任何技能。
- 候选人 256 精通 Tableau 但没有掌握 Python 和 PostgreSQL。
输出表以 candidate_id 升序排序。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：条件筛选 + 分组统计

我们首先筛选出具备 `Python`, `Tableau`, `PostgreSQL` 这三个技能的候选人，然后按照 `candidate_id` 进行分组统计，统计每个候选人具备的技能数量，最后筛选出具备这三个技能的候选人，并且按照 `candidate_id` 进行升序排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT candidate_id
FROM Candidates
WHERE skill IN ('Python', 'Tableau', 'PostgreSQL')
GROUP BY 1
HAVING COUNT(1) = 3
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
