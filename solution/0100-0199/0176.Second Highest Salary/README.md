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

# [题目](这里是题目链接，如：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->


## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
