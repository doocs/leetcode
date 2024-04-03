# [3056. 快照分析](https://leetcode.cn/problems/snaps-analysis)

[English Version](/solution/3000-3099/3056.Snaps%20Analysis/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Activities</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| activity_id   | int     |
| user_id       | int     |
| activity_type | enum    |
| time_spent    | decimal |
+---------------+---------+
activity_id 是这张表中值互不相同的列。
activity_type 是一个 ('send', 'open') 的 ENUM (category)。
这张表包含 activity id，user id，activity type 和 time spent。
</pre>

<p>表：<code>Age</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| age_bucket  | enum |
+-------------+------+
user_id 是这张表中有不同值的列。
age_bucket 是一个 ('21-25', '26-30', '31-35') 的 ENUM (category)。
这张表包含 user id 和 age 组。</pre>

<p>编写一个解决方案来计算 <strong>每个年龄组</strong> <strong>发送</strong> 和 <strong>打开快照</strong> 所花费的总时间 <strong>百分比</strong>。百分比应 <strong>四舍五入</strong> 至小数点后 <code>2</code> 位。</p>

<p>以 <strong>任何</strong> 顺序返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Activities 表：
+-------------+---------+---------------+------------+
| activity_id | user_id | activity_type | time_spent |
+-------------+---------+---------------+------------+
| 7274        | 123     | open          | 4.50       | 
| 2425        | 123     | send          | 3.50       | 
| 1413        | 456     | send          | 5.67       | 
| 2536        | 456     | open          | 3.00       | 
| 8564        | 456     | send          | 8.24       | 
| 5235        | 789     | send          | 6.24       | 
| 4251        | 123     | open          | 1.25       | 
| 1435        | 789     | open          | 5.25       | 
+-------------+---------+---------------+------------+
Age 表：
+---------+------------+
| user_id | age_bucket | 
+---------+------------+
| 123     | 31-35      | 
| 789     | 21-25      | 
| 456     | 26-30      | 
+---------+------------+
<strong>输出：</strong> 
+------------+-----------+-----------+
| age_bucket | send_perc | open_perc |
+------------+-----------+-----------+
| 31-35      | 37.84     | 62.16     |
| 26-30      | 82.26     | 17.74     |
| 21-25      | 54.31     | 45.69     |
+------------+-----------+-----------+
<strong>解释：</strong> 
对于年龄组 31-35：
  - 只有一个用户属于该组，用户 ID 为 123。
  - 该用户花费在发送快照上的总时间为 3.50，并且花费在打开快照上的时间为 4.50 + 1.25 = 5.75。
  - 用户花费的总时间为 3.50 + 5.75 = 9.25。
  - 因此，发送快照百分比为 (3.50 / 9.25) * 100 = 37.84，并且打开快照百分比为 (5.75 / 9.25) * 100 = 62.16。
对于年龄组 26-30：
  - 只有一个用户属于该组，用户 ID 为 456。
  - 该用户花费在发送快照上的总时间为 5.67 + 8.24 = 13.91，并且花费在打开快照上的时间为 3.00。
  - 用户花费的总时间为 13.91 + 3.00 = 16.91。
  - 因此，发送快照百分比为 (13.91 / 16.91) * 100 = 82.26，并且打开快照百分比为 (3.00 / 16.91) * 100 = 17.74。
对于年龄组 21-25：
  - 只有一个用户属于该组，用户 ID 为 789。
  - 该用户花费在发送快照上的总时间为 6.24，并且花费在打开快照上的时间为 5.25。
  - 用户花费的总时间为 6.24 + 5.25 = 11.49。
  - 因此，发送快照百分比为 (6.24 / 11.49) * 100 = 54.31，并且打开快照百分比为 (5.25 / 11.49) * 100 = 45.69。
输出表中的所有百分比舍入到两位。
</pre>

## 解法

### 方法一：等值连接 + 分组求和

我们可以通过等值连接，将 `Activities` 表和 `Age` 表按照 `user_id` 进行连接，然后再按照 `age_bucket` 进行分组，最后计算每个年龄段的发送和打开的百分比。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    age_bucket,
    ROUND(100 * SUM(IF(activity_type = 'send', time_spent, 0)) / SUM(time_spent), 2) AS send_perc,
    ROUND(100 * SUM(IF(activity_type = 'open', time_spent, 0)) / SUM(time_spent), 2) AS open_perc
FROM
    Activities
    JOIN Age USING (user_id)
GROUP BY 1;
```

```python
import pandas as pd


def snap_analysis(activities: pd.DataFrame, age: pd.DataFrame) -> pd.DataFrame:
    merged_df = pd.merge(activities, age, on="user_id")
    total_time_per_age_activity = (
        merged_df.groupby(["age_bucket", "activity_type"])["time_spent"]
        .sum()
        .reset_index()
    )
    pivot_df = total_time_per_age_activity.pivot(
        index="age_bucket", columns="activity_type", values="time_spent"
    ).reset_index()
    pivot_df = pivot_df.fillna(0)
    pivot_df["send_perc"] = round(
        100 * pivot_df["send"] / (pivot_df["send"] + pivot_df["open"]), 2
    )
    pivot_df["open_perc"] = round(
        100 * pivot_df["open"] / (pivot_df["send"] + pivot_df["open"]), 2
    )
    return pivot_df[["age_bucket", "send_perc", "open_perc"]]
```

<!-- tabs:end -->

<!-- end -->
