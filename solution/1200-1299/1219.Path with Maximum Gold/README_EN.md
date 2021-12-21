# [1219. Path with Maximum Gold](https://leetcode.com/problems/path-with-maximum-gold)

[中文文档](/solution/1200-1299/1219.Path%20with%20Maximum%20Gold/README.md)

## Description

<p>In a gold mine <code>grid</code> of size <code>m x n</code>, each cell in this mine has an integer representing the amount of gold in that cell, <code>0</code> if it is empty.</p>

<p>Return the maximum amount of gold you can collect under the conditions:</p>

<ul>
	<li>Every time you are located in a cell you will collect all the gold in that cell.</li>
	<li>From your position, you can walk one step to the left, right, up, or down.</li>
	<li>You can&#39;t visit the same cell more than once.</li>
	<li>Never visit a cell with <code>0</code> gold.</li>
	<li>You can start and stop collecting gold from <strong>any </strong>position in the grid that has some gold.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,6,0],[5,8,7],[0,9,0]]
<strong>Output:</strong> 24
<strong>Explanation:</strong>
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -&gt; 8 -&gt; 7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
<strong>Output:</strong> 28
<strong>Explanation:</strong>
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -&gt; 2 -&gt; 3 -&gt; 4 -&gt; 5 -&gt; 6 -&gt; 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
	<li>There are at most <strong>25 </strong>cells containing gold.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            if i < 0 or i >= len(grid) or j < 0 or j >= len(grid[0]) or grid[i][j] == 0 or vis[i][j]:
                return 0

            vis[i][j] = True
            t = 0
            for x, y in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                t = max(t, dfs(i + x, j + y))
            vis[i][j] = False
            return t + grid[i][j]

        m, n = len(grid), len(grid[0])
        ans = 0
        vis = [[False] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ans = max(ans, dfs(i, j))
        return ans
```

### **Java**

```java
class Solution {
    private int[][] grid;
    private boolean[][] vis;

    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        this.grid = grid;
        this.vis = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || vis[i][j]) {
            return 0;
        }
        vis[i][j] = true;
        int t = 0;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            t = Math.max(t, dfs(i + dir[0], j + dir[1]));
        }
        vis[i][j] = false;
        return t + grid[i][j];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> grid;
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int getMaximumGold(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        this->grid = grid;
        vector<vector<bool>> vis(m, vector<bool>(n, false));
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = max(ans, dfs(i, j, vis));
        return ans;
    }

    int dfs(int i, int j,  vector<vector<bool>>& vis) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[0].size() || grid[i][j] == 0 || vis[i][j]) return 0;
        vis[i][j] = true;
        int t = 0;
        for (auto& dir : dirs)
            t = max(t, dfs(i + dir[0], j + dir[1], vis));
        vis[i][j] = false;
        return t + grid[i][j];
    }
};
```

### **Go**

```go
func getMaximumGold(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}

	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0 || vis[i][j] {
			return 0
		}
		vis[i][j] = true
		dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
		t := 0
		for _, dir := range dirs {
			t = max(t, dfs(i+dir[0], j+dir[1]))
		}
		vis[i][j] = false
		return t + grid[i][j]
	}

	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans = max(ans, dfs(i, j))
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
