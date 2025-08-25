---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3639.Minimum%20Time%20to%20Activate%20String/README_EN.md
rating: 1853
source: Weekly Contest 461 Q3
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3639. Minimum Time to Activate String](https://leetcode.com/problems/minimum-time-to-activate-string)

[中文文档](/solution/3600-3699/3639.Minimum%20Time%20to%20Activate%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> and an integer array <code>order</code>, where <code>order</code> is a <strong><span data-keyword="permutation">permutation</span></strong> of the numbers in the range <code>[0, n - 1]</code>.</p>

<p>Starting from time <code>t = 0</code>, replace the character at index <code>order[t]</code> in <code>s</code> with <code>&#39;*&#39;</code> at each time step.</p>

<p>A <strong><span data-keyword="substring-nonempty">substring</span></strong> is <strong>valid</strong> if it contains <strong>at least</strong> one <code>&#39;*&#39;</code>.</p>

<p>A string is <strong>active</strong> if the total number of <strong>valid</strong> substrings is greater than or equal to <code>k</code>.</p>

<p>Return the <strong>minimum</strong> time <code>t</code> at which the string <code>s</code> becomes <strong>active</strong>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, order = [1,0,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>t</code></th>
			<th style="border: 1px solid black;"><code>order[t]</code></th>
			<th style="border: 1px solid black;">Modified <code>s</code></th>
			<th style="border: 1px solid black;">Valid Substrings</th>
			<th style="border: 1px solid black;">Count</th>
			<th style="border: 1px solid black;">Active<br />
			(Count &gt;= k)</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&quot;a*c&quot;</code></td>
			<td style="border: 1px solid black;"><code>&quot;*&quot;</code>, <code>&quot;a*&quot;</code>, <code>&quot;*c&quot;</code>, <code>&quot;a*c&quot;</code></td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
	</tbody>
</table>

<p>The string <code>s</code> becomes active at <code>t = 0</code>. Thus, the answer is 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cat&quot;, order = [0,2,1], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>t</code></th>
			<th style="border: 1px solid black;"><code>order[t]</code></th>
			<th style="border: 1px solid black;">Modified <code>s</code></th>
			<th style="border: 1px solid black;">Valid Substrings</th>
			<th style="border: 1px solid black;">Count</th>
			<th style="border: 1px solid black;">Active<br />
			(Count &gt;= k)</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;"><code>&quot;*at&quot;</code></td>
			<td style="border: 1px solid black;"><code>&quot;*&quot;</code>, <code>&quot;*a&quot;</code>, <code>&quot;*at&quot;</code></td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>&quot;*a*&quot;</code></td>
			<td style="border: 1px solid black;"><code>&quot;*&quot;</code>, <code>&quot;*a&quot;</code>, <code>&quot;<code inline="">*a*&quot;</code></code>, <code>&quot;<code inline="">a*&quot;</code></code>, <code>&quot;*&quot;</code></td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>&quot;***&quot;</code></td>
			<td style="border: 1px solid black;">All substrings (contain <code>&#39;*&#39;</code>)</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
	</tbody>
</table>

<p>The string <code>s</code> becomes active at <code>t = 2</code>. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;xy&quot;, order = [0,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Even after all replacements, it is impossible to obtain <code>k = 4</code> valid substrings. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>order.length == n</code></li>
	<li><code>0 &lt;= order[i] &lt;= n - 1</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>order</code> is a permutation of integers from 0 to <code>n - 1</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
