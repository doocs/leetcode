# [512. Game Play Analysis II](https://leetcode.com/problems/game-play-analysis-ii)

[中文文档](/solution/0500-0599/0512.Game%20Play%20Analysis%20II/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
select player_id, device_id from Activity where (player_id, event_date) in ((select player_id, min(event_date) from Activity group by player_id))
```

<!-- tabs:end -->
