---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3673.Find%20Zombie%20Sessions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3673. 查找僵尸会话](https://leetcode.cn/problems/find-zombie-sessions)

[English Version](/solution/3600-3699/3673.Find%20Zombie%20Sessions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>app_events</code></p>

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
event_id 是这张表的唯一主键。
event_type 可以是 app_open，click，scroll，purchase 或 app_close。
session_id 将事件按同一用户会话分组。
event_value 表示：对于 purchase - 美元金额，对于 scroll - 滚动的像素数，对于其它 - NULL。
</pre>

<p>编写一个解决方案来识别 <strong>僵尸会话</strong>，即用户看似活跃但表现出异常行为模式的会话。如果会话满足以下所有条件，则被视为 <strong>僵尸会话</strong>：</p>

<ul>
	<li>会话时长&nbsp;<strong>超过</strong>&nbsp;<code>30</code>&nbsp;分钟。</li>
	<li>至少有 <code>5</code> 次滚动事件。</li>
	<li>点击滚动比率低于 <code>0.20</code>。</li>
	<li>会话期间 <strong>没有进行任何购买</strong>。</li>
</ul>

<p>返回结果表按&nbsp;<code>scroll_count</code> <strong>降序</strong>&nbsp;排序，然后按&nbsp;<code>session_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p>返回格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>app_events 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+---------+--------------------------+--------------+
| session_id | user_id | session_duration_minutes | scroll_count |
+------------+---------+--------------------------+--------------+
| S001       | 201     | 35                       | 6            |
+------------+---------+--------------------------+--------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>会话 S001 (User 201)</strong>:

    <ul>
    	<li>时长：10:00:00 到 10:35:00 = 35 分钟（大于 30 分钟）</li>
    	<li>滚动事件：6（至少 5 次）</li>
    	<li>点击事件：0</li>
    	<li>点击滚动比率：0/6 = 0.00（少于 0.20）</li>
    	<li>购买数：0（没有购买）</li>
    	<li>S001 是一个僵尸会话（满足所有条件）</li>
    </ul>
    </li>
    <li><strong>会话 S002 (User 202)</strong>:
    <ul>
    	<li>时长：11:00:00 到 11:20:00 = 20 分钟（少于 30 分钟）</li>
    	<li>有一次购买事件</li>
    	<li>S002 不是一个僵尸会话</li>
    </ul>
    </li>
    <li><strong>会话 S003 (User 203)</strong>:
    <ul>
    	<li>时长：12:00:00 到 13:00:00 = 60 分钟（超过 30 分钟）</li>
    	<li>滚动事件：5（至少 5 次）</li>
    	<li>点击事件：1</li>
    	<li>点击滚动比率：1/5 = 0.20（不少于 0.20）</li>
    	<li>购买数：0（没有购买）</li>
    	<li>S003 不是一个僵尸会话（点击滚动比率等于 0.20，需要更少）。</li>
    </ul>
    </li>
    <li><strong>会话 S004 (User 204)</strong>:
    <ul>
    	<li>时长：14:00:00 到 14:12:00 = 12 分钟（少于 30 分钟）</li>
    	<li>滚动事件：2（少于&nbsp;5 次）</li>
    	<li>S004&nbsp; 不是一个僵尸会话</li>
    </ul>
    </li>

</ul>

<p>结果表按 scroll_count 降序排序，然后按 session_id 升序排序。</p>
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
