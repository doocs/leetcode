---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3252.Premier%20League%20Table%20Ranking%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3252. 英超积分榜排名 II 🔒](https://leetcode.cn/problems/premier-league-table-ranking-ii)

[English Version](/solution/3200-3299/3252.Premier%20League%20Table%20Ranking%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>TeamStats</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| team_id          | int     |
| team_name        | varchar |
| matches_played   | int     |
| wins             | int     |
| draws            | int     |
| losses           | int     |
+------------------+---------+
team_id 是这张表的唯一主键。
这张表包含队伍 id，队伍名，场次，赢局，平局和输局。
</pre>

<p>编写一个解决方案来计算联盟中每支球队的 <strong>得分</strong>，<strong>排名 </strong>和 <b>等级</b>。积分计算方式如下：</p>

<ul>
	<li><strong>赢局</strong> 有&nbsp;<code>3</code>&nbsp;点得分</li>
	<li><strong>平局</strong> 有&nbsp;<code>1</code>&nbsp;点得分</li>
	<li><strong>输局</strong> 有&nbsp;<code>0</code>&nbsp;点得分</li>
</ul>

<p><b>注意：</b>积分相同的球队必须分配相同的排名。</p>

<p><strong>等级评级：</strong></p>

<ul>
	<li>根据积分将联盟分为 <code>3</code> 个等级：</li>
	<li>等级 1：前&nbsp;<code>33%</code>&nbsp;的队伍</li>
	<li>等级 2：中间&nbsp;<code>33%</code> 的队伍</li>
	<li>等级 3：最后&nbsp;<code>34%</code>&nbsp;的队伍</li>
	<li>如果等级边界出现平局，平局的队伍分配到更高的等级。</li>
</ul>

<p>返回结果表以&nbsp;<code>points</code>&nbsp;<strong>降序</strong>&nbsp;排序，然后以&nbsp;<code>team_name</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>TeamStats</code> 表：</p>

<pre class="example-io">
+---------+-------------------+----------------+------+-------+--------+
| team_id | team_name         | matches_played | wins | draws | losses |
+---------+-------------------+----------------+------+-------+--------+
| 1       | Chelsea           | 22             | 13   | 2     | 7      |
| 2       | Nottingham Forest | 27             | 6    | 6     | 15     |
| 3       | Liverpool         | 17             | 1    | 8     | 8      |
| 4       | Aston Villa       | 20             | 1    | 6     | 13     |
| 5       | Fulham            | 31             | 18   | 1     | 12     |
| 6       | Burnley           | 26             | 6    | 9     | 11     |
| 7       | Newcastle United  | 33             | 11   | 10    | 12     |
| 8       | Sheffield United  | 20             | 18   | 2     | 0      |
| 9       | Luton Town        | 5              | 4    | 0     | 1      |
| 10      | Everton           | 14             | 2    | 6     | 6      |
+---------+-------------------+----------------+------+-------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------------+--------+----------+---------+
| team_name         | points | position | tier    |
+-------------------+--------+----------+---------+
| Sheffield United  | 56     | 1        | Tier 1  |
| Fulham            | 55     | 2        | Tier 1  |
| Newcastle United  | 43     | 3        | Tier 1  |
| Chelsea           | 41     | 4        | Tier 1  |
| Burnley           | 27     | 5        | Tier 2  |
| Nottingham Forest | 24     | 6        | Tier 2  |
| Everton           | 12     | 7        | Tier 2  |
| Luton Town        | 12     | 7        | Tier 2  |
| Liverpool         | 11     | 9        | Tier 3  |
| Aston Villa       | 9      | 10       | Tier 3  |
+-------------------+--------+----------+---------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>谢菲尔德联队拿下 56 分（18 胜 * 3 分 + 2 平 * 1 分）位列第 1。</li>
	<li>富勒姆拿下 55 分（18 胜 * 3 分 + 1 平 * 1 分）位列第 2。</li>
	<li>纽卡斯尔联队拿下 43 分（11 胜 * 3 分 + 10 平 * 1 分）位列第 3。</li>
	<li>切尔西拿下 41 分（13 胜 * 3 分 + 2 平 * 1 分）位列第 4。</li>
	<li>伯恩利拿下 27 分（6 胜 * 3 分 + 9 平 * 1 分）位列第 5。</li>
	<li>诺丁汉森林拿下 24 分（6 胜 * 3 分 + 6 平 * 1 分）位列第 6。</li>
	<li>埃弗顿和卢顿镇均拿下 12 分，埃弗顿&nbsp;2 胜 * 3 分 + 6 平 * 1 分，卢顿镇 4 胜 * 3 分。两支队伍并列位列第 7。</li>
	<li>利物浦拿下 11 分（1 胜 * 3 分 + 8 平 * 1 分）位列第 9。</li>
	<li>阿斯顿维拉拿下 9 分（1 胜 * 3 分 + 6 平 * 1 分）位列第 10。</li>
</ul>

<p><strong>等级计算：</strong></p>

<ul>
	<li><strong>等级 1：</strong>根据积分排名前 33% 的球队。谢菲尔德联队、富勒姆、纽卡斯尔联队和切尔西属于等级 1。</li>
	<li><strong>等级 2：</strong>中间&nbsp;33% 的球队。伯恩利、诺丁汉森林、埃弗顿和卢顿镇属于等级 2。</li>
	<li><strong>等级 3：</strong>垫底 34% 的球队。利物浦和阿斯顿维拉落入等级 3。</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + CASE WHEN

<!-- thinking:start -->

> **思考**
>
> 在积分排名之外还要把球队按名次三等分。名次仍是最小排名法，阈值是总队数的三分之一与三分之二向上取整。
>
> 算出积分与 `RANK` 后，用分层条件写入 Tier 1/2/3，再按积分、队名排序。窗口保证同分同档判定一致。

<!-- thinking:end -->

我们可以使用窗口函数 `RANK()` 来计算每支球队的积分、排名，并计算总球队数。然后，我们可以使用 `CASE WHEN` 语句来确定每支球队的等级。

<!-- tabs:start -->

#### MySQL

```sql
WITH
    T AS (
        SELECT
            team_name,
            wins * 3 + draws AS points,
            RANK() OVER (ORDER BY wins * 3 + draws DESC) AS position,
            COUNT(1) OVER () AS total_teams
        FROM TeamStats
    )
SELECT
    team_name,
    points,
    position,
    CASE
        WHEN position <= CEIL(total_teams / 3.0) THEN 'Tier 1'
        WHEN position <= CEIL(2 * total_teams / 3.0) THEN 'Tier 2'
        ELSE 'Tier 3'
    END tier
FROM T
ORDER BY 2 DESC, 1;
```

#### Pandas

```python
import pandas as pd


def calculate_team_tiers(team_stats: pd.DataFrame) -> pd.DataFrame:
    team_stats["points"] = team_stats["wins"] * 3 + team_stats["draws"]
    team_stats["position"] = (
        team_stats["points"].rank(method="min", ascending=False).astype(int)
    )
    total_teams = len(team_stats)
    team_stats["tier"] = np.where(
        team_stats["position"] <= np.ceil(total_teams / 3.0),
        "Tier 1",
        np.where(
            team_stats["position"] <= np.ceil(2 * total_teams / 3.0), "Tier 2", "Tier 3"
        ),
    )
    team_stats = team_stats.sort_values(
        by=["points", "team_name"], ascending=[False, True]
    )
    return team_stats[["team_name", "points", "position", "tier"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
