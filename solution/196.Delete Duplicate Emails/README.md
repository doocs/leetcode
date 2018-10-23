## 删除重复的电子邮箱
### 题目描述


编写一个 SQL 查询，来删除 `Person` 表中所有重复的电子邮箱，重复的邮箱里只保留 **Id** 最小 的那个。
```
+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id 是这个表的主键。
```

例如，在运行你的查询语句之后，上面的 `Person` 表应返回以下几行:
```
+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
```

### 解法
先根据 `Email` 分组，选出每个组中最小的 `Id`，作为一张临时表，再删除掉所有 Id 不在这张临时表的记录。

```sql
# Write your MySQL query statement below

# 用这里注释的语句运行会报错，不能 select 之后再 update
# You can't specify target table 'Person' for update in FROM clause
# delete from Person
# where Id not in(
# select min(Id) as Id
# from Person
# group by Email);

delete from Person
where Id not in(
    select Id from (
        select min(id) as Id
        from Person
        group by Email
    ) a
);
```

#### Input
```json
{"headers": {"Person": ["Id", "Email"]}, "rows": {"Person": [[1, "john@example.com"], [2, "bob@example.com"], [3, "john@example.com"]]}}
```

#### Output
```json
{"headers":["Id","Email"],"values":[[1,"john@example.com"],[2,"bob@example.com"]]}
```