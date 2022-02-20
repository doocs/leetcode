# [2174. Remove All Ones With Row and Column Flips II](https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii)

[中文文档](/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> <strong>binary</strong> matrix <code>grid</code>.</p>

<p>In one operation, you can choose any <code>i</code> and <code>j</code> that meet the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; m</code></li>
	<li><code>0 &lt;= j &lt; n</code></li>
	<li><code>grid[i][j] == 1</code></li>
</ul>

<p>and change the values of <strong>all</strong> cells in row <code>i</code> and column <code>j</code> to zero.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to remove all </em><code>1</code><em>&#39;s from </em><code>grid</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162716-1.png" style="width: 709px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,1,1],[0,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
In the first operation, change all cell values of row 1 and column 1 to zero.
In the second operation, change all cell values of row 0 and column 0 to zero.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162737-2.png" style="width: 734px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0],[1,0,1],[0,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
In the first operation, change all cell values of row 1 and column 0 to zero.
In the second operation, change all cell values of row 2 and column 1 to zero.
Note that we cannot perform an operation using row 1 and column 1 because grid[1][1] != 1.
</pre>

<p><strong>Example 3:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2174.Remove%20All%20Ones%20With%20Row%20and%20Column%20Flips%20II/images/image-20220213162752-3.png" style="width: 156px; height: 150px;" />
<pre>
<strong>Input:</strong> grid = [[0,0],[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no 1&#39;s to remove so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>1 &lt;= m * n &lt;= 15</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
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
