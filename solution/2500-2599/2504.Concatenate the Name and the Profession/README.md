# [2504. 把名字和职业联系起来](https://leetcode.cn/problems/concatenate-the-name-and-the-profession)

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
person_id 是该表的主键。
该表中的每一行都包含一个人的 ID、姓名和职业。
profession 是 ENUM 类型，其值为 ('Doctor', 'Singer', 'Actor', 'Player', 'Engineer', 'Lawyer') 之一。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，报告每个人的名字，后面是他们职业的第一个字母，用括号括起来。</p>

<p>返回按 <code>person_id</code> <strong>降序排列&nbsp;</strong>的结果表。</p>

<p>查询结果格式示例如下。</p>

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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
SELECT person_id, CONCAT(name, "(", substring(profession, 1, 1), ")") AS name
FROM Person
ORDER BY person_id DESC;
```

<!-- tabs:end -->
