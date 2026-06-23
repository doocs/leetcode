---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3962.Maximum%20Subarray%20Sum%20After%20at%20Most%20K%20Swaps/README_EN.md
rating: 2672
source: Weekly Contest 506 Q4
---

<!-- problem:start -->

# [3962. Maximum Subarray Sum After at Most K Swaps](https://leetcode.com/problems/maximum-subarray-sum-after-at-most-k-swaps)

[中文文档](/solution/3900-3999/3962.Maximum%20Subarray%20Sum%20After%20at%20Most%20K%20Swaps/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>You are allowed to perform <strong>at most</strong> <code>k</code> swap operations on the array.</p>

<p>In one swap operation, you may choose any two indices <code>i</code> and <code>j</code> and swap <code>nums[i]</code> and <code>nums[j]</code>.</p>

<p>Return an integer denoting the <strong>maximum possible <span data-keyword="subarray-nonempty">subarray</span> sum</strong> after performing the swaps.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,0,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We can swap on indices 1 and 3, resulting in the array <code>[1, 2, 0, -1]</code>.</li>
	<li>The subarray <code>[1, 2]</code> has a sum of 3, which is the maximum possible subarray sum after at most <code>k = 1</code>​​​​​​​ swap.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum possible subarray sum after at most <code>k = 2</code> swaps is the sum of the entire array, which is 13.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>k = 0</code> swaps are allowed.</li>
	<li>The possible subarrays are <code>[-1]</code>, <code>[-2]</code>, and <code>[-1, -2]</code>, with sums -1, -2, and -3 respectively.</li>
	<li>Among these sums, the maximum is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1500</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
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
