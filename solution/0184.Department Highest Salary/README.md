## 部门工资最高的员工
### 题目描述

`Employee` 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
```
+----+-------+--------+--------------+
| Id | Name  | Salary | DepartmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
```

`Department` 表包含公司所有部门的信息。
```
+----+----------+
| Id | Name     |
+----+----------+
| 1  | IT       |
| 2  | Sales    |
+----+----------+
```

编写一个 SQL 查询，找出每个部门工资最高的员工。例如，根据上述给定的表格，Max 在 IT 部门有最高工资，Henry 在 Sales 部门有最高工资。
```
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| Sales      | Henry    | 80000  |
+------------+----------+--------+
```

### 解法
先对 `Employee` 表根据 `DepartmentId` 进行分组，找出每组中工资最高的员工，再与 `Department` 表进行内连接，即可查出结果。

```sql
# Write your MySQL query statement below

select d.Name as Department, c.Name as Employee, c.Salary
from (select a.Name, a.DepartmentId, a.Salary from Employee a
inner join (select DepartmentId, max(Salary) Salary from Employee group by DepartmentId) b
on a.DepartMentId = b.DepartMentId and a.Salary = b.Salary) c
inner join DepartMent d
on d.Id = c.DepartmentId;
```

#### Input
```json
{"headers": {"Employee": ["Id", "Name", "Salary", "DepartmentId"], "Department": ["Id", "Name"]}, "rows": {"Employee": [[1, "Joe", 70000, 1], [2, "Henry", 80000, 2], [3, "Sam", 60000, 2], [4, "Max", 90000, 1]], "Department": [[1, "IT"], [2, "Sales"]]}}
```

#### Output
```json
{"headers":["Department","Employee","Salary"],"values":[["Sales","Henry",80000],["IT","Max",90000]]}
```