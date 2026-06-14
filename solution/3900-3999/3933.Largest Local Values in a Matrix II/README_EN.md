---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3933.Largest%20Local%20Values%20in%20a%20Matrix%20II/README_EN.md
rating: 2052
source: Weekly Contest 502 Q3
tags:
    - Array
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3933. Largest Local Values in a Matrix II](https://leetcode.com/problems/largest-local-values-in-a-matrix-ii)

[中文文档](/solution/3900-3999/3933.Largest%20Local%20Values%20in%20a%20Matrix%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>n x m</code> integer matrix <code>matrix</code> containing non-negative integers.</p>

<p>A <strong>non-zero </strong>cell <code>(row, col)</code> checks the cells near it as follows:</p>

<ul>
	<li>Let <code>x = matrix[row][col]</code>.</li>
	<li>Consider every cell within <code>x</code> rows and <code>x</code> columns of <code>(row, col)</code>.</li>
	<li>Ignore cells that are outside the matrix.</li>
	<li>Ignore the&nbsp;cells where both the row distance and column distance are exactly <code>x</code>.</li>
</ul>

<p>The cell <code>(row, col)</code> is a <strong>local maximum</strong> if it is <strong>non-zero</strong> and no considered cell has a value <strong>greater than</strong> <code>x</code>.</p>

<p>Return an integer denoting the number of <strong>local maximums</strong> in <code>matrix</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">​​​​​​​Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,2,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3933.Largest%20Local%20Values%20in%20a%20Matrix%20II/images/chatgpt-image-may-14-2026-01_53_19-am.png" style="width: 300px; height: 300px;" />​​​​​​​​​​​​​​​​​​​​​</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For the non-zero cell <code>(3, 3)</code>, <code>x = matrix[3][3] = 2</code>.</li>
	<li>The highlighted cells are the considered cells within <code>x</code> rows and <code>x</code> columns of <code>(3, 3)</code>.</li>
	<li>The four cells with both row and column distances equal to <code>x = 2</code> are ignored.</li>
	<li>No considered cell has a value greater than 2, so <code>(3, 3)</code> is a local maximum.</li>
	<li>There are no other non-zero cells, so the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Only the cell with value 4 is a local maximum. Every other non-zero cell considers a cell with a greater value.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[1,0,1],[0,1,0],[1,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For a cell with value 1, the considered cells are the cell itself and its 4-directionally adjacent cells that are inside the matrix.</li>
	<li>Each of the five cells with value 1 only considers cells with values 0 or 1, so all five of them are local maximums.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [[1,1],[1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>All cells have the same value. Therefore, no cell considers another cell with a greater value, so all 4 cells are local maximums.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == matrix.length &lt;= 200</code></li>
	<li><code>1 &lt;= m == matrix[i].length &lt;= 200</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 200</code></li>
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
