# [1034. 边框着色](https://leetcode-cn.com/problems/coloring-a-border)

[English Version](/solution/1000-1099/1034.Coloring%20A%20Border/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个二维整数网格&nbsp;<code>grid</code>，网格中的每个值表示该位置处的网格块的颜色。</p>

<p>只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一<strong>连通分量</strong>。</p>

<p>连通分量的<strong>边界</strong>是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。</p>

<p>给出位于&nbsp;<code>(r0, c0)</code>&nbsp;的网格块和颜色&nbsp;<code>color</code>，使用指定颜色&nbsp;<code>color</code>&nbsp;为所给网格块的连通分量的边界进行着色，并返回最终的网格&nbsp;<code>grid</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
<strong>输出：</strong>[[3, 3], [3, 2]]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
<strong>输出：</strong>[[1, 3, 3], [2, 3, 3]]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
<strong>输出：</strong>[[2, 2, 2], [2, 1, 2], [2, 2, 2]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= grid.length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[0].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 1000</code></li>
	<li><code>0 &lt;= r0 &lt; grid.length</code></li>
	<li><code>0 &lt;= c0 &lt; grid[0].length</code></li>
	<li><code>1 &lt;= color &lt;= 1000</code></li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索，利用 vis 记录访问过的位置。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
