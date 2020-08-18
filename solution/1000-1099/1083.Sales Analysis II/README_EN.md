# [1083. Sales Analysis II](https://leetcode.com/problems/sales-analysis-ii)

[中文文档](/solution/1000-1099/1083.Sales%20Analysis%20II/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT s.buyer_id
FROM Sales s
JOIN Product p
    ON s.product_id = p.product_id
GROUP BY  s.buyer_id
HAVING sum(p.product_name='S8')>0
        AND sum(p.product_name='iPhone')=0
```

<!-- tabs:end -->
