# [750. Number Of Corner Rectangles](https://leetcode.com/problems/number-of-corner-rectangles)

[中文文档](/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/README.md)

## Description

<p>Given an <code>m x n</code> integer matrix <code>grid</code> where each entry is only <code>0</code> or <code>1</code>, return <em>the number of <strong>corner rectangles</strong></em>.</p>

<p>A <strong>corner rectangle</strong> is four distinct <code>1</code>&#39;s on the grid that forms an axis-aligned rectangle. Note that only the corners need to have the value <code>1</code>. Also, all four <code>1</code>&#39;s used must be distinct.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec1-grid.jpg" style="width: 413px; height: 333px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,1,1],[1,1,1]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec3-grid.jpg" style="width: 333px; height: 93px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Rectangles must have four distinct corners.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>The number of <code>1</code>&#39;s in the grid is in the range <code>[1, 6000]</code>.</li>
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
