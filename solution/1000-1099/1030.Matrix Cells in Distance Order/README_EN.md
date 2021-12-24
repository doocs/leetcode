# [1030. Matrix Cells in Distance Order](https://leetcode.com/problems/matrix-cells-in-distance-order)

[中文文档](/solution/1000-1099/1030.Matrix%20Cells%20in%20Distance%20Order/README.md)

## Description

<p>We are given a matrix with <code>R</code> rows and <code>C</code> columns has cells with integer coordinates&nbsp;<code>(r, c)</code>, where <code>0 &lt;= r &lt; R</code> and <code>0 &lt;= c &lt; C</code>.</p>

<p>Additionally, we are given a cell in that matrix with coordinates&nbsp;<code>(r0, c0)</code>.</p>

<p>Return the coordinates of&nbsp;all cells in the matrix, sorted by their distance from <code>(r0, c0)</code>&nbsp;from smallest distance to largest distance.&nbsp; Here,&nbsp;the distance between two cells <code>(r1, c1)</code> and <code>(r2, c2)</code> is the Manhattan distance,&nbsp;<code>|r1 - r2| + |c1 - c2|</code>.&nbsp; (You may return the answer in any order that satisfies this condition.)</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>R = <span id="example-input-1-1">1</span>, C = <span id="example-input-1-2">2</span>, r0 = <span id="example-input-1-3">0</span>, c0 = <span id="example-input-1-4">0</span>

<strong>Output: </strong><span id="example-output-1">[[0,0],[0,1]]

<strong>Explanation:</strong> The distances from (r0, c0) to other cells are: [0,1]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>R = <span id="example-input-2-1">2</span>, C = <span id="example-input-2-2">2</span>, r0 = <span id="example-input-2-3">0</span>, c0 = <span id="example-input-2-4">1</span>

<strong>Output: </strong><span id="example-output-2">[[0,1],[0,0],[1,1],[1,0]]

</span><span id="example-output-1"><strong>Explanation:</strong> The distances from (r0, c0) to other cells are:</span><span> [0,1,1,2]</span>

The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>R = <span id="example-input-3-1">2</span>, C = <span id="example-input-3-2">3</span>, r0 = <span id="example-input-3-3">1</span>, c0 = <span id="example-input-3-4">2</span>

<strong>Output: </strong><span id="example-output-3">[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]</span>

<span id="example-output-1"><strong>Explanation:</strong> The distances from (r0, c0) to other cells are:</span><span> [0,1,1,2,2,3]</span>

There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].

</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code>1 &lt;= R &lt;= 100</code></li>
	<li><code>1 &lt;= C &lt;= 100</code></li>
	<li><code>0 &lt;= r0 &lt; R</code></li>
	<li><code>0 &lt;= c0 &lt; C</code></li>
</ol>

</div>

</div>

</div>

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
