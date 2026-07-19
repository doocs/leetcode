---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3989.Maximum%20Consistent%20Columns%20in%20a%20Grid/README_EN.md
rating: 2013
source: Weekly Contest 510 Q4
---

<!-- problem:start -->

# [3989. Maximum Consistent Columns in a Grid](https://leetcode.com/problems/maximum-consistent-columns-in-a-grid)

[中文文档](/solution/3900-3999/3989.Maximum%20Consistent%20Columns%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>grid</code> of size <code>m x n</code>, and an integer <code>limit</code>.</p>

<p>You may remove zero or more columns from the grid, but at least one column must remain. The <strong>relative</strong> order of the remaining columns must be preserved.</p>

<p>A grid is called <strong>consistent</strong> if for every row <code>i</code>, and for every pair of adjacent remaining columns <code>a</code> and <code>b</code> with <code>a &lt; b</code>, the following holds: <code>|grid[i][b] - grid[i][a]| &lt;= limit</code>.</p>

<p>Return the <strong>maximum</strong> number of columns that can remain such that the resulting grid is <strong>consistent</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[-2,0,3]], limit = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove column 2 and keep columns 0 and 1, which gives <code>|grid[0][1] &minus; grid[0][0]| = |0 &minus; (&minus;2)| = 2 &lt;= limit</code>.</li>
	<li>Thus, the maximum number of columns that can remain is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,-1,1],[2,2,2]], limit = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove column 1 and keep columns 0 and 2, which gives
	<ul>
		<li><code>|grid[0][2] &minus; grid[0][0]| = |1 &minus; 1| = 0 &lt;= limit</code> and</li>
		<li><code>|grid[1][2] &minus; grid[1][0]| = |2 &minus; 2| = 0 &lt;= limit</code>.</li>
	</ul>
	</li>
	<li>Thus, the maximum number of columns that can remain is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[-5,5]], limit = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove either column 0 or column 1, since <code>|grid[0][1] &minus; grid[0][0]| = |5 &minus; (&minus;5)| = 10 &gt; limit</code>.</li>
	<li>Thus, the maximum number of columns that can remain is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 250</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 250</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= limit &lt;= 10<sup>5</sup>​​​​​​​​​​​​​​​​</code></li>
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
