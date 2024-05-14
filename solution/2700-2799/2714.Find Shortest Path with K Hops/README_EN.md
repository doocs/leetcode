# [2714. Find Shortest Path with K Hops 🔒](https://leetcode.com/problems/find-shortest-path-with-k-hops)

[中文文档](/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/README.md)

<!-- tags:Graph,Shortest Path,Heap (Priority Queue) -->

<!-- difficulty:Hard -->

## Description

<p>You are given a positive integer <code>n</code> which is the number of nodes of a <strong>0-indexed undirected weighted connected</strong> graph and a <strong>0-indexed</strong> <strong>2D array</strong> <code>edges</code> where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given two&nbsp;nodes <code>s</code> and <code>d</code>, and a positive integer <code>k</code>, your task is to find the <strong>shortest</strong> path from <code>s</code> to <code>d</code>, but you can hop over <strong>at most</strong> <code>k</code> edges. In other words,&nbsp;make the weight of <strong>at most</strong> <code>k</code> edges <code>0</code> and then find the <strong>shortest</strong> path from <code>s</code> to <code>d</code>.</p>

<p>Return <em>the length of the <strong>shortest</strong> path from </em><code>s</code><em> to </em><code>d</code><em> with the given condition</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,4],[0,2,2],[2,3,6]], s = 1, d = 3, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example there is only one path from node 1 (the green node) to node 3 (the red node), which is (1-&gt;0-&gt;2-&gt;3) and the length of it is 4 + 2 + 6 = 12. Now we can make weight of two edges 0, we make weight of the blue edges 0, then we have 0 + 2 + 0 = 2. It can be shown that 2 is the minimum length of a path we can achieve with the given condition.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/1.jpg" style="width: 170px; height: 171px;" /></p>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7, edges = [[3,1,9],[3,2,4],[4,0,9],[0,5,6],[3,6,2],[6,0,4],[1,2,4]], s = 4, d = 1, k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> In this example there are 2 paths from node 4 (the green node) to node 1 (the red node), which are (4-&gt;0-&gt;6-&gt;3-&gt;2-&gt;1) and (4-&gt;0-&gt;6-&gt;3-&gt;1). The first one has the length 9 + 4 + 2 + 4 + 4 = 23, and the second one has the length 9 + 4 + 2 + 9 = 24. Now if we make weight of the blue edges 0, we get the shortest path with the length 0 + 4 + 2 + 0 = 6. It can be shown that 6 is the minimum length of a path we can achieve with the given condition.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/2.jpg" style="width: 400px; height: 171px;" /></p>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,4,2],[0,1,3],[0,2,1],[2,1,4],[1,3,4],[3,4,7]], s = 2, d = 3, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example there are 4 paths from node 2 (the green node) to node 3 (the red node), which are (2-&gt;1-&gt;3), (2-&gt;0-&gt;1-&gt;3), (2-&gt;1-&gt;0-&gt;4-&gt;3) and (2-&gt;0-&gt;4-&gt;3). The first two have the length 4 + 4 = 1 + 3 + 4 = 8, the third one has the length 4 + 3 + 2 + 7 = 16 and the last one has the length 1 + 2 + 7 = 10. Now if we make weight of the blue edge 0, we get the shortest path with the length 1 + 2 + 0 = 3. It can be shown that 3 is the minimum length of a path we can achieve with the given condition.
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/3.jpg" style="width: 300px; height: 296px;" /></p>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= min(10<sup>4</sup>, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length = 3</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>1 &lt;= edges[i][2] &lt;=&nbsp;10<sup>6</sup></code></li>
	<li><code>0 &lt;= s, d, k&nbsp;&lt;= n - 1</code></li>
	<li><code>s != d</code></li>
	<li>The input is generated such that the graph is <strong>connected</strong> and has <strong>no</strong>&nbsp;<strong>repeated edges</strong>&nbsp;or&nbsp;<strong>self-loops</strong></li>
</ul>

## Solutions

### Solution 1: Dijkstra Algorithm

First, we construct a graph $g$ based on the given edges, where $g[u]$ represents all neighboring nodes of node $u$ and their corresponding edge weights.

Then, we use Dijkstra's algorithm to find the shortest path from node $s$ to node $d$. However, we need to make some modifications to Dijkstra's algorithm:

-   We need to record the shortest path length from each node $u$ to node $d$, but since we can cross at most $k$ edges, we need to record the shortest path length from each node $u$ to node $d$ and the number of edges crossed $t$, i.e., $dist[u][t]$ represents the shortest path length from node $u$ to node $d$ and the number of edges crossed is $t$.
-   We need to use a priority queue to maintain the current shortest path, but since we need to record the number of edges crossed, we need to use a triple $(dis, u, t)$ to represent the current shortest path, where $dis$ represents the current shortest path length, and $u$ and $t$ represent the current node and the number of edges crossed, respectively.

Finally, we only need to return the minimum value in $dist[d][0..k]$.

The time complexity is $O(n^2 \times \log n)$, and the space complexity is $O(n \times k)$, where $n$ represents the number of nodes and $k$ represents the maximum number of edges crossed.

<!-- tabs:start -->

```python
class Solution:
    def shortestPathWithHops(
        self, n: int, edges: List[List[int]], s: int, d: int, k: int
    ) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [[inf] * (k + 1) for _ in range(n)]
        dist[s][0] = 0
        pq = [(0, s, 0)]
        while pq:
            dis, u, t = heappop(pq)
            for v, w in g[u]:
                if t + 1 <= k and dist[v][t + 1] > dis:
                    dist[v][t + 1] = dis
                    heappush(pq, (dis, v, t + 1))
                if dist[v][t] > dis + w:
                    dist[v][t] = dis + w
                    heappush(pq, (dis + w, v, t))
        return int(min(dist[d]))
```

```java
class Solution {
    public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, s, 0});
        int[][] dist = new int[n][k + 1];
        final int inf = 1 << 30;
        for (int[] e : dist) {
            Arrays.fill(e, inf);
        }
        dist[s][0] = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dis = p[0], u = p[1], t = p[2];
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (t + 1 <= k && dist[v][t + 1] > dis) {
                    dist[v][t + 1] = dis;
                    pq.offer(new int[] {dis, v, t + 1});
                }
                if (dist[v][t] > dis + w) {
                    dist[v][t] = dis + w;
                    pq.offer(new int[] {dis + w, v, t});
                }
            }
        }
        int ans = inf;
        for (int i = 0; i <= k; ++i) {
            ans = Math.min(ans, dist[d][i]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int shortestPathWithHops(int n, vector<vector<int>>& edges, int s, int d, int k) {
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        pq.emplace(0, s, 0);
        int dist[n][k + 1];
        memset(dist, 0x3f, sizeof(dist));
        dist[s][0] = 0;
        while (!pq.empty()) {
            auto [dis, u, t] = pq.top();
            pq.pop();
            for (auto [v, w] : g[u]) {
                if (t + 1 <= k && dist[v][t + 1] > dis) {
                    dist[v][t + 1] = dis;
                    pq.emplace(dis, v, t + 1);
                }
                if (dist[v][t] > dis + w) {
                    dist[v][t] = dis + w;
                    pq.emplace(dis + w, v, t);
                }
            }
        }
        return *min_element(dist[d], dist[d] + k + 1);
    }
};
```

```go
func shortestPathWithHops(n int, edges [][]int, s int, d int, k int) int {
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
	pq := hp{{0, s, 0}}
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, k+1)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[s][0] = 0
	for len(pq) > 0 {
		p := heap.Pop(&pq).(tuple)
		dis, u, t := p.dis, p.u, p.t
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if t+1 <= k && dist[v][t+1] > dis {
				dist[v][t+1] = dis
				heap.Push(&pq, tuple{dis, v, t + 1})
			}
			if dist[v][t] > dis+w {
				dist[v][t] = dis + w
				heap.Push(&pq, tuple{dis + w, v, t})
			}
		}
	}
	return slices.Min(dist[d])
}

type tuple struct{ dis, u, t int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

<!-- tabs:end -->

<!-- end -->
