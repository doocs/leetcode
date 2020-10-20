# [1594. Maximum Non Negative Product in a Matrix](https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix)

[中文文档](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README.md)

## Description

<p>You are given a&nbsp;<code>rows x cols</code>&nbsp;matrix&nbsp;<code>grid</code>.&nbsp;Initially, you&nbsp;are located at the top-left&nbsp;corner <code>(0, 0)</code>,&nbsp;and in each step, you can only <strong>move right&nbsp;or&nbsp;down</strong> in the matrix.</p>

<p>Among all possible paths starting from the top-left corner&nbsp;<code>(0, 0)</code>&nbsp;and ending in the bottom-right corner&nbsp;<code>(rows - 1, cols - 1)</code>, find the path with the&nbsp;<strong>maximum non-negative product</strong>. The product of a path is the product of all integers in the grid cells visited along the path.</p>

<p>Return the&nbsp;<em>maximum non-negative product&nbsp;<strong>modulo</strong>&nbsp;</em><code>10<sup>9</sup>&nbsp;+ 7</code>.&nbsp;<em>If the maximum product is <strong>negative</strong> return&nbsp;</em><code>-1</code>.</p>

<p><strong>Notice that the modulo is performed after getting the maximum product.</strong></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[-1,-2,-3],
&nbsp;              [-2,-3,-3],
&nbsp;              [-3,-3,-2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It&#39;s not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[<strong>1</strong>,-2,1],
&nbsp;              [<strong>1</strong>,<strong>-2</strong>,1],
&nbsp;              [3,<strong>-4</strong>,<strong>1</strong>]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[<strong>1</strong>, 3],
&nbsp;              [<strong>0</strong>,<strong>-4</strong>]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Maximum non-negative product is in bold (1 * 0 * -4 = 0).
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[ <strong>1</strong>, 4,4,0],
&nbsp;              [<strong>-2</strong>, 0,0,1],
&nbsp;              [ <strong>1</strong>,<strong>-1</strong>,<strong>1</strong>,<strong>1</strong>]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 * 1 = 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
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
