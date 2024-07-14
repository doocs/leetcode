---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2881.Create%20a%20New%20Column/README.md
---

<!-- problem:start -->

# [2881. 创建新列](https://leetcode.cn/problems/create-a-new-column)

[English Version](/solution/2800-2899/2881.Create%20a%20New%20Column/README_EN.md)

## 题目描述

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

<p>一家公司计划为员工提供奖金。</p>

<p>编写一个解决方案，创建一个名为&nbsp;<code>bonus</code>&nbsp;的新列，其中包含&nbsp;<code>salary</code>&nbsp;值的&nbsp;<strong>两倍</strong>。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
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
<b>输出：</b>
+---------+--------+--------+
| name    | salary | bonus  |
+---------+--------+--------+
| Piper   | 4548   | 9096   |
| Grace   | 28150  | 56300  |
| Georgia | 1103   | 2206   |
| Willow  |  593   | 13186  |
| Finn    | 74576  | 149152 |
| Thomas  | 24433  | 48866  |
+---------+--------+--------+
<b>解释：</b>
通过将 salary 列中的值加倍创建了一个新的 bonus 列。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接计算

我们可以直接计算 `salary` 的两倍，然后将结果存入 `bonus` 列。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    employees['bonus'] = employees['salary'] * 2
    return employees
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
