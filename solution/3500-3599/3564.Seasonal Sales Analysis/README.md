---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3564.Seasonal%20Sales%20Analysis/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3564. 季节性销售分析](https://leetcode.cn/problems/seasonal-sales-analysis)

[English Version](/solution/3500-3599/3564.Seasonal%20Sales%20Analysis/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>sales</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| sale_id       | int     |
| product_id    | int     |
| sale_date     | date    |
| quantity      | int     |
| price         | decimal |
+---------------+---------+
sale_id 是这张表的唯一主键。
每一行包含一件产品的销售信息，包括 product_id，销售日期，销售数量，以及单价。
</pre>

<p>表：<code>products</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| product_name  | varchar |
| category      | varchar |
+---------------+---------+
product_id 是这张表的唯一主键。
每一行包含一件产品的信息，包括它的名字和分类。
</pre>

<p>编写一个解决方案来找到每个季节最受欢迎的产品分类。季节定义如下：</p>

<ul>
	<li><strong>冬季</strong>：十二月，一月，二月</li>
	<li><strong>春季</strong>：三月，四月，五月</li>
	<li><strong>夏季</strong>：六月，七月，八月</li>
	<li><strong>秋季</strong>：九月，十月，十一月</li>
</ul>

<p>一个 <strong>分类</strong>&nbsp;的 <b>受欢迎度</b>&nbsp;由某个 <strong>季节</strong>&nbsp;的 <strong>总销售量</strong>&nbsp;决定。如果有并列，选择总收入最高的类别 (<code>quantity × price</code>)。如果依然并列，返回字典序更小的分类。</p>

<p>返回结果表以季节 <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>sales 表：</p>

<pre class="example-io">
+---------+------------+------------+----------+-------+
| sale_id | product_id | sale_date  | quantity | price |
+---------+------------+------------+----------+-------+
| 1       | 1          | 2023-01-15 | 5        | 10.00 |
| 2       | 2          | 2023-01-20 | 4        | 15.00 |
| 3       | 3          | 2023-03-10 | 3        | 18.00 |
| 4       | 4          | 2023-04-05 | 1        | 20.00 |
| 5       | 1          | 2023-05-20 | 2        | 10.00 |
| 6       | 2          | 2023-06-12 | 4        | 15.00 |
| 7       | 5          | 2023-06-15 | 5        | 12.00 |
| 8       | 3          | 2023-07-24 | 2        | 18.00 |
| 9       | 4          | 2023-08-01 | 5        | 20.00 |
| 10      | 5          | 2023-09-03 | 3        | 12.00 |
| 11      | 1          | 2023-09-25 | 6        | 10.00 |
| 12      | 2          | 2023-11-10 | 4        | 15.00 |
| 13      | 3          | 2023-12-05 | 6        | 18.00 |
| 14      | 4          | 2023-12-22 | 3        | 20.00 |
| 15      | 5          | 2024-02-14 | 2        | 12.00 |
+---------+------------+------------+----------+-------+
</pre>

<p>products 表：</p>

<pre class="example-io">
+------------+-----------------+----------+
| product_id | product_name    | category |
+------------+-----------------+----------+
| 1          | Warm Jacket     | Apparel  |
| 2          | Designer Jeans  | Apparel  |
| 3          | Cutting Board   | Kitchen  |
| 4          | Smart Speaker   | Tech     |
| 5          | Yoga Mat        | Fitness  |
+------------+-----------------+----------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+----------+----------------+---------------+
| season  | category | total_quantity | total_revenue |
+---------+----------+----------------+---------------+
| Fall    | Apparel  | 10             | 120.00        |
| Spring  | Kitchen  | 3              | 54.00         |
| Summer  | Tech     | 5              | 100.00        |
| Winter  | Apparel  | 9              | 110.00        |
+---------+----------+----------------+---------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>秋季（九月，十月，十一月）：</strong>

    <ul>
    	<li>服装：售出 10 件商品（在 9 月有 6 件夹克，在 11 月 有 4 条牛仔裤），收入 $120.00（6×$10.00 + 4×$15.00）</li>
    	<li>健身: 9 月售出&nbsp;3 张瑜伽垫，收入&nbsp;$36.00</li>
    	<li>最受欢迎：服装总数量最多（10）</li>
    </ul>
    </li>
    <li><strong>春季（三月，四月，五月）：</strong>
    <ul>
    	<li>厨房：5 月 售出 3 张菜板，收入 $54.00</li>
    	<li>科技：4 月 售出 1 台智能音箱，收入&nbsp;$20.00</li>
    	<li>服装: 五月售出 2 件保暖夹克，收入&nbsp;$20.00</li>
    	<li>最受欢迎：厨房总数量最多（3）且收入最多（$54.00）</li>
    </ul>
    </li>
    <li><strong>夏季（六月，七月，八月</strong><strong>）：</strong>
    <ul>
    	<li>服装：六月售出 4 件名牌牛仔裤，收入 $60.00</li>
    	<li>健身：六月售出 5&nbsp;张瑜伽垫，收入&nbsp;$60.00</li>
    	<li>厨房：七月售出 2&nbsp;张菜板，收入 $36.00</li>
    	<li>科技：八月售出 5&nbsp;台智能音箱，收入&nbsp;$100.00</li>
    	<li>最受欢迎：科技和健身都有 5 件商品，但科技收入更多（$100.00 vs $60.00）</li>
    </ul>
    </li>
    <li><strong>冬季（十二月，一月，二月</strong><strong>）：</strong>
    <ul>
    	<li>服装：售出 9 件商品（一月有 5 件夹克和&nbsp;4 条牛仔裤），收入 $110.00</li>
    	<li>厨房：十二月售出 6 张菜板，收入 $108.00</li>
    	<li>科技：十二月售出 3 台智能音箱，收入 $60.00</li>
    	<li>健身：二月售出 2 张瑜伽垫，收入 $24.00</li>
    	<li>最受欢迎：服装总数量最多（9）且收入最多（$110.00）</li>
    </ul>
    </li>

</ul>

<p>结果表以季节升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 分组聚合 + 窗口函数

我们可以通过将 `sales` 表和 `products` 表进行等值连接，获取每个销售记录对应的产品类别。接着，我们可以根据销售日期的月份来确定季节，并对每个季节和类别进行分组，计算总销售数量和总收入。最后，我们使用窗口函数来为每个季节内的类别排名，并筛选出排名第一的类别。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    SeasonalSales AS (
        SELECT
            CASE
                WHEN MONTH(sale_date) IN (12, 1, 2) THEN 'Winter'
                WHEN MONTH(sale_date) IN (3, 4, 5) THEN 'Spring'
                WHEN MONTH(sale_date) IN (6, 7, 8) THEN 'Summer'
                WHEN MONTH(sale_date) IN (9, 10, 11) THEN 'Fall'
            END AS season,
            category,
            SUM(quantity) AS total_quantity,
            SUM(quantity * price) AS total_revenue
        FROM
            sales
            JOIN products USING (product_id)
        GROUP BY 1, 2
    ),
    TopCategoryPerSeason AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY season
                ORDER BY total_quantity DESC, total_revenue DESC
            ) AS rk
        FROM SeasonalSales
    )
SELECT season, category, total_quantity, total_revenue
FROM TopCategoryPerSeason
WHERE rk = 1
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def seasonal_sales_analysis(
    products: pd.DataFrame, sales: pd.DataFrame
) -> pd.DataFrame:
    df = sales.merge(products, on="product_id")
    month_to_season = {
        12: "Winter",
        1: "Winter",
        2: "Winter",
        3: "Spring",
        4: "Spring",
        5: "Spring",
        6: "Summer",
        7: "Summer",
        8: "Summer",
        9: "Fall",
        10: "Fall",
        11: "Fall",
    }
    df["season"] = df["sale_date"].dt.month.map(month_to_season)
    seasonal_sales = df.groupby(["season", "category"], as_index=False).agg(
        total_quantity=("quantity", "sum"),
        total_revenue=("quantity", lambda x: (x * df.loc[x.index, "price"]).sum()),
    )
    seasonal_sales["rk"] = (
        seasonal_sales.sort_values(
            ["season", "total_quantity", "total_revenue"],
            ascending=[True, False, False],
        )
        .groupby("season")
        .cumcount()
        + 1
    )
    result = seasonal_sales[seasonal_sales["rk"] == 1].copy()
    return result[
        ["season", "category", "total_quantity", "total_revenue"]
    ].sort_values("season")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
