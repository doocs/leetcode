## 查找重复的电子邮箱
### 题目描述

编写一个 SQL 查询，查找 `Person` 表中所有重复的电子邮箱。

示例：
```
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
```

根据以上输入，你的查询应返回以下结果：
```
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
```

**说明：**所有电子邮箱都是小写字母。

### 解法
对 `Email` 进行分组，选出数量大于 1 的 `Email` 即可。

```sql
# Write your MySQL query statement below
select Email 
from Person 
group by Email 
having count(Email) > 1;
```

#### Input
```json
{"headers": {"Person": ["Id", "Email"]}, "rows": {"Person": [[1, "a@b.com"], [2, "c@d.com"], [3, "a@b.com"]]}}
```

#### Output
```json
{"headers":["Email"],"values":[["a@b.com"]]}
```