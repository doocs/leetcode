---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/README.md
---

<!-- problem:start -->

# [3359. Find Sorted Submatrices With Maximum Element at Most K 🔒](https://leetcode.cn/problems/find-sorted-submatrices-with-maximum-element-at-most-k)

[English Version](/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a 2D matrix <code>grid</code> of size <code>m x n</code>. You are also given a <strong>non-negative</strong> integer <code>k</code>.</p>

<p>Return the number of <strong>submatrices</strong> of <code>grid</code> that satisfy the following conditions:</p>

<ul>
	<li>The maximum element in the submatrix <strong>less than or equal to</strong> <code>k</code>.</li>
	<li>Each row in the submatrix is sorted in <strong>non-increasing</strong> order.</li>
</ul>

<p>A submatrix <code>(x1, y1, x2, y2)</code> is a matrix that forms by choosing all cells <code>grid[x][y]</code> where <code>x1 &lt;= x &lt;= x2</code> and <code>y1 &lt;= y &lt;= y2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[4,3,2,1],[8,7,6,1]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/images/mine.png" style="width: 360px; height: 200px;" /></strong></p>

<p>The 8 submatrices are:</p>

<ul>
	<li><code>[[1]]</code></li>
	<li><code>[[1]]</code></li>
	<li><code>[[2,1]]</code></li>
	<li><code>[[3,2,1]]</code></li>
	<li><code>[[1],[1]]</code></li>
	<li><code>[[2]]</code></li>
	<li><code>[[3]]</code></li>
	<li><code>[[3,2]]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,1,1],[1,1,1],[1,1,1]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">36</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 36 submatrices of grid. All submatrices have their maximum element equal to 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
​​​​​​

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
