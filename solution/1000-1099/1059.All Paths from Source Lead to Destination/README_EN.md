# [1059. All Paths from Source Lead to Destination](https://leetcode.com/problems/all-paths-from-source-lead-to-destination)

[中文文档](/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/README.md)

## Description

<p>Given the <code>edges</code> of a directed graph where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>, and two nodes <code>source</code> and <code>destination</code> of this graph, determine whether or not all paths starting from <code>source</code> eventually, end at <code>destination</code>, that is:</p>

<ul>
	<li>At least one path exists from the <code>source</code> node to the <code>destination</code> node</li>
	<li>If a path exists from the <code>source</code> node to a node with no outgoing edges, then that node is equal to <code>destination</code>.</li>
	<li>The number of possible paths from <code>source</code> to <code>destination</code> is a finite number.</li>
</ul>

<p>Return <code>true</code> if and only if all roads from <code>source</code> lead to <code>destination</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_1.png" style="width: 200px; height: 208px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> It is possible to reach and get stuck on both node 1 and node 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_2.png" style="width: 200px; height: 230px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_3.png" style="width: 200px; height: 183px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges.length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= source &lt;= n - 1</code></li>
	<li><code>0 &lt;= destination &lt;= n - 1</code></li>
	<li>The given graph may have self-loops and parallel edges.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def leadsToDestination(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        @cache
        def dfs(i):
            if i == destination:
                return not g[i]
            if i in vis or not g[i]:
                return False
            vis.add(i)
            for j in g[i]:
                if not dfs(j):
                    return False
            return True

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
        vis = set()
        return dfs(source)
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] f;
    private boolean[] vis;
    private int k;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        k = destination;
        f = new int[n];
        Arrays.setAll(g, key -> new ArrayList<>());
        for (var e : edges) {
            g[e[0]].add(e[1]);
        }
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (i == k) {
            return g[i].isEmpty();
        }
        if (f[i] != 0) {
            return f[i] == 1;
        }
        if (vis[i] || g[i].isEmpty()) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (!dfs(j)) {
                f[i] = -1;
                return false;
            }
        }
        f[i] = 1;
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool leadsToDestination(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        vector<int> f(n);
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        function<bool(int)> dfs = [&](int i) {
            if (i == destination) {
                return g[i].empty();
            }
            if (f[i]) {
                return f[i] == 1;
            }
            if (vis[i] || g[i].empty()) {
                return false;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (!dfs(j)) {
                    f[i] = -1;
                    return false;
                }
            }
            f[i] = 1;
            return true;
        };
        return dfs(source);
    }
};
```

### **Go**

```go
func leadsToDestination(n int, edges [][]int, source int, destination int) bool {
	vis := make([]bool, n)
	g := make([][]int, n)
	f := make([]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == destination {
			return len(g[i]) == 0
		}
		if f[i] != 0 {
			return f[i] == 1
		}
		if vis[i] || len(g[i]) == 0 {
			return false
		}
		vis[i] = true
		for _, j := range g[i] {
			if !dfs(j) {
				f[i] = -1
				return false
			}
		}
		f[i] = 1
		return true
	}
	return dfs(source)
}
```

### **...**

```

```

<!-- tabs:end -->
