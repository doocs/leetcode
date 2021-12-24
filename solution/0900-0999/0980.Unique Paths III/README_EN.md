# [980. Unique Paths III](https://leetcode.com/problems/unique-paths-iii)

[中文文档](/solution/0900-0999/0980.Unique%20Paths%20III/README.md)

## Description

<p>On a 2-dimensional&nbsp;<code>grid</code>, there are 4 types of squares:</p>

<ul>
	<li><code>1</code> represents the starting square.&nbsp; There is exactly one starting square.</li>
	<li><code>2</code> represents the ending square.&nbsp; There is exactly one ending square.</li>
	<li><code>0</code> represents empty squares we can walk over.</li>
	<li><code>-1</code> represents obstacles that we cannot walk over.</li>
</ul>

<p>Return the number of 4-directional walks&nbsp;from the starting square to the ending square, that <strong>walk over every non-obstacle square&nbsp;exactly once</strong>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]</span>

<strong>Output: </strong><span id="example-output-1">2</span>

<strong>Explanation: </strong>We have the following two paths: 

1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)

2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[[1,0,0,0],[0,0,0,0],[0,0,0,2]]</span>

<strong>Output: </strong><span id="example-output-2">4</span>

<strong>Explanation: </strong>We have the following four paths: 

1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)

2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)

3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)

4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[[0,1],[2,0]]</span>

<strong>Output: </strong><span id="example-output-3">0</span>

<strong>Explanation: </strong>

There is no path that walks over every empty square exactly once.

Note that the starting and ending square can be anywhere in the grid.

</pre>

</div>

</div>

</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= grid.length * grid[0].length &lt;= 20</code></li>
</ol>

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
