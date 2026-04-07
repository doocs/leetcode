---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README_EN.md
rating: 1807
source: Weekly Contest 207 Q3
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [1594. Maximum Non Negative Product in a Matrix](https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix)

[中文文档](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>m x n</code> matrix <code>grid</code>. Initially, you are located at the top-left corner <code>(0, 0)</code>, and in each step, you can only <strong>move right or down</strong> in the matrix.</p>

<p>Among all possible paths starting from the top-left corner <code>(0, 0)</code> and ending in the bottom-right corner <code>(m - 1, n - 1)</code>, find the path with the <strong>maximum non-negative product</strong>. The product of a path is the product of all integers in the grid cells visited along the path.</p>

<p>Return the <em>maximum non-negative product <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>. <em>If the maximum product is <strong>negative</strong>, return </em><code>-1</code>.</p>

<p>Notice that the modulo is performed after getting the maximum product.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product2.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product3.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[1,3],[0,-4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Maximum non-negative product is shown (1 * 0 * -4 = 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define a 3D array $f$, where $f[i][j][0]$ and $f[i][j][1]$ represent the minimum and maximum product of all paths from the top-left corner $(0, 0)$ to position $(i, j)$, respectively. For each position $(i, j)$, we can transition from above $(i - 1, j)$ or from the left $(i, j - 1)$, so we need to consider the results of multiplying the minimum and maximum products of these two paths by the value of the current cell.

Finally, we need to return $f[m - 1][n - 1][1]$ modulo $10^9 + 7$. If $f[m - 1][n - 1][1]$ is less than $0$, return $-1$.

The time complexity is $O(m \times n)$ and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProductPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[[0, 0] for _ in range(n)] for _ in range(m)]

        for i in range(m):
            for j in range(n):
                x = grid[i][j]
                if i == 0 and j == 0:
                    f[i][j][0] = x
                    f[i][j][1] = x
                    continue

                mn, mx = inf, -inf

                if i > 0:
                    a, b = f[i - 1][j]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                if j > 0:
                    a, b = f[i][j - 1]
                    mn = min(mn, a * x, b * x)
                    mx = max(mx, a * x, b * x)

                f[i][j][0], f[i][j][1] = mn, mx

        ans = f[m - 1][n - 1][1]
        mod = 10**9 + 7
        return -1 if ans < 0 else ans % mod
```

#### Java

```java
class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m][n][2];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long x = grid[i][j];

                if (i == 0 && j == 0) {
                    f[i][j][0] = x;
                    f[i][j][1] = x;
                    continue;
                }

                long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;

                if (i > 0) {
                    long a = f[i - 1][j][0], b = f[i - 1][j][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                if (j > 0) {
                    long a = f[i][j - 1][0], b = f[i][j - 1][1];
                    mn = Math.min(mn, Math.min(a * x, b * x));
                    mx = Math.max(mx, Math.max(a * x, b * x));
                }

                f[i][j][0] = mn;
                f[i][j][1] = mx;
            }
        }

        long ans = f[m - 1][n - 1][1];
        int mod = (int) 1e9 + 7;
        return ans < 0 ? -1 : (int) (ans % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProductPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<array<long long, 2>>> f(m, vector<array<long long, 2>>(n));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                long long x = grid[i][j];
                if (i == 0 && j == 0) {
                    f[i][j] = {x, x};
                    continue;
                }

                long long mn = LLONG_MAX, mx = LLONG_MIN;

                if (i > 0) {
                    auto [a, b] = f[i - 1][j];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                if (j > 0) {
                    auto [a, b] = f[i][j - 1];
                    mn = min(mn, min(a * x, b * x));
                    mx = max(mx, max(a * x, b * x));
                }

                f[i][j] = {mn, mx};
            }
        }

        long long ans = f[m - 1][n - 1][1];
        const int mod = 1e9 + 7;
        return ans < 0 ? -1 : ans % mod;
    }
};
```

#### Go

```go
func maxProductPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][][2]int64, m)
	for i := range f {
		f[i] = make([][2]int64, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := int64(grid[i][j])
			if i == 0 && j == 0 {
				f[i][j] = [2]int64{x, x}
				continue
			}

			mn, mx := int64(1<<63-1), int64(-1<<63)

			if i > 0 {
				a, b := f[i-1][j][0], f[i-1][j][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			if j > 0 {
				a, b := f[i][j-1][0], f[i][j-1][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			f[i][j] = [2]int64{mn, mx}
		}
	}

	ans := f[m-1][n-1][1]
	mod := int64(1e9 + 7)
	if ans < 0 {
		return -1
	}
	return int(ans % mod)
}
```

#### TypeScript

```ts
function maxProductPath(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => [0, 0]));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];

            if (i === 0 && j === 0) {
                f[i][j] = [x, x];
                continue;
            }

            let mn = Infinity,
                mx = -Infinity;

            if (i > 0) {
                const [a, b] = f[i - 1][j];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            if (j > 0) {
                const [a, b] = f[i][j - 1];
                mn = Math.min(mn, a * x, b * x);
                mx = Math.max(mx, a * x, b * x);
            }

            f[i][j] = [mn, mx];
        }
    }

    const ans = f[m - 1][n - 1][1];
    const mod = 1e9 + 7;
    return ans < 0 ? -1 : ans % mod;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_product_path(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut f = vec![vec![[0i64; 2]; n]; m];

        for i in 0..m {
            for j in 0..n {
                let x = grid[i][j] as i64;

                if i == 0 && j == 0 {
                    f[i][j] = [x, x];
                    continue;
                }

                let mut mn = i64::MAX;
                let mut mx = i64::MIN;

                if i > 0 {
                    let [a, b] = f[i - 1][j];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                if j > 0 {
                    let [a, b] = f[i][j - 1];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                f[i][j] = [mn, mx];
            }
        }

        let ans = f[m - 1][n - 1][1];
        let mod_val = 1_000_000_007i64;
        if ans < 0 {
            -1
        } else {
            (ans % mod_val) as i32
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
