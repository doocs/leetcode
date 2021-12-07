# [1034. Coloring A Border](https://leetcode.com/problems/coloring-a-border)

[中文文档](/solution/1000-1099/1034.Coloring%20A%20Border/README.md)

## Description

<p>Given a 2-dimensional&nbsp;<code>grid</code> of integers, each value in the grid represents the color of the grid square at that location.</p>

<p>Two squares belong to the same <em>connected component</em> if and only if they have the same color and are next to each other in any of the 4 directions.</p>

<p>The&nbsp;<em>border</em> of a connected component is&nbsp;all the squares in the connected component that are&nbsp;either 4-directionally adjacent to&nbsp;a square not in the component, or on the boundary of the grid (the first or last row or column).</p>

<p>Given a square at location&nbsp;<code>(r0, c0)</code>&nbsp;in the grid and a <code>color</code>, color the&nbsp;border of the connected component of that square with the given <code>color</code>, and return the final <code>grid</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>grid = <span id="example-input-1-1">[[1,1],[1,2]]</span>, r0 = <span id="example-input-1-2">0</span>, c0 = <span id="example-input-1-3">0</span>, color = <span id="example-input-1-4">3</span>

<strong>Output: </strong><span id="example-output-1">[[3, 3], [3, 2]]</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>grid = <span id="example-input-2-1">[[1,2,2],[2,3,2]]</span>, r0 = <span id="example-input-2-2">0</span>, c0 = <span id="example-input-2-3">1</span>, color = <span id="example-input-2-4">3</span>

<strong>Output: </strong><span id="example-output-2">[[1, 3, 3], [2, 3, 3]]</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>grid = <span id="example-input-3-1">[[1,1,1],[1,1,1],[1,1,1]]</span>, r0 = <span id="example-input-3-2">1</span>, c0 = <span id="example-input-3-3">1</span>, color = <span id="example-input-3-4">2</span>

<strong>Output: </strong><span id="example-output-3">[[2, 2, 2], [2, 1, 2], [2, 2, 2]]</span></pre>

</div>

</div>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= grid.length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[0].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
	<li><code>0 &lt;= r0 &lt; grid.length</code></li>
	<li><code>0 &lt;= c0 &lt; grid[0].length</code></li>
	<li><code>1 &lt;= color &lt;= 1000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def colorBorder(self, grid: List[List[int]], row: int, col: int, color: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]

        def dfs(i, j, color):
            vis[i][j] = True
            old_color = grid[i][j]
            for a, b in [[-1, 0], [1, 0], [0, -1], [0, 1]]:
                x, y = a + i, b + j
                if 0 <= x < m and 0 <= y < n:
                    if not vis[x][y]:
                        if grid[x][y] == old_color:
                            dfs(x, y, color)
                        else:
                            grid[i][j] = color
                else:
                    grid[i][j] = color

        dfs(row, col, color)
        return grid
```

### **Java**

```java
class Solution {
    private int[] dirs = new int[]{-1, 0, 1, 0, -1};

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        dfs(grid, r0, c0, color, vis);
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int color, boolean[][] vis) {
        vis[i][j] = true;
        int oldColor = grid[i][j];
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                if (!vis[x][y]) {
                    if (grid[x][y] == oldColor) {
                        dfs(grid, x, y, color, vis);
                    } else {
                        grid[i][j] = color;
                    }
                }
            } else {
                grid[i][j] = color;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int m, n;
    vector<vector<int>> dirs = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    vector<vector<int>> colorBorder(vector<vector<int>>& grid, int row, int col, int color) {
        m = grid.size();
        n = grid[0].size();
        vector<vector<bool>> vis(m, vector<bool>(n, false));
        dfs(row, col, color, grid, vis);
        return grid;
    }

    void dfs(int i, int j, int color, vector<vector<int>>& grid, vector<vector<bool>>& vis) {
        vis[i][j] = true;
        int oldColor = grid[i][j];
        for (auto& dir : dirs)
        {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n)
            {
                if (!vis[x][y])
                {
                    if (grid[x][y] == oldColor) dfs(x, y, color, grid, vis);
                    else grid[i][j] = color;
                }
            }
            else grid[i][j] = color;
        }
    }
};
```

### **Go**

```go
func colorBorder(grid [][]int, row int, col int, color int) [][]int {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := 0; i < m; i++ {
		vis[i] = make([]bool, n)
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}

	var dfs func(i, j, color int)
	dfs = func(i, j, color int) {
		vis[i][j] = true
		oldColor := grid[i][j]
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if !vis[x][y] {
					if grid[x][y] == oldColor {
						dfs(x, y, color)
					} else {
						grid[i][j] = color
					}
				}
			} else {
				grid[i][j] = color
			}
		}
	}
	dfs(row, col, color)
	return grid
}
```

### **...**

```

```

<!-- tabs:end -->
