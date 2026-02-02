---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3785.Minimum%20Swaps%20to%20Avoid%20Forbidden%20Values/README_EN.md
rating: 2051
source: Weekly Contest 481 Q3
tags:
    - Greedy
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3785. Minimum Swaps to Avoid Forbidden Values](https://leetcode.com/problems/minimum-swaps-to-avoid-forbidden-values)

[中文文档](/solution/3700-3799/3785.Minimum%20Swaps%20to%20Avoid%20Forbidden%20Values/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>nums</code> and <code>forbidden</code>, each of length <code>n</code>.</p>

<p>You may perform the following operation any number of times (including zero):</p>

<ul>
	<li>Choose two <strong>distinct</strong> indices <code>i</code> and <code>j</code>, and swap <code>nums[i]</code> with <code>nums[j]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of swaps required such that, for every index <code>i</code>, the value of <code>nums[i]</code> is <strong>not equal</strong> to <code>forbidden[i]</code>. If no amount of swaps can ensure that every index avoids its forbidden value, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], forbidden = [3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of swaps:</p>

<ul>
	<li>Select indices <code>i = 0</code> and <code>j = 1</code> in <code>nums</code> and swap them, resulting in <code>nums = [2, 1, 3]</code>.</li>
	<li>After this swap, for every index <code>i</code>, <code>nums[i]</code> is not equal to <code>forbidden[i]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6,6,5], forbidden = [4,6,5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>
One optimal set of swaps:

<ul>
	<li>Select indices <code>i = 0</code> and <code>j = 2</code> in <code>nums</code> and swap them, resulting in <code>nums = [6, 6, 4, 5]</code>.</li>
	<li>Select indices <code>i = 1</code> and <code>j = 3</code> in <code>nums</code> and swap them, resulting in <code>nums = [6, 5, 4, 6]</code>.</li>
	<li>After these swaps, for every index <code>i</code>, <code>nums[i]</code> is not equal to <code>forbidden[i]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,7], forbidden = [8,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>
It is not possible to make <code>nums[i]</code> different from <code>forbidden[i]</code> for all indices.</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2], forbidden = [2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No swaps are required because <code>nums[i]</code> is already different from <code>forbidden[i]</code> for all indices, so the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == forbidden.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], forbidden[i] &lt;= 10<sup>9</sup></code></li>
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
