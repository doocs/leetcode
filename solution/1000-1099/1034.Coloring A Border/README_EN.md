# [1034. Coloring A Border](https://leetcode.com/problems/coloring-a-border)

[中文文档](/solution/1000-1099/1034.Coloring%20A%20Border/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>, and three integers <code>row</code>, <code>col</code>, and <code>color</code>. Each value in the grid represents the color of the grid square at that location.</p>

<p>Two squares belong to the same <strong>connected component</strong> if they have the same color and are next to each other in any of the 4 directions.</p>

<p>The <strong>border of a connected component</strong> is all the squares in the connected component that are either <strong>4-directionally</strong> adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).</p>

<p>You should color the <strong>border</strong> of the <strong>connected component</strong> that contains the square <code>grid[row][col]</code> with <code>color</code>.</p>

<p>Return <em>the final grid</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
<strong>Output:</strong> [[3,3],[3,2]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
<strong>Output:</strong> [[1,3,3],[2,3,3]]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
<strong>Output:</strong> [[2,2,2],[2,1,2],[2,2,2]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j], color &lt;= 1000</code></li>
	<li><code>0 &lt;= row &lt; m</code></li>
	<li><code>0 &lt;= col &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def colorBorder(
        self, grid: List[List[int]], row: int, col: int, color: int
    ) -> List[List[int]]:
        def dfs(i: int, j: int, c: int) -> None:
            vis[i][j] = True
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n:
                    if not vis[x][y]:
                        if grid[x][y] == c:
                            dfs(x, y, c)
                        else:
                            grid[i][j] = color
                else:
                    grid[i][j] = color

        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]
        dfs(row, col, grid[row][col])
        return grid
```

### **Java**

```java
class Solution {
    private int[][] grid;
    private int color;
    private int m;
    private int n;
    private boolean[][] vis;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        this.grid = grid;
        this.color = color;
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        dfs(row, col, grid[row][col]);
        return grid;
    }

    private void dfs(int i, int j, int c) {
        vis[i][j] = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (!vis[x][y]) {
                    if (grid[x][y] == c) {
                        dfs(x, y, c);
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
    vector<vector<int>> colorBorder(vector<vector<int>>& grid, int row, int col, int color) {
        int m = grid.size();
        int n = grid[0].size();
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int, int)> dfs = [&](int i, int j, int c) {
            vis[i][j] = true;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (!vis[x][y]) {
                        if (grid[x][y] == c) {
                            dfs(x, y, c);
                        } else {
                            grid[i][j] = color;
                        }
                    }
                } else {
                    grid[i][j] = color;
                }
            }
        };
        dfs(row, col, grid[row][col]);
        return grid;
    }
};
```

### **Go**

```go
func colorBorder(grid [][]int, row int, col int, color int) [][]int {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(int, int, int)
	dfs = func(i, j, c int) {
		vis[i][j] = true
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if !vis[x][y] {
					if grid[x][y] == c {
						dfs(x, y, c)
					} else {
						grid[i][j] = color
					}
				}
			} else {
				grid[i][j] = color
			}
		}
	}
	dfs(row, col, grid[row][col])
	return grid
}
```

### **TypeScript**

```ts
function colorBorder(
    grid: number[][],
    row: number,
    col: number,
    color: number,
): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const vis = new Array(m).fill(0).map(() => new Array(n).fill(false));
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, c: number) => {
        vis[i][j] = true;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (!vis[x][y]) {
                    if (grid[x][y] == c) {
                        dfs(x, y, c);
                    } else {
                        grid[i][j] = color;
                    }
                }
            } else {
                grid[i][j] = color;
            }
        }
    };
    dfs(row, col, grid[row][col]);
    return grid;
}
```

### **...**

```

```

<!-- tabs:end -->
