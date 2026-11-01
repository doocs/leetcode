---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4099. Minimum Operations to Sort With Locked Indices](https://leetcode.com/problems/minimum-operations-to-sort-with-locked-indices)

[中文文档](/solution/4000-4099/4099.Minimum%20Operations%20to%20Sort%20With%20Locked%20Indices/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <strong><span data-keyword="permutation-array">permutation</span></strong> of the integers in the range <code>[1, n]</code>.</p>

<p>You are also given a <span data-keyword="binary-array"><strong>binary</strong> array</span> <code>locked</code> of length <code>n</code> and an integer <code>k</code>.</p>

<ul>
	<li><code>locked[i] = 1</code> means index <code>i</code> is locked.</li>
	<li><code>locked[i] = 0</code> means index <code>i</code> is unlocked.</li>
</ul>

<p>You may perform the following operations in <strong>any order</strong>:</p>

<ul>
	<li><strong>Swap:</strong> Choose an index <code>i</code> such that both indices <code>i</code> and <code>i + 1</code> are currently unlocked, and swap <code>nums[i]</code> and <code>nums[i + 1]</code>.</li>
	<li><strong>Unlock:</strong> Choose an index <code>i</code> such that <code>locked[i] = 1</code> and <strong>permanently</strong> unlock it by setting <code>locked[i] = 0</code>.</li>
</ul>

<p>You may perform any number of swap operations but <strong>at most</strong> <code>k</code> unlock operations.</p>

<p>Return the <strong>minimum total number of operations</strong> required to sort <code>nums</code> in <strong><span data-keyword="strictly-increasing-array">strictly increasing</span></strong> order. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,4], locked = [0,1,0,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Unlock index 1. The array <code>locked</code> becomes <code>[0,0,0,1]</code>.</li>
	<li>Swap <code>nums[0]</code> and <code>nums[1]</code>. The array <code>nums</code> becomes <code>[1,2,3,4]</code>.</li>
</ul>

<p>The total number of operations is <code>1 + 1 = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2], locked = [1,0,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Unlock indices 0 and 2. All indices are now unlocked.</li>
	<li>Swap indices 0 and 1. The array <code>nums</code> becomes <code>[1,3,2]</code>.</li>
	<li>Swap indices 1 and 2. The array <code>nums</code> becomes <code>[1,2,3]</code>.</li>
</ul>

<p>The total number of operations is <code>2 + 2 = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,1], locked = [1,0,0], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Index 0 is locked, so its value cannot be moved. Since <code>k = 0</code>, no indices can be unlocked. Therefore, sorting the array is impossible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == locked.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>0 &lt;= locked[i] &lt;= 1</code></li>
	<li><code>nums</code> is a permutation of the integers in the range <code>[1, n]</code>.</li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<p>&nbsp;</p>
Can you solve this in <code>O(nlog(n))</code>?

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
