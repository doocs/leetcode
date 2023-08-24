# [534. 游戏玩法分析 III](https://leetcode.cn/problems/game-play-analysis-iii)

[English Version](/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：SUM() OVER() 窗口函数**

我们可以使用 `SUM() OVER()` 窗口函数来计算每个玩家到目前为止玩了多少游戏。在 `OVER()` 子句中，我们使用 `PARTITION BY` 子句将玩家分组，然后使用 `ORDER BY` 子句按日期排序。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    event_date,
    sum(games_played) OVER (
        PARTITION BY player_id
        ORDER BY event_date
    ) AS games_played_so_far
FROM Activity;
```

<!-- tabs:end -->
