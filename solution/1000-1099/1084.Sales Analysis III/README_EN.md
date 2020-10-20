# [1084. Sales Analysis III](https://leetcode.com/problems/sales-analysis-iii)

[中文文档](/solution/1000-1099/1084.Sales%20Analysis%20III/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT s.product_id,
        p.product_name
FROM Sales s, Product p
WHERE s.product_id = p.product_id
GROUP BY  s.product_id
HAVING sum(sale_date < '2019-01-01') = 0
        AND sum(sale_date > '2019-03-31') = 0
```

<!-- tabs:end -->
