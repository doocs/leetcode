# [613. Shortest Distance in a Line](https://leetcode.com/problems/shortest-distance-in-a-line)

[中文文档](/solution/0600-0699/0613.Shortest%20Distance%20in%20a%20Line/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT min(abs(p1.x-p2.x)) shortest
FROM point p1, point p2
WHERE p1.x != p2.x
```

<!-- tabs:end -->
