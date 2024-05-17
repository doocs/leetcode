---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2987.Find%20Expensive%20Cities/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2987. 寻找房价最贵的城市 🔒](https://leetcode.cn/problems/find-expensive-cities)

[English Version](/solution/2900-2999/2987.Find%20Expensive%20Cities/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Listings</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| listing_id  | int     |
| city        | varchar |
| price       | int     |
+-------------+---------+
listing_id 是这张表具有唯一值的列。
这张表包括 listing_id, city,和 price。
</pre>

<p>编写一个解决方案，查找 <strong>房价平均值</strong> 超过 <strong>全国</strong> 平均房价的 <strong>城市</strong>。</p>

<p>返回 <em>按&nbsp;</em><code>city</code><em> </em><em><strong>升序</strong> 排序的结果表。</em></p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Listings table:
+------------+--------------+---------+
| listing_id | city         | price   | 
+------------+--------------+---------+
| 113        | LosAngeles   | 7560386 | 
| 136        | SanFrancisco | 2380268 |     
| 92         | Chicago      | 9833209 | 
| 60         | Chicago      | 5147582 | 
| 8          | Chicago      | 5274441 |  
| 79         | SanFrancisco | 8372065 | 
| 37         | Chicago      | 7939595 | 
| 53         | LosAngeles   | 4965123 | 
| 178        | SanFrancisco | 999207  | 
| 51         | NewYork      | 5951718 | 
| 121        | NewYork      | 2893760 | 
+------------+--------------+---------+
<b>输出</b>
+------------+
| city       | 
+------------+
| Chicago    | 
| LosAngeles |  
+------------+
<b>解释</b>
全国平均房价为 $6,122,059.45。在列出的城市中：
- Chicago 的平均价格为 $7,043,706.75
- Los Angeles 的平均价格为 $6,277,754.5
- San Francisco 的平均价格为 $3,900,513.33
- New York 的平均价格为 $4,422,739
只有 Chicago 和 Los Angeles 的平均房价超过了全国平均水平。因此，这两个城市包含在输出表中。输出表按城市名称升序排序。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组聚合 + 子查询

我们将 `Listings` 表按照 `city` 分组，然后计算每个城市的平均房价，最后筛选出平均房价大于全国平均房价的城市即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT city
FROM Listings
GROUP BY city
HAVING AVG(price) > (SELECT AVG(price) FROM Listings)
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
