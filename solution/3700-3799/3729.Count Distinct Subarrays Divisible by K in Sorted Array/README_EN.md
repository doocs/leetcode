---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3729.Count%20Distinct%20Subarrays%20Divisible%20by%20K%20in%20Sorted%20Array/README_EN.md
---

<!-- problem:start -->

# [3729. Count Distinct Subarrays Divisible by K in Sorted Array](https://leetcode.com/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array)

[中文文档](/solution/3700-3799/3729.Count%20Distinct%20Subarrays%20Divisible%20by%20K%20in%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> <strong>sorted</strong> in <strong>non-descending</strong> order and a positive integer <code>k</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of <code>nums</code> is <strong>good</strong> if the sum of its elements is <strong>divisible</strong> by <code>k</code>.</p>

<p>Return an integer denoting the number of <strong>distinct</strong> <strong>good</strong> subarrays of <code>nums</code>.</p>

<p>Subarrays are <strong>distinct</strong> if their sequences of values are. For example, there are 3 <strong>distinct</strong> subarrays in <code>[1, 1, 1]</code>, namely <code>[1]</code>, <code>[1, 1]</code>, and <code>[1, 1, 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The good subarrays are <code>[1, 2]</code>, <code>[3]</code>, and <code>[1, 2, 3]</code>. For example, <code>[1, 2, 3]</code> is good because the sum of its elements is <code>1 + 2 + 3 = 6</code>, and <code>6 % k = 6 % 3 = 0</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,2,2,2,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The good subarrays are <code>[2, 2, 2]</code> and <code>[2, 2, 2, 2, 2, 2]</code>. For example, <code>[2, 2, 2]</code> is good because the sum of its elements is <code>2 + 2 + 2 = 6</code>, and <code>6 % k = 6 % 6 = 0</code>.</p>

<p>Note that <code>[2, 2, 2]</code> is counted only once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in non-descending order.</li>
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
