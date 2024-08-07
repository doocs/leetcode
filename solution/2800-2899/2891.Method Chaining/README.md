---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2891.Method%20Chaining/README.md
tags:
    - Pandas
---

<!-- problem:start -->

# [2891. 方法链](https://leetcode.cn/problems/method-chaining)

[English Version](/solution/2800-2899/2891.Method%20Chaining/README_EN.md)

## 题目描述

<!-- description:start -->

<pre>
DataFrame <code>animals</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| name        | object |
| species     | object |
| age         | int    |
| weight      | int    |
+-------------+--------+
</pre>

<p>编写一个解决方案来列出体重 <strong>严格超过&nbsp;</strong>&nbsp;<code>100</code>&nbsp; 千克的动物的名称。</p>

<p>按体重 <strong>降序</strong> 返回动物。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
DataFrame animals:
+----------+---------+-----+--------+
| name     | species | age | weight |
+----------+---------+-----+--------+
| Tatiana  | Snake   | 98  | 464    |
| Khaled   | Giraffe | 50  | 41     |
| Alex     | Leopard | 6   | 328    |
| Jonathan | Monkey  | 45  | 463    |
| Stefan   | Bear    | 100 | 50     |
| Tommy    | Panda   | 26  | 349    |
+----------+---------+-----+--------+
<b>输出：</b>
+----------+
| name     |
+----------+
| Tatiana  |
| Jonathan |
| Tommy    |
| Alex     |
+----------+
<b>解释：</b>
所有体重超过 100 的动物都应包含在结果表中。
Tatiana 的体重为 464，Jonathan 的体重为 463，Tommy 的体重为 349，Alex 的体重为 328。
结果应按体重降序排序。</pre>

<p>&nbsp;</p>

<p>在 Pandas 中，<strong>方法链</strong> 允许我们在 DataFrame 上执行操作，而无需将每个操作拆分成单独的行或创建多个临时变量。</p>

<p>你能用 <strong>一行</strong> 代码的方法链完成这个任务吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def findHeavyAnimals(animals: pd.DataFrame) -> pd.DataFrame:
    return animals[animals['weight'] > 100].sort_values('weight', ascending=False)[
        ['name']
    ]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
