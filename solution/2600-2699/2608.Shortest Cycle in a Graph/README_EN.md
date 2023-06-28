# [2608. Shortest Cycle in a Graph](https://leetcode.com/problems/shortest-cycle-in-a-graph)

[中文文档](/solution/2600-2699/2608.Shortest%20Cycle%20in%20a%20Graph/README.md)

## Description

<p>There is a <strong>bi-directional </strong>graph with <code>n</code> vertices, where each vertex is labeled from <code>0</code> to <code>n - 1</code>. The edges in the graph are represented by a given 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> denotes an edge between vertex <code>u<sub>i</sub></code> and vertex <code>v<sub>i</sub></code>. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.</p>

<p>Return <em>the length of the <strong>shortest </strong>cycle in the graph</em>. If no cycle exists, return <code>-1</code>.</p>

<p>A cycle is a path that starts and ends at the same node, and each edge in the path is used only once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2608.Shortest%20Cycle%20in%20a%20Graph/images/cropped.png" style="width: 387px; height: 331px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The cycle with the smallest length is : 0 -&gt; 1 -&gt; 2 -&gt; 0 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2608.Shortest%20Cycle%20in%20a%20Graph/images/croppedagin.png" style="width: 307px; height: 307px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[0,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are no cycles in this graph.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= edges.length &lt;= 1000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
</ul>

## Solutions

**Solution 1: Enumerate edges + BFS**

We first construct the adjacency list $g$ of the graph according to the array $edges$, where $g[u]$ represents all the adjacent vertices of vertex $u$.

Then we enumerate the two-directional edge $(u, v)$, if the path from vertex $u$ to vertex $v$ still exists after deleting this edge, then the length of the shortest cycle containing this edge is $dist[v] + 1$, where $dist[v]$ represents the shortest path length from vertex $u$ to vertex $v$. We take the minimum of all these cycles.

The time complexity is $O(m^2)$ and the space complexity is $O(m + n)$, where $m$ and $n$ are the length of the array $edges$ and the number of vertices.

**Solution 2: Enumerate points + BFS**

Similar to Solution 1, we first construct the adjacency list $g$ of the graph according to the array $edges$, where $g[u]$ represents all the adjacent vertices of vertex $u$.

Then we enumerate the vertex $u$, if there are two paths from vertex $u$ to vertex $v$, then we currently find a cycle, the length is the sum of the length of the two paths. We take the minimum of all these cycles.

The time complexity is $O(m \times n)$ and the space complexity is $O(m + n)$, where $m$ and $n$ are the length of the array $edges$ and the number of vertices.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findShortestCycle(self, n: int, edges: List[List[int]]) -> int:
        def bfs(u: int, v: int) -> int:
            dist = [inf] * n
            dist[u] = 0
            q = deque([u])
            while q:
                i = q.popleft()
                for j in g[i]:
                    if (i, j) != (u, v) and (j, i) != (u, v) and dist[j] == inf:
                        dist[j] = dist[i] + 1
                        q.append(j)
            return dist[v] + 1

        g = defaultdict(set)
        for u, v in edges:
            g[u].add(v)
            g[v].add(u)
        ans = min(bfs(u, v) for u, v in edges)
        return ans if ans < inf else -1
```

```python
class Solution:
    def findShortestCycle(self, n: int, edges: List[List[int]]) -> int:
        def bfs(u: int) -> int:
            dist = [-1] * n
            dist[u] = 0
            q = deque([(u, -1)])
            ans = inf
            while q:
                u, fa = q.popleft()
                for v in g[u]:
                    if dist[v] < 0:
                        dist[v] = dist[u] + 1
                        q.append((v, u))
                    elif v != fa:
                        ans = min(ans, dist[u] + dist[v] + 1)
            return ans

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        ans = min(bfs(i) for i in range(n))
        return ans if ans < inf else -1
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private final int inf = 1 << 30;

    public int findShortestCycle(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = inf;
        for (var e : edges) {
            int u = e[0], v = e[1];
            ans = Math.min(ans, bfs(u, v));
        }
        return ans < inf ? ans : -1;
    }

    private int bfs(int u, int v) {
        int[] dist = new int[g.length];
        Arrays.fill(dist, inf);
        dist[u] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(u);
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                if ((i == u && j == v) || (i == v && j == u) || dist[j] != inf) {
                    continue;
                }
                dist[j] = dist[i] + 1;
                q.offer(j);
            }
        }
        return dist[v] + 1;
    }
}
```

```java
class Solution {
    private List<Integer>[] g;
    private final int inf = 1 << 30;

    public int findShortestCycle(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, bfs(i));
        }
        return ans < inf ? ans : -1;
    }

    private int bfs(int u) {
        int[] dist = new int[g.length];
        Arrays.fill(dist, -1);
        dist[u] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {u, -1});
        int ans = inf;
        while (!q.isEmpty()) {
            var p = q.poll();
            u = p[0];
            int fa = p[1];
            for (int v : g[u]) {
                if (dist[v] < 0) {
                    dist[v] = dist[u] + 1;
                    q.offer(new int[] {v, u});
                } else if (v != fa) {
                    ans = Math.min(ans, dist[u] + dist[v] + 1);
                }
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
    int findShortestCycle(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        const int inf = 1 << 30;
        auto bfs = [&](int u, int v) -> int {
            int dist[n];
            fill(dist, dist + n, inf);
            dist[u] = 0;
            queue<int> q{{u}};
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if ((i == u && j == v) || (i == v && j == u) || dist[j] != inf) {
                        continue;
                    }
                    dist[j] = dist[i] + 1;
                    q.push(j);
                }
            }
            return dist[v] + 1;
        };
        int ans = inf;
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            ans = min(ans, bfs(u, v));
        }
        return ans < inf ? ans : -1;
    }
};
```

```cpp
class Solution {
public:
    int findShortestCycle(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        const int inf = 1 << 30;
        auto bfs = [&](int u) -> int {
            int dist[n];
            memset(dist, -1, sizeof(dist));
            dist[u] = 0;
            queue<pair<int, int>> q;
            q.emplace(u, -1);
            int ans = inf;
            while (!q.empty()) {
                auto p = q.front();
                u = p.first;
                int fa = p.second;
                q.pop();
                for (int v : g[u]) {
                    if (dist[v] < 0) {
                        dist[v] = dist[u] + 1;
                        q.emplace(v, u);
                    } else if (v != fa) {
                        ans = min(ans, dist[u] + dist[v] + 1);
                    }
                }
            }
            return ans;
        };
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            ans = min(ans, bfs(i));
        }
        return ans < inf ? ans : -1;
    }
};
```

### **Go**

```go
func findShortestCycle(n int, edges [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	const inf = 1 << 30
	bfs := func(u, v int) int {
		dist := make([]int, n)
		for i := range dist {
			dist[i] = inf
		}
		dist[u] = 0
		q := []int{u}
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if (i == u && j == v) || (i == v && j == u) || dist[j] != inf {
					continue
				}
				dist[j] = dist[i] + 1
				q = append(q, j)
			}
		}
		return dist[v] + 1
	}
	ans := inf
	for _, e := range edges {
		u, v := e[0], e[1]
		ans = min(ans, bfs(u, v))
	}
	if ans < inf {
		return ans
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func findShortestCycle(n int, edges [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	const inf = 1 << 30
	bfs := func(u int) int {
		dist := make([]int, n)
		for i := range dist {
			dist[i] = -1
		}
		dist[u] = 0
		q := [][2]int{{u, -1}}
		ans := inf
		for len(q) > 0 {
			p := q[0]
			u = p[0]
			fa := p[1]
			q = q[1:]
			for _, v := range g[u] {
				if dist[v] < 0 {
					dist[v] = dist[u] + 1
					q = append(q, [2]int{v, u})
				} else if v != fa {
					ans = min(ans, dist[u]+dist[v]+1)
				}
			}
		}
		return ans
	}
	ans := inf
	for i := 0; i < n; i++ {
		ans = min(ans, bfs(i))
	}
	if ans < inf {
		return ans
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function findShortestCycle(n: number, edges: number[][]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const inf = 1 << 30;
    let ans = inf;
    const bfs = (u: number, v: number) => {
        const dist: number[] = new Array(n).fill(inf);
        dist[u] = 0;
        const q: number[] = [u];
        while (q.length) {
            const i = q.shift()!;
            for (const j of g[i]) {
                if (
                    (i == u && j == v) ||
                    (i == v && j == u) ||
                    dist[j] != inf
                ) {
                    continue;
                }
                dist[j] = dist[i] + 1;
                q.push(j);
            }
        }
        return 1 + dist[v];
    };
    for (const [u, v] of edges) {
        ans = Math.min(ans, bfs(u, v));
    }
    return ans < inf ? ans : -1;
}
```

```ts
function findShortestCycle(n: number, edges: number[][]): number {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const inf = 1 << 30;
    let ans = inf;
    const bfs = (u: number) => {
        const dist: number[] = new Array(n).fill(-1);
        dist[u] = 0;
        const q: number[][] = [[u, -1]];
        let ans = inf;
        while (q.length) {
            const p = q.shift()!;
            u = p[0];
            const fa = p[1];
            for (const v of g[u]) {
                if (dist[v] < 0) {
                    dist[v] = dist[u] + 1;
                    q.push([v, u]);
                } else if (v !== fa) {
                    ans = Math.min(ans, dist[u] + dist[v] + 1);
                }
            }
        }
        return ans;
    };
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, bfs(i));
    }
    return ans < inf ? ans : -1;
}
```

### **...**

```

```

<!-- tabs:end -->
