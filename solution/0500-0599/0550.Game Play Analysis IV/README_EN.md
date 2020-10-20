# [550. Game Play Analysis IV](https://leetcode.com/problems/game-play-analysis-iv)

[中文文档](/solution/0500-0599/0550.Game%20Play%20Analysis%20IV/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
(select round(count(distinct (b.player_id)) / (select count(distinct (player_id)) from Activity), 2) as fraction
 from Activity a
          left join (select player_id, min(event_date) as first_date from Activity group by player_id) as b
                    on a.player_id = b.player_id and datediff(a.event_date, b.first_date) = 1)
```

<!-- tabs:end -->
