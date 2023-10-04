# [2883. Drop Missing Data](https://leetcode.cn/problems/drop-missing-data)

[English Version](/solution/2800-2899/2883.Drop%20Missing%20Data/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<pre>
DataFrame students
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| student_id  | int    |
| name        | object |
| age         | int    |
+-------------+--------+
</pre>

<p>There are some rows having missing values in the <code>name</code> column.</p>

<p>Write a solution to remove the rows with missing values.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:
</strong>+------------+-------+-----+
| student_id | name  | age |
+------------+-------+-----+
| 32         | Piper | 5   |
| 217        | Grace | 19  |
| 779        | None  | 20  |
| 849        | None  | 14  |
+------------+-------+-----+
<strong>Output:
</strong>+------------+-------+-----+
| student_id | name  | age |
+------------+-------+-----+
| 32         | Piper | 5   |
| 217        | Grace | 19  |
+------------+-------+-----+
<strong>Explanation:</strong> 
Students with ids 779 and 849 have empty values in the name column, so they will be removed.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Pandas**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import pandas as pd


def dropMissingData(students: pd.DataFrame) -> pd.DataFrame:
    return students[students['name'].notnull()]
```

### **...**

```

```

<!-- tabs:end -->
