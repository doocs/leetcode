---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [1971. 寻找图中是否存在路径](https://leetcode.cn/problems/find-if-path-exists-in-graph)

[English Version](/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个具有 <code>n</code> 个顶点的 <strong>双向</strong> 图，其中每个顶点标记从 <code>0</code> 到 <code>n - 1</code>（包含 <code>0</code> 和 <code>n - 1</code>）。图中的边用一个二维整数数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示顶点 <code>ui</code> 和顶点 <code>vi</code> 之间的双向边。 每个顶点对由 <strong>最多一条</strong> 边连接，并且没有顶点存在与自身相连的边。</p>

<p>请你确定是否存在从顶点 <code>source</code> 开始，到顶点 <code>destination</code> 结束的 <strong>有效路径</strong> 。</p>

<p>给你数组 <code>edges</code> 和整数 <code>n</code>、<code>source</code> 和 <code>destination</code>，如果从 <code>source</code> 到 <code>destination</code> 存在 <strong>有效路径</strong> ，则返回 <code>true</code>，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex1.png" style="width: 141px; height: 121px;" />
<pre>
<strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
<strong>输出：</strong>true
<strong>解释：</strong>存在由顶点 0 到顶点 2 的路径:
- 0 → 1 → 2 
- 0 → 2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex2.png" style="width: 281px; height: 141px;" />
<pre>
<strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
<strong>输出：</strong>false
<strong>解释：</strong>不存在由顶点 0 到顶点 5 的路径.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= source, destination &lt;= n - 1</code></li>
	<li>不存在重复边</li>
	<li>不存在指向顶点自身的边</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们首先将 $\textit{edges}$ 转换成邻接表 $g$，然后使用 DFS，判断是否存在从 $\textit{source}$ 到 $\textit{destination}$ 的路径。

过程中，我们用数组 $\textit{vis}$ 记录已经访问过的顶点，避免重复访问。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        def dfs(i: int) -> bool:
            if i == destination:
                return True
            vis.add(i)
            for j in g[i]:
                if j not in vis and dfs(j):
                    return True
            return False

        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        vis = set()
        return dfs(source)
```

#### Java

```java
class Solution {
    private int destination;
    private boolean[] vis;
    private List<Integer>[] g;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        this.destination = destination;
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (i == destination) {
            return true;
        }
        vis[i] = true;
        for (var j : g[i]) {
            if (!vis[j] && dfs(j)) {
                return true;
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
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        function<bool(int)> dfs = [&](int i) -> bool {
            if (i == destination) {
                return true;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j] && dfs(j)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(source);
    }
};
```

#### Go

```go
func validPath(n int, edges [][]int, source int, destination int) bool {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == destination {
			return true
		}
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] && dfs(j) {
				return true
			}
		}
		return false
	}
	return dfs(source)
}
```

#### TypeScript

```ts
function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const vis = new Set<number>();
    const dfs = (i: number) => {
        if (i === destination) {
            return true;
        }
        if (vis.has(i)) {
            return false;
        }
        vis.add(i);
        return g[i].some(dfs);
    };
    return dfs(source);
}
```

#### Rust

```rust
impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let source = source as usize;
        let destination = destination as usize;

        let mut g = vec![Vec::new(); n];
        let mut vis = vec![false; n];

        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        fn dfs(g: &Vec<Vec<usize>>, vis: &mut Vec<bool>, i: usize, destination: usize) -> bool {
            if i == destination {
                return true;
            }
            vis[i] = true;
            for &j in &g[i] {
                if !vis[j] && dfs(g, vis, j, destination) {
                    return true;
                }
            }
            false
        }

        dfs(&g, &mut vis, source, destination)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：BFS

我们也可以使用 BFS，判断是否存在从 $\textit{source}$ 到 $\textit{destination}$ 的路径。

具体地，我们定义一个队列 $q$，初始时将 $\textit{source}$ 加入队列。另外，我们用一个集合 $\textit{vis}$ 记录已经访问过的顶点，避免重复访问。

接下来，我们不断从队列中取出顶点 $i$，如果 $i = \textit{destination}$，则说明存在从 $\textit{source}$ 到 $\textit{destination}$ 的路径，返回 $\textit{true}$。否则，我们遍历 $i$ 的所有邻接顶点 $j$，如果 $j$ 没有被访问过，我们将 $j$ 加入队列 $q$，并且标记 $j$ 为已访问。

最后，如果队列为空，说明不存在从 $\textit{source}$ 到 $\textit{destination}$ 的路径，返回 $\textit{false}$。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([source])
        vis = {source}
        while q:
            i = q.popleft()
            if i == destination:
                return True
            for j in g[i]:
                if j not in vis:
                    vis.add(j)
                    q.append(j)
        return False
```

#### Java

```java
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(source);
        boolean[] vis = new boolean[n];
        vis[source] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == destination) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.offer(j);
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
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        queue<int> q{{source}};
        vector<bool> vis(n);
        vis[source] = true;
        while (q.size()) {
            int i = q.front();
            q.pop();
            if (i == destination) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func validPath(n int, edges [][]int, source int, destination int) bool {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []int{source}
	vis := make([]bool, n)
	vis[source] = true
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if i == destination {
			return true
		}
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				q = append(q, j)
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const vis = new Set<number>([source]);
    const q = [source];
    while (q.length) {
        const i = q.pop()!;
        if (i === destination) {
            return true;
        }
        for (const j of g[i]) {
            if (!vis.has(j)) {
                vis.add(j);
                q.push(j);
            }
        }
    }
    return false;
}
```

#### Rust

```rust
use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let source = source as usize;
        let destination = destination as usize;

        let mut g = vec![Vec::new(); n];
        for edge in edges {
            let u = edge[0] as usize;
            let v = edge[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        let mut q = VecDeque::new();
        let mut vis = HashSet::new();
        q.push_back(source);
        vis.insert(source);

        while let Some(i) = q.pop_front() {
            if i == destination {
                return true;
            }
            for &j in &g[i] {
                if !vis.contains(&j) {
                    vis.insert(j);
                    q.push_back(j);
                }
            }
        }

        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：并查集

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的**合并**及**查询**问题。 它支持两种操作：

1. 查找（Find）：确定某个元素处于哪个子集，单次操作时间复杂度 $O(\alpha(n))$
1. 合并（Union）：将两个子集合并成一个集合，单次操作时间复杂度 $O(\alpha(n))$

对于本题，我们可以利用并查集，将 `edges` 中的边进行合并，然后判断 `source` 和 `destination` 是否在同一个集合中。

时间复杂度 $O(n \log n + m)$ 或 $O(n \alpha(n) + m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

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
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        uf = UnionFind(n)
        for u, v in edges:
            uf.union(u, v)
        return uf.find(source) == uf.find(destination)
```

#### Java

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
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        UnionFind uf = new UnionFind(n);
        for (var e : edges) {
            uf.union(e[0], e[1]);
        }
        return uf.find(source) == uf.find(destination);
    }
}
```

#### C++

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
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        UnionFind uf(n);
        for (const auto& e : edges) {
            uf.unite(e[0], e[1]);
        }
        return uf.find(source) == uf.find(destination);
    }
};
```

#### Go

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

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func validPath(n int, edges [][]int, source int, destination int) bool {
	uf := newUnionFind(n)
	for _, e := range edges {
		uf.union(e[0], e[1])
	}
	return uf.find(source) == uf.find(destination)
}
```

#### TypeScript

```ts
class UnionFind {
    p: number[];
    size: number[];
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
        const [pa, pb] = [this.find(a), this.find(b)];
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

function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const uf = new UnionFind(n);
    edges.forEach(([u, v]) => uf.union(u, v));
    return uf.find(source) === uf.find(destination);
}
```

#### Rust

```rust
struct UnionFind {
    p: Vec<usize>,
    size: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let p = (0..n).collect();
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
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let mut uf = UnionFind::new(n);

        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            uf.union(u, v);
        }

        uf.find(source as usize) == uf.find(destination as usize)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
