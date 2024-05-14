# [3060. 时间范围内的用户活动 🔒](https://leetcode.cn/problems/user-activities-within-time-bounds)

[English Version](/solution/3000-3099/3060.User%20Activities%20within%20Time%20Bounds/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Sessions</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| user_id       | int      |
| session_start | datetime |
| session_end   | datetime |
| session_id    | int      |
| session_type  | enum     |
+---------------+----------+
session_id 是这张表中有不同值的列。
session_type 是 (Viewer, Streamer) 的一个 ENUM (category) 类型。
这张表包含 user id, session start, session end, session id 和 session 类型。
</pre>

<p>编写一个解决方案，以查找 <strong>至少有一个相同</strong> 类型的 <strong>连续会话</strong>（无论是“<strong>Viewer</strong>”还是“<strong>Streamer</strong>”）的 <strong>用户</strong>，会话 <strong>之间</strong> 的 <strong>最大</strong> 间隔为 <code>12</code> 小时。</p>

<p>返回结果表，以<em>&nbsp;</em><code>user_id</code><em>&nbsp;<strong>升序</strong> 排序。</em></p>

<p>结果格式如下所述。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<pre>
<strong>输入:</strong> 
Sessions 表:
+---------+---------------------+---------------------+------------+--------------+
| user_id | session_start       | session_end         | session_id | session_type | 
+---------+---------------------+---------------------+------------+--------------+
| 101     | 2023-11-01 08:00:00 | 2023-11-01 09:00:00 | 1          | Viewer       |  
| 101     | 2023-11-01 10:00:00 | 2023-11-01 11:00:00 | 2          | Streamer     |   
| 102     | 2023-11-01 13:00:00 | 2023-11-01 14:00:00 | 3          | Viewer       | 
| 102     | 2023-11-01 15:00:00 | 2023-11-01 16:00:00 | 4          | Viewer       | 
| 101     | 2023-11-02 09:00:00 | 2023-11-02 10:00:00 | 5          | Viewer       | 
| 102     | 2023-11-02 12:00:00 | 2023-11-02 13:00:00 | 6          | Streamer     | 
| 101     | 2023-11-02 13:00:00 | 2023-11-02 14:00:00 | 7          | Streamer     | 
| 102     | 2023-11-02 16:00:00 | 2023-11-02 17:00:00 | 8          | Viewer       | 
| 103     | 2023-11-01 08:00:00 | 2023-11-01 09:00:00 | 9          | Viewer       | 
| 103     | 2023-11-02 20:00:00 | 2023-11-02 23:00:00 | 10         | Viewer       | 
| 103     | 2023-11-03 09:00:00 | 2023-11-03 10:00:00 | 11         | Viewer       | 
+---------+---------------------+---------------------+------------+--------------+
<strong>输出:</strong> 
+---------+
| user_id |
+---------+
| 102     |
| 103     |
+---------+
<strong>解释:</strong>
- 用户 ID 101 将不会包含在最终输出中，因为他们没有相同会话类型的连续回话。
- 用户 ID 102 将会包含在最终输出中，因为他们分别有两个 session ID 为 3 和 4 的 viewer 会话，并且时间间隔在 12 小时内。
- 用户 ID 103 参与了两次 viewer 会话，间隔不到 12 小时，会话 ID 为 10 和 11。因此，用户 103 将会被包含在最终输出中。
输出表根据 user_id 升序排列。
</pre>

## 解法

### 方法一：窗口函数 + 时间函数

我们先使用 `LAG` 窗口函数，找到每个用户相同类型的会话的上一个会话的结束时间，记为 `prev_session_end`。然后我们使用 `TIMESTAMPDIFF` 函数计算当前会话的开始时间与上一个会话的结束时间的时间差，如果时间差小于等于 12 小时，那么这个用户就符合题目要求。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            session_start,
            LAG(session_end) OVER (
                PARTITION BY user_id, session_type
                ORDER BY session_end
            ) AS prev_session_end
        FROM Sessions
    )
SELECT DISTINCT
    user_id
FROM T
WHERE TIMESTAMPDIFF(HOUR, prev_session_end, session_start) <= 12;
```

```python
import pandas as pd


def user_activities(sessions: pd.DataFrame) -> pd.DataFrame:
    sessions = sessions.sort_values(by=["user_id", "session_start"])
    sessions["prev_session_end"] = sessions.groupby(["user_id", "session_type"])[
        "session_end"
    ].shift(1)
    sessions_filtered = sessions[
        sessions["session_start"] - sessions["prev_session_end"]
        <= pd.Timedelta(hours=12)
    ]
    return pd.DataFrame({"user_id": sessions_filtered["user_id"].unique()})
```

<!-- tabs:end -->

<!-- end -->
