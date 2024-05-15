---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2883.Drop%20Missing%20Data/README.md
---

# [2883. 删去丢失的数据](https://leetcode.cn/problems/drop-missing-data)

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

<p>在 <code>name</code> 列里有一些具有缺失值的行。</p>

<p>编写一个解决方案，删除具有缺失值的行。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<strong>输入：
</strong>+------------+---------+-----+
| student_id | name    | age |
+------------+---------+-----+
| 32         | Piper   | 5   |
| 217        | None    | 19  |
| 779        | Georgia | 20  |
| 849        | Willow  | 14  |
+------------+---------+-----+
<strong>输出：
</strong>+------------+---------+-----+
| student_id | name    | age |
+------------+---------+-----+
| 32         | Piper   | 5   |
| 779        | Georgia | 20  | 
| 849        | Willow  | 14  | 
+------------+---------+-----+
<b>解释：
</b>学号为 217 的学生所在行在 name 列中有空值，因此这一行将被删除。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```python
import pandas as pd


def dropMissingData(students: pd.DataFrame) -> pd.DataFrame:
    return students[students['name'].notnull()]
```

<!-- tabs:end -->

<!-- end -->
