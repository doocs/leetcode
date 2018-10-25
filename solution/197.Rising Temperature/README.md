## 上升的温度
### 题目描述


给定一个 `Weather` 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。
```
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+
```

例如，根据上述给定的 `Weather` 表格，返回如下 Id:
```
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
```

### 解法
利用 datediff 函数返回两个日期相差的天数。

```sql
# Write your MySQL query statement below
select a.Id
from Weather a
inner join Weather b
on a.Temperature > b.Temperature and datediff(a.RecordDate, b.RecordDate) = 1;
```

#### Input
```json
{"headers": {"Weather": ["Id", "RecordDate", "Temperature"]}, "rows": {"Weather": [[1, "2015-01-01", 10], [2, "2015-01-02", 25], [3, "2015-01-03", 20], [4, "2015-01-04", 30]]}}
```

#### Output
```json
{"headers":["Id"],"values":[[2],[4]]}
```