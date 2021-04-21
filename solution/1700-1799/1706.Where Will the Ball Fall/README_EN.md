# [1706. Where Will the Ball Fall](https://leetcode.com/problems/where-will-the-ball-fall)

[中文文档](/solution/1700-1799/1706.Where%20Will%20the%20Ball%20Fall/README.md)

## Description

<p>You have a 2-D <code>grid</code> of size <code>m x n</code> representing a box, and you have <code>n</code> balls. The box is open on the top and bottom sides.</p>

<p>Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.</p>

<ul>
	<li>A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as <code>1</code>.</li>
	<li>A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as <code>-1</code>.</li>
</ul>

<p>We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a &quot;V&quot; shaped pattern between two boards or if a board redirects the ball into either wall of the box.</p>

<p>Return <em>an array </em><code>answer</code><em> of size </em><code>n</code><em> where </em><code>answer[i]</code><em> is the column that the ball falls out of at the bottom after dropping the ball from the </em><code>i<sup>th</sup></code><em> column at the top, or <code>-1</code><em> if the ball gets stuck in the box</em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/09/26/ball.jpg" style="width: 500px; height: 385px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
<strong>Output:</strong> [1,-1,-1,-1,-1]
<strong>Explanation:</strong> This example is shown in the photo.
Ball b0 is dropped at column 0 and falls out of the box at column 1.
Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[-1]]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> The ball gets stuck against the left wall.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
<strong>Output:</strong> [0,1,2,3,4,-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is <code>1</code> or <code>-1</code>.</li>
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
