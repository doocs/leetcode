---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3384. 球队传球成功的优势得分 🔒](https://leetcode.cn/problems/team-dominance-by-pass-success)

[English Version](/solution/3300-3399/3384.Team%20Dominance%20by%20Pass%20Success/README_EN.md)

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
每一行包含队员的唯一标识以及在该场比赛中参赛的某支队伍的名称。

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
(pass_from, time_stamp) 是这张表的主键。
pass_from 是指向 Teams 表 player_id 字段的外键。
每一行代表比赛期间的一次传球，time_stamp 表示传球发生的分钟时间（00:00-90:00），
pass_to 表示 player_id 对应队员接球。

</pre>

<p>编写一个解决方案来计算每支球队&nbsp;<strong>在上下两个半场的优势得分</strong>。规则如下：</p>

<ul>
	<li>一场比赛分为两个半场：<strong>上半场</strong>（<code>00:00</code>-<code><font face="monospace">45:00</font></code>&nbsp;分钟）和&nbsp;<strong>下半场</strong>（<code>45:01</code>-<code>90:00</code>&nbsp;分钟）</li>
	<li>优势得分是根据成功和截获的传球来计算的：
	<ul>
		<li>当 pass_to 是 <strong>同球队</strong>&nbsp;的队员：+<code>1</code> 分</li>
		<li>当 pass_to 是 <strong>对方球队</strong>&nbsp;的队员（截获）：<code>-1</code> 分</li>
	</ul>
	</li>
	<li>更高的优势得分表明传球表现更好</li>
</ul>

<p>返回结果表以&nbsp;<code>team_name</code> 和&nbsp;<code>half_number</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Teams 表：</p>

<pre class="example-io">
+------------+-----------+
| player_id  | team_name |
+------------+-----------+
| 1          | Arsenal   |
| 2          | Arsenal   |
| 3          | Arsenal   |
| 4          | Chelsea   |
| 5          | Chelsea   |
| 6          | Chelsea   |
+------------+-----------+
</pre>

<p>Passes 表：</p>

<pre class="example-io">
+-----------+------------+---------+
| pass_from | time_stamp | pass_to |
+-----------+------------+---------+
| 1         | 00:15      | 2       |
| 2         | 00:45      | 3       |
| 3         | 01:15      | 1       |
| 4         | 00:30      | 1       |
| 2         | 46:00      | 3       |
| 3         | 46:15      | 4       |
| 1         | 46:45      | 2       |
| 5         | 46:30      | 6       |
+-----------+------------+---------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+-------------+-----------+
| team_name | half_number | dominance |
+-----------+-------------+-----------+
| Arsenal   | 1           | 3         |
| Arsenal   | 2           | 1         |
| Chelsea   | 1           | -1        |
| Chelsea   | 2           | 1         |
+-----------+-------------+-----------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>前半场（00:00-45:00）：</strong>

    <ul>
    	<li>阿森纳的传球：
    	<ul>
    		<li>1 → 2 (00:15)：成功传球（+1）</li>
    		<li>2 → 3 (00:45)：成功传球（+1）</li>
    		<li>3 → 1 (01:15)：成功传球（+1）</li>
    	</ul>
    	</li>
    	<li>切尔西的传球：
    	<ul>
    		<li>4 → 1 (00:30): 被阿森纳截获（-1）</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li><strong>下半场（45:01-90:00）：</strong>
    <ul>
    	<li>阿森纳的传球：
    	<ul>
    		<li>2 → 3 (46:00)：成功传球（+1）</li>
    		<li>3 → 4 (46:15)：被切尔西截获&nbsp;(-1)</li>
    		<li>1 → 2 (46:45)：成功传球（+1）</li>
    	</ul>
    	</li>
    	<li>切尔西的传球：
    	<ul>
    		<li>5 → 6 (46:30)：成功传球（+1）</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li>结果以 team_name 和 half_number 升序排序</li>

</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：补齐球队 × 半场骨架 + 等值连接 + 分组求和

<!-- thinking:start -->

> **思考**
>
> 同队传球得 $1$ 分，跨队传球得 $-1$ 分，按球队与上下半场合计。时间戳 $\le 45{:}00$ 为上半场。
>
> 传球表分别按发起者、接收者连接球队表，即可比较两队名并打出优势值，再按发起方队名与半场分组求和。
>
> **但要注意**：题目要求的是「每支球队」在上下两个半场的优势得分，即使某队在某半场没有任何发起传球，也应输出 $0$。若只从 `Passes` 出发分组，缺失的球队/半场不会出现。因此需要先构造「球队 × 半场」的完整骨架，再左连接统计结果。

<!-- thinking:end -->

我们可以通过等值连接，找到每次传球的发起方和接收方所在的球队，然后根据传球的时间戳判断传球发生在上半场还是下半场，根据传球的发起方和接收方所在的球队是否相同，计算出每次传球的优势值，最后按照球队名称和半场编号进行分组求和，得到每支球队在上半场和下半场的优势值。

需要注意的是，**直接对 `Passes` 分组会遗漏「某队某半场无发起传球」的行**。因此需要先用「所有球队」与「两个半场」做笛卡尔积，得到每队两个半场的完整骨架，再将统计结果左连接到骨架上，未匹配到的行补 $0$。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    teams AS (
        SELECT DISTINCT team_name
        FROM Teams
    ),
    halves AS (
        SELECT 1 AS half_number
        UNION ALL
        SELECT 2
    ),
    T AS (
        SELECT
            t1.team_name,
            IF(p.time_stamp <= '45:00', 1, 2) AS half_number,
            IF(t1.team_name = t2.team_name, 1, -1) AS dominance
        FROM
            Passes p
            JOIN Teams t1 ON p.pass_from = t1.player_id
            JOIN Teams t2 ON p.pass_to = t2.player_id
    ),
    s AS (
        SELECT
            team_name,
            half_number,
            SUM(dominance) AS dominance
        FROM T
        GROUP BY team_name, half_number
    )
SELECT
    teams.team_name,
    halves.half_number,
    COALESCE(s.dominance, 0) AS dominance
FROM
    teams
    CROSS JOIN halves
    LEFT JOIN s ON teams.team_name = s.team_name AND halves.half_number = s.half_number
ORDER BY teams.team_name, halves.half_number;
```

<!-- tabs:end -->

````

#### Pandas

```python
import pandas as pd


def calculate_team_dominance(teams: pd.DataFrame, passes: pd.DataFrame) -> pd.DataFrame:
    passes_with_teams = passes.merge(
        teams, left_on="pass_from", right_on="player_id", suffixes=("", "_team_from")
    ).merge(
        teams,
        left_on="pass_to",
        right_on="player_id",
        suffixes=("_team_from", "_team_to"),
    )
    passes_with_teams["half_number"] = passes_with_teams["time_stamp"].apply(
        lambda x: 1 if x <= "45:00" else 2
    )
    passes_with_teams["dominance"] = passes_with_teams.apply(
        lambda row: 1 if row["team_name_team_from"] == row["team_name_team_to"] else -1,
        axis=1,
    )
    result = (
        passes_with_teams.groupby(["team_name_team_from", "half_number"])["dominance"]
        .sum()
        .reset_index()
    )
    result.columns = ["team_name", "half_number", "dominance"]
    result = result.sort_values(by=["team_name", "half_number"])
    return result
````

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
