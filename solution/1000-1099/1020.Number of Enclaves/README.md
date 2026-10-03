---
comments: true
difficulty: 中等
rating: 1615
source: 第 130 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [1020. 飞地的数量](https://leetcode.cn/problems/number-of-enclaves)

[English Version](/solution/1000-1099/1020.Number%20of%20Enclaves/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> ，其中 <code>0</code> 表示一个海洋单元格、<code>1</code> 表示一个陆地单元格。</p>

<p>一次 <strong>移动</strong> 是指从一个陆地单元格走到另一个相邻（<strong>上、下、左、右</strong>）的陆地单元格或跨过 <code>grid</code> 的边界。</p>

<p>返回网格中<strong> 无法 </strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1020.Number%20of%20Enclaves/images/enclaves1.jpg" style="height: 200px; width: 200px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
<strong>输出：</strong>3
<strong>解释：</strong>有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1020.Number%20of%20Enclaves/images/enclaves2.jpg" style="height: 200px; width: 200px;" />
<pre>
<strong>输入：</strong>grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>所有 1 都在边界上或可以到达边界。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 对每一块陆地判断是否能走到边界，最坏要对整张图反复搜索， $m,n\le 500$ 时常数不佳。飞地是无法走到边界的陆地，其补集恰为「与边界连通的陆地」。
>
> 从四条边界上的 $1$ 出发淹没所有能走到的陆地，剩下的 $1$ 便无法离岛。
>
> 深度优先搜索即可完成淹没，最后统计网格中剩余 $1$ 的个数。

<!-- thinking:end -->

我们可以从边界上的陆地开始进行深度优先搜索，将所有与边界相连的陆地都标记为 $0$。最后，统计剩余的 $1$ 的个数，即为答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int):
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    dfs(i, j)
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    dfs(i, j)
        return sum(sum(row) for row in grid)
```

#### Java

```java
class Solution {
    private int[][] grid;

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        for (int j = 0; j < n; j++) {
            for (int i : List.of(0, m - 1)) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j : List.of(0, n - 1)) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }
        int ans = 0;
        for (var row : grid) {
            for (int x : row) {
                ans += x;
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        grid[i][j] = 0;
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; k++) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                dfs(x, y);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int)> dfs = [&](int i, int j) {
            grid[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    dfs(x, y);
                }
            }
        };
        for (int j = 0; j < n; ++j) {
            for (int i : {0, m - 1}) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : {0, n - 1}) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }
        int ans = 0;
        for (const auto& row : grid) {
            ans += accumulate(row.begin(), row.end(), 0);
        }
        return ans;
    }
};
```

#### Go

```go
func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	for j := 0; j < n; j++ {
		for _, i := range [2]int{0, m - 1} {
			if grid[i][j] == 1 {
				dfs(i, j)
			}
		}
	}
	for i := 0; i < m; i++ {
		for _, j := range [2]int{0, n - 1} {
			if grid[i][j] == 1 {
				dfs(i, j)
			}
		}
	}
	for _, row := range grid {
		for _, x := range row {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function numEnclaves(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number) => {
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                dfs(x, y);
            }
        }
    };
    for (let j = 0; j < n; ++j) {
        for (let i of [0, m - 1]) {
            if (grid[i][j] === 1) {
                dfs(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (grid[i][j] === 1) {
                dfs(i, j);
            }
        }
    }
    return grid.flat().reduce((acc, cur) => acc + cur, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let dirs = [-1, 0, 1, 0, -1];

        fn dfs(grid: &mut Vec<Vec<i32>>, i: usize, j: usize, dirs: &[i32; 5]) {
            grid[i][j] = 0;
            for k in 0..4 {
                let (x, y) = (i as i32 + dirs[k], j as i32 + dirs[k + 1]);
                if let Some(row) = grid.get_mut(x as usize) {
                    if let Some(&mut 1) = row.get_mut(y as usize) {
                        dfs(grid, x as usize, y as usize, dirs);
                    }
                }
            }
        }

        for j in 0..n {
            if grid[0][j] == 1 {
                dfs(&mut grid, 0, j, &dirs);
            }
            if grid[m - 1][j] == 1 {
                dfs(&mut grid, m - 1, j, &dirs);
            }
        }

        for i in 0..m {
            if grid[i][0] == 1 {
                dfs(&mut grid, i, 0, &dirs);
            }
            if grid[i][n - 1] == 1 {
                dfs(&mut grid, i, n - 1, &dirs);
            }
        }

        grid.into_iter().flatten().filter(|&x| x == 1).count() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：BFS

<!-- thinking:start -->

> **思考**
>
> 方法一已经用显式栈从边界淹没陆地。连通性不依赖搜索顺序，也可以把边界陆地放入队列按层扩展。
>
> 队列弹出后同样把四个方向上的 $1$ 标成 $0$。四条边界处理完后，剩下的 $1$ 仍是飞地。

<!-- thinking:end -->

我们也可以使用广度优先搜索的方法，将边界上的陆地入队，然后进行广度优先搜索，将所有与边界相连的陆地都标记为 $0$。最后，统计剩余的 $1$ 的个数，即为答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque()
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    q.append((i, j))
                    grid[i][j] = 0
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    q.append((i, j))
                    grid[i][j] = 0
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    q.append((x, y))
                    grid[x][y] = 0
        return sum(sum(row) for row in grid)
```

#### Java

```java
class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 0; j < n; ++j) {
            for (int i : List.of(0, m - 1)) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    grid[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : List.of(0, n - 1)) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    grid[i][j] = 0;
                }
            }
        }
        final int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    q.offer(new int[] {x, y});
                    grid[x][y] = 0;
                }
            }
        }
        int ans = 0;
        for (var row : grid) {
            for (int x : row) {
                ans += x;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        queue<pair<int, int>> q;
        for (int j = 0; j < n; ++j) {
            for (int i : {0, m - 1}) {
                if (grid[i][j] == 1) {
                    q.emplace(i, j);
                    grid[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : {0, n - 1}) {
                if (grid[i][j] == 1) {
                    q.emplace(i, j);
                    grid[i][j] = 0;
                }
            }
        }
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    q.emplace(x, y);
                    grid[x][y] = 0;
                }
            }
        }
        int ans = 0;
        for (const auto& row : grid) {
            ans += accumulate(row.begin(), row.end(), 0);
        }
        return ans;
    }
};
```

#### Go

```go
func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	q := [][2]int{}
	for j := 0; j < n; j++ {
		for _, i := range []int{0, m - 1} {
			if grid[i][j] == 1 {
				q = append(q, [2]int{i, j})
				grid[i][j] = 0
			}
		}
	}
	for i := 0; i < m; i++ {
		for _, j := range []int{0, n - 1} {
			if grid[i][j] == 1 {
				q = append(q, [2]int{i, j})
				grid[i][j] = 0
			}
		}
	}
	for len(q) > 0 {
		i, j := q[0][0], q[0][1]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				q = append(q, [2]int{x, y})
				grid[x][y] = 0
			}
		}
	}
	for _, row := range grid {
		for _, x := range row {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function numEnclaves(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const q: number[][] = [];
    for (let j = 0; j < n; ++j) {
        for (let i of [0, m - 1]) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
                grid[i][j] = 0;
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
                grid[i][j] = 0;
            }
        }
    }
    let head = 0;
    while (head < q.length) {
        const [i, j] = q[head++];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                q.push([x, y]);
                grid[x][y] = 0;
            }
        }
    }
    return grid.flat().reduce((acc, cur) => acc + cur, 0);
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut q = VecDeque::new();
        let dirs = [-1, 0, 1, 0, -1];

        for j in 0..n {
            for &i in &[0, m - 1] {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                    grid[i][j] = 0;
                }
            }
        }

        for i in 0..m {
            for &j in &[0, n - 1] {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                    grid[i][j] = 0;
                }
            }
        }

        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i as isize + dirs[k];
                let y = j as isize + dirs[k + 1];
                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let (x, y) = (x as usize, y as usize);
                    if grid[x][y] == 1 {
                        q.push_back((x, y));
                        grid[x][y] = 0;
                    }
                }
            }
        }

        grid.into_iter().flatten().sum()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：显式栈

<!-- thinking:start -->

> **思考**
>
> 飞地是到不了边界的陆地。对每一块陆地单独判断能否离岛，会在同一张图上反复搜索。$m,n\le 500$，补集是「与边界连通的陆地」。
>
> 从边界上的 $1$ 递归淹没时，一条贴着边界蜿蜒的陆地链会长到上千格，调用栈会先被用尽。
>
> 淹没只要求把能走到的格子标成 $0$，不依赖递归返回值。
>
> 因此用显式栈保存待淹没的格子。弹出后把四个方向上仍为 $1$ 的邻居标 $0$ 并入栈。四条边界都处理完后，网格里剩下的 $1$ 就是飞地。

<!-- thinking:end -->

从四条边界上的陆地出发，用显式栈淹没所有能走到的陆地，将其标为 $0$。最后统计网格中剩余 $1$ 的个数。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def flood(i: int, j: int):
            stk = [(i, j)]
            grid[i][j] = 0
            while stk:
                i, j = stk.pop()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y]:
                        grid[x][y] = 0
                        stk.append((x, y))

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        for j in range(n):
            for i in (0, m - 1):
                if grid[i][j]:
                    flood(i, j)
        for i in range(m):
            for j in (0, n - 1):
                if grid[i][j]:
                    flood(i, j)
        return sum(sum(row) for row in grid)
```

#### Java

```java
class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        for (int j = 0; j < n; j++) {
            for (int i : List.of(0, m - 1)) {
                if (grid[i][j] == 1) {
                    flood(grid, stk, dirs, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j : List.of(0, n - 1)) {
                if (grid[i][j] == 1) {
                    flood(grid, stk, dirs, i, j);
                }
            }
        }
        int ans = 0;
        for (var row : grid) {
            for (int x : row) {
                ans += x;
            }
        }
        return ans;
    }

    private void flood(int[][] grid, Deque<int[]> stk, int[] dirs, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = 0;
        stk.push(new int[] {i, j});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            i = cur[0];
            j = cur[1];
            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    grid[x][y] = 0;
                    stk.push(new int[] {x, y});
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto flood = [&](int i, int j) {
            vector<pair<int, int>> stk{{i, j}};
            grid[i][j] = 0;
            while (!stk.empty()) {
                auto [a, b] = stk.back();
                stk.pop_back();
                for (int k = 0; k < 4; ++k) {
                    int x = a + dirs[k], y = b + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 0;
                        stk.emplace_back(x, y);
                    }
                }
            }
        };
        for (int j = 0; j < n; ++j) {
            for (int i : {0, m - 1}) {
                if (grid[i][j] == 1) {
                    flood(i, j);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j : {0, n - 1}) {
                if (grid[i][j] == 1) {
                    flood(i, j);
                }
            }
        }
        int ans = 0;
        for (const auto& row : grid) {
            ans += accumulate(row.begin(), row.end(), 0);
        }
        return ans;
    }
};
```

#### Go

```go
func numEnclaves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	flood := func(i, j int) {
		grid[i][j] = 0
		stk := [][2]int{{i, j}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for k := 0; k < 4; k++ {
				x, y := cur[0]+dirs[k], cur[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
					grid[x][y] = 0
					stk = append(stk, [2]int{x, y})
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		for _, i := range [2]int{0, m - 1} {
			if grid[i][j] == 1 {
				flood(i, j)
			}
		}
	}
	for i := 0; i < m; i++ {
		for _, j := range [2]int{0, n - 1} {
			if grid[i][j] == 1 {
				flood(i, j)
			}
		}
	}
	for _, row := range grid {
		for _, x := range row {
			ans += x
		}
	}
	return
}
```

#### TypeScript

```ts
function numEnclaves(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const dirs = [-1, 0, 1, 0, -1];
    const flood = (i: number, j: number) => {
        grid[i][j] = 0;
        const stk: number[][] = [[i, j]];
        while (stk.length) {
            const [a, b] = stk.pop()!;
            for (let k = 0; k < 4; ++k) {
                const x = a + dirs[k];
                const y = b + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] === 1) {
                    grid[x][y] = 0;
                    stk.push([x, y]);
                }
            }
        }
    };
    for (let j = 0; j < n; ++j) {
        for (const i of [0, m - 1]) {
            if (grid[i][j] === 1) {
                flood(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (const j of [0, n - 1]) {
            if (grid[i][j] === 1) {
                flood(i, j);
            }
        }
    }
    return grid.flat().reduce((acc, cur) => acc + cur, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let dirs = [-1, 0, 1, 0, -1];

        let flood = |grid: &mut Vec<Vec<i32>>, i: usize, j: usize| {
            grid[i][j] = 0;
            let mut stk = vec![(i, j)];
            while let Some((a, b)) = stk.pop() {
                for k in 0..4 {
                    let x = a as i32 + dirs[k];
                    let y = b as i32 + dirs[k + 1];
                    if x >= 0 && y >= 0 {
                        let (x, y) = (x as usize, y as usize);
                        if x < m && y < n && grid[x][y] == 1 {
                            grid[x][y] = 0;
                            stk.push((x, y));
                        }
                    }
                }
            }
        };

        for j in 0..n {
            if grid[0][j] == 1 {
                flood(&mut grid, 0, j);
            }
            if grid[m - 1][j] == 1 {
                flood(&mut grid, m - 1, j);
            }
        }

        for i in 0..m {
            if grid[i][0] == 1 {
                flood(&mut grid, i, 0);
            }
            if grid[i][n - 1] == 1 {
                flood(&mut grid, i, n - 1);
            }
        }

        grid.into_iter().flatten().filter(|&x| x == 1).count() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
