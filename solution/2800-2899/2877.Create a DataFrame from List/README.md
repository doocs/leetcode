---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2877.Create%20a%20DataFrame%20from%20List/README.md
tags:
    - Pandas
---

<!-- problem:start -->

# [2877. 从表中创建 DataFrame](https://leetcode.cn/problems/create-a-dataframe-from-list)

[English Version](/solution/2800-2899/2877.Create%20a%20DataFrame%20from%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>编写一个解决方案，基于名为&nbsp;&nbsp;<code>student_data</code>&nbsp;的二维列表&nbsp;<b>创建 </b>一个 DataFrame 。这个二维列表包含一些学生的 ID 和年龄信息。</p>

<p>DataFrame 应该有两列，&nbsp;<code>student_id</code>&nbsp;和&nbsp;<code>age</code>，并且与原始二维列表的顺序相同。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：
</strong>student_data:<strong>
</strong><code>[
  [1, 15],
  [2, 11],
  [3, 11],
  [4, 20]
]</code>
<b>输出：</b>
+------------+-----+
| student_id | age |
+------------+-----+
| 1          | 15  |
| 2          | 11  |
| 3          | 11  |
| 4          | 20  |
+------------+-----+
<b>解释：</b>
基于 student_data 创建了一个 DataFrame，包含 student_id 和 age 两列。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    return pd.DataFrame(student_data, columns=['student_id', 'age'])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
