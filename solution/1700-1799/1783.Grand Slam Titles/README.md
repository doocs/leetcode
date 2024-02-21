# [1783. 大满贯数量](https://leetcode.cn/problems/grand-slam-titles)

[English Version](/solution/1700-1799/1783.Grand%20Slam%20Titles/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Players</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| player_id      | int     |
| player_name    | varchar |
+----------------+---------+
player_id 是这个表的主键（具有唯一值的列）
这个表的每一行给出一个网球运动员的 ID 和 姓名
</pre>

<p>&nbsp;</p>

<p>表：<code>Championships</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| year          | int     |
| Wimbledon     | int     |
| Fr_open       | int     |
| US_open       | int     |
| Au_open       | int     |
+---------------+---------+
year 是这个表的主键（具有唯一值的列）
该表的每一行都包含在每场大满贯网球比赛中赢得比赛的球员的 ID
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出每一个球员赢得大满贯比赛的次数。结果不包含没有赢得比赛的球员的ID 。</p>

<p>结果集 <strong>无顺序要求</strong> 。</p>

<p>结果的格式，如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Players 表：
+-----------+-------------+
| player_id | player_name |
+-----------+-------------+
| 1         | Nadal       |
| 2         | Federer     |
| 3         | Novak       |
+-----------+-------------+
Championships 表：
+------+-----------+---------+---------+---------+
| year | Wimbledon | Fr_open | US_open | Au_open |
+------+-----------+---------+---------+---------+
| 2018 | 1         | 1       | 1       | 1       |
| 2019 | 1         | 1       | 2       | 2       |
| 2020 | 2         | 1       | 2       | 2       |
+------+-----------+---------+---------+---------+
<strong>输出：</strong>
+-----------+-------------+-------------------+
| player_id | player_name | grand_slams_count |
+-----------+-------------+-------------------+
| 2         | Federer     | 5                 |
| 1         | Nadal       | 7                 |
+-----------+-------------+-------------------+
<strong>解释：</strong>
Player 1 (Nadal) 获得了 7 次大满贯：其中温网 2 次(2018, 2019), 法国公开赛 3 次 (2018, 2019, 2020), 美国公开赛 1 次 (2018)以及澳网公开赛 1 次 (2018) 。
Player 2 (Federer) 获得了 5 次大满贯：其中温网 1 次 (2020), 美国公开赛 2 次 (2019, 2020) 以及澳网公开赛 2 次 (2019, 2020) 。
Player 3 (Novak)  没有赢得，因此不包含在结果集中。</pre>

## 解法

### 方法一：合并 + 等值连接 + 分组

我们可以使用 `UNION ALL`，将所有赢得大满贯比赛的球员 ID 合并到一张表 `T` 中，然后使用等值连接 `JOIN`，将 `T` 表与 `Players` 表按照 `player_id` 进行连接，最后使用 `GROUP BY` 和 `COUNT` 统计每个球员赢得大满贯比赛的次数。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT Wimbledon AS player_id
        FROM Championships
        UNION ALL
        SELECT Fr_open AS player_id
        FROM Championships
        UNION ALL
        SELECT US_open AS player_id
        FROM Championships
        UNION ALL
        SELECT Au_open AS player_id
        FROM Championships
    )
SELECT player_id, player_name, COUNT(1) AS grand_slams_count
FROM
    T
    JOIN Players USING (player_id)
GROUP BY 1;
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    player_id,
    player_name,
    SUM(
        (
            CASE
                WHEN Wimbledon = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Fr_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN US_open = player_id THEN 1
                ELSE 0
            END
        ) + (
            CASE
                WHEN Au_open = player_id THEN 1
                ELSE 0
            END
        )
    ) AS grand_slams_count
FROM
    Championships
    CROSS JOIN Players
GROUP BY player_id
HAVING grand_slams_count > 0;
```

<!-- tabs:end -->

<!-- end -->
