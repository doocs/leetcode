# [607. 销售员](https://leetcode-cn.com/problems/sales-person)

[English Version](/solution/0600-0699/0607.Sales%20Person/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
