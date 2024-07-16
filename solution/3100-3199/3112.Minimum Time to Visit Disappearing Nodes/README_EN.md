---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/README_EN.md
rating: 1756
source: Biweekly Contest 128 Q3
tags:
    - Graph
    - Array
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3112. Minimum Time to Visit Disappearing Nodes](https://leetcode.com/problems/minimum-time-to-visit-disappearing-nodes)

[中文文档](/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/README.md)

## Description

<!-- description:start -->

<p>There is an undirected graph of <code>n</code> nodes. You are given a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code> describes an edge between node <code>u<sub>i</sub></code> and node <code>v<sub>i</sub></code> with a traversal time of <code>length<sub>i</sub></code> units.</p>

<p>Additionally, you are given an array <code>disappear</code>, where <code>disappear[i]</code> denotes the time when the node <code>i</code> disappears from the graph and you won&#39;t be able to visit it.</p>

<p><strong>Notice</strong> that the graph might be disconnected and might contain multiple edges.</p>

<p>Return the array <code>answer</code>, with <code>answer[i]</code> denoting the <strong>minimum</strong> units of time required to reach node <code>i</code> from node 0. If node <code>i</code> is <strong>unreachable</strong> from node 0 then <code>answer[i]</code> is <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example1.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io"> n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io"> [0,-1,4]</span></p>

<p><strong>Explanation:</strong></p>

<p>We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.</p>

<ul>
	<li>For node 0, we don&#39;t need any time as it is our starting point.</li>
	<li>For node 1, we need at least 2 units of time to traverse <code>edges[0]</code>. Unfortunately, it disappears at that moment, so we won&#39;t be able to visit it.</li>
	<li>For node 2, we need at least 4 units of time to traverse <code>edges[2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example2.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io"> n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io"> [0,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.</p>

<ul>
	<li>For node 0, we don&#39;t need any time as it is the starting point.</li>
	<li>For node 1, we need at least 2 units of time to traverse <code>edges[0]</code>.</li>
	<li>For node 2, we need at least 3 units of time to traverse <code>edges[0]</code> and <code>edges[1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,1]], disappear = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Exactly when we reach node 1, it disappears.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= length<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>disappear.length == n</code></li>
	<li><code>1 &lt;= disappear[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Heap-Optimized Dijkstra

First, we create an adjacency list $\textit{g}$ to store the edges of the graph. Then, we create an array $\textit{dist}$ to store the shortest distances from node $0$ to other nodes. Initialize $\textit{dist}[0] = 0$, and the distances for the rest of the nodes are initialized to infinity.

Next, we use the Dijkstra algorithm to calculate the shortest distances from node $0$ to other nodes. The specific steps are as follows:

1. Create a priority queue $\textit{pq}$ to store the distances and node numbers. Initially, add node $0$ to the queue with a distance of $0$.
2. Remove a node $u$ from the queue. If the distance $du$ of $u$ is greater than $\textit{dist}[u]$, it means $u$ has already been updated, so we skip it directly.
3. Iterate through all neighbor nodes $v$ of node $u$. If $\textit{dist}[v] > \textit{dist}[u] + w$ and $\textit{dist}[u] + w < \textit{disappear}[v]$, then update $\textit{dist}[v] = \textit{dist}[u] + w$ and add node $v$ to the queue.
4. Repeat steps 2 and 3 until the queue is empty.

Finally, we iterate through the $\textit{dist}$ array. If $\textit{dist}[i] < \textit{disappear}[i]$, then $\textit{answer}[i] = \textit{dist}[i]$; otherwise, $\textit{answer}[i] = -1$.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(m)$. Here, $m$ is the number of edges.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTime(
        self, n: int, edges: List[List[int]], disappear: List[int]
    ) -> List[int]:
        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [inf] * n
        dist[0] = 0
        q = [(0, 0)]
        while q:
            du, u = heappop(q)
            if du > dist[u]:
                continue
            for v, w in g[u]:
                if dist[v] > dist[u] + w and dist[u] + w < disappear[v]:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        return [a if a < b else -1 for a, b in zip(dist, disappear)]
```

#### Java

```java
class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty()) {
            var e = pq.poll();
            int du = e[0], u = e[1];
            if (du > dist[u]) {
                continue;
            }
            for (var nxt : g[u]) {
                int v = nxt[0], w = nxt[1];
                if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[] {dist[v], v});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dist[i] < disappear[i] ? dist[i] : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minimumTime(int n, vector<vector<int>>& edges, vector<int>& disappear) {
        vector<vector<pair<int, int>>> g(n);
        for (const auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        vector<int> dist(n, 1 << 30);
        dist[0] = 0;

        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [du, u] = pq.top();
            pq.pop();

            if (du > dist[u]) {
                continue;
            }

            for (auto [v, w] : g[u]) {
                if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                    dist[v] = dist[u] + w;
                    pq.push({dist[v], v});
                }
            }
        }

        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = dist[i] < disappear[i] ? dist[i] : -1;
        }

        return ans;
    }
};
```

#### Go

```go
func minimumTime(n int, edges [][]int, disappear []int) []int {
	g := make([][]pair, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], pair{v, w})
		g[v] = append(g[v], pair{u, w})
	}

	dist := make([]int, n)
	for i := range dist {
		dist[i] = 1 << 30
	}
	dist[0] = 0

	pq := hp{{0, 0}}

	for len(pq) > 0 {
		du, u := pq[0].dis, pq[0].u
		heap.Pop(&pq)

		if du > dist[u] {
			continue
		}

		for _, nxt := range g[u] {
			v, w := nxt.dis, nxt.u
			if dist[v] > dist[u]+w && dist[u]+w < disappear[v] {
				dist[v] = dist[u] + w
				heap.Push(&pq, pair{dist[v], v})
			}
		}
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		if dist[i] < disappear[i] {
			ans[i] = dist[i]
		} else {
			ans[i] = -1
		}
	}

	return ans
}

type pair struct{ dis, u int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function minimumTime(n: number, edges: number[][], disappear: number[]): number[] {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const dist = Array.from({ length: n }, () => Infinity);
    dist[0] = 0;
    const pq = new PriorityQueue({
        compare: (a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]),
    });
    pq.enqueue([0, 0]);
    while (pq.size() > 0) {
        const [du, u] = pq.dequeue()!;
        if (du > dist[u]) {
            continue;
        }
        for (const [v, w] of g[u]) {
            if (dist[v] > dist[u] + w && dist[u] + w < disappear[v]) {
                dist[v] = dist[u] + w;
                pq.enqueue([dist[v], v]);
            }
        }
    }
    return dist.map((a, i) => (a < disappear[i] ? a : -1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
