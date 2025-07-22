---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3586.Find%20COVID%20Recovery%20Patients/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3586. 寻找 COVID 康复患者](https://leetcode.cn/problems/find-covid-recovery-patients)

[English Version](/solution/3500-3599/3586.Find%20COVID%20Recovery%20Patients/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>patients</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| patient_id  | int     |
| patient_name| varchar |
| age         | int     |
+-------------+---------+
patient_id 是这张表的唯一主键。
每一行表示一个患者的信息。
</pre>

<p>表：<code>covid_tests</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| test_id     | int     |
| patient_id  | int     |
| test_date   | date    |
| result      | varchar |
+-------------+---------+
test_id 是这张表的唯一主键。
每一行代表一个 COVID 检测结果。结果可以是阳性、阴性或不确定。
</pre>

<p>编写一个解决方案以找到从 COVID 中康复的患者——那些曾经检测呈阳性但后来检测呈阴性的患者。</p>

<ul>
	<li>患者如果 <strong>至少有一次阳性</strong> 检测结果后，在&nbsp;<strong>之后的日期</strong> 至少有一次 <strong>阴性</strong> 检测结果，则被认为已康复。</li>
	<li>计算从 <strong>首次阳性检测</strong> 结果到 <strong>该阳性检测</strong> 后的 <strong>首次阴性检测结果</strong> 之间的 <strong>康复时间</strong>（以天为单位）</li>
	<li><strong>仅包括&nbsp;</strong>同时具有阳性及阴性检测结果的患者</li>
</ul>

<p>返回结果表以<em>&nbsp;</em><code>recovery_time</code><em> </em><strong>升序 </strong>排序，然后以<em>&nbsp;</em><code>patient_name</code><em> </em><strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>patients 表：</p>

<pre class="example-io">
+------------+--------------+-----+
| patient_id | patient_name | age |
+------------+--------------+-----+
| 1          | Alice Smith  | 28  |
| 2          | Bob Johnson  | 35  |
| 3          | Carol Davis  | 42  |
| 4          | David Wilson | 31  |
| 5          | Emma Brown   | 29  |
+------------+--------------+-----+
</pre>

<p>covid_tests 表：</p>

<pre class="example-io">
+---------+------------+------------+--------------+
| test_id | patient_id | test_date  | result       |
+---------+------------+------------+--------------+
| 1       | 1          | 2023-01-15 | Positive     |
| 2       | 1          | 2023-01-25 | Negative     |
| 3       | 2          | 2023-02-01 | Positive     |
| 4       | 2          | 2023-02-05 | Inconclusive |
| 5       | 2          | 2023-02-12 | Negative     |
| 6       | 3          | 2023-01-20 | Negative     |
| 7       | 3          | 2023-02-10 | Positive     |
| 8       | 3          | 2023-02-20 | Negative     |
| 9       | 4          | 2023-01-10 | Positive     |
| 10      | 4          | 2023-01-18 | Positive     |
| 11      | 5          | 2023-02-15 | Negative     |
| 12      | 5          | 2023-02-20 | Negative     |
+---------+------------+------------+--------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+--------------+-----+---------------+
| patient_id | patient_name | age | recovery_time |
+------------+--------------+-----+---------------+
| 1          | Alice Smith  | 28  | 10            |
| 3          | Carol Davis  | 42  | 10            |
| 2          | Bob Johnson  | 35  | 11            |
+------------+--------------+-----+---------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Alice Smith (patient_id = 1):</strong>

    <ul>
    	<li>首次阳性检测：2023-01-15</li>
    	<li>阳性检测后的首次阴性检测：2023-01-25</li>
    	<li>康复时间：25 - 15 = 10 天</li>
    </ul>
    </li>
    <li><strong>Bob Johnson (patient_id = 2):</strong>
    <ul>
    	<li>首次阳性检测：2023-02-01</li>
    	<li>测试结果不明确：2023-02-05（忽略计算康复时间）</li>
    	<li>阳性检测后的首次阴性检测：2023-02-12</li>
    	<li>康复时间：12 - 1 = 11 天</li>
    </ul>
    </li>
    <li><strong>Carol Davis (patient_id = 3):</strong>
    <ul>
    	<li>检测呈阴性：2023-01-20（在阳性检测前）</li>
    	<li>首次阳性检测：2023-02-10</li>
    	<li>阳性检测后的首次阴性检测：2023-02-20</li>
    	<li>康复时间：20 - 10 = 10 天</li>
    </ul>
    </li>
    <li><strong>没有包含的患者：</strong>
    <ul>
    	<li>David Wilson（patient_id = 4）：只有阳性检测，之后没有阴性检测。</li>
    	<li>Emma Brown（patient_id = 5）：只有阴性检测，从未有阳性检测。</li>
    </ul>
    </li>

</ul>

<p>输出表以 recovery_time 升序排序，然后以 patient_name 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 等值连接

我们可以先找出每个患者的第一次阳性检测日期，记录在表 `first_positive` 中。接着，我们可以在 `covid_tests` 表中找到每个患者在第一次阳性检测之后的第一次阴性检测日期，记录在表 `first_negative_after_positive` 中。最后，我们将这两个表与 `patients` 表连接，计算恢复时间，并按照要求排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    first_positive AS (
        SELECT
            patient_id,
            MIN(test_date) AS first_positive_date
        FROM covid_tests
        WHERE result = 'Positive'
        GROUP BY patient_id
    ),
    first_negative_after_positive AS (
        SELECT
            t.patient_id,
            MIN(t.test_date) AS first_negative_date
        FROM
            covid_tests t
            JOIN first_positive p
                ON t.patient_id = p.patient_id AND t.test_date > p.first_positive_date
        WHERE t.result = 'Negative'
        GROUP BY t.patient_id
    )
SELECT
    p.patient_id,
    p.patient_name,
    p.age,
    DATEDIFF(n.first_negative_date, f.first_positive_date) AS recovery_time
FROM
    first_positive f
    JOIN first_negative_after_positive n ON f.patient_id = n.patient_id
    JOIN patients p ON p.patient_id = f.patient_id
ORDER BY recovery_time ASC, patient_name ASC;
```

#### Pandas

```python
import pandas as pd


def find_covid_recovery_patients(
    patients: pd.DataFrame, covid_tests: pd.DataFrame
) -> pd.DataFrame:
    covid_tests["test_date"] = pd.to_datetime(covid_tests["test_date"])

    pos = (
        covid_tests[covid_tests["result"] == "Positive"]
        .groupby("patient_id", as_index=False)["test_date"]
        .min()
    )
    pos.rename(columns={"test_date": "first_positive_date"}, inplace=True)

    neg = covid_tests.merge(pos, on="patient_id")
    neg = neg[
        (neg["result"] == "Negative") & (neg["test_date"] > neg["first_positive_date"])
    ]
    neg = neg.groupby("patient_id", as_index=False)["test_date"].min()
    neg.rename(columns={"test_date": "first_negative_date"}, inplace=True)

    df = pos.merge(neg, on="patient_id")
    df["recovery_time"] = (
        df["first_negative_date"] - df["first_positive_date"]
    ).dt.days

    out = df.merge(patients, on="patient_id")[
        ["patient_id", "patient_name", "age", "recovery_time"]
    ]
    return out.sort_values(by=["recovery_time", "patient_name"]).reset_index(drop=True)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
