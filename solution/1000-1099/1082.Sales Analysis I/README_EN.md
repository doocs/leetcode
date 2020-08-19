# [1082. Sales Analysis I](https://leetcode.com/problems/sales-analysis-i)

[中文文档](/solution/1000-1099/1082.Sales%20Analysis%20I/README.md)

## Description
None


## Solutions


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
