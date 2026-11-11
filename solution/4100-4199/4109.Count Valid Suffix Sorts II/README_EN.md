---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4109. Count Valid Suffix Sorts II](https://leetcode.com/problems/count-valid-suffix-sorts-ii)

[中文文档](/solution/4100-4199/4109.Count%20Valid%20Suffix%20Sorts%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums</code> and <code>target</code> of lengths <code>n</code> and <code>m</code>, respectively, where <code>n &lt;= m</code>.</p>

<p>For each index <code>i</code>, consider sorting the <strong>suffix</strong> <code>nums[i..n - 1]</code> of the original array in <strong>non-increasing order</strong>.</p>

<p>Return an integer array <code>ans</code> of length <code>m - n + 1</code>, where <code>ans[k]</code> is the number of indices <code>i</code> for which the resulting array equals <code>target[k..k + n - 1]</code>.</p>

<p>A <strong>suffix</strong> of an array is a <span data-keyword="subarray-nonempty">subarray</span> that begins at any index and extends to its end.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,3], target = [2,2,3,2,1,3,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,0,0,0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Sorting the suffix starting at indices 0, 1, and 2 produces <code>[3, 2, 2]</code>, <code>[2, 3, 2]</code>, and <code>[2, 2, 3]</code>, respectively.</p>

<ul>
	<li><code>k = 0</code>: <code>[2, 2, 3]</code> is produced by <code>i = 2</code>.</li>
	<li><code>k = 1</code>: <code>[2, 3, 2]</code> is produced by <code>i = 1</code>.</li>
	<li><code>k = 2, 3, 4</code>: None of the corresponding subarrays can be produced.</li>
	<li><code>k = 5</code>: <code>[3, 2, 2]</code> is produced by <code>i = 0</code>.</li>
</ul>

<p>Therefore, the answer is <code>[1, 1, 0, 0, 0, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,6,5], target = [5,6,5,5,6,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,0,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Sorting the suffix starting at index 0 produces <code>[6, 5, 5]</code>, while starting at either index 1 or 2 produces <code>[5, 6, 5]</code>.</p>

<ul>
	<li><code>k = 0</code>: <code>[5, 6, 5]</code> is produced by <code>i = 1</code> and <code>i = 2</code>.</li>
	<li><code>k = 1</code>: <code>[6, 5, 5]</code> is produced by <code>i = 0</code>.</li>
	<li><code>k = 2</code>: <code>[5, 5, 6]</code> cannot be produced.</li>
	<li><code>k = 3</code>: <code>[5, 6, 5]</code> is produced by <code>i = 1</code> and <code>i = 2</code>.</li>
</ul>

<p>Therefore, the answer is <code>[2, 1, 0, 2]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == target.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>5</sup></code></li>
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
