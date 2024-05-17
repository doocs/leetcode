---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1571.Warehouse%20Manager/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1571. 仓库经理 🔒](https://leetcode.cn/problems/warehouse-manager)

[English Version](/solution/1500-1599/1571.Warehouse%20Manager/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Warehouse</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| name         | varchar |
| product_id   | int     |
| units        | int     |
+--------------+---------+
(name, product_id) 是该表主键(具有唯一值的列的组合).
该表的行包含了每个仓库的所有商品信息.
</pre>

<p>&nbsp;</p>

<p>表: <code>Products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| Width         | int     |
| Length        | int     |
| Height        | int     |
+---------------+---------+
product_id 是该表主键(具有唯一值的列).
该表的行包含了每件商品以英尺为单位的尺寸(宽度, 长度和高度)信息.
</pre>

<p>&nbsp;</p>

<p>编写解决方案报告每个仓库的存货量是多少立方英尺。</p>

<p>返回结果没有顺序要求。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
Warehouse 表</code>:
+------------+--------------+-------------+
| name       | product_id   | units       |
+------------+--------------+-------------+
| LCHouse1   | 1            | 1           |
| LCHouse1   | 2            | 10          |
| LCHouse1   | 3            | 5           |
| LCHouse2   | 1            | 2           |
| LCHouse2   | 2            | 2           |
| LCHouse3   | 4            | 1           |
+------------+--------------+-------------+
Products 表:
+------------+--------------+------------+----------+-----------+
| product_id | product_name | Width      | Length   | Height    |
+------------+--------------+------------+----------+-----------+
| 1          | LC-TV        | 5          | 50       | 40        |
| 2          | LC-KeyChain  | 5          | 5        | 5         |
| 3          | LC-Phone     | 2          | 10       | 10        |
| 4          | LC-T-Shirt   | 4          | 10       | 20        |
+------------+--------------+------------+----------+-----------+
<strong>输出：</strong>
+----------------+------------+
| warehouse_name<code> </code>| volume<code>   </code>  | 
+----------------+------------+
| LCHouse1       | 12250      | 
| LCHouse2       | 20250      |
| LCHouse3       | 800        |
+----------------+------------+
<strong>解释：</strong>
Id为1的商品(LC-TV)的存货量为 5x50x40 = 10000
Id为2的商品(LC-KeyChain)的存货量为 5x5x5 = 125 
Id为3的商品(LC-Phone)的存货量为 2x10x10 = 200
Id为4的商品(LC-T-Shirt)的存货量为 4x10x20 = 800
仓库LCHouse1: 1个单位的LC-TV + 10个单位的LC-KeyChain + 5个单位的LC-Phone.
&nbsp;         总存货量为: 1*10000 + 10*125  + 5*200 = 12250 立方英尺
仓库LCHouse2: 2个单位的LC-TV + 2个单位的LC-KeyChain.
&nbsp;         总存货量为: 2*10000 + 2*125 = 20250 立方英尺
仓库LCHouse3: 1个单位的LC-T-Shirt.
          总存货量为: 1*800 = 800 立方英尺.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组求和

我们可以使用等值连接将 `Warehouse` 表和 `Products` 表按照 `product_id` 进行连接，并按照仓库名称进行分组，然后使用 `SUM` 函数计算每个仓库的存货量。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    name AS warehouse_name,
    SUM(width * length * height * units) AS volume
FROM
    Warehouse
    JOIN Products USING (product_id)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
