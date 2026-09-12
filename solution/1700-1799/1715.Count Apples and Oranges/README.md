---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [1715. 苹果和橘子的个数 🔒](https://leetcode.cn/problems/count-apples-and-oranges)

[English Version](/solution/1700-1799/1715.Count%20Apples%20and%20Oranges/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Boxes</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| box_id       | int  |
| chest_id     | int  |
| apple_count  | int  |
| orange_count | int  |
+--------------+------+
box_id 是该表的主键。
chest_id 是 chests 表的外键。
该表包含大箱子 (box) 中包含的苹果和橘子的个数。每个大箱子中可能包含一个小盒子 (chest) ，小盒子中也包含若干苹果和橘子。</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Chests</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| chest_id     | int  |
| apple_count  | int  |
| orange_count | int  |
+--------------+------+
chest_id 是该表的主键。
该表包含小盒子的信息，以及小盒子中包含的苹果和橘子的个数。</pre>

<p>&nbsp;</p>

<p>编写 SQL 语句，查询每个大箱子中苹果和橘子的个数。如果大箱子中包含小盒子，还应当包含小盒子中苹果和橘子的个数。</p>

<p>以任意顺序返回结果表。</p>

<p>查询结果的格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<strong>输入：</strong>
Boxes 表：
+--------+----------+-------------+--------------+
| box_id | chest_id | apple_count | orange_count |
+--------+----------+-------------+--------------+
| 2      | null     | 6           | 15           |
| 18     | 14       | 4           | 15           |
| 19     | 3        | 8           | 4            |
| 12     | 2        | 19          | 20           |
| 20     | 6        | 12          | 9            |
| 8      | 6        | 9           | 9            |
| 3      | 14       | 16          | 7            |
+--------+----------+-------------+--------------+
Chests 表：
+----------+-------------+--------------+
| chest_id | apple_count | orange_count |
+----------+-------------+--------------+
| 6        | 5           | 6            |
| 14       | 20          | 10           |
| 2        | 8           | 8            |
| 3        | 19          | 4            |
| 16       | 19          | 19           |
+----------+-------------+--------------+
<strong>输出：</strong>
+-------------+--------------+
| apple_count | orange_count |
+-------------+--------------+
| 151         | 123          |
+-------------+--------------+
<strong>解释：</strong>
大箱子 2 中有 6 个苹果和 15 个橘子。
大箱子 18 中有 4 + 20 (在小盒子中) = 24 个苹果和 15 + 10 (在小盒子中) = 25 个橘子。
大箱子 19 中有 8 + 19 (在小盒子中) = 27 个苹果和 4 + 4 (在小盒子中) = 8 个橘子。
大箱子 12 中有 19 + 8 (在小盒子中) = 27 个苹果和 20 + 8 (在小盒子中) = 28 个橘子。
大箱子 20 中有 12 + 5 (在小盒子中) = 17 个苹果和 9 + 6 (在小盒子中) = 15 个橘子。
大箱子 8 中有 9 + 5 (在小盒子中) = 14 个苹果和 9 + 6 (在小盒子中) = 15 个橘子。
大箱子 3 中有 16 + 20 (在小盒子中) = 36 个苹果和 7 + 10 (在小盒子中) = 17 个橘子。
苹果的总个数 = 6 + 24 + 27 + 27 + 17 + 14 + 36 = 151
橘子的总个数 = 15 + 25 + 8 + 28 + 15 + 15 + 17 = 123</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：左连接 + 求和

<!-- thinking:start -->

> **思考**
>
> 每个箱子可能装有一个盒子，苹果与橘子来自箱子本身及其盒子。盒子可缺省，缺省时应视为零。
>
> 将 $\textit{Boxes}$ 按 $\textit{chest\_id}$ 左连 $\textit{Chests}$，空值填 $0$ 后分别对苹果、橘子列求和，得到两列一行的结果。

<!-- thinking:end -->

我们可以将 `Boxes` 表和 `Chests` 表按照 `chest_id` 进行左连接，然后分别求出苹果和橘子的总个数。注意，如果某个箱子中没有小盒子，那么对应的 `chest_id` 为 `null`，此时我们需要认为该箱子中的小盒子中苹果和橘子的个数为 0。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    SUM(IFNULL(b.apple_count, 0) + IFNULL(c.apple_count, 0)) AS apple_count,
    SUM(IFNULL(b.orange_count, 0) + IFNULL(c.orange_count, 0)) AS orange_count
FROM
    Boxes AS b
    LEFT JOIN Chests AS c USING (chest_id);
```

#### Pandas

```python
import pandas as pd


def count_apples_and_oranges(boxes: pd.DataFrame, chests: pd.DataFrame) -> pd.DataFrame:
    merged_df = boxes.merge(
        chests, on="chest_id", how="left", suffixes=("_box", "_chest")
    )
    apple_count = (
        merged_df["apple_count_box"].fillna(0)
        + merged_df["apple_count_chest"].fillna(0)
    ).sum()
    orange_count = (
        merged_df["orange_count_box"].fillna(0)
        + merged_df["orange_count_chest"].fillna(0)
    ).sum()
    return pd.DataFrame({"apple_count": [apple_count], "orange_count": [orange_count]})
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
