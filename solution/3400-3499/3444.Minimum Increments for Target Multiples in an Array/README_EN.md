---
comments: true
difficulty: Hard
rating: 2336
source: Weekly Contest 435 Q3
tags:
    - Bit Manipulation
    - Array
    - Math
    - Dynamic Programming
    - Bitmask
    - Number Theory
---

<!-- problem:start -->

# [3444. Minimum Increments for Target Multiples in an Array](https://leetcode.com/problems/minimum-increments-for-target-multiples-in-an-array)

[中文文档](/solution/3400-3499/3444.Minimum%20Increments%20for%20Target%20Multiples%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given two arrays, <code>nums</code> and <code>target</code>.</p>

<p>In a single operation, you may increment any element of <code>nums</code> by 1.</p>

<p>Return <strong>the minimum number</strong> of operations required so that each element in <code>target</code> has <strong>at least</strong> one multiple in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], target = [4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum number of operations required to satisfy the condition is 1.</p>

<ul>
	<li>Increment 3 to 4 with just one operation, making 4 a multiple of itself.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,4], target = [10,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum number of operations required to satisfy the condition is 2.</p>

<ul>
	<li>Increment 8 to 10 with 2 operations, making 10 a multiple of both 5 and 10.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,9,10], target = [7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Target 7 already has a multiple in nums, so no additional operations are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= target.length &lt;= 4</code></li>
	<li><code>target.length &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Every target must divide at least one array element; we may only increment. There are few targets and up to $10^5$ elements.
>
> One element may cover several targets by rising to a multiple of their LCM. We compute that increment per subset, then cover the targets across elements.
>
> Bitmask DP: $f[s]$ is the minimum increment to cover set $s$. Each element offers a cost for every subset $t$ and relaxes $f$. This is practical for $|target|\le 4$.

<!-- thinking:end -->

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
