# [512. 游戏玩法分析 II 🔒](https://leetcode.cn/problems/game-play-analysis-ii)

[English Version](/solution/0500-0599/0512.Game%20Play%20Analysis%20II/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Table:&nbsp;<code>Activity</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) 是这个表的两个主键(具有唯一值的列的组合)
这个表显示的是某些游戏玩家的游戏活动情况
每一行是在某天使用某个设备登出之前登录并玩多个游戏（可能为0）的玩家的记录
</pre>

<p>请编写解决方案，描述每一个玩家首次登陆的设备名称</p>

<p>返回结果格式如以下示例：</p>

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
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
<strong>输出：</strong>
+-----------+-----------+
| player_id | device_id |
+-----------+-----------+
| 1         | 2         |
| 2         | 3         |
| 3         | 1         |
+-----------+-----------+</pre>

## 解法

### 方法一：子查询

我们可以使用 `GROUP BY` 和 `MIN` 函数来找到每个玩家的第一次登录日期，然后使用联合键子查询来找到每个玩家的第一次登录设备。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    device_id
FROM Activity
WHERE
    (player_id, event_date) IN (
        SELECT
            player_id,
            MIN(event_date) AS event_date
        FROM Activity
        GROUP BY 1
    );
```

<!-- tabs:end -->

### 方法二：窗口函数

我们可以使用窗口函数 `rank()`，它可以为每个玩家的每个登录日期分配一个排名，然后我们可以选择排名为 $1$ 的行。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY player_id
                ORDER BY event_date
            ) AS rk
        FROM Activity
    )
SELECT player_id, device_id
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->

<!-- end -->
