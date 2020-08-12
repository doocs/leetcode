# [550. 游戏玩法分析 IV](https://leetcode-cn.com/problems/game-play-analysis-iv)

[English Version](/solution/0500-0599/0550.Game%20Play%20Analysis%20IV/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
(select round(count(distinct (b.player_id)) / (select count(distinct (player_id)) from Activity), 2) as fraction
 from Activity a
          left join (select player_id, min(event_date) as first_date from Activity group by player_id) as b
                    on a.player_id = b.player_id and datediff(a.event_date, b.first_date) = 1)
```

<!-- tabs:end -->
