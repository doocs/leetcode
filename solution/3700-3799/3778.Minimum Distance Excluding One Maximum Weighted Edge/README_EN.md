---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3778.Minimum%20Distance%20Excluding%20One%20Maximum%20Weighted%20Edge/README_EN.md
---

<!-- problem:start -->

# [3778. Minimum Distance Excluding One Maximum Weighted Edge 🔒](https://leetcode.com/problems/minimum-distance-excluding-one-maximum-weighted-edge)

[中文文档](/solution/3700-3799/3778.Minimum%20Distance%20Excluding%20One%20Maximum%20Weighted%20Edge/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code> and a 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code>.</p>

<p>There is a <strong>weighted</strong> <strong>connected</strong> simple undirected graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>. Each <code>[u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> in <code>edges</code> represents an edge between node <code>u<sub>i</sub></code> and node <code>v<sub>i</sub></code> with <strong>positive</strong> weight <code>w<sub>i</sub></code>.</p>

<p>The <strong>cost</strong> of a path is the <strong>sum</strong> of weights of the edges in the path, <strong>excluding</strong> the edge with the <strong>maximum</strong> weight. If there are multiple edges in the path with the maximum weight, <strong>only</strong> the <strong>first</strong> such edge is excluded.</p>

<p>Return an integer representing the <strong>minimum</strong> <strong>cost</strong> of a path going from node 0 to node <code>n - 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,2],[1,2,7],[2,3,7],[3,4,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one path going from node 0 to node 4: <code>0 -&gt; 1 -&gt; 2 -&gt; 3 -&gt; 4</code>.</p>

<p>The edge weights on this path are 2, 7, 7, and 4.</p>

<p>Excluding the first edge with maximum weight, which is <code>1 -&gt; 2</code>, the cost of this path is <code>2 + 7 + 4 = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1],[1,2,1],[0,2,50000]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are two paths going from node 0 to node 2:</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code></li>
</ul>

<p>The edge weights on this path are 1 and 1.</p>

<p>Excluding the first edge with maximum weight, which is <code>0 -&gt; 1</code>, the cost of this path is 1.</p>

<ul>
	<li><code>0 -&gt; 2</code></li>
</ul>

<p>The only edge weight on this path is 1.</p>

<p>Excluding the first edge with maximum weight, which is <code>0 -&gt; 2</code>, the cost of this path is 0.</p>

<p>The minimum cost is <code>min(1, 0) = 0</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 10<sup>9</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt; n</code></li>
	<li><code>[u<sub>i</sub>, v<sub>i</sub>] != [u<sub>j</sub>, v<sub>j</sub>]</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
	<li>The graph is connected.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dijkstra's Algorithm

The problem is essentially equivalent to finding a path from node $0$ to node $n-1$, where we have one opportunity to treat the weight of a traversed edge as $0$, in order to minimize the sum of path weights.

We first convert $\textit{edges}$ into an adjacency list $\textit{g}$, where $\textit{g}[u]$ stores all edges $(v, w)$ connected to node $u$, indicating that there is an edge with weight $w$ between node $u$ and node $v$.

Next, we use Dijkstra's algorithm to find the shortest path. We define a 2D array $\textit{dist}$, where $\textit{dist}[u][0]$ represents the minimum sum of path weights from node $0$ to node $u$ without using the opportunity to treat an edge weight as $0$; $\textit{dist}[u][1]$ represents the minimum sum of path weights from node $0$ to node $u$ having already used the opportunity to treat an edge weight as $0$.

We use a priority queue $\textit{pq}$ to store pending nodes. Initially, we enqueue $(0, 0, 0)$, indicating that we start from node $0$, with a current path weight sum of $0$, and haven't used the opportunity.

In each iteration, we dequeue the node $(\textit{cur}, u, \textit{used})$ with the minimum path weight sum from the priority queue. If the current path weight sum $\textit{cur}$ is greater than $\textit{dist}[u][\textit{used}]$, we skip this node.

If the current node $u$ is node $n-1$ and we have already used the opportunity $\textit{used} = 1$, we return the current path weight sum $\textit{cur}$.

For each edge $(v, w)$ of node $u$, we calculate the path weight sum to reach node $v$ without using the opportunity: $\textit{nxt} = \textit{cur} + w$. If $\textit{nxt} < \textit{dist}[v][\textit{used}]$, we update $\textit{dist}[v][\textit{used}]$ and enqueue $(\textit{nxt}, v, \textit{used})$.

If we haven't used the opportunity yet $\textit{used} = 0$, we calculate the path weight sum to reach node $v$ when using the opportunity: $\textit{nxt} = \textit{cur}$. If $\textit{nxt} < \textit{dist}[v][1]$, we update $\textit{dist}[v][1]$ and enqueue $(\textit{nxt}, v, 1)$.

After the traversal ends, we return $\textit{dist}[n-1][1]$ as the answer.

The time complexity is $O(m \times \log n)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostExcludingMax(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [[inf] * 2 for _ in range(n)]
        dist[0][0] = 0
        pq = [(0, 0, 0)]
        while pq:
            cur, u, used = heappop(pq)
            if cur > dist[u][used]:
                continue
            if u == n - 1 and used:
                return cur
            for v, w in g[u]:
                nxt = cur + w
                if nxt < dist[v][used]:
                    dist[v][used] = nxt
                    heappush(pq, (nxt, v, used))
                if used == 0:
                    nxt = cur
                    if nxt < dist[v][1]:
                        dist[v][1] = nxt
                        heappush(pq, (nxt, v, 1))
        return dist[n - 1][1]
```

#### Java

```java
class Solution {
    public long minCostExcludingMax(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        long inf = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            dist[i][0] = inf;
            dist[i][1] = inf;
        }
        dist[0][0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 0, 0});

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cur = t[0];
            int u = (int) t[1];
            int used = (int) t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (int[] ed : g[u]) {
                int v = ed[0], w = ed[1];
                long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.add(new long[]{nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.add(new long[]{nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCostExcludingMax(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        long long inf = LLONG_MAX / 4;
        vector<array<long long, 2>> dist(n, {inf, inf});
        dist[0][0] = 0;

        priority_queue<array<long long, 3>, vector<array<long long, 3>>, greater<array<long long, 3>>> pq;
        pq.push({0, 0, 0});

        while (!pq.empty()) {
            auto t = pq.top();
            pq.pop();
            long long cur = t[0];
            int u = t[1];
            int used = t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (auto [v, w] : g[u]) {
                long long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.push({nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.push({nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
};
```

#### Go

```go
func minCostExcludingMax(n int, edges [][]int) int64 {
	g := make([][]edge, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], edge{v, w})
		g[v] = append(g[v], edge{u, w})
	}

	inf := int64(math.MaxInt64 / 4)
	dist := make([][2]int64, n)
	for i := 0; i < n; i++ {
		dist[i][0] = inf
		dist[i][1] = inf
	}
	dist[0][0] = 0

	pq := hp{{0, 0, 0}}
	for len(pq) > 0 {
		t := heap.Pop(&pq).(state)
		cur, u, used := t.cur, t.u, t.used
		if cur > dist[u][used] {
			continue
		}
		if u == n-1 && used == 1 {
			return cur
		}
		for _, ed := range g[u] {
			v, w := ed.to, int64(ed.w)

			nxt := cur + w
			if nxt < dist[v][used] {
				dist[v][used] = nxt
				heap.Push(&pq, state{nxt, v, used})
			}

			if used == 0 {
				nxt = cur
				if nxt < dist[v][1] {
					dist[v][1] = nxt
					heap.Push(&pq, state{nxt, v, 1})
				}
			}
		}
	}
	return dist[n-1][1]
}

type edge struct {
	to int
	w  int
}

type state struct {
	cur  int64
	u    int
	used int
}

type hp []state

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cur < h[j].cur }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(state)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
```

#### TypeScript

```ts
function minCostExcludingMax(n: number, edges: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }

    const INF = Infinity;
    const dist: number[][] = Array.from({ length: n }, () => [INF, INF]);
    dist[0][0] = 0;

    const pq = new PriorityQueue<[number, number, number]>((a, b) =>
        a[0] === b[0] ? a[1] - b[1] : a[0] - b[0],
    );

    pq.enqueue([0, 0, 0]);

    while (pq.size() > 0) {
        const [cur, u, used] = pq.dequeue()!;
        if (cur > dist[u][used]) {
            continue;
        }
        if (u === n - 1 && used === 1) {
            return cur;
        }

        for (const [v, w] of g[u]) {
            const nxt1 = cur + w;
            if (nxt1 < dist[v][used]) {
                dist[v][used] = nxt1;
                pq.enqueue([nxt1, v, used]);
            }
            if (used === 0) {
                const nxt2 = cur;
                if (nxt2 < dist[v][1]) {
                    dist[v][1] = nxt2;
                    pq.enqueue([nxt2, v, 1]);
                }
            }
        }
    }

    return dist[n - 1][1];
}
```

#### Rust

```rust
use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_cost_excluding_max(n: i32, edges: Vec<Vec<i32>>) -> i64 {
        let n = n as usize;

        let mut g: Vec<Vec<(usize, i64)>> = vec![Vec::new(); n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            let w = e[2] as i64;
            g[u].push((v, w));
            g[v].push((u, w));
        }

        let inf: i64 = i64::MAX / 4;
        let mut dist = vec![[inf; 2]; n];
        dist[0][0] = 0;

        // (cur_cost, node, used)
        let mut pq = BinaryHeap::new();
        pq.push(Reverse((0_i64, 0_usize, 0_usize)));

        while let Some(Reverse((cur, u, used))) = pq.pop() {
            if cur > dist[u][used] {
                continue;
            }

            if u == n - 1 && used == 1 {
                return cur;
            }

            for &(v, w) in &g[u] {
                // normal edge
                let nxt = cur + w;
                if nxt < dist[v][used] {
                    dist[v][used] = nxt;
                    pq.push(Reverse((nxt, v, used)));
                }

                // skip max edge (only once)
                if used == 0 {
                    let nxt = cur;
                    if nxt < dist[v][1] {
                        dist[v][1] = nxt;
                        pq.push(Reverse((nxt, v, 1)));
                    }
                }
            }
        }

        dist[n - 1][1]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
