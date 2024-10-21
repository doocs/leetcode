---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3328.Find%20Cities%20in%20Each%20State%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3328. Find Cities in Each State II 🔒](https://leetcode.cn/problems/find-cities-in-each-state-ii)

[English Version](/solution/3300-3399/3328.Find%20Cities%20in%20Each%20State%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code>cities</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| state       | varchar |
| city        | varchar |
+-------------+---------+
(state, city) is the combination of columns with unique values for this table.
Each row of this table contains the state name and the city name within that state.
</pre>

<p>Write a solution to find <strong>all the cities</strong> in <strong>each state</strong> and analyze them based on the following requirements:</p>

<ul>
	<li>Combine all cities into a <strong>comma-separated</strong> string for each state.</li>
	<li>Only include states that have <strong>at least</strong> <code>3</code> cities.</li>
	<li>Only include states where <strong>at least one city</strong> starts with the <strong>same letter as the state name</strong>.</li>
</ul>

<p>Return <em>the result table ordered by</em> <em>the count of matching-letter cities in <strong>descending</strong> order</em>&nbsp;<em>and then by state name in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>cities table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+-------------------------------------------+-----------------------+
| state       | cities                                    | matching_letter_count |
+-------------+-------------------------------------------+-----------------------+
| Pennsylvania| Philadelphia, Pittsburgh, Pottstown       | 3                     |
| Texas       | Dallas, Taylor, Temple, Tyler             | 2                     |
| New York    | Buffalo, Newark, New York City, Rochester | 2                     |
+-------------+-------------------------------------------+-----------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>Pennsylvania</strong>:

    <ul>
    	<li>Has 3 cities (meets minimum requirement)</li>
    	<li>All 3 cities start with &#39;P&#39; (same as state)</li>
    	<li>matching_letter_count = 3</li>
    </ul>
    </li>
    <li><strong>Texas</strong>:
    <ul>
    	<li>Has 4 cities (meets minimum requirement)</li>
    	<li>2 cities (Temple, Tyler) start with &#39;T&#39; (same as state)</li>
    	<li>matching_letter_count = 2</li>
    </ul>
    </li>
    <li><strong>New York</strong>:
    <ul>
    	<li>Has 4 cities (meets minimum requirement)</li>
    	<li>2 cities (Newark, New York City) start with &#39;N&#39; (same as state)</li>
    	<li>matching_letter_count = 2</li>
    </ul>
    </li>
    <li><strong>California</strong> is not included in the output because:
    <ul>
    	<li>Although it has 4 cities (meets minimum requirement)</li>
    	<li>No cities start with &#39;C&#39; (doesn&#39;t meet the matching letter requirement)</li>
    </ul>
    </li>

</ul>

<p><strong>Note:</strong></p>

<ul>
	<li>Results are ordered by matching_letter_count in descending order</li>
	<li>When matching_letter_count is the same (Texas and New York both have 2), they are ordered by state name alphabetically</li>
	<li>Cities in each row are ordered alphabetically</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组聚合 + 过滤

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
