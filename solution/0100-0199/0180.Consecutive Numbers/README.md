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

**方法一：窗口函数**

我们可以使用窗口函数 `LAG` 和 `LEAD` 来判断当前行的前一行和后一行是否与当前行的 `num` 相等，如果当前行与前一行的 `num` 相等，则记字段 $a$ 为 $1$，否则记为 $0$；如果当前行与后一行的 `num` 相等，则记字段 $b$ 为 $1$，否则记为 $0$。

最后，我们只需要筛选出 $a = 1$ 且 $b = 1$ 的行，即为连续出现至少三次的数字。注意，我们需要使用 `DISTINCT` 关键字来对结果去重。

我们也可以对数字进行分组，具体做法是使用 `IF` 函数来判断当前行与前一行的 `num` 是否相等，如果相等则记为 $0$，否则记为 $1$，然后使用窗口函数 `SUM` 来计算前缀和，这样计算出的前缀和就是分组的标识。最后，我们只需要按照分组标识进行分组，然后筛选出每组中的行数大于等于 $3$ 的数字即可。同样，我们需要使用 `DISTINCT` 关键字来对结果去重。

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            num = (LAG(num) OVER (ORDER BY id)) AS a,
            num = (LEAD(num) OVER (ORDER BY id)) AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = 1 AND b = 1;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER (ORDER BY id)), 0, 1) AS st
        FROM Logs
    ),
    S AS (
        SELECT *, SUM(st) OVER (ORDER BY id) AS p
        FROM T
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM S
GROUP BY p
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->
