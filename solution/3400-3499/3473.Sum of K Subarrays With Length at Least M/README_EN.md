---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3473.Sum%20of%20K%20Subarrays%20With%20Length%20at%20Least%20M/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3473. Sum of K Subarrays With Length at Least M](https://leetcode.com/problems/sum-of-k-subarrays-with-length-at-least-m)

[中文文档](/solution/3400-3499/3473.Sum%20of%20K%20Subarrays%20With%20Length%20at%20Least%20M/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers, <code>k</code> and <code>m</code>.</p>

<p>Return the <strong>maximum</strong> sum of <code>k</code> non-overlapping <span data-keyword="subarray">subarrays</span> of <code>nums</code>, where each subarray has a length of <strong>at least</strong> <code>m</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,-1,3,3,4], k = 2, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal choice is:</p>

<ul>
	<li>Subarray <code>nums[3..5]</code> with sum <code>3 + 3 + 4 = 10</code> (length is <code>3 &gt;= m</code>).</li>
	<li>Subarray <code>nums[0..1]</code> with sum <code>1 + 2 = 3</code> (length is <code>2 &gt;= m</code>).</li>
</ul>

<p>The total sum is <code>10 + 3 = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-10,3,-1,-2], k = 4, m = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-10</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal choice is choosing each element as a subarray. The output is <code>(-10) + 3 + (-1) + (-2) = -10</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= floor(nums.length / m)</code></li>
	<li><code>1 &lt;= m &lt;= 3</code></li>
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
