# [2328. Number of Increasing Paths in a Grid](https://leetcode.com/problems/number-of-increasing-paths-in-a-grid)

[中文文档](/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>, where you can move from a cell to any adjacent cell in all <code>4</code> directions.</p>

<p>Return <em>the number of <strong>strictly</strong> <strong>increasing</strong> paths in the grid such that you can start from <strong>any</strong> cell and end at <strong>any</strong> cell. </em>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two paths are considered different if they do not have exactly the same sequence of visited cells.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/images/griddrawio-4.png" style="width: 181px; height: 121px;" />
<pre>
<strong>Input:</strong> grid = [[1,1],[3,4]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The strictly increasing paths are:
- Paths with length 1: [1], [1], [3], [4].
- Paths with length 2: [1 -&gt; 3], [1 -&gt; 4], [3 -&gt; 4].
- Paths with length 3: [1 -&gt; 3 -&gt; 4].
The total number of paths is 4 + 3 + 1 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1],[2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The strictly increasing paths are:
- Paths with length 1: [1], [2].
- Paths with length 2: [1 -&gt; 2].
The total number of paths is 2 + 1 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            res = 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] > grid[i][j]:
                    res += dfs(x, y)
            return res

        m, n = len(grid), len(grid[0])
        mod = 10**9 + 7
        return sum(dfs(i, j) for i in range(m) for j in range(n)) % mod
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[][] g;
    private int[][] f;
    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        f = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = (ans + dfs(i, j)) % MOD;
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int res = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] > g[i][j]) {
                res = (res + dfs(x, y)) % MOD;
            }
        }
        f[i][j] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;
    int countPaths(vector<vector<int>>& grid) {
        int ans = 0;
        vector<vector<int>> f(grid.size(), vector<int>(grid[0].size()));
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                ans = (ans + dfs(i, j, f, grid)) % mod;
        return ans;
    }

    int dfs(int i, int j, vector<vector<int>>& f, vector<vector<int>>& g) {
        if (f[i][j]) return f[i][j];
        int res = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < g.size() && y >= 0 && y < g[0].size() && g[x][y] > g[i][j])
                res = (res + dfs(x, y, f, g)) % mod;
        }
        f[i][j] = res;
        return res;
    }
};
```

### **Go**

```go
func countPaths(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	mod := int(1e9) + 7
	ans := 0
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if f[i][j] > 0 {
			return f[i][j]
		}
		res := 1
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j] {
				res = (res + dfs(x, y)) % mod
			}
		}
		f[i][j] = res
		return res
	}
	for i, row := range grid {
		for j := range row {
			ans = (ans + dfs(i, j)) % mod
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countPaths(grid: number[][]): number {
    const mod = BigInt(10 ** 9 + 7);
    const dirs = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    const m = grid.length,
        n = grid[0].length;
    const dp = Array.from({ length: m }, v => new Array(n).fill(-1n));

    function dfs(x, y) {
        if (dp[x][y] != -1) return dp[x][y];
        let count = 1n;
        for (let [dx, dy] of dirs) {
            let i = x + dx,
                j = y + dy;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] <= grid[x][y])
                continue;
            count = (count + dfs(i, j)) % mod;
        }
        dp[x][y] = count;
        return count;
    }

    let sum = 0n;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            sum = (sum + dfs(i, j)) % mod;
        }
    }
    return Number(sum);
}
```

### **...**

```

```

<!-- tabs:end -->
