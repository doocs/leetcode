# [1068. 产品销售分析 I](https://leetcode-cn.com/problems/product-sales-analysis-i)

[English Version](/solution/1000-1099/1068.Product%20Sales%20Analysis%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT p.product_name,
         s.year,
         s.price
FROM Sales s
JOIN Product p
    ON s.product_id = p.product_id
```

<!-- tabs:end -->
