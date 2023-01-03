# [200. 岛屿数量](https://leetcode.cn/problems/number-of-islands)

[English Version](/solution/0200-0299/0200.Number%20of%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>

<p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>

<p>此外，你可以假设该网格的四条边均被水包围。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 300</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：Flood fill 算法**

Flood fill 算法是从一个区域中提取若干个连通的点与其他相邻区域区分开（或分别染成不同颜色）的经典算法。因为其思路类似洪水从一个区域扩散到所有能到达的区域而得名。

最简单的实现方法是采用 DFS 的递归方法，也可以采用 BFS 的迭代来实现。

时间复杂度 $O(m\times n)$，空间复杂度 $O(m\times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

**方法二：并查集**

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的**合并**及**查询**问题。 它支持两种操作：

1. 查找（Find）：确定某个元素处于哪个子集，单次操作时间复杂度 $O(\alpha(n))$
1. 合并（Union）：将两个子集合并成一个集合，单次操作时间复杂度 $O(\alpha(n))$

其中 $\alpha$ 为阿克曼函数的反函数，其增长极其缓慢，也就是说其单次操作的平均运行时间可以认为是一个很小的常数。

以下是并查集的常用模板，需要熟练掌握。其中：

-   `n` 表示节点数
-   `p` 存储每个点的父节点，初始时每个点的父节点都是自己
-   `size` 只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
-   `find(x)` 函数用于查找 $x$ 所在集合的祖宗节点
-   `union(a, b)` 函数用于合并 $a$ 和 $b$ 所在的集合

```python
p = list(range(n))
size = [1] * n

def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]


def union(a, b):
    pa, pb = find(a), find(b)
    if pa == pb:
        return
    p[pa] = pb
    size[pb] += size[pa]
```

时间复杂度 $O(m\times n\times \alpha(m\times n))$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS - Flood Fill 算法：

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def dfs(i, j):
            grid[i][j] = '0'
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':
                    dfs(x, y)

        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    dfs(i, j)
                    ans += 1
        return ans
```

BFS - Flood Fill 算法：

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def bfs(i, j):
            grid[i][j] = '0'
            q = deque([(i, j)])
            while q:
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y] == '1':
                        q.append((x, y))
                        grid[x][y] = 0

        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    bfs(i, j)
                    ans += 1
        return ans
```

并查集：

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        dirs = (0, 1, 0)
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if x < m and y < n and grid[x][y] == '1':
                            p[find(i * n + j)] = find(x * n + y)
        return sum(grid[i][j] == '1' and i * n + j == find(i * n + j) for i in range(m) for j in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS - Flood Fill 算法：

```java
class Solution {
    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        grid[i][j] = '0';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(x, y);
            }
        }
    }
}
```

BFS - Flood Fill 算法：

```java
class Solution {
    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void bfs(int i, int j) {
        grid[i][j] = '0';
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k];
                int y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    q.offer(new int[]{x, y});
                    grid[x][y] = '0';
                }
            }
        }
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid[x][y] == '1') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

DFS - Flood Fill 算法：

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int)> dfs = [&](int i, int j) {
            grid[i][j] = '0';
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == '1') {
                    dfs(x, y);
                }
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

BFS - Flood Fill 算法：

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int)> bfs = [&](int i, int j) {
            grid[i][j] = '0';
            queue<pair<int, int>> q;
            q.push({i, j});
            vector<int> dirs = {-1, 0, 1, 0, -1};
            while (!q.empty()) {
                auto [a, b] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = a + dirs[k];
                    int y = b + dirs[k + 1];
                    if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == '1') {
                        q.push({x, y});
                        grid[x][y] = '0';
                    }
                }
            }
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    bfs(i, j);
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

并查集：

```cpp
class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<int> p(m * n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int dirs[3] = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid[x][y] == '1') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += grid[i][j] == '1' && i * n + j == find(i * n + j);
            }
        }
        return ans;
    }
};
```

### **Go**

DFS - Flood Fill 算法：

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = '0'
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1' {
				dfs(x, y)
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				dfs(i, j)
				ans++
			}
		}
	}
	return ans
}
```

BFS - Flood Fill 算法：

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	bfs := func(i, j int) {
		grid[i][j] = '0'
		q := [][]int{[]int{i, j}}
		dirs := []int{-1, 0, 1, 0, -1}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1' {
					q = append(q, []int{x, y})
					grid[x][y] = '0'
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				bfs(i, j)
				ans++
			}
		}
	}
	return ans
}
```

并查集：

```go
func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{1, 0, 1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				for k := 0; k < 2; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x < m && y < n && grid[x][y] == '1' {
						p[find(x*n+y)] = find(i*n + j)
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' && i*n+j == find(i*n+j) {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

DFS - Flood Fill 算法：

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    function dfs(i, j) {
        grid[i][j] = '0';
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(x, y);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                dfs(i, j);
                ++ans;
            }
        }
    }
    return ans;
}
```

BFS - Flood Fill 算法：

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    function bfs(i, j) {
        grid[i][j] = '0';
        let q = [[i, j]];
        const dirs = [-1, 0, 1, 0, -1];
        while (q.length) {
            [i, j] = q.shift();
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    q.push([x, y]);
                    grid[x][y] = '0';
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                bfs(i, j);
                ++ans;
            }
        }
    }
    return ans;
}
```

并查集：

```ts
function numIslands(grid: string[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let p = [];
    for (let i = 0; i < m * n; ++i) {
        p.push(i);
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    const dirs = [1, 0, 1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                for (let k = 0; k < 2; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (x < m && y < n && grid[x][y] == '1') {
                        p[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                ++ans;
            }
        }
    }
    return ans;
}
```

### **Rust**

DFS - Flood Fill 算法：

```rust
const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        fn dfs(grid: &mut Vec<Vec<char>>, i: usize, j: usize) {
            grid[i][j] = '0';
            for k in 0..4 {
                let x = i as i32 + DIRS[k];
                let y = j as i32 + DIRS[k + 1];
                if x >= 0
                    && (x as usize) < grid.len()
                    && y >= 0
                    && (y as usize) < grid[0].len()
                    && grid[x as usize][y as usize] == '1'
                {
                    dfs(grid, x as usize, y as usize);
                }
            }
        }

        let mut grid = grid;
        let mut ans = 0;
        for i in 0..grid.len() {
            for j in 0..grid[0].len() {
                if grid[i][j] == '1' {
                    dfs(&mut grid, i, j);
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

BFS - Flood Fill 算法：

```rust
use std::collections::VecDeque;

const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        fn bfs(grid: &mut Vec<Vec<char>>, i: usize, j: usize) {
            grid[i][j] = '0';
            let mut queue = VecDeque::from([(i, j)]);
            while !queue.is_empty() {
                let (i, j) = queue.pop_front().unwrap();
                for k in 0..4 {
                    let x = i as i32 + DIRS[k];
                    let y = j as i32 + DIRS[k + 1];
                    if x >= 0
                        && (x as usize) < grid.len()
                        && y >= 0
                        && (y as usize) < grid[0].len()
                        && grid[x as usize][y as usize] == '1'
                    {
                        grid[x as usize][y as usize] = '0';
                        queue.push_back((x as usize, y as usize));
                    }
                }
            }
        }

        let mut grid = grid;
        let mut ans = 0;
        for i in 0..grid.len() {
            for j in 0..grid[0].len() {
                if grid[i][j] == '1' {
                    bfs(&mut grid, i, j);
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

并查集：

```rust
const DIRS: [usize; 3] = [1, 0, 1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut p: Vec<i32> = (0..(m * n) as i32).collect();

        fn find(p: &mut Vec<i32>, x: usize) -> i32 {
            if p[x] != x as i32 {
                p[x] = find(p, p[x] as usize);
            }
            p[x]
        }

        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == '1' {
                    for k in 0..2 {
                        let x = i + DIRS[k];
                        let y = j + DIRS[k + 1];
                        if x < m && y < n && grid[x][y] == '1' {
                            let f1 = find(&mut p, x * n + y);
                            let f2 = find(&mut p, i * n + j);
                            p[f1 as usize] = f2;
                        }
                    }
                }
            }
        }

        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] == '1' && p[i * n + j] == (i * n + j) as i32 {
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
