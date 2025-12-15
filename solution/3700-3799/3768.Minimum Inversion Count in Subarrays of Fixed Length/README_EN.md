---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3768.Minimum%20Inversion%20Count%20in%20Subarrays%20of%20Fixed%20Length/README_EN.md
rating: 2157
source: Biweekly Contest 171 Q4
tags:
    - Segment Tree
    - Array
    - Sliding Window
---

<!-- problem:start -->

# [3768. Minimum Inversion Count in Subarrays of Fixed Length](https://leetcode.com/problems/minimum-inversion-count-in-subarrays-of-fixed-length)

[中文文档](/solution/3700-3799/3768.Minimum%20Inversion%20Count%20in%20Subarrays%20of%20Fixed%20Length/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>An <strong>inversion</strong> is a pair of indices <code>(i, j)</code> from <code>nums</code> such that <code>i &lt; j</code> and <code>nums[i] &gt; nums[j]</code>.</p>

<p>The <strong>inversion count</strong> of a <strong><span data-keyword="subarray-nonempty">subarray</span></strong> is the number of inversions within it.</p>

<p>Return the <strong>minimum</strong> inversion count among all <strong>subarrays</strong> of <code>nums</code> with length <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2,5,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>We consider all subarrays of length <code>k = 3</code> (indices below are relative to each subarray):</p>

<ul>
	<li><code>[3, 1, 2]</code> has 2 inversions: <code>(0, 1)</code> and <code>(0, 2)</code>.</li>
	<li><code>[1, 2, 5]</code> has 0 inversions.</li>
	<li><code>[2, 5, 4]</code> has 1 inversion: <code>(1, 2)</code>.</li>
</ul>

<p>The minimum inversion count among all subarrays of length <code>3</code> is 0, achieved by subarray <code>[1, 2, 5]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,3,2,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one subarray of length <code>k = 4</code>: <code>[5, 3, 2, 1]</code>.<br />
Within this subarray, the inversions are: <code>(0, 1)</code>, <code>(0, 2)</code>, <code>(0, 3)</code>, <code>(1, 2)</code>, <code>(1, 3)</code>, and <code>(2, 3)</code>.<br />
Total inversions is 6, so the minimum inversion count is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All subarrays of length <code>k = 1</code> contain only one element, so no inversions are possible.<br />
The minimum inversion count is therefore 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
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
