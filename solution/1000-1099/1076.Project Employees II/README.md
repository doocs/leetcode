# [1076. 项目员工II](https://leetcode-cn.com/problems/project-employees-ii)

[English Version](/solution/1000-1099/1076.Project%20Employees%20II/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
