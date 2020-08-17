# [1075. Project Employees I](https://leetcode.com/problems/project-employees-i)

[中文文档](/solution/1000-1099/1075.Project%20Employees%20I/README.md)

## Description
None


## Solutions


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
