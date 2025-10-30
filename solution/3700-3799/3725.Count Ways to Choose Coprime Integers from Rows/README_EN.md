---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3725.Count%20Ways%20to%20Choose%20Coprime%20Integers%20from%20Rows/README_EN.md
rating: 1981
source: Biweekly Contest 168 Q4
tags:
    - Array
    - Math
    - Dynamic Programming
    - Combinatorics
    - Matrix
    - Number Theory
---

<!-- problem:start -->

# [3725. Count Ways to Choose Coprime Integers from Rows](https://leetcode.com/problems/count-ways-to-choose-coprime-integers-from-rows)

[中文文档](/solution/3700-3799/3725.Count%20Ways%20to%20Choose%20Coprime%20Integers%20from%20Rows/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>m x n</code> matrix <code>mat</code> of positive integers.</p>

<p>Return an integer denoting the number of ways to choose <strong>exactly one</strong> integer from each row of <code>mat</code> such that the <strong>greatest common divisor</strong> of all chosen integers is 1.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th align="center" style="border: 1px solid black;">Chosen integer in the first row</th>
			<th align="center" style="border: 1px solid black;">Chosen integer in the second row</th>
			<th align="center" style="border: 1px solid black;">Greatest common divisor of chosen integers</th>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>

<p>3 of these combinations have a greatest common divisor of 1. Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[2,2],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Every combination has a greatest common divisor of 2. Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == mat.length &lt;= 150</code></li>
	<li><code>1 &lt;= n == mat[i].length &lt;= 150</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 150</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
