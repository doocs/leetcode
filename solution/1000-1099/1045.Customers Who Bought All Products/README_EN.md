# [1045. Customers Who Bought All Products](https://leetcode.com/problems/customers-who-bought-all-products)

[中文文档](/solution/1000-1099/1045.Customers%20Who%20Bought%20All%20Products/README.md)

## Description

None

## Solutions

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
