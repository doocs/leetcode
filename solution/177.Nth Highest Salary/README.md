## 第N高的薪水
### 题目描述

编写一个 SQL 查询，获取 `Employee` 表中第 n 高的薪水（Salary）。
```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

例如上述 `Employee` 表，n = 2 时，应返回第二高的薪水 `200`。如果不存在第 n 高的薪水，那么查询应返回 null。
```
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
```

### 解法
对 Salary 进行分组，然后根据 Salary 降序排列。选出偏移为 n-1 的一个记录即可。

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  SET N = N - 1;
  RETURN (
      # Write your MySQL query statement below.
      select Salary from Employee group by Salary order by Salary desc limit 1 offset N
  );
END

```

#### Input
```json
{
    "headers": {
        "Employee": [
            "Id",
            "Salary"
        ]
    },
    "argument": 2,
    "rows": {
        "Employee": [
            [
                1,
                100
            ],
            [
                2,
                200
            ],
            [
                3,
                300
            ]
        ]
    }
}
```

#### Output
```json
{
    "headers": [
        "getNthHighestSalary(2)"
    ],
    "values": [
        [
            200
        ]
    ]
}
```