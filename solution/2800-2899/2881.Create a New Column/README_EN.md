---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2881.Create%20a%20New%20Column/README_EN.md
---

<!-- problem:start -->

# [2881. Create a New Column](https://leetcode.com/problems/create-a-new-column)

[中文文档](/solution/2800-2899/2881.Create%20a%20New%20Column/README.md)

## Description

<!-- description:start -->

<pre>
DataFrame <code>employees</code>
+-------------+--------+
| Column Name | Type.  |
+-------------+--------+
| name        | object |
| salary      | int.   |
+-------------+--------+
</pre>

<p>A&nbsp;company plans to provide its employees with a bonus.</p>

<p>Write a solution to create a new column name <code>bonus</code> that contains the <strong>doubled values</strong> of the <code>salary</code> column.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
DataFrame employees
+---------+--------+
| name    | salary |
+---------+--------+
| Piper   | 4548   |
| Grace   | 28150  |
| Georgia | 1103   |
| Willow  | 6593   |
| Finn    | 74576  |
| Thomas  | 24433  |
+---------+--------+
<strong>Output:</strong>
+---------+--------+--------+
| name    | salary | bonus  |
+---------+--------+--------+
| Piper   | 4548   | 9096   |
| Grace   | 28150  | 56300  |
| Georgia | 1103   | 2206   |
| Willow  | 6593   | 13186  |
| Finn    | 74576  | 149152 |
| Thomas  | 24433  | 48866  |
+---------+--------+--------+
<strong>Explanation:</strong> 
A new column bonus is created by doubling the value in the column salary.</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Calculation

We can directly calculate the double of `salary` and then store the result in the `bonus` column.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
import pandas as pd


def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    employees['bonus'] = employees['salary'] * 2
    return employees
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
