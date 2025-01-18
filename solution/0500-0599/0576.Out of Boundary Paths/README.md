---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/README.md
tags:
    - 动态规划
---

<!-- problem:start -->

# [576. 出界的路径数](https://leetcode.cn/problems/out-of-boundary-paths)

[English Version](/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的网格和一个球。球的起始坐标为 <code>[startRow, startColumn]</code> 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 <strong>最多</strong> 可以移动 <code>maxMove</code> 次球。</p>

<p>给你五个整数 <code>m</code>、<code>n</code>、<code>maxMove</code>、<code>startRow</code> 以及 <code>startColumn</code> ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_1.png" style="width: 500px; height: 296px;" />
<pre>
<strong>输入：</strong>m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_2.png" style="width: 500px; height: 293px;" />
<pre>
<strong>输入：</strong>m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= maxMove &lt;= 50</code></li>
	<li><code>0 &lt;= startRow &lt; m</code></li>
	<li><code>0 &lt;= startColumn &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们定义一个函数 $\textit{dfs}(i, j, k)$ 表示从坐标 $(i, j)$ 出发，还剩下 $k$ 步可以移动的情况下，可以移出边界的路径数量。

在函数 $\textit{dfs}(i, j, k)$ 中，我们首先处理边界情况，如果当前坐标 $(i, j)$ 不在网格范围内，如果 $k \geq 0$，则返回 $1$，否则返回 $0$。如果 $k \leq 0$，说明还在网格内，但是已经没有移动次数了，返回 $0$。接下来，我们遍历四个方向，移动到下一个坐标 $(x, y)$，然后递归调用 $\textit{dfs}(x, y, k - 1)$，并将结果累加到答案中。

在主函数中，我们调用 $\textit{dfs}(startRow, startColumn, maxMove)$，即从起始坐标 $(\textit{startRow}, \textit{startColumn})$ 出发，还剩下 $\textit{maxMove}$ 步可以移动的情况下，可以移出边界的路径数量。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(m \times n \times k)$，空间复杂度 $O(m \times n \times k)$。其中 $m$ 和 $n$ 分别是网格的行数和列数，而 $k$ 是可以移动的步数，本题中 $k = \textit{maxMove} \leq 50$。

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
