# [180. 连续出现的数字](https://leetcode.cn/problems/consecutive-numbers)

[English Version](/solution/0100-0199/0180.Consecutive%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
id 是这个表的主键。</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，查找所有至少连续出现三次的数字。</p>

<p>返回的结果表中的数据可以按 <strong>任意顺序</strong> 排列。</p>

<p>查询结果格式如下面的例子所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Logs 表：
+----+-----+
| Id | Num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
<strong>输出：</strong>
Result 表：
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
<strong>解释：</strong>1 是唯一连续出现至少三次的数字。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
select distinct(Num) as ConsecutiveNums from Logs Curr where
    Num = (select Num from Logs where id = Curr.id - 1) and
    Num = (select Num from Logs where id = Curr.id - 2)
```

```sql
# Write your MySQL query statement below
SELECT DISTINCT l1.num AS ConsecutiveNums
FROM
    logs AS l1,
    logs AS l2,
    logs AS l3
WHERE
    l1.id = l2.id - 1
    AND
    l2.id = l3.id - 1
    AND
    l1.num = l2.num
    AND
    l2.num = l3.num
```

<!-- tabs:end -->
