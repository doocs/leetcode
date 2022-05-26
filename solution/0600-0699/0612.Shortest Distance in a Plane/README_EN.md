# [612. Shortest Distance in a Plane](https://leetcode.com/problems/shortest-distance-in-a-plane)

[中文文档](/solution/0600-0699/0612.Shortest%20Distance%20in%20a%20Plane/README.md)

## Description

None

## Solutions

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
