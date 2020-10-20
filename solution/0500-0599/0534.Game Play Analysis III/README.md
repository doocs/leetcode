# [534. 游戏玩法分析 III](https://leetcode-cn.com/problems/game-play-analysis-iii)

[English Version](/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
select a.player_id, a.event_date, sum(b.games_played) as games_played_so_far from Activity a, Activity b where a.player_id = b.player_id and a.event_date >= b.event_date group by a.player_id, a.event_date
```

<!-- tabs:end -->
