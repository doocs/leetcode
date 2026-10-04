---
comments: true
difficulty: Hard
tags:
    - Database
---

<!-- problem:start -->

# [3617. Find Students with Study Spiral Pattern](https://leetcode.com/problems/find-students-with-study-spiral-pattern)

[中文文档](/solution/3600-3699/3617.Find%20Students%20with%20Study%20Spiral%20Pattern/README.md)

## Description

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

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Enumerating every divisor of the session list and treating it as the cycle length accepts a block such as A, B, A, C repeated twice with length $4$, even though only three subjects appear. The statement defines cycle length as the number of distinct subjects.
>
> The two-day limit applies to every adjacent pair in that student's full date order. One gap longer than two days means the sessions are not one consecutive run, so a later fragment is not reported on its own. Sessions on the same date are ordered by $\textit{session\_id}$.
>
> When the whole record stays within that limit, the cycle length $k$ is the number of distinct subjects. At least two complete cycles require $k\ge 3$, at least $2k$ sessions, and a length divisible by $k$.
>
> Each session is then compared with the subject at the same offset in the first cycle. When the whole record repeats those $k$ subjects, record $k$ and the total hours, join the student table, and sort by cycle length and hours, both descending.

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
