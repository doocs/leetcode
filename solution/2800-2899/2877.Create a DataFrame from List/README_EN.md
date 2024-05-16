---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2877.Create%20a%20DataFrame%20from%20List/README_EN.md
---

<!-- problem:start -->

# [2877. Create a DataFrame from List](https://leetcode.com/problems/create-a-dataframe-from-list)

[中文文档](/solution/2800-2899/2877.Create%20a%20DataFrame%20from%20List/README.md)

## Description

<p>Write a solution to <strong>create</strong> a DataFrame from a 2D list called <code>student_data</code>. This 2D list contains the IDs and ages of some students.</p>

<p>The DataFrame should have two columns, <code>student_id</code> and <code>age</code>, and be in the same order as the original 2D list.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:
</strong>student_data:<strong>
</strong><code>[
  [1, 15],
  [2, 11],
  [3, 11],
  [4, 20]
]</code>
<strong>Output:</strong>
+------------+-----+
| student_id | age |
+------------+-----+
| 1          | 15  |
| 2          | 11  |
| 3          | 11  |
| 4          | 20  |
+------------+-----+
<strong>Explanation:</strong>
A DataFrame was created on top of student_data, with two columns named <code>student_id</code> and <code>age</code>.
</pre>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
import pandas as pd


def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    return pd.DataFrame(student_data, columns=['student_id', 'age'])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
