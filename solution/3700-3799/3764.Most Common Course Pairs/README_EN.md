---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3764.Most%20Common%20Course%20Pairs/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3764. Most Common Course Pairs](https://leetcode.com/problems/most-common-course-pairs)

[中文文档](/solution/3700-3799/3764.Most%20Common%20Course%20Pairs/README.md)

## Description

<!-- description:start -->

<p>Table: <code>course_completions</code></p>

<pre>
+-------------------+---------+
| Column Name       | Type    | 
+-------------------+---------+
| user_id           | int     |
| course_id         | int     |
| course_name       | varchar |
| completion_date   | date    |
| course_rating     | int     |
+-------------------+---------+
(user_id, course_id) is the combination of columns with unique values for this table.
Each row represents a completed course by a user with their rating (1-5 scale).
</pre>

<p>Write a solution to identify <strong>skill mastery pathways</strong> by analyzing course completion sequences among top-performing students:</p>

<ul>
	<li>Consider only <strong>top-performing students</strong> (those who completed <strong>at least </strong><code>5</code><strong> courses</strong> with an <strong>average rating of </strong><code>4</code><strong> or higher</strong>).</li>
	<li>For each top performer, identify the <strong>sequence of courses</strong> they completed in chronological order.</li>
	<li>Find all <strong>consecutive course pairs</strong> (<code>Course A &rarr; Course B</code>) taken by these students.</li>
	<li>Return the <strong>pair frequency</strong>, identifying which course transitions are most common among high achievers.</li>
</ul>

<p>Return <em>the result table ordered by</em> <em>pair frequency in <strong>descending</strong> order</em>&nbsp;<em>and then by first course name and second course name in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>course_completions table:</p>

<pre class="example-io">
+---------+-----------+------------------+-----------------+---------------+
| user_id | course_id | course_name      | completion_date | course_rating |
+---------+-----------+------------------+-----------------+---------------+
| 1       | 101       | Python Basics    | 2024-01-05      | 5             |
| 1       | 102       | SQL Fundamentals | 2024-02-10      | 4             |
| 1       | 103       | JavaScript       | 2024-03-15      | 5             |
| 1       | 104       | React Basics     | 2024-04-20      | 4             |
| 1       | 105       | Node.js          | 2024-05-25      | 5             |
| 1       | 106       | Docker           | 2024-06-30      | 4             |
| 2       | 101       | Python Basics    | 2024-01-08      | 4             |
| 2       | 104       | React Basics     | 2024-02-14      | 5             |
| 2       | 105       | Node.js          | 2024-03-20      | 4             |
| 2       | 106       | Docker           | 2024-04-25      | 5             |
| 2       | 107       | AWS Fundamentals | 2024-05-30      | 4             |
| 3       | 101       | Python Basics    | 2024-01-10      | 3             |
| 3       | 102       | SQL Fundamentals | 2024-02-12      | 3             |
| 3       | 103       | JavaScript       | 2024-03-18      | 3             |
| 3       | 104       | React Basics     | 2024-04-22      | 2             |
| 3       | 105       | Node.js          | 2024-05-28      | 3             |
| 4       | 101       | Python Basics    | 2024-01-12      | 5             |
| 4       | 108       | Data Science     | 2024-02-16      | 5             |
| 4       | 109       | Machine Learning | 2024-03-22      | 5             |
+---------+-----------+------------------+-----------------+---------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------------+------------------+------------------+
| first_course     | second_course    | transition_count |
+------------------+------------------+------------------+
| Node.js          | Docker           | 2                |
| React Basics     | Node.js          | 2                |
| Docker           | AWS Fundamentals | 1                |
| JavaScript       | React Basics     | 1                |
| Python Basics    | React Basics     | 1                |
| Python Basics    | SQL Fundamentals | 1                |
| SQL Fundamentals | JavaScript       | 1                |
+------------------+------------------+------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1</strong>: Completed 6 courses with average rating 4.5 (qualifies as top performer)</li>
	<li><strong>User 2</strong>: Completed 5 courses with average rating 4.4 (qualifies as top performer)</li>
	<li><strong>User 3</strong>: Completed 5 courses but average rating is 2.8 (does not qualify)</li>
	<li><strong>User 4</strong>: Completed only 3 courses (does not qualify)</li>
	<li><strong>Course Pairs Among Top Performers</strong>:
	<ul>
		<li>User 1: Python Basics &rarr; SQL Fundamentals &rarr; JavaScript &rarr; React Basics &rarr; Node.js &rarr; Docker</li>
		<li>User 2: Python Basics &rarr; React Basics &rarr; Node.js &rarr; Docker &rarr; AWS Fundamentals</li>
		<li>Most common transitions: Node.js &rarr; Docker (2 times), React Basics &rarr; Node.js (2 times)</li>
	</ul>
	</li>
</ul>

<p>Results are ordered by transition_count in descending order, then by first_course in ascending order, and then by second_course in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping and Counting

We first filter out all top students, denoted as `top_students`, i.e., students who have completed at least 5 courses with an average rating of at least 4. Then for each top student, we sort by completion time and find all consecutive course pairs, denoted as `course_pairs`. Finally, we group and count all course pairs, calculate the occurrence count of each course pair, and output the results sorted as required.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    top_students AS (
        SELECT user_id
        FROM course_completions
        GROUP BY user_id
        HAVING COUNT(1) >= 5 AND AVG(course_rating) >= 4
    ),
    course_pairs AS (
        SELECT
            course_name AS first_course,
            LEAD(course_name) OVER (
                PARTITION BY user_id
                ORDER BY completion_date
            ) second_course
        FROM
            top_students
            JOIN course_completions USING (user_id)
    )
SELECT
    *,
    COUNT(1) transition_count
FROM course_pairs
WHERE second_course IS NOT NULL
GROUP BY 1, 2
ORDER BY 3 DESC, 1, 2;
```

#### Pandas

```python
import pandas as pd


def topLearnerCourseTransitions(course_completions: pd.DataFrame) -> pd.DataFrame:
    grp = course_completions.groupby("user_id")
    top_students = grp.filter(
        lambda df: df.shape[0] >= 5 and df["course_rating"].mean() >= 4
    )["user_id"].unique()

    df = course_completions[course_completions["user_id"].isin(top_students)].copy()
    df = df.sort_values(["user_id", "completion_date"])
    df["second_course"] = df.groupby("user_id")["course_name"].shift(-1)
    df["first_course"] = df["course_name"]

    pairs = df[df["second_course"].notna()][["first_course", "second_course"]]

    result = (
        pairs.groupby(["first_course", "second_course"])
        .size()
        .reset_index(name="transition_count")
        .sort_values(
            ["transition_count", "first_course", "second_course"],
            ascending=[False, True, True],
            key=lambda col: col.str.lower() if col.dtype == "object" else col,
        )
        .reset_index(drop=True)
    )

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
