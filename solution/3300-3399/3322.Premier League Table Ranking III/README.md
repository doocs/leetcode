---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3322.Premier%20League%20Table%20Ranking%20III/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3322. 英超积分榜排名 III 🔒](https://leetcode.cn/problems/premier-league-table-ranking-iii)

[English Version](/solution/3300-3399/3322.Premier%20League%20Table%20Ranking%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>SeasonStats</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| season_id        | int     |
| team_id          | int     |
| team_name        | varchar |
| matches_played   | int     |
| wins             | int     |
| draws            | int     |
| losses           | int     |
| goals_for        | int     |
| goals_against    | int     |
+------------------+---------+
(season_id, team_id) 是这张表的唯一主键。
这张表包含每个赛季中每支球队的赛季 id，队伍 id，队伍名，比赛场次，赢场，平局，输场，进球数 (goals_for)，以及失球数 (goals_against)。
</pre>

<p>编写一个解决方案来计算&nbsp;<strong>每个赛季每支球队的积分，净胜球&nbsp;</strong>和&nbsp;<strong>排名</strong>。排名应确定如下：</p>

<ul>
	<li>球队首先按总分排名（从高到低）</li>
	<li>如果积分持平，球队就会根据净胜球（从最高到最低）进行排名</li>
	<li>如果净胜球也持平，则球队将按球队名称按字母顺序排名</li>
</ul>

<p>积分如下计算：</p>

<ul>
	<li><strong>赢局</strong> 有&nbsp;<code>3</code>&nbsp;点得分</li>
	<li><strong>平局</strong> 有&nbsp;<code>1</code>&nbsp;点得分</li>
	<li><strong>输局</strong> 有&nbsp;<code>0</code>&nbsp;点得分</li>
</ul>

<p>净胜球计算如下：<code>goals_for - goals_against</code></p>

<p>返回结果表以&nbsp;<code>season_id</code> <strong>升序</strong>&nbsp;排序，然后以&nbsp;<code>rank</code> <strong>升序</strong>&nbsp;排序，最后以&nbsp;<code>team_name</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<p><strong>输入：</strong></p>

<p><code>SeasonStats</code>&nbsp;表：</p>

<pre>
+------------+---------+-------------------+----------------+------+-------+--------+-----------+---------------+
| season_id  | team_id | team_name         | matches_played | wins | draws | losses | goals_for | goals_against |
+------------+---------+-------------------+----------------+------+-------+--------+-----------+---------------+
| 2021       | 1       | Manchester City   | 38             | 29   | 6     | 3      | 99        | 26            |
| 2021       | 2       | Liverpool         | 38             | 28   | 8     | 2      | 94        | 26            |
| 2021       | 3       | Chelsea           | 38             | 21   | 11    | 6      | 76        | 33            |
| 2021       | 4       | Tottenham         | 38             | 22   | 5     | 11     | 69        | 40            |
| 2021       | 5       | Arsenal           | 38             | 22   | 3     | 13     | 61        | 48            |
| 2022       | 1       | Manchester City   | 38             | 28   | 5     | 5      | 94        | 33            |
| 2022       | 2       | Arsenal           | 38             | 26   | 6     | 6      | 88        | 43            |
| 2022       | 3       | Manchester United | 38             | 23   | 6     | 9      | 58        | 43            |
| 2022       | 4       | Newcastle         | 38             | 19   | 14    | 5      | 68        | 33            |
| 2022       | 5       | Liverpool         | 38             | 19   | 10    | 9      | 75        | 47            |
+------------+---------+-------------------+----------------+------+-------+--------+-----------+---------------+
</pre>

<p><strong>输出：</strong></p>

<pre>
+------------+---------+-------------------+--------+-----------------+------+
| season_id  | team_id | team_name         | points | goal_difference | rank |
+------------+---------+-------------------+--------+-----------------+------+
| 2021       | 1       | Manchester City   | 93     | 73              | 1    |
| 2021       | 2       | Liverpool         | 92     | 68              | 2    |
| 2021       | 3       | Chelsea           | 74     | 43              | 3    |
| 2021       | 4       | Tottenham         | 71     | 29              | 4    |
| 2021       | 5       | Arsenal           | 69     | 13              | 5    |
| 2022       | 1       | Manchester City   | 89     | 61              | 1    |
| 2022       | 2       | Arsenal           | 84     | 45              | 2    |
| 2022       | 3       | Manchester United | 75     | 15              | 3    |
| 2022       | 4       | Newcastle         | 71     | 35              | 4    |
| 2022       | 5       | Liverpool         | 67     | 28              | 5    |
+------------+---------+-------------------+--------+-----------------+------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于 2021 赛季：
	<ul>
		<li>曼城有 93 积分 (29 * 3 + 6 * 1) 以及 73&nbsp;(99 - 26)&nbsp;个净胜球。</li>
		<li>利物浦有 92 积分 (28 * 3 + 8 * 1) 以及 68 (94 - 26) 个净胜球。</li>
		<li>切尔西有&nbsp;74 积分 (21 * 3 + 11 * 1) 以及 43 (76 - 33)&nbsp;个净胜球。</li>
		<li>托特纳姆有 71 积分 (22 * 3 + 5 * 1) 以及 29 (69 - 40)&nbsp;个净胜球。</li>
		<li>阿森纳有 69 积分 (22 * 3 + 3 * 1) 以及 13 (61 - 48) 个净胜球。</li>
	</ul>
	</li>
	<li>对于 2022 赛季：
	<ul>
		<li>曼城有 89 积分 (28 * 3 + 5 * 1) 以及 61 (94 - 33)&nbsp;个净胜球。</li>
		<li>阿森纳有 84 积分 (26 * 3 + 6 * 1) 以及 45 (88 - 43)&nbsp;个净胜球。</li>
		<li>曼联有&nbsp;75 积分 (23 * 3 + 6 * 1) 以及 15 (58 - 43)&nbsp;个净胜球。</li>
		<li>纽卡斯尔有&nbsp;71 积分 (19 * 3 + 14 * 1) 以及 35 (68 - 33)&nbsp;个净胜球。</li>
		<li>利物浦有 67 积分 (19 * 3 + 10 * 1) 以及 28 (75 - 47)&nbsp;个净胜球。</li>
	</ul>
	</li>
	<li>球队首先以积分排名，然后是净胜球，最后是球队名称。</li>
	<li>输出以 season_id 升序排序，然后以排名升序排序，最后以 team_name 升序排序。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

我们可以使用窗口函数 `RANK()`，将球队按照赛季分组，按照积分、净胜球和球队名称的顺序进行排名。

最后，我们只需要按照 `season_id`、`position` 和 `team_name` 进行排序即可。

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    season_id,
    team_id,
    team_name,
    wins * 3 + draws points,
    goals_for - goals_against goal_difference,
    RANK() OVER (
        PARTITION BY season_id
        ORDER BY wins * 3 + draws DESC, goals_for - goals_against DESC, team_name
    ) position
FROM SeasonStats
ORDER BY 1, 6, 3;
```

#### Pandas

```python
import pandas as pd


def process_team_standings(season_stats: pd.DataFrame) -> pd.DataFrame:
    season_stats["points"] = season_stats["wins"] * 3 + season_stats["draws"]
    season_stats["goal_difference"] = (
        season_stats["goals_for"] - season_stats["goals_against"]
    )

    season_stats = season_stats.sort_values(
        ["season_id", "points", "goal_difference", "team_name"],
        ascending=[True, False, False, True],
    )

    season_stats["position"] = season_stats.groupby("season_id").cumcount() + 1

    return season_stats[
        ["season_id", "team_id", "team_name", "points", "goal_difference", "position"]
    ]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
