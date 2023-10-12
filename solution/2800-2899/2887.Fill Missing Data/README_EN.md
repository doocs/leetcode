# [2887. Fill Missing Data](https://leetcode.com/problems/fill-missing-data)

[中文文档](/solution/2800-2899/2887.Fill%20Missing%20Data/README.md)

## Description

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

<p>Write a solution to fill in the missing value as <code><strong>0</strong></code> in the <code>quantity</code> column.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<pre>
<strong class="example">Example 1:</strong>
<strong>Input:</strong>+-----------------+----------+-------+
| name            | quantity | price |
+-----------------+----------+-------+
| Wristwatch      | 32       | 135   |
| WirelessEarbuds | None     | 821   |
| GolfClubs       | None     | 9319  |
| Printer         | 849      | 3051  |
+-----------------+----------+-------+
<strong>Output:
</strong>+-----------------+----------+-------+
| name            | quantity | price |
+-----------------+----------+-------+
| Wristwatch      | 32       | 135   |
| WirelessEarbuds | 0        | 821   |
| GolfClubs       | 0        | 9319  |
| Printer         | 849      | 3051  |
+-----------------+----------+-------+
<strong>Explanation:</strong> 
The quantity for Toaster and Headphones are filled by 0.</pre>

## Solutions

<!-- tabs:start -->

### **Pandas**

```python
import pandas as pd


def fillMissingValues(products: pd.DataFrame) -> pd.DataFrame:
    products['quantity'] = products['quantity'].fillna(0)
    return products
```

### **...**

```

```

<!-- tabs:end -->
