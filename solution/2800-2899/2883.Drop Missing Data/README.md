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
</strong>+------------+-------+-----+
| student_id | name  | age |
+------------+-------+-----+
| 32         | Piper | 5   |
| 217        | Grace | 19  |
| 779        | None  | 20  |
| 849        | None  | 14  |
+------------+-------+-----+
<strong>输出：
</strong>+------------+-------+-----+
| student_id | name  | age |
+------------+-------+-----+
| 32         | Piper | 5   |
| 217        | Grace | 19  |
+------------+-------+-----+
<b>解释：
</b>学号为 779 和 849 的学生所在行在 name 列中有空值，因此它们将被删除。</pre>

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
