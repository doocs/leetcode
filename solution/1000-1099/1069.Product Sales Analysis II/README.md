# [1069. 产品销售分析 II](https://leetcode-cn.com/problems/product-sales-analysis-ii)

[English Version](/solution/1000-1099/1069.Product%20Sales%20Analysis%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT product_id,
         sum(quantity) AS total_quantity
FROM Sales
GROUP BY  product_id
```

<!-- tabs:end -->
