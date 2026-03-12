---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3600.Maximize%20Spanning%20Tree%20Stability%20with%20Upgrades/README_EN.md
rating: 2301
source: Weekly Contest 456 Q4
tags:
    - Greedy
    - Union Find
    - Graph
    - Binary Search
    - Minimum Spanning Tree
---

<!-- problem:start -->

# [3600. Maximize Spanning Tree Stability with Upgrades](https://leetcode.com/problems/maximize-spanning-tree-stability-with-upgrades)

[中文文档](/solution/3600-3699/3600.Maximize%20Spanning%20Tree%20Stability%20with%20Upgrades/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, representing <code>n</code> nodes numbered from 0 to <code>n - 1</code> and a list of <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, s<sub>i</sub>, must<sub>i</sub>]</code>:</p>

<ul>
	<li><code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</li>
	<li><code>s<sub>i</sub></code> is the strength of the edge.</li>
	<li><code>must<sub>i</sub></code> is an integer (0 or 1). If <code>must<sub>i</sub> == 1</code>, the edge <strong>must</strong> be included in the<strong> </strong><strong>spanning tree</strong>. These edges <strong>cannot</strong> be <strong>upgraded</strong>.</li>
</ul>

<p>You are also given an integer <code>k</code>, the <strong>maximum</strong> number of upgrades you can perform. Each upgrade <strong>doubles</strong> the strength of an edge, and each eligible edge (with <code>must<sub>i</sub> == 0</code>) can be upgraded <strong>at most</strong> once.</p>

<p>The <strong>stability</strong> of a spanning tree is defined as the <strong>minimum</strong> strength score among all edges included in it.</p>

<p>Return the <strong>maximum</strong> possible stability of any valid spanning tree. If it is impossible to connect all nodes, return <code>-1</code>.</p>

<p><strong>Note</strong>: A <strong>spanning tree</strong> of a graph with <code>n</code> nodes is a subset of the edges that connects all nodes together (i.e. the graph is <strong>connected</strong>) <em>without</em> forming any cycles, and uses <strong>exactly</strong> <code>n - 1</code> edges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Edge <code>[0,1]</code> with strength = 2 must be included in the spanning tree.</li>
	<li>Edge <code>[1,2]</code> is optional and can be upgraded from 3 to 6 using one upgrade.</li>
	<li>The resulting spanning tree includes these two edges with strengths 2 and 6.</li>
	<li>The minimum strength in the spanning tree is 2, which is the maximum possible stability.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since all edges are optional and up to <code>k = 2</code> upgrades are allowed.</li>
	<li>Upgrade edges <code>[0,1]</code> from 4 to 8 and <code>[1,2]</code> from 3 to 6.</li>
	<li>The resulting spanning tree includes these two edges with strengths 8 and 6.</li>
	<li>The minimum strength in the tree is 6, which is the maximum possible stability.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, s<sub>i</sub>, must<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= s<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>must<sub>i</sub></code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= k &lt;= n</code></li>
	<li>There are no duplicate edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Union-Find

According to the problem description, the stability of a spanning tree is determined by the minimum strength edge in it. If a stability $x$ is feasible, then for any $y < x$, stability $y$ is also feasible. Therefore, we can use binary search to find the maximum stability.

We first add all required edges into the Union-Find and record the minimum strength $mn$ among them. If there is a cycle among the required edges, return $-1$ directly. Then we add all edges into the Union-Find; if the final number of connected components is greater than $1$, it means not all nodes can be connected, and we return $-1$.

Next, we perform binary search in the range $[1, mn]$. We define a function $\text{check}(lim)$ to check whether there exists a spanning tree with stability at least $lim$. In the $\text{check}$ function, we first add all edges with strength no less than $lim$ into the Union-Find. Then we try to use the upgrade count to connect the remaining edges, with the condition that the edge strength is at least $lim/2$ (since upgrading doubles the strength). If the final number of connected components in the Union-Find is $1$, a spanning tree satisfying the condition exists.

The time complexity is $O((m \times \alpha(n) + n) \times \log M)$, and the space complexity is $O(n)$. Here, $m$ is the number of edges, $n$ is the number of nodes, and $M$ is the maximum edge strength.

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n
        self.cnt = n

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
        self.cnt -= 1
        return True


class Solution:
    def maxStability(self, n: int, edges: List[List[int]], k: int) -> int:
        def check(lim: int) -> bool:
            uf = UnionFind(n)
            for u, v, s, _ in edges:
                if s >= lim:
                    uf.union(u, v)
            rem = k
            for u, v, s, _ in edges:
                if s * 2 >= lim and rem > 0:
                    if uf.union(u, v):
                        rem -= 1
            return uf.cnt == 1

        uf = UnionFind(n)
        mn = 10**6
        for u, v, s, must in edges:
            if must:
                mn = min(mn, s)
                if not uf.union(u, v):
                    return -1
        for u, v, _, _ in edges:
            uf.union(u, v)
        if uf.cnt > 1:
            return -1
        l, r = 1, mn
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

#### Java

```java
class UnionFind {
    int[] p, size;
    int cnt;

    UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        cnt = n;
        for (int i = 0; i < n; i++) {
            p[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        cnt--;
        return true;
    }
}

class Solution {

    int n;
    int[][] edges;
    int k;

    private boolean check(int lim) {
        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s >= lim) {
                uf.union(u, v);
            }
        }

        int rem = k;
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s * 2 >= lim && rem > 0) {
                if (uf.union(u, v)) {
                    rem--;
                }
            }
        }

        return uf.cnt == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {
        this.n = n;
        this.edges = edges;
        this.k = k;

        UnionFind uf = new UnionFind(n);
        int mn = (int)1e6;

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must == 1) {
                mn = Math.min(mn, s);
                if (!uf.union(u, v)) {
                    return -1;
                }
            }
        }

        for (int[] e : edges) {
            uf.union(e[0], e[1]);
        }

        if (uf.cnt > 1) {
            return -1;
        }

        int l = 1, r = mn;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    vector<int> p, size;
    int cnt;

    UnionFind(int n) {
        p.resize(n);
        size.assign(n, 1);
        cnt = n;
        for (int i = 0; i < n; i++) p[i] = i;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        cnt--;
        return true;
    }
};

class Solution {
public:
    int n, k;
    vector<vector<int>> edges;

    bool check(int lim) {
        UnionFind uf(n);

        for (auto& e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s >= lim) {
                uf.unite(u, v);
            }
        }

        int rem = k;
        for (auto& e : edges) {
            int u = e[0], v = e[1], s = e[2];
            if (s * 2 >= lim && rem > 0) {
                if (uf.unite(u, v)) {
                    rem--;
                }
            }
        }

        return uf.cnt == 1;
    }

    int maxStability(int n, vector<vector<int>>& edges, int k) {
        this->n = n;
        this->edges = edges;
        this->k = k;

        UnionFind uf(n);
        int mn = 1e6;

        for (auto& e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];
            if (must) {
                mn = min(mn, s);
                if (!uf.unite(u, v)) {
                    return -1;
                }
            }
        }

        for (auto& e : edges) {
            uf.unite(e[0], e[1]);
        }

        if (uf.cnt > 1) {
            return -1;
        }

        int l = 1, r = mn;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
};
```

#### Go

```go
type UnionFind struct {
	p    []int
	size []int
	cnt  int
}

func NewUnionFind(n int) *UnionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &UnionFind{p, size, n}
}

func (uf *UnionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *UnionFind) union(a, b int) bool {
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
	uf.cnt--
	return true
}

var (
	N int
	E [][]int
	K int
)

func check(lim int) bool {
	uf := NewUnionFind(N)

	for _, e := range E {
		u, v, s := e[0], e[1], e[2]
		if s >= lim {
			uf.union(u, v)
		}
	}

	rem := K
	for _, e := range E {
		u, v, s := e[0], e[1], e[2]
		if s*2 >= lim && rem > 0 {
			if uf.union(u, v) {
				rem--
			}
		}
	}

	return uf.cnt == 1
}

func maxStability(n int, edges [][]int, k int) int {
	N = n
	E = edges
	K = k

	uf := NewUnionFind(n)
	mn := int(1e6)

	for _, e := range edges {
		u, v, s, must := e[0], e[1], e[2], e[3]
		if must == 1 {
			if s < mn {
				mn = s
			}
			if !uf.union(u, v) {
				return -1
			}
		}
	}

	for _, e := range edges {
		uf.union(e[0], e[1])
	}

	if uf.cnt > 1 {
		return -1
	}

	l, r := 1, mn
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}

	return l
}
```

#### TypeScript

```ts
class UnionFind {
    p: number[];
    size: number[];
    cnt: number;

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
        this.size = new Array(n).fill(1);
        this.cnt = n;
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
        if (pa === pb) return false;

        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }

        this.cnt--;
        return true;
    }
}

let N: number;
let E: number[][];
let K: number;

function check(lim: number): boolean {
    const uf = new UnionFind(N);

    for (const [u, v, s] of E) {
        if (s >= lim) {
            uf.union(u, v);
        }
    }

    let rem = K;
    for (const [u, v, s] of E) {
        if (s * 2 >= lim && rem > 0) {
            if (uf.union(u, v)) {
                rem--;
            }
        }
    }

    return uf.cnt === 1;
}

function maxStability(n: number, edges: number[][], k: number): number {
    N = n;
    E = edges;
    K = k;

    const uf = new UnionFind(n);
    let mn = 1e6;

    for (const [u, v, s, must] of edges) {
        if (must) {
            mn = Math.min(mn, s);
            if (!uf.union(u, v)) return -1;
        }
    }

    for (const [u, v] of edges) {
        uf.union(u, v);
    }

    if (uf.cnt > 1) return -1;

    let l = 1,
        r = mn;

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
```

#### Rust

```rust
struct UnionFind {
    p: Vec<i32>,
    sz: Vec<i32>,
    cnt: i32,
}

impl UnionFind {
    fn new(n: i32) -> Self {
        Self {
            p: (0..n).collect(),
            sz: vec![1; n as usize],
            cnt: n,
        }
    }

    fn find(&mut self, x: i32) -> i32 {
        let i = x as usize;
        if self.p[i] != x {
            self.p[i] = self.find(self.p[i]);
        }
        self.p[i]
    }

    fn union(&mut self, a: i32, b: i32) -> bool {
        let (pa, pb) = (self.find(a), self.find(b));
        if pa == pb {
            return false;
        }
        let (a, b) = (pa as usize, pb as usize);
        if self.sz[a] < self.sz[b] {
            self.p[a] = pb;
            self.sz[b] += self.sz[a];
        } else {
            self.p[b] = pa;
            self.sz[a] += self.sz[b];
        }
        self.cnt -= 1;
        true
    }
}

impl Solution {
    pub fn max_stability(n: i32, edges: Vec<Vec<i32>>, k: i32) -> i32 {
        let mut uf = UnionFind::new(n);
        let mut mn = 1_000_000;

        for e in &edges {
            if e[3] == 1 {
                mn = mn.min(e[2]);
                if !uf.union(e[0], e[1]) {
                    return -1;
                }
            }
        }

        for e in &edges {
            uf.union(e[0], e[1]);
        }

        if uf.cnt > 1 {
            return -1;
        }

        let check = |lim: i32| {
            let mut uf = UnionFind::new(n);

            for e in &edges {
                if e[2] >= lim {
                    uf.union(e[0], e[1]);
                }
            }

            let mut rem = k;
            for e in &edges {
                if rem > 0 && e[2] * 2 >= lim && uf.union(e[0], e[1]) {
                    rem -= 1;
                }
            }

            uf.cnt == 1
        };

        let (mut l, mut r) = (1, mn);
        while l < r {
            let mid = (l + r + 1) >> 1;
            if check(mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
