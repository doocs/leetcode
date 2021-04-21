# [803. Bricks Falling When Hit](https://leetcode.com/problems/bricks-falling-when-hit)

[中文文档](/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README.md)

## Description

<p>You are given an <code>m x n</code> binary <code>grid</code>, where each <code>1</code> represents a brick and <code>0</code> represents an empty space. A brick is <strong>stable</strong> if:</p>

<ul>
	<li>It is directly connected to the top of the grid, or</li>
	<li>At least one other brick in its four adjacent cells is <strong>stable</strong>.</li>
</ul>

<p>You are also given an array <code>hits</code>, which is a sequence of erasures we want to apply. Each time we want to erase the brick at the location <code>hits[i] = (row<sub>i</sub>, col<sub>i</sub>)</code>. The brick on that location&nbsp;(if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will <strong>fall</strong>. Once a brick falls, it is <strong>immediately</strong> erased from the <code>grid</code> (i.e., it does not land on other stable bricks).</p>

<p>Return <em>an array </em><code>result</code><em>, where each </em><code>result[i]</code><em> is the number of bricks that will <strong>fall</strong> after the </em><code>i<sup>th</sup></code><em> erasure is applied.</em></p>

<p><strong>Note</strong> that an erasure may refer to a location with no brick, and if it does, no bricks drop.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
<strong>Output:</strong> [2]
<strong>Explanation: </strong>Starting with the grid:
[[1,0,0,0],
 [<u>1</u>,1,1,0]]
We erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,<u>1</u>,<u>1</u>,0]]
The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
[[1,0,0,0],
 [0,0,0,0]]
Hence the result is [2].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
<strong>Output:</strong> [0,0]
<strong>Explanation: </strong>Starting with the grid:
[[1,0,0,0],
 [1,<u>1</u>,0,0]]
We erase the underlined brick at (1,1), resulting in the grid:
[[1,0,0,0],
 [1,0,0,0]]
All remaining bricks are still stable, so no bricks fall. The grid remains the same:
[[1,0,0,0],
 [<u>1</u>,0,0,0]]
Next, we erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,0,0,0]]
Once again, all remaining bricks are still stable, so no bricks fall.
Hence the result is [0,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= hits.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>hits[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i&nbsp;</sub>&lt;= m - 1</code></li>
	<li><code>0 &lt;=&nbsp;y<sub>i</sub> &lt;= n - 1</code></li>
	<li>All <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are unique.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
