# [2882. 删去重复的行](https://leetcode.cn/problems/drop-duplicate-rows)

[English Version](/solution/2800-2899/2882.Drop%20Duplicate%20Rows/README_EN.md)

<!-- tags: -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<pre>
DataFrame customers
+-------------+--------+
| Column Name | Type   |
+-------------+--------+
| customer_id | int    |
| name        | object |
| email       | object |
+-------------+--------+
</pre>

<p>在 DataFrame 中基于&nbsp;<code>email</code>&nbsp;列存在一些重复行。</p>

<p>编写一个解决方案，删除这些重复行，仅保留第一次出现的行。</p>

<p>返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>
+-------------+---------+---------------------+
| customer_id | name    | email               |
+-------------+---------+---------------------+
| 1           | Ella    | emily@example.com   |
| 2           | David   | michael@example.com |
| 3           | Zachary | sarah@example.com   |
| 4           | Alice   | john@example.com    |
| 5           | Finn    | john@example.com    |
| 6           | Violet  | alice@example.com   |
+-------------+---------+---------------------+
<b>输出：</b>
+-------------+---------+---------------------+
| customer_id | name    | email               |
+-------------+---------+---------------------+
| 1           | Ella    | emily@example.com   |
| 2           | David   | michael@example.com |
| 3           | Zachary | sarah@example.com   |
| 4           | Alice   | john@example.com    |
| 6           | Violet  | alice@example.com   |
+-------------+---------+---------------------+
<b>解释：</b>
Alice (customer_id = 4) 和 Finn (customer_id = 5) 都使用 john@example.com，因此只保留该邮箱地址的第一次出现。
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```python
import pandas as pd


def dropDuplicateEmails(customers: pd.DataFrame) -> pd.DataFrame:
    return customers.drop_duplicates(subset=['email'])
```

<!-- tabs:end -->

<!-- end -->
