---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3475.DNA%20Pattern%20Recognition/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3475. DNA Pattern Recognition](https://leetcode.com/problems/dna-pattern-recognition)

[中文文档](/solution/3400-3499/3475.DNA%20Pattern%20Recognition/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Samples</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    | 
+----------------+---------+
| sample_id      | int     |
| dna_sequence   | varchar |
| species        | varchar |
+----------------+---------+
sample_id is the unique key for this table.
Each row contains a DNA sequence represented as a string of characters (A, T, G, C) and the species it was collected from.
</pre>

<p>Biologists are studying basic patterns in DNA sequences. Write a solution to identify <code>sample_id</code> with the following patterns:</p>

<ul>
	<li>Sequences that <strong>start</strong> with <strong>ATG</strong>&nbsp;(a common <strong>start codon</strong>)</li>
	<li>Sequences that <strong>end</strong> with either <strong>TAA</strong>, <strong>TAG</strong>, or <strong>TGA</strong>&nbsp;(<strong>stop codons</strong>)</li>
	<li>Sequences containing the motif <strong>ATAT</strong>&nbsp;(a simple repeated pattern)</li>
	<li>Sequences that have <strong>at least</strong> <code>3</code> <strong>consecutive</strong> <strong>G</strong>&nbsp;(like <strong>GGG</strong>&nbsp;or <strong>GGGG</strong>)</li>
</ul>

<p>Return <em>the result table ordered by&nbsp;</em><em>sample_id in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Samples table:</p>

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

<p><strong>Output:</strong></p>

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

<p><strong>Explanation:</strong></p>

<ul>
	<li>Sample 1 (ATGCTAGCTAGCTAA):
	<ul>
		<li>Starts with ATG&nbsp;(has_start = 1)</li>
		<li>Ends with TAA&nbsp;(has_stop = 1)</li>
		<li>Does not contain ATAT&nbsp;(has_atat = 0)</li>
		<li>Does not contain at least 3 consecutive &#39;G&#39;s (has_ggg = 0)</li>
	</ul>
	</li>
	<li>Sample 2 (GGGTCAATCATC):
	<ul>
		<li>Does not start with ATG&nbsp;(has_start = 0)</li>
		<li>Does not end with TAA, TAG, or TGA&nbsp;(has_stop = 0)</li>
		<li>Does not contain ATAT&nbsp;(has_atat = 0)</li>
		<li>Contains GGG&nbsp;(has_ggg = 1)</li>
	</ul>
	</li>
	<li>Sample 3 (ATATATCGTAGCTA):
	<ul>
		<li>Does not start with ATG&nbsp;(has_start = 0)</li>
		<li>Does not end with TAA, TAG, or TGA&nbsp;(has_stop = 0)</li>
		<li>Contains ATAT&nbsp;(has_atat = 1)</li>
		<li>Does not contain at least 3 consecutive &#39;G&#39;s (has_ggg = 0)</li>
	</ul>
	</li>
	<li>Sample 4 (ATGGGGTCATCATAA):
	<ul>
		<li>Starts with ATG&nbsp;(has_start = 1)</li>
		<li>Ends with TAA&nbsp;(has_stop = 1)</li>
		<li>Does not contain ATAT&nbsp;(has_atat = 0)</li>
		<li>Contains GGGG&nbsp;(has_ggg = 1)</li>
	</ul>
	</li>
	<li>Sample 5 (TCAGTCAGTCAG):
	<ul>
		<li>Does not match any patterns (all fields = 0)</li>
	</ul>
	</li>
	<li>Sample 6 (ATATCGCGCTAG):
	<ul>
		<li>Does not start with ATG&nbsp;(has_start = 0)</li>
		<li>Ends with TAG&nbsp;(has_stop = 1)</li>
		<li>Starts with ATAT&nbsp;(has_atat = 1)</li>
		<li>Does not contain at least 3 consecutive &#39;G&#39;s (has_ggg = 0)</li>
	</ul>
	</li>
	<li>Sample 7 (CGTATGCGTCGTA):
	<ul>
		<li>Does not start with ATG&nbsp;(has_start = 0)</li>
		<li>Does not end with TAA, &quot;TAG&quot;, or &quot;TGA&quot; (has_stop = 0)</li>
		<li>Does not contain ATAT&nbsp;(has_atat = 0)</li>
		<li>Does not contain at least 3 consecutive &#39;G&#39;s (has_ggg = 0)</li>
	</ul>
	</li>
</ul>

<p><strong>Note:</strong></p>

<ul>
	<li>The result is ordered by sample_id in ascending order</li>
	<li>For each pattern, 1 indicates the pattern is present and 0 indicates it is not present</li>
</ul>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Fuzzy Matching + Regular Expressions

We can use `LIKE` and `REGEXP` for pattern matching, where:

-   LIKE `'ATG%'` checks if it starts with ATG
-   REGEXP `'TAA$|TAG$|TGA$'` checks if it ends with TAA, TAG, or TGA ($ indicates the end of the string)
-   LIKE `'%ATAT%'` checks if it contains ATAT
-   REGEXP `'GGG+'` checks if it contains at least 3 consecutive Gs

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
