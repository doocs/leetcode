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
