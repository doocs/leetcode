---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3626.Find%20Stores%20with%20Inventory%20Imbalance/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3626. 查找库存不平衡的店铺](https://leetcode.cn/problems/find-stores-with-inventory-imbalance)

[English Version](/solution/3600-3699/3626.Find%20Stores%20with%20Inventory%20Imbalance/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>stores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| store_id    | int     |
| store_name  | varchar |
| location    | varchar |
+-------------+---------+
store_id 是这张表的唯一主键。
每一行包含有关商店及其位置的信息。
</pre>

<p>表：<code>inventory</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| inventory_id| int     |
| store_id    | int     |
| product_name| varchar |
| quantity    | int     |
| price       | decimal |
+-------------+---------+
inventory_id 是这张表的唯一主键。
每一行代表特定商店中某一特定产品的库存情况。
</pre>

<p>编写一个解决方案来查找库存不平衡的商店 - 即最贵商品的库存比最便宜商品少的商店。</p>

<ul>
	<li>对于每个商店，识别 <strong>最贵的商品</strong>（最高价格）及其数量，如果有多个最贵的商品则选取数量最多的一个。</li>
	<li>对于每个商店，识别 <strong>最便宜的商品</strong>（最低价格）及其数量，如果有多个最便宜的物品则选取数量最多的一个。</li>
	<li>如果最贵商品的数量 <strong>少于</strong> 最便宜商品的数量，则商店存在库存不平衡。</li>
	<li>按（最便宜商品的数量/最贵商品的数量）计算 <strong>不平衡比</strong>。</li>
	<li>不平衡比&nbsp;<strong>舍入到 2 位</strong>&nbsp;小数</li>
	<li>结果只包含&nbsp;<strong>至少有</strong><strong> </strong><code>3</code>&nbsp;<strong>个不同商品</strong> 的店铺</li>
</ul>

<p>返回结果表以不平衡比率降序排列，然后按商店名称升序排列。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>stores 表：</p>

<pre class="example-io">
+----------+----------------+-------------+
| store_id | store_name     | location    |
+----------+----------------+-------------+
| 1        | Downtown Tech  | New York    |
| 2        | Suburb Mall    | Chicago     |
| 3        | City Center    | Los Angeles |
| 4        | Corner Shop    | Miami       |
| 5        | Plaza Store    | Seattle     |
+----------+----------------+-------------+
</pre>

<p>inventory 表：</p>

<pre class="example-io">
+--------------+----------+--------------+----------+--------+
| inventory_id | store_id | product_name | quantity | price  |
+--------------+----------+--------------+----------+--------+
| 1            | 1        | Laptop       | 5        | 999.99 |
| 2            | 1        | Mouse        | 50       | 19.99  |
| 3            | 1        | Keyboard     | 25       | 79.99  |
| 4            | 1        | Monitor      | 15       | 299.99 |
| 5            | 2        | Phone        | 3        | 699.99 |
| 6            | 2        | Charger      | 100      | 25.99  |
| 7            | 2        | Case         | 75       | 15.99  |
| 8            | 2        | Headphones   | 20       | 149.99 |
| 9            | 3        | Tablet       | 2        | 499.99 |
| 10           | 3        | Stylus       | 80       | 29.99  |
| 11           | 3        | Cover        | 60       | 39.99  |
| 12           | 4        | Watch        | 10       | 299.99 |
| 13           | 4        | Band         | 25       | 49.99  |
| 14           | 5        | Camera       | 8        | 599.99 |
| 15           | 5        | Lens         | 12       | 199.99 |
+--------------+----------+--------------+----------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+----------+----------------+-------------+------------------+--------------------+------------------+
| store_id | store_name     | location    | most_exp_product | cheapest_product   | imbalance_ratio  |
+----------+----------------+-------------+------------------+--------------------+------------------+
| 3        | City Center    | Los Angeles | Tablet           | Stylus             | 40.00            |
| 1        | Downtown Tech  | New York    | Laptop           | Mouse              | 10.00            |
| 2        | Suburb Mall    | Chicago     | Phone            | Case               | 25.00            |
+----------+----------------+-------------+------------------+--------------------+------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Downtown Tech (store_id = 1)：</strong>

    <ul>
    	<li>最贵的商品：笔记本（$999.99）数量为 5</li>
    	<li>最便宜的商品：鼠标（$19.99）数量为 50</li>
    	<li>库存不平衡：5 &lt; 50（贵的商品的库存更少）</li>
    	<li>不平衡比：50 / 5 = 10.00</li>
    	<li>有 4&nbsp;件商品（≥ 3），所以满足要求</li>
    </ul>
    </li>
    <li><strong>Suburb Mall (store_id = 2)：</strong>
    <ul>
    	<li>最贵的商品：手机（$699.99）数量为 3</li>
    	<li>最便宜的商品：保护壳（$15.99）数量为75</li>
    	<li>库存不平衡：3 &lt; 75（贵的商品的库存更少）</li>
    	<li>不平衡比：75 / 3 = 25.00</li>
    	<li>有 4&nbsp;件商品（≥ 3），所以满足要求</li>
    </ul>
    </li>
    <li><strong>City Center (store_id = 3)：</strong>
    <ul>
    	<li>最贵的商品：平板电脑（$499.99）数量为 2</li>
    	<li>最便宜的商品：触控笔（$29.99）数量为 80</li>
    	<li>不平衡比：2 &lt; 80（贵的商品的库存更少）</li>
    	<li>不平衡比：80 / 2 = 40.00</li>
    	<li>有 3 件商品（≥ 3），所以满足要求</li>
    </ul>
    </li>
    <li><strong>未包含的商店：</strong>
    <ul>
    	<li>Corner Shop（store_id = 4）：只有两件商品（手表，手环）- 不满足最少 3 件商品的要求</li>
    	<li>Plaza Store（store_id = 5）：只有两件商品（相机，镜头）- 不满足最少 3 件商品的要求</li>
    </ul>
    </li>

</ul>

<p>结果表按不平衡比降序排序，然后以商店名升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + 连接

我们可以使用窗口函数来计算每个商店的最贵和最便宜商品，并且使用连接来筛选出库存不平衡的商店。具体步骤如下：

1. **计算每个商店的最贵商品**：使用 `RANK()` 窗口函数按价格降序排列，并在数量相同的情况下按数量降序排列，选取排名第一的商品。
2. **计算每个商店的最便宜商品**：使用 `RANK()` 窗口函数按价格升序排列，并在数量相同的情况下按数量降序排列，选取排名第一的商品。
3. **筛选至少有 3 个不同商品的商店**：使用 `COUNT()` 窗口函数来统计每个商店的商品数量，并筛选出数量大于等于 3 的商店。
4. **连接最贵和最便宜商品**：将最贵商品和最便宜商品的结果进行连接，确保最贵商品的数量小于最便宜商品的数量。
5. **计算不平衡比**：计算最便宜商品数量与最贵商品数量的比率，并将其舍入到两位小数。
6. **连接商店信息**：将结果与商店信息表进行连接，以获取商店名称和位置。
7. **排序结果**：按不平衡比降序排列，然后按商店名称升序排列。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            store_id,
            product_name,
            quantity,
            RANK() OVER (
                PARTITION BY store_id
                ORDER BY price DESC, quantity DESC
            ) rk1,
            RANK() OVER (
                PARTITION BY store_id
                ORDER BY price, quantity DESC
            ) rk2,
            COUNT(1) OVER (PARTITION BY store_id) cnt
        FROM inventory
    ),
    P1 AS (
        SELECT *
        FROM T
        WHERE rk1 = 1 AND cnt >= 3
    ),
    P2 AS (
        SELECT *
        FROM T
        WHERE rk2 = 1
    )
SELECT
    s.store_id store_id,
    store_name,
    location,
    p1.product_name most_exp_product,
    p2.product_name cheapest_product,
    ROUND(p2.quantity / p1.quantity, 2) imbalance_ratio
FROM
    P1 p1
    JOIN P2 p2 ON p1.store_id = p2.store_id AND p1.quantity < p2.quantity
    JOIN stores s ON p1.store_id = s.store_id
ORDER BY imbalance_ratio DESC, store_name;
```

#### Pandas

```python
import pandas as pd


def find_inventory_imbalance(
    stores: pd.DataFrame, inventory: pd.DataFrame
) -> pd.DataFrame:
    # 首先筛选出至少有3个产品的店铺
    store_counts = inventory["store_id"].value_counts()
    valid_stores = store_counts[store_counts >= 3].index

    # 找出每个店铺最贵的产品
    most_expensive = (
        inventory[inventory["store_id"].isin(valid_stores)]
        .sort_values(["store_id", "price", "quantity"], ascending=[True, False, False])
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # 找出每个店铺最便宜的产品
    cheapest = (
        inventory.sort_values(
            ["store_id", "price", "quantity"], ascending=[True, True, False]
        )
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # 合并结果
    merged = pd.merge(
        most_expensive, cheapest, on="store_id", suffixes=("_most", "_cheap")
    )

    # 筛选出最贵产品数量 < 最便宜产品数量的记录
    result = merged[merged["quantity_most"] < merged["quantity_cheap"]].copy()

    # 计算不平衡比例
    result["imbalance_ratio"] = (
        result["quantity_cheap"] / result["quantity_most"]
    ).round(2)

    # 合并店铺信息
    result = pd.merge(result, stores, on="store_id")

    # 选择并重命名列
    result = result[
        [
            "store_id",
            "store_name",
            "location",
            "product_name_most",
            "product_name_cheap",
            "imbalance_ratio",
        ]
    ].rename(
        columns={
            "product_name_most": "most_exp_product",
            "product_name_cheap": "cheapest_product",
        }
    )

    # 按要求排序
    result = result.sort_values(
        ["imbalance_ratio", "store_name"], ascending=[False, True]
    ).reset_index(drop=True)

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
