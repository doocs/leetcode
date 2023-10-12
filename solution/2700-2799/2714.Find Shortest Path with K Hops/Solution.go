func shortestPathWithHops(n int, edges [][]int, s int, d int, k int) int {
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
	pq := hp{{0, s, 0}}
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, k+1)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[s][0] = 0
	for len(pq) > 0 {
		p := heap.Pop(&pq).(tuple)
		dis, u, t := p.dis, p.u, p.t
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if t+1 <= k && dist[v][t+1] > dis {
				dist[v][t+1] = dis
				heap.Push(&pq, tuple{dis, v, t + 1})
			}
			if dist[v][t] > dis+w {
				dist[v][t] = dis + w
				heap.Push(&pq, tuple{dis + w, v, t})
			}
		}
	}
	ans := math.MaxInt32
	for i := 0; i <= k; i++ {
		ans = min(ans, dist[d][i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

type tuple struct{ dis, u, t int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }