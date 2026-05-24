---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3938.Maximum%20Path%20Intersection%20Sum%20in%20a%20Grid/README_EN.md
---

<!-- problem:start -->

# [3938. Maximum Path Intersection Sum in a Grid](https://leetcode.com/problems/maximum-path-intersection-sum-in-a-grid)

[中文文档](/solution/3900-3999/3938.Maximum%20Path%20Intersection%20Sum%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p data-end="139" data-start="64">You are given an <code>m x n</code> integer matrix <code>grid</code>.</p>

<p>Two players move across the grid:</p>

<ul>
	<li>Player 1 starts at the top-left cell <code>(0, 0)</code> and can move only right or down. Their destination is the bottom-right cell <code>(m - 1, n - 1)</code>.</li>
	<li>Player 2 starts at the bottom-left cell <code>(m - 1, 0)</code> and can move only right or up. Their destination is the top-right cell <code>(0, n - 1)</code>.</li>
</ul>

<p>Each player must choose a valid path from their respective starting cell to their destination.</p>

<p>A cell is called <strong>shared</strong> if it belongs to <strong>both</strong> chosen paths.</p>

<p>Return an integer denoting the <strong>maximum</strong> possible sum of values of all <strong>shared</strong> cells.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3938.Maximum%20Path%20Intersection%20Sum%20in%20a%20Grid/images/image.png" style="width: 200px; height: 251px;" />​​​​​​​​​​​​​​​​​​​​​
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,0,-3],[1,-2,1,0],[-4,2,-1,3],[3,-3,3,-2],[-1,-5,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>
The diagram shows one optimal choice of paths.

<ul>
	<li>Player 1 follows the red/purple path from the top-left cell to the bottom-right cell:
	<ul>
		<li><code>(0, 0) &rarr; (1, 0) &rarr; (2, 0) &rarr; (2, 1) &rarr; (2, 2) &rarr; (2, 3) &rarr; (3, 3) &rarr; (4, 3)</code></li>
	</ul>
	</li>
	<li>Player 2 follows the blue/purple path from the bottom-left cell to the top-right cell:
	<ul>
		<li><code>(4, 0) &rarr; (4, 1) &rarr; (3, 1) &rarr; (2, 1) &rarr; (2, 2) &rarr; (2, 3) &rarr; (1, 3) &rarr; (0, 3)</code></li>
	</ul>
	</li>
	<li>The shared cells are <code>(2, 1)</code>, <code>(2, 2)</code>, and <code>(2, 3)</code>.</li>
	<li>The sum is <code>2 + (-1) + 3 = 4</code>, which is the maximum possible sum.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3938.Maximum%20Path%20Intersection%20Sum%20in%20a%20Grid/images/chatgpt-image-may-19-2026-01_39_39-pm.png" style="width: 200px; height: 200px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[4,-2,-3],[-1,-3,-1],[-4,2,-1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal pair of paths is shown in the diagram.</p>

<ul>
	<li>Player 1 follows the red/purple path:
	<ul>
		<li><code>(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (1, 2) &rarr; (2, 2)</code></li>
	</ul>
	</li>
	<li>Player 2 follows the blue/purple path:
	<ul>
		<li><code>(2, 0) &rarr; (1, 0) &rarr; (0, 0) &rarr; (0, 1) &rarr; (0, 2)</code></li>
	</ul>
	</li>
	<li>The shared cells are <code>(0, 0)</code> and <code>(1, 0)</code>.</li>
	<li>The sum is <code>4 + (-1) = 3</code>, which is the maximum possible.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>-100 &lt;= grid[i][j] &lt;= 100</code></li>
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
