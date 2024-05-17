---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1097.Game%20Play%20Analysis%20V/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1097. 游戏玩法分析 V 🔒](https://leetcode.cn/problems/game-play-analysis-v)

[English Version](/solution/1000-1099/1097.Game%20Play%20Analysis%20V/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Activity</code>&nbsp;</p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
（player_id，event_date）是此表的主键(具有唯一值的列的组合)
这张表显示了某些游戏的玩家的活动情况
每一行表示一个玩家的记录，在某一天使用某个设备注销之前，登录并玩了很多游戏（可能是 0）
</pre>

<p>&nbsp;</p>

<p>玩家的 <strong>安装日期</strong> 定义为该玩家的第一个登录日。</p>

<p>我们将日期 x&nbsp;的 <strong>第一天留存率</strong> 定义为：假定安装日期为 <code>X</code>&nbsp;的玩家的数量为 <code>N</code> ，其中在 <code>X</code>&nbsp;之后的一天重新登录的玩家数量为 <code>M</code>，<code>M/N</code> 就是第一天留存率，<strong>四舍五入到小数点后两位</strong>。</p>

<p>编写解决方案，报告所有安装日期、当天安装游戏的玩家数量和玩家的&nbsp;<strong>第一天留存率</strong>。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Activity 表：
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-01 | 0            |
| 3         | 4         | 2016-07-03 | 5            |
+-----------+-----------+------------+--------------+
<strong>输出：</strong>
+------------+----------+----------------+
| install_dt | installs | Day1_retention |
+------------+----------+----------------+
| 2016-03-01 | 2        | 0.50           |
| 2017-06-25 | 1        | 0.00           |
+------------+----------+----------------+
<strong>解释：</strong>
玩家 1 和 3 在 2016-03-01 安装了游戏，但只有玩家 1 在 2016-03-02 重新登录，所以 2016-03-01 的第一天留存率是 1/2=0.50
玩家 2 在 2017-06-25 安装了游戏，但在 2017-06-26 没有重新登录，因此 2017-06-25 的第一天留存率为 0/1=0.00
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            player_id,
            event_date,
            MIN(event_date) OVER (PARTITION BY player_id) AS install_dt
        FROM Activity
    )
SELECT
    install_dt,
    COUNT(DISTINCT player_id) AS installs,
    ROUND(
        SUM(DATEDIFF(event_date, install_dt) = 1) / COUNT(DISTINCT player_id),
        2
    ) AS day1_retention
FROM T
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
