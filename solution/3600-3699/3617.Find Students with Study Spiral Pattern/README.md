---
comments: true
difficulty: 困难
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

<!-- thinking:start -->

> **思考**
>
> 若把周期长度理解成段长的任意约数，A、B、A、C 重复两轮会被记成 $4$，但这段只有 $3$ 门不同的课，与题目对周期长度的定义不符。连续段必须先按日期排好，间隔大于 $2$ 天就断开，同一天的场次再按 $\textit{session\_id}$ 区分先后。
>
> 周期长度应取该段不同科目的个数 $k$。仅当 $k\ge 3$、段长不少于 $2k$ 且能被 $k$ 整除时，才可能构成至少两轮完整循环。
>
> 此时比较每个位置是否等于首轮中的对应科目。整段都由这 $k$ 门课按原顺序重复，才记下周期长度与总学时，再与学生表连接，按周期长度、总学时降序输出。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    ranked_sessions AS (
        SELECT
            s.student_id,
            ss.session_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY s.student_id
                ORDER BY ss.session_date, ss.session_id
            ) AS rn
        FROM
            study_sessions ss
            JOIN students s ON s.student_id = ss.student_id
    ),
    grouped_sessions AS (
        SELECT
            *,
            DATEDIFF(
                session_date,
                LAG(session_date) OVER (
                    PARTITION BY student_id
                    ORDER BY session_date, session_id
                )
            ) AS date_diff
        FROM ranked_sessions
    ),
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
                ORDER BY session_date, session_id
            ) AS group_id
        FROM grouped_sessions
    ),
    group_stats AS (
        SELECT
            student_id,
            group_id,
            COUNT(*) AS session_count,
            COUNT(DISTINCT subject) AS cycle_length,
            SUM(hours_studied) AS total_hours
        FROM session_groups
        GROUP BY student_id, group_id
        HAVING
            cycle_length >= 3
            AND session_count >= cycle_length * 2
            AND session_count % cycle_length = 0
    ),
    numbered_sessions AS (
        SELECT
            g.student_id,
            g.group_id,
            g.subject,
            gs.cycle_length,
            gs.total_hours,
            ROW_NUMBER() OVER (
                PARTITION BY g.student_id, g.group_id
                ORDER BY g.session_date, g.session_id
            ) AS session_index
        FROM
            session_groups g
            JOIN group_stats gs ON gs.student_id = g.student_id AND gs.group_id = g.group_id
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    n.cycle_length,
    n.total_hours AS total_study_hours
FROM
    numbered_sessions n
    JOIN students s ON s.student_id = n.student_id
WHERE
    n.session_index = 1
    AND NOT EXISTS (
        SELECT 1
        FROM
            numbered_sessions cur
            JOIN numbered_sessions cyc
                ON cyc.student_id = cur.student_id
                AND cyc.group_id = cur.group_id
                AND cyc.session_index = (cur.session_index - 1) % n.cycle_length + 1
        WHERE
            cur.student_id = n.student_id
            AND cur.group_id = n.group_id
            AND NOT (cur.subject <=> cyc.subject)
    )
ORDER BY n.cycle_length DESC, n.total_hours DESC;
```

#### Pandas

```python
import pandas as pd
from datetime import timedelta


def find_study_spiral_pattern(
    students: pd.DataFrame, study_sessions: pd.DataFrame
) -> pd.DataFrame:
    # Convert session_date to datetime
    study_sessions["session_date"] = pd.to_datetime(study_sessions["session_date"])

    result = []

    # Group study sessions by student
    for student_id, group in study_sessions.groupby("student_id"):
        # Sort sessions by date, then by session id when dates tie
        group = group.sort_values(["session_date", "session_id"]).reset_index(drop=True)

        temp = []  # Holds current contiguous segment
        last_date = None

        for idx, row in group.iterrows():
            if not temp:
                temp.append(row)
            else:
                delta = (row["session_date"] - last_date).days
                if delta <= 2:
                    temp.append(row)
                else:
                    # Check the previous contiguous segment
                    if len(temp) >= 6:
                        _check_pattern(student_id, temp, result)
                    temp = [row]
            last_date = row["session_date"]

        # Check the final segment
        if len(temp) >= 6:
            _check_pattern(student_id, temp, result)

    # Build result DataFrame
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

    # Join with students table to get name and major
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
    # Cycle length is the number of distinct subjects, not an arbitrary divisor.
    cycle_len = len(set(subjects))
    if cycle_len < 3 or n < cycle_len * 2 or n % cycle_len != 0:
        return

    first_cycle = subjects[:cycle_len]
    if subjects != first_cycle * (n // cycle_len):
        return

    result.append(
        {
            "student_id": student_id,
            "cycle_length": cycle_len,
            "total_study_hours": hours,
        }
    )
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
