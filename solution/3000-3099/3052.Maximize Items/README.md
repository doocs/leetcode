---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3052.Maximize%20Items/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3052. 最大化商品 🔒](https://leetcode.cn/problems/maximize-items)

[English Version](/solution/3000-3099/3052.Maximize%20Items/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Inventory</code></font></p>

<pre>
+----------------+---------+ 
| Column Name    | Type    | 
+----------------+---------+ 
| item_id        | int     | 
| item_type      | varchar |
| item_category  | varchar |
| square_footage | decimal |
+----------------+---------+
item_id 是这张表中有不同值的列。
每一行包含 item id，item type，item category 和 sqaure footage。
</pre>

<p>Leetcode 仓库想要最大化它能够在&nbsp;<code>500,000</code> 平方英尺的仓库中储存的商品数。他想要尽可能多地存储 <strong>主要</strong> 商品，然后用 <strong>剩下</strong> 的空间存储最大数量的 <strong>非主要</strong> 商品。</p>

<p>编写一个解决方案来找到能够在&nbsp;<code>500,000</code>&nbsp;平方英尺的仓库中存储&nbsp;<b>主要</b>&nbsp;和&nbsp;<strong>非主要</strong>&nbsp;商品的数量。输出商品类型&nbsp;<code>prime_eligible</code>&nbsp;和&nbsp;<code>not_prime</code>，以及能储存商品的最大数量。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>商品 <strong>数</strong> 必须是一个整数。</li>
	<li>如果&nbsp;<strong>not_prime</strong>&nbsp;分类的数量是&nbsp;<code>0</code>，你应当对这部分分类 <strong>输出</strong>&nbsp;<code>0</code>&nbsp;。</li>
</ul>

<p>返回结果表，以商品数 <strong>升序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Inventory 表：
+---------+----------------+---------------+----------------+
| item_id | item_type      | item_category | square_footage | 
+---------+----------------+---------------+----------------+
| 1374    | prime_eligible | Watches       | 68.00          | 
| 4245    | not_prime      | Art           | 26.40          | 
| 5743    | prime_eligible | Software      | 325.00         | 
| 8543    | not_prime      | Clothing      | 64.50          |  
| 2556    | not_prime      | Shoes         | 15.00          |
| 2452    | prime_eligible | Scientific    | 85.00          |
| 3255    | not_prime      | Furniture     | 22.60          | 
| 1672    | prime_eligible | Beauty        | 8.50           |  
| 4256    | prime_eligible | Furniture     | 55.50          |
| 6325    | prime_eligible | Food          | 13.20          | 
+---------+----------------+---------------+----------------+
<strong>输出：</strong> 
+----------------+-------------+
| item_type      | item_count  | 
+----------------+-------------+
| prime_eligible | 5400        | 
| not_prime      | 8           | 
+----------------+-------------+
<strong>解释：</strong> 
- prime-eligible 分类包括总计 6 件商品，总面积为 555.20 (68 + 325 + 85 + 8.50 + 55.50 + 13.20) 平方英尺。可以存放这 6 种物品的 900 件组合，总计 5400 件，占地 499,680 平方英尺。
- 对于 not_prime 分类，共有 4 件商品，总面积为 128.50 平方英尺。在减去 prime-eligible 商品使用的空间之后 (500,000 - 499,680 = 320)，还有放 2 件 non-prime 商品的空间，在320平方英尺的面积内，共容纳 8 个 non-prime 商品。
输出表以商品数量降序排序。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：连接查询 + 合并

我们先计算出所有 prime_eligible 类型的物品的总面积，记录在 `T` 表的 `s` 字段中。

接下来，我们分别计算 prime_eligible 和 not_prime 类型的物品的数量。对于 prime_eligible 类型的物品，我们可以存储的份数是 $\lfloor \frac{500000}{s} \rfloor$，对于 not_prime 类型的物品，我们可以存储的份数是 $\lfloor \frac{500000 \mod s}{\sum \text{s1}} \rfloor$。其中 $\sum \text{s1}$ 是所有 not_prime 类型的物品的总面积。再分别乘上 prime_eligible 和 not_prime 类型的物品的数量，就是我们的结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT SUM(square_footage) AS s
        FROM Inventory
        WHERE item_type = 'prime_eligible'
    )
SELECT
    'prime_eligible' AS item_type,
    COUNT(1) * FLOOR(500000 / s) AS item_count
FROM
    Inventory
    JOIN T
WHERE item_type = 'prime_eligible'
UNION ALL
SELECT
    'not_prime',
    IFNULL(COUNT(1) * FLOOR(IF(s = 0, 500000, 500000 % s) / SUM(square_footage)), 0)
FROM
    Inventory
    JOIN T
WHERE item_type = 'not_prime';
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
