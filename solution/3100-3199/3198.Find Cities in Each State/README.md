---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3198.Find%20Cities%20in%20Each%20State/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3198. 查找每个州的城市 🔒](https://leetcode.cn/problems/find-cities-in-each-state)

[English Version](/solution/3100-3199/3198.Find%20Cities%20in%20Each%20State/README_EN.md)

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
(state, city) 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含州名和其中的城市名。
</pre>

<p>编写一个解决方案来 <strong>查找每个州的所有城市</strong>，并将它们组合成 <strong>一个逗号分隔</strong> 的字符串。</p>

<p>返回结果表以&nbsp;<code>state</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>cities 表：</p>

<pre class="example-io">
+-------------+---------------+
| state       | city          |
+-------------+---------------+
| California  | Los Angeles   |
| California  | San Francisco |
| California  | San Diego     |
| Texas       | Houston       |
| Texas       | Austin        |
| Texas       | Dallas        |
| New York    | New York City |
| New York    | Buffalo       |
| New York    | Rochester     |
+-------------+---------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-------------+---------------------------------------+
| state       | cities                                |
+-------------+---------------------------------------+
| California  | Los Angeles, San Diego, San Francisco |
| New York    | Buffalo, New York City, Rochester     |
| Texas       | Austin, Dallas, Houston               |
+-------------+---------------------------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>California：</strong>所有城市 ("Los Angeles", "San Diego", "San Francisco") 以逗号分隔的字符串列出。</li>
	<li><strong>New York：</strong>所有城市 ("Buffalo", "New York City", "Rochester") 以逗号分隔的字符串列出。</li>
	<li><strong>Texas：</strong>所有城市 ("Austin", "Dallas", "Houston") 以逗号分隔的字符串列出。</li>
</ul>

<p><b>注意：</b>输出表以州名升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组聚合

我们可以先按照 `state` 字段进行分组，然后对每个分组内的 `city` 字段进行排序，最后使用 `GROUP_CONCAT` 函数将排序后的城市名连接成一个逗号分隔的字符串。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    state,
    GROUP_CONCAT(city ORDER BY city SEPARATOR ', ') cities
FROM cities
GROUP BY 1
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_cities(cities: pd.DataFrame) -> pd.DataFrame:
    result = (
        cities.groupby("state")["city"]
        .apply(lambda x: ", ".join(sorted(x)))
        .reset_index()
    )
    result.columns = ["state", "cities"]
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
