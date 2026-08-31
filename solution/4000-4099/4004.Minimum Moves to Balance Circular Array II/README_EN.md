---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README_EN.md
tags:
    - Graph
    - Array
    - Math
    - Min-Cost Flow
---

<!-- problem:start -->

# [4004. Minimum Moves to Balance Circular Array II 🔒](https://leetcode.com/problems/minimum-moves-to-balance-circular-array-ii)

[中文文档](/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <span data-keyword="circular-array">circular array</span> <code>balance</code> of length <code>n</code>, where <code>balance[i]</code> is the net balance of person <code>i</code>.</p>

<p>In one move, a person can transfer <strong>exactly</strong> 1 unit of balance to either their left or right neighbor.</p>

<p>Return the <strong>minimum</strong> number of moves required so that every person has a <strong>non-negative</strong> balance. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [-1,2,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 0</code>, resulting in <code>balance = [0, 1, -1]</code></li>
	<li>Move 1 unit from <code>i = 1</code> to <code>i = 2</code>, resulting in <code>balance = [0, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [4,-1,-2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal sequence of moves is:</p>

<ul>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 1</code>, resulting in <code>balance = [3, 0, -2]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [2, 0, -1]</code></li>
	<li>Move 1 unit from <code>i = 0</code> to <code>i = 2</code>, resulting in <code>balance = [1, 0, 0]</code></li>
</ul>

<p>Thus, the minimum number of moves required is 3.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">balance = [-3,-3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make all balances non-negative for <code>balance = [-3, -3, 5]</code>, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == balance.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= balance[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Minimum Cost Maximum Flow

Let $n$ be the length of $\textit{balance}$. If the sum of all balances is negative, it is impossible to make everyone's balance non-negative, so we return $-1$ directly.

Otherwise, we model the problem as a **minimum cost flow** problem:

- Create a source $s$ and a sink $t$;
- For each person $i$ with $\textit{balance}[i] > 0$ (a surplus), add an edge from $s$ to $i$ with capacity $\textit{balance}[i]$ and unit cost $0$;
- For each person $i$ with $\textit{balance}[i] < 0$ (a deficit), add an edge from $i$ to $t$ with capacity $-\textit{balance}[i]$ and unit cost $0$;
- For each $i$, add an edge from $i$ to each of its two neighbors with infinite capacity and unit cost $1$, representing that transferring $1$ unit of balance to a neighbor takes $1$ move.

Let $\textit{totalDeficit} = \sum_{\textit{balance}[i] < 0} (-\textit{balance}[i])$ be the total deficit. The answer is the minimum cost of sending $\textit{totalDeficit}$ units of flow from $s$ to $t$. Since the circular edges connect everyone in both directions, all the required flow can always be delivered as long as the total balance is non-negative. We use SPFA-based successive shortest path augmentation to solve the minimum cost flow problem.

Note that each augmentation pushes the entire bottleneck flow along a shortest path instead of just $1$ unit: the bottleneck edge is either an edge connected to the source or the sink (which then gets saturated), or a reverse circular edge (which reroutes all the existing flow on the corresponding forward edge). Hence the number of augmentations does not depend on the magnitudes of the balances and stays on the order of $O(n)$ for the constraints of this problem. Each augmentation uses SPFA to find a shortest augmenting path, which takes $O(VE)$ time in the worst case, where $V = n + 2$ and $E = O(n)$.

The time complexity is $O(n^3)$ in the worst case, and the space complexity is $O(n)$. Note that $O(n^3)$ is a very conservative bound: on the one hand, there are only $O(n)$ augmentations in practice; on the other hand, the graph in this problem is a unit-cost cycle, on which SPFA behaves almost like BFS — each node is dequeued only a constant number of times on average, so one augmentation actually costs about $O(n)$. The total amount of work is therefore about $O(n^2)$ in practice, roughly $10^7$ simple operations when $n = 1000$, which is fast enough to pass. For a strictly provable bound, SPFA can be replaced by Dijkstra's algorithm with Johnson's potentials, giving $O(n^2 \log n)$ time.

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
