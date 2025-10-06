---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3632.Subarrays%20with%20XOR%20at%20Least%20K/README_EN.md
tags:
    - Bit Manipulation
    - Tree
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [3632. Subarrays with XOR at Least K 🔒](https://leetcode.com/problems/subarrays-with-xor-at-least-k)

[中文文档](/solution/3600-3699/3632.Subarrays%20with%20XOR%20at%20Least%20K/README.md)

## Description

<!-- description:start -->

<p>Given an array of positive integers <code data-end="114" data-start="109">nums</code> of length <code data-end="128" data-start="125">n</code> and a non‑negative integer <code data-end="159" data-start="156">k</code>.</p>

<p>Return the number of <strong>contiguous <span data-keyword="subarray">subarrays</span></strong> whose bitwise XOR of all elements is <strong>greater</strong> than or <strong>equal</strong> to <code data-end="268" data-start="265">k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid subarrays with <code>XOR &gt;= 2</code> are <code>[3]</code> at index 0, <code>[3, 1]</code> at indices 0 - 1, <code>[3, 1, 2, 3]</code> at indices 0 - 3, <code>[1, 2]</code> at indices 1 - 2, <code>[2]</code> at index 2, and <code>[3]</code> at index 3; there are 6 in total.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,0,0], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Every contiguous subarray yields <code>XOR = 0</code>, which meets <code>k = 0</code>. There are 6 such subarrays in total.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="49" data-start="21"><code data-end="47" data-start="21">1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="76" data-start="52"><code data-end="74" data-start="52">0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li data-end="97" data-start="79"><code data-end="95" data-start="79">0 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
