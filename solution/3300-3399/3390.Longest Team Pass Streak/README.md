---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3390.Longest%20Team%20Pass%20Streak/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3390. 最长团队传球连击 🔒](https://leetcode.cn/problems/longest-team-pass-streak)

[English Version](/solution/3300-3399/3390.Longest%20Team%20Pass%20Streak/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Teams</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| player_id   | int     |
| team_name   | varchar | 
+-------------+---------+
player_id 是这张表的唯一主键。
每行包含队员的唯一标识符以及在该场比赛中参赛的某支队伍的名称。
</pre>

<p>表：<code>Passes</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| pass_from   | int     |
| time_stamp  | varchar |
| pass_to     | int     |
+-------------+---------+
(pass_from, time_stamp) 是这张表的唯一主键。
pass_from 是 Teams 表中 player_id 的外键。
每一行代表比赛中的一次传球，time_stamp 表示传球发生的分钟时间（00:00-90:00）。
pass_to 是接收传球队员的 player_id。
</pre>

<p>编写一个解决方案以找到比赛中 <strong>每个队伍</strong> 的 <strong>最长连续成功传球</strong>。规则如下：</p>

<ul>
	<li>成功连击的定义为连续传球，其中：
	<ul>
		<li><code>pass_from</code> 和&nbsp;<code>pass_to</code>&nbsp;表示的队员来自同一队伍</li>
	</ul>
	</li>
	<li>当出现以下情况时，连击就会中断：
	<ul>
		<li>传球被截获（由对方球队的一名球员接住）</li>
	</ul>
	</li>
</ul>

<p>返回结果表以&nbsp;<code>team_name</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Teams 表：</p>

<pre>
+-----------+-----------+
| player_id | team_name |
+-----------+-----------+
| 1         | Arsenal   |
| 2         | Arsenal   |
| 3         | Arsenal   |
| 4         | Arsenal   |
| 5         | Chelsea   |
| 6         | Chelsea   |
| 7         | Chelsea   |
| 8         | Chelsea   |
+-----------+-----------+
</pre>

<p>Passes 表：</p>

<pre>
+-----------+------------+---------+
| pass_from | time_stamp | pass_to |
+-----------+------------+---------+
| 1         | 00:05      | 2       |
| 2         | 00:07      | 3       |
| 3         | 00:08      | 4       |
| 4         | 00:10      | 5       |
| 6         | 00:15      | 7       |
| 7         | 00:17      | 8       |
| 8         | 00:20      | 6       |
| 6         | 00:22      | 5       |
| 1         | 00:25      | 2       |
| 2         | 00:27      | 3       |
+-----------+------------+---------+
</pre>

<p><strong>输出：</strong></p>

<pre>
+-----------+----------------+
| team_name | longest_streak |
+-----------+----------------+
| Arsenal   | 3              |
| Chelsea   | 4              |
+-----------+----------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>阿森纳的</strong>&nbsp;连击：

    <ul>
    	<li>第一次连击：3 次传球（1→2→3→4）当队员 4 传球给切尔西的队员 5 时结束</li>
    	<li>第二次连击：2 次传球（1→2→3）</li>
    	<li>最长连击 = 3</li>
    </ul>
    </li>
    <li><strong>切尔西的</strong>&nbsp;连击：
    <ul>
    	<li>第一次连击：3 次传球（6→7→8→6→5）</li>
    	<li>最长连击 = 4</li>
    </ul>
    </li>

</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
WITH
    PassesWithTeams AS (
        SELECT
            p.pass_from,
            p.pass_to,
            t1.team_name AS team_from,
            t2.team_name AS team_to,
            IF(t1.team_name = t2.team_name, 1, 0) same_team_flag,
            p.time_stamp
        FROM
            Passes p
            JOIN Teams t1 ON p.pass_from = t1.player_id
            JOIN Teams t2 ON p.pass_to = t2.player_id
    ),
    StreakGroups AS (
        SELECT
            team_from AS team_name,
            time_stamp,
            same_team_flag,
            SUM(
                CASE
                    WHEN same_team_flag = 0 THEN 1
                    ELSE 0
                END
            ) OVER (
                PARTITION BY team_from
                ORDER BY time_stamp
            ) AS group_id
        FROM PassesWithTeams
    ),
    StreakLengths AS (
        SELECT
            team_name,
            group_id,
            COUNT(*) AS streak_length
        FROM StreakGroups
        WHERE same_team_flag = 1
        GROUP BY 1, 2
    ),
    LongestStreaks AS (
        SELECT
            team_name,
            MAX(streak_length) AS longest_streak
        FROM StreakLengths
        GROUP BY 1
    )
SELECT
    team_name,
    longest_streak
FROM LongestStreaks
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
