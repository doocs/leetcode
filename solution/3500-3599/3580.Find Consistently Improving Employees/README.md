---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3580.Find%20Consistently%20Improving%20Employees/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3580. 寻找持续进步的员工](https://leetcode.cn/problems/find-consistently-improving-employees)

[English Version](/solution/3500-3599/3580.Find%20Consistently%20Improving%20Employees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
+-------------+---------+
employee_id 是这张表的唯一主键。
每一行包含一名员工的信息。
</pre>

<p>表：<code>performance_reviews</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| review_id   | int  |
| employee_id | int  |
| review_date | date |
| rating      | int  |
+-------------+------+
review_id 是这张表的唯一主键。
每一行表示一名员工的绩效评估。评分在 1-5 的范围内，5分代表优秀，1分代表较差。
</pre>

<p>编写一个解决方案，以找到在过去三次评估中持续提高绩效的员工。</p>

<ul>
	<li>员工 <strong>至少需要</strong> <code>3</code>&nbsp;<strong>次评估&nbsp;</strong>才能被考虑</li>
	<li>员工过去的&nbsp;<code>3</code> 次评估，评分必须&nbsp;<strong>严格递增</strong>（每次评价都比上一次好）</li>
	<li>根据 <code>review_date</code> 为每位员工分析最近的 <code>3</code> 次评估</li>
	<li><strong>进步分数</strong> 为最后 <code>3</code> 次评估中最后一次评分与最早一次评分之间的差值</li>
</ul>

<p>返回结果表以<em>&nbsp;</em><strong>进步分数 降序</strong>&nbsp;排序，然后以&nbsp;<strong>名字</strong>&nbsp;<strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>employees 表：</p>

<pre class="example-io">
+-------------+----------------+
| employee_id | name           |
+-------------+----------------+
| 1           | Alice Johnson  |
| 2           | Bob Smith      |
| 3           | Carol Davis    |
| 4           | David Wilson   |
| 5           | Emma Brown     |
+-------------+----------------+
</pre>

<p>performance_reviews 表：</p>

<pre class="example-io">
+-----------+-------------+-------------+--------+
| review_id | employee_id | review_date | rating |
+-----------+-------------+-------------+--------+
| 1         | 1           | 2023-01-15  | 2      |
| 2         | 1           | 2023-04-15  | 3      |
| 3         | 1           | 2023-07-15  | 4      |
| 4         | 1           | 2023-10-15  | 5      |
| 5         | 2           | 2023-02-01  | 3      |
| 6         | 2           | 2023-05-01  | 2      |
| 7         | 2           | 2023-08-01  | 4      |
| 8         | 2           | 2023-11-01  | 5      |
| 9         | 3           | 2023-03-10  | 1      |
| 10        | 3           | 2023-06-10  | 2      |
| 11        | 3           | 2023-09-10  | 3      |
| 12        | 3           | 2023-12-10  | 4      |
| 13        | 4           | 2023-01-20  | 4      |
| 14        | 4           | 2023-04-20  | 4      |
| 15        | 4           | 2023-07-20  | 4      |
| 16        | 5           | 2023-02-15  | 3      |
| 17        | 5           | 2023-05-15  | 2      |
+-----------+-------------+-------------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+----------------+-------------------+
| employee_id | name           | improvement_score |
+-------------+----------------+-------------------+
| 2           | Bob Smith      | 3                 |
| 1           | Alice Johnson  | 2                 |
| 3           | Carol Davis    | 2                 |
+-------------+----------------+-------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Alice Johnson (employee_id = 1)：</strong>

    <ul>
    	<li>有 4 次评估，分数：2, 3, 4, 5</li>
    	<li>最后 3 次评估（按日期）：2023-04-15 (3), 2023-07-15 (4), 2023-10-15 (5)</li>
    	<li>评分严格递增：3 → 4 → 5</li>
    	<li>进步分数：5 - 3 = 2</li>
    </ul>
    </li>
    <li><strong>Carol Davis (employee_id = 3)：</strong>
    <ul>
    	<li>有 4 次评估，分数：1, 2, 3, 4</li>
    	<li>最后 3 次评估（按日期）：2023-06-10 (2)，2023-09-10 (3)，2023-12-10 (4)</li>
    	<li>评分严格递增：2 → 3 → 4</li>
    	<li>进步分数：4 - 2 = 2</li>
    </ul>
    </li>
    <li><strong>Bob Smith (employee_id = 2)：</strong>
    <ul>
    	<li>有 4 次评估，分数：3，2，4，5</li>
    	<li>最后 3 次评估（按日期）：2023-05-01 (2)，2023-08-01 (4)，2023-11-01 (5)</li>
    	<li>评分严格递增：2 → 4 → 5</li>
    	<li>进步分数：5 - 2 = 3</li>
    </ul>
    </li>
    <li><strong>未包含的员工：</strong>
    <ul>
    	<li>David Wilson (employee_id = 4)：之前 3 次评估都是 4 分（没有进步）</li>
    	<li>Emma Brown (employee_id = 5)：只有 2 次评估（需要至少 3 次）</li>
    </ul>
    </li>

</ul>

<p>输出表以 improvement_score 降序排序，然后以 name 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用窗口函数和聚合函数

我们首先将每个员工的最近三次绩效评估记录提取出来，并计算出每次评估的评分与前一次评估的评分之差。接着，我们筛选出那些评分严格递增的员工，并计算他们的改进分数（即最后一次评分减去第一次评分）。最后，我们按照改进分数降序排列，并按姓名升序排列。

<!-- tabs:start -->

#### MySQL

```sql
WITH
    recent AS (
        SELECT
            employee_id,
            review_date,
            ROW_NUMBER() OVER (
                PARTITION BY employee_id
                ORDER BY review_date DESC
            ) AS rn,
            (
                LAG(rating) OVER (
                    PARTITION BY employee_id
                    ORDER BY review_date DESC
                ) - rating
            ) AS delta
        FROM performance_reviews
    )
SELECT
    employee_id,
    name,
    SUM(delta) AS improvement_score
FROM
    recent
    JOIN employees USING (employee_id)
WHERE rn > 1 AND rn <= 3
GROUP BY 1
HAVING COUNT(*) = 2 AND MIN(delta) > 0
ORDER BY 3 DESC, 2;
```

#### Pandas

```python
import pandas as pd


def find_consistently_improving_employees(
    employees: pd.DataFrame, performance_reviews: pd.DataFrame
) -> pd.DataFrame:
    performance_reviews = performance_reviews.sort_values(
        ["employee_id", "review_date"], ascending=[True, False]
    )
    performance_reviews["rn"] = (
        performance_reviews.groupby("employee_id").cumcount() + 1
    )
    performance_reviews["lag_rating"] = performance_reviews.groupby("employee_id")[
        "rating"
    ].shift(1)
    performance_reviews["delta"] = (
        performance_reviews["lag_rating"] - performance_reviews["rating"]
    )
    recent = performance_reviews[
        (performance_reviews["rn"] > 1) & (performance_reviews["rn"] <= 3)
    ]
    improvement = (
        recent.groupby("employee_id")
        .agg(
            improvement_score=("delta", "sum"),
            count=("delta", "count"),
            min_delta=("delta", "min"),
        )
        .reset_index()
    )
    improvement = improvement[
        (improvement["count"] == 2) & (improvement["min_delta"] > 0)
    ]
    result = improvement.merge(employees[["employee_id", "name"]], on="employee_id")
    result = result.sort_values(
        by=["improvement_score", "name"], ascending=[False, True]
    )
    return result[["employee_id", "name", "improvement_score"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
