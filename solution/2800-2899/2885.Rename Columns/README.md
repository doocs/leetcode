---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2885.Rename%20Columns/README.md
tags:
    - Pandas
---

<!-- problem:start -->

# [2885. 重命名列](https://leetcode.cn/problems/rename-columns)

[English Version](/solution/2800-2899/2885.Rename%20Columns/README_EN.md)

## 题目描述

<!-- description:start -->

<pre>
DataFrame <code>students</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| id          | int    |
| first       | object |
| last        | object |
| age         | int    |
+-------------+--------+
</pre>

<p>编写一个解决方案，按以下方式重命名列：</p>

<ul>
	<li><code>id</code>&nbsp;重命名为&nbsp;<code>student_id</code></li>
	<li><code>first</code>&nbsp;重命名为&nbsp;<code>first_name</code></li>
	<li><code>last</code>&nbsp;重命名为&nbsp;<code>last_name</code></li>
	<li><code>age</code>&nbsp;重命名为&nbsp;<code>age_in_years</code></li>
</ul>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：
</strong>+----+---------+----------+-----+
| id | first   | last     | age |
+----+---------+----------+-----+
| 1  | Mason   | King     | 6   |
| 2  | Ava     | Wright   | 7   |
| 3  | Taylor  | Hall     | 16  |
| 4  | Georgia | Thompson | 18  |
| 5  | Thomas  | Moore    | 10  |
+----+---------+----------+-----+
<b>输出：</b>
+------------+------------+-----------+--------------+
| student_id | first_name | last_name | age_in_years |
+------------+------------+-----------+--------------+
| 1          | Mason      | King      | 6            |
| 2          | Ava        | Wright    | 7            |
| 3          | Taylor     | Hall      | 16           |
| 4          | Georgia    | Thompson  | 18           |
| 5          | Thomas     | Moore     | 10           |
+------------+------------+-----------+--------------+
<b>解释：</b>
列名已相应更换。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def renameColumns(students: pd.DataFrame) -> pd.DataFrame:
    students.rename(
        columns={
            'id': 'student_id',
            'first': 'first_name',
            'last': 'last_name',
            'age': 'age_in_years',
        },
        inplace=True,
    )
    return students
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
