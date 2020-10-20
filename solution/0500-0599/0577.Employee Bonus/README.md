# [577. 员工奖金](https://leetcode-cn.com/problems/employee-bonus)

[English Version](/solution/0500-0599/0577.Employee%20Bonus/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT name,
         bonus
FROM Employee e
LEFT JOIN Bonus b
    ON e.empId = b.empId
WHERE (b.bonus < 1000
        OR b.bonus is null)
```

<!-- tabs:end -->
