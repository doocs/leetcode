# [1579. Remove Max Number of Edges to Keep Graph Fully Traversable](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable)

[中文文档](/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/README.md)

## Description

<p>Alice and Bob have an undirected graph of <code>n</code> nodes and three types of edges:</p>

<ul>
	<li>Type 1: Can be traversed by Alice only.</li>
	<li>Type 2: Can be traversed by Bob only.</li>
	<li>Type 3: Can be traversed by both Alice and Bob.</li>
</ul>

<p>Given an array <code>edges</code> where <code>edges[i] = [type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>]</code> represents a bidirectional edge of type <code>type<sub>i</sub></code> between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.</p>

<p>Return <em>the maximum number of edges you can remove, or return</em> <code>-1</code> <em>if Alice and Bob cannot fully traverse the graph.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/ex1.png" style="width: 179px; height: 191px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/ex2.png" style="width: 178px; height: 190px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
<strong>Output:</strong> 0
<strong>Explanation: </strong>Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/ex3.png" style="width: 178px; height: 190px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
<strong>Output:</strong> -1
<b>Explanation: </b>In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it&#39;s impossible to make the graph fully traversable.</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10<sup>5</sup>, 3 * n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 &lt;= type<sub>i</sub> &lt;= 3</code></li>
	<li><code>1 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt;= n</code></li>
	<li>All tuples <code>(type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>)</code> are distinct.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.n = n

    def union(self, a, b):
        pa, pb = self.find(a - 1), self.find(b - 1)
        if pa == pb:
            return False
        self.p[pa] = pb
        self.n -= 1
        return True

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]


class Solution:
    def maxNumEdgesToRemove(self, n: int, edges: List[List[int]]) -> int:
        ufa, ufb = UnionFind(n), UnionFind(n)
        ans = 0
        for t, u, v in edges:
            if t == 3:
                if ufa.union(u, v):
                    ufb.union(u, v)
                else:
                    ans += 1
        ans += sum(
            (t == 1 and not ufa.union(u, v)) or (t == 2 and not ufb.union(u, v))
            for t, u, v in edges
        )
        return ans if ufa.n == 1 and ufb.n == 1 else -1
```

### **Java**

```java
class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;
        for (int[] e : edges) {
            if (e[0] == 3) {
                if (ufa.union(e[1], e[2])) {
                    ufb.union(e[1], e[2]);
                } else {
                    ++ans;
                }
            }
        }
        for (int[] e : edges) {
            if ((e[0] == 1 && !ufa.union(e[1], e[2])) || (e[0] == 2 && !ufb.union(e[1], e[2]))) {
                ++ans;
            }
        }
        return ufa.n == 1 && ufb.n == 1 ? ans : -1;
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
        int pa = find(a - 1);
        int pb = find(b - 1);
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
        int pa = find(a - 1), pb = find(b - 1);
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
    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        UnionFind ufa(n), ufb(n);
        int ans = 0;
        for (auto& e : edges) {
            if (e[0] == 3) {
                if (ufa.unite(e[1], e[2]))
                    ufb.unite(e[1], e[2]);
                else
                    ++ans;
            }
        }
        for (auto& e : edges)
            if ((e[0] == 1 && !ufa.unite(e[1], e[2])) || (e[0] == 2 && !ufb.unite(e[1], e[2])))
                ++ans;
        return ufa.n == 1 && ufb.n == 1 ? ans : -1;
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
	pa, pb := uf.find(a-1), uf.find(b-1)
	if pa == pb {
		return false
	}
	uf.p[pa] = pb
	uf.n--
	return true
}

func maxNumEdgesToRemove(n int, edges [][]int) int {
	ufa, ufb := newUnionFind(n), newUnionFind(n)
	ans := 0
	for _, e := range edges {
		if e[0] == 3 {
			if ufa.union(e[1], e[2]) {
				ufb.union(e[1], e[2])
			} else {
				ans++
			}
		}
	}
	for _, e := range edges {
		if (e[0] == 1 && !ufa.union(e[1], e[2])) || (e[0] == 2 && !ufb.union(e[1], e[2])) {
			ans++
		}
	}
	if ufa.n == 1 && ufb.n == 1 {
		return ans
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
