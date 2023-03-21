# [980. Unique Paths III](https://leetcode.com/problems/unique-paths-iii)

[中文文档](/solution/0900-0999/0980.Unique%20Paths%20III/README.md)

## Description

<p>You are given an <code>m x n</code> integer array <code>grid</code> where <code>grid[i][j]</code> could be:</p>

<ul>
	<li><code>1</code> representing the starting square. There is exactly one starting square.</li>
	<li><code>2</code> representing the ending square. There is exactly one ending square.</li>
	<li><code>0</code> representing empty squares we can walk over.</li>
	<li><code>-1</code> representing obstacles that we cannot walk over.</li>
</ul>

<p>Return <em>the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0980.Unique%20Paths%20III/images/lc-unique1.jpg" style="width: 324px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0980.Unique%20Paths%20III/images/lc-unique2.jpg" style="width: 324px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0980.Unique%20Paths%20III/images/lc-unique3-.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[0,1],[2,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>1 &lt;= m * n &lt;= 20</code></li>
	<li><code>-1 &lt;= grid[i][j] &lt;= 2</code></li>
	<li>There is exactly one starting cell and one ending cell.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def uniquePathsIII(self, grid: List[List[int]]) -> int:
        def dfs(i, j, k):
            if grid[i][j] == 2:
                return int(k == cnt + 1)
            ans = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and (x, y) not in vis and grid[x][y] != -1:
                    vis.add((x, y))
                    ans += dfs(x, y, k + 1)
                    vis.remove((x, y))
            return ans

        m, n = len(grid), len(grid[0])
        start = next((i, j) for i in range(m)
                     for j in range(n) if grid[i][j] == 1)
        dirs = (-1, 0, 1, 0, -1)
        cnt = sum(grid[i][j] == 0 for i in range(m) for j in range(n))
        vis = {start}
        return dfs(*start, 0)
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int cnt;
    private int[][] grid;
    private boolean[][] vis;

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int x = 0, y = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ++cnt;
                } else if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        }
        vis = new boolean[m][n];
        vis[x][y] = true;
        return dfs(x, y, 0);
    }

    private int dfs(int i, int j, int k) {
        if (grid[i][j] == 2) {
            return k == cnt + 1 ? 1 : 0;
        }
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int h = 0; h < 4; ++h) {
            int x = i + dirs[h], y = j + dirs[h + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] != -1) {
                vis[x][y] = true;
                ans += dfs(x, y, k + 1);
                vis[x][y] = false;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int uniquePathsIII(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt = 0;
        for (auto& row : grid) {
            for (auto& x : row) {
                cnt += x == 0;
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        function<int(int, int, int)> dfs = [&](int i, int j, int k) -> int {
            if (grid[i][j] == 2) {
                return k == cnt + 1 ? 1 : 0;
            }
            int ans = 0;
            for (int h = 0; h < 4; ++h) {
                int x = i + dirs[h], y = j + dirs[h + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] != -1) {
                    vis[x][y] = true;
                    ans += dfs(x, y, k + 1);
                    vis[x][y] = false;
                }
            }
            return ans;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    vis[i][j] = true;
                    return dfs(i, j, 0);
                }
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func uniquePathsIII(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt := 0
	vis := make([][]bool, m)
	x, y := 0, 0
	for i, row := range grid {
		vis[i] = make([]bool, n)
		for j, v := range row {
			if v == 0 {
				cnt++
			} else if v == 1 {
				x, y = i, j
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if grid[i][j] == 2 {
			if k == cnt+1 {
				return 1
			}
			return 0
		}
		ans := 0
		for h := 0; h < 4; h++ {
			x, y := i+dirs[h], j+dirs[h+1]
			if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && grid[x][y] != -1 {
				vis[x][y] = true
				ans += dfs(x, y, k+1)
				vis[x][y] = false
			}
		}
		return ans
	}
	vis[x][y] = true
	return dfs(x, y, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
