---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2339.All%20the%20Matches%20of%20the%20League/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [2339. All the Matches of the League 🔒](https://leetcode.com/problems/all-the-matches-of-the-league)

[中文文档](/solution/2300-2399/2339.All%20the%20Matches%20of%20the%20League/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Teams</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| team_name   | varchar |
+-------------+---------+
team_name is the column with unique values of this table.
Each row of this table shows the name of a team.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report&nbsp;all the possible matches of the league. Note that every two teams play two matches with each other, with one team being the <code>home_team</code> once and the other time being the <code>away_team</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Teams table:
+-------------+
| team_name   |
+-------------+
| Leetcode FC |
| Ahly SC     |
| Real Madrid |
+-------------+
<strong>Output:</strong> 
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
<strong>Explanation:</strong> All the matches of the league are shown in the table.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT t1.team_name AS home_team, t2.team_name AS away_team
FROM
    Teams AS t1
    JOIN Teams AS t2
WHERE t1.team_name != t2.team_name;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
