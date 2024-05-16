---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/README.md
rating: 2011
source: 第 3 场双周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 二分查找
    - 矩阵
    - 堆（优先队列）
---

# [1102. 得分最高的路径 🔒](https://leetcode.cn/problems/path-with-maximum-minimum-value)

[English Version](/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <code>m x n</code> 的整数矩阵&nbsp;<code>grid</code>，返回从 <code>(0,0)</code> 开始到 <code>(m - 1, n - 1)</code> 在四个基本方向上移动的路径的最大 <strong>分数</strong> 。</p>

<p>一条路径的 <strong>分数</strong> 是该路径上的最小值。</p>

<ul>
	<li>例如，路径 <code>8 → 4 → 5 → 9</code> 的得分为 <code>4</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid1.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[5,4,5],[1,2,6],[7,4,6]]
<strong>输出：</strong>4
<strong>解释：</strong>得分最高的路径用黄色突出显示。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
<strong>输出：</strong>2</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid3.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
<strong>输出：</strong>3</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一：排序 + 并查集

我们先将矩阵的每个元素构建一个三元组 $(v, i, j)$，其中 $v$ 表示元素值，而 $i$ 和 $j$ 分别表示元素在矩阵中的行和列。然后对这些三元组按照元素值从大到小进行排序，存放在列表 $q$ 中。

接下来，我们按顺序从 $q$ 中取出三元组，将其对应的元素值作为路径的分数，并且将该位置标记为已访问。然后我们检查该位置的上下左右四个相邻位置，如果某个相邻位置已经被访问过，那么我们就将该位置与当前位置进行合并。如果发现位置 $(0, 0)$ 和位置 $(m - 1, n - 1)$ 已经被合并，那么我们就可以直接返回当前路径的分数，即为答案。

时间复杂度 $O(m \times n \times (\log (m \times n) + \alpha(m \times n)))$，其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        q = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        q.sort()
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        vis = set()
        while find(0) != find(m * n - 1):
            v, i, j = q.pop()
            ans = v
            vis.add((i, j))
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (x, y) in vis:
                    p[find(i * n + j)] = find(x * n + y)
        return ans
```

```java
class Solution {
    private int[] p;

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
                p[i * n + j] = i * n + j;
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; find(0) != find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(t[1] * n + t[2]);
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

```cpp
class Solution {
public:
    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> q;
        vector<int> p(m * n);
        iota(p.begin(), p.end(), 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.emplace_back(grid[i][j], i, j);
            }
        }
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        sort(q.begin(), q.end(), greater<tuple<int, int, int>>());
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (auto& [v, i, j] : q) {
            vis[i][j] = true;
            ans = v;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
            if (find(0) == find(m * n - 1)) {
                break;
            }
        }
        return ans;
    }
};
```

```go
func maximumMinimumPath(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	vis := make([][]bool, m)
	q := [][3]int{}
	for i, row := range grid {
		vis[i] = make([]bool, n)
		for j, v := range row {
			p[i*n+j] = i*n + j
			q = append(q, [3]int{v, i, j})
		}
	}
	sort.Slice(q, func(i, j int) bool { return q[i][0] > q[j][0] })
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, t := range q {
		v, i, j := t[0], t[1], t[2]
		ans = v
		vis[i][j] = true
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && vis[x][y] {
				p[find(x*n+y)] = find(i*n + j)
			}
		}
		if find(0) == find(m*n-1) {
			break
		}
	}
	return
}
```

```ts
function maximumMinimumPath(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const p: number[] = Array(m * n)
        .fill(0)
        .map((_, i) => i);
    const q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            q.push([grid[i][j], i, j]);
        }
    }
    q.sort((a, b) => b[0] - a[0]);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const vis: boolean[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(false));
    let ans = 0;
    for (let k = 0; find(0) !== find(m * n - 1); ++k) {
        const [t, i, j] = q[k];
        ans = t;
        vis[i][j] = true;
        for (let d = 0; d < 4; ++d) {
            const [x, y] = [i + dirs[d], j + dirs[d + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                p[find(i * n + j)] = find(x * n + y);
            }
        }
    }
    return ans;
}
```

```rust
struct UnionFind {
    p: Vec<usize>,
    size: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let p: Vec<usize> = (0..n).collect();
        let size = vec![1; n];
        UnionFind { p, size }
    }

    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x {
            self.p[x] = self.find(self.p[x]);
        }
        self.p[x]
    }

    fn union(&mut self, a: usize, b: usize) {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa != pb {
            if self.size[pa] > self.size[pb] {
                self.p[pb] = pa;
                self.size[pa] += self.size[pb];
            } else {
                self.p[pa] = pb;
                self.size[pb] += self.size[pa];
            }
        }
    }
}

impl Solution {
    pub fn maximum_minimum_path(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut uf = UnionFind::new(m * n);
        let mut q: Vec<Vec<i32>> = Vec::new();

        for i in 0..m {
            for j in 0..n {
                q.push(vec![grid[i][j], i as i32, j as i32]);
            }
        }

        q.sort_by(|a, b| b[0].cmp(&a[0]));

        let mut vis: Vec<Vec<bool>> = vec![vec![false; n]; m];
        let dirs: [i32; 5] = [-1, 0, 1, 0, -1];
        let mut ans = 0;
        for k in 0..q.len() {
            if uf.find(0) == uf.find(m * n - 1) {
                break;
            }
            let t = &q[k];
            let (v, i, j) = (t[0], t[1] as usize, t[2] as usize);
            ans = v;
            vis[i][j] = true;
            for d in 0..4 {
                let x = (i as i32) + dirs[d];
                let y = (j as i32) + dirs[d + 1];
                if
                    x >= 0 &&
                    x < (m as i32) &&
                    y >= 0 &&
                    y < (n as i32) &&
                    vis[x as usize][y as usize]
                {
                    uf.union((x as usize) * n + (y as usize), i * n + j);
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class UnionFind:
    __slots__ = ("p", "size")

    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x: int) -> int:
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a: int, b: int) -> bool:
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        uf = UnionFind(m * n)
        q = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        q.sort()
        ans = 0
        vis = set()
        dirs = (-1, 0, 1, 0, -1)
        while uf.find(0) != uf.find(m * n - 1):
            v, i, j = q.pop()
            ans = v
            vis.add((i, j))
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (x, y) in vis:
                    uf.union(x * n + y, i * n + j)
        return ans
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; uf.find(0) != uf.find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    uf.union(x * n + y, t[1] * n + t[2]);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> q;
        UnionFind uf(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.emplace_back(grid[i][j], i, j);
            }
        }
        sort(q.begin(), q.end(), greater<tuple<int, int, int>>());
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (auto& [v, i, j] : q) {
            vis[i][j] = true;
            ans = v;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    uf.unite(x * n + y, i * n + j);
                }
            }
            if (uf.find(0) == uf.find(m * n - 1)) {
                break;
            }
        }
        return ans;
    }
};
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func maximumMinimumPath(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	uf := newUnionFind(m * n)
	vis := make([][]bool, m)
	q := [][3]int{}
	for i, row := range grid {
		vis[i] = make([]bool, n)
		for j, v := range row {
			q = append(q, [3]int{v, i, j})
		}
	}
	sort.Slice(q, func(i, j int) bool { return q[i][0] > q[j][0] })
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, t := range q {
		v, i, j := t[0], t[1], t[2]
		ans = v
		vis[i][j] = true
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && vis[x][y] {
				uf.union(x*n+y, i*n+j)
			}
		}
		if uf.find(0) == uf.find(m*n-1) {
			break
		}
	}
	return
}
```

```ts
class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }
}

function maximumMinimumPath(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            q.push([grid[i][j], i, j]);
        }
    }
    q.sort((a, b) => b[0] - a[0]);
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const vis: boolean[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(false));
    let ans = 0;
    const uf = new UnionFind(m * n);
    for (let k = 0; uf.find(0) !== uf.find(m * n - 1); ++k) {
        const [t, i, j] = q[k];
        ans = t;
        vis[i][j] = true;
        for (let d = 0; d < 4; ++d) {
            const [x, y] = [i + dirs[d], j + dirs[d + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                uf.union(i * n + j, x * n + y);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
