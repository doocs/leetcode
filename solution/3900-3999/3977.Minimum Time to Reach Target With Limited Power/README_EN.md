---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/README_EN.md
---

<!-- problem:start -->

# [3977. Minimum Time to Reach Target With Limited Power](https://leetcode.com/problems/minimum-time-to-reach-target-with-limited-power)

[中文文档](/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>directed</strong> weighted graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>.</p>

<p>The graph is represented by a 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code> indicates a directed edge from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> that takes <code>t<sub>i</sub></code> seconds to traverse.</p>

<p>You are also given an integer <code>power</code> representing the initial available power, and an integer array <code>cost</code> of length <code>n</code>, where <code>cost[u]</code> represents the power required to forward the signal from node <code>u</code> through <strong>any</strong> one of its outgoing edges.</p>

<p>You are given two integers <code>source</code> and <code>target</code>.</p>

<p>The signal starts at <code>source</code> at time 0 with <code>power</code> units of power and follows these rules:</p>

<ul>
	<li>The signal may traverse a directed edge from node <code>u</code> only if the remaining power is <strong>at least</strong> <code>cost[u]</code>.</li>
	<li>No power is consumed when the signal arrives at a node, unless it later leaves that node by traversing another edge.</li>
	<li>When the signal is forwarded from node <code>u</code>, the remaining power is <strong>decreased</strong> by <code>cost[u]</code> units.</li>
	<li>Traversing an edge <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code> <strong>increases</strong> the total time by <code>t<sub>i</sub></code> seconds.</li>
</ul>

<p>Return an integer array <code>answer</code> of size 2, where:</p>

<ul>
	<li><code>answer[0]</code> is the <strong>minimum</strong> time required for the signal to reach node <code>target</code>.</li>
	<li><code>answer[1]</code> is the <strong>maximum</strong> remaining power among all paths that achieve <code>answer[0]</code>.</li>
</ul>

<p>If the signal cannot reach <code>target</code>, return <code>[-1, -1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g1.png" style="width: 197px; height: 200px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,1],[1,4,1],[0,2,1],[2,3,1],[3,4,1]], power = 4, cost = [2,3,1,1,1], source = 0, target = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The signal starts at node 0 with 4 units of power.</li>
	<li>The path <code>0 -&gt; 1 -&gt; 4</code> is not valid, because after leaving node 0, the signal has 2 units of power remaining, which is less than <code>cost[1] = 3</code>.</li>
	<li>The valid path <code>0 -&gt; 2 -&gt; 3 -&gt; 4</code> takes a total time of 3.</li>
	<li>The total power consumed along this path is <code>cost[0] + cost[2] + cost[3] = 4</code>, leaving 0 remaining power.</li>
	<li>Hence, the answer is <code>[3, 0]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g22.png" style="width: 167px; height: 170px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[1,2,2],[2,0,2]], power = 3, cost = [1,1,1], source = 1, target = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since the <code>source</code> and <code>target</code> are the same node, no traversal is required.</li>
	<li>Hence, the minimum total time taken is 0, and no power is consumed.</li>
	<li>Therefore, the answer is <code>[0, 3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g23.png" style="height: 120px; width: 171px;" />​​​​​​​</p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,3],[2,3,4]], power = 3, cost = [1,1,1,1], source = 0, target = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no valid path from <code>source</code> to <code>target</code>, therefore return <code>[-1, -1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 1000</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= t<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= power &lt;= 1000</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 2000</code></li>
	<li><code>0 &lt;= source, target &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dijkstra

This is a shortest path problem, but the state must track the remaining power in addition to the current node.

We define $\textit{dist}[u][p]$ as the minimum time required to reach node $u$ with $p$ units of remaining power. Initially, $\textit{dist}[\textit{source}][\textit{power}] = 0$, and all other states are set to infinity.

We use Dijkstra's algorithm with a priority queue storing triples $(d, p, u)$, representing the current minimum time, the remaining power, and the current node. To maximize the remaining power when the time is the same, we store the remaining power as a negative value when pushing into the queue, so the priority queue prefers states with more remaining power during comparison.

When popping $(d, p, u)$:

- If $u = \textit{target}$, return $[d, p]$ directly;
- If $d > \textit{dist}[u][p]$ or $p < \textit{cost}[u]$, skip this state;
- Otherwise, forward the signal from node $u$, decreasing the remaining power by $\textit{cost}[u]$, then traverse all outgoing edges $(v, t)$ and try to update $\textit{dist}[v][p - \textit{cost}[u]] = \min(\textit{dist}[v][p - \textit{cost}[u]], d + t)$.

If the priority queue becomes empty before reaching the target node, return $[-1, -1]$.

The time complexity is $O((n + m) \times \textit{power} \times \log (n \times \textit{power}))$, and the space complexity is $O(n \times \textit{power})$. Here, $n$ and $m$ are the number of nodes and edges, and $\textit{power}$ is the initial power.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTimeMaxPower(
        self,
        n: int,
        edges: List[List[int]],
        power: int,
        cost: List[int],
        source: int,
        target: int,
    ) -> List[int]:
        g = [[] for _ in range(n)]
        for u, v, t in edges:
            g[u].append((v, t))
        dist = [[inf] * (power + 1) for _ in range(n)]
        dist[source][power] = 0
        pq = [(0, -power, source)]
        while pq:
            d, p, u = heappop(pq)
            p = -p
            if u == target:
                return [d, p]
            if d > dist[u][p] or p < cost[u]:
                continue
            p -= cost[u]
            for v, t in g[u]:
                nd = d + t
                if nd < dist[v][p]:
                    dist[v][p] = nd
                    heappush(pq, (nd, -p, v))
        return [-1, -1]
```

#### Java

```java
class Solution {
    public long[] minTimeMaxPower(
        int n, int[][] edges, int power, int[] cost, int source, int target) {
        long inf = Long.MAX_VALUE / 4;

        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int[] e : edges) {
            g[e[0]].add(new int[] {e[1], e[2]});
        }

        long[][] dist = new long[n][power + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        dist[source][power] = 0;
        pq.offer(new long[] {0, -power, source});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int p = (int) -cur[1];
            int u = (int) cur[2];

            if (u == target) return new long[] {d, p};
            if (d > dist[u][p] || p < cost[u]) continue;

            p -= cost[u];

            for (int[] e : g[u]) {
                int v = e[0];
                int t = e[1];

                long nd = d + t;

                if (nd < dist[v][p]) {
                    dist[v][p] = nd;
                    pq.offer(new long[] {nd, -p, v});
                }
            }
        }

        return new long[] {-1, -1};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> minTimeMaxPower(
        int n,
        vector<vector<int>>& edges,
        int power,
        vector<int>& cost,
        int source,
        int target) {
        using ll = long long;
        const ll inf = LLONG_MAX / 4;

        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            g[e[0]].push_back({e[1], e[2]});
        }

        vector<vector<ll>> dist(n, vector<ll>(power + 1, inf));

        using T = tuple<ll, int, int>;
        priority_queue<T, vector<T>, greater<T>> pq;

        dist[source][power] = 0;
        pq.push({0, -power, source});

        while (!pq.empty()) {
            auto [d, negp, u] = pq.top();
            pq.pop();

            int p = -negp;

            if (u == target) return {d, p};
            if (d > dist[u][p] || p < cost[u]) continue;

            p -= cost[u];

            for (auto& [v, t] : g[u]) {
                ll nd = d + t;

                if (nd < dist[v][p]) {
                    dist[v][p] = nd;
                    pq.push({nd, -p, v});
                }
            }
        }

        return {-1, -1};
    }
};
```

#### Go

```go
type State struct {
	d int64
	p int
	u int
}

type PQ []State

func (h PQ) Len() int { return len(h) }
func (h PQ) Less(i, j int) bool {
	if h[i].d != h[j].d {
		return h[i].d < h[j].d
	}
	return h[i].p < h[j].p
}
func (h PQ) Swap(i, j int) { h[i], h[j] = h[j], h[i] }

func (h *PQ) Push(x interface{}) { *h = append(*h, x.(State)) }
func (h *PQ) Pop() interface{} {
	old := *h
	x := old[len(old)-1]
	*h = old[:len(old)-1]
	return x
}

func minTimeMaxPower(
	n int,
	edges [][]int,
	power int,
	cost []int,
	source int,
	target int,
) []int64 {

	inf := int64(1 << 62)

	g := make([][][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], []int{e[1], e[2]})
	}

	dist := make([][]int64, n)
	for i := range dist {
		dist[i] = make([]int64, power+1)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}

	pq := &PQ{}
	heap.Push(pq, State{0, -power, source})

	dist[source][power] = 0

	for pq.Len() > 0 {
		cur := heap.Pop(pq).(State)
		d := cur.d
		p := -cur.p
		u := cur.u

		if u == target {
			return []int64{d, int64(p)}
		}

		if d > dist[u][p] || p < cost[u] {
			continue
		}

		p -= cost[u]

		for _, e := range g[u] {
			v, t := e[0], e[1]
			nd := d + int64(t)

			if nd < dist[v][p] {
				dist[v][p] = nd
				heap.Push(pq, State{nd, -p, v})
			}
		}
	}

	return []int64{-1, -1}
}
```

#### TypeScript

```ts
function minTimeMaxPower(
    n: number,
    edges: number[][],
    power: number,
    cost: number[],
    source: number,
    target: number,
): number[] {
    const inf = 1e18;

    const g: [number, number][][] = Array.from({ length: n }, () => []);

    for (const [u, v, t] of edges) {
        g[u].push([v, t]);
    }

    const dist: number[][] = Array.from({ length: n }, () => Array(power + 1).fill(inf));

    const pq = new PriorityQueue<number[]>((a, b) => {
        if (a[0] !== b[0]) return a[0] - b[0];
        return a[1] - b[1];
    });

    dist[source][power] = 0;
    pq.enqueue([0, -power, source]);

    while (!pq.isEmpty()) {
        const [d, negp, u] = pq.dequeue();
        let p = -negp;

        if (u === target) return [d, p];
        if (d > dist[u][p] || p < cost[u]) continue;

        p -= cost[u];

        for (const [v, t] of g[u]) {
            const nd = d + t;

            if (nd < dist[v][p]) {
                dist[v][p] = nd;
                pq.enqueue([nd, -p, v]);
            }
        }
    }

    return [-1, -1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
