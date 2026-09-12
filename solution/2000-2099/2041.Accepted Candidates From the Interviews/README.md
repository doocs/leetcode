---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2041.Accepted%20Candidates%20From%20the%20Interviews/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2041. 面试中被录取的候选人 🔒](https://leetcode.cn/problems/accepted-candidates-from-the-interviews)

[English Version](/solution/2000-2099/2041.Accepted%20Candidates%20From%20the%20Interviews/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Candidates</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| candidate_id | int      |
| name         | varchar  |
| years_of_exp | int      |
| interview_id | int      |
+--------------+----------+
candidate_id 是这个表的主键（具有唯一值的列）。
该表的每一行都表示候选人的姓名、工作年限以及面试 ID 。
</pre>

<p>&nbsp;</p>

<p>表：<code>Rounds</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| interview_id | int  |
| round_id     | int  |
| score        | int  |
+--------------+------+
(interview_id, round_id）是本表的主键（具有唯一值的列的组合）。
本表的每一行都表示一轮面试的分数
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出 <strong>至少有两年</strong> 工作经验、且面试分数之和 <strong>严格大于 <code>15</code>&nbsp;</strong>的候选人的 ID<strong> 。</strong></p>

<p>可以以 <strong>任何顺序 </strong>返回结果表。</p>

<p>查询结果的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Candidates table:
+--------------+---------+--------------+--------------+
| candidate_id | name    | years_of_exp | interview_id |
+--------------+---------+--------------+--------------+
| 11           | Atticus | 1            | 101          |
| 9            | Ruben   | 6            | 104          |
| 6            | Aliza   | 10           | 109          |
| 8            | Alfredo | 0            | 107          |
+--------------+---------+--------------+--------------+
Rounds table:
+--------------+----------+-------+
| interview_id | round_id | score |
+--------------+----------+-------+
| 109          | 3        | 4     |
| 101          | 2        | 8     |
| 109          | 4        | 1     |
| 107          | 1        | 3     |
| 104          | 3        | 6     |
| 109          | 1        | 4     |
| 104          | 4        | 7     |
| 104          | 1        | 2     |
| 109          | 2        | 1     |
| 104          | 2        | 7     |
| 107          | 2        | 3     |
| 101          | 1        | 8     |
+--------------+----------+-------+
<strong>输出：</strong>
+--------------+
| candidate_id |
+--------------+
| 9            |
+--------------+
<strong>解释：</strong>
- 候选人 11 ：总分是 16 ，1 年工作经验。由于工作年限，不列入结果表。
- 候选人 9 ：总分是 22 ，6 年工作经验。列入结果表。
- 候选人 6 ：总分是 10 ，10 年工作经验。由于分数不足，不列入结果表。
- 候选人 8 ：总分是 6 ，0 年工作经验。由于工作年限和分数，不列入结果表。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：连接表 + 分组 + 过滤

<!-- thinking:start -->

> **思考**
>
> 录取条件是工龄至少两年且各轮分数之和大于 $15$。候选人与轮次通过 `interview_id` 相连。
>
> 先过滤工龄，再按 `candidate_id` 分组求和，`HAVING` 留下总分超 $15$ 者。SQL 与 pandas 路径同一聚合。

<!-- thinking:end -->

我们可以将 `Candidates` 表和 `Rounds` 表按照 `interview_id` 进行连接，筛选出工作年限至少为 2 年的候选人，然后按照 `candidate_id` 进行分组，计算每个候选人的总分，最后筛选出总分大于 15 分的候选人。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT candidate_id
FROM
    Candidates
    JOIN Rounds USING (interview_id)
WHERE years_of_exp >= 2
GROUP BY 1
HAVING SUM(score) > 15;
```

#### Pandas

```python
import pandas as pd


def accepted_candidates(candidates: pd.DataFrame, rounds: pd.DataFrame) -> pd.DataFrame:
    merged_df = pd.merge(candidates, rounds, on="interview_id")
    filtered_df = merged_df[merged_df["years_of_exp"] >= 2]
    grouped_df = filtered_df.groupby("candidate_id").agg({"score": "sum"})
    return grouped_df[grouped_df["score"] > 15].reset_index()[["candidate_id"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
