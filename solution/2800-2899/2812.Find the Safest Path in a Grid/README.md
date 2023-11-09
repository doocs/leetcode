# [2812. 找出最安全路径](https://leetcode.cn/problems/find-the-safest-path-in-a-grid)

[English Version](/solution/2800-2899/2812.Find%20the%20Safest%20Path%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n x n</code> 的二维矩阵 <code>grid</code> ，其中 <code>(r, c)</code> 表示：</p>

<ul>
	<li>如果 <code>grid[r][c] = 1</code> ，则表示一个存在小偷的单元格</li>
	<li>如果 <code>grid[r][c] = 0</code> ，则表示一个空单元格</li>
</ul>

<p>你最开始位于单元格 <code>(0, 0)</code> 。在一步移动中，你可以移动到矩阵中的任一相邻单元格，包括存在小偷的单元格。</p>

<p>矩阵中路径的 <strong>安全系数</strong> 定义为：从路径中任一单元格到矩阵中任一小偷所在单元格的 <strong>最小</strong> 曼哈顿距离。</p>

<p>返回所有通向单元格<em> </em><code>(n - 1, n - 1)</code> 的路径中的 <strong>最大安全系数</strong> 。</p>

<p>单元格 <code>(r, c)</code> 的某个 <strong>相邻</strong> 单元格，是指在矩阵中存在的 <code>(r, c + 1)</code>、<code>(r, c - 1)</code>、<code>(r + 1, c)</code> 和 <code>(r - 1, c)</code> 之一。</p>

<p>两个单元格 <code>(a, b)</code> 和 <code>(x, y)</code> 之间的 <strong>曼哈顿距离</strong> 等于 <code>| a - x | + | b - y |</code> ，其中 <code>|val|</code> 表示 <code>val</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2812.Find%20the%20Safest%20Path%20in%20a%20Grid/images/example1.png" style="width: 362px; height: 242px;" />
<pre>
<strong>输入：</strong>grid = [[1,0,0],[0,0,0],[0,0,1]]
<strong>输出：</strong>0
<strong>解释：</strong>从 (0, 0) 到 (n - 1, n - 1) 的每条路径都经过存在小偷的单元格 (0, 0) 和 (n - 1, n - 1) 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2812.Find%20the%20Safest%20Path%20in%20a%20Grid/images/example2.png" style="width: 362px; height: 242px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,1],[0,0,0],[0,0,0]]
<strong>输出：</strong>2
<strong>解释：</strong>
上图所示路径的安全系数为 2：
- 该路径上距离小偷所在单元格（0，2）最近的单元格是（0，0）。它们之间的曼哈顿距离为 | 0 - 0 | + | 0 - 2 | = 2 。
可以证明，不存在安全系数更高的其他路径。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2812.Find%20the%20Safest%20Path%20in%20a%20Grid/images/example3.png" style="width: 362px; height: 242px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
<strong>输出：</strong>2
<strong>解释：</strong>
上图所示路径的安全系数为 2：
- 该路径上距离小偷所在单元格（0，3）最近的单元格是（1，2）。它们之间的曼哈顿距离为 | 0 - 1 | + | 3 - 2 | = 2 。
- 该路径上距离小偷所在单元格（3，0）最近的单元格是（3，2）。它们之间的曼哈顿距离为 | 3 - 3 | + | 0 - 2 | = 2 。
可以证明，不存在安全系数更高的其他路径。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length == n &lt;= 400</code></li>
	<li><code>grid[i].length == n</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>grid</code> 至少存在一个小偷</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：多源 BFS + 排序 + 并查集**

我们可以先找出所有小偷的位置，然后从这些位置开始进行多源 BFS，得到每个位置到小偷的最短距离，然后按照距离从大到小排序，将每个位置逐个加入并查集，如果最终起点和终点在同一个连通分量中，那么当前距离就是答案。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
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
    def maximumSafenessFactor(self, grid: List[List[int]]) -> int:
        n = len(grid)
        if grid[0][0] or grid[n - 1][n - 1]:
            return 0
        q = deque()
        dist = [[inf] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                if grid[i][j]:
                    q.append((i, j))
                    dist[i][j] = 0
        dirs = (-1, 0, 1, 0, -1)
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and dist[x][y] == inf:
                    dist[x][y] = dist[i][j] + 1
                    q.append((x, y))

        q = ((dist[i][j], i, j) for i in range(n) for j in range(n))
        q = sorted(q, reverse=True)
        uf = UnionFind(n * n)
        for d, i, j in q:
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and dist[x][y] >= d:
                    uf.union(i * n + j, x * n + y)
            if uf.find(0) == uf.find(n * n - 1):
                return int(d)
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        final int inf = 1 << 30;
        for (int[] d : dist) {
            Arrays.fill(d, inf);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == inf) {
                    dist[x][y] = dist[i][j] + 1;
                    q.offer(new int[] {x, y});
                }
            }
        }
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                t.add(new int[] {dist[i][j], i, j});
            }
        }
        t.sort((a, b) -> Integer.compare(b[0], a[0]));
        UnionFind uf = new UnionFind(n * n);
        for (int[] p : t) {
            int d = p[0], i = p[1], j = p[2];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d) {
                    uf.union(i * n + j, x * n + y);
                }
            }
            if (uf.find(0) == uf.find(n * n - 1)) {
                return d;
            }
        }
        return 0;
    }
}

class UnionFind {
    public int[] p;
    public int n;

    public UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        this.n = n;
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        --n;
        return true;
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class UnionFind {
public:
    vector<int> p;
    int n;

    UnionFind(int _n)
        : n(_n)
        , p(_n) {
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        p[pa] = pb;
        --n;
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};

class Solution {
public:
    int maximumSafenessFactor(vector<vector<int>>& grid) {
        int n = grid.size();
        if (grid[0][0] || grid[n - 1][n - 1]) {
            return 0;
        }
        queue<pair<int, int>> q;
        int dist[n][n];
        memset(dist, 0x3f, sizeof(dist));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    dist[i][j] = 0;
                    q.emplace(i, j);
                }
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == 0x3f3f3f3f) {
                    dist[x][y] = dist[i][j] + 1;
                    q.emplace(x, y);
                }
            }
        }
        vector<tuple<int, int, int>> t;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                t.emplace_back(dist[i][j], i, j);
            }
        }
        sort(t.begin(), t.end());
        reverse(t.begin(), t.end());
        UnionFind uf(n * n);
        for (auto [d, i, j] : t) {
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d) {
                    uf.unite(i * n + j, x * n + y);
                }
            }
            if (uf.find(0) == uf.find(n * n - 1)) {
                return d;
            }
        }
        return 0;
    }
};
```

### **Go**

```go
type unionFind struct {
	p []int
	n int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	return &unionFind{p, n}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	if uf.find(a) == uf.find(b) {
		return false
	}
	uf.p[uf.find(a)] = uf.find(b)
	uf.n--
	return true
}

func maximumSafenessFactor(grid [][]int) int {
	n := len(grid)
	if grid[0][0] == 1 || grid[n-1][n-1] == 1 {
		return 0
	}
	q := [][2]int{}
	dist := make([][]int, n)
	const inf = 1 << 30
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				dist[i][j] = 0
				q = append(q, [2]int{i, j})
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] == inf {
				dist[x][y] = dist[i][j] + 1
				q = append(q, [2]int{x, y})
			}
		}
	}
	t := [][3]int{}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			t = append(t, [3]int{dist[i][j], i, j})
		}
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][0] > t[j][0]
	})
	uf := newUnionFind(n * n)
	for _, p := range t {
		d, i, j := p[0], p[1], p[2]
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d {
				uf.union(i*n+j, x*n+y)
			}
		}
		if uf.find(0) == uf.find(n*n-1) {
			return d
		}
	}
	return 0
}
```

### **TypeScript**

```ts
class UnionFind {
    private p: number[];
    private n: number;

    constructor(n: number) {
        this.n = n;
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
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
        if (pa !== pb) {
            this.p[pa] = pb;
            this.n--;
            return true;
        }
        return false;
    }
}

function maximumSafenessFactor(grid: number[][]): number {
    const n = grid.length;
    if (grid[0][0] === 1 || grid[n - 1][n - 1] === 1) {
        return 0;
    }
    const q: number[][] = [];
    const inf = 1 << 30;
    const dist: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(inf));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                dist[i][j] = 0;
                q.push([i, j]);
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.shift()!;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] === inf) {
                dist[x][y] = dist[i][j] + 1;
                q.push([x, y]);
            }
        }
    }
    const t: number[][] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            t.push([dist[i][j], i, j]);
        }
    }
    t.sort((a, b) => b[0] - a[0]);
    const uf = new UnionFind(n * n);
    for (const [d, i, j] of t) {
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < n && y >= 0 && y < n && dist[x][y] >= d) {
                uf.union(i * n + j, x * n + y);
            }
        }
        if (uf.find(0) == uf.find(n * n - 1)) {
            return d;
        }
    }
    return 0;
}
```

```ts
function maximumSafenessFactor(grid: number[][]): number {
    const n = grid.length;
    const g = Array.from({ length: n }, () => new Array(n).fill(-1));
    const vis = Array.from({ length: n }, () => new Array(n).fill(false));
    let q: [number, number][] = [];
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
            }
        }
    }
    let level = 0;
    while (q.length) {
        const t: [number, number][] = [];
        for (const [x, y] of q) {
            if (x < 0 || y < 0 || x === n || y === n || g[x][y] !== -1) {
                continue;
            }
            g[x][y] = level;
            t.push([x + 1, y]);
            t.push([x - 1, y]);
            t.push([x, y + 1]);
            t.push([x, y - 1]);
        }
        q = t;
        level++;
    }
    const dfs = (i: number, j: number, v: number) => {
        if (i < 0 || j < 0 || i === n || j === n || vis[i][j] || g[i][j] <= v) {
            return false;
        }
        vis[i][j] = true;
        return (
            (i === n - 1 && j === n - 1) ||
            dfs(i + 1, j, v) ||
            dfs(i, j + 1, v) ||
            dfs(i - 1, j, v) ||
            dfs(i, j - 1, v)
        );
    };

    let left = 0;
    let right = level;
    while (left < right) {
        vis.forEach(v => v.fill(false));
        const mid = (left + right) >>> 1;
        if (dfs(0, 0, mid)) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return right;
}
```

### **Rust**

```rust
use std::collections::VecDeque;
impl Solution {
    fn dfs(i: usize, j: usize, v: i32, g: &Vec<Vec<i32>>, vis: &mut Vec<Vec<bool>>) -> bool {
        if vis[i][j] || g[i][j] <= v {
            return false;
        }
        vis[i][j] = true;
        let n = g.len();
        (i == n - 1 && j == n - 1) ||
            (i != 0 && Self::dfs(i - 1, j, v, g, vis)) ||
            (i != n - 1 && Self::dfs(i + 1, j, v, g, vis)) ||
            (j != 0 && Self::dfs(i, j - 1, v, g, vis)) ||
            (j != n - 1 && Self::dfs(i, j + 1, v, g, vis))
    }

    pub fn maximum_safeness_factor(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut g = vec![vec![-1; n]; n];
        let mut q = VecDeque::new();
        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                }
            }
        }
        let mut level = 0;
        while !q.is_empty() {
            let m = q.len();
            for _ in 0..m {
                let (i, j) = q.pop_front().unwrap();
                if g[i][j] != -1 {
                    continue;
                }
                g[i][j] = level;
                if i != n - 1 {
                    q.push_back((i + 1, j));
                }
                if i != 0 {
                    q.push_back((i - 1, j));
                }
                if j != n - 1 {
                    q.push_back((i, j + 1));
                }
                if j != 0 {
                    q.push_back((i, j - 1));
                }
            }
            level += 1;
        }
        let mut left = 0;
        let mut right = level;
        while left < right {
            let mid = (left + right) >> 1;
            if Self::dfs(0, 0, mid, &g, &mut vec![vec![false; n]; n]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        right
    }
}
```

### **...**

```

```

<!-- tabs:end -->
