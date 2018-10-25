## 连续出现的数字
### 题目描述

编写一个 SQL 查询，查找所有至少连续出现三次的数字。
```
+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
```

例如，给定上面的 `Logs` 表， `1` 是唯一连续出现至少三次的数字。
```
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
```

### 解法
联表查询。

```sql
# Write your MySQL query statement below

select distinct l1.Num ConsecutiveNums 
from Logs l1
join Logs l2 on l1.Id = l2.Id - 1
join Logs l3 on l2.Id = l3.Id - 1
where l1.Num = l2.Num and l2.Num = l3.Num


```

#### Input
```json
{"headers": {"Logs": ["Id", "Num"]}, "rows": {"Logs": [[1, 1], [2, 1], [3, 1], [4, 2], [5, 1], [6, 2], [7, 2]]}}
```

#### Output
```json
{"headers":["ConsecutiveNums"],"values":[[1]]}
```