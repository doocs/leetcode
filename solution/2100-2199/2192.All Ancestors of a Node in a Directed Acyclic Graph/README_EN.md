# [2192. All Ancestors of a Node in a Directed Acyclic Graph](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph)

[中文文档](/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/README.md)

## Description

<p>You are given a positive integer <code>n</code> representing the number of nodes of a <strong>Directed Acyclic Graph</strong> (DAG). The nodes are numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>).</p>

<p>You are also given a 2D integer array <code>edges</code>, where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> denotes that there is a <strong>unidirectional</strong> edge from <code>from<sub>i</sub></code> to <code>to<sub>i</sub></code> in the graph.</p>

<p>Return <em>a list</em> <code>answer</code><em>, where </em><code>answer[i]</code><em> is the <strong>list of ancestors</strong> of the</em> <code>i<sup>th</sup></code> <em>node, sorted in <strong>ascending order</strong></em>.</p>

<p>A node <code>u</code> is an <strong>ancestor</strong> of another node <code>v</code> if <code>u</code> can reach <code>v</code> via a set of edges.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/images/e1.png" style="width: 322px; height: 265px;" />
<pre>
<strong>Input:</strong> n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
<strong>Output:</strong> [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
<strong>Explanation:</strong>
The above diagram represents the input graph.
- Nodes 0, 1, and 2 do not have any ancestors.
- Node 3 has two ancestors 0 and 1.
- Node 4 has two ancestors 0 and 2.
- Node 5 has three ancestors 0, 1, and 3.
- Node 6 has five ancestors 0, 1, 2, 3, and 4.
- Node 7 has four ancestors 0, 1, 2, and 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/images/e2.png" style="width: 343px; height: 299px;" />
<pre>
<strong>Input:</strong> n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
<strong>Output:</strong> [[],[0],[0,1],[0,1,2],[0,1,2,3]]
<strong>Explanation:</strong>
The above diagram represents the input graph.
- Node 0 does not have any ancestor.
- Node 1 has one ancestor 0.
- Node 2 has two ancestors 0 and 1.
- Node 3 has three ancestors 0, 1, and 2.
- Node 4 has four ancestors 0, 1, 2, and 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= edges.length &lt;= min(2000, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li>There are no duplicate edges.</li>
	<li>The graph is <strong>directed</strong> and <strong>acyclic</strong>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getAncestors(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        g = defaultdict(list)
        for u, v in edges:
            g[v].append(u)
        ans = []
        for i in range(n):
            if not g[i]:
                ans.append([])
                continue
            q = deque([i])
            vis = [False] * n
            vis[i] = True
            t = []
            while q:
                for _ in range(len(q)):
                    v = q.popleft()
                    for u in g[v]:
                        if not vis[u]:
                            vis[u] = True
                            q.append(u)
                            t.append(u)
            ans.append(sorted(t))
        return ans
```

### **Java**

```java
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[1]].add(e[0]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> t = new ArrayList<>();
            if (g[i].isEmpty()) {
                ans.add(t);
                continue;
            }
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            boolean[] vis = new boolean[n];
            vis[i] = true;
            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.poll();
                    for (int u : g[v]) {
                        if (!vis[u]) {
                            vis[u] = true;
                            q.offer(u);
                            t.add(u);
                        }
                    }
                }
            }
            Collections.sort(t);
            ans.add(t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) g[e[1]].push_back(e[0]);
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            vector<int> t;
            if (g[i].empty()) {
                ans.push_back(t);
                continue;
            }
            queue<int> q {{i}};
            vector<bool> vis(n);
            vis[i] = true;
            while (!q.empty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.front();
                    q.pop();
                    for (int u : g[v]) {
                        if (vis[u]) continue;
                        vis[u] = true;
                        q.push(u);
                        t.push_back(u);
                    }
                }
            }
            sort(t.begin(), t.end());
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func getAncestors(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[1]] = append(g[e[1]], e[0])
	}
	var ans [][]int
	for i := 0; i < n; i++ {
		var t []int
		if len(g[i]) == 0 {
			ans = append(ans, t)
			continue
		}
		q := []int{i}
		vis := make([]bool, n)
		vis[i] = true
		for len(q) > 0 {
			for j := len(q); j > 0; j-- {
				v := q[0]
				q = q[1:]
				for _, u := range g[v] {
					if !vis[u] {
						vis[u] = true
						q = append(q, u)
						t = append(t, u)
					}
				}
			}
		}
		sort.Ints(t)
		ans = append(ans, t)
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
