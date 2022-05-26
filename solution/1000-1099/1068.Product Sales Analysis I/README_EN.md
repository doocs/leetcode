# [1068. Product Sales Analysis I](https://leetcode.com/problems/product-sales-analysis-i)

[中文文档](/solution/1000-1099/1068.Product%20Sales%20Analysis%20I/README.md)

## Description

None

## Solutions

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
