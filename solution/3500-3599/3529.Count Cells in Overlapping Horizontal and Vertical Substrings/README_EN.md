---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/README_EN.md
tags:
    - Array
    - String
    - Matrix
    - String Matching
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3529. Count Cells in Overlapping Horizontal and Vertical Substrings](https://leetcode.com/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings)

[中文文档](/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> consisting of characters and a string <code>pattern</code>.</p>

<p>A <strong data-end="264" data-start="240">horizontal substring</strong> is a contiguous sequence of characters read from left to right. If the end of a row is reached before the substring is complete, it wraps to the first column of the next row and continues as needed. You do <strong>not</strong> wrap from the bottom row back to the top.</p>

<p>A <strong data-end="484" data-start="462">vertical substring</strong> is a contiguous sequence of characters read from top to bottom. If the bottom of a column is reached before the substring is complete, it wraps to the first row of the next column and continues as needed. You do <strong>not</strong> wrap from the last column back to the first.</p>

<p>Count the number of cells in the matrix that satisfy the following condition:</p>

<ul>
	<li>The cell must be part of <strong>at least</strong> one horizontal substring and <strong>at least</strong> one vertical substring, where <strong>both</strong> substrings are equal to the given <code>pattern</code>.</li>
</ul>

<p>Return the count of these cells.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/images/gridtwosubstringsdrawio.png" style="width: 150px; height: 187px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;a&quot;,&quot;a&quot;,&quot;c&quot;,&quot;c&quot;],[&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;c&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;c&quot;,&quot;a&quot;,&quot;a&quot;,&quot;c&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]], pattern = &quot;abaca&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The pattern <code>&quot;abaca&quot;</code> appears once as a horizontal substring (colored blue) and once as a vertical substring (colored red), intersecting at one cell (colored purple).</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3529.Count%20Cells%20in%20Overlapping%20Horizontal%20and%20Vertical%20Substrings/images/gridexample2fixeddrawio.png" style="width: 150px; height: 150px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;c&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;b&quot;,&quot;b&quot;,&quot;a&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;a&quot;]], pattern = &quot;aba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The cells colored above are all part of at least one horizontal and one vertical substring matching the pattern <code>&quot;aba&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;a&quot;]], pattern = &quot;a&quot;</span></p>

<p><strong>Output:</strong> 1</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= pattern.length &lt;= m * n</code></li>
	<li><code>grid</code> and <code>pattern</code> consist of only lowercase English letters.</li>
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
