---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [323. 无向图中连通分量的数目 🔒](https://leetcode.cn/problems/number-of-connected-components-in-an-undirected-graph)

[English Version](/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个包含&nbsp;<code>n</code> 个节点的图。给定一个整数 <code>n</code> 和一个数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>返回 <em>图中已连接分量的数目</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/images/conn1-graph.jpg" /></p>

<pre>
<strong>输入: </strong><code>n = 5</code>, <code>edges = [[0, 1], [1, 2], [3, 4]]</code>
<strong>输出: </strong>2
</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/images/conn2-graph.jpg" /></p>

<pre>
<strong>输入: </strong><code>n = 5,</code> <code>edges = [[0,1], [1,2], [2,3], [3,4]]</code>
<strong>输出:&nbsp;&nbsp;</strong>1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>1 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>&nbsp;&lt;= b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li><code>edges</code> 中不会出现重复的边</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 无向图的连通块个数。从每个未访问点出发走完其所在分量，每开始一次搜索计数加一。$n,m$ 规模允许 $O(n+m)$。
>
> 建邻接表后 DFS：已访问返回 $0$，否则标记并递归邻居，返回 $1$。对全部点求和即块数。

<!-- thinking:end -->

我们先根据给定的边构建一个邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻居节点。

然后我们遍历所有节点，对于每个节点，我们使用 DFS 遍历所有与其相邻的节点，并将其标记为已访问，直到所有与其相邻的节点都被访问过，这样我们就找到了一个连通分量，答案加一。然后我们继续遍历下一个未访问的节点，直到所有节点都被访问过。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> int:
            if i in vis:
                return 0
            vis.add(i)
            for j in g[i]:
                dfs(j)
            return 1

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        return sum(dfs(i) for i in range(n))
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int countComponents(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(i);
        }
        return ans;
    }

    private int dfs(int i) {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        for (int j : g[i]) {
            dfs(j);
        }
        return 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countComponents(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<bool> vis(n);
        function<int(int)> dfs = [&](int i) {
            if (vis[i]) {
                return 0;
            }
            vis[i] = true;
            for (int j : g[i]) {
                dfs(j);
            }
            return 1;
        };
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(i);
        }
        return ans;
    }
};
```

#### Go

```go
func countComponents(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if vis[i] {
			return 0
		}
		vis[i] = true
		for _, j := range g[i] {
			dfs(j)
		}
		return 1
	}
	for i := range g {
		ans += dfs(i)
	}
	return
}
```

#### TypeScript

```ts
function countComponents(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number): number => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        for (const j of g[i]) {
            dfs(j);
        }
        return 1;
    };
    return g.reduce((acc, _, i) => acc + dfs(i), 0);
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var countComponents = function (n, edges) {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis = Array(n).fill(false);
    const dfs = i => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        for (const j of g[i]) {
            dfs(j);
        }
        return 1;
    };
    return g.reduce((acc, _, i) => acc + dfs(i), 0);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：并查集

<!-- thinking:start -->

> **思考**
>
> DFS 需要邻接表与递归栈。并查集按边合并：成功合并则块数减一，初始块数为 $n$。无需显式建图，近似线性。

<!-- thinking:end -->

我们可以使用并查集来维护图中的连通分量。

我们首先初始化一个并查集，然后遍历所有的边，对于每条边 $(a, b)$，我们将节点 $a$ 和节点 $b$ 合并到同一个连通分量中，如果连接成功，说明节点 $a$ 和节点 $b$ 之前不在同一个连通分量中，连通分量数目减一。

最后我们返回连通分量的数目。

时间复杂度 $O(n + m \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是节点数和边数，而 $\alpha(n)$ 是 Ackermann 函数的反函数，可以看作是一个很小的常数。

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
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        uf = UnionFind(n)
        for a, b in edges:
            n -= uf.union(a, b)
        return n
```

#### Java

```java
class UnionFind {
    private final int[] p;
    private final int[] size;

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

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (var e : edges) {
            n -= uf.union(e[0], e[1]) ? 1 : 0;
        }
        return n;
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

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
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
    int countComponents(int n, vector<vector<int>>& edges) {
        UnionFind uf(n);
        for (auto& e : edges) {
            n -= uf.unite(e[0], e[1]);
        }
        return n;
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

func countComponents(n int, edges [][]int) int {
	uf := newUnionFind(n)
	for _, e := range edges {
		if uf.union(e[0], e[1]) {
			n--
		}
	}
	return n
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

function countComponents(n: number, edges: number[][]): number {
    const uf = new UnionFind(n);
    for (const [a, b] of edges) {
        n -= uf.union(a, b) ? 1 : 0;
    }
    return n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：BFS

<!-- thinking:start -->

> **思考**
>
> 若避免递归深度，可用队列把方法一改成 BFS：未访问点入队后扩展整块，计数同样加一。时间仍为 $O(n+m)$。

<!-- thinking:end -->

我们也可以使用 BFS 来统计图中的连通分量。

与方法一类似，我们首先根据给定的边构建一个邻接表 $g$，然后遍历所有节点，对于每个节点，如果它没有被访问过，我们就从该节点开始进行 BFS 遍历，将所有与其相邻的节点都标记为已访问，直到所有与其相邻的节点都被访问过，这样我们就找到了一个连通分量，答案加一。

遍历所有节点后，我们就得到了图中连通分量的数目。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        ans = 0
        for i in range(n):
            if i in vis:
                continue
            vis.add(i)
            q = deque([i])
            while q:
                a = q.popleft()
                for b in g[a]:
                    if b not in vis:
                        vis.add(b)
                        q.append(b)
            ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int ans = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++ans;
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            while (!q.isEmpty()) {
                int a = q.poll();
                for (int b : g[a]) {
                    if (!vis[b]) {
                        vis[b] = true;
                        q.offer(b);
                    }
                }
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
    int countComponents(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<bool> vis(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++ans;
            queue<int> q{{i}};
            while (!q.empty()) {
                int a = q.front();
                q.pop();
                for (int b : g[a]) {
                    if (!vis[b]) {
                        vis[b] = true;
                        q.push(b);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countComponents(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	for i := range g {
		if vis[i] {
			continue
		}
		vis[i] = true
		ans++
		q := []int{i}
		for len(q) > 0 {
			a := q[0]
			q = q[1:]
			for _, b := range g[a] {
				if !vis[b] {
					vis[b] = true
					q = append(q, b)
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countComponents(n: number, edges: number[][]): number {
    const g: Map<number, number[]> = new Map(Array.from({ length: n }, (_, i) => [i, []]));
    for (const [a, b] of edges) {
        g.get(a)!.push(b);
        g.get(b)!.push(a);
    }

    const vis = new Set<number>();
    let ans = 0;
    for (const [i] of g) {
        if (vis.has(i)) {
            continue;
        }
        const q = [i];
        for (const j of q) {
            if (vis.has(j)) {
                continue;
            }
            vis.add(j);
            q.push(...g.get(j)!);
        }
        ans++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
