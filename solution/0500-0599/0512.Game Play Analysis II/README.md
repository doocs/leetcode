# [512. 游戏玩法分析 II](https://leetcode-cn.com/problems/game-play-analysis-ii)

[English Version](/solution/0500-0599/0512.Game%20Play%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
select player_id, device_id from Activity where (player_id, event_date) in ((select player_id, min(event_date) from Activity group by player_id))
```

<!-- tabs:end -->
