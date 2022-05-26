# [612. 平面上的最近距离](https://leetcode-cn.com/problems/shortest-distance-in-a-plane)

[English Version](/solution/0600-0699/0612.Shortest%20Distance%20in%20a%20Plane/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT round(min(sqrt(power(p1.x-p2.x,
        2) + power(p1.y-p2.y,
        2))),
        2) shortest
FROM point_2d p1, point_2d p2
WHERE (p1.x, p1.y) <> (p2.x,p2.y)
```

<!-- tabs:end -->
