---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README_EN.md
---

<!-- problem:start -->

# [3555. Smallest Subarray to Sort in Every Sliding Window 🔒](https://leetcode.com/problems/smallest-subarray-to-sort-in-every-sliding-window)

[中文文档](/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>For each contiguous <span data-keyword="subarray">subarray</span> of length <code>k</code>, determine the <strong>minimum</strong> length of a continuous segment that must be sorted so that the entire window becomes <strong>non‑decreasing</strong>; if the window is already sorted, its required length is zero.</p>

<p>Return an array of length <code>n &minus; k + 1</code> where each element corresponds to the answer for its window.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0...2] = [1, 3, 2]</code>. Sort <code>[3, 2]</code> to get <code>[1, 2, 3]</code>, the answer is 2.</li>
	<li><code>nums[1...3] = [3, 2, 4]</code>. Sort <code>[3, 2]</code> to get <code>[2, 3, 4]</code>, the answer is 2.</li>
	<li><code>nums[2...4] = [2, 4, 5]</code> is already sorted, so the answer is 0.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,3,2,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0...3] = [5, 4, 3, 2]</code>. The whole subarray must be sorted, so the answer is 4.</li>
	<li><code>nums[1...4] = [4, 3, 2, 1]</code>. The whole subarray must be sorted, so the answer is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
