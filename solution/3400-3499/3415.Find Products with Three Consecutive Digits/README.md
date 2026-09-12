---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [3415. 查找具有三个连续数字的产品 🔒](https://leetcode.cn/problems/find-products-with-three-consecutive-digits)

[English Version](/solution/3400-3499/3415.Find%20Products%20with%20Three%20Consecutive%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| name        | varchar |
+-------------+---------+
product_id 是这张表的唯一主键。
这张表的每一行包含产品的 ID 和名字。
</pre>

<p>编写一个解决方案来找到所有名字中包含 <strong>三位连续数字</strong>&nbsp;且无连续三位以上数字的所有 <strong>产品</strong>。</p>

<p>返回结果表以&nbsp;<code>product_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>products 表：</p>

<pre class="example-io">
+-------------+--------------------+
| product_id  | name               |
+-------------+--------------------+
| 1           | ABC123XYZ          |
| 2           | A12B34C            |
| 3           | Product56789       |
| 4           | NoDigitsHere       |
| 5           | 789Product         |
| 6           | Item003Description |
| 7           | Product12X34       |
+-------------+--------------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+--------------------+
| product_id  | name               |
+-------------+--------------------+
| 1           | ABC123XYZ          |
| 5           | 789Product         |
| 6           | Item003Description |
+-------------+--------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>产品 1：ABC123XYZ 包含数字 123。</li>
	<li>产品 5：789Product&nbsp;包含数字 789。</li>
	<li>产品 6：Item003Description 包含数字 003，恰好是三个数字。</li>
</ul>

<p><strong>注意：</strong></p>

<ul>
	<li>结果以&nbsp;<code>product_id</code>&nbsp;升序排序。</li>
	<li>只有名称中恰好具有三个连续数字的产品才会包含在结果中。</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：正则匹配

<!-- thinking:start -->

> **思考**
>
> 需要找出名称中含有连续三位数字的产品。若用循环逐字符数长度，边界（数字夹在字母或串端）容易写漏。
>
> 正则一次扫描即可同时处理「恰好三段数字被非数字或边界隔开」的情形。模式 $(^|[^0-9])[0-9]{3}([^0-9]|$)$ 避免把更长数字串中的局部三段误判为独立片段的同时，仍能命中孤立的三位。
>
> 过滤后按 $\textit{product\_id}$ 排序返回，与题面输出顺序一致。

<!-- thinking:end -->

我们可以使用正则表达式来匹配包含三个连续数字的产品名称。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT product_id, name
FROM Products
WHERE name REGEXP '(^|[^0-9])[0-9]{3}([^0-9]|$)'
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_products(products: pd.DataFrame) -> pd.DataFrame:
    filtered = products[
        products["name"].str.contains(r"(^|[^0-9])[0-9]{3}([^0-9]|$)", regex=True)
    ]
    return filtered.sort_values(by="product_id")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
