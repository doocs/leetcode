# [2123. Minimum Operations to Remove Adjacent Ones in Matrix](https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix)

[中文文档](/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> binary matrix <code>grid</code>. In one operation, you can flip any <code>1</code> in <code>grid</code> to be <code>0</code>.</p>

<p>A binary matrix is <strong>well-isolated</strong> if there is no <code>1</code> in the matrix that is <strong>4-directionally connected</strong> (i.e., horizontal and vertical) to another <code>1</code>.</p>

<p>Return <em>the minimum number of operations to make </em><code>grid</code><em> <strong>well-isolated</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181501-1.png" style="width: 644px; height: 250px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[0,1,1],[1,1,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Use 3 operations to change grid[0][1], grid[1][2], and grid[2][1] to 0.
After, no more 1&#39;s are 4-directionally connected and grid is well-isolated.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181518-2.png" style="height: 250px; width: 255px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no 1&#39;s in grid and it is well-isolated.
No operations were done so return 0.
</pre>

<p><strong>Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181817-3.png" style="width: 165px; height: 167px;" />
<pre>
<strong>Input:</strong> grid = [[0,1],[1,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> None of the 1&#39;s are 4-directionally connected and grid is well-isolated.
No operations were done so return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
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
