---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [626. 换座位](https://leetcode.cn/problems/exchange-seats)

[English Version](/solution/0600-0699/0626.Exchange%20Seats/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Seat</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| student     | varchar |
+-------------+---------+
<code>id</code> 是该表的主键（唯一值）列。
该表的每一行都表示学生的姓名和 ID。
ID 序列始终从 1 开始并连续增加。
</pre>

<p>&nbsp;</p>

<p>编写解决方案来交换每两个连续的学生的座位号。如果学生的数量是奇数，则最后一个学生的id不交换。</p>

<p>按 <code>id</code> <strong>升序</strong> 返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Seat 表:
+----+---------+
| id | student |
+----+---------+
| 1  | Abbot   |
| 2  | Doris   |
| 3  | Emerson |
| 4  | Green   |
| 5  | Jeames  |
+----+---------+
<strong>输出:</strong> 
+----+---------+
| id | student |
+----+---------+
| 1  | Doris   |
| 2  | Abbot   |
| 3  | Green   |
| 4  | Emerson |
| 5  | Jeames  |
+----+---------+
<strong>解释:
</strong>请注意，如果学生人数为奇数，则不需要更换最后一名学生的座位。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 相邻奇偶座位互换，最后一人若落单则不动。自连接可按「配对 id」取对方姓名。
>
> `(id+1)^1-1` 把奇数映到下一偶数、偶数映到上一奇数。左连接后 `COALESCE` 在没有搭档时保留原名。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    Seat AS s1
    LEFT JOIN Seat AS s2 ON (s1.id + 1) ^ 1 - 1 = s2.id
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 也可不连表，直接改 `id`：非末奇数加一、偶数减一、末奇数不变，再按新 `id` 排序，效果与换人相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    id + (
        CASE
            WHEN id % 2 = 1
            AND id != (SELECT MAX(id) FROM Seat) THEN 1
            WHEN id % 2 = 0 THEN -1
            ELSE 0
        END
    ) AS id,
    student
FROM Seat
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- thinking:start -->

> **思考**
>
> 把 $0$ 起始的下标做异或翻转，再用 `RANK` 得到新座位号，一行即可表达成对交换，末位因没有配对自然保持相对顺序。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    RANK() OVER (ORDER BY (id - 1) ^ 1) AS id,
    student
FROM Seat;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法四

<!-- thinking:start -->

> **思考**
>
> 方法二用子查询取最大 `id` 判断末行。亦可用 `ROW_NUMBER` 与窗口 `COUNT` 比较是否最后一行，避免再扫一遍表。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN id & 1 = 0 THEN id - 1
        WHEN ROW_NUMBER() OVER (ORDER BY id) != COUNT(id) OVER () THEN id + 1
        ELSE id
    END AS id,
    student
FROM Seat
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
