# [607. Sales Person](https://leetcode.com/problems/sales-person)

[中文文档](/solution/0600-0699/0607.Sales%20Person/README.md)

## Description

None

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT name
FROM salesperson
WHERE sales_id NOT IN
    (SELECT sales_id
    FROM orders
    WHERE com_id =
        (SELECT com_id
        FROM company
        WHERE name = 'RED'))
```

<!-- tabs:end -->
