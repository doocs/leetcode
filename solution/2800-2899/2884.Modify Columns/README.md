---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2884.Modify%20Columns/README.md
---

<!-- problem:start -->

# [2884. 修改列](https://leetcode.cn/problems/modify-columns)

[English Version](/solution/2800-2899/2884.Modify%20Columns/README_EN.md)

## 题目描述

<!-- description:start -->

<pre>
DataFrame <code>employees</code>
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| name        | object |
| salary      | int    |
+-------------+--------+
</pre>

<p>一家公司决定增加员工的薪水。</p>

<p>编写一个解决方案，将每个员工的薪水乘以2来 <strong>修改</strong>&nbsp;<code>salary</code>&nbsp;列。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<strong>输入：
</strong>DataFrame employees
+---------+--------+
| name    | salary |
+---------+--------+
| Jack    | 19666  |
| Piper   | 74754  |
| Mia     | 62509  |
| Ulysses | 54866  |
+---------+--------+
<strong>输出：
</strong>+---------+--------+
| name    | salary |
+---------+--------+
| Jack    | 39332  |
| Piper   | 149508 |
| Mia     | 125018 |
| Ulysses | 109732 |
+---------+--------+
<strong>解释：
</strong>每个人的薪水都被加倍。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
import pandas as pd


def modifySalaryColumn(employees: pd.DataFrame) -> pd.DataFrame:
    employees['salary'] *= 2
    return employees
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
