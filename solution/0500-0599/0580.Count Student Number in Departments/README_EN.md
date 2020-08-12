# [580. Count Student Number in Departments](https://leetcode.com/problems/count-student-number-in-departments)

[中文文档](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT dept_name,
        ifnull(total,
        0) AS student_number
FROM department
LEFT JOIN 
    (SELECT dept_id,
         count(*) AS total
    FROM student
    GROUP BY  dept_id) tmp
    ON department.dept_id = tmp.dept_id
ORDER BY  tmp.total desc
```

<!-- tabs:end -->
