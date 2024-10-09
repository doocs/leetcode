---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3252.Premier%20League%20Table%20Ranking%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3252. Premier League Table Ranking II 🔒](https://leetcode.com/problems/premier-league-table-ranking-ii)

[中文文档](/solution/3200-3299/3252.Premier%20League%20Table%20Ranking%20II/README.md)

## Description

<!-- description:start -->

<p>Table: <code>TeamStats</code></p>

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
team_id is the unique key for this table.
This table contains team id, team name, matches_played, wins, draws, and losses.
</pre>

<p>Write a solution to calculate the <strong>points</strong>, <strong>position</strong>, and <strong>tier</strong> for each team in the league. Points are calculated as follows:</p>

<ul>
	<li><code>3</code> points for a <strong>win</strong></li>
	<li><code>1</code> point for a <strong>draw</strong></li>
	<li><code>0</code> points for a <strong>loss</strong></li>
</ul>

<p><strong>Note:</strong>&nbsp;Teams with the same points must be assigned the same position.</p>

<p><strong>Tier ranking:</strong></p>

<ul>
	<li>Divide the league into <code>3</code> tiers based on points:</li>
	<li>Tier 1: Top <code>33%</code> of teams</li>
	<li>Tier 2: Middle <code>33%</code> of teams</li>
	<li>Tier 3: Bottom<code> 34%</code> of teams</li>
	<li>In case of <strong>ties</strong> at<strong> tier boundaries</strong>, place tied teams in the <strong>higher tier</strong>.</li>
</ul>

<p>Return <em>the result table </em><em>ordered by</em> <code>points</code>&nbsp;<em>in&nbsp;<strong>descending</strong>,<strong>&nbsp;</strong>and then by</em> <code>team_name</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p><code>TeamStats</code> table:</p>

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

<p><strong>Output:</strong></p>

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

<p><strong>Explanation:</strong></p>

<ul>
	<li>Sheffield United has 56 points (18 wins * 3 points + 2 draws * 1 point) and is in position 1.</li>
	<li>Fulham has 55 points (18 wins * 3 points + 1 draw * 1 point) and is in position 2.</li>
	<li>Newcastle United has 43 points (11 wins * 3 points + 10 draws * 1 point) and is in position 3.</li>
	<li>Chelsea has 41 points (13 wins * 3 points + 2 draws * 1 point) and is in position 4.</li>
	<li>Burnley has 27 points (6 wins * 3 points + 9 draws * 1 point) and is in position 5.</li>
	<li>Nottingham Forest has 24 points (6 wins * 3 points + 6 draws * 1 point) and is in position 6.</li>
	<li>Everton and Luton Town both have 12 points, with Everton having 2 wins * 3 points + 6 draws * 1 point, and Luton Town having 4 wins * 3 points. Both teams share position 7.</li>
	<li>Liverpool has 11 points (1 win * 3 points + 8 draws * 1 point) and is in position 9.</li>
	<li>Aston Villa has 9 points (1 win * 3 points + 6 draws * 1 point) and is in position 10.</li>
</ul>

<p><strong>Tier Calculation:</strong></p>

<ul>
	<li><strong>Tier 1:</strong> The top 33% of teams based on points. Sheffield United, Fulham, Newcastle United, and Chelsea fall into Tier 1.</li>
	<li><strong>Tier 2:</strong> The middle 33% of teams. Burnley, Nottingham Forest, Everton, and Luton Town fall into Tier 2.</li>
	<li><strong>Tier 3:</strong> The bottom 34% of teams. Liverpool and Aston Villa fall into Tier 3.</li>
</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Function + CASE WHEN

We can use the window function `RANK()` to calculate each team's points, ranking, and the total number of teams. Then, we can use the `CASE WHEN` statement to determine the grade of each team.

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
