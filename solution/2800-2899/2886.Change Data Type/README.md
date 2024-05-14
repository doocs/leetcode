# [2886. 改变数据类型](https://leetcode.cn/problems/change-data-type)

[English Version](/solution/2800-2899/2886.Change%20Data%20Type/README_EN.md)

<!-- tags: -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<pre>
DataFrame <code>students</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| student_id  | int    |
| name        | object |
| age         | int    |
| grade       | float  |
+-------------+--------+
</pre>

<p>编写一个解决方案来纠正以下错误：</p>

<p>&nbsp;<code>grade</code>&nbsp;列被存储为浮点数，将它转换为整数。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：
</strong>DataFrame students:
+------------+------+-----+-------+
| student_id | name | age | grade |
+------------+------+-----+-------+
| 1          | Ava  | 6   | 73.0  |
| 2          | Kate | 15  | 87.0  |
+------------+------+-----+-------+
<strong>输出：
</strong>+------------+------+-----+-------+
| student_id | name | age | grade |
+------------+------+-----+-------+
| 1          | Ava  | 6   | 73    |
| 2          | Kate | 15  | 87    |
+------------+------+-----+-------+
<b>解释：</b>
grade 列的数据类型已转换为整数。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```python
import pandas as pd


def changeDatatype(students: pd.DataFrame) -> pd.DataFrame:
    students['grade'] = students['grade'].astype(int)
    return students
```

<!-- tabs:end -->

<!-- end -->
