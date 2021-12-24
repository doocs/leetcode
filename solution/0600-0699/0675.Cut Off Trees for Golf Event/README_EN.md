# [675. Cut Off Trees for Golf Event](https://leetcode.com/problems/cut-off-trees-for-golf-event)

[中文文档](/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/README.md)

## Description

<p>You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an <code>m x n</code> matrix. In this matrix:</p>

<ul>
	<li><code>0</code> means the cell cannot be walked through.</li>
	<li><code>1</code> represents an empty cell that can be walked through.</li>
	<li>A number greater than <code>1</code> represents a tree in a cell that can be walked through, and this number is the tree&#39;s height.</li>
</ul>

<p>In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.</p>

<p>You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes <code>1</code> (an empty cell).</p>

<p>Starting from the point <code>(0, 0)</code>, return <em>the minimum steps you need to walk to cut off all the trees</em>. If you cannot cut off all the trees, return <code>-1</code>.</p>

<p>You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/images/trees1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> forest = [[1,2,3],[0,0,4],[7,6,5]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/images/trees2.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> forest = [[1,2,3],[0,0,0],[7,6,5]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The trees in the bottom row cannot be accessed as the middle row is blocked.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> forest = [[2,3,4],[0,0,5],[8,7,6]]
<strong>Output:</strong> 6
<b>Explanation:</b> You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == forest.length</code></li>
	<li><code>n == forest[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= forest[i][j] &lt;= 10<sup>9</sup></code></li>
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
