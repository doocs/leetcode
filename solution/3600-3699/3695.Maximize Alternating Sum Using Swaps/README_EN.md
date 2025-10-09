---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3695.Maximize%20Alternating%20Sum%20Using%20Swaps/README_EN.md
rating: 1984
source: Biweekly Contest 166 Q4
tags:
    - Greedy
    - Union Find
    - Array
    - Sorting
---

<!-- problem:start -->

# [3695. Maximize Alternating Sum Using Swaps](https://leetcode.com/problems/maximize-alternating-sum-using-swaps)

[中文文档](/solution/3600-3699/3695.Maximize%20Alternating%20Sum%20Using%20Swaps/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You want to maximize the <strong>alternating sum</strong> of <code>nums</code>, which is defined as the value obtained by <strong>adding</strong> elements at even indices and <strong>subtracting</strong> elements at odd indices. That is, <code>nums[0] - nums[1] + nums[2] - nums[3]...</code></p>

<p>You are also given a 2D integer array <code>swaps</code> where <code>swaps[i] = [p<sub>i</sub>, q<sub>i</sub>]</code>. For each pair <code>[p<sub>i</sub>, q<sub>i</sub>]</code> in <code>swaps</code>, you are allowed to swap the elements at indices <code>p<sub>i</sub></code> and <code>q<sub>i</sub></code>. These swaps can be performed any number of times and in any order.</p>

<p>Return the maximum possible <strong>alternating sum</strong> of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], swaps = [[0,2],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum alternating sum is achieved when <code>nums</code> is <code>[2, 1, 3]</code> or <code>[3, 1, 2]</code>. As an example, you can obtain <code>nums = [2, 1, 3]</code> as follows.</p>

<ul>
	<li>Swap <code>nums[0]</code> and <code>nums[2]</code>. <code>nums</code> is now <code>[3, 2, 1]</code>.</li>
	<li>Swap <code>nums[1]</code> and <code>nums[2]</code>. <code>nums</code> is now <code>[3, 1, 2]</code>.</li>
	<li>Swap <code>nums[0]</code> and <code>nums[2]</code>. <code>nums</code> is now <code>[2, 1, 3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], swaps = [[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum alternating sum is achieved by not performing any swaps.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1000000000,1,1000000000,1,1000000000], swaps = []</span></p>

<p><strong>Output:</strong> <span class="example-io">-2999999997</span></p>

<p><strong>Explanation:</strong></p>

<p>Since we cannot perform any swaps, the maximum alternating sum is achieved by not performing any swaps.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= swaps.length &lt;= 10<sup>5</sup></code></li>
	<li><code>swaps[i] = [p<sub>i</sub>, q<sub>i</sub>]</code></li>
	<li><code>0 &lt;= p<sub>i</sub> &lt; q<sub>i</sub> &lt;= nums.length - 1</code></li>
	<li><code>[p<sub>i</sub>, q<sub>i</sub>] != [p<sub>j</sub>, q<sub>j</sub>]</code></li>
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
