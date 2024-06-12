---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0827.Making%20A%20Large%20Island/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [827. 最大人工岛](https://leetcode.cn/problems/making-a-large-island)

[English Version](/solution/0800-0899/0827.Making%20A%20Large%20Island/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x n</code> 二进制矩阵 <code>grid</code> 。<strong>最多</strong> 只能将一格&nbsp;<code>0</code> 变成&nbsp;<code>1</code> 。</p>

<p>返回执行此操作后，<code>grid</code> 中最大的岛屿面积是多少？</p>

<p><strong>岛屿</strong> 由一组上、下、左、右四个方向相连的&nbsp;<code>1</code> 形成。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 0], [0, 1]]
<strong>输出:</strong> 3
<strong>解释:</strong> 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入: </strong>grid =<strong> </strong>[[1, 1], [1, 0]]
<strong>输出:</strong> 4
<strong>解释:</strong> 将一格0变成1，岛屿的面积扩大为 4。</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 1], [1, 1]]
<strong>输出:</strong> 4
<strong>解释:</strong> 没有0可以让我们变成1，面积依然为 4。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们可以给每个连通块一个唯一的标识，用数组 $p$ 记录每个位置所属的连通块，即 $p[i][j]$ 表示 $(i, j)$ 所属的连通块编号。用数组 $cnt$ 记录每个连通块的大小，即 $cnt[root]$ 表示连通块 $root$ 的大小。

首先，我们遍历整个矩阵，对于每个 $grid[i][j] = 1$ 且 $p[i][j] = 0$ 的位置，我们对其进行深度优先搜索，将其所属的连通块标记为 $root$，并统计连通块的大小。

接着，我们遍历整个矩阵，对于每个 $grid[i][j] = 0$ 的位置，我们找到其上、下、左、右四个位置所属的连通块，将这些连通块的大小相加，再加上当前位置的 $1$，即可得到将当前位置变为 $1$ 后的最大岛屿面积。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为矩阵的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int):
            p[i][j] = root
            cnt[root] += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] and p[x][y] == 0:
                    dfs(x, y)

        n = len(grid)
        cnt = Counter()
        p = [[0] * n for _ in range(n)]
        dirs = (-1, 0, 1, 0, -1)
        root = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x and p[i][j] == 0:
                    root += 1
                    dfs(i, j)
        ans = max(cnt.values() or [0])
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 0:
                    s = set()
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            s.add(p[x][y])
                    ans = max(ans, sum(cnt[root] for root in s) + 1)
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int root;
    private int[] cnt;
    private int[][] p;
    private int[][] grid;
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        p = new int[n][n];
        this.grid = grid;
        cnt = new int[n * n + 1];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && p[i][j] == 0) {
                    ++root;
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    Set<Integer> s = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            s.add(p[x][y]);
                        }
                    }
                    int t = 1;
                    for (int x : s) {
                        t += cnt[x];
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        p[i][j] = root;
        ++cnt[root];
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0) {
                dfs(x, y);
            }
        }
        return cnt[root];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        int p[n][n];
        memset(p, 0, sizeof(p));
        int cnt[n * n + 1];
        memset(cnt, 0, sizeof(cnt));
        const int dirs[5] = {-1, 0, 1, 0, -1};
        int root = 0;
        int ans = 0;
        function<int(int, int)> dfs = [&](int i, int j) {
            p[i][j] = root;
            ++cnt[root];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] && !p[x][y]) {
                    dfs(x, y);
                }
            }
            return cnt[root];
        };
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && !p[i][j]) {
                    ++root;
                    ans = max(ans, dfs(i, j));
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    unordered_set<int> s;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            s.insert(p[x][y]);
                        }
                    }
                    int t = 1;
                    for (int x : s) {
                        t += cnt[x];
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, n)
	}
	cnt := make([]int, n*n+1)
	dirs := []int{-1, 0, 1, 0, -1}
	root := 0
	ans := 0

	var dfs func(int, int) int
	dfs = func(i, j int) int {
		p[i][j] = root
		cnt[root]++
		for k := 0; k < 4; k++ {
			x := i + dirs[k]
			y := j + dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0 {
				dfs(x, y)
			}
		}
		return cnt[root]
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && p[i][j] == 0 {
				root++
				ans = max(ans, dfs(i, j))
			}
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				s := make(map[int]struct{})
				for k := 0; k < 4; k++ {
					x := i + dirs[k]
					y := j + dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						s[p[x][y]] = struct{}{}
					}
				}
				t := 1
				for x := range s {
					t += cnt[x]
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function largestIsland(grid: number[][]): number {
    const n = grid.length;
    const p = Array.from({ length: n }, () => Array(n).fill(0));
    const cnt = Array(n * n + 1).fill(0);
    const dirs = [-1, 0, 1, 0, -1];
    let root = 0;
    let ans = 0;

    const dfs = (i: number, j: number): number => {
        p[i][j] = root;
        cnt[root]++;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] === 1 && p[x][y] === 0) {
                dfs(x, y);
            }
        }
        return cnt[root];
    };

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1 && p[i][j] === 0) {
                root++;
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 0) {
                const s = new Set<number>();
                for (let k = 0; k < 4; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        s.add(p[x][y]);
                    }
                }
                let t = 1;
                for (const x of s) {
                    t += cnt[x];
                }
                ans = Math.max(ans, t);
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn largest_island(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut p = vec![vec![0; n]; n];
        let mut cnt = vec![0; n * n + 1];
        let dirs = [-1, 0, 1, 0, -1];
        let mut root = 0;
        let mut ans = 0;

        fn dfs(
            grid: &Vec<Vec<i32>>,
            p: &mut Vec<Vec<i32>>,
            cnt: &mut Vec<i32>,
            root: i32,
            i: usize,
            j: usize,
            dirs: &[i32; 5]
        ) -> i32 {
            p[i][j] = root;
            cnt[root as usize] += 1;
            for k in 0..4 {
                let x = (i as i32) + dirs[k];
                let y = (j as i32) + dirs[k + 1];
                if
                    x >= 0 &&
                    (x as usize) < grid.len() &&
                    y >= 0 &&
                    (y as usize) < grid.len() &&
                    grid[x as usize][y as usize] == 1 &&
                    p[x as usize][y as usize] == 0
                {
                    dfs(grid, p, cnt, root, x as usize, y as usize, dirs);
                }
            }
            cnt[root as usize]
        }

        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 1 && p[i][j] == 0 {
                    root += 1;
                    ans = ans.max(dfs(&grid, &mut p, &mut cnt, root, i, j, &dirs));
                }
            }
        }

        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 0 {
                    let mut s = HashSet::new();
                    for k in 0..4 {
                        let x = (i as i32) + dirs[k];
                        let y = (j as i32) + dirs[k + 1];
                        if x >= 0 && (x as usize) < n && y >= 0 && (y as usize) < n {
                            s.insert(p[x as usize][y as usize]);
                        }
                    }
                    let mut t = 1;
                    for &x in &s {
                        t += cnt[x as usize];
                    }
                    ans = ans.max(t);
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
