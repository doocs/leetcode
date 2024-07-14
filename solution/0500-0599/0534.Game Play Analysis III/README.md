---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [534. 游戏玩法分析 III 🔒](https://leetcode.cn/problems/game-play-analysis-iii)

[English Version](/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
（player_id，event_date）是此表的主键（具有唯一值的列）。
这张表显示了某些游戏的玩家的活动情况。
每一行是一个玩家的记录，他在某一天使用某个设备注销之前登录并玩了很多游戏（可能是 0 ）。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，同时报告每组玩家和日期，以及玩家到 <strong>目前为止</strong> 玩了多少游戏。也就是说，玩家在该日期之前所玩的游戏总数。详细情况请查看示例。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-05-02 | 6            |
| 1         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
<strong>输出：</strong>
+-----------+------------+---------------------+
| player_id | event_date | games_played_so_far |
+-----------+------------+---------------------+
| 1         | 2016-03-01 | 5                   |
| 1         | 2016-05-02 | 11                  |
| 1         | 2017-06-25 | 12                  |
| 3         | 2016-03-02 | 0                   |
| 3         | 2018-07-03 | 5                   |
+-----------+------------+---------------------+
<strong>解释：</strong>
对于 ID 为 1 的玩家，2016-05-02 共玩了 5+6=11 个游戏，2017-06-25 共玩了 5+6+1=12 个游戏。
对于 ID 为 3 的玩家，2018-07-03 共玩了 0+5=5 个游戏。
请注意，对于每个玩家，我们只关心玩家的登录日期。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：使用窗口函数

我们可以使用窗口函数 `SUM() OVER()`，按照 `player_id` 分组，按照 `event_date` 排序，计算每个用户截止到当前日期的游戏总数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    event_date,
    SUM(games_played) OVER (
        PARTITION BY player_id
        ORDER BY event_date
    ) AS games_played_so_far
FROM Activity;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：使用自连接 + 分组

我们也可以使用自连接，将 `Activity` 表自连接，连接条件为 `t1.player_id = t2.player_id AND t1.event_date >= t2.event_date`，然后按照 `t1.player_id` 和 `t1.event_date` 分组，累计 `t2.games_played`，得到每个用户截止到当前日期的游戏总数。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    t1.player_id,
    t1.event_date,
    SUM(t2.games_played) AS games_played_so_far
FROM
    Activity AS t1,
    Activity AS t2
WHERE t1.player_id = t2.player_id AND t1.event_date >= t2.event_date
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    t1.player_id,
    t1.event_date,
    SUM(t2.games_played) AS games_played_so_far
FROM
    Activity AS t1
    CROSS JOIN Activity AS t2 ON t1.player_id = t2.player_id AND t1.event_date >= t2.event_date
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
