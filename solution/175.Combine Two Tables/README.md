## 组合两个表
### 题目描述

表1: `Person`
```
+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键
```

表2: `Address`
```
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键
```

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
```
FirstName, LastName, City, State
```

### 解法
题意中说无论 `person` 是否有地址信息，都要查出来，因此，使用左外连接查询。注意使用 `on` 关键字。

```sql
# Write your MySQL query statement below
select a.FirstName, a.LastName, b.City, b.State from Person a left join Address b on a.PersonId = b.PersonId;

```

#### Input
```json
{
    "headers": {
        "Person": [
            "PersonId",
            "LastName",
            "FirstName"
        ],
        "Address": [
            "AddressId",
            "PersonId",
            "City",
            "State"
        ]
    },
    "rows": {
        "Person": [
            [
                1,
                "Wang",
                "Allen"
            ]
        ],
        "Address": [
            [
                1,
                2,
                "New York City",
                "New York"
            ]
        ]
    }
}
```

#### Output
```json
{
    "headers": [
        "FirstName",
        "LastName",
        "City",
        "State"
    ],
    "values": [
        [
            "Allen",
            "Wang",
            null,
            null
        ]
    ]
}
```