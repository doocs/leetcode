# [499. The Maze III](https://leetcode.com/problems/the-maze-iii)

[中文文档](/solution/0400-0499/0499.The%20Maze%20III/README.md)

## Description

<p>There is a ball in a <code>maze</code> with empty spaces (represented as <code>0</code>) and walls (represented as <code>1</code>). The ball can go through the empty spaces by rolling <strong>up, down, left or right</strong>, but it won&#39;t stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.</p>

<p>Given the <code>m x n</code> <code>maze</code>, the ball&#39;s position <code>ball</code> and the hole&#39;s position <code>hole</code>, where <code>ball = [ball<sub>row</sub>, ball<sub>col</sub>]</code> and <code>hole = [hole<sub>row</sub>, hole<sub>col</sub>]</code>, return <em>a string </em><code>instructions</code><em> of all the instructions that the ball should follow to drop in the hole with the <strong>shortest distance</strong> possible</em>. If there are multiple valid instructions, return the <strong>lexicographically minimum</strong> one. If the ball can&#39;t drop in the hole, return <code>&quot;impossible&quot;</code>.</p>

<p>If there is a way for the ball to drop in the hole, the answer <code>instructions</code> should contain the characters <code>&#39;u&#39;</code> (i.e., up), <code>&#39;d&#39;</code> (i.e., down), <code>&#39;l&#39;</code> (i.e., left), and <code>&#39;r&#39;</code> (i.e., right).</p>

<p>The <strong>distance</strong> is the number of <strong>empty spaces</strong> traveled by the ball from the start position (excluded) to the destination (included).</p>

<p>You may assume that <strong>the borders of the maze are all walls</strong> (see examples).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze3-1-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [0,1]
<strong>Output:</strong> &quot;lul&quot;
<strong>Explanation:</strong> There are two shortest ways for the ball to drop into the hole.
The first way is left -&gt; up -&gt; left, represented by &quot;lul&quot;.
The second way is up -&gt; left, represented by &#39;ul&#39;.
Both ways have shortest distance 6, but the first way is lexicographically smaller because &#39;l&#39; &lt; &#39;u&#39;. So the output is &quot;lul&quot;.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze3-2-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [3,0]
<strong>Output:</strong> &quot;impossible&quot;
<strong>Explanation:</strong> The ball cannot reach the hole.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
<strong>Output:</strong> &quot;dldr&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>maze[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>ball.length == 2</code></li>
	<li><code>hole.length == 2</code></li>
	<li><code>0 &lt;= ball<sub>row</sub>, hole<sub>row</sub> &lt;= m</code></li>
	<li><code>0 &lt;= ball<sub>col</sub>, hole<sub>col</sub> &lt;= n</code></li>
	<li>Both the ball and the hole exist in an empty space, and they will not be in the same position initially.</li>
	<li>The maze contains <strong>at least 2 empty spaces</strong>.</li>
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
