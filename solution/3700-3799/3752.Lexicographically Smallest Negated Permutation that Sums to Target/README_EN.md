---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3752.Lexicographically%20Smallest%20Negated%20Permutation%20that%20Sums%20to%20Target/README_EN.md
rating: 1827
source: Biweekly Contest 170 Q3
tags:
    - Greedy
    - Array
    - Math
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [3752. Lexicographically Smallest Negated Permutation that Sums to Target](https://leetcode.com/problems/lexicographically-smallest-negated-permutation-that-sums-to-target)

[中文文档](/solution/3700-3799/3752.Lexicographically%20Smallest%20Negated%20Permutation%20that%20Sums%20to%20Target/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code> and an integer <code>target</code>.</p>

<p>Return the <strong><span data-keyword="lexicographically-smaller-array">lexicographically smallest</span></strong> array of integers of size <code>n</code> such that:</p>

<ul>
	<li>The <strong>sum</strong> of its elements equals <code>target</code>.</li>
	<li>The <strong>absolute values</strong> of its elements form a <strong>permutation</strong> of size <code>n</code>.</li>
</ul>

<p>If no such array exists, return an empty array.</p>

<p>A <strong>permutation</strong> of size <code>n</code> is a rearrangement of integers <code>1, 2, ..., n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, target = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[-3,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>The arrays that sum to 0 and whose absolute values form a permutation of size 3 are:</p>

<ul>
	<li><code>[-3, 1, 2]</code></li>
	<li><code>[-3, 2, 1]</code></li>
	<li><code>[-2, -1, 3]</code></li>
	<li><code>[-2, 3, -1]</code></li>
	<li><code>[-1, -2, 3]</code></li>
	<li><code>[-1, 3, -2]</code></li>
	<li><code>[1, -3, 2]</code></li>
	<li><code>[1, 2, -3]</code></li>
	<li><code>[2, -3, 1]</code></li>
	<li><code>[2, 1, -3]</code></li>
	<li><code>[3, -2, -1]</code></li>
	<li><code>[3, -1, -2]</code></li>
</ul>

<p>The lexicographically smallest one is <code>[-3, 1, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, target = 10000000000</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no arrays that sum to <span class="example-io">10000000000 and whose absolute values form a permutation of size 1. Therefore, the answer is <code>[]</code>.</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>10</sup> &lt;= target &lt;= 10<sup>10</sup></code></li>
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
