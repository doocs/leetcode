# [463. Island Perimeter](https://leetcode.com/problems/island-perimeter)

[中文文档](/solution/0400-0499/0463.Island%20Perimeter/README.md)

## Description

<p>You are given <code>row x col</code> <code>grid</code> representing a map where <code>grid[i][j] = 1</code> represents&nbsp;land and <code>grid[i][j] = 0</code> represents water.</p>

<p>Grid cells are connected <strong>horizontally/vertically</strong> (not diagonally). The <code>grid</code> is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).</p>

<p>The island doesn&#39;t have &quot;lakes&quot;, meaning the water inside isn&#39;t connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don&#39;t exceed 100. Determine the perimeter of the island.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0463.Island%20Perimeter/images/island.png" style="width: 221px; height: 213px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
<strong>Output:</strong> 16
<strong>Explanation:</strong> The perimeter is the 16 yellow stripes in the image above.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1]]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>row == grid.length</code></li>
	<li><code>col == grid[i].length</code></li>
	<li><code>1 &lt;= row, col &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is <code>0</code> or <code>1</code>.</li>
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
function islandPerimeter(grid: number[][]): number {
    let m = grid.length, n = grid[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let top = 0, left = 0;
            if (i > 0) {
                top = grid[i - 1][j];
            }
            if (j > 0) {
                left = grid[i][j - 1];
            }
            let cur = grid[i][j];
            if (cur != top) ++ans;
            if (cur != left) ++ans;
        }
    }
    // 最后一行， 最后一列
    for (let i = 0; i < m; ++i) {
        if (grid[i][n - 1] == 1) ++ans;
    }
    for (let j = 0; j < n; ++j) {
        if (grid[m - 1][j] == 1) ++ans;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
