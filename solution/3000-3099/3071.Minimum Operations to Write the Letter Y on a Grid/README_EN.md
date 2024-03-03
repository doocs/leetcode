# [3071. Minimum Operations to Write the Letter Y on a Grid](https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid)

[中文文档](/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/README.md)

<!-- tags: -->

## Description

<p>You are given a <strong>0-indexed</strong> <code>n x n</code> grid where <code>n</code> is odd, and <code>grid[r][c]</code> is <code>0</code>, <code>1</code>, or <code>2</code>.</p>

<p>We say that a cell belongs to the Letter <strong>Y</strong> if it belongs to one of the following:</p>

<ul>
	<li>The diagonal starting at the top-left cell and ending at the center cell of the grid.</li>
	<li>The diagonal starting at the top-right cell and ending at the center cell of the grid.</li>
	<li>The vertical line starting at the center cell and ending at the bottom border of the grid.</li>
</ul>

<p>The Letter <strong>Y</strong> is written on the grid if and only if:</p>

<ul>
	<li>All values at cells belonging to the Y are equal.</li>
	<li>All values at cells not belonging to the Y are equal.</li>
	<li>The values at cells belonging to the Y are different from the values at cells not belonging to the Y.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of operations needed to write the letter Y on the grid given that in one operation you can change the value at any cell to</em> <code>0</code><em>,</em> <code>1</code><em>,</em> <em>or</em> <code>2</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y2.png" style="width: 461px; height: 121px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,2],[1,1,0],[0,1,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 1 while those that do not belong to Y are equal to 0.
It can be shown that 3 is the minimum number of operations needed to write Y on the grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y3.png" style="width: 701px; height: 201px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 0 while those that do not belong to Y are equal to 2. 
It can be shown that 12 is the minimum number of operations needed to write Y on the grid.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 49 </code></li>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 2</code></li>
	<li><code>n</code> is odd.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
