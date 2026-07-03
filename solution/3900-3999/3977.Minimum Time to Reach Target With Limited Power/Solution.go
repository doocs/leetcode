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
