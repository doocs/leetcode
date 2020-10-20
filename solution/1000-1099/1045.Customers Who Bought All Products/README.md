# [1045. 买下所有产品的客户](https://leetcode-cn.com/problems/customers-who-bought-all-products)

[English Version](/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (
	SELECT COUNT(*)
	FROM Product
)
```

<!-- tabs:end -->
