# [2510. Check if There is a Path With Equal Number of 0's And 1's](https://leetcode.com/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s)

[中文文档](/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> <strong>binary</strong> matrix <code>grid</code>. You can move from a cell <code>(row, col)</code> to any of the cells <code>(row + 1, col)</code> or <code>(row, col + 1)</code>.</p>

<p>Return <code>true</code><em> if there is a path from </em><code>(0, 0)</code><em> to </em><code>(m - 1, n - 1)</code><em> that visits an <strong>equal</strong> number of </em><code>0</code><em>&#39;s and </em><code>1</code><em>&#39;s</em>. Otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/images/yetgriddrawio-4.png" />
<pre>
<strong>Input:</strong> grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The path colored in blue in the above diagram is a valid path because we have 3 cells with a value of 1 and 3 with a value of 0. Since there is a valid path, we return true.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/images/yetgrid2drawio-1.png" style="width: 151px; height: 151px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[0,0,1],[1,0,0]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no path in this grid with an equal number of 0&#39;s and 1&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        @cache
        def dfs(i, j, k):
            if i >= m or j >= n:
                return False
            k += grid[i][j]
            if k > s or i + j + 1 - k > s:
                return False
            if i == m - 1 and j == n - 1:
                return k == s
            return dfs(i + 1, j, k) or dfs(i, j + 1, k)

        m, n = len(grid), len(grid[0])
        s = m + n - 1
        if s & 1:
            return False
        s >>= 1
        return dfs(0, 0, 0)
```

### **Java**

```java
class Solution {
    private int s;
    private int m;
    private int n;
    private int[][] grid;
    private Boolean[][][] f;

    public boolean isThereAPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        s = m + n - 1;
        f = new Boolean[m][n][s];
        if (s % 2 == 1) {
            return false;
        }
        s >>= 1;
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return false;
        }
        k += grid[i][j];
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k > s || i + j + 1 - k > s) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return k == s;
        }
        f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
        return f[i][j][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int s = m + n - 1;
        if (s & 1) return false;
        int f[m][n][s];
        s >>= 1;
        memset(f, -1, sizeof f);
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (i >= m || j >= n) return false;
            k += grid[i][j];
            if (f[i][j][k] != -1) return f[i][j][k];
            if (k > s || i + j + 1 - k > s) return false;
            if (i == m - 1 && j == n - 1) return k == s;
            f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
            return f[i][j][k];
        };
        return dfs(0, 0, 0);
    }
};
```

### **Go**

```go
func isThereAPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	s := m + n - 1
	if s%2 == 1 {
		return false
	}
	s >>= 1
	f := [100][100][200]int{}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if i >= m || j >= n {
			return false
		}
		k += grid[i][j]
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		if k > s || i+j+1-k > s {
			return false
		}
		if i == m-1 && j == n-1 {
			return k == s
		}
		res := dfs(i+1, j, k) || dfs(i, j+1, k)
		if res {
			f[i][j][k] = 1
		}
		return res
	}
	return dfs(0, 0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
