# [1070. 产品销售分析 III](https://leetcode-cn.com/problems/product-sales-analysis-iii)

[English Version](/solution/1000-1099/1070.Product%20Sales%20Analysis%20III/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
SELECT s.product_id,
         s.year AS first_year,
         s.quantity,
         s.price
FROM Sales s
WHERE (product_id, year) IN 
    (SELECT product_id,
         min(year)
    FROM Sales
    GROUP BY  product_id)
```

<!-- tabs:end -->
