---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0180.Consecutive%20Numbers/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [180. 连续出现的数字](https://leetcode.cn/problems/consecutive-numbers)

[English Version](/solution/0100-0199/0180.Consecutive%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次连接

我们可以使用两次连接来解决这个问题。

我们首先进行一次自连接，连接条件是 `l1.num = l2.num` 并且 `l1.id = l2.id - 1`，这样我们就可以找出所有至少连续出现两次的数字。然后，我们再进行一次自连接，连接条件是 `l2.num = l3.num` 并且 `l2.id = l3.id - 1`，这样我们就可以找出所有至少连续出现三次的数字。最后，我们只需要筛选出去重的 `l2.num` 即可。

<!-- tabs:start -->

```python
import pandas as pd


def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    all_the_same = lambda lst: lst.nunique() == 1
    logs["is_consecutive"] = (
        logs["num"].rolling(window=3, center=True, min_periods=3).apply(all_the_same)
    )
    return (
        logs.query("is_consecutive == 1.0")[["num"]]
        .drop_duplicates()
        .rename(columns={"num": "ConsecutiveNums"})
    )
```

```sql
# Write your MySQL query statement below
SELECT DISTINCT l2.num AS ConsecutiveNums
FROM
    Logs AS l1
    JOIN Logs AS l2 ON l1.id = l2.id - 1 AND l1.num = l2.num
    JOIN Logs AS l3 ON l2.id = l3.id - 1 AND l2.num = l3.num;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：窗口函数

我们可以使用窗口函数 `LAG` 和 `LEAD` 来获取上一行的 `num` 和下一行的 `num`，记录在字段 $a$ 和 $b$ 中。最后，我们只需要筛选出 $a =num$ 并且 $b = num$ 的行，这些行就是至少连续出现三次的数字。注意，我们需要使用 `DISTINCT` 关键字来对结果去重。

我们也可以对数字进行分组，具体做法是使用 `IF` 函数来判断当前行与前一行的 `num` 是否相等，如果相等则记为 $0$，否则记为 $1$，然后使用窗口函数 `SUM` 来计算前缀和，这样计算出的前缀和就是分组的标识。最后，我们只需要按照分组标识进行分组，然后筛选出每组中的行数大于等于 $3$ 的数字即可。同样，我们需要使用 `DISTINCT` 关键字来对结果去重。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            LAG(num) OVER () AS a,
            LEAD(num) OVER () AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = num AND b = num;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER ()), 0, 1) AS st
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

<!-- solution:end -->

<!-- problem:end -->
