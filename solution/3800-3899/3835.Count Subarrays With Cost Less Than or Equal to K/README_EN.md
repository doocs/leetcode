---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3835.Count%20Subarrays%20With%20Cost%20Less%20Than%20or%20Equal%20to%20K/README_EN.md
---

<!-- problem:start -->

# [3835. Count Subarrays With Cost Less Than or Equal to K](https://leetcode.com/problems/count-subarrays-with-cost-less-than-or-equal-to-k)

[中文文档](/solution/3800-3899/3835.Count%20Subarrays%20With%20Cost%20Less%20Than%20or%20Equal%20to%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>, and an integer <code>k</code>.</p>

<p>For any <span data-keyword="subarray-nonempty">subarray</span> <code>nums[l..r]</code>, define its <strong>cost</strong> as:</p>

<p><code>cost = (max(nums[l..r]) - min(nums[l..r])) * (r - l + 1)</code>.</p>

<p>Return an integer denoting the number of subarrays of <code>nums</code> whose cost is <strong>less than or equal</strong> to <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>We consider all subarrays of <code>nums</code>:</p>

<ul>
	<li><code>nums[0..0]</code>: <code>cost = (1 - 1) * 1 = 0</code></li>
	<li><code>nums[0..1]</code>: <code>cost = (3 - 1) * 2 = 4</code></li>
	<li><code>nums[0..2]</code>: <code>cost = (3 - 1) * 3 = 6</code></li>
	<li><code>nums[1..1]</code>: <code>cost = (3 - 3) * 1 = 0</code></li>
	<li><code>nums[1..2]</code>: <code>cost = (3 - 2) * 2 = 2</code></li>
	<li><code>nums[2..2]</code>: <code>cost = (2 - 2) * 1 = 0</code></li>
</ul>

<p>There are 5 subarrays whose cost is less than or equal to 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5,5], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>For any subarray of <code>nums</code>, the maximum and minimum values are the same, so the cost is always 0.</p>

<p>As a result, every subarray of <code>nums</code> has cost less than or equal to 0.</p>

<p>For an array of length 4, the total number of subarrays is <code>(4 * 5) / 2 = 10</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The only subarrays of <code>nums</code> with cost 0 are the single-element subarrays, and there are 3 of them.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>15</sup></code></li>
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
