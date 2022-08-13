# [1245. Tree Diameter](https://leetcode.com/problems/tree-diameter)

[中文文档](/solution/1200-1299/1245.Tree%20Diameter/README.md)

## Description

<p>The <strong>diameter</strong> of a tree is <strong>the number of edges</strong> in the longest path in that tree.</p>

<p>There is an undirected tree of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given a 2D array <code>edges</code> where <code>edges.length == n - 1</code> and <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return <em>the <strong>diameter</strong> of the tree</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/tree1.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest path of the tree is the path 1 - 0 - 2.
</pre>

<p><strong>Example 2:</strong></p>
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
