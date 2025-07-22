---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0685.Redundant%20Connection%20II/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [685. 冗余连接 II](https://leetcode.cn/problems/redundant-connection-ii)

[English Version](/solution/0600-0699/0685.Redundant%20Connection%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在本问题中，有根树指满足以下条件的 <strong>有向</strong> 图。该树只有一个根节点，所有其他节点都是该根节点的后继。该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。</p>

<p>输入一个有向图，该图由一个有着 <code>n</code> 个节点（节点值不重复，从 <code>1</code> 到 <code>n</code>）的树及一条附加的有向边构成。附加的边包含在 <code>1</code> 到 <code>n</code> 中的两个不同顶点间，这条附加的边不属于树中已存在的边。</p>

<p>结果图是一个以边组成的二维数组&nbsp;<code>edges</code> 。 每个元素是一对 <code>[u<sub>i</sub>, v<sub>i</sub>]</code>，用以表示 <strong>有向 </strong>图中连接顶点 <code>u<sub>i</sub></code> 和顶点 <code>v<sub>i</sub></code> 的边，其中 <code>u<sub>i</sub></code> 是 <code>v<sub>i</sub></code> 的一个父节点。</p>

<p>返回一条能删除的边，使得剩下的图是有 <code>n</code> 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0685.Redundant%20Connection%20II/images/graph1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>输入：</strong>edges = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>[2,3]
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0685.Redundant%20Connection%20II/images/graph2.jpg" style="width: 222px; height: 382px;" />
<pre>
<strong>输入：</strong>edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
<strong>输出：</strong>[4,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集

根据题目描述，对于一棵有根树，根节点的入度为 $0$，其余节点的入度为 $1$。在向树中添加一条边之后，可能会出现以下三种情况：

1. 添加的边指向了非根节点，该节点的入度变为 $2$，此时图中不存在有向环；

    ```plaintext
       1
      / \
     v   v
     2-->3
    ```

1. 添加的边指向了非根节点，该节点的入度变为 $2$，此时图中存在有向环；

    ```plaintext
       1
       |
       v
       2 <--> 3
    ```

1. 添加的边指向了根节点，根节点的入度变为 $1$，此时图中存在有向环，但不存在入度为 $2$ 的节点。

    ```plaintext
        1
        /^
       v  \
       2-->3
    ```

因此，我们首先计算每个节点的入度，如果存在入度为 $2$ 的节点，我们定位到该节点对应的两条边，分别记为 $\textit{dup}[0]$ 和 $\textit{dup}[1]$。如果在删除 $\textit{dup}[1]$ 之后，剩余的边无法形成树，则说明 $\textit{dup}[0]$ 是需要删除的边；否则 $\textit{dup}[1]$ 是需要删除的边。

如果不存在入度为 $2$ 的节点，我们遍历数组 $\textit{edges}$，对于每条边 $(u, v)$，我们使用并查集维护节点之间的连通性。如果 $u$ 和 $v$ 已经连通，说明图中存在有向环，此时当前边即为需要删除的边。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 为边的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRedundantDirectedConnection(self, edges: List[List[int]]) -> List[int]:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(edges)
        ind = [0] * n
        for _, v in edges:
            ind[v - 1] += 1
        dup = [i for i, (_, v) in enumerate(edges) if ind[v - 1] == 2]
        p = list(range(n))
        if dup:
            for i, (u, v) in enumerate(edges):
                if i == dup[1]:
                    continue
                pu, pv = find(u - 1), find(v - 1)
                if pu == pv:
                    return edges[dup[0]]
                p[pu] = pv
            return edges[dup[1]]
        for i, (u, v) in enumerate(edges):
            pu, pv = find(u - 1), find(v - 1)
            if pu == pv:
                return edges[i]
            p[pu] = pv
```

#### Java

```java
class Solution {
    private int[] p;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] ind = new int[n];
        for (var e : edges) {
            ++ind[e[1] - 1];
        }
        List<Integer> dup = new ArrayList<>();
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            if (ind[edges[i][1] - 1] == 2) {
                dup.add(i);
            }
            p[i] = i;
        }
        if (!dup.isEmpty()) {
            for (int i = 0; i < n; ++i) {
                if (i == dup.get(1)) {
                    continue;
                }
                int pu = find(edges[i][0] - 1);
                int pv = find(edges[i][1] - 1);
                if (pu == pv) {
                    return edges[dup.get(0)];
                }
                p[pu] = pv;
            }
            return edges[dup.get(1)];
        }
        for (int i = 0;; ++i) {
            int pu = find(edges[i][0] - 1);
            int pv = find(edges[i][1] - 1);
            if (pu == pv) {
                return edges[i];
            }
            p[pu] = pv;
        }
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<int> ind(n);
        for (const auto& e : edges) {
            ++ind[e[1] - 1];
        }
        vector<int> dup;
        for (int i = 0; i < n; ++i) {
            if (ind[edges[i][1] - 1] == 2) {
                dup.push_back(i);
            }
        }
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return x == p[x] ? x : p[x] = find(p[x]);
        };
        if (!dup.empty()) {
            for (int i = 0; i < n; ++i) {
                if (i == dup[1]) {
                    continue;
                }
                int pu = find(edges[i][0] - 1);
                int pv = find(edges[i][1] - 1);
                if (pu == pv) {
                    return edges[dup[0]];
                }
                p[pu] = pv;
            }
            return edges[dup[1]];
        }
        for (int i = 0;; ++i) {
            int pu = find(edges[i][0] - 1);
            int pv = find(edges[i][1] - 1);
            if (pu == pv) {
                return edges[i];
            }
            p[pu] = pv;
        }
    }
};
```

#### Go

```go
func findRedundantDirectedConnection(edges [][]int) []int {
	n := len(edges)
	ind := make([]int, n)
	for _, e := range edges {
		ind[e[1]-1]++
	}
	dup := []int{}
	for i, e := range edges {
		if ind[e[1]-1] == 2 {
			dup = append(dup, i)
		}
	}
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	if len(dup) > 0 {
		for i, e := range edges {
			if i == dup[1] {
				continue
			}
			pu, pv := find(e[0]-1), find(e[1]-1)
			if pu == pv {
				return edges[dup[0]]
			}
			p[pu] = pv
		}
		return edges[dup[1]]
	}
	for _, e := range edges {
		pu, pv := find(e[0]-1), find(e[1]-1)
		if pu == pv {
			return e
		}
		p[pu] = pv
	}
	return nil
}
```

#### TypeScript

```ts
function findRedundantDirectedConnection(edges: number[][]): number[] {
    const n = edges.length;
    const ind: number[] = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++ind[v - 1];
    }
    const dup: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (ind[edges[i][1] - 1] === 2) {
            dup.push(i);
        }
    }
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    if (dup.length) {
        for (let i = 0; i < n; ++i) {
            if (i === dup[1]) {
                continue;
            }
            const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
            if (pu === pv) {
                return edges[dup[0]];
            }
            p[pu] = pv;
        }
        return edges[dup[1]];
    }
    for (let i = 0; ; ++i) {
        const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
        if (pu === pv) {
            return edges[i];
        }
        p[pu] = pv;
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantDirectedConnection = function (edges) {
    const n = edges.length;
    const ind = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++ind[v - 1];
    }
    const dup = [];
    for (let i = 0; i < n; ++i) {
        if (ind[edges[i][1] - 1] === 2) {
            dup.push(i);
        }
    }
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    if (dup.length) {
        for (let i = 0; i < n; ++i) {
            if (i === dup[1]) {
                continue;
            }
            const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
            if (pu === pv) {
                return edges[dup[0]];
            }
            p[pu] = pv;
        }
        return edges[dup[1]];
    }
    for (let i = 0; ; ++i) {
        const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
        if (pu === pv) {
            return edges[i];
        }
        p[pu] = pv;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：并查集（模板做法）

这里给出一个并查集的模板做法，供大家参考。

时间复杂度 $O(n \alpha(n))$，空间复杂度 $O(n)$。其中 $n$ 为边的数量，而 $\alpha(n)$ 是阿克曼函数的反函数，可以认为是一个很小的常数。

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    __slots__ = "p", "size"

    def __init__(self, n: int):
        self.p: List[int] = list(range(n))
        self.size: List[int] = [1] * n

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
    def findRedundantDirectedConnection(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        ind = [0] * n
        for _, v in edges:
            ind[v - 1] += 1
        dup = [i for i, (_, v) in enumerate(edges) if ind[v - 1] == 2]
        uf = UnionFind(n)
        if dup:
            for i, (u, v) in enumerate(edges):
                if i == dup[1]:
                    continue
                if not uf.union(u - 1, v - 1):
                    return edges[dup[0]]
            return edges[dup[1]]
        for i, (u, v) in enumerate(edges):
            if not uf.union(u - 1, v - 1):
                return edges[i]
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
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] ind = new int[n];
        for (var e : edges) {
            ++ind[e[1] - 1];
        }
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (ind[edges[i][1] - 1] == 2) {
                dup.add(i);
            }
        }
        UnionFind uf = new UnionFind(n);
        if (!dup.isEmpty()) {
            for (int i = 0; i < n; ++i) {
                if (i == dup.get(1)) {
                    continue;
                }
                if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                    return edges[dup.get(0)];
                }
            }
            return edges[dup.get(1)];
        }
        for (int i = 0;; ++i) {
            if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[i];
            }
        }
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
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<int> ind(n);
        for (const auto& e : edges) {
            ++ind[e[1] - 1];
        }
        vector<int> dup;
        for (int i = 0; i < n; ++i) {
            if (ind[edges[i][1] - 1] == 2) {
                dup.push_back(i);
            }
        }
        UnionFind uf(n);
        if (!dup.empty()) {
            for (int i = 0; i < n; ++i) {
                if (i == dup[1]) {
                    continue;
                }
                if (!uf.unite(edges[i][0] - 1, edges[i][1] - 1)) {
                    return edges[dup[0]];
                }
            }
            return edges[dup[1]];
        }
        for (int i = 0;; ++i) {
            if (!uf.unite(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[i];
            }
        }
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

func findRedundantDirectedConnection(edges [][]int) []int {
	n := len(edges)
	ind := make([]int, n)
	for _, e := range edges {
		ind[e[1]-1]++
	}
	dup := []int{}
	for i, e := range edges {
		if ind[e[1]-1] == 2 {
			dup = append(dup, i)
		}
	}
	uf := newUnionFind(n)
	if len(dup) > 0 {
		for i, e := range edges {
			if i == dup[1] {
				continue
			}
			if !uf.union(e[0]-1, e[1]-1) {
				return edges[dup[0]]
			}
		}
		return edges[dup[1]]
	}
	for _, e := range edges {
		if !uf.union(e[0]-1, e[1]-1) {
			return e
		}
	}
	return nil
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

function findRedundantDirectedConnection(edges: number[][]): number[] {
    const n = edges.length;
    const ind: number[] = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++ind[v - 1];
    }
    const dup: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (ind[edges[i][1] - 1] === 2) {
            dup.push(i);
        }
    }
    const uf = new UnionFind(n);
    if (dup.length) {
        for (let i = 0; i < n; ++i) {
            if (i === dup[1]) {
                continue;
            }
            if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[dup[0]];
            }
        }
        return edges[dup[1]];
    }
    for (let i = 0; ; ++i) {
        if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
            return edges[i];
        }
    }
}
```

#### JavaScript

```js
class UnionFind {
    constructor(n) {
        this.p = Array.from({ length: n }, (_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x) {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a, b) {
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

/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantDirectedConnection = function (edges) {
    const n = edges.length;
    const ind = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++ind[v - 1];
    }
    const dup = [];
    for (let i = 0; i < n; ++i) {
        if (ind[edges[i][1] - 1] === 2) {
            dup.push(i);
        }
    }
    const uf = new UnionFind(n);
    if (dup.length) {
        for (let i = 0; i < n; ++i) {
            if (i === dup[1]) {
                continue;
            }
            if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                return edges[dup[0]];
            }
        }
        return edges[dup[1]];
    }
    for (let i = 0; ; ++i) {
        if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
            return edges[i];
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
