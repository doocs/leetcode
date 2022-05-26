# [511. Game Play Analysis I](https://leetcode.com/problems/game-play-analysis-i)

[中文文档](/solution/0500-0599/0511.Game%20Play%20Analysis%20I/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
select player_id, min(event_date) as first_login from Activity group by player_id
```

<!-- tabs:end -->
