---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3673.Find%20Zombie%20Sessions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3673. Find Zombie Sessions](https://leetcode.cn/problems/find-zombie-sessions)

[English Version](/solution/3600-3699/3673.Find%20Zombie%20Sessions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>app_events</code></p>

<pre>
+------------------+----------+
| Column Name      | Type     | 
+------------------+----------+
| event_id         | int      |
| user_id          | int      |
| event_timestamp  | datetime |
| event_type       | varchar  |
| session_id       | varchar  |
| event_value      | int      |
+------------------+----------+
event_id is the unique identifier for this table.
event_type can be app_open, click, scroll, purchase, or app_close.
session_id groups events within the same user session.
event_value represents: for purchase - amount in dollars, for scroll - pixels scrolled, for others - NULL.
</pre>

<p>Write a solution to identify <strong>zombie sessions,&nbsp;</strong>sessions where users appear active but show abnormal behavior patterns. A session is considered a <strong>zombie session</strong> if it meets ALL the following criteria:</p>

<ul>
	<li>The session duration is <strong>more than</strong> <code>30</code> minutes.</li>
	<li>Has <strong>at least</strong> <code>5</code> scroll events.</li>
	<li>The <strong>click-to-scroll ratio</strong> is less than <code>0.20</code> .</li>
	<li><strong>No purchases</strong> were made during the session.</li>
</ul>

<p>Return <em>the result table ordered by</em>&nbsp;<code>scroll_count</code> <em>in <strong>descending</strong> order, then by</em> <code>session_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>app_events table:</p>

<pre class="example-io">
+----------+---------+---------------------+------------+------------+-------------+
| event_id | user_id | event_timestamp     | event_type | session_id | event_value |
+----------+---------+---------------------+------------+------------+-------------+
| 1        | 201     | 2024-03-01 10:00:00 | app_open   | S001       | NULL        |
| 2        | 201     | 2024-03-01 10:05:00 | scroll     | S001       | 500         |
| 3        | 201     | 2024-03-01 10:10:00 | scroll     | S001       | 750         |
| 4        | 201     | 2024-03-01 10:15:00 | scroll     | S001       | 600         |
| 5        | 201     | 2024-03-01 10:20:00 | scroll     | S001       | 800         |
| 6        | 201     | 2024-03-01 10:25:00 | scroll     | S001       | 550         |
| 7        | 201     | 2024-03-01 10:30:00 | scroll     | S001       | 900         |
| 8        | 201     | 2024-03-01 10:35:00 | app_close  | S001       | NULL        |
| 9        | 202     | 2024-03-01 11:00:00 | app_open   | S002       | NULL        |
| 10       | 202     | 2024-03-01 11:02:00 | click      | S002       | NULL        |
| 11       | 202     | 2024-03-01 11:05:00 | scroll     | S002       | 400         |
| 12       | 202     | 2024-03-01 11:08:00 | click      | S002       | NULL        |
| 13       | 202     | 2024-03-01 11:10:00 | scroll     | S002       | 350         |
| 14       | 202     | 2024-03-01 11:15:00 | purchase   | S002       | 50          |
| 15       | 202     | 2024-03-01 11:20:00 | app_close  | S002       | NULL        |
| 16       | 203     | 2024-03-01 12:00:00 | app_open   | S003       | NULL        |
| 17       | 203     | 2024-03-01 12:10:00 | scroll     | S003       | 1000        |
| 18       | 203     | 2024-03-01 12:20:00 | scroll     | S003       | 1200        |
| 19       | 203     | 2024-03-01 12:25:00 | click      | S003       | NULL        |
| 20       | 203     | 2024-03-01 12:30:00 | scroll     | S003       | 800         |
| 21       | 203     | 2024-03-01 12:40:00 | scroll     | S003       | 900         |
| 22       | 203     | 2024-03-01 12:50:00 | scroll     | S003       | 1100        |
| 23       | 203     | 2024-03-01 13:00:00 | app_close  | S003       | NULL        |
| 24       | 204     | 2024-03-01 14:00:00 | app_open   | S004       | NULL        |
| 25       | 204     | 2024-03-01 14:05:00 | scroll     | S004       | 600         |
| 26       | 204     | 2024-03-01 14:08:00 | scroll     | S004       | 700         |
| 27       | 204     | 2024-03-01 14:10:00 | click      | S004       | NULL        |
| 28       | 204     | 2024-03-01 14:12:00 | app_close  | S004       | NULL        |
+----------+---------+---------------------+------------+------------+-------------+
</pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+------------+---------+--------------------------+--------------+
| session_id | user_id | session_duration_minutes | scroll_count |
+------------+---------+--------------------------+--------------+
| S001       | 201     | 35                       | 6            |
+------------+---------+--------------------------+--------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Session S001 (User 201)</strong>:

    <ul>
    	<li>Duration: 10:00:00 to 10:35:00 = 35 minutes (more than 30)&nbsp;</li>
    	<li>Scroll events: 6 (at least 5)&nbsp;</li>
    	<li>Click events: 0</li>
    	<li>Click-to-scroll ratio: 0/6 = 0.00 (less than 0.20)&nbsp;</li>
    	<li>Purchases: 0 (no purchases)&nbsp;</li>
    	<li>S001 is a zombie session (meets all criteria)</li>
    </ul>
    </li>
    <li><strong>Session S002 (User 202)</strong>:
    <ul>
    	<li>Duration: 11:00:00 to 11:20:00 = 20 minutes (less than 30)&nbsp;</li>
    	<li>Has a purchase event&nbsp;</li>
    	<li>S002 is&nbsp;not a zombie session&nbsp;</li>
    </ul>
    </li>
    <li><strong>Session S003 (User 203)</strong>:
    <ul>
    	<li>Duration: 12:00:00 to 13:00:00 = 60 minutes (more than 30)&nbsp;</li>
    	<li>Scroll events: 5 (at least 5)&nbsp;</li>
    	<li>Click events: 1</li>
    	<li>Click-to-scroll ratio: 1/5 = 0.20 (not less than 0.20)&nbsp;</li>
    	<li>Purchases: 0 (no purchases)&nbsp;</li>
    	<li>S003 is&nbsp;not a zombie session (click-to-scroll ratio equals 0.20, needs to be less)</li>
    </ul>
    </li>
    <li><strong>Session S004 (User 204)</strong>:
    <ul>
    	<li>Duration: 14:00:00 to 14:12:00 = 12 minutes (less than 30)&nbsp;</li>
    	<li>Scroll events: 2 (less than 5)&nbsp;</li>
    	<li>S004&nbsp; is not a zombie session&nbsp;</li>
    </ul>
    </li>

</ul>

<p>The result table is ordered by scroll_count in descending order, then by session_id in ascending order.</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们可以将会话按照 `session_id` 进行分组，统计每个会话的持续时间、滚动事件数、点击事件数和购买事件数，然后根据题目中的条件进行筛选，最后按照滚动事件数降序、会话 ID 升序排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    session_id,
    user_id,
    TIMESTAMPDIFF(MINUTE, MIN(event_timestamp), MAX(event_timestamp)) session_duration_minutes,
    SUM(event_type = 'scroll') scroll_count
FROM app_events
GROUP BY session_id
HAVING
    session_duration_minutes >= 30
    AND SUM(event_type = 'click') / SUM(event_type = 'scroll') < 0.2
    AND SUM(event_type = 'purchase') = 0
    AND SUM(event_type = 'scroll') >= 5
ORDER BY scroll_count DESC, session_id;
```

#### Pandas

```python
import pandas as pd


def find_zombie_sessions(app_events: pd.DataFrame) -> pd.DataFrame:
    if not pd.api.types.is_datetime64_any_dtype(app_events["event_timestamp"]):
        app_events["event_timestamp"] = pd.to_datetime(app_events["event_timestamp"])

    grouped = app_events.groupby(["session_id", "user_id"])

    result = grouped.agg(
        session_duration_minutes=(
            "event_timestamp",
            lambda x: (x.max() - x.min()).total_seconds() // 60,
        ),
        scroll_count=("event_type", lambda x: (x == "scroll").sum()),
        click_count=("event_type", lambda x: (x == "click").sum()),
        purchase_count=("event_type", lambda x: (x == "purchase").sum()),
    ).reset_index()

    result = result[
        (result["session_duration_minutes"] >= 30)
        & (result["click_count"] / result["scroll_count"] < 0.2)
        & (result["purchase_count"] == 0)
        & (result["scroll_count"] >= 5)
    ]

    result = result.sort_values(
        by=["scroll_count", "session_id"], ascending=[False, True]
    ).reset_index(drop=True)

    return result[["session_id", "user_id", "session_duration_minutes", "scroll_count"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
