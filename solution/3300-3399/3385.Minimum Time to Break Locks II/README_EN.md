---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3385.Minimum%20Time%20to%20Break%20Locks%20II/README_EN.md
---

<!-- problem:start -->

# [3385. Minimum Time to Break Locks II 🔒](https://leetcode.com/problems/minimum-time-to-break-locks-ii)

[中文文档](/solution/3300-3399/3385.Minimum%20Time%20to%20Break%20Locks%20II/README.md)

## Description

<!-- description:start -->

<p>Bob is stuck in a dungeon and must break <code>n</code> locks, each requiring some amount of <strong>energy</strong> to break. The required energy for each lock is stored in an array called <code>strength</code> where <code>strength[i]</code> indicates the energy needed to break the <code>i<sup>th</sup></code> lock.</p>

<p>To break a lock, Bob uses a sword with the following characteristics:</p>

<ul>
	<li>The initial energy of the sword is 0.</li>
	<li>The initial factor <code><font face="monospace">X</font></code> by which the energy of the sword increases is 1.</li>
	<li>Every minute, the energy of the sword increases by the current factor <code>X</code>.</li>
	<li>To break the <code>i<sup>th</sup></code> lock, the energy of the sword must reach at least <code>strength[i]</code>.</li>
	<li>After breaking a lock, the energy of the sword resets to 0, and the factor <code>X</code> increases by 1.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> time in minutes required for Bob to break all <code>n</code> locks and escape the dungeon.</p>

<p>Return the <strong>minimum </strong>time required for Bob to break all <code>n</code> locks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [3,4,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Time</th>
			<th>Energy</th>
			<th>X</th>
			<th>Action</th>
			<th>Updated X</th>
		</tr>
		<tr>
			<td>0</td>
			<td>0</td>
			<td>1</td>
			<td>Nothing</td>
			<td>1</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>Break 3<sup>rd</sup> Lock</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>2</td>
			<td>2</td>
			<td>Nothing</td>
			<td>2</td>
		</tr>
		<tr>
			<td>3</td>
			<td>4</td>
			<td>2</td>
			<td>Break 2<sup>nd</sup> Lock</td>
			<td>3</td>
		</tr>
		<tr>
			<td>4</td>
			<td>3</td>
			<td>3</td>
			<td>Break 1<sup>st</sup> Lock</td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 4 minutes; thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [2,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Time</th>
			<th>Energy</th>
			<th>X</th>
			<th>Action</th>
			<th>Updated X</th>
		</tr>
		<tr>
			<td>0</td>
			<td>0</td>
			<td>1</td>
			<td>Nothing</td>
			<td>1</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>1</td>
			<td>Nothing</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
			<td>2</td>
			<td>1</td>
			<td>Break 1<sup>st</sup> Lock</td>
			<td>2</td>
		</tr>
		<tr>
			<td>3</td>
			<td>2</td>
			<td>2</td>
			<td>Nothing</td>
			<td>2</td>
		</tr>
		<tr>
			<td>4</td>
			<td>4</td>
			<td>2</td>
			<td>Break 3<sup>rd</sup> Lock</td>
			<td>3</td>
		</tr>
		<tr>
			<td>5</td>
			<td>3</td>
			<td>3</td>
			<td>Nothing</td>
			<td>3</td>
		</tr>
		<tr>
			<td>6</td>
			<td>6</td>
			<td>3</td>
			<td>Break 2<sup>nd</sup> Lock</td>
			<td>4</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 6 minutes; thus, the answer is 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 80</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>n == strength.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class MCFGraph:
    class Edge(NamedTuple):
        src: int
        dst: int
        cap: int
        flow: int
        cost: int

    class _Edge:
        def __init__(self, dst: int, cap: int, cost: int) -> None:
            self.dst = dst
            self.cap = cap
            self.cost = cost
            self.rev: Optional[MCFGraph._Edge] = None

    def __init__(self, n: int) -> None:
        self._n = n
        self._g: List[List[MCFGraph._Edge]] = [[] for _ in range(n)]
        self._edges: List[MCFGraph._Edge] = []

    def add_edge(self, src: int, dst: int, cap: int, cost: int) -> int:
        assert 0 <= src < self._n
        assert 0 <= dst < self._n
        assert 0 <= cap
        m = len(self._edges)
        e = MCFGraph._Edge(dst, cap, cost)
        re = MCFGraph._Edge(src, 0, -cost)
        e.rev = re
        re.rev = e
        self._g[src].append(e)
        self._g[dst].append(re)
        self._edges.append(e)
        return m

    def get_edge(self, i: int) -> Edge:
        assert 0 <= i < len(self._edges)
        e = self._edges[i]
        re = cast(MCFGraph._Edge, e.rev)
        return MCFGraph.Edge(re.dst, e.dst, e.cap + re.cap, re.cap, e.cost)

    def edges(self) -> List[Edge]:
        return [self.get_edge(i) for i in range(len(self._edges))]

    def flow(self, s: int, t: int, flow_limit: Optional[int] = None) -> Tuple[int, int]:
        return self.slope(s, t, flow_limit)[-1]

    def slope(
        self, s: int, t: int, flow_limit: Optional[int] = None
    ) -> List[Tuple[int, int]]:
        assert 0 <= s < self._n
        assert 0 <= t < self._n
        assert s != t
        if flow_limit is None:
            flow_limit = cast(int, sum(e.cap for e in self._g[s]))

        dual = [0] * self._n
        prev: List[Optional[Tuple[int, MCFGraph._Edge]]] = [None] * self._n

        def refine_dual() -> bool:
            pq = [(0, s)]
            visited = [False] * self._n
            dist: List[Optional[int]] = [None] * self._n
            dist[s] = 0
            while pq:
                dist_v, v = heappop(pq)
                if visited[v]:
                    continue
                visited[v] = True
                if v == t:
                    break
                dual_v = dual[v]
                for e in self._g[v]:
                    w = e.dst
                    if visited[w] or e.cap == 0:
                        continue
                    reduced_cost = e.cost - dual[w] + dual_v
                    new_dist = dist_v + reduced_cost
                    dist_w = dist[w]
                    if dist_w is None or new_dist < dist_w:
                        dist[w] = new_dist
                        prev[w] = v, e
                        heappush(pq, (new_dist, w))
            else:
                return False
            dist_t = dist[t]
            for v in range(self._n):
                if visited[v]:
                    dual[v] -= cast(int, dist_t) - cast(int, dist[v])
            return True

        flow = 0
        cost = 0
        prev_cost_per_flow: Optional[int] = None
        result = [(flow, cost)]
        while flow < flow_limit:
            if not refine_dual():
                break
            f = flow_limit - flow
            v = t
            while prev[v] is not None:
                u, e = cast(Tuple[int, MCFGraph._Edge], prev[v])
                f = min(f, e.cap)
                v = u
            v = t
            while prev[v] is not None:
                u, e = cast(Tuple[int, MCFGraph._Edge], prev[v])
                e.cap -= f
                assert e.rev is not None
                e.rev.cap += f
                v = u
            c = -dual[s]
            flow += f
            cost += f * c
            if c == prev_cost_per_flow:
                result.pop()
            result.append((flow, cost))
            prev_cost_per_flow = c
        return result


class Solution:
    def findMinimumTime(self, a: List[int]) -> int:
        n = len(a)
        s = n * 2
        t = s + 1
        g = MCFGraph(t + 1)

        for i in range(n):
            g.add_edge(s, i, 1, 0)
            g.add_edge(i + n, t, 1, 0)
            for j in range(n):
                g.add_edge(i, j + n, 1, (a[i] - 1) // (j + 1) + 1)

        return g.flow(s, t, n)[1]
```

#### Java

```java
class MCFGraph {
    static class Edge {
        int src, dst, cap, flow, cost;

        Edge(int src, int dst, int cap, int flow, int cost) {
            this.src = src;
            this.dst = dst;
            this.cap = cap;
            this.flow = flow;
            this.cost = cost;
        }
    }

    static class _Edge {
        int dst, cap, cost;
        _Edge rev;

        _Edge(int dst, int cap, int cost) {
            this.dst = dst;
            this.cap = cap;
            this.cost = cost;
            this.rev = null;
        }
    }

    private int n;
    private List<List<_Edge>> graph;
    private List<_Edge> edges;

    public MCFGraph(int n) {
        this.n = n;
        this.graph = new ArrayList<>();
        this.edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public int addEdge(int src, int dst, int cap, int cost) {
        assert (0 <= src && src < n);
        assert (0 <= dst && dst < n);
        assert (0 <= cap);

        int m = edges.size();
        _Edge e = new _Edge(dst, cap, cost);
        _Edge re = new _Edge(src, 0, -cost);
        e.rev = re;
        re.rev = e;

        graph.get(src).add(e);
        graph.get(dst).add(re);
        edges.add(e);
        return m;
    }

    public Edge getEdge(int i) {
        assert (0 <= i && i < edges.size());
        _Edge e = edges.get(i);
        _Edge re = e.rev;
        return new Edge(re.dst, e.dst, e.cap + re.cap, re.cap, e.cost);
    }

    public List<Edge> edges() {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            result.add(getEdge(i));
        }
        return result;
    }

    public int[] flow(int s, int t, Integer flowLimit) {
        List<int[]> result = slope(s, t, flowLimit);
        return result.get(result.size() - 1);
    }

    public List<int[]> slope(int s, int t, Integer flowLimit) {
        assert (0 <= s && s < n);
        assert (0 <= t && t < n);
        assert (s != t);

        if (flowLimit == null) {
            flowLimit = graph.get(s).stream().mapToInt(e -> e.cap).sum();
        }

        int[] dual = new int[n];
        Tuple[] prev = new Tuple[n];

        List<int[]> result = new ArrayList<>();
        result.add(new int[] {0, 0});

        while (true) {
            if (!refineDual(s, t, dual, prev)) {
                break;
            }

            int f = flowLimit;
            int v = t;
            while (prev[v] != null) {
                Tuple tuple = prev[v];
                int u = tuple.first;
                _Edge e = tuple.second;
                f = Math.min(f, e.cap);
                v = u;
            }

            v = t;
            while (prev[v] != null) {
                Tuple tuple = prev[v];
                int u = tuple.first;
                _Edge e = tuple.second;
                e.cap -= f;
                e.rev.cap += f;
                v = u;
            }

            int c = -dual[s];
            result.add(new int[] {
                result.get(result.size() - 1)[0] + f, result.get(result.size() - 1)[1] + f * c});

            if (c == result.get(result.size() - 2)[1]) {
                result.remove(result.size() - 2);
            }
        }

        return result;
    }

    private boolean refineDual(int s, int t, int[] dual, Tuple[] prev) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {0, s});
        boolean[] visited = new boolean[n];
        Integer[] dist = new Integer[n];
        Arrays.fill(dist, null);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int distV = current[0];
            int v = current[1];

            if (visited[v]) continue;
            visited[v] = true;

            if (v == t) break;

            int dualV = dual[v];
            for (_Edge e : graph.get(v)) {
                int w = e.dst;
                if (visited[w] || e.cap == 0) continue;

                int reducedCost = e.cost - dual[w] + dualV;
                int newDist = distV + reducedCost;
                Integer distW = dist[w];

                if (distW == null || newDist < distW) {
                    dist[w] = newDist;
                    prev[w] = new Tuple(v, e);
                    pq.add(new int[] {newDist, w});
                }
            }
        }

        if (!visited[t]) return false;

        int distT = dist[t];
        for (int v = 0; v < n; v++) {
            if (visited[v]) {
                dual[v] -= distT - dist[v];
            }
        }

        return true;
    }

    static class Tuple {
        int first;
        _Edge second;

        Tuple(int first, _Edge second) {
            this.first = first;
            this.second = second;
        }
    }
}

class Solution {
    public int findMinimumTime(int[] strength) {
        int n = strength.length;
        int s = n * 2;
        int t = s + 1;
        MCFGraph g = new MCFGraph(t + 1);

        for (int i = 0; i < n; i++) {
            g.addEdge(s, i, 1, 0);
            g.addEdge(i + n, t, 1, 0);
            for (int j = 0; j < n; j++) {
                g.addEdge(i, j + n, 1, (strength[i] - 1) / (j + 1) + 1);
            }
        }

        return g.flow(s, t, n)[1];
    }
}
```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
