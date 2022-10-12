# [1466. Reorder Routes to Make All Paths Lead to the City Zero](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero)

[中文文档](/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/README.md)

## Description

<p>There are <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code> and <code>n - 1</code> roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.</p>

<p>Roads are represented by <code>connections</code> where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a road from city <code>a<sub>i</sub></code> to city <code>b<sub>i</sub></code>.</p>

<p>This year, there will be a big event in the capital (city <code>0</code>), and many people want to travel to this city.</p>

<p>Your task consists of reorienting some roads such that each city can visit the city <code>0</code>. Return the <strong>minimum</strong> number of edges changed.</p>

<p>It&#39;s <strong>guaranteed</strong> that each city can reach city <code>0</code> after reorder.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_1_1819.png" style="width: 311px; height: 189px;" />
<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_2_1819.png" style="width: 509px; height: 79px;" />
<pre>
<strong>Input:</strong> n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, connections = [[1,0],[2,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>connections.length == n - 1</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
</ul>

## Solutions

DFS.

Treat the graph as undirected. Start a dfs from the root, if you come across an edge in the forward direction, you need to reverse the edge.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minReorder(self, n: int, connections: List[List[int]]) -> int:
        def dfs(u):
            vis[u] = True
            ans = 0
            for v in g[u]:
                if not vis[v]:
                    if (u, v) in s:
                        ans += 1
                    ans += dfs(v)
            return ans

        g = defaultdict(list)
        s = set()
        for a, b in connections:
            g[a].append(b)
            g[b].append(a)
            s.add((a, b))
        vis = [False] * n
        return dfs(0)
```

### **Java**

```java
class Solution {
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Pair<Integer, Boolean>>> g = new HashMap<>();
        for (int[] e : connections) {
            int u = e[0], v = e[1];
            g.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, true));
            g.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, false));
        }
        boolean[] vis = new boolean[n];
        return dfs(0, g, vis);
    }

    private int dfs(int u, Map<Integer, List<Pair<Integer, Boolean>>> g, boolean[] vis) {
        vis[u] = true;
        int ans = 0;
        for (Pair<Integer, Boolean> e : g.getOrDefault(u, Collections.emptyList())) {
            int v = e.getKey();
            boolean exist = e.getValue();
            if (!vis[v]) {
                if (exist) {
                    ++ans;
                }
                ans += dfs(v, g, vis);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        unordered_map<int, vector<pair<int, bool>>> g;
        for (auto& e : connections) {
            int u = e[0], v = e[1];
            g[u].push_back({v, true});
            g[v].push_back({u, false});
        }
        vector<bool> vis(n);
        return dfs(0, g, vis);
    }

    int dfs(int u, unordered_map<int, vector<pair<int, bool>>>& g, vector<bool>& vis) {
        vis[u] = true;
        int ans = 0;
        for (auto& p : g[u]) {
            int v = p.first;
            bool exist = p.second;
            if (!vis[v]) {
                if (exist) ++ans;
                ans += dfs(v, g, vis);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minReorder(n int, connections [][]int) int {
	type pib struct {
		v int
		b bool
	}
	g := map[int][]pib{}
	for _, e := range connections {
		u, v := e[0], e[1]
		g[u] = append(g[u], pib{v, true})
		g[v] = append(g[v], pib{u, false})
	}
	vis := make([]bool, n)
	var dfs func(int) int
	dfs = func(u int) int {
		ans := 0
		vis[u] = true
		for _, p := range g[u] {
			v, exist := p.v, p.b
			if !vis[v] {
				if exist {
					ans++
				}
				ans += dfs(v)
			}
		}
		return ans
	}
	return dfs(0)
}
```

### **...**

```

```

<!-- tabs:end -->
