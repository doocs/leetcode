---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3220.Odd%20and%20Even%20Transactions/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3220. 奇数和偶数交易](https://leetcode.cn/problems/odd-and-even-transactions)

[English Version](/solution/3200-3299/3220.Odd%20and%20Even%20Transactions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>transactions</code></p>

<pre>
+------------------+------+
| Column Name      | Type | 
+------------------+------+
| transaction_id   | int  |
| amount           | int  |
| transaction_date | date |
+------------------+------+
transactions_id 列唯一标识了表中的每一行。
这张表的每一行包含交易 id，金额总和和交易日期。
</pre>

<p>编写一个解决方案来查找每天 <strong>奇数</strong> 交易金额和 <strong>偶数</strong> 交易金额的 <strong>总和</strong>。如果某天没有奇数或偶数交易，显示为&nbsp;<code>0</code>。</p>

<p>返回结果表以&nbsp;<code>transaction_date</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p><code>transactions</code> 表：</p>

<pre class="example-io">
+----------------+--------+------------------+
| transaction_id | amount | transaction_date |
+----------------+--------+------------------+
| 1              | 150    | 2024-07-01       |
| 2              | 200    | 2024-07-01       |
| 3              | 75     | 2024-07-01       |
| 4              | 300    | 2024-07-02       |
| 5              | 50     | 2024-07-02       |
| 6              | 120    | 2024-07-03       |
+----------------+--------+------------------+
  </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+------------------+---------+----------+
| transaction_date | odd_sum | even_sum |
+------------------+---------+----------+
| 2024-07-01       | 75      | 350      |
| 2024-07-02       | 0       | 350      |
| 2024-07-03       | 0       | 120      |
+------------------+---------+----------+
  </pre>

<p><strong>解释：</strong></p>

<ul>
	<li>对于交易日期：
	<ul>
		<li>2024-07-01:
		<ul>
			<li>奇数交易金额总和：75</li>
			<li>偶数交易金额总和：150 + 200 = 350</li>
		</ul>
		</li>
		<li>2024-07-02:
		<ul>
			<li>奇数交易金额总和：0</li>
			<li>偶数交易金额总和：300 + 50 = 350</li>
		</ul>
		</li>
		<li>2024-07-03:
		<ul>
			<li>奇数交易金额总和：0</li>
			<li>偶数交易金额总和：120</li>
		</ul>
		</li>
	</ul>
	</li>
</ul>

<p><b>注意：</b>输出表以&nbsp;<code>transaction_date</code>&nbsp;升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组求和

我们可以将数据按照 `transaction_date` 进行分组，然后分别计算奇数和偶数的交易金额之和。最后按照 `transaction_date` 升序排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    transaction_date,
    SUM(IF(amount % 2 = 1, amount, 0)) AS odd_sum,
    SUM(IF(amount % 2 = 0, amount, 0)) AS even_sum
FROM transactions
GROUP BY 1
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def sum_daily_odd_even(transactions: pd.DataFrame) -> pd.DataFrame:
    transactions["odd_sum"] = transactions["amount"].where(
        transactions["amount"] % 2 == 1, 0
    )
    transactions["even_sum"] = transactions["amount"].where(
        transactions["amount"] % 2 == 0, 0
    )

    result = (
        transactions.groupby("transaction_date")
        .agg(odd_sum=("odd_sum", "sum"), even_sum=("even_sum", "sum"))
        .reset_index()
    )

    result = result.sort_values("transaction_date")

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
