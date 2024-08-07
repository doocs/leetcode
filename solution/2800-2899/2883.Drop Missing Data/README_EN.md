---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2883.Drop%20Missing%20Data/README_EN.md
tags:
    - Pandas
---

<!-- problem:start -->

# [2883. Drop Missing Data](https://leetcode.com/problems/drop-missing-data)

[中文文档](/solution/2800-2899/2883.Drop%20Missing%20Data/README.md)

## Description

<!-- description:start -->

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
</strong>+------------+---------+-----+
| student_id | name    | age |
+------------+---------+-----+
| 32         | Piper   | 5   |
| 217        | None    | 19  |
| 779        | Georgia | 20  |
| 849        | Willow  | 14  |
+------------+---------+-----+
<strong>Output:
</strong>+------------+---------+-----+
| student_id | name    | age |
+------------+---------+-----+
| 32         | Piper   | 5   |
| 779        | Georgia | 20  | 
| 849        | Willow  | 14  | 
+------------+---------+-----+
<strong>Explanation:</strong> 
Student with id 217 havs empty value in the name column, so it will be removed.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def dropMissingData(students: pd.DataFrame) -> pd.DataFrame:
    return students[students['name'].notnull()]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
