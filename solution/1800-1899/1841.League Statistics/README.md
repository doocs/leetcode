# [1841. 联赛信息统计](https://leetcode.cn/problems/league-statistics)

[English Version](/solution/1800-1899/1841.League%20Statistics/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Teams</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| team_id        | int     |
| team_name      | varchar |
+----------------+---------+
team_id 是该表主键.
每一行都包含了一个参加联赛的队伍信息.
</pre>

<p>&nbsp;</p>

<p>表: <code>Matches</code></p>

<pre>
+-----------------+---------+
| Column Name     | Type    |
+-----------------+---------+
| home_team_id    | int     |
| away_team_id    | int     |
| home_team_goals | int     |
| away_team_goals | int     |
+-----------------+---------+
(home_team_id, away_team_id) 是该表主键.
每一行包含了一次比赛信息.
home_team_goals 代表主场队得球数.
away_team_goals 代表客场队得球数.
获得球数较多的队伍为胜者队伍.
</pre>

<p>&nbsp;</p>

<p>写一段SQL，用来报告联赛信息. 统计数据应使用已进行的比赛来构建，其中 <strong>获胜</strong> 球队获得 <strong>三分</strong> ，而失败球队获得<strong> 零分</strong>&nbsp;。如果 <strong>打平 </strong>，两支球队都得&nbsp;<strong>一分&nbsp;</strong>。</p>

<p>result 表的每行应包含以下信息:</p>

<ul>
	<li><code>team_name</code> - <code>Teams</code> 表中的队伍名字</li>
	<li><code>matches_played</code> - 主场与客场球队进行的比赛次数.</li>
	<li><code>points</code> - 球队获得的总分数.</li>
	<li><code>goal_for</code> - 球队在所有比赛中获取的总进球数</li>
	<li><code>goal_against</code> - 球队在所有比赛中，他的对手球队的所有进球数</li>
	<li><code>goal_diff</code> - <code>goal_for - goal_against</code>.</li>
</ul>

<p>按 <code>points</code> <strong>降序</strong> 返回结果表。 如果两队或多队得分相同，则按 <code>goal_diff</code> <strong>降序</strong> 排列。 如果仍然存在平局，则以&nbsp;<code>team_name</code> <strong>按字典顺序</strong> 排列它们。</p>

<p>查询的结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Teams 表:
+---------+-----------+
| team_id | team_name |
+---------+-----------+
| 1       | Ajax      |
| 4       | Dortmund  |
| 6       | Arsenal   |
+---------+-----------+
Matches 表:
+--------------+--------------+-----------------+-----------------+
| home_team_id | away_team_id | home_team_goals | away_team_goals |
+--------------+--------------+-----------------+-----------------+
| 1            | 4            | 0               | 1               |
| 1            | 6            | 3               | 3               |
| 4            | 1            | 5               | 2               |
| 6            | 1            | 0               | 0               |
+--------------+--------------+-----------------+-----------------+
<strong>输出：</strong>
+-----------+----------------+--------+----------+--------------+-----------+
| team_name | matches_played | points | goal_for | goal_against | goal_diff |
+-----------+----------------+--------+----------+--------------+-----------+
| Dortmund  | 2              | 6      | 6        | 2            | 4         |
| Arsenal   | 2              | 2      | 3        | 3            | 0         |
| Ajax      | 4              | 2      | 5        | 9            | -4        |
+-----------+----------------+--------+----------+--------------+-----------+
<strong>解释：</strong>
Ajax (team_id=1) 有4场比赛: 2败2平. 总分数 = 0 + 0 + 1 + 1 = 2.
Dortmund (team_id=4) 有2场比赛: 2胜. 总分数 = 3 + 3 = 6.
Arsenal (team_id=6) 有2场比赛: 2平. 总分数 = 1 + 1 = 2.
Dortmund 是积分榜上的第一支球队. Ajax和Arsenal 有同样的分数, 但Arsenal的goal_diff高于Ajax, 所以Arsenal在表中的顺序在Ajaxzhi'qian.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
