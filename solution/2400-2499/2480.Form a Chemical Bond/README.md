---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2480.Form%20a%20Chemical%20Bond/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2480. 形成化学键 🔒](https://leetcode.cn/problems/form-a-chemical-bond)

[English Version](/solution/2400-2499/2480.Form%20a%20Chemical%20Bond/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Elements</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| symbol      | varchar |
| type        | enum    |
| electrons   | int     |
+-------------+---------+
symbol 是该表的主键(具有唯一值的列)。
该表的每一行包含一个元素的信息。
type 是 ENUM&nbsp;类型，它的值是 ('Metal', 'Nonmetal', 'Noble') 之一
 - 如果 type 是 Noble, electrons 是 0。
 - 如果 type 是 Metal, electrons 是这种元素的一个原子所能给出的电子数。
 - 如果 type 是 Nonmetal, electrons 这种元素的一个原子所需要的电子数。
</pre>

<p>&nbsp;</p>

<p>如果一个元素是&nbsp;<code>'Metal'</code>，另外一个元素是&nbsp;<code>'Nonmetal'</code>&nbsp;，那么它们可以形成键。</p>

<p>编写一个解决方案找出所有可以形成键的元素对。</p>

<p data-group="1-1">以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Elements 表:
+--------+----------+-----------+
| symbol | type     | electrons |
+--------+----------+-----------+
| He     | Noble    | 0         |
| Na     | Metal    | 1         |
| Ca     | Metal    | 2         |
| La     | Metal    | 3         |
| Cl     | Nonmetal | 1         |
| O      | Nonmetal | 2         |
| N      | Nonmetal | 3         |
+--------+----------+-----------+
<strong>输出:</strong> 
+-------+----------+
| metal | nonmetal |
+-------+----------+
| La    | Cl       |
| Ca    | Cl       |
| Na    | Cl       |
| La    | O        |
| Ca    | O        |
| Na    | O        |
| La    | N        |
| Ca    | N        |
| Na    | N        |
+-------+----------+
<strong>解释:</strong> 
Metal 元素包括 La, Ca, and Na.
Nonmetal 元素包括 Cl, O, and N.
每个 Metal 元素与输出表中的 Nonmeal 元素配对。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 化学键即金属与非金属的笛卡尔积。两表自连接，分别限制 $\textit{type}$ 为 Metal 与 Nonmetal，选出符号对即可。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT a.symbol AS metal, b.symbol AS nonmetal
FROM
    Elements AS a,
    Elements AS b
WHERE a.type = 'Metal' AND b.type = 'Nonmetal';
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
