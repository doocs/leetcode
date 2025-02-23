---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README.md
---

<!-- problem:start -->

# [3462. 提取至多 K 个元素的最大总和](https://leetcode.cn/problems/maximum-sum-with-at-most-k-elements)

[English Version](/solution/3400-3499/3462.Maximum%20Sum%20With%20at%20Most%20K%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-pm-slice="1 3 []">给你一个大小为 <code>n x m</code>&nbsp;的二维矩阵&nbsp;<code>grid</code>&nbsp;，以及一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>limits</code>&nbsp;，和一个整数&nbsp;<code>k</code>&nbsp;。你的目标是从矩阵 <code>grid</code> 中提取出&nbsp;<strong>至多</strong> <code>k</code>&nbsp;个元素，并计算这些元素的最大总和，提取时需满足以下限制<b>：</b></p>

<ul data-spread="false">
	<li>
	<p>从 <code>grid</code>&nbsp;的第 <code>i</code> 行提取的元素数量不超过 <code>limits[i]</code> 。</p>
	</li>
</ul>

<p data-pm-slice="1 1 []">返回最大总和。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,2],[3,4]], limits = [1,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>7</span></p>

<p><b>解释：</b></p>

<ul>
	<li>从第 2 行提取至多 2 个元素，取出 4 和 3 。</li>
	<li>至多提取 2 个元素时的最大总和&nbsp;<code>4 + 3 = 7</code>&nbsp;。</li>
</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">21</span></p>

<p><b>解释：</b></p>

<ul>
	<li>从第 1&nbsp;行提取至多 2 个元素，取出 7 。</li>
	<li>从第 2 行提取至多 2 个元素，取出&nbsp;8 和 6 。</li>
	<li>至多提取 3&nbsp;个元素时的最大总和 <code>7 + 8 + 6 = 21</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>n == grid.length == limits.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= limits[i] &lt;= m</code></li>
	<li><code>0 &lt;= k &lt;= min(n * m, sum(limits))</code></li>
</ul>

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
