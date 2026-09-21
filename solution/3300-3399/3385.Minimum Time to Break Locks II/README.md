---
comments: true
difficulty: 困难
tags:
    - 广度优先搜索
    - 图
    - 数组
---

<!-- problem:start -->

# [3385. 破解锁的最少时间 II 🔒](https://leetcode.cn/problems/minimum-time-to-break-locks-ii)

[English Version](/solution/3300-3399/3385.Minimum%20Time%20to%20Break%20Locks%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Bob 被困在了一个地窖里，他需要破解 <code>n</code>&nbsp;个锁才能逃出地窖，每一个锁都需要一定的 <strong>能量</strong>&nbsp;才能打开。每一个锁需要的能量存放在一个数组&nbsp;<code>strength</code>&nbsp;里，其中&nbsp;<code>strength[i]</code>&nbsp;表示打开第 <code>i</code>&nbsp;个锁需要的能量。</p>

<p>Bob 有一把剑，它具备以下的特征：</p>

<ul>
	<li>一开始剑的能量为 0 。</li>
	<li>剑的能量增加因子&nbsp;<code><font face="monospace">X</font></code>&nbsp;一开始的值为 1 。</li>
	<li>每分钟，剑的能量都会增加当前的&nbsp;<code>X</code>&nbsp;值。</li>
	<li>打开第 <code>i</code>&nbsp;把锁，剑的能量需要到达 <strong>至少</strong>&nbsp;<code>strength[i]</code>&nbsp;。</li>
	<li>打开一把锁以后，剑的能量会变回 0 ，<code>X</code>&nbsp;的值会增加 1。</li>
</ul>

<p>你的任务是打开所有 <code>n</code>&nbsp;把锁并逃出地窖，请你求出需要的 <strong>最少</strong>&nbsp;分钟数。</p>

<p>请你返回 Bob<strong>&nbsp;</strong>打开所有 <code>n</code>&nbsp;把锁需要的 <strong>最少</strong>&nbsp;时间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [3,4,1]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 3&nbsp;把锁</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 4 分钟打开所有的锁，所以答案为 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [2,5,4]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">打开第 3 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">4</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 6&nbsp;分钟打开所有的锁，所以答案为 6。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 80</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 与 I 相同，第 $j$ 把锁在能量为 $j+1$ 时耗时 $\lceil a[i]/(j+1) \rceil$，但 $n \le 80$，状压不可行。这是锁与「第几次破」之间的最小权匹配。
>
> 左部为锁，右部为次序 $1..n$，边权为该锁排在该次序时的耗时。源汇容量为 $1$，保证一一对应。
>
> 最小费用最大流求出匹配总权，即为最短总时间。

<!-- thinking:end -->

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
class MCFGraph {
public:
    MCFGraph(int n)
        : n(n)
        , g(n) {}

    void addEdge(int src, int dst, int cap, int cost) {
        int i = g[src].size();
        int j = g[dst].size();
        g[src].push_back({dst, cap, cost, j});
        g[dst].push_back({src, 0, -cost, i});
    }

    pair<int, int> flow(int s, int t, int flowLimit) {
        return slope(s, t, flowLimit).back();
    }

private:
    struct _Edge {
        int dst, cap, cost, rev;
    };

    int n;
    vector<vector<_Edge>> g;

    vector<pair<int, int>> slope(int s, int t, int flowLimit) {
        vector<int> dual(n);
        vector<int> pv(n), pe(n);
        auto refineDual = [&]() -> bool {
            vector<int> dist(n, INT_MAX);
            vector<char> vis(n);
            priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
            dist[s] = 0;
            pq.emplace(0, s);
            while (!pq.empty()) {
                auto [distV, v] = pq.top();
                pq.pop();
                if (vis[v]) {
                    continue;
                }
                vis[v] = 1;
                if (v == t) {
                    break;
                }
                int dualV = dual[v];
                for (int i = 0; i < (int) g[v].size(); ++i) {
                    auto& e = g[v][i];
                    int w = e.dst;
                    if (vis[w] || e.cap == 0) {
                        continue;
                    }
                    int newDist = distV + e.cost - dual[w] + dualV;
                    if (newDist < dist[w]) {
                        dist[w] = newDist;
                        pv[w] = v;
                        pe[w] = i;
                        pq.emplace(newDist, w);
                    }
                }
            }
            if (!vis[t]) {
                return false;
            }
            int distT = dist[t];
            for (int v = 0; v < n; ++v) {
                if (vis[v]) {
                    dual[v] -= distT - dist[v];
                }
            }
            return true;
        };

        int flow = 0, cost = 0;
        int prevCostPerFlow = INT_MIN;
        vector<pair<int, int>> result = {{0, 0}};
        while (flow < flowLimit) {
            if (!refineDual()) {
                break;
            }
            int f = flowLimit - flow;
            for (int v = t; v != s; v = pv[v]) {
                f = min(f, g[pv[v]][pe[v]].cap);
            }
            for (int v = t; v != s; v = pv[v]) {
                auto& e = g[pv[v]][pe[v]];
                e.cap -= f;
                g[v][e.rev].cap += f;
            }
            int c = -dual[s];
            flow += f;
            cost += f * c;
            if (c == prevCostPerFlow) {
                result.pop_back();
            }
            result.emplace_back(flow, cost);
            prevCostPerFlow = c;
        }
        return result;
    }
};

class Solution {
public:
    int findMinimumTime(vector<int>& strength) {
        int n = strength.size();
        int s = n * 2;
        int t = s + 1;
        MCFGraph g(t + 1);
        for (int i = 0; i < n; ++i) {
            g.addEdge(s, i, 1, 0);
            g.addEdge(i + n, t, 1, 0);
            for (int j = 0; j < n; ++j) {
                g.addEdge(i, j + n, 1, (strength[i] - 1) / (j + 1) + 1);
            }
        }
        return g.flow(s, t, n).second;
    }
};
```

#### Go

```go
type mcfEdge struct {
	dst, cap, cost, rev int
}

type MCFGraph struct {
	n int
	g [][]mcfEdge
}

func NewMCFGraph(n int) *MCFGraph {
	return &MCFGraph{n: n, g: make([][]mcfEdge, n)}
}

func (m *MCFGraph) AddEdge(src, dst, cap, cost int) {
	i := len(m.g[src])
	j := len(m.g[dst])
	m.g[src] = append(m.g[src], mcfEdge{dst: dst, cap: cap, cost: cost, rev: j})
	m.g[dst] = append(m.g[dst], mcfEdge{dst: src, cap: 0, cost: -cost, rev: i})
}

func (m *MCFGraph) Flow(s, t, flowLimit int) (int, int) {
	res := m.slope(s, t, flowLimit)
	return res[len(res)-1][0], res[len(res)-1][1]
}

type mcfItem struct{ dist, v int }
type mcfPQ []mcfItem

func (p mcfPQ) Len() int           { return len(p) }
func (p mcfPQ) Less(i, j int) bool { return p[i].dist < p[j].dist }
func (p mcfPQ) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }
func (p *mcfPQ) Push(x any)        { *p = append(*p, x.(mcfItem)) }
func (p *mcfPQ) Pop() any {
	old := *p
	x := old[len(old)-1]
	*p = old[:len(old)-1]
	return x
}

func (m *MCFGraph) slope(s, t, flowLimit int) [][2]int {
	dual := make([]int, m.n)
	pv := make([]int, m.n)
	pe := make([]int, m.n)
	refineDual := func() bool {
		dist := make([]int, m.n)
		vis := make([]bool, m.n)
		for i := range dist {
			dist[i] = math.MaxInt32
		}
		pq := &mcfPQ{{0, s}}
		heap.Init(pq)
		dist[s] = 0
		for pq.Len() > 0 {
			cur := heap.Pop(pq).(mcfItem)
			if vis[cur.v] {
				continue
			}
			vis[cur.v] = true
			if cur.v == t {
				break
			}
			dualV := dual[cur.v]
			for i := range m.g[cur.v] {
				e := &m.g[cur.v][i]
				w := e.dst
				if vis[w] || e.cap == 0 {
					continue
				}
				newDist := cur.dist + e.cost - dual[w] + dualV
				if newDist < dist[w] {
					dist[w] = newDist
					pv[w] = cur.v
					pe[w] = i
					heap.Push(pq, mcfItem{newDist, w})
				}
			}
		}
		if !vis[t] {
			return false
		}
		distT := dist[t]
		for v := 0; v < m.n; v++ {
			if vis[v] {
				dual[v] -= distT - dist[v]
			}
		}
		return true
	}

	flow, cost := 0, 0
	prevCostPerFlow := math.MinInt32
	result := [][2]int{{0, 0}}
	for flow < flowLimit {
		if !refineDual() {
			break
		}
		f := flowLimit - flow
		for v := t; v != s; v = pv[v] {
			if cap := m.g[pv[v]][pe[v]].cap; cap < f {
				f = cap
			}
		}
		for v := t; v != s; v = pv[v] {
			e := &m.g[pv[v]][pe[v]]
			e.cap -= f
			m.g[v][e.rev].cap += f
		}
		c := -dual[s]
		flow += f
		cost += f * c
		if c == prevCostPerFlow {
			result = result[:len(result)-1]
		}
		result = append(result, [2]int{flow, cost})
		prevCostPerFlow = c
	}
	return result
}

func findMinimumTime(strength []int) int {
	n := len(strength)
	s := n * 2
	t := s + 1
	g := NewMCFGraph(t + 1)
	for i := 0; i < n; i++ {
		g.AddEdge(s, i, 1, 0)
		g.AddEdge(i+n, t, 1, 0)
		for j := 0; j < n; j++ {
			g.AddEdge(i, j+n, 1, (strength[i]-1)/(j+1)+1)
		}
	}
	_, cost := g.Flow(s, t, n)
	return cost
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
