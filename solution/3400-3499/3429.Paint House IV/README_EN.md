---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3429.Paint%20House%20IV/README_EN.md
rating: 2165
source: Weekly Contest 433 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3429. Paint House IV](https://leetcode.com/problems/paint-house-iv)

[中文文档](/solution/3400-3499/3429.Paint%20House%20IV/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>even</strong> integer <code>n</code> representing the number of houses arranged in a straight line, and a 2D array <code>cost</code> of size <code>n x 3</code>, where <code>cost[i][j]</code> represents the cost of painting house <code>i</code> with color <code>j + 1</code>.</p>

<p>The houses will look <strong>beautiful</strong> if they satisfy the following conditions:</p>

<ul>
	<li>No <strong>two</strong> adjacent houses are painted the same color.</li>
	<li>Houses <strong>equidistant</strong> from the ends of the row are <strong>not</strong> painted the same color. For example, if <code>n = 6</code>, houses at positions <code>(0, 5)</code>, <code>(1, 4)</code>, and <code>(2, 3)</code> are considered equidistant.</li>
</ul>

<p>Return the <strong>minimum</strong> cost to paint the houses such that they look <strong>beautiful</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, cost = [[3,5,7],[6,2,9],[4,8,1],[7,3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal painting sequence is <code>[1, 2, 3, 2]</code> with corresponding costs <code>[3, 2, 1, 3]</code>. This satisfies the following conditions:</p>

<ul>
	<li>No adjacent houses have the same color.</li>
	<li>Houses at positions 0 and 3 (equidistant from the ends) are not painted the same color <code>(1 != 2)</code>.</li>
	<li>Houses at positions 1 and 2 (equidistant from the ends) are not painted the same color <code>(2 != 3)</code>.</li>
</ul>

<p>The minimum cost to paint the houses so that they look beautiful is <code>3 + 2 + 1 + 3 = 9</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, cost = [[2,4,6],[5,3,8],[7,1,9],[4,6,2],[3,5,7],[8,2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal painting sequence is <code>[1, 3, 2, 3, 1, 2]</code> with corresponding costs <code>[2, 8, 1, 2, 3, 2]</code>. This satisfies the following conditions:</p>

<ul>
	<li>No adjacent houses have the same color.</li>
	<li>Houses at positions 0 and 5 (equidistant from the ends) are not painted the same color <code>(1 != 2)</code>.</li>
	<li>Houses at positions 1 and 4 (equidistant from the ends) are not painted the same color <code>(3 != 1)</code>.</li>
	<li>Houses at positions 2 and 3 (equidistant from the ends) are not painted the same color <code>(2 != 3)</code>.</li>
</ul>

<p>The minimum cost to paint the houses so that they look beautiful is <code>2 + 8 + 1 + 2 + 3 + 2 = 18</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>cost.length == n</code></li>
	<li><code>cost[i].length == 3</code></li>
	<li><code>0 &lt;= cost[i][j] &lt;= 10<sup>5</sup></code></li>
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
