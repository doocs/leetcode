# [1001. Grid Illumination](https://leetcode.com/problems/grid-illumination)

[中文文档](/solution/1000-1099/1001.Grid%20Illumination/README.md)

## Description

<p>There is a 2D <code>grid</code> of size <code>N x N</code> where each cell of this grid has a lamp that is initially <strong>turned off</strong>.</p>

<p>You are given a 2D array of lamp positions <code>lamps</code>, where <code>lamps[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> indicates that the lamp at <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> is <strong>turned on</strong>. Even if the same lamp is listed more than once, it is turned on.</p>

<p>When a lamp is turned on, it <strong>illuminates its cell</strong> and <strong>all other cells</strong> in the same <strong>row, column, or diagonal</strong>.</p>

<p>You are also given another 2D array <code>queries</code>, where <code>queries[j] = [row<sub>j</sub>, col<sub>j</sub>]</code>. For the <code>j<sup>th</sup></code> query, determine whether <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code> is illuminated or not. After answering the <code>j<sup>th</sup></code> query, <strong>turn off</strong> the lamp at <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code> and its <strong>8 adjacent lamps</strong> if they exist. A lamp is adjacent if its cell shares either a side or corner with <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code>.</p>

<p>Return <em>an array of integers </em><code>ans</code><em>,</em><em> where </em><code>ans[j]</code><em> should be </em><code>1</code><em> if the cell in the </em><code>j<sup>th</sup></code><em> query was illuminated, or </em><code>0</code><em> if the lamp was not.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_1.jpg" style="width: 750px; height: 209px;" />
<pre>
<strong>Input:</strong> N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
The 0<sup>th</sup>&nbsp;query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step1.jpg" style="width: 500px; height: 218px;" />
The 1<sup>st</sup>&nbsp;query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step2.jpg" style="width: 500px; height: 219px;" />
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
<strong>Output:</strong> [1,1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> N = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
<strong>Output:</strong> [1,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= lamps.length &lt;= 20000</code></li>
	<li><code>0 &lt;= queries.length &lt;= 20000</code></li>
	<li><code>lamps[i].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, col<sub>i</sub> &lt; N</code></li>
	<li><code>queries[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>j</sub>, col<sub>j</sub> &lt; N</code></li>
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
<!-- tabs:end -->
