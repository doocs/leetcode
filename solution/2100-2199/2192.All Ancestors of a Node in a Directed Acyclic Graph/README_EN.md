# [2192. All Ancestors of a Node in a Directed Acyclic Graph](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph)

[中文文档](/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/README.md)

## Description

<p>You are given a positive integer <code>n</code> representing the number of nodes of a <strong>Directed Acyclic Graph</strong> (DAG). The nodes are numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>).</p>

<p>You are also given a 2D integer array <code>edges</code>, where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> denotes that there is a <strong>unidirectional</strong> edge from <code>from<sub>i</sub></code> to <code>to<sub>i</sub></code> in the graph.</p>

<p>Return <em>a list</em> <code>answer</code><em>, where </em><code>answer[i]</code><em> is the <strong>list of ancestors</strong> of the</em> <code>i<sup>th</sup></code> <em>node, sorted in <strong>ascending order</strong></em>.</p>

<p>A node <code>u</code> is an <strong>ancestor</strong> of another node <code>v</code> if <code>u</code> can reach <code>v</code> via a set of edges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
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

<p><strong class="example">Example 2:</strong></p>
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
        def bfs(s: int):
            q = deque([s])
            vis = {s}
            while q:
                i = q.popleft()
                for j in g[i]:
                    if j not in vis:
                        vis.add(j)
                        q.append(j)
                        ans[j].append(s)

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
        ans = [[] for _ in range(n)]
        for i in range(n):
            bfs(i)
        return ans
```

### **Java**

```java
class Solution {
    private int n;
    private List<Integer>[] g;
    private List<List<Integer>> ans;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        g = new List[n];
        this.n = n;
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            g[e[0]].add(e[1]);
        }
        ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ans.add(new ArrayList<>());
        }
        for (int i = 0; i < n; ++i) {
            bfs(i);
        }
        return ans;
    }

    private void bfs(int s) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(s);
        boolean[] vis = new boolean[n];
        vis[s] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.offer(j);
                    ans.get(j).add(s);
                }
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        vector<vector<int>> ans(n);
        auto bfs = [&](int s) {
            queue<int> q;
            q.push(s);
            bool vis[n];
            memset(vis, 0, sizeof(vis));
            vis[s] = true;
            while (q.size()) {
                int i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        ans[j].push_back(s);
                        q.push(j);
                    }
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            bfs(i);
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
		g[e[0]] = append(g[e[0]], e[1])
	}
	ans := make([][]int, n)
	bfs := func(s int) {
		q := []int{s}
		vis := make([]bool, n)
		vis[s] = true
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
					ans[j] = append(ans[j], s)
				}
			}
		}
	}
	for i := 0; i < n; i++ {
		bfs(i)
	}
	return ans
}
```

### **TypeScript**

```ts
function getAncestors(n: number, edges: number[][]): number[][] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
    }
    const ans: number[][] = Array.from({ length: n }, () => []);
    const bfs = (s: number) => {
        const q: number[] = [s];
        const vis: boolean[] = Array.from({ length: n }, () => false);
        vis[s] = true;
        while (q.length) {
            const i = q.shift()!;
            for (const j of g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    ans[j].push(s);
                    q.push(j);
                }
            }
        }
    };
    for (let i = 0; i < n; ++i) {
        bfs(i);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
