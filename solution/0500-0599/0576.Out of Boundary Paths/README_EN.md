---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [576. Out of Boundary Paths](https://leetcode.com/problems/out-of-boundary-paths)

[中文文档](/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/README.md)

## Description

<!-- description:start -->

<p>There is an <code>m x n</code> grid with a ball. The ball is initially at the position <code>[startRow, startColumn]</code>. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply <strong>at most</strong> <code>maxMove</code> moves to the ball.</p>

<p>Given the five integers <code>m</code>, <code>n</code>, <code>maxMove</code>, <code>startRow</code>, <code>startColumn</code>, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_1.png" style="width: 500px; height: 296px;" />
<pre>
<strong>Input:</strong> m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
<strong>Output:</strong> 6
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_2.png" style="width: 500px; height: 293px;" />
<pre>
<strong>Input:</strong> m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
<strong>Output:</strong> 12
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= maxMove &lt;= 50</code></li>
	<li><code>0 &lt;= startRow &lt; m</code></li>
	<li><code>0 &lt;= startColumn &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We define a function $\textit{dfs}(i, j, k)$ to represent the number of paths that can move out of the boundary starting from coordinates $(i, j)$ with $k$ steps remaining.

In the function $\textit{dfs}(i, j, k)$, we first handle the boundary cases. If the current coordinates $(i, j)$ are out of the grid range, return $1$ if $k \geq 0$, otherwise return $0$. If $k \leq 0$, it means we are still within the grid but have no remaining moves, so return $0$. Next, we iterate over the four directions, move to the next coordinates $(x, y)$, then recursively call $\textit{dfs}(x, y, k - 1)$, and accumulate the results to the answer.

In the main function, we call $\textit{dfs}(startRow, startColumn, maxMove)$, which represents the number of paths that can move out of the boundary starting from the initial coordinates $(\textit{startRow}, \textit{startColumn})$ with $\textit{maxMove}$ steps remaining.

To avoid redundant calculations, we can use memoization.

The time complexity is $O(m \times n \times k)$, and the space complexity is $O(m \times n \times k)$. Here, $m$ and $n$ are the number of rows and columns of the grid, and $k$ is the number of steps that can be moved, with $k = \textit{maxMove} \leq 50$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPaths(
        self, m: int, n: int, maxMove: int, startRow: int, startColumn: int
    ) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if not 0 <= i < m or not 0 <= j < n:
                return int(k >= 0)
            if k <= 0:
                return 0
            ans = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                ans = (ans + dfs(x, y, k - 1)) % mod
            return ans

        mod = 10**9 + 7
        dirs = (-1, 0, 1, 0, -1)
        return dfs(startRow, startColumn, maxMove)
```

#### Java

```java
class Solution {
    private int m, n;
    private Integer[][][] f;
    private final int mod = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        f = new Integer[m][n][maxMove + 1];
        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return k >= 0 ? 1 : 0;
        }
        if (k <= 0) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = 0;
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            ans = (ans + dfs(x, y, k - 1)) % mod;
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int f[m][n][maxMove + 1];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return k >= 0;
            }
            if (k <= 0) {
                return 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = 0;
            for (int d = 0; d < 4; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                ans = (ans + dfs(x, y, k - 1)) % mod;
            }
            return f[i][j][k] = ans;
        };
        return dfs(startRow, startColumn, maxMove);
    }
};
```

#### Go

```go
func findPaths(m int, n int, maxMove int, startRow int, startColumn int) int {
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, maxMove+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	const mod int = 1e9 + 7
	var dfs func(int, int, int) int
	dirs := [5]int{-1, 0, 1, 0, -1}
	dfs = func(i, j, k int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			if k >= 0 {
				return 1
			}
			return 0
		}
		if k <= 0 {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		ans := 0
		for d := 0; d < 4; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			ans = (ans + dfs(x, y, k-1)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(startRow, startColumn, maxMove)
}
```

#### TypeScript

```ts
function findPaths(
    m: number,
    n: number,
    maxMove: number,
    startRow: number,
    startColumn: number,
): number {
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(maxMove + 1).fill(-1)),
    );
    const mod = 1000000007;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return k >= 0 ? 1 : 0;
        }
        if (k <= 0) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        let ans = 0;
        for (let d = 0; d < 4; ++d) {
            const [x, y] = [i + dirs[d], j + dirs[d + 1]];
            ans = (ans + dfs(x, y, k - 1)) % mod;
        }
        return (f[i][j][k] = ans);
    };
    return dfs(startRow, startColumn, maxMove);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
