# [694. Number of Distinct Islands](https://leetcode.com/problems/number-of-distinct-islands)

[中文文档](/solution/0600-0699/0694.Number%20of%20Distinct%20Islands/README.md)

## Description

<p>You are given an <code>m x n</code> binary matrix <code>grid</code>. An island is a group of <code>1</code>&#39;s (representing land) connected <strong>4-directionally</strong> (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>

<p>An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.</p>

<p>Return <em>the number of <b>distinct</b> islands</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0694.Number%20of%20Distinct%20Islands/images/distinctisland1-1-grid.jpg" style="width: 413px; height: 334px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0694.Number%20of%20Distinct%20Islands/images/distinctisland1-2-grid.jpg" style="width: 413px; height: 334px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numDistinctIslands(self, grid: List[List[int]]) -> int:
        def dfs(i, j, direction, path):
            grid[i][j] = 0
            path.append(str(direction))
            dirs = [-1, 0, 1, 0, -1]
            for k in range(1, 5):
                x, y = i + dirs[k - 1], j + dirs[k]
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y, k, path)
            path.append(str(-direction))

        paths = set()
        path = []
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    dfs(i, j, 0, path)
                    paths.add(''.join(path))
                    path.clear()
        return len(paths)
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        Set<String> paths = new HashSet<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, 0, path);
                    paths.add(path.toString());
                }
            }
        }
        return paths.size();
    }

    private void dfs(int i, int j, int direction, StringBuilder path) {
        grid[i][j] = 0;
        path.append(direction);
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1];
            int y = j + dirs[k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, k, path);
            }
        }
        path.append(direction);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numDistinctIslands(vector<vector<int>>& grid) {
        unordered_set<string> paths;
        string path;
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == 1) {
                    path = "";
                    dfs(i, j, 0, grid, path);
                    paths.insert(path);
                }
            }
        }
        return paths.size();
    }

    void dfs(int i, int j, int direction, vector<vector<int>>& grid, string& path) {
        grid[i][j] = 0;
        path += to_string(direction);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 1; k < 5; ++k) {
            int x = i + dirs[k - 1], y = j + dirs[k];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, k, grid, path);
        }
        path += to_string(direction);
    }
};
```

### **Go**

```go
func numDistinctIslands(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	paths := make(map[string]bool)
	path := ""
	var dfs func(i, j, direction int)
	dfs = func(i, j, direction int) {
		grid[i][j] = 0
		path += strconv.Itoa(direction)
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 1; k < 5; k++ {
			x, y := i+dirs[k-1], j+dirs[k]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y, k)
			}
		}
		path += strconv.Itoa(direction)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				path = ""
				dfs(i, j, 0)
				paths[path] = true
			}
		}
	}
	return len(paths)
}
```

### **...**

```

```

<!-- tabs:end -->
