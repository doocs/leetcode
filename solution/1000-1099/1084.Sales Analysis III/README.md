# [1084. 销售分析III](https://leetcode-cn.com/problems/sales-analysis-iii)

[English Version](/solution/1000-1099/1084.Sales%20Analysis%20III/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
