# [2328. 网格图中递增路径的数目](https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid)

[English Version](/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的整数网格图&nbsp;<code>grid</code>&nbsp;，你可以从一个格子移动到&nbsp;<code>4</code>&nbsp;个方向相邻的任意一个格子。</p>

<p>请你返回在网格图中从 <strong>任意</strong>&nbsp;格子出发，达到 <strong>任意</strong>&nbsp;格子，且路径中的数字是 <strong>严格递增</strong>&nbsp;的路径数目。由于答案可能会很大，请将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>如果两条路径中访问过的格子不是完全相同的，那么它们视为两条不同的路径。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/images/griddrawio-4.png" style="width: 181px; height: 121px;"></p>

<pre><b>输入：</b>grid = [[1,1],[3,4]]
<b>输出：</b>8
<b>解释：</b>严格递增路径包括：
- 长度为 1 的路径：[1]，[1]，[3]，[4] 。
- 长度为 2 的路径：[1 -&gt; 3]，[1 -&gt; 4]，[3 -&gt; 4] 。
- 长度为 3 的路径：[1 -&gt; 3 -&gt; 4] 。
路径数目为 4 + 3 + 1 = 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>grid = [[1],[2]]
<b>输出：</b>3
<b>解释：</b>严格递增路径包括：
- 长度为 1 的路径：[1]，[2] 。
- 长度为 2 的路径：[1 -&gt; 2] 。
路径数目为 2 + 1 = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

时间复杂度 $O(mn)$。

相似题目：[329. 矩阵中的最长递增路径](/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README.md)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
