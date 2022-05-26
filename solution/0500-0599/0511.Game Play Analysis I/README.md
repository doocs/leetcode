# [511. 游戏玩法分析 I](https://leetcode-cn.com/problems/game-play-analysis-i)

[English Version](/solution/0500-0599/0511.Game%20Play%20Analysis%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
select player_id, min(event_date) as first_login from Activity group by player_id
```

<!-- tabs:end -->
