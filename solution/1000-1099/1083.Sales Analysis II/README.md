# [1083. 销售分析 II](https://leetcode-cn.com/problems/sales-analysis-ii)

[English Version](/solution/1000-1099/1083.Sales%20Analysis%20II/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
