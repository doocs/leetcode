---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3797.Count%20Routes%20to%20Climb%20a%20Rectangular%20Grid/README_EN.md
rating: 2375
source: Biweekly Contest 173 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3797. Count Routes to Climb a Rectangular Grid](https://leetcode.com/problems/count-routes-to-climb-a-rectangular-grid)

[中文文档](/solution/3700-3799/3797.Count%20Routes%20to%20Climb%20a%20Rectangular%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a string array <code>grid</code> of size <code>n</code>, where each string <code>grid[i]</code> has length <code>m</code>. The character <code>grid[i][j]</code> is one of the following symbols:</p>

<ul>
	<li><code>&#39;.&#39;</code>: The cell is available.</li>
	<li><code>&#39;#&#39;</code>: The cell is blocked.</li>
</ul>

<p>You want to count the number of different routes to climb <code>grid</code>. Each route must start from <em>any cell</em> in the bottom row (row <code>n - 1</code>) and end in the top row (row 0).</p>

<p>However, there are some constraints on the route.</p>

<ul>
	<li>You can only move from one available cell to <strong>another</strong> available cell.</li>
	<li>The <strong>Euclidean distance</strong> of each move is <strong>at most</strong> <code>d</code>, where <code>d</code> is an integer parameter given to you. The Euclidean distance between two cells <code>(r1, c1)</code>, <code>(r2, c2)</code> is <code>sqrt((r1 - r2)<sup>2</sup> + (c1 - c2)<sup>2</sup>)</code>.</li>
	<li>Each move either stays on the same row or moves to the row directly above (from row <code>r</code> to <code>r - 1</code>).</li>
	<li>You cannot stay on the same row for two consecutive turns. If you stay on the same row in a move (and this move is not the last move), your next move must go to the row above.</li>
</ul>

<p>Return an integer denoting the number of such routes. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [&quot;..&quot;,&quot;#.&quot;], d = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>We label the cells we visit in the routes sequentially, starting from 1. The two routes are:</p>

<pre>
.2
#1
</pre>

<pre>
32
#1
</pre>

<p>We can move from the cell (1, 1) to the cell (0, 1) because the Euclidean distance is <code>sqrt((1 - 0)<sup>2</sup> + (1 - 1)<sup>2</sup>) = sqrt(1) &lt;= d</code>.</p>

<p>However, we cannot move from the cell (1, 1) to the cell (0, 0) because the Euclidean distance is <code>sqrt((1 - 0)<sup>2</sup> + (1 - 0)<sup>2</sup>) = sqrt(2) &gt; d</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [&quot;..&quot;,&quot;#.&quot;], d = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Two of the routes are given in example 1. The other two routes are:</p>

<pre>
2.
#1
</pre>

<pre>
23
#1
</pre>

<p>Note that we can move from (1, 1) to (0, 0) because the Euclidean distance is <code>sqrt(2) &lt;= d</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [&quot;#&quot;], d = 750</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>We cannot choose any cell as the starting cell. Therefore, there are no routes.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [&quot;..&quot;], d = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible routes are:</p>

<pre>
.1
</pre>

<pre>
1.
</pre>

<pre>
12
</pre>

<pre>
21
</pre>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length &lt;= 750</code></li>
	<li><code>1 &lt;= m == grid[i].length &lt;= 750</code></li>
	<li><code>grid[i][j]</code> is <code>&#39;.&#39;</code> or <code>&#39;#&#39;</code>.</li>
	<li><code>1 &lt;= d &lt;= 750</code></li>
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
