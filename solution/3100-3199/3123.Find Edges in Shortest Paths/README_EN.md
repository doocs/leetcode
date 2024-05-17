---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/README_EN.md
rating: 2093
source: Weekly Contest 394 Q4
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3123. Find Edges in Shortest Paths](https://leetcode.com/problems/find-edges-in-shortest-paths)

[中文文档](/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected weighted graph of <code>n</code> nodes numbered from 0 to <code>n - 1</code>. The graph consists of <code>m</code> edges represented by a 2D array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>Consider all the shortest paths from node 0 to node <code>n - 1</code> in the graph. You need to find a <strong>boolean</strong> array <code>answer</code> where <code>answer[i]</code> is <code>true</code> if the edge <code>edges[i]</code> is part of <strong>at least</strong> one shortest path. Otherwise, <code>answer[i]</code> is <code>false</code>.</p>

<p>Return the array <code>answer</code>.</p>

<p><strong>Note</strong> that the graph may not be connected.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graph35drawio-1.png" style="height: 129px; width: 250px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,true,true,false,true,true,true,false]</span></p>

<p><strong>Explanation:</strong></p>

<p>The following are <strong>all</strong> the shortest paths between nodes 0 and 5:</p>

<ul>
	<li>The path <code>0 -&gt; 1 -&gt; 5</code>: The sum of weights is <code>4 + 1 = 5</code>.</li>
	<li>The path <code>0 -&gt; 2 -&gt; 3 -&gt; 5</code>: The sum of weights is <code>1 + 1 + 3 = 5</code>.</li>
	<li>The path <code>0 -&gt; 2 -&gt; 3 -&gt; 1 -&gt; 5</code>: The sum of weights is <code>1 + 1 + 2 + 1 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graphhhh.png" style="width: 185px; height: 136px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false,false,true]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is one shortest path between nodes 0 and 3, which is the path <code>0 -&gt; 2 -&gt; 3</code> with the sum of weights <code>1 + 2 = 3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= m &lt;= min(5 * 10<sup>4</sup>, n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There are no repeated edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Heap Optimized Dijkstra

First, we create an adjacency list $g$ to store the edges of the graph. Then we create an array $dist$ to store the shortest distance from node $0$ to other nodes. We initialize $dist[0] = 0$, and the distance of other nodes is initialized to infinity.

Then, we use the Dijkstra algorithm to calculate the shortest distance from node $0$ to other nodes. The specific steps are as follows:

1. Create a priority queue $q$ to store the distance and node number of the nodes. Initially, add node $0$ to the queue with a distance of $0$.
2. Take a node $a$ from the queue. If the distance $da$ of $a$ is greater than $dist[a]$, it means that $a$ has been updated, so skip it directly.
3. Traverse all neighbor nodes $b$ of node $a$. If $dist[b] > dist[a] + w$, update $dist[b] = dist[a] + w$, and add node $b$ to the queue.
4. Repeat steps 2 and 3 until the queue is empty.

Next, we create an answer array $ans$ of length $m$, initially all elements are $false$. If $dist[n - 1]$ is infinity, it means that node $0$ cannot reach node $n - 1$, return $ans$ directly. Otherwise, we start from node $n - 1$, traverse all edges, if the edge $(a, b, i)$ satisfies $dist[a] = dist[b] + w$, set $ans[i]$ to $true$, and add node $b$ to the queue.

Finally, return the answer.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges respectively.

<!-- tabs:start -->

```python
class Solution:
    def findAnswer(self, n: int, edges: List[List[int]]) -> List[bool]:
        g = defaultdict(list)
        for i, (a, b, w) in enumerate(edges):
            g[a].append((b, w, i))
            g[b].append((a, w, i))
        dist = [inf] * n
        dist[0] = 0
        q = [(0, 0)]
        while q:
            da, a = heappop(q)
            if da > dist[a]:
                continue
            for b, w, _ in g[a]:
                if dist[b] > dist[a] + w:
                    dist[b] = dist[a] + w
                    heappush(q, (dist[b], b))
        m = len(edges)
        ans = [False] * m
        if dist[n - 1] == inf:
            return ans
        q = deque([n - 1])
        while q:
            a = q.popleft()
            for b, w, i in g[a]:
                if dist[a] == dist[b] + w:
                    ans[i] = True
                    q.append(b)
        return ans
```

```java
class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int m = edges.length;
        for (int i = 0; i < m; ++i) {
            int a = edges[i][0], b = edges[i][1], w = edges[i][2];
            g[a].add(new int[] {b, w, i});
            g[b].add(new int[] {a, w, i});
        }
        int[] dist = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(dist, inf);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int da = p[0], a = p[1];
            if (da > dist[a]) {
                continue;
            }
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.offer(new int[] {dist[b], b});
                }
            }
        }
        boolean[] ans = new boolean[m];
        if (dist[n - 1] == inf) {
            return ans;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n - 1);
        while (!q.isEmpty()) {
            int a = q.poll();
            for (var e : g[a]) {
                int b = e[0], w = e[1], i = e[2];
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.offer(b);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<bool> findAnswer(int n, vector<vector<int>>& edges) {
        vector<vector<array<int, 3>>> g(n);
        int m = edges.size();
        for (int i = 0; i < m; ++i) {
            auto e = edges[i];
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w, i});
            g[b].push_back({a, w, i});
        }
        const int inf = 1 << 30;
        vector<int> dist(n, inf);
        dist[0] = 0;

        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [da, a] = pq.top();
            pq.pop();
            if (da > dist[a]) {
                continue;
            }

            for (auto [b, w, _] : g[a]) {
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.push({dist[b], b});
                }
            }
        }
        vector<bool> ans(m);
        if (dist[n - 1] == inf) {
            return ans;
        }
        queue<int> q{{n - 1}};
        while (!q.empty()) {
            int a = q.front();
            q.pop();
            for (auto [b, w, i] : g[a]) {
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.push(b);
                }
            }
        }
        return ans;
    }
};
```

```go
func findAnswer(n int, edges [][]int) []bool {
	g := make([][][3]int, n)
	for i, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [3]int{b, w, i})
		g[b] = append(g[b], [3]int{a, w, i})
	}
	dist := make([]int, n)
	const inf int = 1 << 30
	for i := range dist {
		dist[i] = inf
	}
	dist[0] = 0
	pq := hp{{0, 0}}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		da, a := p.dis, p.u
		if da > dist[a] {
			continue
		}
		for _, e := range g[a] {
			b, w := e[0], e[1]
			if dist[b] > dist[a]+w {
				dist[b] = dist[a] + w
				heap.Push(&pq, pair{dist[b], b})
			}
		}
	}
	ans := make([]bool, len(edges))
	if dist[n-1] == inf {
		return ans
	}
	q := []int{n - 1}
	for len(q) > 0 {
		a := q[0]
		q = q[1:]
		for _, e := range g[a] {
			b, w, i := e[0], e[1], e[2]
			if dist[a] == dist[b]+w {
				ans[i] = true
				q = append(q, b)
			}
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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
