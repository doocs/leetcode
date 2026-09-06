---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4033.Valid%20K-Unique%20Subarrays%20I/README_EN.md
rating: 2314
source: Weekly Contest 516 Q4
---

<!-- problem:start -->

# [4033. Valid K-Unique Subarrays I](https://leetcode.com/problems/valid-k-unique-subarrays-i)

[中文文档](/solution/4000-4099/4033.Valid%20K-Unique%20Subarrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You are also given a 2D integer array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represents the <span data-keyword="subarray-nonempty"><strong>subarray</strong></span> <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>.</p>

<p>For each query, the <strong>subarray</strong> <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> is considered <strong>valid</strong> if:</p>

<ul>
	<li>It contains <strong>exactly</strong> <code>k</code> <strong>distinct</strong> numbers, and</li>
	<li>The <span data-keyword="frequency-array"><strong>frequency</strong></span> of every number in the <strong>subarray</strong> is <strong>even</strong>.</li>
</ul>

<p>Return a boolean array <code>ans</code>, where <code>ans[i]</code> is <code>true</code> if <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> is <strong>valid</strong>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,1], k = 2, queries = [[0,1],[0,3],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,true,false]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Unique numbers</th>
			<th style="border: 1px solid black;">Frequency</th>
			<th style="border: 1px solid black;">Validity check</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[0, 1]</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">{1, 2} &rarr; 2</td>
			<td style="border: 1px solid black;">{1: 1, 2: 1}</td>
			<td style="border: 1px solid black;"><code>false</code>: Element counts are not even.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[0, 3]</td>
			<td style="border: 1px solid black;">[1, 2, 2, 1]</td>
			<td style="border: 1px solid black;">{1, 2} &rarr; 2</td>
			<td style="border: 1px solid black;">{1: 2, 2: 2}</td>
			<td style="border: 1px solid black;"><code>true</code>: Exactly <code>k = 2</code> distinct elements, all appear&nbsp;an even number of times.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[2, 2]</td>
			<td style="border: 1px solid black;">{2} &rarr; 1</td>
			<td style="border: 1px solid black;">{2: 2}</td>
			<td style="border: 1px solid black;"><code>false</code>: Number of distinct elements is less than <code>k = 2</code>.</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [false, true, false]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3], k = 1, queries = [[1,2],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Unique numbers</th>
			<th style="border: 1px solid black;">Frequency</th>
			<th style="border: 1px solid black;">Validity check</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[3, 3]</td>
			<td style="border: 1px solid black;">{3} &rarr; 1</td>
			<td style="border: 1px solid black;">{3: 2}</td>
			<td style="border: 1px solid black;"><code>true</code>: Exactly <code>k = 1</code> distinct element, appears an&nbsp;even number of times.</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[0, 2]</td>
			<td style="border: 1px solid black;">[3, 3, 3]</td>
			<td style="border: 1px solid black;">{3} &rarr; 1</td>
			<td style="border: 1px solid black;">{3: 3}</td>
			<td style="border: 1px solid black;"><code>false</code>: 3 does not appear an even number of times.</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [true, false]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt;= n - 1</code></li>
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
