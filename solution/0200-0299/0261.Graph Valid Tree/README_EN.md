---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0261.Graph%20Valid%20Tree/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [261. Graph Valid Tree 🔒](https://leetcode.com/problems/graph-valid-tree)

[中文文档](/solution/0200-0299/0261.Graph%20Valid%20Tree/README.md)

## Description

<p>You have a graph of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given an integer n and a list of <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the graph.</p>

<p>Return <code>true</code> <em>if the edges of the given graph make up a valid tree, and</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree1-graph.jpg" style="width: 222px; height: 302px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree2-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no self-loops or repeated edges.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

To determine whether it is a tree, the following two conditions must be met:

1. The number of edges is equal to the number of nodes minus one;
2. There is no cycle.

We can use a union-find set to determine whether there is a cycle. We traverse the edges, if two nodes are already in the same set, it means there is a cycle. Otherwise, we merge the two nodes into the same set. Then we decrease the number of connected components by one, and finally check whether the number of connected components is $1$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for a, b in edges:
            pa, pb = find(a), find(b)
            if pa == pb:
                return False
            p[pa] = pb
            n -= 1
        return n == 1
```

```java
class Solution {
public:
    bool validTree(int n, vector<vector<int>>& edges) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (auto& e : edges) {
            int pa = find(e[0]), pb = find(e[1]);
            if (pa == pb) {
                return false;
            }
            p[pa] = pb;
            --n;
        }
        return n == 1;
    }
};
```

```cpp
class Solution {
public:
    vector<int> p;

    bool validTree(int n, vector<vector<int>>& edges) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) return 0;
            p[find(a)] = find(b);
            --n;
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

```go
func validTree(n int, edges [][]int) bool {
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
	for _, e := range edges {
		pa, pb := find(e[0]), find(e[1])
		if pa == pb {
			return false
		}
		p[pa] = pb
		n--
	}
	return n == 1
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of edges) {
        const pa = find(a);
        const pb = find(b);
        if (pa === pb) {
            return false;
        }
        p[pa] = pb;
        --n;
    }
    return n === 1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: DFS

We can also use depth-first search to determine whether there is a cycle. We can use an array $vis$ to record the visited nodes. During the search, we first mark the node as visited, then traverse the nodes adjacent to this node. If the adjacent node has been visited, we skip it, otherwise we recursively visit the adjacent node. Finally, we check whether all nodes have been visited. If there are nodes that have not been visited, it means that it cannot form a tree, so we return `false`.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        def dfs(i: int):
            vis.add(i)
            for j in g[i]:
                if j not in vis:
                    dfs(j)

        if len(edges) != n - 1:
            return False
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        dfs(0)
        return len(vis) == n
```

```java
class Solution {
    private List<Integer>[] g;
    private Set<Integer> vis = new HashSet<>();

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0);
        return vis.size() == n;
    }

    private void dfs(int i) {
        vis.add(i);
        for (int j : g[i]) {
            if (!vis.contains(j)) {
                dfs(j);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    bool validTree(int n, vector<vector<int>>& edges) {
        if (edges.size() != n - 1) {
            return false;
        }
        vector<int> g[n];
        vector<int> vis(n);
        function<void(int)> dfs = [&](int i) {
            vis[i] = true;
            --n;
            for (int j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        dfs(0);
        return n == 0;
    }
};
```

```go
func validTree(n int, edges [][]int) bool {
	if len(edges) != n-1 {
		return false
	}
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		n--
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}
	dfs(0)
	return n == 0
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    if (edges.length !== n - 1) {
        return false;
    }
    const g = Array.from({ length: n }, () => []);
    const vis = Array.from({ length: n }, () => false);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = i => {
        vis[i] = true;
        --n;
        for (const j of g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    };
    dfs(0);
    return n === 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
