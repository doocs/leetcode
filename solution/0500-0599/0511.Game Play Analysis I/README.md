# [511. 游戏玩法分析 I](https://leetcode.cn/problems/game-play-analysis-i)

[English Version](/solution/0500-0599/0511.Game%20Play%20Analysis%20I/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>活动表&nbsp;<code>Activity</code>：</p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
在 SQL 中，表的主键是 (player_id, event_date)。
这张表展示了一些游戏玩家在游戏平台上的行为活动。
每行数据记录了一名玩家在退出平台之前，当天使用同一台设备登录平台后打开的游戏的数目（可能是 0 个）。
</pre>

<p>&nbsp;</p>

<p>查询每位玩家 <strong>第一次登录平台的日期</strong>。</p>

<p>查询结果的格式如下所示：</p>

<pre>
Activity 表：
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+

Result 表：
+-----------+-------------+
| player_id | first_login |
+-----------+-------------+
| 1         | 2016-03-01  |
| 2         | 2017-06-25  |
| 3         | 2016-03-02  |
+-----------+-------------+
</pre>

## 解法

### 方法一：分组求最小值

我们可以用 `GROUP BY` 对 `player_id` 进行分组，然后取每一组中最小的 `event_date` 作为玩家第一次登录平台的日期。

<!-- tabs:start -->

```python
import pandas as pd


def game_analysis(activity: pd.DataFrame) -> pd.DataFrame:
    return (
        activity.groupby("player_id")
        .agg(first_login=("event_date", "min"))
        .reset_index()
    )
```

```sql
# Write your MySQL query statement below
SELECT player_id, MIN(event_date) AS first_login
FROM Activity
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
