---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4071. Minimum Rotations to Dial a Number II](https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-ii)

[English Version](/solution/4000-4099/4071.Minimum%20Rotations%20to%20Dial%20a%20Number%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer <code>n</code> and a string <code>s</code> of length <code>n</code> consisting of digits.</p>

<p>The dial contains the digits 0 through 9 in order and is <strong>circular</strong>, so 0 and 9 are adjacent. The pointer initially points to 0.</p>

<p>To dial each digit of <code>s</code> <strong>in order</strong>, rotate the pointer until it points to that digit. Each rotation moves the pointer to an <strong>adjacent</strong> digit, and you may rotate in <strong>either</strong> direction. Dialing a digit that the pointer already points to requires no rotations.</p>

<p>Before dialing, you may perform the following operation <strong>at most once</strong>:</p>

<ul>
	<li>Choose an index <code>k</code> such that <code>0 &lt;= k &lt; n</code> and <strong>reverse</strong> the <strong><span data-keyword="string-suffix">suffix</span></strong> <code>s[k..n - 1]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> total number of rotations needed to dial the string after optimally choosing whether to perform the operation and which suffix to reverse.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;1502&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>Reverse the suffix starting at <code>k = 1</code> to obtain <code>&quot;1205&quot;</code>, then dial it.</p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>1 + 1 + 2 + 5 = 9</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;2916&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose not to reverse a suffix and dial <code>&quot;2916&quot;</code>.</p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">6</td>
			<td style="border: 1px solid #ccc; padding: 5px;">5</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>2 + 3 + 2 + 5 = 12</code>, which is the minimum total number of rotations.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = &quot;4219&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Reverse the suffix starting at <code>k = 0</code>, which reverses the entire string, to obtain <code>&quot;9124&quot;</code>, then dial it.</p>

<table style="border-collapse: collapse; text-align: center;">
	<thead>
		<tr>
			<th style="border: 1px solid #ccc; padding: 5px;">Step</th>
			<th style="border: 1px solid #ccc; padding: 5px;">From</th>
			<th style="border: 1px solid #ccc; padding: 5px;">To</th>
			<th style="border: 1px solid #ccc; padding: 5px;">Rotations</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">0</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">9</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">3</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
			<td style="border: 1px solid #ccc; padding: 5px;">4</td>
			<td style="border: 1px solid #ccc; padding: 5px;">2</td>
		</tr>
	</tbody>
</table>

<p>The total is <code>1 + 2 + 1 + 2 = 6</code>, which is the minimum total number of rotations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
