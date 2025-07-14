---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3617.Find%20Students%20with%20Study%20Spiral%20Pattern/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3617. Find Students with Study Spiral Pattern](https://leetcode.cn/problems/find-students-with-study-spiral-pattern)

[English Version](/solution/3600-3699/3617.Find%20Students%20with%20Study%20Spiral%20Pattern/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>students</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| major        | varchar |
+--------------+---------+
student_id is the unique identifier for this table.
Each row contains information about a student and their academic major.
</pre>

<p>Table: <code>study_sessions</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| session_id    | int     |
| student_id    | int     |
| subject       | varchar |
| session_date  | date    |
| hours_studied | decimal |
+---------------+---------+
session_id is the unique identifier for this table.
Each row represents a study session by a student for a specific subject.
</pre>

<p>Write a solution to find students who follow the <strong>Study Spiral Pattern</strong>&nbsp;- students who consistently study multiple subjects in a rotating cycle.</p>

<ul>
	<li>A Study Spiral Pattern means a student studies at least <code>3</code><strong> different subjects</strong> in a repeating sequence</li>
	<li>The pattern must repeat for <strong>at least </strong><code>2</code><strong> complete cycles</strong> (minimum <code>6</code> study sessions)</li>
	<li>Sessions must be <strong>consecutive dates</strong> with no gaps longer than <code>2</code> days between sessions</li>
	<li>Calculate the <strong>cycle length</strong> (number of different subjects in the pattern)</li>
	<li>Calculate the <strong>total study hours</strong> across all sessions in the pattern</li>
	<li>Only include students with cycle length of <strong>at least </strong><code>3</code><strong> subjects</strong></li>
</ul>

<p>Return <em>the result table ordered by cycle length in <strong>descending</strong> order, then by total study hours in <strong>descending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>students table:</p>

<pre class="example-io">
+------------+--------------+------------------+
| student_id | student_name | major            |
+------------+--------------+------------------+
| 1          | Alice Chen   | Computer Science |
| 2          | Bob Johnson  | Mathematics      |
| 3          | Carol Davis  | Physics          |
| 4          | David Wilson | Chemistry        |
| 5          | Emma Brown   | Biology          |
+------------+--------------+------------------+
</pre>

<p>study_sessions table:</p>

<pre class="example-io">
+------------+------------+------------+--------------+---------------+
| session_id | student_id | subject    | session_date | hours_studied |
+------------+------------+------------+--------------+---------------+
| 1          | 1          | Math       | 2023-10-01   | 2.5           |
| 2          | 1          | Physics    | 2023-10-02   | 3.0           |
| 3          | 1          | Chemistry  | 2023-10-03   | 2.0           |
| 4          | 1          | Math       | 2023-10-04   | 2.5           |
| 5          | 1          | Physics    | 2023-10-05   | 3.0           |
| 6          | 1          | Chemistry  | 2023-10-06   | 2.0           |
| 7          | 2          | Algebra    | 2023-10-01   | 4.0           |
| 8          | 2          | Calculus   | 2023-10-02   | 3.5           |
| 9          | 2          | Statistics | 2023-10-03   | 2.5           |
| 10         | 2          | Geometry   | 2023-10-04   | 3.0           |
| 11         | 2          | Algebra    | 2023-10-05   | 4.0           |
| 12         | 2          | Calculus   | 2023-10-06   | 3.5           |
| 13         | 2          | Statistics | 2023-10-07   | 2.5           |
| 14         | 2          | Geometry   | 2023-10-08   | 3.0           |
| 15         | 3          | Biology    | 2023-10-01   | 2.0           |
| 16         | 3          | Chemistry  | 2023-10-02   | 2.5           |
| 17         | 3          | Biology    | 2023-10-03   | 2.0           |
| 18         | 3          | Chemistry  | 2023-10-04   | 2.5           |
| 19         | 4          | Organic    | 2023-10-01   | 3.0           |
| 20         | 4          | Physical   | 2023-10-05   | 2.5           |
+------------+------------+------------+--------------+---------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+--------------+------------------+--------------+-------------------+
| student_id | student_name | major            | cycle_length | total_study_hours |
+------------+--------------+------------------+--------------+-------------------+
| 2          | Bob Johnson  | Mathematics      | 4            | 26.0              |
| 1          | Alice Chen   | Computer Science | 3            | 15.0              |
+------------+--------------+------------------+--------------+-------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Alice Chen (student_id = 1):</strong>

    <ul>
    	<li>Study sequence: Math &rarr; Physics &rarr; Chemistry &rarr; Math &rarr; Physics &rarr; Chemistry</li>
    	<li>Pattern: 3 subjects (Math, Physics, Chemistry) repeating for 2 complete cycles</li>
    	<li>Consecutive dates: Oct 1-6 with no gaps &gt; 2 days</li>
    	<li>Cycle length: 3 subjects</li>
    	<li>Total hours: 2.5 + 3.0 + 2.0 + 2.5 + 3.0 + 2.0 = 15.0 hours</li>
    </ul>
    </li>
    <li><strong>Bob Johnson (student_id = 2):</strong>
    <ul>
    	<li>Study sequence: Algebra &rarr; Calculus &rarr; Statistics &rarr; Geometry &rarr; Algebra &rarr; Calculus &rarr; Statistics &rarr; Geometry</li>
    	<li>Pattern: 4 subjects (Algebra, Calculus, Statistics, Geometry) repeating for 2 complete cycles</li>
    	<li>Consecutive dates: Oct 1-8 with no gaps &gt; 2 days</li>
    	<li>Cycle length: 4 subjects</li>
    	<li>Total hours: 4.0 + 3.5 + 2.5 + 3.0 + 4.0 + 3.5 + 2.5 + 3.0 = 26.0&nbsp;hours</li>
    </ul>
    </li>
    <li><strong>Students not included:</strong>
    <ul>
    	<li>Carol Davis (student_id = 3): Only 2 subjects (Biology, Chemistry) - doesn&#39;t meet minimum 3 subjects requirement</li>
    	<li>David Wilson (student_id = 4): Only 2 study sessions with a 4-day gap - doesn&#39;t meet consecutive dates requirement</li>
    	<li>Emma Brown (student_id = 5): No study sessions recorded</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by cycle_length in descending order, then by total_study_hours in descending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    -- 第一步：为每个学生的学习记录按照日期排序并编号
    ranked_sessions AS (
        SELECT
            s.student_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY s.student_id
                ORDER BY ss.session_date
            ) AS rn
        FROM
            study_sessions ss
            JOIN students s ON s.student_id = ss.student_id
    ),
    -- 第二步：计算当前学习日期与前一次学习的日期差
    grouped_sessions AS (
        SELECT
            *,
            DATEDIFF(
                session_date,
                LAG(session_date) OVER (
                    PARTITION BY student_id
                    ORDER BY session_date
                )
            ) AS date_diff
        FROM ranked_sessions
    ),
    -- 第三步：将学习记录按照日期差是否大于2进行分组（连续段）
    session_groups AS (
        SELECT
            *,
            SUM(
                CASE
                    WHEN date_diff > 2
                    OR date_diff IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY student_id
                ORDER BY session_date
            ) AS group_id
        FROM grouped_sessions
    ),
    -- 第四步：筛选出每个学生的每个连续学习段中包含至少6次学习的序列
    valid_sequences AS (
        SELECT
            student_id,
            group_id,
            COUNT(*) AS session_count,
            GROUP_CONCAT(subject ORDER BY session_date) AS subject_sequence,
            SUM(hours_studied) AS total_hours
        FROM session_groups
        GROUP BY student_id, group_id
        HAVING session_count >= 6
    ),
    -- 第五步：检测是否存在重复的科目循环模式
    pattern_detected AS (
        SELECT
            vs.student_id,
            vs.total_hours,
            vs.subject_sequence,
            COUNT(
                DISTINCT
                SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', n), ',', -1)
            ) AS cycle_length
        FROM
            valid_sequences vs
            JOIN (
                -- 生成1到100的数字，用于提取第n个科目
                SELECT a.N + b.N * 10 + 1 AS n
                FROM
                    (
                        SELECT 0 AS N
                        UNION
                        SELECT 1
                        UNION
                        SELECT 2
                        UNION
                        SELECT 3
                        UNION
                        SELECT 4
                        UNION
                        SELECT 5
                        UNION
                        SELECT 6
                        UNION
                        SELECT 7
                        UNION
                        SELECT 8
                        UNION
                        SELECT 9
                    ) a,
                    (
                        SELECT 0 AS N
                        UNION
                        SELECT 1
                        UNION
                        SELECT 2
                        UNION
                        SELECT 3
                        UNION
                        SELECT 4
                        UNION
                        SELECT 5
                        UNION
                        SELECT 6
                        UNION
                        SELECT 7
                        UNION
                        SELECT 8
                        UNION
                        SELECT 9
                    ) b
            ) nums
                ON n <= 10
        WHERE
            -- 简化匹配：检查前半段和后半段是否相同（即是否重复）
            LENGTH(subject_sequence) > 0
            AND LOCATE(',', subject_sequence) > 0
            AND (
                -- 匹配3科循环2轮的模式
                subject_sequence LIKE CONCAT(
                    SUBSTRING_INDEX(subject_sequence, ',', 3),
                    ',',
                    SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', 6), ',', -3),
                    '%'
                )
                OR subject_sequence LIKE CONCAT( -- 匹配4科循环2轮的模式
                    SUBSTRING_INDEX(subject_sequence, ',', 4),
                    ',',
                    SUBSTRING_INDEX(SUBSTRING_INDEX(subject_sequence, ',', 8), ',', -4),
                    '%'
                )
            )
        GROUP BY vs.student_id, vs.total_hours, vs.subject_sequence
    ),
    -- 第六步：拼接学生基本信息，并过滤掉循环长度小于3的结果
    final_output AS (
        SELECT
            s.student_id,
            s.student_name,
            s.major,
            pd.cycle_length,
            pd.total_hours AS total_study_hours
        FROM
            pattern_detected pd
            JOIN students s ON s.student_id = pd.student_id
        WHERE pd.cycle_length >= 3
    )
-- 第七步：输出结果，并按循环长度和总学习时长降序排列
SELECT *
FROM final_output
ORDER BY cycle_length DESC, total_study_hours DESC;
```

#### Pandas

```python
import pandas as pd
from datetime import timedelta


def find_study_spiral_pattern(
    students: pd.DataFrame, study_sessions: pd.DataFrame
) -> pd.DataFrame:
    study_sessions["session_date"] = pd.to_datetime(study_sessions["session_date"])

    result = []

    for student_id, group in study_sessions.groupby("student_id"):
        # 按日期排序
        group = group.sort_values("session_date").reset_index(drop=True)

        # 用于记录当前连续段
        temp = []
        last_date = None

        for idx, row in group.iterrows():
            if not temp:
                temp.append(row)
            else:
                delta = (row["session_date"] - last_date).days
                if delta <= 2:
                    temp.append(row)
                else:
                    # 处理之前的连续段
                    if len(temp) >= 6:
                        _check_pattern(student_id, temp, result)
                    temp = [row]
            last_date = row["session_date"]

        # 最后一个连续段
        if len(temp) >= 6:
            _check_pattern(student_id, temp, result)

    # 构造结果 DataFrame
    df_result = pd.DataFrame(
        result, columns=["student_id", "cycle_length", "total_study_hours"]
    )

    if df_result.empty:
        return pd.DataFrame(
            columns=[
                "student_id",
                "student_name",
                "major",
                "cycle_length",
                "total_study_hours",
            ]
        )

    # 合并 student_name 和 major
    df_result = df_result.merge(students, on="student_id")

    df_result = df_result[
        ["student_id", "student_name", "major", "cycle_length", "total_study_hours"]
    ]

    return df_result.sort_values(
        by=["cycle_length", "total_study_hours"], ascending=[False, False]
    ).reset_index(drop=True)


def _check_pattern(student_id, sessions, result):
    subjects = [row["subject"] for row in sessions]
    hours = sum(row["hours_studied"] for row in sessions)

    n = len(subjects)
    for cycle_len in range(3, n // 2 + 1):
        if n % cycle_len != 0:
            continue
        first_cycle = subjects[:cycle_len]
        is_pattern = True
        for i in range(1, n // cycle_len):
            if subjects[i * cycle_len : (i + 1) * cycle_len] != first_cycle:
                is_pattern = False
                break
        if is_pattern:
            result.append(
                {
                    "student_id": student_id,
                    "cycle_length": cycle_len,
                    "total_study_hours": hours,
                }
            )
            break  # 只记录最长周期的第一个匹配
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
