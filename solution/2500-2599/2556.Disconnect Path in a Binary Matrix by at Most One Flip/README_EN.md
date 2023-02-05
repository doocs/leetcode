# [2556. Disconnect Path in a Binary Matrix by at Most One Flip](https://leetcode.com/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip)

[中文文档](/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> <strong>binary</strong> matrix <code>grid</code>. You can move from a cell <code>(row, col)</code> to any of the cells <code>(row + 1, col)</code> or <code>(row, col + 1)</code> that has the value <code>1</code>.&nbsp;The matrix is <strong>disconnected</strong> if there is no path from <code>(0, 0)</code> to <code>(m - 1, n - 1)</code>.</p>

<p>You can flip the value of <strong>at most one</strong> (possibly none) cell. You <strong>cannot flip</strong> the cells <code>(0, 0)</code> and <code>(m - 1, n - 1)</code>.</p>

<p>Return <code>true</code> <em>if it is possible to make the matrix disconnect or </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note</strong> that flipping a cell changes its value from <code>0</code> to <code>1</code> or from <code>1</code> to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/images/yetgrid2drawio.png" style="width: 441px; height: 151px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,0,0],[1,1,1]]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can change the cell shown in the diagram above. There is no path from (0, 0) to (2, 2) in the resulting grid.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/images/yetgrid3drawio.png" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,0,1],[1,1,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is not possible to change at most one cell such that there is not path from (0, 0) to (2, 2).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPossibleToCutPath(self, grid: List[List[int]]) -> bool:
        def dfs(i, j):
            if i >= m or j >= n or grid[i][j] == 0:
                return False
            grid[i][j] = 0
            if i == m - 1 and j == n - 1:
                return True
            return dfs(i + 1, j) or dfs(i, j + 1)

        m, n = len(grid), len(grid[0])
        a = dfs(0, 0)
        grid[0][0] = grid[-1][-1] = 1
        b = dfs(0, 0)
        return not (a and b)
```

### **Java**

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public boolean isPossibleToCutPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        boolean a = dfs(0, 0);
        grid[0][0] = 1;
        grid[m - 1][n - 1] = 1;
        boolean b = dfs(0, 0);
        return !(a && b);
    }

    private boolean dfs(int i, int j) {
        if (i >= m || j >= n || grid[i][j] == 0) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        grid[i][j] = 0;
        return dfs(i + 1, j) || dfs(i, j + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPossibleToCutPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        function<bool(int, int)> dfs = [&](int i, int j) -> bool {
            if (i >= m || j >= n || grid[i][j] == 0) {
                return false;
            }
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            grid[i][j] = 0;
            return dfs(i + 1, j) || dfs(i, j + 1);
        };
        bool a = dfs(0, 0);
        grid[0][0] = grid[m - 1][n - 1] = 1;
        bool b = dfs(0, 0);
        return !(a && b);
    }
};
```

### **Go**

```go
func isPossibleToCutPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i >= m || j >= n || grid[i][j] == 0 {
			return false
		}
		if i == m-1 && j == n-1 {
			return true
		}
		grid[i][j] = 0
		return dfs(i+1, j) || dfs(i, j+1)
	}
	a := dfs(0, 0)
	grid[0][0], grid[m-1][n-1] = 1, 1
	b := dfs(0, 0)
	return !(a && b)
}
```

### **...**

```

```

<!-- tabs:end -->
