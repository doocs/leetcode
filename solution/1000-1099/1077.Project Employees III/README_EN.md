# [1077. Project Employees III](https://leetcode.com/problems/project-employees-iii)

[中文文档](/solution/1000-1099/1077.Project%20Employees%20III/README.md)

## Description

None

## Solutions

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
