---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3050.Pizza%20Toppings%20Cost%20Analysis/README.md
tags:
    - 数据库
---

# [3050. 披萨配料成本分析 🔒](https://leetcode.cn/problems/pizza-toppings-cost-analysis)

[English Version](/solution/3000-3099/3050.Pizza%20Toppings%20Cost%20Analysis/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code><font face="monospace">Toppings</font></code></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| topping_name | varchar | 
| cost         | decimal |
+--------------+---------+
topping_name 是这张表的主键。
这张表的每一行包含配料名和配料的花费。
</pre>

<p>编写一个解决方案根据给定的配料表来计算 <strong>所有可能的&nbsp;<code>3</code>&nbsp;种配料&nbsp;</strong>披萨组合的 <strong>总花费</strong>。总花费必须 <strong>舍入</strong> 到 <code>2</code> 位 <strong>整数</strong>。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><strong>不要</strong>&nbsp;包含 <strong>重复</strong> 配料的披萨。例如，‘Pepperoni, Pepperoni, Onion Pizza’。</li>
	<li>配料 <strong>必须</strong> 以 <strong>字母顺序</strong> 排序。例如，'Chicken, Onions, Sausage'。'Onion, Sausage, Chicken' 不会被通过。</li>
</ul>

<p>返回结果表，以总花费 <strong>降序</strong> 排序，配料的组合 <strong>升序</strong> 排序。</p>

<p>返回格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Toppings 表：
+--------------+------+
| topping_name | cost |
+--------------+------+
| Pepperoni    | 0.50 |
| Sausage      | 0.70 |
| Chicken      | 0.55 |
| Extra Cheese | 0.40 |
+--------------+------+
<strong>输出：</strong> 
+--------------------------------+------------+
| pizza                          | total_cost | 
+--------------------------------+------------+
| Chicken,Pepperoni,Sausage      | 1.75       |  
| Chicken,Extra Cheese,Sausage   | 1.65       |
| Extra Cheese,Pepperoni,Sausage | 1.60       |
| Chicken,Extra Cheese,Pepperoni | 1.45       | 
+--------------------------------+------------+
<strong>解释：</strong> 
这三种配料只有四种不同的组合：
- Chicken, Pepperoni, Sausage：总花费是 $1.75 (Chicken $0.55，Pepperoni $0.50，Sausage $0.70)。
- Chicken, Extra Cheese, Sausage：总花费是 $1.65 (Chicken $0.55，Extra Cheese $0.40，Sausage $0.70)。
- Extra Cheese, Pepperoni, Sausage：总花费是 $1.60 (Extra Cheese $0.40，Pepperoni $0.50，Sausage $0.70)。
- Chicken, Extra Cheese, Pepperoni：总花费是 $1.45 (Chicken $0.55，Extra Cheese $0.40，Pepperoni $0.50)。
输出表根据总花费降序排序。</pre>

## 解法

### 方法一：窗口函数 + 条件连接

我们先使用窗口函数，按照 `topping_name` 字段对表进行排序，并为每一行添加一个 `rk` 字段，表示当前行的排名。

然后我们使用条件连接，连接三次表 `T`，分别为 `t1`, `t2`, `t3`。连接条件是 `t1.rk < t2.rk` 和 `t2.rk < t3.rk`。然后我们计算三个配料的总价，按照总价降序排序，再按照配料名升序排序。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT *, RANK() OVER (ORDER BY topping_name) AS rk
        FROM Toppings
    )
SELECT
    CONCAT(t1.topping_name, ',', t2.topping_name, ',', t3.topping_name) AS pizza,
    t1.cost + t2.cost + t3.cost AS total_cost
FROM
    T AS t1
    JOIN T AS t2 ON t1.rk < t2.rk
    JOIN T AS t3 ON t2.rk < t3.rk
ORDER BY 2 DESC, 1 ASC;
```

<!-- tabs:end -->

<!-- end -->
