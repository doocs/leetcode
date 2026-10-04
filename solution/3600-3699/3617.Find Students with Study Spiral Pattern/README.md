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
> 若把周期长度理解成段长的任意约数，A、B、A、C 重复两轮会被记成 $4$，但这段只有 $3$ 门不同的课，与题目对周期长度的定义不符。
>
> 间隔约束作用在该学生按时间排好的全部记录上。相邻两次学习只要有一次间隔大于 $2$ 天，这些记录就不是一段连续日期；同一天的场次再按 $\textit{session\_id}$ 区分先后。前面隔开很久的一节课，不能把后面碰巧重复的科目单独截出来当作螺旋。
>
> 整段都连续时，周期长度取不同科目的个数 $k$。仅当 $k\ge 3$、记录数不少于 $2k$ 且能被 $k$ 整除时，才可能构成至少两轮完整循环。
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
            ss.student_id,
            ss.session_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            DATEDIFF(
                ss.session_date,
                LAG(ss.session_date) OVER (
                    PARTITION BY ss.student_id
                    ORDER BY ss.session_date, ss.session_id
                )
            ) AS date_diff,
            ROW_NUMBER() OVER (
                PARTITION BY ss.student_id
                ORDER BY ss.session_date, ss.session_id
            ) AS session_index
        FROM study_sessions ss
    ),
    contiguous_students AS (
        SELECT student_id
        FROM ranked_sessions
        GROUP BY student_id
        HAVING
            SUM(
                CASE
                    WHEN date_diff > 2 THEN 1
                    ELSE 0
                END
            ) = 0
    ),
    student_stats AS (
        SELECT
            r.student_id,
            COUNT(*) AS session_count,
            COUNT(DISTINCT r.subject) AS cycle_length,
            SUM(r.hours_studied) AS total_hours
        FROM
            ranked_sessions r
            JOIN contiguous_students c ON c.student_id = r.student_id
        GROUP BY r.student_id
        HAVING
            cycle_length >= 3
            AND session_count >= cycle_length * 2
            AND session_count % cycle_length = 0
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    st.cycle_length,
    st.total_hours AS total_study_hours
FROM
    student_stats st
    JOIN students s ON s.student_id = st.student_id
WHERE
    NOT EXISTS (
        SELECT 1
        FROM
            ranked_sessions cur
            JOIN ranked_sessions cyc
                ON cyc.student_id = cur.student_id
                AND cyc.session_index = (cur.session_index - 1) % st.cycle_length + 1
        WHERE cur.student_id = st.student_id AND NOT (cur.subject <=> cyc.subject)
    )
ORDER BY st.cycle_length DESC, st.total_hours DESC;
```

#### Pandas

```python
import pandas as pd


def find_study_spiral_pattern(
    students: pd.DataFrame, study_sessions: pd.DataFrame
) -> pd.DataFrame:
    study_sessions = study_sessions.copy()
    study_sessions["session_date"] = pd.to_datetime(study_sessions["session_date"])

    result = []

    for student_id, group in study_sessions.groupby("student_id"):
        group = group.sort_values(["session_date", "session_id"]).reset_index(drop=True)
        # One gap longer than two days breaks the whole record. A later fragment
        # is not a separate spiral.
        if len(group) >= 2:
            gaps = group["session_date"].diff().dt.days.iloc[1:]
            if (gaps > 2).any():
                continue
        _check_pattern(student_id, [row for _, row in group.iterrows()], result)

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
