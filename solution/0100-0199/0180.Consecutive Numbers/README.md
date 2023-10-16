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
在 SQL 中，id 是该表的主键。
id 是一个自增列。</pre>

<p>&nbsp;</p>

<p>找出所有至少连续出现三次的数字。</p>

<p>返回的结果表中的数据可以按 <strong>任意顺序</strong> 排列。</p>

<p>结果格式如下面的例子所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Logs 表：
+----+-----+
| id | num |
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
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            CASE
                WHEN (LAG(num) OVER (ORDER BY id)) = num THEN 0
                ELSE 1
            END AS mark
        FROM Logs
    ),
    p AS (SELECT num, SUM(mark) OVER (ORDER BY id) AS gid FROM t)
SELECT DISTINCT num AS ConsecutiveNums
FROM p
GROUP BY gid
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->
