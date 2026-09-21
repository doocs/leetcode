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
