---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/README_EN.md
rating: 2108
tags:
    - Bit Manipulation
    - Union Find
    - Graph
    - Array
---

# [3108. Minimum Cost Walk in Weighted Graph](https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph)

[中文文档](/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/README.md)

## Description

<p>There is an undirected weighted graph with <code>n</code> vertices labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given the integer <code>n</code> and an array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between vertices <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with a weight of <code>w<sub>i</sub></code>.</p>

<p>A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It&#39;s important to note that a walk may visit the same edge or vertex more than once.</p>

<p>The <strong>cost</strong> of a walk starting at node <code>u</code> and ending at node <code>v</code> is defined as the bitwise <code>AND</code> of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is <code>w<sub>0</sub>, w<sub>1</sub>, w<sub>2</sub>, ..., w<sub>k</sub></code>, then the cost is calculated as <code>w<sub>0</sub> &amp; w<sub>1</sub> &amp; w<sub>2</sub> &amp; ... &amp; w<sub>k</sub></code>, where <code>&amp;</code> denotes the bitwise <code>AND</code> operator.</p>

<p>You are also given a 2D array <code>query</code>, where <code>query[i] = [s<sub>i</sub>, t<sub>i</sub>]</code>. For each query, you need to find the minimum cost of the walk starting at vertex <code>s<sub>i</sub></code> and ending at vertex <code>t<sub>i</sub></code>. If there exists no such walk, the answer is <code>-1</code>.</p>

<p>Return <em>the array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> denotes the <strong>minimum</strong> cost of a walk for query </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,-1]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/images/q4_example1-1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 351px; height: 141px;" />
<p>To achieve the cost of 1 in the first query, we need to move on the following edges: <code>0-&gt;1</code> (weight 7), <code>1-&gt;2</code> (weight 1), <code>2-&gt;1</code> (weight 1), <code>1-&gt;3</code> (weight 7).</p>

<p>In the second query, there is no walk between nodes 3 and 4, so the answer is -1.</p>

<p><strong class="example">Example 2:</strong></p>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/images/q4_example2e.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 211px; height: 181px;" />
<p>To achieve the cost of 0 in the first query, we need to move on the following edges: <code>1-&gt;2</code> (weight 1), <code>2-&gt;1</code> (weight 6), <code>1-&gt;2</code> (weight 1).</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= query.length &lt;= 10<sup>5</sup></code></li>
	<li><code>query[i].length == 2</code></li>
	<li><code>0 &lt;= s<sub>i</sub>, t<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>s<sub>i</sub> !=&nbsp;t<sub>i</sub></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Union Find

We note that a positive integer performing bitwise AND operation with several other positive integers will only get smaller. Therefore, to minimize the cost of the journey, we should perform bitwise AND operation on the weights of all edges in the same connected component, and then perform the query.

So, the problem is transformed into how to find all the edges in the same connected component and perform bitwise AND operation.

We can use a union-find set to maintain the connected components.

Specifically, we traverse each edge $(u, v, w)$ and merge $u$ and $v$. Then, we traverse each edge $(u, v, w)$ again, find the root node $root$ of the connected component where $u$ and $v$ are located, and use an array $g$ to record the result of the bitwise AND operation of the weights of all edges in each connected component.

Finally, for each query $(s, t)$, we first judge whether $s$ equals $t$. If they are equal, the answer is $0$. Otherwise, we judge whether $s$ and $t$ are in the same connected component. If they are in the same connected component, the answer is the $g$ value of the root node of the connected component of this query. Otherwise, the answer is $-1$.

The time complexity is $O((n + m + q) \times \alpha(n))$, and the space complexity is $O(n)$. Here, $n$, $m$, and $q$ represent the number of nodes, edges, and queries, respectively, and $\alpha(n)$ represents the inverse function of the Ackermann function.

<!-- tabs:start -->

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
    def minimumCost(
        self, n: int, edges: List[List[int]], query: List[List[int]]
    ) -> List[int]:
        g = [-1] * n
        uf = UnionFind(n)
        for u, v, _ in edges:
            uf.union(u, v)
        for u, _, w in edges:
            root = uf.find(u)
            g[root] &= w

        def f(u: int, v: int) -> int:
            if u == v:
                return 0
            a, b = uf.find(u), uf.find(v)
            return g[a] if a == b else -1

        return [f(s, t) for s, t in query]
```

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

    public int size(int x) {
        return size[find(x)];
    }
}

class Solution {
    private UnionFind uf;
    private int[] g;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        uf = new UnionFind(n);
        for (var e : edges) {
            uf.union(e[0], e[1]);
        }
        g = new int[n];
        Arrays.fill(g, -1);
        for (var e : edges) {
            int root = uf.find(e[0]);
            g[root] &= e[2];
        }
        int m = query.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int s = query[i][0], t = query[i][1];
            ans[i] = f(s, t);
        }
        return ans;
    }

    private int f(int u, int v) {
        if (u == v) {
            return 0;
        }
        int a = uf.find(u), b = uf.find(v);
        return a == b ? g[a] : -1;
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

    int getSize(int x) {
        return size[find(x)];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    vector<int> minimumCost(int n, vector<vector<int>>& edges, vector<vector<int>>& query) {
        g = vector<int>(n, -1);
        uf = new UnionFind(n);
        for (auto& e : edges) {
            uf->unite(e[0], e[1]);
        }
        for (auto& e : edges) {
            int root = uf->find(e[0]);
            g[root] &= e[2];
        }
        vector<int> ans;
        for (auto& q : query) {
            ans.push_back(f(q[0], q[1]));
        }
        return ans;
    }

private:
    UnionFind* uf;
    vector<int> g;

    int f(int u, int v) {
        if (u == v) {
            return 0;
        }
        int a = uf->find(u), b = uf->find(v);
        return a == b ? g[a] : -1;
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

func (uf *unionFind) getSize(x int) int {
	return uf.size[uf.find(x)]
}

func minimumCost(n int, edges [][]int, query [][]int) (ans []int) {
	uf := newUnionFind(n)
	g := make([]int, n)
	for i := range g {
		g[i] = -1
	}
	for _, e := range edges {
		uf.union(e[0], e[1])
	}
	for _, e := range edges {
		root := uf.find(e[0])
		g[root] &= e[2]
	}
	f := func(u, v int) int {
		if u == v {
			return 0
		}
		a, b := uf.find(u), uf.find(v)
		if a == b {
			return g[a]
		}
		return -1
	}
	for _, q := range query {
		ans = append(ans, f(q[0], q[1]))
	}
	return
}
```

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

    getSize(x: number): number {
        return this.size[this.find(x)];
    }
}

function minimumCost(n: number, edges: number[][], query: number[][]): number[] {
    const uf = new UnionFind(n);
    const g: number[] = Array(n).fill(-1);
    for (const [u, v, _] of edges) {
        uf.union(u, v);
    }
    for (const [u, _, w] of edges) {
        const root = uf.find(u);
        g[root] &= w;
    }
    const f = (u: number, v: number): number => {
        if (u === v) {
            return 0;
        }
        const [a, b] = [uf.find(u), uf.find(v)];
        return a === b ? g[a] : -1;
    };
    return query.map(([u, v]) => f(u, v));
}
```

<!-- tabs:end -->

<!-- end -->
