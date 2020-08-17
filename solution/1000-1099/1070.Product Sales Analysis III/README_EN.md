# [1070. Product Sales Analysis III](https://leetcode.com/problems/product-sales-analysis-iii)

[中文文档](/solution/1000-1099/1070.Product%20Sales%20Analysis%20III/README.md)

## Description
None


## Solutions


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
