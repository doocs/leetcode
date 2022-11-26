# [882. Reachable Nodes In Subdivided Graph](https://leetcode.com/problems/reachable-nodes-in-subdivided-graph)

[中文文档](/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/README.md)

## Description

<p>You are given an undirected graph (the <strong>&quot;original graph&quot;</strong>) with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You decide to <strong>subdivide</strong> each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.</p>

<p>The graph is given as a 2D array of <code>edges</code> where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cnt<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the original graph, and <code>cnt<sub>i</sub></code> is the total number of new nodes that you will <strong>subdivide</strong> the edge into. Note that <code>cnt<sub>i</sub> == 0</code> means you will not subdivide the edge.</p>

<p>To <strong>subdivide</strong> the edge <code>[u<sub>i</sub>, v<sub>i</sub>]</code>, replace it with <code>(cnt<sub>i</sub> + 1)</code> new edges and <code>cnt<sub>i</sub></code> new nodes. The new nodes are <code>x<sub>1</sub></code>, <code>x<sub>2</sub></code>, ..., <code>x<sub>cnt<sub>i</sub></sub></code>, and the new edges are <code>[u<sub>i</sub>, x<sub>1</sub>]</code>, <code>[x<sub>1</sub>, x<sub>2</sub>]</code>, <code>[x<sub>2</sub>, x<sub>3</sub>]</code>, ..., <code>[x<sub>cnt<sub>i</sub>-1</sub>, x<sub>cnt<sub>i</sub></sub>]</code>, <code>[x<sub>cnt<sub>i</sub></sub>, v<sub>i</sub>]</code>.</p>

<p>In this <strong>new graph</strong>, you want to know how many nodes are <strong>reachable</strong> from the node <code>0</code>, where a node is <strong>reachable</strong> if the distance is <code>maxMoves</code> or less.</p>

<p>Given the original graph and <code>maxMoves</code>, return <em>the number of nodes that are <strong>reachable</strong> from node </em><code>0</code><em> in the new graph</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/images/origfinal.png" style="width: 600px; height: 247px;" />
<pre>
<strong>Input:</strong> edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
<strong>Output:</strong> 13
<strong>Explanation:</strong> The edge subdivisions are shown in the image above.
The nodes that are reachable are highlighted in yellow.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
<strong>Output:</strong> 23
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= edges.length &lt;= min(n * (n - 1) / 2, 10<sup>4</sup>)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt; n</code></li>
	<li>There are <strong>no multiple edges</strong> in the graph.</li>
	<li><code>0 &lt;= cnt<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= maxMoves &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n &lt;= 3000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reachableNodes(self, edges: List[List[int]], maxMoves: int, n: int) -> int:
        g = defaultdict(list)
        for u, v, cnt in edges:
            g[u].append((v, cnt + 1))
            g[v].append((u, cnt + 1))
        q = [(0, 0)]
        dist = [0] + [inf] * n
        while q:
            d, u = heappop(q)
            for v, cnt in g[u]:
                if (t := d + cnt) < dist[v]:
                    dist[v] = t
                    q.append((t, v))
        ans = sum(d <= maxMoves for d in dist)
        for u, v, cnt in edges:
            a = min(cnt, max(0, maxMoves - dist[u]))
            b = min(cnt, max(0, maxMoves - dist[v]))
            ans += min(cnt, a + b)
        return ans
```

### **Java**

```java
class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2] + 1;
            g[u].add(new int[] {v, cnt});
            g[v].add(new int[] {u, cnt});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, 0});
        dist[0] = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[0], u = p[1];
            for (var nxt : g[u]) {
                int v = nxt[0], cnt = nxt[1];
                if (d + cnt < dist[v]) {
                    dist[v] = d + cnt;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        int ans = 0;
        for (int d : dist) {
            if (d <= maxMoves) {
                ++ans;
            }
        }
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int a = Math.min(cnt, Math.max(0, maxMoves - dist[u]));
            int b = Math.min(cnt, Math.max(0, maxMoves - dist[v]));
            ans += Math.min(cnt, a + b);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reachableNodes(vector<vector<int>>& edges, int maxMoves, int n) {
        using pii = pair<int, int>;
        vector<vector<pii>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], cnt = e[2] + 1;
            g[u].emplace_back(v, cnt);
            g[v].emplace_back(u, cnt);
        }
        priority_queue<pii, vector<pii>, greater<pii>> q;
        q.emplace(0, 0);
        int dist[n];
        memset(dist, 0x3f, sizeof dist);
        dist[0] = 0;
        while (!q.empty()) {
            auto [d, u] = q.top();
            q.pop();
            for (auto& [v, cnt] : g[u]) {
                if (d + cnt < dist[v]) {
                    dist[v] = d + cnt;
                    q.emplace(dist[v], v);
                }
            }
        }
        int ans = 0;
        for (int& d : dist) ans += d <= maxMoves;
        for (auto& e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int a = min(cnt, max(0, maxMoves - dist[u]));
            int b = min(cnt, max(0, maxMoves - dist[v]));
            ans += min(cnt, a + b);
        }
        return ans;
    }
};
```

### **Go**

```go
func reachableNodes(edges [][]int, maxMoves int, n int) (ans int) {
	g := make([][]pair, n)
	for _, e := range edges {
		u, v, cnt := e[0], e[1], e[2]+1
		g[u] = append(g[u], pair{cnt, v})
		g[v] = append(g[v], pair{cnt, u})
	}
	dist := make([]int, n)
	for i := range dist {
		dist[i] = 1 << 30
	}
	dist[0] = 0
	q := hp{{0, 0}}
	for len(q) > 0 {
		p := heap.Pop(&q).(pair)
		d, u := p.v, p.i
		for _, nxt := range g[u] {
			v, cnt := nxt.i, nxt.v
			if t := d + cnt; t < dist[v] {
				dist[v] = t
				heap.Push(&q, pair{t, v})
			}
		}
	}
	for _, d := range dist {
		if d <= maxMoves {
			ans++
		}
	}
	for _, e := range edges {
		u, v, cnt := e[0], e[1], e[2]
		a := min(cnt, max(0, maxMoves-dist[u]))
		b := min(cnt, max(0, maxMoves-dist[v]))
		ans += min(cnt, a+b)
	}
	return
}

type pair struct{ v, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
