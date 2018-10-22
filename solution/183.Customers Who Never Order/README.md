## 从不订购的客户
### 题目描述

某网站包含两个表，`Customers` 表和 `Orders` 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。

`Customers` 表：
```
+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
```

`Orders` 表：
```
+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
```

例如给定上述表格，你的查询应返回：
```
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
```

### 解法
两个表左连接，找出 `CustomerId` 为 `null` 的记录即可。

```sql
# Write your MySQL query statement below

select Name as Customers 
from Customers c
left join Orders o
on c.Id = o.CustomerId
where o.CustomerId is null
```

#### Input
```json
{"headers": {"Customers": ["Id", "Name"], "Orders": ["Id", "CustomerId"]}, "rows": {"Customers": [[1, "Joe"], [2, "Henry"], [3, "Sam"], [4, "Max"]], "Orders": [[1, 3], [2, 1]]}}
```

#### Output
```json
{"headers":["Customers"],"values":[["Henry"],["Max"]]}
```