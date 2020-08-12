# [570. Managers with at Least 5 Direct Reports](https://leetcode.com/problems/managers-with-at-least-5-direct-reports)

[中文文档](/solution/0500-0599/0570.Managers%20with%20at%20Least%205%20Direct%20Reports/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT Name
FROM Employee
WHERE Id IN 
    (SELECT ManagerId
    FROM Employee
    GROUP BY  ManagerId
    HAVING count(*) >= 5)
```

<!-- tabs:end -->
