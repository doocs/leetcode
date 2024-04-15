func minimumTime(n int, edges [][]int, disappear []int) []int {
	g := make([][]pair, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], pair{v, w})
		g[v] = append(g[v], pair{u, w})
	}

	dist := make([]int, n)
	for i := range dist {
		dist[i] = 1 << 30
	}
	dist[0] = 0

	pq := hp{{0, 0}}

	for len(pq) > 0 {
		du, u := pq[0].dis, pq[0].u
		heap.Pop(&pq)

		if du > dist[u] {
			continue
		}

		for _, nxt := range g[u] {
			v, w := nxt.dis, nxt.u
			if dist[v] > dist[u]+w && dist[u]+w < disappear[v] {
				dist[v] = dist[u] + w
				heap.Push(&pq, pair{dist[v], v})
			}
		}
	}

	ans := make([]int, n)
	for i := 0; i < n; i++ {
		if dist[i] < disappear[i] {
			ans[i] = dist[i]
		} else {
			ans[i] = -1
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