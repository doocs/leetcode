# [2435. Paths in Matrix Whose Sum Is Divisible by K](https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k)

[中文文档](/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>grid</code> and an integer <code>k</code>. You are currently at position <code>(0, 0)</code> and you want to reach position <code>(m - 1, n - 1)</code> moving only <strong>down</strong> or <strong>right</strong>.</p>

<p>Return<em> the number of paths where the sum of the elements on the path is divisible by </em><code>k</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220813183124-1.png" style="width: 437px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two paths where the sum of the elements on the path is divisible by k.
The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220817112930-3.png" style="height: 85px; width: 132px;" />
<pre>
<strong>Input:</strong> grid = [[0,0]], k = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220812224605-3.png" style="width: 257px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfPaths(self, grid: List[List[int]], k: int) -> int:
        @cache
        def dfs(i, j, s):
            if i < 0 or i >= m or j < 0 or j >= n:
                return 0
            s = (s + grid[i][j]) % k
            if i == m - 1 and j == n - 1:
                return int(s == 0)
            ans = dfs(i + 1, j, s) + dfs(i, j + 1, s)
            return ans % mod

        m, n = len(grid), len(grid[0])
        mod = 10**9 + 7
        ans = dfs(0, 0, 0)
        dfs.cache_clear()
        return ans
```

```python
class Solution:
    def numberOfPaths(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[[0] * k for _ in range(n)] for _ in range(m)]
        dp[0][0][grid[0][0] % k] = 1
        mod = 10**9 + 7
        for i in range(m):
            for j in range(n):
                for s in range(k):
                    t = ((s - grid[i][j] % k) + k) % k
                    if i:
                        dp[i][j][s] += dp[i - 1][j][t]
                    if j:
                        dp[i][j][s] += dp[i][j - 1][t]
                    dp[i][j][s] %= mod
        return dp[-1][-1][0]
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int k;
    private static final int MOD = (int) 1e9 + 7;
    private int[][] grid;
    private int[][][] f;

    public int numberOfPaths(int[][] grid, int k) {
        this.grid = grid;
        this.k = k;
        m = grid.length;
        n = grid[0].length;
        f = new int[m][n][k];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int s) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        s = (s + grid[i][j]) % k;
        if (f[i][j][s] != -1) {
            return f[i][j][s];
        }
        if (i == m - 1 && j == n - 1) {
            return s == 0 ? 1 : 0;
        }
        int ans = dfs(i + 1, j, s) + dfs(i, j + 1, s);
        ans %= MOD;
        f[i][j][s] = ans;
        return ans;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int s = 0; s < k; ++s) {
                    int t = ((s - grid[i][j] % k) + k) % k;
                    if (i > 0) {
                        dp[i][j][s] += dp[i - 1][j][t];
                    }
                    if (j > 0) {
                        dp[i][j][s] += dp[i][j - 1][t];
                    }
                    dp[i][j][s] %= MOD;
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int mod = 1e9 + 7;
        vector<vector<vector<int>>> f(m, vector<vector<int>>(n, vector<int>(k, -1)));
        function<int(int, int, int)> dfs;
        dfs = [&](int i, int j, int s) {
            if (i < 0 || i >= m || j < 0 || j >= n) return 0;
            s = (s + grid[i][j]) % k;
            if (i == m - 1 && j == n - 1) return s == 0 ? 1 : 0;
            if (f[i][j][s] != -1) return f[i][j][s];
            int ans = dfs(i + 1, j, s) + dfs(i, j + 1, s);
            ans %= mod;
            f[i][j][s] = ans;
            return ans;
        };
        return dfs(0, 0, 0);
    }
};
```

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<vector<int>>> dp(m, vector<vector<int>>(n, vector<int>(k)));
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int s = 0; s < k; ++s) {
                    int t = ((s - grid[i][j] % k) + k) % k;
                    if (i) dp[i][j][s] += dp[i - 1][j][t];
                    if (j) dp[i][j][s] += dp[i][j - 1][t];
                    dp[i][j][s] %= mod;
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
};
```

### **Go**

```go
func numberOfPaths(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	var mod int = 1e9 + 7
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k)
			for x := 0; x < k; x++ {
				f[i][j][x] = -1
			}
		}
	}
	var dfs func(i, j, s int) int
	dfs = func(i, j, s int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			return 0
		}
		s = (s + grid[i][j]) % k
		if i == m-1 && j == n-1 {
			if s == 0 {
				return 1
			}
			return 0
		}
		if f[i][j][s] != -1 {
			return f[i][j][s]
		}
		ans := dfs(i+1, j, s) + dfs(i, j+1, s)
		ans %= mod
		f[i][j][s] = ans
		return ans
	}
	return dfs(0, 0, 0)
}
```

```go
func numberOfPaths(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	var mod int = 1e9 + 7
	dp := make([][][]int, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, k)
		}
	}
	dp[0][0][grid[0][0]%k] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for s := 0; s < k; s++ {
				t := ((s - grid[i][j]%k) + k) % k
				if i > 0 {
					dp[i][j][s] += dp[i-1][j][t]
				}
				if j > 0 {
					dp[i][j][s] += dp[i][j-1][t]
				}
				dp[i][j][s] %= mod
			}
		}
	}
	return dp[m-1][n-1][0]
}
```

### **TypeScript**

```ts
function numberOfPaths(grid: number[][], k: number): number {
    const MOD = 10 ** 9 + 7;
    const m = grid.length,
        n = grid[0].length;
    let ans = Array.from({ length: m + 1 }, () =>
        Array.from({ length: n + 1 }, () => new Array(k).fill(0)),
    );
    ans[0][1][0] = 1;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            for (let v = 0; v < k; v++) {
                let key = (grid[i][j] + v) % k;
                ans[i + 1][j + 1][key] =
                    (ans[i][j + 1][v] +
                        ans[i + 1][j][v] +
                        ans[i + 1][j + 1][key]) %
                    MOD;
            }
        }
    }
    return ans[m][n][0];
}
```

### **...**

```

```

<!-- tabs:end -->
