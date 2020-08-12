# [586. 订单最多的客户](https://leetcode-cn.com/problems/customer-placing-the-largest-number-of-orders)

[English Version](/solution/0500-0599/0586.Customer%20Placing%20the%20Largest%20Number%20of%20Orders/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
SELECT customer_number
FROM 
    (SELECT customer_number,
         count(*) AS total
    FROM orders
    GROUP BY  customer_number
    ORDER BY  total DESC limit 1) tmp
```

<!-- tabs:end -->
