---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3390.Longest%20Team%20Pass%20Streak/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3390. Longest Team Pass Streak 🔒](https://leetcode.cn/problems/longest-team-pass-streak)

[English Version](/solution/3300-3399/3390.Longest%20Team%20Pass%20Streak/README_EN.md)

## 题目描述

<!-- description:start -->

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
