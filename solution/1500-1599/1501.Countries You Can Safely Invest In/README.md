---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1501.Countries%20You%20Can%20Safely%20Invest%20In/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1501. 可以放心投资的国家 🔒](https://leetcode.cn/problems/countries-you-can-safely-invest-in)

[English Version](/solution/1500-1599/1501.Countries%20You%20Can%20Safely%20Invest%20In/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表&nbsp;<code>Person</code>:</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| id             | int     |
| name           | varchar |
| phone_number   | varchar |
+----------------+---------+
id 是该表具有唯一值的列.
该表每一行包含一个人的名字和电话号码.
电话号码的格式是:'xxx-yyyyyyy', 其中 xxx 是国家码(3 个字符), yyyyyyy 是电话号码(7 个字符), x 和 y 都表示数字. 同时, 国家码和电话号码都可以包含前导 0.
</pre>

<p>&nbsp;</p>

<p>表&nbsp;<code>Country</code>:</p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| name           | varchar |
| country_code   | varchar |
+----------------+---------+
country_code 是该表具有唯一值的列.
该表每一行包含国家名和国家码. country_code 的格式是'xxx', x 是数字.
</pre>

<p>&nbsp;</p>

<p>表&nbsp;<code>Calls</code>:</p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| caller_id   | int  |
| callee_id   | int  |
| duration    | int  |
+-------------+------+
该表无主键, 可能包含重复行.
每一行包含呼叫方 id, 被呼叫方 id 和以分钟为单位的通话时长. caller_id != callee_id
</pre>

<p>&nbsp;</p>

<p>一家电信公司想要投资新的国家。该公司想要投资的国家是:&nbsp; 该国的平均通话时长要严格地大于全球平均通话时长。</p>

<p>写一个解决方案,&nbsp;&nbsp;找到所有该公司可以投资的国家。</p>

<p>返回的结果表 <strong>无顺序要求</strong>。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<span style="white-space: pre-wrap;"><strong>输入：</strong>
Person 表：</span>
+----+----------+--------------+
| id | name     | phone_number |
+----+----------+--------------+
| 3  | Jonathan | 051-1234567  |
| 12 | Elvis    | 051-7654321  |
| 1  | Moncef   | 212-1234567  |
| 2  | Maroua   | 212-6523651  |
| 7  | Meir     | 972-1234567  |
| 9  | Rachel   | 972-0011100  |
+----+----------+--------------+
Country 表:
+----------+--------------+
| name     | country_code |
+----------+--------------+
| Peru     | 051          |
| Israel   | 972          |
| Morocco  | 212          |
| Germany  | 049          |
| Ethiopia | 251          |
+----------+--------------+
Calls 表:
+-----------+-----------+----------+
| caller_id | callee_id | duration |
+-----------+-----------+----------+
| 1         | 9         | 33       |
| 2         | 9         | 4        |
| 1         | 2         | 59       |
| 3         | 12        | 102      |
| 3         | 12        | 330      |
| 12        | 3         | 5        |
| 7         | 9         | 13       |
| 7         | 1         | 3        |
| 9         | 7         | 1        |
| 1         | 7         | 7        |
+-----------+-----------+----------+
<b>输出：</b>
+----------+
| country  |
+----------+
| Peru     |
+----------+
<b>解释：</b>
国家 Peru 的平均通话时长是 (102 + 102 + 330 + 330 + 5 + 5) / 6 = 145.666667
国家 Israel 的平均通话时长是 (33 + 4 + 13 + 13 + 3 + 1 + 1 + 7) / 8 = 9.37500
国家 Morocco 的平均通话时长是 (33 + 4 + 59 + 59 + 3 + 7) / 6 = 27.5000 
全球平均通话时长 = (2 * (33 + 4 + 59 + 102 + 330 + 5 + 13 + 3 + 1 + 7)) / 20 = 55.70000
所以, Peru 是唯一的平均通话时长大于全球平均通话时长的国家, 也是唯一的推荐投资的国家.
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组 + 子查询

<!-- thinking:start -->

> **思考**
>
> 题目要求找出平均通话时长严格高于全球平均水平的国家。通话记录只给出双方编号，国家信息藏在电话号码前三位，因此必须把人、通话与国家三张表对齐后才能按国家聚合。
>
> 将 $Person$ 与 $Calls$ 按「主叫或被叫等于该人」连接，再用号码前三位对齐 $Country$，即可按国家求平均时长，并与 $Calls$ 上的全局平均比较。子查询承担全局均值，外层过滤即可得到答案。

<!-- thinking:end -->

我们可以使用等值连接，将 `Person` 表和 `Calls` 表连接起来，连接的条件是 `Person.id = Calls.caller_id` 或者 `Person.id = Calls.callee_id`，然后再将连接后的表和 `Country` 表连接起来，连接的条件是 `left(phone_number, 3) = country_code`，最后按照国家分组，计算每个国家的平均通话时长，然后再使用子查询，找出平均通话时长大于全球平均通话时长的国家。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT country
FROM
    (
        SELECT c.name AS country, AVG(duration) AS duration
        FROM
            Person
            JOIN Calls ON id IN(caller_id, callee_id)
            JOIN Country AS c ON LEFT(phone_number, 3) = country_code
        GROUP BY 1
    ) AS t
WHERE duration > (SELECT AVG(duration) FROM Calls);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一用嵌套子查询完成「按国家聚合再与全局均值比较」，语义无误，只是中间结果还要再包一层。公共表表达式可将国家平均时长物化为 $T$，外层直接筛选，查询结构更清晰，计算关系不变。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT c.name AS country, AVG(duration) AS duration
        FROM
            Person
            JOIN Calls ON id IN(caller_id, callee_id)
            JOIN Country AS c ON LEFT(phone_number, 3) = country_code
        GROUP BY 1
    )
SELECT country
FROM T
WHERE duration > (SELECT AVG(duration) FROM Calls);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
