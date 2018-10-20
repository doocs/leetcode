## 第二高的薪水
### 题目描述

编写一个 SQL 查询，获取 `Employee` 表中第二高的薪水（Salary） 。
```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

例如上述 `Employee` 表，SQL查询应该返回 `200` 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 `null`。
```
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
```

### 解法
从小于最高薪水的记录里面选最高即可。

```sql
# Write your MySQL query statement below
select max(Salary) SecondHighestSalary from Employee where Salary < (Select max(Salary) from Employee)

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
        "SecondHighestSalary"
    ],
    "values": [
        [
            200
        ]
    ]
}
```