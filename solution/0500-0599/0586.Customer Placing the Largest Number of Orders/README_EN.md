# [586. Customer Placing the Largest Number of Orders](https://leetcode.com/problems/customer-placing-the-largest-number-of-orders)

[中文文档](/solution/0500-0599/0586.Customer%20Placing%20the%20Largest%20Number%20of%20Orders/README.md)

## Description

None

## Solutions

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
