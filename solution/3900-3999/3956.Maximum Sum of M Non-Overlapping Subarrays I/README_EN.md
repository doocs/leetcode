---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3956.Maximum%20Sum%20of%20M%20Non-Overlapping%20Subarrays%20I/README_EN.md
rating: 2060
source: Weekly Contest 505 Q3
tags:
    - Queue
    - Array
    - Binary Search
    - Dynamic Programming
    - Prefix Sum
    - Sliding Window
    - Monotonic Queue
---

<!-- problem:start -->

# [3956. Maximum Sum of M Non-Overlapping Subarrays I](https://leetcode.com/problems/maximum-sum-of-m-non-overlapping-subarrays-i)

[中文文档](/solution/3900-3999/3956.Maximum%20Sum%20of%20M%20Non-Overlapping%20Subarrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, and three integers <code>m</code>, <code>l</code>, and <code>r</code>.</p>

<p>Your task is to select <strong>at least</strong> one and <strong>at most</strong> <code>m</code> <strong>non-overlapping <span data-keyword="subarray-nonempty">subarrays</span></strong> from <code>nums</code> such that:</p>

<ul>
	<li>Each selected <strong>subarray</strong> has a length between <code>[l, r]</code> (inclusive).</li>
	<li>The total sum of all selected <strong>subarrays</strong> is <strong>maximized</strong>.</li>
</ul>

<p>Return the <strong>maximum</strong> total sum you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,-5,2], m = 2, l = 1, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal strategy is to:</p>

<ul>
	<li>Select the subarray <code>[4, 1]</code> with sum <code>4 + 1 = 5</code> and the subarray <code>[2]</code> with sum 2. Both subarrays have length between <code>[l, r]</code>.</li>
	<li>The total sum of these subarrays is <code>5 + 2 = 7</code>, which is the maximum achievable sum with at most <code>m = 2</code> subarrays.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,3,4], m = 2, l = 1, r = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal strategy is to:</p>

<ul>
	<li>Select the subarray <code>[1]</code> with sum <code>1</code> and the subarray <code>[3, 4]</code> with sum <code>3 + 4 = 7</code>. Both subarrays have length between <code>[l, r]</code>.</li>
	<li>The total sum of these subarrays is <code>1 + 7 = 8</code>, which is the maximum achievable sum with at most <code>m = 2</code> subarrays.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,7,-4], m = 1, l = 2, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Select the subarray <code>[-1, 7]</code> from <code>nums</code> which has length between <code>[l, r]</code>.</li>
	<li>The total sum of this subarray is <code>-1 + 7 = 6</code>, which is the maximum achievable sum with at most <code>m = 1</code> subarray.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,-4,-1], m = 2, l = 1, r = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>All subarrays of <code>nums</code> have negative sums. The optimal strategy is to select the subarray <code>[-1]</code>, which has length between <code>[l, r]</code>.</li>
	<li>The total sum of this subarray is -1, which is the maximum achievable sum with at most <code>m = 2</code> subarrays.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup>​​​​​​​</code></li>
	<li><code>1 &lt;= m &lt;= n</code></li>
	<li><code>1 &lt;= l &lt;= r &lt;= n</code></li>
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
