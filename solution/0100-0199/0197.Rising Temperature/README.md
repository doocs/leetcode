# [197. 上升的温度](https://leetcode-cn.com/problems/rising-temperature)

[English Version](/solution/0100-0199/0197.Rising%20Temperature/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个 <code>Weather</code> 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。</p>

<pre>+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+</pre>

<p>例如，根据上述给定的 <code>Weather</code> 表格，返回如下 Id:</p>

<pre>+----+
| Id |
+----+
|  2 |
|  4 |
+----+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
select w1.Id
from
    Weather w1,
    Weather w2
where
    DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 and w1.Temperature > w2.Temperature
```

<!-- tabs:end -->
