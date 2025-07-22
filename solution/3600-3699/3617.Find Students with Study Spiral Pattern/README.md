---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3617.Find%20Students%20with%20Study%20Spiral%20Pattern/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3617. 查找具有螺旋学习模式的学生](https://leetcode.cn/problems/find-students-with-study-spiral-pattern)

[English Version](/solution/3600-3699/3617.Find%20Students%20with%20Study%20Spiral%20Pattern/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>students</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| major        | varchar |
+--------------+---------+
student_id 是这张表的唯一主键。
每一行包含有关学生及其学术专业的信息。
</pre>

<p>表：<code>study_sessions</code></p>

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
session_id 是这张表的唯一主键。
每一行代表一个学生针对特定学科的学习时段。
</pre>

<p>编写一个解决方案来找出遵循 <strong>螺旋学习模式</strong> 的学生——即那些持续学习多个学科并按循环周期进行学习的学生。</p>

<ul>
	<li>螺旋学习模式意味着学生以重复的顺序学习至少 <code>3</code> 个 <strong>不同的学科</strong>。</li>
	<li>模式必须重复 <strong>至少</strong><strong>&nbsp;</strong><code>2</code><strong>&nbsp;个完整周期</strong>（最少&nbsp;<code>6</code>&nbsp;次学习记录）。</li>
	<li>两次学习记录必须是间隔不超过&nbsp;<code>2</code>&nbsp;天的 <strong>连续日期</strong>。</li>
	<li>计算 <strong>循环长度</strong>（模式中不同的学科数量）。</li>
	<li>计算模式中所有学习记录的 <strong>总学习时长</strong>。</li>
	<li>仅包含循环长度 <strong>至少为&nbsp;</strong><strong>&nbsp;</strong><code>3</code><strong>&nbsp;门学科</strong>&nbsp;的学生。</li>
</ul>

<p>返回结果表按循环长度 <strong>降序</strong>&nbsp;排序，然后按总学习时间 <strong>降序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>students 表：</p>

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

<p>study_sessions 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+--------------+------------------+--------------+-------------------+
| student_id | student_name | major            | cycle_length | total_study_hours |
+------------+--------------+------------------+--------------+-------------------+
| 2          | Bob Johnson  | Mathematics      | 4            | 26.0              |
| 1          | Alice Chen   | Computer Science | 3            | 15.0              |
+------------+--------------+------------------+--------------+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Alice Chen (student_id = 1):</strong>

    <ul>
    	<li>学习序列：Math → Physics → Chemistry → Math → Physics → Chemistry</li>
    	<li>模式：3 门学科（Math，Physics，Chemistry）重复 2 个完整周期</li>
    	<li>连续日期：十月 1-6，没有超过 2 天的间隔</li>
    	<li>循环长度：3 门学科</li>
    	<li>总时间：2.5 + 3.0 + 2.0 + 2.5 + 3.0 + 2.0 = 15.0 小时</li>
    </ul>
    </li>
    <li><strong>Bob Johnson (student_id = 2):</strong>
    <ul>
    	<li>学习序列：Algebra → Calculus → Statistics → Geometry → Algebra → Calculus → Statistics → Geometry</li>
    	<li>模式：4 门学科（Algebra，Calculus，Statistics，Geometry）重复 2 个完整周期</li>
    	<li>连续日期：十月 1-8，没有超过 2 天的间隔</li>
    	<li>循环长度：4 门学科</li>
    	<li>总时间：4.0 + 3.5 + 2.5 + 3.0 + 4.0 + 3.5 + 2.5 + 3.0 = 26.0&nbsp;小时</li>
    </ul>
    </li>
    <li><strong>未包含学生：</strong>
    <ul>
    	<li>Carol Davis (student_id = 3)：仅 2 门学科（生物，化学）- 未满足至少 3 门学科的要求</li>
    	<li>David Wilson (student_id = 4)：仅 2 次学习课程，间隔 4 天 - 不符合连续日期要求</li>
    	<li>Emma Brown (student_id = 5)：没有记录学习课程</li>
    </ul>
    </li>

</ul>

<p>结果表以 cycle_length 降序排序，然后以 total_study_hours 降序排序。</p>
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
                OR subject_sequence LIKE CONCAT(
                    -- 匹配4科循环2轮的模式
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
