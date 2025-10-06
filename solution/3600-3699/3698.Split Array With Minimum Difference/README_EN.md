---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3698.Split%20Array%20With%20Minimum%20Difference/README_EN.md
rating: 1648
source: Weekly Contest 469 Q2
---

<!-- problem:start -->

# [3698. Split Array With Minimum Difference](https://leetcode.com/problems/split-array-with-minimum-difference)

[中文文档](/solution/3600-3699/3698.Split%20Array%20With%20Minimum%20Difference/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Split the array into <strong>exactly</strong> two <span data-keyword="subarray-nonempty">subarrays</span>, <code>left</code> and <code>right</code>, such that <code>left</code> is <strong><span data-keyword="strictly-increasing-array">strictly increasing</span> </strong> and <code>right</code> is <strong><span data-keyword="strictly-decreasing-array">strictly decreasing</span></strong>.</p>

<p>Return the <strong>minimum possible absolute difference</strong> between the sums of <code>left</code> and <code>right</code>. If no valid split exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[3, 2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;"><code>|1 - 5| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>|4 - 2| = 2</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>left</code></th>
			<th style="border: 1px solid black;"><code>right</code></th>
			<th style="border: 1px solid black;">Validity</th>
			<th style="border: 1px solid black;"><code>left</code> sum</th>
			<th style="border: 1px solid black;"><code>right</code> sum</th>
			<th style="border: 1px solid black;">Absolute difference</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[2, 4, 3]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">-</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[4, 3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;"><code>|3 - 7| = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[1, 2, 4]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>|7 - 3| = 4</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum absolute difference is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid split exists, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
