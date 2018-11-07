## 交换工资
### 题目描述

给定一个 `salary` 表，如下所示，有m=男性 和 f=女性的值 。交换所有的 f 和 m 值(例如，将所有 f 值更改为 m，反之亦然)。要求使用一个更新查询，并且没有中间临时表。

例如:
```
| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | m   | 2500   |
| 2  | B    | f   | 1500   |
| 3  | C    | m   | 5500   |
| 4  | D    | f   | 500    |
```

运行你所编写的查询语句之后，将会得到以下表:
```
| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | f   | 2500   |
| 2  | B    | m   | 1500   |
| 3  | C    | f   | 5500   |
| 4  | D    | m   | 500    |
```

### 解法
使用 `if` 函数 或者 `case when .. then .. else ... end`。

```sql
# Write your MySQL query statement below
# update salary
# set sex = if(sex = 'm', 'f', 'm')

update salary
set sex = (case when sex = 'f' then 'm' else 'f' end)

```

#### Input
```json
{"headers":{"salary":["id","name","sex","salary"]},"rows":{"salary":[[1,"A","m",2500],[2,"B","f",1500],[3,"C","m",5500],[4,"D","f",500]]}}
```

#### Output
```json
{"headers":["id","name","sex","salary"],"values":[[1,"A","f",2500],[2,"B","m",1500],[3,"C","f",5500],[4,"D","m",500]]}
```