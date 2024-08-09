---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3246.Premier%20League%20Table%20Ranking/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3246. 英超积分榜排名 🔒](https://leetcode.cn/problems/premier-league-table-ranking)

[English Version](/solution/3200-3299/3246.Premier%20League%20Table%20Ranking/README_EN.md)

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

<p>编写一个解决方案来计算联盟中每支球队的 <strong>得分</strong> 和 <strong>排名</strong>。积分计算方式如下：</p>

<ul>
	<li><strong>赢局</strong> 有&nbsp;<code>3</code>&nbsp;点得分</li>
	<li><strong>平局</strong> 有&nbsp;<code>1</code>&nbsp;点得分</li>
	<li><strong>输局</strong> 有&nbsp;<code>0</code>&nbsp;点得分</li>
</ul>

<p><b>注意：</b>积分相同的球队必须分配相同的排名。</p>

<p>返回结果表以&nbsp;<code>points</code>&nbsp;<strong>降序</strong>&nbsp;排序，然后以&nbsp;<code>team_name</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>TeamStats</code> 表：</p>

<pre class="example-io">
+---------+-----------------+----------------+------+-------+--------+
| team_id | team_name       | matches_played | wins | draws | losses |
+---------+-----------------+----------------+------+-------+--------+
| 1       | Manchester City | 10             | 6    | 2     | 2      |
| 2       | Liverpool       | 10             | 6    | 2     | 2      |
| 3       | Chelsea         | 10             | 5    | 3     | 2      |
| 4       | Arsenal         | 10             | 4    | 4     | 2      |
| 5       | Tottenham       | 10             | 3    | 5     | 2      |
+---------+-----------------+----------------+------+-------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+-----------------+--------+----------+
| team_id | team_name       | points | position |
+---------+-----------------+--------+----------+
| 2       | Liverpool       | 20     | 1        |
| 1       | Manchester City | 20     | 1        |
| 3       | Chelsea         | 18     | 3        |
| 4       | Arsenal         | 16     | 4        |
| 5       | Tottenham       | 14     | 5        |
+---------+-----------------+--------+----------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>曼城和利物浦均拿下 20 分（6 赢 * 3 分 + 2 平 * 1 分），所以他们并列第一。</li>
	<li>切尔西拿下&nbsp;18 分（5 赢 * 3 分 + 3 平 * 1 分）所以位列第三。</li>
	<li>阿森纳拿下 16 分（4 赢 * 3 分 + 4 平 * 1 分）位列第四。</li>
	<li>托特纳姆热刺队拿下 14 分（3 赢 * 3 分 + 5 平 * 1 分）位列第五。</li>
</ul>

<p>输出表以得分降序排序，然后以&nbsp;team_name 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

我们可以使用 `RANK()` 窗口函数来计算球队的排名，然后按照得分和球队名进行排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    team_id,
    team_name,
    wins * 3 + draws points,
    RANK() OVER (ORDER BY (wins * 3 + draws) DESC) position
FROM TeamStats
ORDER BY 3 DESC, 2;
```

#### Pandas

```python
import pandas as pd


def calculate_team_standings(team_stats: pd.DataFrame) -> pd.DataFrame:
    team_stats["points"] = team_stats["wins"] * 3 + team_stats["draws"]
    team_stats["position"] = team_stats["points"].rank(method="min", ascending=False)
    team_stats = team_stats.sort_values(
        by=["points", "team_name"], ascending=[False, True]
    )
    return team_stats[["team_id", "team_name", "points", "position"]]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
