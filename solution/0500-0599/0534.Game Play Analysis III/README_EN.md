# [534. Game Play Analysis III](https://leetcode.com/problems/game-play-analysis-iii)

[中文文档](/solution/0500-0599/0534.Game%20Play%20Analysis%20III/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
select a.player_id, a.event_date, sum(b.games_played) as games_played_so_far from Activity a, Activity b where a.player_id = b.player_id and a.event_date >= b.event_date group by a.player_id, a.event_date
```

<!-- tabs:end -->
