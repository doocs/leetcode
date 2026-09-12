---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3061. 计算滞留雨水 🔒](https://leetcode.cn/problems/calculate-trapping-rain-water)

[English Version](/solution/3000-3099/3061.Calculate%20Trapping%20Rain%20Water/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<font face="monospace">Heights</font></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| height      | int  |
+-------------+------+
id 是这张表的主键（值互不相同的列），并且保证有序。
这张表的每一行都包含 id 和 height。
</pre>

<p>编写一个解决方案来计算景观中 <strong>沙洲之间</strong> 可以滞留的雨水量，认为每个沙洲的 <strong>宽度</strong> 为 <code>1</code> 个单位。</p>

<p>以 <strong>任何</strong> 顺序返回结果表。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>输入:</strong> 
Heights table:
+-----+--------+
| id  | height |
+-----+--------+
| 1   | 0      |
| 2   | 1      |
| 3   | 0      |
| 4   | 2      |
| 5   | 1      |
| 6   | 0      |
| 7   | 1      |
| 8   | 3      |
| 9   | 2      |
| 10  | 1      |
| 11  | 2      |
| 12  | 1      |
+-----+--------+
<strong>输出:</strong> 
+---------------------+
| total_trapped_water | 
+---------------------+
| 6                   | 
+---------------------+
<strong>解释:</strong> 
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3061.Calculate%20Trapping%20Rain%20Water/images/1709609248-wtdiVm-image.png" style="width: 500px; height: 202px;" />

上面描绘的高度图(在黑色部分)以图形表示，x 轴表示 id，y 轴表示 heights [0,1,0,2,1,0,1,3,2,1,2,1]。在这个场景中，在蓝色部分滞留了 6 个单位的雨水。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数 + 求和

<!-- thinking:start -->

> **思考**
>
> 经典接雨水：每位水量为左右最高柱的较小者减去自身。行式数据可用窗口或累计最大代替双指针。
>
> 从左累计最大与从右累计最大分别给出 $l$、$r$，逐行取 $\min(l,r)-h$ 再求和。
>
> 用 $\texttt{cummax}$ 与反向 $\texttt{cummax}$ 实现两侧最高。

<!-- thinking:end -->

我们使用窗口函数 `MAX(height) OVER (ORDER BY id)` 来计算每个位置及其左边的最大高度，使用 `MAX(height) OVER (ORDER BY id DESC)` 来计算每个位置及其右边的最大高度，分别记为 `l` 和 `r`。那么每个位置上的蓄水量就是 `min(l, r) - height`，最后求和即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            MAX(height) OVER (ORDER BY id) AS l,
            MAX(height) OVER (ORDER BY id DESC) AS r
        FROM Heights
    )
SELECT SUM(LEAST(l, r) - height) AS total_trapped_water
FROM T;
```

#### Python3

```python
import pandas as pd


def calculate_trapped_rain_water(heights: pd.DataFrame) -> pd.DataFrame:
    heights["l"] = heights["height"].cummax()
    heights["r"] = heights["height"][::-1].cummax()[::-1]
    heights["trapped_water"] = heights[["l", "r"]].min(axis=1) - heights["height"]
    return pd.DataFrame({"total_trapped_water": [heights["trapped_water"].sum()]})
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
