# [580. 统计各专业学生人数](https://leetcode-cn.com/problems/count-student-number-in-departments)

[English Version](/solution/0500-0599/0580.Count%20Student%20Number%20in%20Departments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
