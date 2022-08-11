# [2371. Minimize Maximum Value in a Grid](https://leetcode.com/problems/minimize-maximum-value-in-a-grid)

[中文文档](/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code> containing <strong>distinct</strong> positive integers.</p>

<p>You have to replace each integer in the matrix with a positive integer satisfying the following conditions:</p>

<ul>
	<li>The <strong>relative</strong> order of every two elements that are in the same row or column should stay the <strong>same</strong> after the replacements.</li>
	<li>The <strong>maximum</strong> number in the matrix after the replacements should be as <strong>small</strong> as possible.</li>
</ul>

<p>The relative order stays the same if for all pairs of elements in the original matrix such that <code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code> where either <code>r<sub>1</sub> == r<sub>2</sub></code> or <code>c<sub>1</sub> == c<sub>2</sub></code>, then it must be true that <code>grid[r<sub>1</sub>][c<sub>1</sub>] &gt; grid[r<sub>2</sub>][c<sub>2</sub>]</code> after the replacements.</p>

<p>For example, if <code>grid = [[2, 4, 5], [7, 3, 9]]</code> then a good replacement could be either <code>grid = [[1, 2, 3], [2, 1, 4]]</code> or <code>grid = [[1, 2, 3], [3, 1, 4]]</code>.</p>

<p>Return <em>the <strong>resulting</strong> matrix.</em> If there are multiple answers, return <strong>any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/images/grid2drawio.png" style="width: 371px; height: 121px;" />
<pre>
<strong>Input:</strong> grid = [[3,1],[2,5]]
<strong>Output:</strong> [[2,1],[1,2]]
<strong>Explanation:</strong> The above diagram shows a valid replacement.
The maximum number in the matrix is 2. It can be shown that no smaller value can be obtained.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[10]]
<strong>Output:</strong> [[1]]
<strong>Explanation:</strong> We replace the only number in the matrix with 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>grid</code> consists of distinct integers.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
