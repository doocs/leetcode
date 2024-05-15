---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2504.Concatenate%20the%20Name%20and%20the%20Profession/README.md
tags:
    - 数据库
---

# [2504. 把名字和职业联系起来 🔒](https://leetcode.cn/problems/concatenate-the-name-and-the-profession)

[English Version](/solution/2500-2599/2504.Concatenate%20the%20Name%20and%20the%20Profession/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| person_id   | int     |
| name        | varchar |
| profession  | ENUM    |
+-------------+---------+
<code>person_id</code> 是该表的主键（具有唯一值的列）。
该表中的每一行都包含一个人的 ID、姓名和职业。
profession 是 ENUM 类型，其值为 ('Doctor', 'Singer', 'Actor', 'Player', 'Engineer', 'Lawyer') 之一。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案报告每个人的名字，后面是他们职业的第一个字母，用括号括起来。</p>

<p>返回按 <code>person_id</code> <strong>降序排列&nbsp;</strong>的结果表。</p>

<p>返回结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+-----------+-------+------------+
| person_id | name  | profession |
+-----------+-------+------------+
| 1         | Alex  | Singer     |
| 3         | Alice | Actor      |
| 2         | Bob   | Player     |
| 4         | Messi | Doctor     |
| 6         | Tyson | Engineer   |
| 5         | Meir  | Lawyer     |
+-----------+-------+------------+
<strong>输出:</strong> 
+-----------+----------+
| person_id | name     |
+-----------+----------+
| 6         | Tyson(E) |
| 5         | Meir(L)  |
| 4         | Messi(D) |
| 3         | Alice(A) |
| 2         | Bob(P)   |
| 1         | Alex(S)  |
+-----------+----------+
<strong>解释:</strong> 请注意，在名称和职业的第一个字母之间不应该有任何空白。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT person_id, CONCAT(name, "(", SUBSTRING(profession, 1, 1), ")") AS name
FROM Person
ORDER BY person_id DESC;
```

<!-- tabs:end -->

<!-- end -->
