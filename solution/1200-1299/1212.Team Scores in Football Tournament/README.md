# [1212. 查询球队积分](https://leetcode-cn.com/problems/team-scores-in-football-tournament)

[English Version](/solution/1200-1299/1212.Team%20Scores%20in%20Football%20Tournament/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Teams</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| team_id       | int      |
| team_name     | varchar  |
+---------------+----------+
此表的主键是 team_id，表中的每一行都代表一支独立足球队。
</pre>

<p>Table:&nbsp;<code>Matches</code></p>

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
此表的主键是 match_id，表中的每一行都代表一场已结束的比赛，比赛的主客队分别由它们自己的 id 表示，他们的进球由 host_goals 和 guest_goals 分别表示。
</pre>

<p>&nbsp;</p>

<p>积分规则如下：</p>

<ul>
	<li>赢一场得三分；</li>
	<li>平一场得一分；</li>
	<li>输一场不得分。</li>
</ul>

<p>写出一条SQL语句以查询每个队的&nbsp;<strong>team_id</strong>，<strong>team_name</strong> 和 <strong>num_points</strong>。结果根据&nbsp;num_points<strong> 降序排序</strong>，如果有两队积分相同，那么这两队按&nbsp;team_id&nbsp; <strong>升序排序</strong>。</p>

<p>查询结果格式如下：</p>

<pre>
<code>Teams </code>table:
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

Result table:
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
