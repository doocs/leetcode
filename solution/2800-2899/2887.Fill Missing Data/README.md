---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2887.Fill%20Missing%20Data/README.md
---

<!-- problem:start -->

# [2887. 填充缺失值](https://leetcode.cn/problems/fill-missing-data)

[English Version](/solution/2800-2899/2887.Fill%20Missing%20Data/README_EN.md)

## 题目描述

<!-- description:start -->

<pre>
DataFrame <code>products</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| name        | object |
| quantity    | int    |
| price       | int    |
+-------------+--------+
</pre>

<p>编写一个解决方案，在 <code>quantity</code> 列中将缺失的值填充为&nbsp;<code><strong>0</strong></code>。</p>

<p>返回结果如下示例所示。</p>

<p>&nbsp;</p>
<strong class="example">示例 1：</strong>

<pre>
<strong>输入：
</strong>+-----------------+----------+-------+
| name            | quantity | price |
+-----------------+----------+-------+
| Wristwatch      | 32       | 135   |
| WirelessEarbuds | None     | 821   |
| GolfClubs       | None     | 9319  |
| Printer         | 849      | 3051  |
+-----------------+----------+-------+
<strong>输出：
</strong>+-----------------+----------+-------+
| name            | quantity | price |
+-----------------+----------+-------+
| Wristwatch      | 32       | 135   |
| WirelessEarbuds | 0        | 821   |
| GolfClubs       | 0        | 9319  |
| Printer         | 849      | 3051  |
+-----------------+----------+-------+
<b>解释：</b>
Toaster 和 Headphones 的数量被填充为 0。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
import pandas as pd


def fillMissingValues(products: pd.DataFrame) -> pd.DataFrame:
    products['quantity'] = products['quantity'].fillna(0)
    return products
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
