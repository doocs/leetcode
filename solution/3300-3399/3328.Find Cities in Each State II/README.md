---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3328.Find%20Cities%20in%20Each%20State%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3328. 查找每个州的城市 II 🔒](https://leetcode.cn/problems/find-cities-in-each-state-ii)

[English Version](/solution/3300-3399/3328.Find%20Cities%20in%20Each%20State%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>cities</code></p>

<pre>
+-------------+---------+
| Column Name | Type    | 
+-------------+---------+
| state       | varchar |
| city        | varchar |
+-------------+---------+
(state, city) 是这张表中值互不相同的列的组合。
这张表的每一行包含州名和其中的城市名。
</pre>

<p>编写一个解决方案来找到 <strong>每个州</strong>&nbsp;中的 <strong>所有城市</strong>&nbsp;并且根据下列条件分析它们：</p>

<ul>
	<li>用 <b>逗号分隔</b>&nbsp;字符串组合每一个州的所有城市。</li>
	<li>只显示有 <strong>至少</strong>&nbsp;<code>3</code>&nbsp;个城市的州。</li>
	<li>只显示&nbsp;<strong>至少有一个城市</strong>&nbsp;以与 <strong>州名相同字母开头</strong>&nbsp;的州。</li>
</ul>

<p>返回结果表以字母匹配城市的数量 <strong>降序</strong> 排序，然后按州名称 <strong>升序</strong> 排序的结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>cities 表：</p>

<pre class="example-io">
+--------------+---------------+
| state        | city          |
+--------------+---------------+
| New York     | New York City |
| New York     | Newark        |
| New York     | Buffalo       |
| New York     | Rochester     |
| California   | San Francisco |
| California   | Sacramento    |
| California   | San Diego     |
| California   | Los Angeles   |
| Texas        | Tyler         |
| Texas        | Temple        |
| Texas        | Taylor        |
| Texas        | Dallas        |
| Pennsylvania | Philadelphia  |
| Pennsylvania | Pittsburgh    |
| Pennsylvania | Pottstown     |
+--------------+---------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+-------------------------------------------+-----------------------+
| state       | cities                                    | matching_letter_count |
+-------------+-------------------------------------------+-----------------------+
| Pennsylvania| Philadelphia, Pittsburgh, Pottstown       | 3                     |
| Texas       | Dallas, Taylor, Temple, Tyler             | 3                     |
| New York    | Buffalo, Newark, New York City, Rochester | 2                     |
+-------------+-------------------------------------------+-----------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>Pennsylvania</strong>:

    <ul>
    	<li>有 3 个城市（符合最低条件）</li>
    	<li>所有的 3 个城市都以 'P' 开头（与州相同）</li>
    	<li>matching_letter_count = 3</li>
    </ul>
    </li>
    <li><strong>Texas</strong>:
    <ul>
    	<li>有 4 个城市（符合最低条件）</li>
    	<li>3 个城市 (Taylor, Temple, Tyler) 以 'T' 开头（与州相同）</li>
    	<li>matching_letter_count = 3</li>
    </ul>
    </li>
    <li><strong>New York</strong>:
    <ul>
    	<li>有 4 个城市（符合最低条件）</li>
    	<li>2 个城市 (Newark, New York City) 以 'N' 开头（与州相同）</li>
    	<li>matching_letter_count = 2</li>
    </ul>
    </li>
    <li><strong>California</strong> 没有包含在输出表，因为：
    <ul>
    	<li>尽管它有 4 个城市（符合最低条件）</li>
    	<li>没有城市以 'C' 开头（不符合字母匹配条件）</li>
    </ul>
    </li>

</ul>

<p><strong>注意：</strong></p>

<ul>
	<li>结果以 matching_letter_count 降序排序。</li>
	<li>当 matching_letter_count 持平（Texas 和 New York 都为 2），按州名字母序排序。</li>
	<li>每一行的城市也以字母序排序。</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组聚合 + 过滤

<!-- thinking:start -->

> **思考**
>
> 需要按州汇总城市名，并统计「城市名首字母与州名相同」的个数，再筛出城市数不少于 $3$ 且匹配数大于 $0$ 的州。
>
> 这些指标都是分组聚合量：排序拼接、布尔求和、计数。先算匹配标记再 $\textit{groupby}$ 即可一次得到。
>
> 过滤后按匹配数降序、州名升序排列，并去掉仅用于筛选的城市计数字段。

<!-- thinking:end -->

我们可以将 `cities` 表按照 `state` 字段进行分组聚合，然后对每个分组进行过滤，筛选出满足条件的分组。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    state,
    GROUP_CONCAT(city ORDER BY city SEPARATOR ', ') AS cities,
    COUNT(
        CASE
            WHEN LEFT(city, 1) = LEFT(state, 1) THEN 1
        END
    ) AS matching_letter_count
FROM cities
GROUP BY 1
HAVING COUNT(city) >= 3 AND matching_letter_count > 0
ORDER BY 3 DESC, 1;
```

#### Pandas

```python
import pandas as pd


def state_city_analysis(cities: pd.DataFrame) -> pd.DataFrame:
    cities["matching_letter"] = cities["city"].str[0] == cities["state"].str[0]

    result = (
        cities.groupby("state")
        .agg(
            cities=("city", lambda x: ", ".join(sorted(x))),
            matching_letter_count=("matching_letter", "sum"),
            city_count=("city", "count"),
        )
        .reset_index()
    )

    result = result[(result["city_count"] >= 3) & (result["matching_letter_count"] > 0)]

    result = result.sort_values(
        by=["matching_letter_count", "state"], ascending=[False, True]
    )

    result = result.drop(columns=["city_count"])

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
