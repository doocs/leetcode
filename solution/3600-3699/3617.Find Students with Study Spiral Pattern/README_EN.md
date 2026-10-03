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
> A spiral is a date-contiguous sequence (gaps at most two days) whose subjects repeat a cycle of length at least $3$. After grouping by student, sort by date and split on gaps.
>
> Only a segment of length at least $6$ can contain two full cycles. For every divisor $\textit{cycle\_len}\ge 3$ of the length, compare later blocks with the first.
>
> The first valid cycle records that student's cycle length and total hours. Join the student table and sort by cycle length then hours, both descending.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    ranked_sessions AS (
        SELECT
            ss.student_id,
            ss.session_date,
            ss.subject,
            ss.hours_studied,
            ROW_NUMBER() OVER (
                PARTITION BY ss.student_id
                ORDER BY ss.session_date
            ) AS session_rank
        FROM study_sessions ss
    ),
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
    session_groups AS (
        SELECT
            *,
            SUM(
                CASE
                    WHEN date_diff > 2 OR date_diff IS NULL THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY student_id
                ORDER BY session_date
            ) AS group_id
        FROM grouped_sessions
    ),
    numbered_sessions AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, group_id
                ORDER BY session_date
            ) AS session_index,
            COUNT(*) OVER (
                PARTITION BY student_id, group_id
            ) AS session_count,
            SUM(hours_studied) OVER (
                PARTITION BY student_id, group_id
            ) AS total_hours
        FROM session_groups
    ),
    cycle_candidates AS (
        SELECT
            student_id,
            group_id,
            session_count,
            total_hours,
            3 AS cycle_length
        FROM numbered_sessions
        WHERE session_index = 1 AND session_count >= 6

        UNION ALL

        SELECT
            student_id,
            group_id,
            session_count,
            total_hours,
            cycle_length + 1
        FROM cycle_candidates
        WHERE cycle_length < FLOOR(session_count / 2)
    ),
    matching_patterns AS (
        SELECT
            c.student_id,
            c.group_id,
            c.cycle_length,
            c.total_hours
        FROM cycle_candidates c
        WHERE MOD(c.session_count, c.cycle_length) = 0
            AND NOT EXISTS (
                SELECT 1
                FROM numbered_sessions current_session
                JOIN numbered_sessions cycle_session
                    ON cycle_session.student_id = current_session.student_id
                    AND cycle_session.group_id = current_session.group_id
                    AND cycle_session.session_index =
                        MOD(current_session.session_index - 1, c.cycle_length) + 1
                WHERE current_session.student_id = c.student_id
                    AND current_session.group_id = c.group_id
                    AND NOT (current_session.subject <=> cycle_session.subject)
            )
    ),
    ranked_patterns AS (
        SELECT
            student_id,
            group_id,
            cycle_length,
            total_hours,
            ROW_NUMBER() OVER (
                PARTITION BY student_id, group_id
                ORDER BY cycle_length
            ) AS pattern_rank
        FROM matching_patterns
    )
SELECT
    s.student_id,
    s.student_name,
    s.major,
    p.cycle_length,
    p.total_hours AS total_study_hours
FROM ranked_patterns p
JOIN students s ON s.student_id = p.student_id
WHERE p.pattern_rank = 1
ORDER BY p.cycle_length DESC, p.total_hours DESC;

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
        # Sort sessions by date
        group = group.sort_values("session_date").reset_index(drop=True)

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

    # Try possible cycle lengths from 3 up to half of the sequence
    for cycle_len in range(3, n // 2 + 1):
        if n % cycle_len != 0:
            continue

        # Extract the first cycle
        first_cycle = subjects[:cycle_len]
        is_pattern = True

        # Compare each following cycle with the first
        for i in range(1, n // cycle_len):
            if subjects[i * cycle_len : (i + 1) * cycle_len] != first_cycle:
                is_pattern = False
                break

        # If a repeated cycle is detected, store the result
        if is_pattern:
            result.append(
                {
                    "student_id": student_id,
                    "cycle_length": cycle_len,
                    "total_study_hours": hours,
                }
            )
            break  # Stop at the first valid cycle found
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
