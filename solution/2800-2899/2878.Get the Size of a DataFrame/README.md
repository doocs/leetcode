---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2878.Get%20the%20Size%20of%20a%20DataFrame/README.md
---

<!-- problem:start -->

# [2878. 获取 DataFrame 的大小](https://leetcode.cn/problems/get-the-size-of-a-dataframe)

[English Version](/solution/2800-2899/2878.Get%20the%20Size%20of%20a%20DataFrame/README_EN.md)

## 题目描述

<!-- description:start -->

<pre>
DataFrame <code>players:</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| player_id   | int    |
| name        | object |
| age         | int    |
| position    | object |
| ...         | ...    |
+-------------+--------+
</pre>

<p>编写一个解决方案，计算并显示&nbsp;<code>players</code>&nbsp;的&nbsp;<strong>行数和列数</strong>。</p>

<p>将结果返回为一个数组：</p>

<p><code>[number of rows, number of columns]</code></p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：
</strong>+-----------+----------+-----+-------------+--------------------+
| player_id | name     | age | position    | team               |
+-----------+----------+-----+-------------+--------------------+
| 846       | Mason    | 21  | Forward     | RealMadrid         |
| 749       | Riley    | 30  | Winger      | Barcelona          |
| 155       | Bob      | 28  | Striker     | ManchesterUnited   |
| 583       | Isabella | 32  | Goalkeeper  | Liverpool          |
| 388       | Zachary  | 24  | Midfielder  | BayernMunich       |
| 883       | Ava      | 23  | Defender    | Chelsea            |
| 355       | Violet   | 18  | Striker     | Juventus           |
| 247       | Thomas   | 27  | Striker     | ParisSaint-Germain |
| 761       | Jack     | 33  | Midfielder  | ManchesterCity     |
| 642       | Charlie  | 36  | Center-back | Arsenal            |
+-----------+----------+-----+-------------+--------------------+<strong>
输出：
</strong>[10, 5]
<b>解释：</b>
这个 DataFrame 包含 10 行和 5 列。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def getDataframeSize(players: pd.DataFrame) -> List[int]:
    return list(players.shape)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
