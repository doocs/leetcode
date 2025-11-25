---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/README_EN.md
rating: 1951
source: Weekly Contest 314 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [2435. Paths in Matrix Whose Sum Is Divisible by K](https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k)

[中文文档](/solution/2400-2499/2435.Paths%20in%20Matrix%20Whose%20Sum%20Is%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We denote the $k$ in the problem as $K$, and let $m$ and $n$ be the number of rows and columns of the matrix $\textit{grid}$, respectively.

Define $f[i][j][k]$ as the number of paths starting from $(0, 0)$, reaching position $(i, j)$, where the sum of elements along the path modulo $K$ equals $k$. Initially, $f[0][0][\textit{grid}[0][0] \bmod K] = 1$. The final answer is $f[m - 1][n - 1][0]$.

We can derive the state transition equation:

$$
f[i][j][k] = f[i - 1][j][(k - \textit{grid}[i][j])\bmod K] + f[i][j - 1][(k - \textit{grid}[i][j])\bmod K]
$$

To avoid issues with negative modulo operations, we can replace $(k - \textit{grid}[i][j])\bmod K$ in the above equation with $((k - \textit{grid}[i][j] \bmod K) + K) \bmod K$.

The answer is $f[m - 1][n - 1][0]$.

The time complexity is $O(m \times n \times K)$ and the space complexity is $O(m \times n \times K)$, where $m$ and $n$ are the number of rows and columns of the matrix $\textit{grid}$, respectively, and $K$ is the integer $k$ from the problem.

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
