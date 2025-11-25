---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/README.md
rating: 1951
source: 第 314 场周赛 Q4
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [2435. 矩阵中和能被 K 整除的路径](https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k)

[English Version](/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你从起点&nbsp;<code>(0, 0)</code>&nbsp;出发，每一步只能往 <strong>下</strong>&nbsp;或者往 <strong>右</strong>&nbsp;，你想要到达终点&nbsp;<code>(m - 1, n - 1)</code>&nbsp;。</p>

<p>请你返回路径和能被 <code>k</code>&nbsp;整除的路径数目，由于答案可能很大，返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220813183124-1.png" style="width: 437px; height: 200px;"></p>

<pre><b>输入：</b>grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
<b>输出：</b>2
<b>解释：</b>有两条路径满足路径上元素的和能被 k 整除。
第一条路径为上图中用红色标注的路径，和为 5 + 2 + 4 + 5 + 2 = 18 ，能被 3 整除。
第二条路径为上图中用蓝色标注的路径，和为 5 + 3 + 0 + 5 + 2 = 15 ，能被 3 整除。
</pre>

<p><strong>示例 2：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220817112930-3.png" style="height: 85px; width: 132px;">
<pre><b>输入：</b>grid = [[0,0]], k = 5
<b>输出：</b>1
<b>解释：</b>红色标注的路径和为 0 + 0 = 0 ，能被 5 整除。
</pre>

<p><strong>示例 3：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/images/image-20220812224605-3.png" style="width: 257px; height: 200px;">
<pre><b>输入：</b>grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
<b>输出：</b>10
<b>解释：</b>每个数字都能被 1 整除，所以每一条路径的和都能被 k 整除。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们记题目中的 $k$ 为 $K$，矩阵 $\textit{grid}$ 的行数和列数分别为 $m$ 和 $n$。

定义 $f[i][j][k]$ 表示从起点 $(0, 0)$ 出发，到达位置 $(i, j)$，且路径上元素和对 $K$ 取模等于 $k$ 的路径数目。初始时，$f[0][0][\textit{grid}[0][0] \bmod K] = 1$。 最终答案即为 $f[m - 1][n - 1][0]$。

我们可以得到状态转移方程：

$$
f[i][j][k] = f[i - 1][j][(k - \textit{grid}[i][j])\bmod K] + f[i][j - 1][(k - \textit{grid}[i][j])\bmod K]
$$

为了避免负数取模的问题，我们可以将上式中的 $(k - \textit{grid}[i][j])\bmod K$ 替换为 $((k - \textit{grid}[i][j] \bmod K) + K) \bmod K$。

答案即为 $f[m - 1][n - 1][0]$。

时间复杂度 $O(m \times n \times K)$，空间复杂度 $O(m \times n \times K)$。其中 $m$ 和 $n$ 分别是矩阵 $\textit{grid}$ 的行数和列数，而 $K$ 是题目中的整数 $k$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPaths(self, grid: List[List[int]], K: int) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[[0] * K for _ in range(n)] for _ in range(m)]
        f[0][0][grid[0][0] % K] = 1
        for i in range(m):
            for j in range(n):
                for k in range(K):
                    k0 = ((k - grid[i][j] % K) + K) % K
                    if i:
                        f[i][j][k] += f[i - 1][j][k0]
                    if j:
                        f[i][j][k] += f[i][j - 1][k0]
                    f[i][j][k] %= mod
        return f[m - 1][n - 1][0]
```

#### Java

```java
class Solution {
    public int numberOfPaths(int[][] grid, int K) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][K];
        f[0][0][grid[0][0] % K] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < K; ++k) {
                    int k0 = ((k - grid[i][j] % K) + K) % K;
                    if (i > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % mod;
                    }
                    if (j > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % mod;
                    }
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPaths(vector<vector<int>>& grid, int K) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        int f[m][n][K];
        memset(f, 0, sizeof(f));
        f[0][0][grid[0][0] % K] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < K; ++k) {
                    int k0 = ((k - grid[i][j] % K) + K) % K;
                    if (i > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % mod;
                    }
                    if (j > 0) {
                        f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % mod;
                    }
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
};
```

#### Go

```go
func numberOfPaths(grid [][]int, K int) int {
	const mod = 1e9 + 7
	m, n := len(grid), len(grid[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, K)
		}
	}
	f[0][0][grid[0][0]%K] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < K; k++ {
				k0 := ((k - grid[i][j]%K) + K) % K
				if i > 0 {
					f[i][j][k] = (f[i][j][k] + f[i-1][j][k0]) % mod
				}
				if j > 0 {
					f[i][j][k] = (f[i][j][k] + f[i][j-1][k0]) % mod
				}
			}
		}
	}
	return f[m-1][n-1][0]
}
```

#### TypeScript

```ts
function numberOfPaths(grid: number[][], K: number): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(K).fill(0)),
    );
    f[0][0][grid[0][0] % K] = 1;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < K; ++k) {
                const k0 = (k - (grid[i][j] % K) + K) % K;
                if (i > 0) {
                    f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % mod;
                }
                if (j > 0) {
                    f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % mod;
                }
            }
        }
    }
    return f[m - 1][n - 1][0];
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_paths(grid: Vec<Vec<i32>>, K: i32) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let m = grid.len();
        let n = grid[0].len();
        let K = K as usize;
        let mut f = vec![vec![vec![0; K]; n]; m];
        f[0][0][grid[0][0] as usize % K] = 1;
        for i in 0..m {
            for j in 0..n {
                for k in 0..K {
                    let k0 = ((k + K - grid[i][j] as usize % K) % K) as usize;
                    if i > 0 {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k0]) % MOD;
                    }
                    if j > 0 {
                        f[i][j][k] = (f[i][j][k] + f[i][j - 1][k0]) % MOD;
                    }
                }
            }
        }
        f[m - 1][n - 1][0]
    }
}
```

#### C#

```cs
public class Solution {
    public int NumberOfPaths(int[][] grid, int k) {
        const int mod = (int) 1e9 + 7;
        int m = grid.Length, n = grid[0].Length;
        int[,,] f = new int[m, n, k];
        f[0, 0, grid[0][0] % k] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int p = 0; p < k; ++p) {
                    int k0 = ((p - grid[i][j] % k) + k) % k;
                    if (i > 0) {
                        f[i, j, p] = (f[i, j, p] + f[i - 1, j, k0]) % mod;
                    }
                    if (j > 0) {
                        f[i, j, p] = (f[i, j, p] + f[i, j - 1, k0]) % mod;
                    }
                }
            }
        }
        return f[m - 1, n - 1, 0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
