# [1841. League Statistics](https://leetcode.com/problems/league-statistics)

[中文文档](/solution/1800-1899/1841.League%20Statistics/README.md)

## Description

<p>Table: <code>Teams</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| team_id        | int     |
| team_name      | varchar |
+----------------+---------+
team_id is the primary key for this table.
Each row contains information about one team in the league.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Matches</code></p>

<pre>
+-----------------+---------+
| Column Name     | Type    |
+-----------------+---------+
| home_team_id    | int     |
| away_team_id    | int     |
| home_team_goals | int     |
| away_team_goals | int     |
+-----------------+---------+
(home_team_id, away_team_id) is the primary key for this table.
Each row contains information about one match.
home_team_goals is the number of goals scored by the home team.
away_team_goals is the number of goals scored by the away team.
The winner of the match is the team with the higher number of goals.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the statistics of the league. The statistics should be built using the played matches where the <strong>winning</strong> team gets <strong>three points</strong> and the <strong>losing</strong> team gets <strong>no points</strong>. If a match ends with a <strong>draw</strong>, both teams get <strong>one point</strong>.</p>

<p>Each row of the result table should contain:</p>

<ul>
	<li><code>team_name</code> - The name of the team in the <code>Teams</code> table.</li>
	<li><code>matches_played</code> - The number of matches played as either a home or away team.</li>
	<li><code>points</code> - The total points the team has so far.</li>
	<li><code>goal_for</code> - The total number of goals scored by the team across all matches.</li>
	<li><code>goal_against</code> - The total number of goals scored by opponent teams against this team across all matches.</li>
	<li><code>goal_diff</code> - The result of <code>goal_for - goal_against</code>.</li>
</ul>

<p>Return the result table ordered by <code>points</code> <strong>in descending order</strong>. If two or more teams have the same <code>points</code>, order them by <code>goal_diff</code> <strong>in descending order</strong>. If there is still a tie, order them by <code>team_name</code> in <strong>lexicographical order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Teams table:
+---------+-----------+
| team_id | team_name |
+---------+-----------+
| 1       | Ajax      |
| 4       | Dortmund  |
| 6       | Arsenal   |
+---------+-----------+
Matches table:
+--------------+--------------+-----------------+-----------------+
| home_team_id | away_team_id | home_team_goals | away_team_goals |
+--------------+--------------+-----------------+-----------------+
| 1            | 4            | 0               | 1               |
| 1            | 6            | 3               | 3               |
| 4            | 1            | 5               | 2               |
| 6            | 1            | 0               | 0               |
+--------------+--------------+-----------------+-----------------+
<strong>Output:</strong> 
+-----------+----------------+--------+----------+--------------+-----------+
| team_name | matches_played | points | goal_for | goal_against | goal_diff |
+-----------+----------------+--------+----------+--------------+-----------+
| Dortmund  | 2              | 6      | 6        | 2            | 4         |
| Arsenal   | 2              | 2      | 3        | 3            | 0         |
| Ajax      | 4              | 2      | 5        | 9            | -4        |
+-----------+----------------+--------+----------+--------------+-----------+
<strong>Explanation:</strong> 
Ajax (team_id=1) played 4 matches: 2 losses and 2 draws. Total points = 0 + 0 + 1 + 1 = 2.
Dortmund (team_id=4) played 2 matches: 2 wins. Total points = 3 + 3 = 6.
Arsenal (team_id=6) played 2 matches: 2 draws. Total points = 1 + 1 = 2.
Dortmund is the first team in the table. Ajax and Arsenal have the same points, but since Arsenal has a higher goal_diff than Ajax, Arsenal comes before Ajax in the table.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
