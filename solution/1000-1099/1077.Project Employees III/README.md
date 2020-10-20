# [1077. 项目员工 III](https://leetcode-cn.com/problems/project-employees-iii)

[English Version](/solution/1000-1099/1077.Project%20Employees%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT p.project_id,
         p.employee_id
FROM Project p
JOIN Employee e
    ON p.employee_id = e.employee_id
WHERE (p.project_id, e.experience_years) IN
    (SELECT p.project_id,
         max(e.experience_years) AS max_years
    FROM Project p
    JOIN Employee e
        ON p.employee_id = e.employee_id
    GROUP BY  p.project_id)
```

<!-- tabs:end -->
