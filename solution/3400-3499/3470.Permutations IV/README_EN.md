---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3470.Permutations%20IV/README_EN.md
rating: 2473
source: Biweekly Contest 151 Q4
tags:
    - Array
    - Math
    - Combinatorics
    - Enumeration
---

<!-- problem:start -->

# [3470. Permutations IV](https://leetcode.com/problems/permutations-iv)

[中文文档](/solution/3400-3499/3470.Permutations%20IV/README.md)

## Description

<!-- description:start -->

<p>Given two integers, <code>n</code> and <code>k</code>, an <strong>alternating permutation</strong> is a permutation of the first <code>n</code> positive integers such that no <strong>two</strong> adjacent elements are both odd or both even.</p>

<p>Return the <strong>k-th</strong> <strong>alternating permutation</strong> sorted in <em>lexicographical order</em>. If there are fewer than <code>k</code> valid <strong>alternating permutations</strong>, return an empty list.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2, 3, 4]</code> are:</p>

<ol>
	<li><code>[1, 2, 3, 4]</code></li>
	<li><code>[1, 4, 3, 2]</code></li>
	<li><code>[2, 1, 4, 3]</code></li>
	<li><code>[2, 3, 4, 1]</code></li>
	<li><code>[3, 2, 1, 4]</code></li>
	<li><code>[3, 4, 1, 2]</code> &larr; 6th permutation</li>
	<li><code>[4, 1, 2, 3]</code></li>
	<li><code>[4, 3, 2, 1]</code></li>
</ol>

<p>Since <code>k = 6</code>, we return <code>[3, 4, 1, 2]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2, 3]</code> are:</p>

<ol>
	<li><code>[1, 2, 3]</code></li>
	<li><code>[3, 2, 1]</code> &larr; 2nd permutation</li>
</ol>

<p>Since <code>k = 2</code>, we return <code>[3, 2, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically-sorted alternating permutations of <code>[1, 2]</code> are:</p>

<ol>
	<li><code>[1, 2]</code></li>
	<li><code>[2, 1]</code></li>
</ol>

<p>There are only 2 alternating permutations, but <code>k = 3</code>, which is out of range. Thus, we return an empty list <code>[]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
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
