---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README.md
tags:
    - 图
    - 数组
    - 数学
---

<!-- problem:start -->

# [4004. 使循环数组余额非负的最少移动次数 II 🔒](https://leetcode.cn/problems/minimum-moves-to-balance-circular-array-ii)

[English Version](/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的 <span data-keyword="circular-array">环形数组</span> <code>balance</code>，其中 <code>balance[i]</code>&nbsp;是第 <code>i</code> 个人的净余额。</p>

<p>在一次操作中，一个人可以向其左侧或右侧的相邻人员转移&nbsp;<strong>恰好</strong> 1 单位的余额。</p>

<p>返回使每个人的余额都变为&nbsp;<strong>非负&nbsp;</strong>所需的&nbsp;<strong>最少&nbsp;</strong>操作次数。如果无法做到，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [-1,2,-1]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的操作序列如下：</p>

<ul>
	<li>从 <code>i = 1</code> 向 <code>i = 0</code> 转移 1 单位余额，得到 <code>balance = [0, 1, -1]</code></li>
	<li>从 <code>i = 1</code> 向 <code>i = 2</code> 转移 1 单位余额，得到 <code>balance = [0, 0, 0]</code></li>
</ul>

<p>因此，所需的最少操作次数为 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [4,-1,-2]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的操作序列如下：</p>

<ul>
	<li>从 <code>i = 0</code> 向 <code>i = 1</code> 转移 1 单位余额，得到 <code>balance = [3, 0, -2]</code></li>
	<li>从 <code>i = 0</code> 向 <code>i = 2</code> 转移 1 单位余额，得到 <code>balance = [2, 0, -1]</code></li>
	<li>从 <code>i = 0</code> 向 <code>i = 2</code> 再转移 1 单位余额，得到 <code>balance = [1, 0, 0]</code></li>
</ul>

<p>因此，所需的最少操作次数为 3。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [-3,-3,5]</span></p>

<p><strong>输出：</strong><span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>对于 <code>balance = [-3, -3, 5]</code>，无法使所有人的余额都变为非负，因此答案为 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == balance.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= balance[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：最小费用最大流

记 $n$ 为数组 $\textit{balance}$ 的长度。如果所有人的余额之和为负，那么无论如何操作都无法使每个人的余额非负，直接返回 $-1$。

否则，我们可以将问题建模为**最小费用流**问题：

- 建立源点 $s$ 和汇点 $t$；
- 对于每个 $\textit{balance}[i] > 0$ 的人 $i$（盈余者），从 $s$ 向 $i$ 连一条容量为 $\textit{balance}[i]$、单位费用为 $0$ 的边；
- 对于每个 $\textit{balance}[i] < 0$ 的人 $i$（亏空者），从 $i$ 向 $t$ 连一条容量为 $-\textit{balance}[i]$、单位费用为 $0$ 的边；
- 对于每个 $i$，从 $i$ 向其左右两个相邻的人各连一条容量为无穷大、单位费用为 $1$ 的边，表示每向相邻的人转移 $1$ 单位余额需要 $1$ 次操作。

记总亏空 $\textit{totalDeficit} = \sum_{\textit{balance}[i] < 0} (-\textit{balance}[i])$，那么答案就是从 $s$ 向 $t$ 输送 $\textit{totalDeficit}$ 单位流量的最小费用。由于环形边是双向连通的，只要余额总和非负，这些流量必然可以全部送达。我们使用基于 SPFA 的连续最短路增广算法求解最小费用流。

注意每次增广都会沿最短路径一次推完瓶颈流量，而不是只推 $1$ 个单位：瓶颈边要么是与源点或汇点相连的边（该边被耗尽），要么是环形边的反向边（对应正向边上的存量流量被全部改道）。因此增广轮数与余额的具体数值无关，在本题数据规模下为 $O(n)$ 量级。每次增广使用 SPFA 求最短增广路，最坏时间复杂度为 $O(VE)$，其中 $V = n + 2$，$E = O(n)$。

时间复杂度最坏 $O(n^3)$，空间复杂度 $O(n)$。需要说明的是，$O(n^3)$ 是非常保守的上界：一方面增广轮数实际只有 $O(n)$ 量级；另一方面本题中的图是单位费用的环图，SPFA 在其上的行为接近 BFS，每个节点平均只出队常数次，单轮增广实际接近 $O(n)$。因此总运算量实际约为 $O(n^2)$ 量级，$n = 1000$ 时仅约 $10^7$ 次简单运算，足以通过本题。若希望获得严格可证的上界，可以将 SPFA 换成 Dijkstra 配合 Johnson 势函数，时间复杂度为 $O(n^2 \log n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, balance: List[int]) -> int:
        total_balance = sum(balance)
        if total_balance < 0:
            return -1

        n = len(balance)
        total_deficit = sum(-x for x in balance if x < 0)
        if total_deficit == 0:
            return 0

        source = n
        sink = n + 1
        num_nodes = n + 2

        graph = [[] for _ in range(num_nodes)]

        def add_edge(u, v, cap, cost):
            graph[u].append([v, cap, cost, len(graph[v])])
            graph[v].append([u, 0, -cost, len(graph[u]) - 1])

        for i in range(n):
            if balance[i] > 0:
                add_edge(source, i, balance[i], 0)
            elif balance[i] < 0:
                add_edge(i, sink, -balance[i], 0)

            add_edge(i, (i + 1) % n, inf, 1)
            add_edge(i, (i - 1 + n) % n, inf, 1)

        total_cost = 0
        current_flow = 0

        while current_flow < total_deficit:
            dist = [inf] * num_nodes
            parent_node = [-1] * num_nodes
            parent_edge = [-1] * num_nodes
            in_queue = [False] * num_nodes

            queue = deque([source])
            dist[source] = 0
            in_queue[source] = True

            while queue:
                u = queue.popleft()
                in_queue[u] = False

                for idx, (v, cap, cost, _) in enumerate(graph[u]):
                    if cap > 0 and dist[v] > dist[u] + cost:
                        dist[v] = dist[u] + cost
                        parent_node[v] = u
                        parent_edge[v] = idx
                        if not in_queue[v]:
                            queue.append(v)
                            in_queue[v] = True

            if dist[sink] == inf:
                break

            push_flow = total_deficit - current_flow
            curr = sink
            while curr != source:
                p = parent_node[curr]
                idx = parent_edge[curr]
                push_flow = min(push_flow, graph[p][idx][1])
                curr = p

            curr = sink
            while curr != source:
                p = parent_node[curr]
                idx = parent_edge[curr]
                rev_idx = graph[p][idx][3]
                graph[p][idx][1] -= push_flow
                graph[curr][rev_idx][1] += push_flow
                curr = p

            current_flow += push_flow
            total_cost += push_flow * dist[sink]

        return total_cost if current_flow == total_deficit else -1
```

#### Java

```java
class MinCostMaxFlow {

    static class Edge {
        int to;
        int cap;
        int cost;
        int rev;

        Edge(int to, int cap, int cost, int rev) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }
    }

    private static final int INF = 1 << 29;

    private final int n;
    private final List<Edge>[] graph;

    public MinCostMaxFlow(int n) {
        this.n = n;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int cap, int cost) {
        graph[u].add(new Edge(v, cap, cost, graph[v].size()));
        graph[v].add(new Edge(u, 0, -cost, graph[u].size() - 1));
    }

    public long minCostFlow(int source, int sink, int maxFlow) {
        long totalCost = 0;
        int currentFlow = 0;

        while (currentFlow < maxFlow) {
            int[] dist = new int[n];
            Arrays.fill(dist, INF);

            int[] parentNode = new int[n];
            int[] parentEdge = new int[n];
            boolean[] inQueue = new boolean[n];

            Arrays.fill(parentNode, -1);
            Arrays.fill(parentEdge, -1);

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(source);
            dist[source] = 0;
            inQueue[source] = true;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                inQueue[u] = false;

                for (int i = 0; i < graph[u].size(); i++) {
                    Edge e = graph[u].get(i);
                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            queue.offer(e.to);
                        }
                    }
                }
            }

            if (dist[sink] == INF) {
                return -1;
            }

            int pushFlow = maxFlow - currentFlow;

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge e = graph[parentNode[cur]].get(parentEdge[cur]);
                pushFlow = Math.min(pushFlow, e.cap);
            }

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge e = graph[parentNode[cur]].get(parentEdge[cur]);
                e.cap -= pushFlow;
                graph[cur].get(e.rev).cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += 1L * pushFlow * dist[sink];
        }

        return totalCost;
    }
}

class Solution {

    public long minMoves(int[] balance) {
        int totalBalance = 0;
        int totalDeficit = 0;

        for (int x : balance) {
            totalBalance += x;
            if (x < 0) {
                totalDeficit += -x;
            }
        }

        if (totalBalance < 0) {
            return -1;
        }

        if (totalDeficit == 0) {
            return 0;
        }

        int n = balance.length;
        int source = n;
        int sink = n + 1;
        int INF = 1 << 29;

        MinCostMaxFlow mcmf = new MinCostMaxFlow(n + 2);

        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) {
                mcmf.addEdge(source, i, balance[i], 0);
            } else if (balance[i] < 0) {
                mcmf.addEdge(i, sink, -balance[i], 0);
            }

            mcmf.addEdge(i, (i + 1) % n, INF, 1);
            mcmf.addEdge(i, (i - 1 + n) % n, INF, 1);
        }

        return mcmf.minCostFlow(source, sink, totalDeficit);
    }
}
```

#### C++

```cpp
class MinCostMaxFlow {
public:
    struct Edge {
        int to, cap, cost, rev;

        Edge(int to, int cap, int cost, int rev)
            : to(to)
            , cap(cap)
            , cost(cost)
            , rev(rev) {}
    };

    static constexpr int INF = 1e9;

    int n;
    vector<vector<Edge>> graph;

    MinCostMaxFlow(int n)
        : n(n)
        , graph(n) {}

    void addEdge(int u, int v, int cap, int cost) {
        graph[u].emplace_back(v, cap, cost, graph[v].size());
        graph[v].emplace_back(u, 0, -cost, graph[u].size() - 1);
    }

    long long minCostFlow(int source, int sink, int maxFlow) {
        long long totalCost = 0;
        int currentFlow = 0;

        while (currentFlow < maxFlow) {
            vector<int> dist(n, INF);
            vector<int> parentNode(n, -1);
            vector<int> parentEdge(n, -1);
            vector<bool> inQueue(n, false);

            queue<int> q;
            q.push(source);
            dist[source] = 0;
            inQueue[source] = true;

            while (!q.empty()) {
                int u = q.front();
                q.pop();
                inQueue[u] = false;

                for (int i = 0; i < graph[u].size(); i++) {
                    Edge& e = graph[u][i];
                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            q.push(e.to);
                        }
                    }
                }
            }

            if (dist[sink] == INF) {
                return -1;
            }

            int pushFlow = maxFlow - currentFlow;

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge& e = graph[parentNode[cur]][parentEdge[cur]];
                pushFlow = min(pushFlow, e.cap);
            }

            for (int cur = sink; cur != source; cur = parentNode[cur]) {
                Edge& e = graph[parentNode[cur]][parentEdge[cur]];
                e.cap -= pushFlow;
                graph[cur][e.rev].cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += 1LL * pushFlow * dist[sink];
        }

        return totalCost;
    }
};

class Solution {
public:
    long long minMoves(vector<int>& balance) {
        int totalBalance = accumulate(balance.begin(), balance.end(), 0);
        if (totalBalance < 0) {
            return -1;
        }

        int totalDeficit = 0;
        for (int x : balance) {
            if (x < 0) {
                totalDeficit += -x;
            }
        }

        if (totalDeficit == 0) {
            return 0;
        }

        int n = balance.size();
        int source = n;
        int sink = n + 1;

        MinCostMaxFlow mcmf(n + 2);

        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) {
                mcmf.addEdge(source, i, balance[i], 0);
            } else if (balance[i] < 0) {
                mcmf.addEdge(i, sink, -balance[i], 0);
            }

            mcmf.addEdge(i, (i + 1) % n, MinCostMaxFlow::INF, 1);
            mcmf.addEdge(i, (i - 1 + n) % n, MinCostMaxFlow::INF, 1);
        }

        return mcmf.minCostFlow(source, sink, totalDeficit);
    }
};
```

#### Go

```go
type Edge struct {
	to   int
	cap  int
	cost int
	rev  int
}

type MinCostMaxFlow struct {
	n     int
	graph [][]Edge
}

const INF = int(1e9)

func NewMinCostMaxFlow(n int) *MinCostMaxFlow {
	return &MinCostMaxFlow{
		n:     n,
		graph: make([][]Edge, n),
	}
}

func (m *MinCostMaxFlow) AddEdge(u, v, cap, cost int) {
	m.graph[u] = append(m.graph[u], Edge{
		to:   v,
		cap:  cap,
		cost: cost,
		rev:  len(m.graph[v]),
	})
	m.graph[v] = append(m.graph[v], Edge{
		to:   u,
		cap:  0,
		cost: -cost,
		rev:  len(m.graph[u]) - 1,
	})
}

func (m *MinCostMaxFlow) MinCostFlow(source, sink, maxFlow int) int64 {
	var totalCost int64
	currentFlow := 0

	for currentFlow < maxFlow {
		dist := make([]int, m.n)
		parentNode := make([]int, m.n)
		parentEdge := make([]int, m.n)
		inQueue := make([]bool, m.n)

		for i := 0; i < m.n; i++ {
			dist[i] = INF
			parentNode[i] = -1
			parentEdge[i] = -1
		}

		queue := []int{source}
		head := 0
		dist[source] = 0
		inQueue[source] = true

		for head < len(queue) {
			u := queue[head]
			head++
			inQueue[u] = false

			for i, e := range m.graph[u] {
				if e.cap > 0 && dist[e.to] > dist[u]+e.cost {
					dist[e.to] = dist[u] + e.cost
					parentNode[e.to] = u
					parentEdge[e.to] = i

					if !inQueue[e.to] {
						inQueue[e.to] = true
						queue = append(queue, e.to)
					}
				}
			}
		}

		if dist[sink] == INF {
			return -1
		}

		pushFlow := maxFlow - currentFlow

		for cur := sink; cur != source; cur = parentNode[cur] {
			e := &m.graph[parentNode[cur]][parentEdge[cur]]
			if e.cap < pushFlow {
				pushFlow = e.cap
			}
		}

		for cur := sink; cur != source; cur = parentNode[cur] {
			p := parentNode[cur]
			idx := parentEdge[cur]
			rev := m.graph[p][idx].rev

			m.graph[p][idx].cap -= pushFlow
			m.graph[cur][rev].cap += pushFlow
		}

		currentFlow += pushFlow
		totalCost += int64(pushFlow * dist[sink])
	}

	return totalCost
}

func minMoves(balance []int) int64 {
	totalBalance := 0
	totalDeficit := 0

	for _, x := range balance {
		totalBalance += x
		if x < 0 {
			totalDeficit += -x
		}
	}

	if totalBalance < 0 {
		return -1
	}

	if totalDeficit == 0 {
		return 0
	}

	n := len(balance)
	source := n
	sink := n + 1

	mcmf := NewMinCostMaxFlow(n + 2)

	for i, x := range balance {
		if x > 0 {
			mcmf.AddEdge(source, i, x, 0)
		} else if x < 0 {
			mcmf.AddEdge(i, sink, -x, 0)
		}

		mcmf.AddEdge(i, (i+1)%n, INF, 1)
		mcmf.AddEdge(i, (i-1+n)%n, INF, 1)
	}

	return mcmf.MinCostFlow(source, sink, totalDeficit)
}
```

#### TypeScript

```ts
class Edge {
    to: number;
    cap: number;
    cost: number;
    rev: number;

    constructor(to: number, cap: number, cost: number, rev: number) {
        this.to = to;
        this.cap = cap;
        this.cost = cost;
        this.rev = rev;
    }
}

class MinCostMaxFlow {
    private n: number;
    private graph: Edge[][];

    static readonly INF = 1e9;

    constructor(n: number) {
        this.n = n;
        this.graph = Array.from({ length: n }, () => []);
    }

    addEdge(u: number, v: number, cap: number, cost: number): void {
        this.graph[u].push(new Edge(v, cap, cost, this.graph[v].length));

        this.graph[v].push(new Edge(u, 0, -cost, this.graph[u].length - 1));
    }

    minCostFlow(source: number, sink: number, maxFlow: number): number {
        let totalCost = 0;
        let currentFlow = 0;

        while (currentFlow < maxFlow) {
            const dist = new Array<number>(this.n).fill(MinCostMaxFlow.INF);

            const parentNode = new Array<number>(this.n).fill(-1);

            const parentEdge = new Array<number>(this.n).fill(-1);

            const inQueue = new Array<boolean>(this.n).fill(false);

            const queue: number[] = [];

            queue.push(source);
            dist[source] = 0;
            inQueue[source] = true;

            let head = 0;

            while (head < queue.length) {
                const u = queue[head++];
                inQueue[u] = false;

                for (let i = 0; i < this.graph[u].length; i++) {
                    const e = this.graph[u][i];

                    if (e.cap > 0 && dist[e.to] > dist[u] + e.cost) {
                        dist[e.to] = dist[u] + e.cost;
                        parentNode[e.to] = u;
                        parentEdge[e.to] = i;

                        if (!inQueue[e.to]) {
                            inQueue[e.to] = true;
                            queue.push(e.to);
                        }
                    }
                }
            }

            if (dist[sink] === MinCostMaxFlow.INF) {
                return -1;
            }

            let pushFlow = maxFlow - currentFlow;

            for (let cur = sink; cur !== source; cur = parentNode[cur]) {
                const e = this.graph[parentNode[cur]][parentEdge[cur]];
                pushFlow = Math.min(pushFlow, e.cap);
            }

            for (let cur = sink; cur !== source; cur = parentNode[cur]) {
                const p = parentNode[cur];
                const idx = parentEdge[cur];

                const e = this.graph[p][idx];

                e.cap -= pushFlow;
                this.graph[cur][e.rev].cap += pushFlow;
            }

            currentFlow += pushFlow;
            totalCost += pushFlow * dist[sink];
        }

        return totalCost;
    }
}

function minMoves(balance: number[]): number {
    let totalBalance = 0;
    let totalDeficit = 0;

    for (const x of balance) {
        totalBalance += x;
        if (x < 0) {
            totalDeficit += -x;
        }
    }

    if (totalBalance < 0) {
        return -1;
    }

    if (totalDeficit === 0) {
        return 0;
    }

    const n = balance.length;

    const source = n;
    const sink = n + 1;

    const mcmf = new MinCostMaxFlow(n + 2);

    for (let i = 0; i < n; i++) {
        if (balance[i] > 0) {
            mcmf.addEdge(source, i, balance[i], 0);
        } else if (balance[i] < 0) {
            mcmf.addEdge(i, sink, -balance[i], 0);
        }

        mcmf.addEdge(i, (i + 1) % n, MinCostMaxFlow.INF, 1);

        mcmf.addEdge(i, (i - 1 + n) % n, MinCostMaxFlow.INF, 1);
    }

    return mcmf.minCostFlow(source, sink, totalDeficit);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
