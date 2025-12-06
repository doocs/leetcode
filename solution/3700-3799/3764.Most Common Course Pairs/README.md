---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3764.Most%20Common%20Course%20Pairs/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3764. 最常见的课程组合](https://leetcode.cn/problems/most-common-course-pairs)

[English Version](/solution/3700-3799/3764.Most%20Common%20Course%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>course_completions</code></p>

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
(user_id, course_id) 是此表中具有不同值的列的组合。
每一行代表一个用户完成的课程及其评分（1-5 分）。
</pre>

<p>编写一个解决方案，通过分析顶尖学生完成课程的序列来识别 <strong>课程路径</strong>：</p>

<ul>
	<li>只考虑 <strong>顶尖学生</strong>（完成 <strong>至少</strong> <code>5</code> <strong>门课程且平均评分</strong> <code>4</code> <strong>分或以上&nbsp;</strong>的人）。</li>
	<li>对每个顶尖学生，确定他们按时间顺序完成的 <strong>课程序列</strong>。</li>
	<li>找出这些学生所学的所有 <strong>连续课程对&nbsp;</strong>（<code>课程 A → 课程 B</code>）。</li>
	<li>返回课程对的频率，确定顶尖学生中最常见的课程路径。</li>
</ul>

<p>返回结果表，按课程对频率 <strong>降序</strong> 排列，若频率相同则按第一课程名称和第二课程名称 <strong>升序</strong> 排列。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>course_completions 表：</p>

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

<p><strong>输出：</strong></p>

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

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 1：</strong>完成了 6 门课程，平均分为 4.5（满足顶尖学生）</li>
	<li><strong>用户 2：</strong>完成了 5 门课程，平均分为 4.4（满足顶尖学生）</li>
	<li><strong>用户 3：</strong>完成了 5 门课程但平均得分为 2.8（不满足资格）</li>
	<li><strong>用户 4：</strong>只完成了 3 门课程（不满足资格）</li>
	<li><strong>顶尖学生的课程对：</strong>
	<ul>
		<li>用户 1：Python Basics → SQL Fundamentals → JavaScript → React Basics → Node.js → Docker</li>
		<li>用户 2：Python Basics → React Basics → Node.js → Docker → AWS Fundamentals</li>
		<li>最常见的路径：Node.js → Docker (2 次)，React Basics → Node.js (2 次)</li>
	</ul>
	</li>
</ul>

<p>结果按 transition_count 降序排列，然后按 first_course 升序排列，再按 second_course 升序排列。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们首先筛选出所有顶尖学生，记为 `top_students`，即完成课程数不少于 5 且平均评分不少于 4 的学生。 然后对于每个顶尖学生，按完成时间排序，找出所有连续的课程对，记为 `course_pairs`。 最后对所有课程对进行分组统计，计算每个课程对的出现次数，并按要求排序输出结果。

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
