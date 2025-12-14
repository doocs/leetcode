---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/README_EN.md
rating: 1892
source: Weekly Contest 457 Q3
tags:
    - Union Find
    - Graph
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [3608. Minimum Time for K Connected Components](https://leetcode.com/problems/minimum-time-for-k-connected-components)

[中文文档](/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that can be removed at <code>time<sub>i</sub></code>.</p>

<p>You are also given an integer <code>k</code>.</p>

<p>Initially, the graph may be connected or disconnected. Your task is to find the <strong>minimum</strong> time <code>t</code> such that after removing all edges with <code>time &lt;= t</code>, the graph contains <strong>at least</strong> <code>k</code> connected components.</p>

<p>Return the <strong>minimum</strong> time <code>t</code>.</p>

<p>A <strong>connected component</strong> is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,3]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022724.png" style="width: 230px; height: 85px;" /></p>

<ul>
	<li>Initially, there is one connected component <code>{0, 1}</code>.</li>
	<li>At <code>time = 1</code> or <code>2</code>, the graph remains unchanged.</li>
	<li>At <code>time = 3</code>, edge <code>[0, 1]</code> is removed, resulting in <code>k = 2</code> connected components <code>{0}</code>, <code>{1}</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[1,2,4]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022812.png" style="width: 180px; height: 164px;" /></p>

<ul>
	<li>Initially, there is one connected component <code>{0, 1, 2}</code>.</li>
	<li>At <code>time = 2</code>, edge <code>[0, 1]</code> is removed, resulting in two connected components <code>{0}</code>, <code>{1, 2}</code>.</li>
	<li>At <code>time = 4</code>, edge <code>[1, 2]</code> is removed, resulting in <code>k = 3</code> connected components <code>{0}</code>, <code>{1}</code>, <code>{2}</code>. Thus, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,2,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022930.png" style="width: 180px; height: 155px;" /></p>

<ul>
	<li>Since there are already <code>k = 2</code> disconnected components <code>{1}</code>, <code>{0, 2}</code>, no edge removal is needed. Thus, the answer is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>There are no duplicate edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

We can sort the edges by time in ascending order, then starting from the edge with the largest time, add edges to the graph one by one, while using a union-find data structure to maintain the number of connected components in the current graph. When the number of connected components is less than $k$, the current time is the minimum time we are looking for.

The time complexity is $O(n \times \alpha(n))$, and the space complexity is $O(n)$, where $\alpha$ is the inverse Ackermann function.

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
    def minTime(self, n: int, edges: List[List[int]], k: int) -> int:
        edges.sort(key=lambda x: x[2])
        uf = UnionFind(n)
        cnt = n
        for u, v, t in edges[::-1]:
            if uf.union(u, v):
                cnt -= 1
                if cnt < k:
                    return t
        return 0
```

#### Java

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
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
        int pa = find(a);
        int pb = find(b);
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
    public int minTime(int n, int[][] edges, int k) {
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        UnionFind uf = new UnionFind(n);
        int cnt = n;

        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0];
            int v = edges[i][1];
            int t = edges[i][2];

            if (uf.union(u, v)) {
                if (--cnt < k) {
                    return t;
                }
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    vector<int> p;
    vector<int> size;

    UnionFind(int n) {
        p.resize(n);
        size.resize(n, 1);
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    bool unite(int a, int b) {
        int pa = find(a);
        int pb = find(b);
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
};

class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, int k) {
        sort(edges.begin(), edges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });

        UnionFind uf(n);
        int cnt = n;

        for (int i = edges.size() - 1; i >= 0; i--) {
            int u = edges[i][0];
            int v = edges[i][1];
            int t = edges[i][2];

            if (uf.unite(u, v)) {
                cnt--;
                if (cnt < k) {
                    return t;
                }
            }
        }
        return 0;
    }
};
```

#### Go

```go
type UnionFind struct {
    p    []int
    size []int
}

func NewUnionFind(n int) *UnionFind {
    uf := &UnionFind{
        p:    make([]int, n),
        size: make([]int, n),
    }
    for i := 0; i < n; i++ {
        uf.p[i] = i
        uf.size[i] = 1
    }
    return uf
}

func (uf *UnionFind) find(x int) int {
    if uf.p[x] != x {
        uf.p[x] = uf.find(uf.p[x])
    }
    return uf.p[x]
}

func (uf *UnionFind) union(a, b int) bool {
    pa := uf.find(a)
    pb := uf.find(b)
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

func minTime(n int, edges [][]int, k int) int {
    sort.Slice(edges, func(i, j int) bool {
        return edges[i][2] < edges[j][2]
    })

    uf := NewUnionFind(n)
    cnt := n

    for i := len(edges) - 1; i >= 0; i-- {
        u := edges[i][0]
        v := edges[i][1]
        t := edges[i][2]

        if uf.union(u, v) {
            cnt--
            if cnt < k {
                return t
            }
        }
    }
    return 0
}
```

#### TypeScript

```ts
class UnionFind {
    p: number[];
    size: number[];

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
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
        if (pa === pb) return false;

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

function minTime(n: number, edges: number[][], k: number): number {
    edges.sort((a, b) => a[2] - b[2]);

    const uf = new UnionFind(n);
    let cnt = n;

    for (let i = edges.length - 1; i >= 0; i--) {
        const [u, v, t] = edges[i];

        if (uf.union(u, v)) {
            if (--cnt < k) {
                return t;
            }
        }
    }

    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
