# [1443. Minimum Time to Collect All Apples in a Tree](https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree)

[中文文档](/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/README.md)

## Description

<p>Given an undirected tree consisting of <code>n</code> vertices numbered from <code>0</code> to <code>n-1</code>, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. <em>Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at <strong>vertex 0</strong> and coming back to this vertex.</em></p>

<p>The edges of the undirected tree are given in the array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> means that exists an edge connecting the vertices <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Additionally, there is a boolean array <code>hasApple</code>, where <code>hasApple[i] = true</code> means that vertex <code>i</code> has an apple; otherwise, it does not have any apple.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_1.png" style="width: 300px; height: 212px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
<strong>Output:</strong> 8 
<strong>Explanation:</strong> The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_2.png" style="width: 300px; height: 212px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>hasApple.length == n</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        def dfs(u, cost):
            if vis[u]:
                return 0
            vis[u] = True
            nxt_cost = 0
            for v in g[u]:
                nxt_cost += dfs(v, 2)
            if not hasApple[u] and nxt_cost == 0:
                return 0
            return cost + nxt_cost

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        vis = [False] * n
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        boolean[] vis = new boolean[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    private int dfs(int u, int cost, List<Integer>[] g, List<Boolean> hasApple, boolean[] vis) {
        if (vis[u]) {
            return 0;
        }
        vis[u] = true;
        int nxtCost = 0;
        for (int v : g[u]) {
            nxtCost += dfs(v, 2, g, hasApple, vis);
        }
        if (!hasApple.get(u) && nxtCost == 0) {
            return 0;
        }
        return cost + nxtCost;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    int dfs(int u, int cost, vector<vector<int>>& g, vector<bool>& hasApple, vector<bool>& vis) {
        if (vis[u]) return 0;
        vis[u] = true;
        int nxt = 0;
        for (int& v : g[u]) nxt += dfs(v, 2, g, hasApple, vis);
        if (!hasApple[u] && !nxt) return 0;
        return cost + nxt;
    }
};
```

### **Go**

```go
func minTime(n int, edges [][]int, hasApple []bool) int {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int, int) int
	dfs = func(u, cost int) int {
		if vis[u] {
			return 0
		}
		vis[u] = true
		nxt := 0
		for _, v := range g[u] {
			nxt += dfs(v, 2)
		}
		if !hasApple[u] && nxt == 0 {
			return 0
		}
		return cost + nxt
	}
	return dfs(0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
