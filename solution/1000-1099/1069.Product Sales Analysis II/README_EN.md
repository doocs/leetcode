# [1069. Product Sales Analysis II](https://leetcode.com/problems/product-sales-analysis-ii)

[中文文档](/solution/1000-1099/1069.Product%20Sales%20Analysis%20II/README.md)

## Description
None


## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT product_id,
         sum(quantity) AS total_quantity
FROM Sales
GROUP BY  product_id
```

<!-- tabs:end -->
