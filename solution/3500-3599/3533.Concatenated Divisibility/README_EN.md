---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3533.Concatenated%20Divisibility/README_EN.md
---

<!-- problem:start -->

# [3533. Concatenated Divisibility](https://leetcode.com/problems/concatenated-divisibility)

[中文文档](/solution/3500-3599/3533.Concatenated%20Divisibility/README.md)

## Description

<!-- description:start -->

<p data-end="378" data-start="31">You are given an array of positive integers <code data-end="85" data-start="79">nums</code> and a positive integer <code data-end="112" data-start="109">k</code>.</p>

<p data-end="378" data-start="31">A <span data-keyword="permutation-array">permutation</span> of <code data-end="137" data-start="131">nums</code> is said to form a <strong data-end="183" data-start="156">divisible concatenation</strong> if, when you <em>concatenate</em> <em>the decimal representations</em> of the numbers in the order specified by the permutation, the resulting number is <strong>divisible by</strong> <code data-end="359" data-start="356">k</code>.</p>

<p data-end="561" data-start="380">Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> permutation (when considered as a list of integers) that forms a <strong>divisible concatenation</strong>. If no such permutation exists, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,12,45], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,12,45]</span></p>

<p><strong>Explanation:</strong></p>

<table data-end="896" data-start="441" node="[object Object]" style="border: 1px solid black;">
	<thead data-end="497" data-start="441">
		<tr data-end="497" data-start="441">
			<th data-end="458" data-start="441" style="border: 1px solid black;">Permutation</th>
			<th data-end="479" data-start="458" style="border: 1px solid black;">Concatenated Value</th>
			<th data-end="497" data-start="479" style="border: 1px solid black;">Divisible by 5</th>
		</tr>
	</thead>
	<tbody data-end="896" data-start="555">
		<tr data-end="611" data-start="555">
			<td style="border: 1px solid black;">[3, 12, 45]</td>
			<td style="border: 1px solid black;">31245</td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr data-end="668" data-start="612">
			<td style="border: 1px solid black;">[3, 45, 12]</td>
			<td style="border: 1px solid black;">34512</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr data-end="725" data-start="669">
			<td style="border: 1px solid black;">[12, 3, 45]</td>
			<td style="border: 1px solid black;">12345</td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr data-end="782" data-start="726">
			<td style="border: 1px solid black;">[12, 45, 3]</td>
			<td style="border: 1px solid black;">12453</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr data-end="839" data-start="783">
			<td style="border: 1px solid black;">[45, 3, 12]</td>
			<td style="border: 1px solid black;">45312</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr data-end="896" data-start="840">
			<td style="border: 1px solid black;">[45, 12, 3]</td>
			<td style="border: 1px solid black;">45123</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<p data-end="1618" data-start="1525">The lexicographically smallest permutation that forms a divisible concatenation is <code>[3,12,45]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,5], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,10]</span></p>

<p><strong>Explanation:</strong></p>

<table data-end="1421" data-start="1200" node="[object Object]" style="border: 1px solid black;">
	<thead data-end="1255" data-start="1200">
		<tr data-end="1255" data-start="1200">
			<th data-end="1216" data-start="1200" style="border: 1px solid black;">Permutation</th>
			<th data-end="1237" data-start="1216" style="border: 1px solid black;">Concatenated Value</th>
			<th data-end="1255" data-start="1237" style="border: 1px solid black;">Divisible by 10</th>
		</tr>
	</thead>
	<tbody data-end="1421" data-start="1312">
		<tr data-end="1366" data-start="1312">
			<td style="border: 1px solid black;">[5, 10]</td>
			<td style="border: 1px solid black;">510</td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr data-end="1421" data-start="1367">
			<td style="border: 1px solid black;">[10, 5]</td>
			<td style="border: 1px solid black;">105</td>
			<td style="border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<p data-end="2011" data-start="1921">The lexicographically smallest permutation that forms a divisible concatenation is <code>[5,10]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since no permutation of <code data-end="177" data-start="171">nums</code> forms a valid divisible concatenation, return an empty list.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 13</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
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
