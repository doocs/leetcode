---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4067. Longest Subarray With Restricted Pair Sums](https://leetcode.com/problems/longest-subarray-with-restricted-pair-sums)

[中文文档](/solution/4000-4099/4067.Longest%20Subarray%20With%20Restricted%20Pair%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong>subarray</strong> <code>nums[l..r]</code> is valid if there are no three <strong>distinct</strong> indices <code>i</code>, <code>j</code>, and <code>k</code> such that <code>l &lt;= i, j, k &lt;= r</code> and:</p>

<ul>
	<li><code>nums[i] + nums[j] == nums[k]</code></li>
</ul>

<p>Return the <strong>maximum</strong> length of a valid subarray of <code>nums</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Consider the subarray <code>[3, 5, 3]</code>. The pairs of elements at distinct indices have the following sums:</p>

<ul>
	<li><code>3 + 5 = 8</code></li>
	<li><code>3 + 3 = 6</code>, using the two different occurrences of 3</li>
	<li><code>5 + 3 = 8</code></li>
</ul>

<p>None of these sums is an element at the remaining index, so the subarray is valid.</p>

<p>Every subarray of length 4 contains 2, 3, and 5 at distinct indices, where <code>2 + 3 = 5</code>. Therefore, no longer valid subarray exists, and the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The sums obtained from every pair of elements at distinct indices are 7, 8, 9, 9, 10, and 11. None of these values appears at the remaining index, so the entire array is valid.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 500</code></li>
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
