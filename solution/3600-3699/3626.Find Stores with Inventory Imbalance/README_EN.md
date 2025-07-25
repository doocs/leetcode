---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3626.Find%20Stores%20with%20Inventory%20Imbalance/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3626. Find Stores with Inventory Imbalance](https://leetcode.com/problems/find-stores-with-inventory-imbalance)

[中文文档](/solution/3600-3699/3626.Find%20Stores%20with%20Inventory%20Imbalance/README.md)

## Description

<!-- description:start -->

<p>Table: <code>stores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| store_id    | int     |
| store_name  | varchar |
| location    | varchar |
+-------------+---------+
store_id is the unique identifier for this table.
Each row contains information about a store and its location.
</pre>

<p>Table: <code>inventory</code></p>

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
inventory_id is the unique identifier for this table.
Each row represents the inventory of a specific product at a specific store.
</pre>

<p>Write a solution to find stores that have <strong>inventory imbalance</strong> - stores where the most expensive product has lower stock than the cheapest product.</p>

<ul>
	<li>For each store, identify the <strong>most expensive product</strong> (highest price) and its quantity</li>
	<li>For each store, identify the <strong>cheapest product</strong> (lowest price) and its quantity</li>
	<li>A store has inventory imbalance if the most expensive product&#39;s quantity is <strong>less than</strong> the cheapest product&#39;s quantity</li>
	<li>Calculate the <strong>imbalance ratio</strong> as (cheapest_quantity / most_expensive_quantity)</li>
	<li><strong>Round</strong> the imbalance ratio to <strong>2</strong> decimal places</li>
	<li>Only include stores that have <strong>at least </strong><code>3</code><strong> different products</strong></li>
</ul>

<p>Return <em>the result table ordered by imbalance ratio in <strong>descending</strong> order, then by store name in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>stores table:</p>

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

<p>inventory table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+----------+----------------+-------------+------------------+--------------------+------------------+
| store_id | store_name     | location    | most_exp_product | cheapest_product   | imbalance_ratio  |
+----------+----------------+-------------+------------------+--------------------+------------------+
| 3        | City Center    | Los Angeles | Tablet           | Stylus             | 40.00            |
| 1        | Downtown Tech  | New York    | Laptop           | Mouse              | 10.00            |
| 2        | Suburb Mall    | Chicago     | Phone            | Case               | 25.00            |
+----------+----------------+-------------+------------------+--------------------+------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Downtown Tech (store_id = 1):</strong>

    <ul>
    	<li>Most expensive product: Laptop ($999.99) with quantity 5</li>
    	<li>Cheapest product: Mouse ($19.99) with quantity 50</li>
    	<li>Inventory imbalance: 5 &lt; 50 (expensive product has lower stock)</li>
    	<li>Imbalance ratio: 50 / 5 = 10.00</li>
    	<li>Has 4 products (&ge; 3), so qualifies</li>
    </ul>
    </li>
    <li><strong>Suburb Mall (store_id = 2):</strong>
    <ul>
    	<li>Most expensive product: Phone ($699.99) with quantity 3</li>
    	<li>Cheapest product: Case ($15.99) with quantity 75</li>
    	<li>Inventory imbalance: 3 &lt; 75 (expensive product has lower stock)</li>
    	<li>Imbalance ratio: 75 / 3 = 25.00</li>
    	<li>Has 4 products (&ge; 3), so qualifies</li>
    </ul>
    </li>
    <li><strong>City Center (store_id = 3):</strong>
    <ul>
    	<li>Most expensive product: Tablet ($499.99) with quantity 2</li>
    	<li>Cheapest product: Stylus ($29.99) with quantity 80</li>
    	<li>Inventory imbalance: 2 &lt; 80 (expensive product has lower stock)</li>
    	<li>Imbalance ratio: 80 / 2 = 40.00</li>
    	<li>Has 3 products (&ge; 3), so qualifies</li>
    </ul>
    </li>
    <li><strong>Stores not included:</strong>
    <ul>
    	<li>Corner Shop (store_id = 4): Only has 2 products (Watch, Band) - doesn&#39;t meet minimum 3 products requirement</li>
    	<li>Plaza Store (store_id = 5): Only has 2 products (Camera, Lens) - doesn&#39;t meet minimum 3 products requirement</li>
    </ul>
    </li>

</ul>

<p>The Results table is ordered by imbalance ratio in descending order, then by store name in ascending order</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Window Functions + Joins

We can use window functions to calculate the most expensive and cheapest products for each store, and use joins to filter out stores with inventory imbalance. The specific steps are as follows:

1. **Calculate the most expensive product for each store**: Use the `RANK()` window function to sort by price in descending order, and in case of the same price, sort by quantity in descending order, selecting the product ranked first.
2. **Calculate the cheapest product for each store**: Use the `RANK()` window function to sort by price in ascending order, and in case of the same price, sort by quantity in descending order, selecting the product ranked first.
3. **Filter stores with at least 3 different products**: Use the `COUNT()` window function to count the number of products for each store, and filter out stores with a count greater than or equal to 3.
4. **Join most expensive and cheapest products**: Join the results of the most expensive and cheapest products, ensuring that the quantity of the most expensive product is less than the quantity of the cheapest product.
5. **Calculate imbalance ratio**: Calculate the ratio of the cheapest product quantity to the most expensive product quantity, and round it to two decimal places.
6. **Join store information**: Join the results with the store information table to get store names and locations.
7. **Sort results**: Sort by imbalance ratio in descending order, then by store name in ascending order.

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
    # Step 1: Identify stores with at least 3 products
    store_counts = inventory["store_id"].value_counts()
    valid_stores = store_counts[store_counts >= 3].index

    # Step 2: Find most expensive product for each valid store
    # Sort by price (descending) then quantity (descending) and take first record per store
    most_expensive = (
        inventory[inventory["store_id"].isin(valid_stores)]
        .sort_values(["store_id", "price", "quantity"], ascending=[True, False, False])
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # Step 3: Find cheapest product for each store
    # Sort by price (ascending) then quantity (descending) and take first record per store
    cheapest = (
        inventory.sort_values(
            ["store_id", "price", "quantity"], ascending=[True, True, False]
        )
        .groupby("store_id")
        .first()
        .reset_index()
    )

    # Step 4: Merge the two datasets on store_id
    merged = pd.merge(
        most_expensive, cheapest, on="store_id", suffixes=("_most", "_cheap")
    )

    # Step 5: Filter for cases where cheapest product has higher quantity than most expensive
    result = merged[merged["quantity_most"] < merged["quantity_cheap"]].copy()

    # Step 6: Calculate imbalance ratio (cheapest quantity / most expensive quantity)
    result["imbalance_ratio"] = (
        result["quantity_cheap"] / result["quantity_most"]
    ).round(2)

    # Step 7: Merge with store information to get store names and locations
    result = pd.merge(result, stores, on="store_id")

    # Step 8: Select and rename columns for final output
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

    # Step 9: Sort by imbalance ratio (descending) then store name (ascending)
    result = result.sort_values(
        ["imbalance_ratio", "store_name"], ascending=[False, True]
    ).reset_index(drop=True)

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
