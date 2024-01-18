# [1212. Team Scores in Football Tournament](https://leetcode.com/problems/team-scores-in-football-tournament)

[中文文档](/solution/1200-1299/1212.Team%20Scores%20in%20Football%20Tournament/README.md)

## Description

<p>Table: <code>Teams</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| team_id       | int      |
| team_name     | varchar  |
+---------------+----------+
team_id is the column with unique values of this table.
Each row of this table represents a single football team.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Matches</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| match_id      | int     |
| host_team     | int     |
| guest_team    | int     | 
| host_goals    | int     |
| guest_goals   | int     |
+---------------+---------+
match_id is the column of unique values of this table.
Each row is a record of a finished match between two different teams. 
Teams host_team and guest_team are represented by their IDs in the Teams table (team_id), and they scored host_goals and guest_goals goals, respectively.
</pre>

<p>&nbsp;</p>
You would like to compute the scores of all teams after all matches. Points are awarded as follows:

<ul>
	<li>A team receives <strong>three points</strong> if they win a match (i.e., Scored more goals than the opponent team).</li>
	<li>A team receives <strong>one point</strong> if they draw a match (i.e., Scored the same number of goals as the opponent team).</li>
	<li>A team receives <strong>no points</strong> if they lose a match (i.e., Scored fewer goals than the opponent team).</li>
</ul>

<p>Write a solution that selects the <code>team_id</code>, <code>team_name</code> and <code>num_points</code> of each team in the tournament after all described matches.</p>

<p>Return the result table ordered by <code>num_points</code> <strong>in decreasing order</strong>. In case of a tie, order the records by <code>team_id</code> <strong>in increasing order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Teams table:
+-----------+--------------+
| team_id   | team_name    |
+-----------+--------------+
| 10        | Leetcode FC  |
| 20        | NewYork FC   |
| 30        | Atlanta FC   |
| 40        | Chicago FC   |
| 50        | Toronto FC   |
+-----------+--------------+
Matches table:
+------------+--------------+---------------+-------------+--------------+
| match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
+------------+--------------+---------------+-------------+--------------+
| 1          | 10           | 20            | 3           | 0            |
| 2          | 30           | 10            | 2           | 2            |
| 3          | 10           | 50            | 5           | 1            |
| 4          | 20           | 30            | 1           | 0            |
| 5          | 50           | 30            | 1           | 0            |
+------------+--------------+---------------+-------------+--------------+
<strong>Output:</strong> 
+------------+--------------+---------------+
| team_id    | team_name    | num_points    |
+------------+--------------+---------------+
| 10         | Leetcode FC  | 7             |
| 20         | NewYork FC   | 3             |
| 50         | Toronto FC   | 3             |
| 30         | Atlanta FC   | 1             |
| 40         | Chicago FC   | 0             |
+------------+--------------+---------------+
</pre>

## Solutions

### Solution 1: Left Join + Group By + Case Expression

We can join the `Teams` table and the `Matches` table using a left join, where the join condition is `team_id = host_team OR team_id = guest_team`, to obtain all the match information for each team.

Next, we group by `team_id` and use a `CASE` expression to calculate the points for each team according to the following rules:

-   If the team is the host team and has more goals than the guest team, add $3$ points to the team's score.
-   If the team is the guest team and has more goals than the host team, add $3$ points to the team's score.
-   If the host team and the guest team have the same number of goals, add $1$ point to the team's score.

Finally, we sort the result by points in descending order, and if the points are the same, we sort by `team_id` in ascending order.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    team_id,
    team_name,
    SUM(
        CASE
            WHEN team_id = host_team
            AND host_goals > guest_goals THEN 3
            WHEN team_id = guest_team
            AND guest_goals > host_goals THEN 3
            WHEN host_goals = guest_goals THEN 1
            ELSE 0
        END
    ) AS num_points
FROM
    Teams
    LEFT JOIN Matches ON team_id = host_team OR team_id = guest_team
GROUP BY 1
ORDER BY 3 DESC, 1;
```

<!-- tabs:end -->

<!-- end -->
