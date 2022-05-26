# [610. Triangle Judgement](https://leetcode.com/problems/triangle-judgement)

[中文文档](/solution/0600-0699/0610.Triangle%20Judgement/README.md)

## Description

None

## Solutions

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
