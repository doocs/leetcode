# [613. 直线上的最近距离](https://leetcode-cn.com/problems/shortest-distance-in-a-line)

[English Version](/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
SELECT min(abs(p1.x-p2.x)) shortest
FROM point p1, point p2
WHERE p1.x != p2.x
```

<!-- tabs:end -->
