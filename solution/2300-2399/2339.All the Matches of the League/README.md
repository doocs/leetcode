# [2339. 联赛的所有比赛](https://leetcode.cn/problems/all-the-matches-of-the-league)

[English Version](/solution/2300-2399/2339.All%20the%20Matches%20of%20the%20League/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Teams</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| team_name   | varchar |
+-------------+---------+
team_name 是该表中具有唯一值的列。
此表的每一行都显示了团队的名称。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，获取联赛中所有比赛。每两支球队进行两场比赛，其中一支球队是主队&nbsp;<code>home_team</code>&nbsp;，另一支是客场队&nbsp;<code>away_team</code>。<br />
按 <strong>任意顺序</strong> 返回结果表。<br />
返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Teams 表:
+-------------+
| team_name   |
+-------------+
| Leetcode FC |
| Ahly SC     |
| Real Madrid |
+-------------+
<strong>输出:</strong> 
+-------------+-------------+
| home_team   | away_team   |
+-------------+-------------+
| Real Madrid | Leetcode FC |
| Real Madrid | Ahly SC     |
| Leetcode FC | Real Madrid |
| Leetcode FC | Ahly SC     |
| Ahly SC     | Real Madrid |
| Ahly SC     | Leetcode FC |
+-------------+-------------+
<strong>解释:</strong> 该联赛的所有比赛都列在表格中。
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT t1.team_name AS home_team, t2.team_name AS away_team
FROM
    Teams AS t1
    JOIN Teams AS t2
WHERE t1.team_name != t2.team_name;
```

<!-- tabs:end -->

<!-- end -->
