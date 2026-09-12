---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2173.Longest%20Winning%20Streak/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2173. 最多连胜的次数 🔒](https://leetcode.cn/problems/longest-winning-streak)

[English Version](/solution/2100-2199/2173.Longest%20Winning%20Streak/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Matches</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| player_id   | int  |
| match_day   | date |
| result      | enum |
+-------------+------+
(player_id, match_day) 是该表的主键（具有唯一值的列的组合）。
每一行包括了：参赛选手 id、 比赛时间、 比赛结果。
比赛结果（result）的枚举类型为 ('Win', 'Draw', 'Lose')。</pre>

<p>&nbsp;</p>

<p>选手的&nbsp;<strong>连胜数</strong> 是指连续获胜的次数，且没有被平局或输球中断。</p>

<p>编写解决方案来计算每个参赛选手最多的<strong>连胜数</strong>。</p>

<p>结果可以以 <strong>任何顺序</strong> 返回。</p>

<p>结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Matches 表:
+-----------+------------+--------+
| player_id | match_day  | result |
+-----------+------------+--------+
| 1         | 2022-01-17 | Win    |
| 1         | 2022-01-18 | Win    |
| 1         | 2022-01-25 | Win    |
| 1         | 2022-01-31 | Draw   |
| 1         | 2022-02-08 | Win    |
| 2         | 2022-02-06 | Lose   |
| 2         | 2022-02-08 | Lose   |
| 3         | 2022-03-30 | Win    |
+-----------+------------+--------+
<strong>输出:</strong> 
+-----------+----------------+
| player_id | longest_streak |
+-----------+----------------+
| 1         | 3              |
| 2         | 0              |
| 3         | 1              |
+-----------+----------------+
<strong>解释:</strong> 
Player 1:
从 2022-01-17 到 2022-01-25, player 1连续赢了三场比赛。
2022-01-31, player 1 平局.
2022-02-08, player 1 赢了一场比赛。
最多连胜了三场比赛。

Player 2:
从 2022-02-06 到 2022-02-08, player 2 输了两场比赛。
最多连赢了0场比赛。

Player 3:
2022-03-30, player 3 赢了一场比赛。
最多连赢了一场比赛。
</pre>

<p>&nbsp;</p>

<p><strong>进阶:</strong> 如果我们想计算最长的连续不输的次数（即获胜或平局），你将如何调整？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 求每名选手连续获胜的最长段。行按比赛日排列后，连续相同结果可用「名次差」分组：同一选手内按日期的行号减去按（选手、结果）的行号，在一段连续相同结果中为常数。
>
> 对该差值为键分组，统计其中 $\textit{result}='Win'$ 的行数，再按选手取最大。
>
> 窗口函数完成分组，外层聚合得到 $\textit{longest\_streak}$。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            *,
            ROW_NUMBER() OVER (
                PARTITION BY player_id
                ORDER BY match_day
            ) - ROW_NUMBER() OVER (
                PARTITION BY player_id, result
                ORDER BY match_day
            ) AS rk
        FROM Matches
    ),
    T AS (
        SELECT player_id, SUM(result = 'Win') AS s
        FROM S
        GROUP BY player_id, rk
    )
SELECT player_id, MAX(s) AS longest_streak
FROM T
GROUP BY player_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
