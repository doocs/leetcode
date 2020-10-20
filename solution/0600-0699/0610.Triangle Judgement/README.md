# [610. 判断三角形](https://leetcode-cn.com/problems/triangle-judgement)

[English Version](/solution/0600-0699/0610.Triangle%20Judgement/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT x,
         y,
         z,
         if(x + y > z
        AND x + z > y
        AND y + z > x, "Yes", "No") AS triangle
FROM triangle
```

<!-- tabs:end -->
