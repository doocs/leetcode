---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1245.Tree%20Diameter/README_EN.md
rating: 1792
source: Biweekly Contest 12 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Topological Sort
---

# [1245. Tree Diameter 🔒](https://leetcode.com/problems/tree-diameter)

[中文文档](/solution/1200-1299/1245.Tree%20Diameter/README.md)

## Description

<p>The <strong>diameter</strong> of a tree is <strong>the number of edges</strong> in the longest path in that tree.</p>

<p>There is an undirected tree of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D array <code>edges</code> where <code>edges.length == n - 1</code> and <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return <em>the <strong>diameter</strong> of the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree1.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest path of the tree is the path 1 - 0 - 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree2.jpg" style="width: 224px; height: 225px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length + 1</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## Solutions

### Solution 1: Two DFS

First, perform DFS on any node to find the furthest node, then perform DFS again from this node to reach another furthest node. The node reached by the first DFS can be proven to be one end of the diameter of this graph, and the second DFS will reach the other end. Let's prove this theorem.

Theorem: In a connected undirected acyclic graph, the furthest node that can be reached from any node must be one end of the diameter of the graph.

Proof: Suppose this diameter is $\delta(s, t)$. There are two cases:

1. When the starting node $y$ is on $\delta(s, t)$, suppose the furthest node $z$ reached is not any of $s, t$. At this time, connect $\delta(y, z)$ with $\delta(y, s)$ that does not overlap with it (you can also assume that the other direction of the diameter does not overlap), you can get a longer diameter, which contradicts the premise.
1. When the starting node $y$ is not on $\delta(s, t)$, there are two cases:

    - When the furthest node $z$ reached by $y$ crosses $\delta(s, t)$, mark the intersecting node as $x$. At this time, $\delta(y, z) = \delta(y, x) + \delta(x, z)$. And at this time $\delta(y, z) > \delta(y, t)$, so $\delta(x, z) > \delta(x, t)$. According to the conclusion of 1, this assumption is not established.
    - When the furthest node $z$ reached by $y$ does not intersect with $\delta(s, t)$, define the first node on the simple path from $y$ to $t$ that also exists on the simple path $\delta(s, t)$ as $x$, and the last node on the simple path $\delta(y, z)$ as $x'$. As shown in the figure below. Then according to the assumption, $\delta(y, z) \geq \delta(y, t) \Rightarrow \delta(x', z) \geq \delta(x', x) + \delta(x, t)$. In this case, $\delta(x, z) \geq \delta(x, t)$, which does not match the premise that $\delta(s, t)$ corresponds to the diameter, so the furthest node $z$ of $y$ cannot be outside the path corresponding to the diameter from $s$ to $t$.

    <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree-diameter.svg">

Therefore, the theorem holds.

Similar problems:

-   [1522. Diameter of N-Ary Tree](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README.md)

<!-- tabs:start -->

```python
class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        def dfs(u, t):
            nonlocal ans, vis, d, next
            if vis[u]:
                return
            vis[u] = True
            for v in d[u]:
                dfs(v, t + 1)
            if ans < t:
                ans = t
                next = u

        d = defaultdict(set)
        vis = [False] * (len(edges) + 1)
        for u, v in edges:
            d[u].add(v)
            d[v].add(u)
        ans = 0
        next = 0
        dfs(edges[0][0], 0)
        vis = [False] * (len(edges) + 1)
        dfs(next, 0)
        return ans
```

```java
class Solution {
    private Map<Integer, Set<Integer>> g;
    private boolean[] vis;
    private int next;
    private int ans;

    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        ans = 0;
        g = new HashMap<>();
        for (int[] e : edges) {
            g.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            g.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        vis = new boolean[n + 1];
        next = edges[0][0];
        dfs(next, 0);
        vis = new boolean[n + 1];
        dfs(next, 0);
        return ans;
    }

    private void dfs(int u, int t) {
        if (vis[u]) {
            return;
        }
        vis[u] = true;
        if (ans < t) {
            ans = t;
            next = u;
        }
        for (int v : g.get(u)) {
            dfs(v, t + 1);
        }
    }
}
```

```cpp
class Solution {
public:
    unordered_map<int, unordered_set<int>> g;
    vector<bool> vis;
    int ans;
    int next;

    int treeDiameter(vector<vector<int>>& edges) {
        for (auto& e : edges) {
            g[e[0]].insert(e[1]);
            g[e[1]].insert(e[0]);
        }
        int n = edges.size();
        ans = 0;
        vis.resize(n + 1);
        next = edges[0][0];
        dfs(next, 0);
        vis.assign(vis.size(), false);
        dfs(next, 0);
        return ans;
    }

    void dfs(int u, int t) {
        if (vis[u]) return;
        vis[u] = true;
        if (ans < t) {
            ans = t;
            next = u;
        }
        for (int v : g[u]) dfs(v, t + 1);
    }
};
```

```go
func treeDiameter(edges [][]int) int {
	n := len(edges)
	g := make(map[int][]int)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
		g[e[1]] = append(g[e[1]], e[0])
	}
	vis := make(map[int]bool, n+1)
	ans := 0
	next := edges[0][0]
	var dfs func(u, t int)
	dfs = func(u, t int) {
		if vis[u] {
			return
		}
		vis[u] = true
		if ans < t {
			ans = t
			next = u
		}
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v, t+1)
			}
		}
	}
	dfs(next, 0)
	vis = make(map[int]bool, n+1)
	dfs(next, 0)
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
