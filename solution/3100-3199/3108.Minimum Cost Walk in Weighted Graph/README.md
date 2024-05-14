# [3108. 带权图里旅途的最小代价](https://leetcode.cn/problems/minimum-cost-walk-in-weighted-graph)

[English Version](/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/README_EN.md)

<!-- tags:位运算,并查集,图,数组 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的带权无向图，节点编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。</p>

<p>给你一个整数 <code>n</code>&nbsp;和一个数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条权值为&nbsp;<code>w<sub>i</sub></code>&nbsp;的无向边。</p>

<p>在图中，一趟旅途包含一系列节点和边。旅途开始和结束点都是图中的节点，且图中存在连接旅途中相邻节点的边。注意，一趟旅途可能访问同一条边或者同一个节点多次。</p>

<p>如果旅途开始于节点 <code>u</code>&nbsp;，结束于节点 <code>v</code>&nbsp;，我们定义这一趟旅途的 <strong>代价</strong>&nbsp;是经过的边权按位与 <code>AND</code>&nbsp;的结果。换句话说，如果经过的边对应的边权为&nbsp;<code>w<sub>0</sub>, w<sub>1</sub>, w<sub>2</sub>, ..., w<sub>k</sub></code>&nbsp;，那么代价为<code>w<sub>0</sub> &amp; w<sub>1</sub> &amp; w<sub>2</sub> &amp; ... &amp; w<sub>k</sub></code>&nbsp;，其中&nbsp;<code>&amp;</code>&nbsp;表示按位与&nbsp;<code>AND</code>&nbsp;操作。</p>

<p>给你一个二维数组&nbsp;<code>query</code>&nbsp;，其中&nbsp;<code>query[i] = [s<sub>i</sub>, t<sub>i</sub>]</code>&nbsp;。对于每一个查询，你需要找出从节点开始&nbsp;<code>s<sub>i</sub></code>&nbsp;，在节点&nbsp;<code>t<sub>i</sub></code>&nbsp;处结束的旅途的最小代价。如果不存在这样的旅途，答案为&nbsp;<code>-1</code>&nbsp;。</p>

<p>返回数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[i]</code><em>&nbsp;</em>表示对于查询 <code>i</code>&nbsp;的&nbsp;<strong>最小</strong>&nbsp;旅途代价。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[1,-1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/images/q4_example1-1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 351px; height: 141px;" /></p>

<p>第一个查询想要得到代价为 1 的旅途，我们依次访问：<code>0-&gt;1</code>（边权为 7 ）<code>1-&gt;2</code>&nbsp;（边权为 1 ）<code>2-&gt;1</code>（边权为 1 ）<code>1-&gt;3</code>&nbsp;（边权为 7 ）。</p>

<p>第二个查询中，无法从节点 3 到节点 4 ，所以答案为 -1 。</p>

<p><strong class="example">示例 2：</strong></p>
</div>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[0]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3108.Minimum%20Cost%20Walk%20in%20Weighted%20Graph/images/q4_example2e.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 211px; height: 181px;" /></p>

<p>第一个查询想要得到代价为 0 的旅途，我们依次访问：<code>1-&gt;2</code>（边权为 1 ），<code>2-&gt;1</code>（边权 为 6 ），<code>1-&gt;2</code>（边权为 1 ）。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= query.length &lt;= 10<sup>5</sup></code></li>
	<li><code>query[i].length == 2</code></li>
	<li><code>0 &lt;= s<sub>i</sub>, t<sub>i</sub> &lt;= n - 1</code></li>
</ul>

## 解法

### 方法一：贪心 + 并查集

我们注意到，一个正整数与其他若干个正整数不断进行“按位与”运算，结果只会越来越小。因此，为了使得旅途的代价尽可能小，我们应该将处于同一个连通分量的所有边的权值进行“按位与”运算，然后再进行查询。

那么，问题转化为，如何找出同一个连通份量的所有边，然后进行“按位与”运算。

我们可以用并查集来维护连通分量。

具体地，我们遍历每一条边 $(u, v, w)$，将 $u$ 和 $v$ 进行合并。然后，我们再一次遍历每一条边 $(u, v, w)$，找到 $u$ 和 $v$ 所在的连通分量的根节点 $root$，用一个数组 $g$ 记录每个连通分量的所有边的权值进行“按位与”运算的结果。

最后，对于每一个查询 $(s, t)$，我们首先判断 $s$ 与 $t$ 是否相等，如果相等，那么答案为 $0$，否则，我们判断 $s$ 和 $t$ 是否在同一个连通分量中，如果在同一个连通分量中，那么答案为该查询的连通分量的根节点的 $g$ 值，否则，答案为 $-1$。

时间复杂度 $O((n + m + q) \times \alpha(n))$，空间复杂度 $O(n)$。其中 $n$, $m$ 和 $q$ 分别表示节点数、边数和查询数，而 $\alpha(n)$ 表示 Ackermann 函数的反函数。

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
