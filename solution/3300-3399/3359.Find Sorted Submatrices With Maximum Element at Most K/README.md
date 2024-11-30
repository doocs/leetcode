---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/README.md
---

<!-- problem:start -->

# [3359. 查找最大元素不超过 K 的有序子矩阵 🔒](https://leetcode.cn/problems/find-sorted-submatrices-with-maximum-element-at-most-k)

[English Version](/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个大小为&nbsp;<code>m x n</code>&nbsp;的二维矩阵&nbsp;<code>grid</code>。同时给定一个 <strong>非负整数</strong>&nbsp;<code>k</code>。</p>

<p>返回满足下列条件的&nbsp;<code>grid</code>&nbsp;的子矩阵数量：</p>

<ul>
	<li>子矩阵中最大的元素 <b>小于等于</b>&nbsp;<code>k</code>。</li>
	<li>子矩阵的每一行都以 <strong>非递增</strong> 顺序排序。</li>
</ul>

<p>矩阵的子矩阵&nbsp;<code>(x1, y1, x2, y2)</code>&nbsp;是通过选择所有满足&nbsp;<code>x1 &lt;= x &lt;= x2</code>&nbsp;且&nbsp;<code>y1 &lt;= y &lt;= y2</code>&nbsp;的&nbsp;<code>grid[x][y]</code> 元素组成的矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[4,3,2,1],[8,7,6,1]], k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3359.Find%20Sorted%20Submatrices%20With%20Maximum%20Element%20at%20Most%20K/images/mine.png" style="width: 360px; height: 200px;" /></strong></p>

<p>8 个子矩阵分别是：</p>

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

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,1,1],[1,1,1],[1,1,1]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>36</span></p>

<p><strong>解释：</strong></p>

<p>矩阵中有 36 个子矩阵。所有子矩阵的最大元素都等于 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

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
