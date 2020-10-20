# [1075. 项目员工 I](https://leetcode-cn.com/problems/project-employees-i)

[English Version](/solution/1000-1099/1075.Project%20Employees%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT p.project_id,
         round(avg(e.experience_years),
        2) AS average_years
FROM Project p
JOIN Employee e
    ON p.employee_id = e.employee_id
GROUP BY  p.project_id
```

<!-- tabs:end -->
