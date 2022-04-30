# [2253. Dynamic Unpivoting of a Table](https://leetcode.cn/problems/dynamic-unpivoting-of-a-table)

[English Version](/solution/2200-2299/2253.Dynamic%20Unpivoting%20of%20a%20Table/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Products</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| store_name<sub>1</sub> | int     |
| store_name<sub>2</sub> | int     |
|      :      | int     |
|      :      | int     |
|      :      | int     |
| store_name<sub>n</sub> | int     |
+-------------+---------+
product_id is the primary key for this table.
Each row in this table indicates the product&#39;s price in n different stores.
If the product is not available in a store, the price will be null in that store&#39;s column.
The names of the stores may change from one testcase to another. There will be at least 1 store and at most 30 stores.
</pre>

<p>&nbsp;</p>

<p><strong>Important note:</strong> This problem targets those who have a good experience with SQL. If you are a beginner, we recommend that you skip it for now.</p>

<p>Implement the procedure <code>UnpivotProducts</code> to reorganize the <code>Products</code> table so that each row has the id of one product, the name of a store where it is sold, and its price in that store. If a product is not available in a store, do <strong>not</strong> include a row with that <code>product_id</code> and <code>store</code> combination in the result table. There should be three columns: <code>product_id</code>, <code>store</code>, and <code>price</code>.</p>

<p>The procedure should return the table after reorganizing it.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Products table:
+------------+----------+--------+------+------+
| product_id | LC_Store | Nozama | Shop | Souq |
+------------+----------+--------+------+------+
| 1          | 100      | null   | 110  | null |
| 2          | null     | 200    | null | 190  |
| 3          | null     | null   | 1000 | 1900 |
+------------+----------+--------+------+------+
<strong>Output:</strong> 
+------------+----------+-------+
| product_id | store    | price |
+------------+----------+-------+
| 1          | LC_Store | 100   |
| 1          | Shop     | 110   |
| 2          | Nozama   | 200   |
| 2          | Souq     | 190   |
| 3          | Shop     | 1000  |
| 3          | Souq     | 1900  |
+------------+----------+-------+
<strong>Explanation:</strong> 
Product 1 is sold in LC_Store and Shop with prices of 100 and 110 respectively.
Product 2 is sold in Nozama and Souq with prices of 200 and 190.
Product 3 is sold in Shop and Souq with prices of 1000 and 1900.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
