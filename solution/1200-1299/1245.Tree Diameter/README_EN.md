# [1245. Tree Diameter](https://leetcode.com/problems/tree-diameter)

[中文文档](/solution/1200-1299/1245.Tree%20Diameter/README.md)

## Description

<p>Given an undirected tree, return&nbsp;its diameter: the number of <strong>edges</strong> in a longest path in that tree.</p>

<p>The tree is given as an array&nbsp;of&nbsp;<code>edges</code>&nbsp;where <code>edges[i] = [u, v]</code> is a bidirectional edge between nodes&nbsp;<code>u</code> and <code>v</code>.&nbsp; Each node has&nbsp;labels in the set <code>{0, 1, ..., edges.length}</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/1397_example_1.png" style="width: 226px; height: 233px;" /></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>
A longest path of the tree is the path 1 - 0 - 2.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/1397_example_2.png" style="width: 350px; height: 316px;" /></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= edges.length &lt;&nbsp;10^4</code></li>
	<li><code>edges[i][0] != edges[i][1]</code></li>
	<li><code>0 &lt;= edges[i][j] &lt;= edges.length</code></li>
	<li>The given edges form an undirected tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, unordered_set<int>> g;
    vector<bool> vis;
    int ans;
    int next;

    int treeDiameter(vector<vector<int>>& edges) {
        for (auto& e : edges)
        {
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
        if (ans < t)
        {
            ans = t;
            next = u;
        }
        for (int v : g[u]) dfs(v, t + 1);
    }
};
```

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
