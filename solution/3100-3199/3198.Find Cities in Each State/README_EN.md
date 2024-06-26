---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3198.Find%20Cities%20in%20Each%20State/README_EN.md
---

<!-- problem:start -->

# [3198. Find Cities in Each State 🔒](https://leetcode.com/problems/find-cities-in-each-state)

[中文文档](/solution/3100-3199/3198.Find%20Cities%20in%20Each%20State/README.md)

## Description

<!-- description:start -->

<p>Table: <code>cities</code></p>

<pre>
+-------------+---------+
| Column Name | Type    | 
+-------------+---------+
| state       | varchar |
| city        | varchar |
+-------------+---------+
(state, city) is the primary key (combination of columns with unique values) for this table.
Each row of this table contains the state name and the city name within that state.
</pre>

<p>Write a solution to find <strong>all the cities in each state</strong> and combine them into a <strong>single comma-separated</strong> string.</p>

<p>Return <em>the result table ordered by</em> <code>state</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>cities table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+-------------+---------------------------------------+
| state       | cities                                |
+-------------+---------------------------------------+
| California  | Los Angeles, San Diego, San Francisco |
| New York    | Buffalo, New York City, Rochester     |
| Texas       | Austin, Dallas, Houston               |
+-------------+---------------------------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>California:</strong> All cities (&quot;Los Angeles&quot;, &quot;San Diego&quot;, &quot;San Francisco&quot;) are listed in a comma-separated string.</li>
	<li><strong>New York:</strong> All cities (&quot;Buffalo&quot;, &quot;New York City&quot;, &quot;Rochester&quot;) are listed in a comma-separated string.</li>
	<li><strong>Texas:</strong> All cities (&quot;Austin&quot;, &quot;Dallas&quot;, &quot;Houston&quot;) are listed in a comma-separated string.</li>
</ul>

<p><strong>Note:</strong> The output table is ordered by the state name in ascending order.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping and Aggregation

We can first group by the `state` field, then sort the `city` field within each group, and finally use the `GROUP_CONCAT` function to concatenate the sorted city names into a comma-separated string.

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
