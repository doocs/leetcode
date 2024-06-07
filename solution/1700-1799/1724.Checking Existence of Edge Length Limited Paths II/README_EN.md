---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/README_EN.md
tags:
    - Union Find
    - Graph
    - Minimum Spanning Tree
---

<!-- problem:start -->

# [1724. Checking Existence of Edge Length Limited Paths II 🔒](https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths-ii)

[中文文档](/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/README.md)

## Description

<!-- description:start -->

<p>An undirected graph of <code>n</code> nodes is defined by <code>edgeList</code>, where <code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code> denotes an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with distance <code>dis<sub>i</sub></code>. Note that there may be <strong>multiple</strong> edges between two nodes, and the graph may not be connected.</p>

<p>Implement the <code>DistanceLimitedPathsExist</code> class:</p>

<ul>
	<li><code>DistanceLimitedPathsExist(int n, int[][] edgeList)</code> Initializes the class with an undirected graph.</li>
	<li><code>boolean query(int p, int q, int limit)</code> Returns <code>true</code> if there exists a path from <code>p</code> to <code>q</code> such that each edge on the path has a distance <strong>strictly less than</strong> <code>limit</code>, and otherwise <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1724.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths%20II/images/messed.png" style="width: 300px; height: 298px;" /></strong></p>

<pre>
<strong>Input</strong>
[&quot;DistanceLimitedPathsExist&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]
[[6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]], [2, 3, 2], [1, 3, 3], [2, 0, 3], [0, 5, 6]]
<strong>Output</strong>
[null, true, false, true, false]

<strong>Explanation</strong>
DistanceLimitedPathsExist distanceLimitedPathsExist = new DistanceLimitedPathsExist(6, [[0, 2, 4], [0, 3, 2], [1, 2, 3], [2, 3, 1], [4, 5, 5]]);
distanceLimitedPathsExist.query(2, 3, 2); // return true. There is an edge from 2 to 3 of distance 1, which is less than 2.
distanceLimitedPathsExist.query(1, 3, 3); // return false. There is no way to go from 1 to 3 with distances <strong>strictly</strong> less than 3.
distanceLimitedPathsExist.query(2, 0, 3); // return true. There is a way to go from 2 to 0 with distance &lt; 3: travel from 2 to 3 to 0.
distanceLimitedPathsExist.query(0, 5, 6); // return false. There are no paths from 0 to 5.
</pre>

<p>&nbsp;</p>
<p><code><strong>Constraints:</strong></code></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edgeList.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, p, q &lt;= n-1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p != q</code></li>
	<li><code>1 &lt;= dis<sub>i</sub>, limit &lt;= 10<sup>9</sup></code></li>
	<li>At most&nbsp;<code>10<sup>4</sup></code> calls will be made to <code>query</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class PersistentUnionFind:
    def __init__(self, n):
        self.rank = [0] * n
        self.p = list(range(n))
        self.version = [inf] * n

    def find(self, x, t=inf):
        if self.p[x] == x or self.version[x] >= t:
            return x
        return self.find(self.p[x], t)

    def union(self, a, b, t):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.rank[pa] > self.rank[pb]:
            self.version[pb] = t
            self.p[pb] = pa
        else:
            self.version[pa] = t
            self.p[pa] = pb
            if self.rank[pa] == self.rank[pb]:
                self.rank[pb] += 1
        return True


class DistanceLimitedPathsExist:
    def __init__(self, n: int, edgeList: List[List[int]]):
        self.puf = PersistentUnionFind(n)
        edgeList.sort(key=lambda x: x[2])
        for u, v, dis in edgeList:
            self.puf.union(u, v, dis)

    def query(self, p: int, q: int, limit: int) -> bool:
        return self.puf.find(p, limit) == self.puf.find(q, limit)
```

#### Java

```java
class PersistentUnionFind {
    private final int inf = 1 << 30;
    private int[] rank;
    private int[] parent;
    private int[] version;

    public PersistentUnionFind(int n) {
        rank = new int[n];
        parent = new int[n];
        version = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            version[i] = inf;
        }
    }

    public int find(int x, int t) {
        if (parent[x] == x || version[x] >= t) {
            return x;
        }
        return find(parent[x], t);
    }

    public boolean union(int a, int b, int t) {
        int pa = find(a, inf);
        int pb = find(b, inf);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] > rank[pb]) {
            version[pb] = t;
            parent[pb] = pa;
        } else {
            version[pa] = t;
            parent[pa] = pb;
            if (rank[pa] == rank[pb]) {
                rank[pb]++;
            }
        }
        return true;
    }
}

public class DistanceLimitedPathsExist {
    private PersistentUnionFind puf;

    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        puf = new PersistentUnionFind(n);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for (var e : edgeList) {
            puf.union(e[0], e[1], e[2]);
        }
    }

    public boolean query(int p, int q, int limit) {
        return puf.find(p, limit) == puf.find(q, limit);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
 * boolean param_1 = obj.query(p,q,limit);
 */
```

#### C++

```cpp
class PersistentUnionFind {
private:
    vector<int> rank;
    vector<int> parent;
    vector<int> version;

public:
    PersistentUnionFind(int n)
        : rank(n, 0)
        , parent(n)
        , version(n, INT_MAX) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x, int t) {
        if (parent[x] == x || version[x] >= t) {
            return x;
        }
        return find(parent[x], t);
    }

    bool unionSet(int a, int b, int t) {
        int pa = find(a, INT_MAX);
        int pb = find(b, INT_MAX);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] > rank[pb]) {
            version[pb] = t;
            parent[pb] = pa;
        } else {
            version[pa] = t;
            parent[pa] = pb;
            if (rank[pa] == rank[pb]) {
                rank[pb]++;
            }
        }
        return true;
    }
};

class DistanceLimitedPathsExist {
private:
    PersistentUnionFind puf;

public:
    DistanceLimitedPathsExist(int n, vector<vector<int>>& edgeList)
        : puf(n) {
        sort(edgeList.begin(), edgeList.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[2] < b[2];
            });

        for (const auto& edge : edgeList) {
            puf.unionSet(edge[0], edge[1], edge[2]);
        }
    }

    bool query(int p, int q, int limit) {
        return puf.find(p, limit) == puf.find(q, limit);
    }
};

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist* obj = new DistanceLimitedPathsExist(n, edgeList);
 * bool param_1 = obj->query(p,q,limit);
 */
```

#### Go

```go
type PersistentUnionFind struct {
	rank    []int
	parent  []int
	version []int
}

func NewPersistentUnionFind(n int) *PersistentUnionFind {
	rank := make([]int, n)
	parent := make([]int, n)
	version := make([]int, n)

	for i := 0; i < n; i++ {
		parent[i] = i
	}

	return &PersistentUnionFind{
		rank:    rank,
		parent:  parent,
		version: version,
	}
}

func (uf *PersistentUnionFind) find(x int, t int) int {
	if uf.parent[x] == x || uf.version[x] >= t {
		return x
	}
	return uf.find(uf.parent[x], t)
}

func (uf *PersistentUnionFind) union(a, b, t int) bool {
	pa := uf.find(a, int(^uint(0)>>1))
	pb := uf.find(b, int(^uint(0)>>1))

	if pa == pb {
		return false
	}

	if uf.rank[pa] > uf.rank[pb] {
		uf.version[pb] = t
		uf.parent[pb] = pa
	} else {
		uf.version[pa] = t
		uf.parent[pa] = pb
		if uf.rank[pa] == uf.rank[pb] {
			uf.rank[pb]++
		}
	}

	return true
}

type DistanceLimitedPathsExist struct {
	puf *PersistentUnionFind
}

func Constructor(n int, edgeList [][]int) DistanceLimitedPathsExist {
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] < edgeList[j][2]
	})

	puf := NewPersistentUnionFind(n)

	for _, edge := range edgeList {
		puf.union(edge[0], edge[1], edge[2])
	}

	return DistanceLimitedPathsExist{
		puf: puf,
	}
}

func (dle *DistanceLimitedPathsExist) Query(p, q, limit int) bool {
	return dle.puf.find(p, limit) == dle.puf.find(q, limit)
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * obj := Constructor(n, edgeList);
 * param_1 := obj.Query(p,q,limit);
 */
```

#### TypeScript

```ts
class PersistentUnionFind {
    private rank: number[];
    private parent: number[];
    private version: number[];

    constructor(n: number) {
        this.rank = Array(n).fill(0);
        this.parent = Array.from({ length: n }, (_, i) => i);
        this.version = Array(n).fill(Infinity);
    }

    find(x: number, t: number): number {
        if (this.parent[x] === x || this.version[x] >= t) {
            return x;
        }
        return this.find(this.parent[x], t);
    }

    union(a: number, b: number, t: number): boolean {
        const pa = this.find(a, Infinity);
        const pb = this.find(b, Infinity);

        if (pa === pb) {
            return false;
        }

        if (this.rank[pa] > this.rank[pb]) {
            this.version[pb] = t;
            this.parent[pb] = pa;
        } else {
            this.version[pa] = t;
            this.parent[pa] = pb;
            if (this.rank[pa] === this.rank[pb]) {
                this.rank[pb]++;
            }
        }

        return true;
    }
}

class DistanceLimitedPathsExist {
    private puf: PersistentUnionFind;

    constructor(n: number, edgeList: number[][]) {
        this.puf = new PersistentUnionFind(n);
        edgeList.sort((a, b) => a[2] - b[2]);
        for (const [u, v, dis] of edgeList) {
            this.puf.union(u, v, dis);
        }
    }

    query(p: number, q: number, limit: number): boolean {
        return this.puf.find(p, limit) === this.puf.find(q, limit);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * var obj = new DistanceLimitedPathsExist(n, edgeList)
 * var param_1 = obj.query(p,q,limit)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
