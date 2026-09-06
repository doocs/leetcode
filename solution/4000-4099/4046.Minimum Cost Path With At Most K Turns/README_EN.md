---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4046.Minimum%20Cost%20Path%20With%20At%20Most%20K%20Turns/README_EN.md
---

<!-- problem:start -->

# [4046. Minimum Cost Path With At Most K Turns](https://leetcode.com/problems/minimum-cost-path-with-at-most-k-turns)

[中文文档](/solution/4000-4099/4046.Minimum%20Cost%20Path%20With%20At%20Most%20K%20Turns/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>grid</code> of size <code>m x n</code>, where <code>grid[i][j]</code> represents the cost of visiting cell <code>(i, j)</code>, and an integer <code>k</code>.</p>

<p>You start at the <strong>top-left</strong> cell <code>(0, 0)</code> and want to reach the <strong>bottom-right</strong> cell <code>(m - 1, n - 1)</code>.</p>

<p>From each cell, you may move one step in any of the four directions: <strong>up</strong>, <strong>down</strong>, <strong>left</strong>, or <strong>right</strong>.</p>

<p>The cost of a path is the sum of the values of all visited cells, <strong>including</strong> the starting and ending cells. If a cell is visited more than once, its value is included each time it is visited.</p>

<p>Return the <strong>minimum</strong> possible path cost to reach <code>(m - 1, n - 1)</code> using <strong>at most</strong> <code>k</code> turns. If no such path exists, return <code>-1</code>.</p>

<p>A <strong>turn</strong> occurs when the direction changes between two consecutive moves. For example, moving right and then down counts as one turn, while moving right and then right does not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,7,3],[1,4,5]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>An optimal path is <code>(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (1, 2)</code>. The moves are down, right, right.</li>
	<li>The direction changes from down to right once, so the path uses exactly <code>k = 1</code> turn.</li>
	<li>The total path cost is <code>2 + 1 + 4 + 5 = 12</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[4,1,9],[3,2,5],[4,8,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>An optimal path is <code>(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (1, 2) &rarr; (2, 2)</code>. The moves are down, right, right, down.</li>
	<li>The direction changes from down to right and from right to down, so the path uses exactly <code>k = 2</code> turns.</li>
	<li>The total path cost is <code>4 + 3 + 2 + 5 + 6 = 20</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,9],[3,4]], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>It is impossible to reach <code>(1, 1)</code> using <code>k = 0</code> turns. Thus, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 75</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 75</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt; min(m, n)</code></li>
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
