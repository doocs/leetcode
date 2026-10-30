func shortestPath(n int, edges [][]int, labels string, k int) int {
	g := make([][][2]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], [2]int{e[1], e[2]})
	}

	const inf = int(^uint(0) >> 1)
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, k+1)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}

	dist[0][1] = 0
	pq := &hp{{0, 0, 1}}
	heap.Init(pq)

	for pq.Len() > 0 {
		p := heap.Pop(pq).(tuple)
		d, u, cur := p.d, p.u, p.cur

		if u == n-1 {
			return d
		}
		if d > dist[u][cur] {
			continue
		}

		for _, e := range g[u] {
			v, w := e[0], e[1]
			nxt := cur + 1
			if labels[v] != labels[u] {
				nxt = 1
			}
			nd := d + w
			if nxt <= k && nd < dist[v][nxt] {
				dist[v][nxt] = nd
				heap.Push(pq, tuple{nd, v, nxt})
			}
		}
	}
	return -1
}

type tuple struct {
	d, u, cur int
}

type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].d < h[j].d }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any {
	a := *h
	v := a[len(a)-1]
	*h = a[:len(a)-1]
	return v
}
