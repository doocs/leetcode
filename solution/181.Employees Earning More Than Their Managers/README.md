## 连续出现的数字
### 题目描述

`Employee` 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
```
+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |
+----+-------+--------+-----------+
```

给定 `Employee` 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。
```
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```

### 解法
联表查询。

```sql
# Write your MySQL query statement below

# select s.Name as Employee
# from Employee s 
# where s.ManagerId is not null and s.Salary > (select Salary from Employee where Id = s.ManagerId);


select s1.Name as Employee
from Employee s1
inner join Employee s2
on s1.ManagerId = s2.Id
where s1.Salary > s2.Salary;

```

#### Input
```json
{"headers": {"Employee": ["Id", "Name", "Salary", "ManagerId"]}, "rows": {"Employee": [[1, "Joe", 70000, 3], [2, "Henry", 80000, 4], [3, "Sam", 60000, null], [4, "Max", 90000, null]]}}
```

#### Output
```json
{"headers":["Employee"],"values":[["Joe"]]}
```