# [550. 游戏玩法分析 IV](https://leetcode.cn/problems/game-play-analysis-iv)

[English Version](/solution/0500-0599/0550.Game%20Play%20Analysis%20IV/README_EN.md)

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
（player_id，event_date）是此表的主键（具有唯一值的列的组合）。
这张表显示了某些游戏的玩家的活动情况。
每一行是一个玩家的记录，他在某一天使用某个设备注销之前登录并玩了很多游戏（可能是 0）。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，报告在首次登录的第二天再次登录的玩家的 <strong>比率</strong>，<strong>四舍五入到小数点后两位</strong>。换句话说，你需要计算从首次登录日期开始至少连续两天登录的玩家的数量，然后除以玩家总数。</p>

<p>结果格式如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
<strong>输出：</strong>
+-----------+
| fraction  |
+-----------+
| 0.33      |
+-----------+
<strong>解释：</strong>
只有 ID 为 1 的玩家在第一天登录后才重新登录，所以答案是 1/3 = 0.33
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT round(AVG(b.event_date IS NOT NULL), 2) AS fraction
FROM
    (
        SELECT
            player_id,
            MIN(event_date) AS event_date
        FROM activity
        GROUP BY player_id
    ) AS a
    LEFT JOIN activity AS b
        ON a.player_id = b.player_id AND DATEDIFF(a.event_date, b.event_date) = -1;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            player_id,
            datediff(
                lead(event_date) OVER (
                    PARTITION BY player_id
                    ORDER BY event_date
                ),
                event_date
            ) AS diff,
            row_number() OVER (
                PARTITION BY player_id
                ORDER BY event_date
            ) AS rk
        FROM Activity
    )
SELECT
    round(
        count(DISTINCT if(diff = 1, player_id, NULL)) / count(
            DISTINCT player_id
        ),
        2
    ) AS fraction
FROM T
WHERE rk = 1;
```

<!-- tabs:end -->
