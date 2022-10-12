# [329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix)

[中文文档](/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> integers <code>matrix</code>, return <em>the length of the longest increasing path in </em><code>matrix</code>.</p>

<p>From each cell, you can either move in four directions: left, right, up, or down. You <strong>may not</strong> move <strong>diagonally</strong> or move <strong>outside the boundary</strong> (i.e., wrap-around is not allowed).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/grid1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[9,9,4],[6,6,8],[2,1,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing path is <code>[1, 2, 6, 9]</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/tmp-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> matrix = [[3,4,5],[3,2,6],[2,2,1]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>The longest increasing path is <code>[3, 4, 5, 6]</code>. Moving diagonally is not allowed.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            ans = 1
            for a, b in [[-1, 0], [1, 0], [0, 1], [0, -1]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and matrix[x][y] > matrix[i][j]:
                    ans = max(ans, dfs(x, y) + 1)
            return ans

        m, n = len(matrix), len(matrix[0])
        return max(dfs(i, j) for i in range(m) for j in range(n))
```

### **Java**

```java
class Solution {
    private int[][] memo;
    private int[][] matrix;
    private int m;
    private int n;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                ans = Math.max(ans, dfs(x, y) + 1);
            }
        }
        memo[i][j] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> memo;
    vector<vector<int>> matrix;
    int m;
    int n;

    int longestIncreasingPath(vector<vector<int>>& matrix) {
        m = matrix.size();
        n = matrix[0].size();
        memo.resize(m, vector<int>(n, -1));
        this->matrix = matrix;
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = max(ans, dfs(i, j));
        return ans;
    }

    int dfs(int i, int j) {
        if (memo[i][j] != -1) return memo[i][j];
        int ans = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j])
                ans = max(ans, dfs(x, y) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }
};
```

### **Go**

```go
func longestIncreasingPath(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	memo := make([][]int, m)
	for i := range memo {
		memo[i] = make([]int, n)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	ans := -1
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if memo[i][j] != -1 {
			return memo[i][j]
		}
		ans := 1
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j] {
				ans = max(ans, dfs(x, y)+1)
			}
		}
		memo[i][j] = ans
		return ans
	}
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
