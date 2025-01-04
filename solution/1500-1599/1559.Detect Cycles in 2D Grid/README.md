---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/README.md
rating: 1837
source: 第 33 场双周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [1559. 二维网格图中探测环](https://leetcode.cn/problems/detect-cycles-in-2d-grid)

[English Version](/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维字符网格数组&nbsp;<code>grid</code>&nbsp;，大小为&nbsp;<code>m x n</code>&nbsp;，你需要检查&nbsp;<code>grid</code>&nbsp;中是否存在 <strong>相同值</strong> 形成的环。</p>

<p>一个环是一条开始和结束于同一个格子的长度 <strong>大于等于 4</strong>&nbsp;的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 <strong>相同的值&nbsp;</strong>。</p>

<p>同时，你也不能回到上一次移动时所在的格子。比方说，环&nbsp;&nbsp;<code>(1, 1) -&gt; (1, 2) -&gt; (1, 1)</code>&nbsp;是不合法的，因为从 <code>(1, 2)</code>&nbsp;移动到 <code>(1, 1)</code> 回到了上一次移动时的格子。</p>

<p>如果 <code>grid</code>&nbsp;中有相同值形成的环，请你返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e1.png" style="height: 152px; width: 231px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;]]
<strong>输出：</strong>true
<strong>解释：</strong>如下图所示，有 2 个用不同颜色标出来的环：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e11.png" style="height: 163px; width: 225px;">
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e2.png" style="height: 154px; width: 236px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;c&quot;,&quot;c&quot;,&quot;c&quot;,&quot;a&quot;],[&quot;c&quot;,&quot;d&quot;,&quot;c&quot;,&quot;c&quot;],[&quot;c&quot;,&quot;c&quot;,&quot;e&quot;,&quot;c&quot;],[&quot;f&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]]
<strong>输出：</strong>true
<strong>解释：</strong>如下图所示，只有高亮所示的一个合法环：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e22.png" style="height: 157px; width: 229px;">
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e3.png" style="height: 120px; width: 183px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;z&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;b&quot;,&quot;a&quot;]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>grid</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以遍历二维网格中的每一个格子，对于每一个格子，如果格子 $grid[i][j]$ 未被访问过，我们就从该格子开始进行广度优先搜索，搜索过程中，我们需要记录每一个格子的父节点，以及上一个格子的坐标，如果下一个格子的值与当前格子的值相同，且不是上一个格子，并且已经被访问过，那么就说明存在环，返回 $\textit{true}$。遍历完所有格子后，如果没有找到环，返回 $\textit{false}$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是二维网格的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]
        dirs = (-1, 0, 1, 0, -1)
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if vis[i][j]:
                    continue
                vis[i][j] = True
                q = [(i, j, -1, -1)]
                while q:
                    x, y, px, py = q.pop()
                    for dx, dy in pairwise(dirs):
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < m and 0 <= ny < n:
                            if grid[nx][ny] != grid[i][j] or (nx == px and ny == py):
                                continue
                            if vis[nx][ny]:
                                return True
                            vis[nx][ny] = True
                            q.append((nx, ny, x, y))
        return False
```

#### Java

```java
class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j]) {
                    Deque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {i, j, -1, -1});
                    vis[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0], y = p[1], px = p[2], py = p[3];
                        for (int k = 0; k < 4; ++k) {
                            int nx = x + dirs[k], ny = y + dirs[k + 1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if (grid[nx][ny] != grid[x][y] || (nx == px && ny == py)) {
                                    continue;
                                }
                                if (vis[nx][ny]) {
                                    return true;
                                }
                                q.offer(new int[] {nx, ny, x, y});
                                vis[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsCycle(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> vis(m, vector<bool>(n));
        const vector<int> dirs = {-1, 0, 1, 0, -1};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j]) {
                    queue<array<int, 4>> q;
                    q.push({i, j, -1, -1});
                    vis[i][j] = true;

                    while (!q.empty()) {
                        auto p = q.front();
                        q.pop();
                        int x = p[0], y = p[1], px = p[2], py = p[3];

                        for (int k = 0; k < 4; ++k) {
                            int nx = x + dirs[k], ny = y + dirs[k + 1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if (grid[nx][ny] != grid[x][y] || (nx == px && ny == py)) {
                                    continue;
                                }
                                if (vis[nx][ny]) {
                                    return true;
                                }
                                q.push({nx, ny, x, y});
                                vis[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func containsCycle(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if !vis[i][j] {
				q := [][]int{{i, j, -1, -1}}
				vis[i][j] = true

				for len(q) > 0 {
					p := q[0]
					q = q[1:]
					x, y, px, py := p[0], p[1], p[2], p[3]

					for k := 0; k < 4; k++ {
						nx, ny := x+dirs[k], y+dirs[k+1]
						if nx >= 0 && nx < m && ny >= 0 && ny < n {
							if grid[nx][ny] != grid[x][y] || (nx == px && ny == py) {
								continue
							}
							if vis[nx][ny] {
								return true
							}
							q = append(q, []int{nx, ny, x, y})
							vis[nx][ny] = true
						}
					}
				}
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsCycle(grid: string[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!vis[i][j]) {
                const q: [number, number, number, number][] = [[i, j, -1, -1]];
                vis[i][j] = true;
                for (const [x, y, px, py] of q) {
                    for (let k = 0; k < 4; k++) {
                        const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            if (grid[nx][ny] !== grid[x][y] || (nx === px && ny === py)) {
                                continue;
                            }
                            if (vis[nx][ny]) {
                                return true;
                            }
                            q.push([nx, ny, x, y]);
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn contains_cycle(grid: Vec<Vec<char>>) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let mut vis = vec![vec![false; n]; m];
        let dirs = vec![-1, 0, 1, 0, -1];

        for i in 0..m {
            for j in 0..n {
                if !vis[i][j] {
                    let mut q = vec![(i as isize, j as isize, -1, -1)];
                    vis[i][j] = true;

                    while !q.is_empty() {
                        let (x, y, px, py) = q.pop().unwrap();

                        for k in 0..4 {
                            let nx = x + dirs[k];
                            let ny = y + dirs[k + 1];
                            if nx >= 0 && nx < m as isize && ny >= 0 && ny < n as isize {
                                let nx = nx as usize;
                                let ny = ny as usize;
                                if grid[nx][ny] != grid[x as usize][y as usize]
                                    || (nx == px as usize && ny == py as usize)
                                {
                                    continue;
                                }
                                if vis[nx][ny] {
                                    return true;
                                }
                                q.push((nx as isize, ny as isize, x, y));
                                vis[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {character[][]} grid
 * @return {boolean}
 */
var containsCycle = function (grid) {
    const [m, n] = [grid.length, grid[0].length];
    const vis = Array.from({ length: m }, () => Array(n).fill(false));
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!vis[i][j]) {
                const q = [[i, j, -1, -1]];
                vis[i][j] = true;
                for (const [x, y, px, py] of q) {
                    for (let k = 0; k < 4; k++) {
                        const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            if (grid[nx][ny] !== grid[x][y] || (nx === px && ny === py)) {
                                continue;
                            }
                            if (vis[nx][ny]) {
                                return true;
                            }
                            q.push([nx, ny, x, y]);
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }
    return false;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：DFS

我们可以遍历二维网格中的每一个格子，对于每一个格子，如果格子 $grid[i][j]$ 未被访问过，我们就从该格子开始进行深度优先搜索，搜索过程中，我们需要记录每一个格子的父节点，以及上一个格子的坐标，如果下一个格子的值与当前格子的值相同，且不是上一个格子，并且已经被访问过，那么就说明存在环，返回 $\textit{true}$。遍历完所有格子后，如果没有找到环，返回 $\textit{false}$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是二维网格的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        def dfs(x: int, y: int, px: int, py: int) -> bool:
            vis[x][y] = True
            for dx, dy in pairwise(dirs):
                nx, ny = x + dx, y + dy
                if 0 <= nx < m and 0 <= ny < n:
                    if grid[nx][ny] != grid[x][y] or (nx == px and ny == py):
                        continue
                    if vis[nx][ny] or dfs(nx, ny, x, y):
                        return True
            return False

        m, n = len(grid), len(grid[0])
        vis = [[False] * n for _ in range(m)]
        dirs = (-1, 0, 1, 0, -1)
        for i in range(m):
            for j in range(n):
                if vis[i][j]:
                    continue
                if dfs(i, j, -1, -1):
                    return True
        return False
```

#### Java

```java
class Solution {
    private char[][] grid;
    private boolean[][] vis;
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private int m;
    private int n;

    public boolean containsCycle(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j] && dfs(i, j, -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int px, int py) {
        vis[x][y] = true;
        for (int k = 0; k < 4; ++k) {
            int nx = x + dirs[k], ny = y + dirs[k + 1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (grid[nx][ny] != grid[x][y] || (nx == px && ny == py)) {
                    continue;
                }
                if (vis[nx][ny] || dfs(nx, ny, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsCycle(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> vis(m, vector<bool>(n));
        const vector<int> dirs = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int x, int y, int px, int py) -> bool {
            vis[x][y] = true;
            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (grid[nx][ny] != grid[x][y] || (nx == px && ny == py)) {
                        continue;
                    }
                    if (vis[nx][ny] || dfs(nx, ny, x, y)) {
                        return true;
                    }
                }
            }
            return false;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j] && dfs(i, j, -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func containsCycle(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(x, y, px, py int) bool
	dfs = func(x, y, px, py int) bool {
		vis[x][y] = true
		for k := 0; k < 4; k++ {
			nx, ny := x+dirs[k], y+dirs[k+1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n {
				if grid[nx][ny] != grid[x][y] || (nx == px && ny == py) {
					continue
				}
				if vis[nx][ny] || dfs(nx, ny, x, y) {
					return true
				}
			}
		}
		return false
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if !vis[i][j] && dfs(i, j, -1, -1) {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsCycle(grid: string[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
    const dfs = (x: number, y: number, px: number, py: number): boolean => {
        vis[x][y] = true;
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; k++) {
            const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (grid[nx][ny] !== grid[x][y] || (nx === px && ny === py)) {
                    continue;
                }
                if (vis[nx][ny] || dfs(nx, ny, x, y)) {
                    return true;
                }
            }
        }
        return false;
    };
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!vis[i][j] && dfs(i, j, -1, -1)) {
                return true;
            }
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn contains_cycle(grid: Vec<Vec<char>>) -> bool {
        let m = grid.len();
        let n = grid[0].len();
        let mut vis = vec![vec![false; n]; m];
        let dirs = vec![-1, 0, 1, 0, -1];

        fn dfs(
            x: usize,
            y: usize,
            px: isize,
            py: isize,
            grid: &Vec<Vec<char>>,
            vis: &mut Vec<Vec<bool>>,
            dirs: &Vec<isize>,
        ) -> bool {
            vis[x][y] = true;
            for k in 0..4 {
                let nx = (x as isize + dirs[k]) as usize;
                let ny = (y as isize + dirs[k + 1]) as usize;
                if nx < grid.len() && ny < grid[0].len() {
                    if grid[nx][ny] != grid[x][y] || (nx as isize == px && ny as isize == py) {
                        continue;
                    }
                    if vis[nx][ny] || dfs(nx, ny, x as isize, y as isize, grid, vis, dirs) {
                        return true;
                    }
                }
            }
            false
        }

        for i in 0..m {
            for j in 0..n {
                if !vis[i][j] && dfs(i, j, -1, -1, &grid, &mut vis, &dirs) {
                    return true;
                }
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
