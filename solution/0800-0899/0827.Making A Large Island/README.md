# [827. 最大人工岛](https://leetcode.cn/problems/making-a-large-island)

[English Version](/solution/0800-0899/0827.Making%20A%20Large%20Island/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n x n</code> 二进制矩阵 <code>grid</code> 。<strong>最多</strong> 只能将一格 <code>0</code> 变成 <code>1</code> 。</p>

<p>返回执行此操作后，<code>grid</code> 中最大的岛屿面积是多少？</p>

<p><strong>岛屿</strong> 由一组上、下、左、右四个方向相连的 <code>1</code> 形成。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 0], [0, 1]]
<strong>输出:</strong> 3
<strong>解释:</strong> 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>grid =<strong> </strong>[[1, 1], [1, 0]]
<strong>输出:</strong> 4
<strong>解释:</strong> 将一格0变成1，岛屿的面积扩大为 4。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 1], [1, 1]]
<strong>输出:</strong> 4
<strong>解释:</strong> 没有0可以让我们变成1，面积依然为 4。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= n <= 500</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：并查集**

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

在这道题中，相邻的 $1$ 组成一个岛屿，因此，我们需要将相邻的 $1$ 归到同一个集合中。这可以视为一个合并操作，不难想到用并查集来实现。

第一次遍历 `grid`，通过并查集的 `union` 操作合并所有相邻的 $1$，并且统计每个岛屿的面积，记录在 $size$ 数组中。

再次遍历 `grid`，对于每个 $0$，我们统计相邻的四个点中 $1$ 所在的岛屿（通过并查集的 `find` 操作找到所在岛屿），累加去重后的岛屿面积，更新最大值。

时间复杂度 $O(n^2\times \alpha(n))$。其中 $n$ 为矩阵 `grid` 的边长。

**方法二：DFS**

我们也可以通过 DFS，找到每个岛屿。

同一个岛屿中的所有点都属于同一个集合，我们可以用不同的 `root` 值标识不同的岛屿，用 $p$ 记录每个 $grid[i][j]$ 对应的 `root` 值，用 $cnt$ 记录每个岛屿的面积。

遍历 `grid`，对于每个 $0$，我们统计相邻的四个点中 $1$ 所在的岛屿（与方法一不同的是，我们这里直接取 $p[i][j]$ 作为 `root`），累加去重后的岛屿面积，更新最大值。

时间复杂度 $O(n^2)$。其中 $n$ 为矩阵 `grid` 的边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    for a, b in [[0, -1], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            union(x * n + y, i * n + j)
        ans = max(size)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    vis = set()
                    t = 1
                    for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            root = find(x * n + y)
                            if root not in vis:
                                vis.add(root)
                                t += size[root]
                    ans = max(ans, t)
        return ans
```

```python
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            p[i][j] = root
            cnt[root] += 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] and p[x][y] == 0:
                    dfs(x, y)

        n = len(grid)
        cnt = Counter()
        p = [[0] * n for _ in range(n)]
        root = 0
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v and p[i][j] == 0:
                    root += 1
                    dfs(i, j)

        ans = max(cnt.values(), default=0)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    t = 1
                    vis = set()
                    for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            root = p[x][y]
                            if root not in vis:
                                vis.add(root)
                                t += cnt[root]
                    ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int ans = 1;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) {
                                continue;
                            }
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = Math.max(ans, size[pb]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int root = find(x * n + y);
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += size[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
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

```java
class Solution {
    private int n;
    private int ans;
    private int root;
    private int[][] p;
    private int[][] grid;
    private int[] cnt;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        cnt = new int[n * n + 1];
        p = new int[n][n];
        this.grid = grid;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        p[i][j] = root;
        ++cnt[root];
        ans = Math.max(ans, cnt[root]);
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0) {
                dfs(x, y);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> p(n * n);
        vector<int> size(n * n, 1);
        iota(p.begin(), p.end(), 0);

        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };

        int ans = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) continue;
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = max(ans,size[pb]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    int t = 1;
                    unordered_set<int> vis;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int root = find(x * n + y);
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += size[root];
                            }
                        }
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        int root = 0;
        vector<vector<int>> p(n, vector<int>(n));
        vector<int> cnt(n * n + 1);

        function<void(int, int)> dfs;
        dfs = [&](int i, int j) {
            p[i][j] = root;
            ++cnt[root];
            ans = max(ans, cnt[root]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] && p[x][y] == 0) {
                    dfs(x, y);
                }
            }
        };

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    int t = 1;
                    unordered_set<int> vis;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([]int, n*n)
	size := make([]int, n*n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 1
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						pa, pb := find(x*n+y), find(i*n+j)
						if pa != pb {
							p[pa] = pb
							size[pb] += size[pa]
							ans = max(ans, size[pb])
						}
					}
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						root := find(x*n + y)
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += size[root]
						}
					}
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, n)
	}
	cnt := make([]int, n*n+1)
	dirs := []int{-1, 0, 1, 0, -1}
	ans, root := 0, 0

	var dfs func(i, j int)
	dfs = func(i, j int) {
		p[i][j] = root
		cnt[root]++
		ans = max(ans, cnt[root])
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0 {
				dfs(x, y)
			}
		}
	}

	for i, row := range grid {
		for j, v := range row {
			if v == 1 && p[i][j] == 0 {
				root++
				dfs(i, j)
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						root := p[x][y]
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += cnt[root]
						}
					}
				}
				ans = max(ans, t)
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function largestIsland(grid: number[][]): number {
    const n = grid.length;
    const vis = Array.from({ length: n }, () => new Array(n).fill(false));
    const group = Array.from({ length: n }, () => new Array(n).fill(0));
    const dfs = (i: number, j: number, paths: [number, number][]) => {
        if (
            i < 0 ||
            j < 0 ||
            i === n ||
            j === n ||
            vis[i][j] ||
            grid[i][j] !== 1
        ) {
            return;
        }
        vis[i][j] = true;
        paths.push([i, j]);
        dfs(i + 1, j, paths);
        dfs(i, j + 1, paths);
        dfs(i - 1, j, paths);
        dfs(i, j - 1, paths);
    };
    let count = 1;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const paths: [number, number][] = [];
            dfs(i, j, paths);
            if (paths.length !== 0) {
                for (const [x, y] of paths) {
                    group[x][y] = count;
                    grid[x][y] = paths.length;
                }
                count++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let sum = grid[i][j];
            if (grid[i][j] === 0) {
                sum++;
                const set = new Set();
                if (i !== 0) {
                    sum += grid[i - 1][j];
                    set.add(group[i - 1][j]);
                }
                if (i !== n - 1 && !set.has(group[i + 1][j])) {
                    sum += grid[i + 1][j];
                    set.add(group[i + 1][j]);
                }
                if (j !== 0 && !set.has(group[i][j - 1])) {
                    sum += grid[i][j - 1];
                    set.add(group[i][j - 1]);
                }
                if (j !== n - 1 && !set.has(group[i][j + 1])) {
                    sum += grid[i][j + 1];
                }
            }
            res = Math.max(res, sum);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    fn dfs(
        i: usize,
        j: usize,
        grid: &Vec<Vec<i32>>,
        paths: &mut Vec<(usize, usize)>,
        vis: &mut Vec<Vec<bool>>,
    ) {
        let n = vis.len();
        if vis[i][j] || grid[i][j] != 1 {
            return;
        }
        paths.push((i, j));
        vis[i][j] = true;
        if i != 0 {
            Self::dfs(i - 1, j, grid, paths, vis);
        }
        if j != 0 {
            Self::dfs(i, j - 1, grid, paths, vis);
        }
        if i != n - 1 {
            Self::dfs(i + 1, j, grid, paths, vis);
        }
        if j != n - 1 {
            Self::dfs(i, j + 1, grid, paths, vis);
        }
    }

    pub fn largest_island(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut vis = vec![vec![false; n]; n];
        let mut group = vec![vec![0; n]; n];
        let mut count = 1;
        for i in 0..n {
            for j in 0..n {
                let mut paths: Vec<(usize, usize)> = Vec::new();
                Self::dfs(i, j, &grid, &mut paths, &mut vis);
                let m = paths.len() as i32;
                if m != 0 {
                    for (x, y) in paths {
                        grid[x][y] = m;
                        group[x][y] = count;
                    }
                    count += 1;
                }
            }
        }
        let mut res = 0;
        for i in 0..n {
            for j in 0..n {
                let mut sum = grid[i][j];
                if grid[i][j] == 0 {
                    sum += 1;
                    let mut set = HashSet::new();
                    if i != 0 {
                        sum += grid[i - 1][j];
                        set.insert(group[i - 1][j]);
                    }
                    if j != 0 && !set.contains(&group[i][j - 1]) {
                        sum += grid[i][j - 1];
                        set.insert(group[i][j - 1]);
                    }
                    if i != n - 1 && !set.contains(&group[i + 1][j]) {
                        sum += grid[i + 1][j];
                        set.insert(group[i + 1][j]);
                    }
                    if j != n - 1 && !set.contains(&group[i][j + 1]) {
                        sum += grid[i][j + 1];
                        set.insert(group[i][j + 1]);
                    }
                }
                res = res.max(sum);
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
