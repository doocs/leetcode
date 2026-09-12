---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [3293. 计算产品最终价格 🔒](https://leetcode.cn/problems/calculate-product-final-price)

[English Version](/solution/3200-3299/3293.Calculate%20Product%20Final%20Price/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace"><code>Products</code></font></p>

<pre>
+------------+---------+ 
| Column Name| Type    | 
+------------+---------+ 
| product_id | int     | 
| category   | varchar |
| price      | decimal |
+------------+---------+
product_id 是这张表的唯一主键。
每一行包含产品的 ID，分类以及价格。
</pre>

<p>表：<font face="monospace"><code>Discounts</code></font></p>

<pre>
+------------+---------+ 
| Column Name| Type    | 
+------------+---------+ 
| category   | varchar |
| discount   | int     |
+------------+---------+
category 是这张表的主键。
每一行包含有一个产品分类和该分类的折扣百分比（值的范围从 0 到 100）。
</pre>

<p>编写一个解决方案来找到每个产品使用 <strong>分类折扣</strong>&nbsp;后的 <strong>最终价格</strong>。如果一个产品分类 <strong>没有关联的折扣</strong>，它的价格保持 <strong>不变</strong>。</p>

<p>返回结果表以&nbsp;<code>product_id</code><em> </em><strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p><code>Products</code> 表：</p>

<pre class="example-io">
+------------+-------------+-------+
| product_id | category    | price |
+------------+-------------+-------+
| 1          | Electronics | 1000  |
| 2          | Clothing    | 50    |
| 3          | Electronics | 1200  | 
| 4          | Home        | 500   |
+------------+-------------+-------+
  </pre>

<p><code>Discounts</code> 表：</p>

<pre class="example-io">
+------------+----------+
| category   | discount |
+------------+----------+
| Electronics| 10       |
| Clothing   | 20       |
+------------+----------+
  </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------+------------+-------------+
| product_id | final_price| category    |
+------------+------------+-------------+
| 1          | 900        | Electronics |
| 2          | 40         | Clothing    |
| 3          | 1080       | Electronics |
| 4          | 500        | Home        |
+------------+------------+-------------+
  </pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于产品 1，它属于电器分类，有 10% 的折扣，所以最终价格为 1000 - (10% of 1000) = 900。</li>
	<li>对于产品 2，它属于衣物分类，有 20% 的折扣，所以最终价格为 50 - (20% of 50) = 40。</li>
	<li>对于产品 3，它属于电器分类，有 10% 的折扣，所以最终价格为&nbsp;1200 - (10% of 1200) = 1080。</li>
	<li>对于产品 4，它属于家具分类，没有可用的折扣，所以最终价格仍是 500。</li>
</ul>
结果表以 product_id 升序排序。</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接

<!-- thinking:start -->

> **思考**
>
> 最终价是原价乘 $(100-折扣)/100$，无折扣则视为 $0$。按类别对齐折扣，左连接可保留没有折扣的商品。
>
> `Products` 左连 `Discounts`，空折扣填 $0$ 后计算 `final_price`，按 `product_id` 排序输出。

<!-- thinking:end -->

我们可以将 `Products` 表和 `Discounts` 表按照 `category` 列进行左连接，然后计算最终价格。如果某个产品的类别没有关联的折扣，那么它的价格保持不变。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    product_id,
    price * (100 - IFNULL(discount, 0)) / 100 final_price,
    category
FROM
    Products
    LEFT JOIN Discounts USING (category)
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def calculate_final_prices(
    products: pd.DataFrame, discounts: pd.DataFrame
) -> pd.DataFrame:
    # Perform a left join on the 'category' column
    merged_df = pd.merge(products, discounts, on="category", how="left")

    # Calculate the final price
    merged_df["final_price"] = (
        merged_df["price"] * (100 - merged_df["discount"].fillna(0)) / 100
    )

    # Select the necessary columns and sort by 'product_id'
    result_df = merged_df[["product_id", "final_price", "category"]].sort_values(
        "product_id"
    )

    return result_df
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
