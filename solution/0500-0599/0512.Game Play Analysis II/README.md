---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [512. 游戏玩法分析 II 🔒](https://leetcode.cn/problems/game-play-analysis-ii)

[English Version](/solution/0500-0599/0512.Game%20Play%20Analysis%20II/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：子查询

<!-- thinking:start -->

> **思考**
>
> 在得到每人首次登录日之后，还要取出当天的 `device_id`。若直接 `GROUP BY` 设备，无法保证设备与最小日期同行。
>
> 子查询先按玩家求出 `MIN(event_date)`，外层用 `(player_id, event_date)` 配对把对应设备筛出。联合键保证取到的是首次登录那一行，而不是任意一行。

<!-- thinking:end -->

我们可以使用 `GROUP BY` 和 `MIN` 函数来找到每个玩家的第一次登录日期，然后使用联合键子查询来找到每个玩家的第一次登录设备。

<!-- tabs:start -->

#### MySQL

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

<!-- solution:end -->

<!-- solution:start -->

### 方法二：窗口函数

<!-- thinking:start -->

> **思考**
>
> 子查询需要一次聚合再回表匹配。窗口函数可以在原表上按玩家按日期编号，直接留下排名为 $1$ 的行。
>
> `RANK() OVER (PARTITION BY player_id ORDER BY event_date)` 把首次登录标成 $1$，外层过滤即可。语义与方法一相同，少一次显式自连接。

<!-- thinking:end -->

我们可以使用窗口函数 `rank()`，它可以为每个玩家的每个登录日期分配一个排名，然后我们可以选择排名为 $1$ 的行。

<!-- tabs:start -->

#### MySQL

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

<!-- solution:end -->

<!-- problem:end -->
