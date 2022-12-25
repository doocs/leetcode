# [2253. 动态取消表的旋转](https://leetcode.cn/problems/dynamic-unpivoting-of-a-table)

[English Version](/solution/2200-2299/2253.Dynamic%20Unpivoting%20of%20a%20Table/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Products</code></p>

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
product_id 是该表的主键。
该表中的每一行都表示该商品在 n 个不同商店中的价格。
如果商店中没有该商品，则该商店列中的价格将为 null。
不同测试用例的商店名称可能会不同。至少有1家店，最多30家店。
</pre>

<p>&nbsp;</p>

<p><strong>重要提示:</strong> 这个问题针对的是那些对 SQL 有丰富经验的人。如果你是初学者，我们建议你现在跳过它。</p>

<p>实现 <code>UnpivotProducts</code> 过程来重新组织 <code>Products</code> 表，使每一行都有一个产品的 id、销售该商品的商店名称以及该商品在该商店中的价格。如果某个商品在某个商店中不可用，则不要在结果表中包含该 <code>product_id</code> 和 <code>store</code> 组合的行。结果应该有三列:<code>product_id</code>、<code>store</code> 和 <code>price</code>。</p>

<p>过程应该在重新组织表之后返回它。</p>

<p data-group="1-1">以 <strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Products 表:
+------------+----------+--------+------+------+
| product_id | LC_Store | Nozama | Shop | Souq |
+------------+----------+--------+------+------+
| 1          | 100      | null   | 110  | null |
| 2          | null     | 200    | null | 190  |
| 3          | null     | null   | 1000 | 1900 |
+------------+----------+--------+------+------+
<strong>输出:</strong> 
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
<strong>解释:</strong> 
商品 1 在 LC_Store 和 Shop 销售，价格分别为 100 和 110。
商品 2 在 Nozama 和 Souq 销售，价格分别为 200 和 190。
商品 3 在 Shop 和 Souq 出售，价格分别为 1000 和 1900。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
