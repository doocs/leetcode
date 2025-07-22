---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3475.DNA%20Pattern%20Recognition/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3475. DNA 模式识别](https://leetcode.cn/problems/dna-pattern-recognition)

[English Version](/solution/3400-3499/3475.DNA%20Pattern%20Recognition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Samples</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    | 
+----------------+---------+
| sample_id      | int     |
| dna_sequence   | varchar |
| species        | varchar |
+----------------+---------+
sample_id 是这张表的唯一主键。
每一行包含一个 DNA 序列以一个字符（A，T，G，C）组成的字符串表示以及它所采集自的物种。
</pre>

<p>生物学家正在研究 DNA 序列中的基本模式。编写一个解决方案以识别具有以下模式的&nbsp;<code>sample_id</code>：</p>

<ul>
	<li>以&nbsp;<strong>ATG</strong> <strong>开头</strong>&nbsp;的序列（一个常见的 <strong>起始密码子</strong>）</li>
	<li>以 <strong>TAA</strong>，<strong>TAG</strong>&nbsp;或&nbsp;<strong>TGA</strong>&nbsp;<strong>结尾</strong>&nbsp;的序列（终止密码子）</li>
	<li>包含基序 <strong>ATAT</strong> 的序列（一个简单重复模式）</li>
	<li>有 <strong>至少</strong>&nbsp;<code>3</code>&nbsp;<strong>个连续</strong>&nbsp;<strong>G</strong>&nbsp;的序列（如&nbsp;<strong>GGG</strong>&nbsp;或&nbsp;<strong>GGGG</strong>）</li>
</ul>

<p>返回结果表以&nbsp;sample_id <strong>升序</strong>&nbsp;排序<em>。</em></p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Samples 表：</p>

<pre class="example-io">
+-----------+------------------+-----------+
| sample_id | dna_sequence     | species   |
+-----------+------------------+-----------+
| 1         | ATGCTAGCTAGCTAA  | Human     |
| 2         | GGGTCAATCATC     | Human     |
| 3         | ATATATCGTAGCTA   | Human     |
| 4         | ATGGGGTCATCATAA  | Mouse     |
| 5         | TCAGTCAGTCAG     | Mouse     |
| 6         | ATATCGCGCTAG     | Zebrafish |
| 7         | CGTATGCGTCGTA    | Zebrafish |
+-----------+------------------+-----------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+------------------+-------------+-------------+------------+------------+------------+
| sample_id | dna_sequence     | species     | has_start   | has_stop   | has_atat   | has_ggg    |
+-----------+------------------+-------------+-------------+------------+------------+------------+
| 1         | ATGCTAGCTAGCTAA  | Human       | 1           | 1          | 0          | 0          |
| 2         | GGGTCAATCATC     | Human       | 0           | 0          | 0          | 1          |
| 3         | ATATATCGTAGCTA   | Human       | 0           | 0          | 1          | 0          |
| 4         | ATGGGGTCATCATAA  | Mouse       | 1           | 1          | 0          | 1          |
| 5         | TCAGTCAGTCAG     | Mouse       | 0           | 0          | 0          | 0          |
| 6         | ATATCGCGCTAG     | Zebrafish   | 0           | 1          | 1          | 0          |
| 7         | CGTATGCGTCGTA    | Zebrafish   | 0           | 0          | 0          | 0          |
+-----------+------------------+-------------+-------------+------------+------------+------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>样本 1（ATGCTAGCTAGCTAA）：
	<ul>
		<li>以 ATG 开头（has_start = 1）</li>
		<li>以 TAA 结尾（has_stop = 1）</li>
		<li>不包含 ATAT（has_atat = 0）</li>
		<li>不包含至少 3 个连续 ‘G’（has_ggg = 0）</li>
	</ul>
	</li>
	<li>样本 2（GGGTCAATCATC）：
	<ul>
		<li>不以 ATG 开头（has_start = 0）</li>
		<li>不以 TAA，TAG 或 TGA 结尾（has_stop = 0）</li>
		<li>不包含 ATAT（has_atat = 0）</li>
		<li>包含 GGG（has_ggg = 1）</li>
	</ul>
	</li>
	<li>样本 3（ATATATCGTAGCTA）：
	<ul>
		<li>不以 ATG 开头（has_start = 0）</li>
		<li>不以 TAA，TAG 或 TGA 结尾（has_stop = 0）</li>
		<li>包含 ATAT（has_atat = 1）</li>
		<li>不包含至少 3 个连续 ‘G’（has_ggg = 0）</li>
	</ul>
	</li>
	<li>样本 4（ATGGGGTCATCATAA）：
	<ul>
		<li>以 ATG 开头（has_start = 1）</li>
		<li>以 TAA 结尾（has_stop = 1）</li>
		<li>不包含 ATAT（has_atat = 0）</li>
		<li>包含 GGGG（has_ggg = 1）</li>
	</ul>
	</li>
	<li>样本 5（TCAGTCAGTCAG）：
	<ul>
		<li>不匹配任何模式（所有字段 = 0）</li>
	</ul>
	</li>
	<li>样本 6（ATATCGCGCTAG）：
	<ul>
		<li>不以 ATG 开头（has_start = 0）</li>
		<li>以 TAG 结尾（has_stop = 1）</li>
		<li>包含 ATAT（has_atat = 1）</li>
		<li>不包含至少 3 个连续 ‘G’（has_ggg = 0）</li>
	</ul>
	</li>
	<li>样本 7（CGTATGCGTCGTA）：
	<ul>
		<li>不以 ATG 开头（has_start = 0）</li>
		<li>不以 TAA，TAG 或 TGA 结尾（has_stop = 0）</li>
		<li>不包含 ATAT（has_atat = 0）</li>
		<li>不包含至少 3 个连续 ‘G’（has_ggg = 0）</li>
	</ul>
	</li>
</ul>

<p><strong>注意：</strong></p>

<ul>
	<li>结果以 sample_id 升序排序</li>
	<li>对于每个模式，1 表示该模式存在，0 表示不存在</li>
</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模糊匹配 + 正则表达式

我们可以利用 `LIKE` 和 `REGEXP` 来进行模式匹配，其中：

-   LIKE `'ATG%'` 检测是否以 ATG 开头
-   REGEXP `'TAA$|TAG$|TGA$'` 检测是否以 TAA、TAG 或 TGA 结尾（$ 表示字符串结尾）
-   LIKE `'%ATAT%'` 检测是否包含 ATAT
-   REGEXP `'GGG+'` 检测是否包含至少 3 个 G

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    sample_id,
    dna_sequence,
    species,
    dna_sequence LIKE 'ATG%' AS has_start,
    dna_sequence REGEXP 'TAA$|TAG$|TGA$' AS has_stop,
    dna_sequence LIKE '%ATAT%' AS has_atat,
    dna_sequence REGEXP 'GGG+' AS has_ggg
FROM Samples
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def analyze_dna_patterns(samples: pd.DataFrame) -> pd.DataFrame:
    samples["has_start"] = samples["dna_sequence"].str.startswith("ATG").astype(int)
    samples["has_stop"] = (
        samples["dna_sequence"].str.endswith(("TAA", "TAG", "TGA")).astype(int)
    )
    samples["has_atat"] = samples["dna_sequence"].str.contains("ATAT").astype(int)
    samples["has_ggg"] = samples["dna_sequence"].str.contains("GGG+").astype(int)
    return samples.sort_values(by="sample_id").reset_index(drop=True)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
