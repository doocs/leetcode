---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3796.Find%20Maximum%20Value%20in%20a%20Constrained%20Sequence/README_EN.md
rating: 1832
source: Biweekly Contest 173 Q3
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [3796. Find Maximum Value in a Constrained Sequence](https://leetcode.com/problems/find-maximum-value-in-a-constrained-sequence)

[中文文档](/solution/3700-3799/3796.Find%20Maximum%20Value%20in%20a%20Constrained%20Sequence/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, a 2D integer array <code>restrictions</code>, and an integer array <code>diff</code> of length <code>n - 1</code>. Your task is to construct a sequence of length <code>n</code>, denoted by <code>a[0], a[1], ..., a[n - 1]</code>, such that it satisfies the following conditions:</p>

<ul>
	<li><code>a[0]</code> is 0.</li>
	<li>All elements in the sequence are <strong>non-negative</strong>.</li>
	<li>For every index <code>i</code> (<code>0 &lt;= i &lt;= n - 2</code>), <code>abs(a[i] - a[i + 1]) &lt;= diff[i]</code>.</li>
	<li>For each <code>restrictions[i] = [idx, maxVal]</code>, the value at position <code>idx</code> in the sequence must not exceed <code>maxVal</code> (i.e., <code>a[idx] &lt;= maxVal</code>).</li>
</ul>

<p>Your goal is to construct a valid sequence that <strong>maximizes</strong> the <strong>largest</strong> value within the sequence while satisfying all the above conditions.</p>

<p>Return an integer denoting the <strong>largest</strong> value present in such an optimal sequence.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10, restrictions = [[3,1],[8,1]], diff = [2,2,3,1,4,5,1,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sequence <code>a = [0, 2, 4, 1, 2, 6, 2, 1, 1, 3]</code> satisfies the given constraints (<code>a[3] &lt;= 1</code> and <code>a[8] &lt;= 1</code>).</li>
	<li>The maximum value in the sequence is 6.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 8, restrictions = [[3,2]], diff = [3,5,2,4,2,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sequence <code>a = [0, 3, 3, 2, 6, 8, 11, 12]</code> satisfies the given constraints (<code>a[3] &lt;= 2</code>).</li>
	<li>The maximum value in the sequence is 12.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= restrictions.length &lt;= n - 1</code></li>
	<li><code>restrictions[i].length == 2</code></li>
	<li><code>restrictions[i] = [idx, maxVal]</code></li>
	<li><code>1 &lt;= idx &lt; n</code></li>
	<li><code>1 &lt;= maxVal &lt;= 10<sup>6</sup></code></li>
	<li><code>diff.length == n - 1</code></li>
	<li><code>1 &lt;= diff[i] &lt;= 10</code></li>
	<li>The values of <code>restrictions[i][0]</code> are unique.</li>
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
