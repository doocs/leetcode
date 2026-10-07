---
comments: true
difficulty: 困难
rating: 2001
source: 第 300 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
    - 记忆化
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [2328. 网格图中递增路径的数目](https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid)

[English Version](/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 严格递增路径可从任意格出发、任意长度。网格至多 $10^5$ 格，枚举路径不可行。从一格出发的路径数只取决于能走到的更大邻居。
>
> 记忆化 $dfs(i,j)$：自身一条，加上四个严格更大邻居的方案。每个格子计算一次，总和即为全部路径数。

<!-- thinking:end -->

我们设计一个函数 $dfs(i, j)$，表示从网格图中的第 $i$ 行第 $j$ 列的格子出发，能够到达任意格子的严格递增路径数目。那么答案就是 $\sum_{i=0}^{m-1} \sum_{j=0}^{n-1} dfs(i, j)$。搜索过程中，我们可以用一个二维数组 $f$ 记录已经计算过的结果，避免重复计算。

函数 $dfs(i, j)$ 的计算过程如下：

- 如果 $f[i][j]$ 不为 $0$，说明已经计算过，直接返回 $f[i][j]$；
- 否则，我们初始化 $f[i][j] = 1$，然后枚举 $(i, j)$ 的四个方向，如果某个方向的格子 $(x, y)$ 满足 $0 \leq x \lt m$, $0 \leq y \lt n$ 且 $grid[i][j] \lt grid[x][y]$，我们就可以从格子 $(i, j)$ 出发，到达格子 $(x, y)$，且路径上的数字是严格递增的，因此有 $f[i][j] += dfs(x, y)$。

最后，我们返回 $f[i][j]$。

答案为 $\sum_{i=0}^{m-1} \sum_{j=0}^{n-1} dfs(i, j)$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格图的行数和列数。

相似题目：

- [329. 矩阵中的最长递增路径](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README.md)。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            ans = 1
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[i][j] < grid[x][y]:
                    ans = (ans + dfs(x, y)) % mod
            return ans

        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        return sum(dfs(i, j) for i in range(m) for j in range(n)) % mod
```

#### Java

```java
class Solution {
    private int[][] f;
    private int[][] grid;
    private int m;
    private int n;
    private final int mod = (int) 1e9 + 7;

    public int countPaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        f = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = (ans + dfs(i, j)) % mod;
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int ans = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                ans = (ans + dfs(x, y)) % mod;
            }
        }
        return f[i][j] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        int f[m][n];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (f[i][j]) {
                return f[i][j];
            }
            int ans = 1;
            int dirs[5] = {-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                    ans = (ans + dfs(x, y)) % mod;
                }
            }
            return f[i][j] = ans;
        };
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = (ans + dfs(i, j)) % mod;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPaths(grid [][]int) (ans int) {
	const mod = 1e9 + 7
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if f[i][j] != 0 {
			return f[i][j]
		}
		f[i][j] = 1
		dirs := [5]int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y] {
				f[i][j] = (f[i][j] + dfs(x, y)) % mod
			}
		}
		return f[i][j]
	}
	for i, row := range grid {
		for j := range row {
			ans = (ans + dfs(i, j)) % mod
		}
	}
	return
}
```

#### TypeScript

```ts
function countPaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f = new Array(m).fill(0).map(() => new Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (f[i][j]) {
            return f[i][j];
        }
        let ans = 1;
        const dirs: number[] = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                ans = (ans + dfs(x, y)) % mod;
            }
        }
        return (f[i][j] = ans);
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans = (ans + dfs(i, j)) % mod;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：排序 + 动态规划

<!-- thinking:start -->

> **思考**
>
> 严格递增路径可从任意格出发。网格至多 $10^5$ 格。若从每个格子记忆化走向更大的邻居，一行严格递增的 $1000$ 个数就会把调用栈用尽，而 $m$ 和 $n$ 都可以到 $1000$。
>
> 瓶颈是这条递增链：每一步只进入尚未算完的更大邻居，深度与格子数同阶。
>
> 一格的方案数只依赖严格更大的邻居，这些边构成有向无环图。
>
> 因此按格子值从大到小处理。先令 $f[i][j]=1$，再把四个更大邻居已经算完的方案数加进来，并对 $10^9+7$ 取模。全部 $f$ 之和再取模，就是从任意格出发的路径总数。

<!-- thinking:end -->

令 $f[i][j]$ 表示从第 $i$ 行第 $j$ 列出发的严格递增路径数。一格至少有一条只含自己的路径，所以初值为 $1$。它还能走向四个方向上严格更大的邻居，因此

$$
f[i][j] = 1 + \sum_{\substack{(x,y)\sim(i,j)\\ grid[i][j] < grid[x][y]}} f[x][y] \pmod{10^9+7}.
$$

值更大的格子不依赖当前格子，按 $grid$ 从大到小处理时，这些 $f[x][y]$ 已经就绪。答案为 $\sum f[i][j]$ 对 $10^9+7$ 取模。

时间复杂度 $O(mn \log(mn))$，空间复杂度 $O(mn)$。其中 $m$ 和 $n$ 分别是网格图的行数和列数。

相似题目：

- [329. 矩阵中的最长递增路径](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README.md)。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[1] * n for _ in range(m)]
        cells = [(grid[i][j], i, j) for i in range(m) for j in range(n)]
        cells.sort(reverse=True)
        dirs = (-1, 0, 1, 0, -1)
        for _, i, j in cells:
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[i][j] < grid[x][y]:
                    f[i][j] = (f[i][j] + f[x][y]) % mod
        return sum(sum(row) for row in f) % mod
```

#### Java

```java
class Solution {
    public int countPaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        int[][] cells = new int[m * n][3];
        for (int i = 0, k = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j] = 1;
                cells[k][0] = grid[i][j];
                cells[k][1] = i;
                cells[k][2] = j;
                ++k;
            }
        }
        Arrays.sort(cells, (a, b) -> Integer.compare(b[0], a[0]));
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] cell : cells) {
            int i = cell[1], j = cell[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                    f[i][j] = (f[i][j] + f[x][y]) % mod;
                }
            }
        }
        long ans = 0;
        for (int[] row : f) {
            for (int v : row) {
                ans += v;
            }
        }
        return (int) (ans % mod);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(m, vector<int>(n, 1));
        vector<array<int, 3>> cells;
        cells.reserve(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cells.push_back({grid[i][j], i, j});
            }
        }
        sort(cells.begin(), cells.end(), [](const array<int, 3>& a, const array<int, 3>& b) {
            return a[0] > b[0];
        });
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (auto& cell : cells) {
            int i = cell[1], j = cell[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                    f[i][j] = (f[i][j] + f[x][y]) % mod;
                }
            }
        }
        long long ans = 0;
        for (auto& row : f) {
            for (int v : row) {
                ans += v;
            }
        }
        return ans % mod;
    }
};
```

#### Go

```go
func countPaths(grid [][]int) int {
	const mod = 1e9 + 7
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	cells := make([][3]int, 0, m*n)
	for i := 0; i < m; i++ {
		f[i] = make([]int, n)
		for j := 0; j < n; j++ {
			f[i][j] = 1
			cells = append(cells, [3]int{grid[i][j], i, j})
		}
	}
	sort.Slice(cells, func(a, b int) bool { return cells[a][0] > cells[b][0] })
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, cell := range cells {
		i, j := cell[1], cell[2]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y] {
				f[i][j] = (f[i][j] + f[x][y]) % mod
			}
		}
	}
	ans := 0
	for _, row := range f {
		for _, v := range row {
			ans = (ans + v) % mod
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countPaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(1));
    const cells: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            cells.push([grid[i][j], i, j]);
        }
    }
    cells.sort((a, b) => b[0] - a[0]);
    const dirs = [-1, 0, 1, 0, -1];
    for (const [_, i, j] of cells) {
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[i][j] < grid[x][y]) {
                f[i][j] = (f[i][j] + f[x][y]) % mod;
            }
        }
    }
    let ans = 0;
    for (const row of f) {
        for (const v of row) {
            ans = (ans + v) % mod;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
