# [577. Employee Bonus](https://leetcode.com/problems/employee-bonus)

[中文文档](/solution/0500-0599/0577.Employee%20Bonus/README.md)

## Description
None


## Solutions


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
