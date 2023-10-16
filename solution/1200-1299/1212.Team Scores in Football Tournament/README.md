# [1212. 查询球队积分](https://leetcode.cn/problems/team-scores-in-football-tournament)

[English Version](/solution/1200-1299/1212.Team%20Scores%20in%20Football%20Tournament/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Teams</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| team_id       | int      |
| team_name     | varchar  |
+---------------+----------+
team_id 是该表具有唯一值的列。
表中的每一行都代表一支独立足球队。
</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Matches</code></p>

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
match_id 是该表具有唯一值的列。
表中的每一行都代表一场已结束的比赛。
比赛的主客队分别由它们自己的 id 表示，他们的进球由 host_goals 和 guest_goals 分别表示。
</pre>

<p>&nbsp;</p>

<p>你希望在所有比赛之后计算所有球队的比分。积分奖励方式如下:</p>

<ul>
	<li>如果球队赢了比赛(即比对手进更多的球)，就得 <strong>3</strong> 分。</li>
	<li>如果双方打成平手(即，与对方得分相同)，则得 <strong>1</strong> 分。</li>
	<li>如果球队输掉了比赛(例如，比对手少进球)，就 <strong>不得分</strong> 。</li>
</ul>

<p>编写解决方案，以找出每个队的&nbsp;<code>team_id</code>，<code>team_name</code> 和 <code>num_points</code>。</p>

<p>返回的结果根据&nbsp;<code>num_points</code><strong> 降序排序</strong>，如果有两队积分相同，那么这两队按&nbsp;<code>team_id</code>&nbsp; <strong>升序排序</strong>。</p>

<p>返回结果格式如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Teams </code>table:
+-----------+--------------+
| team_id   | team_name    |
+-----------+--------------+
| 10        | Leetcode FC  |
| 20        | NewYork FC   |
| 30        | Atlanta FC   |
| 40        | Chicago FC   |
| 50        | Toronto FC   |
+-----------+--------------+
<code>Matches </code>table:
+------------+--------------+---------------+-------------+--------------+
| match_id   | host_team    | guest_team    | host_goals  | guest_goals  |
+------------+--------------+---------------+-------------+--------------+
| 1          | 10           | 20            | 3           | 0            |
| 2          | 30           | 10            | 2           | 2            |
| 3          | 10           | 50            | 5           | 1            |
| 4          | 20           | 30            | 1           | 0            |
| 5          | 50           | 30            | 1           | 0            |
+------------+--------------+---------------+-------------+--------------+
<strong>输出：</strong>
+------------+--------------+---------------+
| team_id    | team_name    | num_points    |
+------------+--------------+---------------+
| 10         | Leetcode FC  | 7             |
| 20         | NewYork FC   | 3             |
| 50         | Toronto FC   | 3             |
| 30         | Atlanta FC   | 1             |
| 40         | Chicago FC   | 0             |
+------------+--------------+---------------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：左连接 + 分组 + CASE 表达式**

我们可以通过左连接，将 `Teams` 表和 `Matches` 表连接起来，连接的条件为 `team_id = host_team OR team_id = guest_team`，这样就可以得到每个球队的所有比赛信息。

接下来，我们按照 `team_id` 分组，然后使用 `CASE` 表达式计算每个球队的积分，计算规则如下：

-   如果球队是主队，且主队进球数大于客队进球数，则积分加 $3$ 分；
-   如果球队是客队，且客队进球数大于主队进球数，则积分加 $3$ 分；
-   如果主队和客队进球数相同，则积分加 $1$ 分；

最后，我们按照积分降序排序，如果积分相同，则按照 `team_id` 升序排序。

<!-- tabs:start -->

### **SQL**

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
