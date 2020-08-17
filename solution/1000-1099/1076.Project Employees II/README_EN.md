# [1076. Project Employees II](https://leetcode.com/problems/project-employees-ii)

[中文文档](/solution/1000-1099/1076.Project%20Employees%20II/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT project_id
FROM Project
GROUP BY  project_id
HAVING count(*) >= all
    (SELECT count(*)
    FROM Project
    GROUP BY  project_id )
```

<!-- tabs:end -->
