---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3428.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subsequences/README_EN.md
rating: 2028
source: Weekly Contest 433 Q2
tags:
    - Array
    - Math
    - Dynamic Programming
    - Combinatorics
    - Sorting
---

<!-- problem:start -->

# [3428. Maximum and Minimum Sums of at Most Size K Subsequences](https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences)

[中文文档](/solution/3400-3499/3428.Maximum%20and%20Minimum%20Sums%20of%20at%20Most%20Size%20K%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a positive integer <code>k</code>. Return the sum of the <strong>maximum</strong> and <strong>minimum</strong> elements of all <strong><span data-keyword="subsequence-sequence-nonempty">subsequences</span></strong> of <code>nums</code> with <strong>at most</strong> <code>k</code> elements.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 2</span></p>

<p><strong>Output:</strong> 24</p>

<p><strong>Explanation:</strong></p>

<p>The subsequences of <code>nums</code> with at most 2 elements are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><b>Subsequence </b></th>
			<th style="border: 1px solid black;">Minimum</th>
			<th style="border: 1px solid black;">Maximum</th>
			<th style="border: 1px solid black;">Sum</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 2]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[1, 3]</code></td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><strong>Final Total</strong></td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">&nbsp;</td>
			<td style="border: 1px solid black;">24</td>
		</tr>
	</tbody>
</table>

<p>The output would be 24.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,0,6], k = 1</span></p>

<p><strong>Output:</strong> 2<span class="example-io">2</span></p>

<p><strong>Explanation: </strong></p>

<p>For subsequences with exactly 1 element, the minimum and maximum values are the element itself. Therefore, the total is <code>5 + 5 + 0 + 0 + 6 + 6 = 22</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1], k = 2</span></p>

<p><strong>Output:</strong> 12</p>

<p><strong>Explanation:</strong></p>

<p>The subsequences <code>[1, 1]</code> and <code>[1]</code> each appear 3 times. For all of them, the minimum and maximum are both 1. Thus, the total is 12.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code><font face="monospace">1 &lt;= k &lt;= min(70, nums.length)</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> We want the sum of maxima plus the sum of minima over subsequences of length at most $k$. $n\le 10^5$ and $k\le 100$ rule out listing subsequences.
>
> After sorting, the number of times $a_i$ is a maximum (minimum) equals the number of ways to pick at most $k-1$ elements from its left (right).
>
> We therefore add $a_i\cdot\sum_{j=0}^{\min(i,k-1)}C(i,j)$ as a max contribution, and the symmetric min contribution. Binomial coefficients are built row-wise because $k$ is only $100$.

<!-- thinking:end -->

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
