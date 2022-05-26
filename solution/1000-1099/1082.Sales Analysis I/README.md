# [1082. 销售分析 I](https://leetcode-cn.com/problems/sales-analysis-i)

[English Version](/solution/1000-1099/1082.Sales%20Analysis%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT seller_id
FROM Sales
GROUP BY  seller_id
HAVING sum(price) =
    (SELECT sum(price) AS s
    FROM Sales
    GROUP BY  seller_id
    ORDER BY  s DESC limit 1)
```

<!-- tabs:end -->
