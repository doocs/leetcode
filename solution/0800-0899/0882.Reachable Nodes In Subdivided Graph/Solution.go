func reachableNodes(edges [][]int, maxMoves int, n int) (ans int) {
	g := make([][]pair, n)
	for _, e := range edges {
		u, v, cnt := e[0], e[1], e[2]+1
		g[u] = append(g[u], pair{cnt, v})
		g[v] = append(g[v], pair{cnt, u})
	}
	dist := make([]int, n)
	for i := range dist {
		dist[i] = 1 << 30
	}
	dist[0] = 0
	q := hp{{0, 0}}
	for len(q) > 0 {
		p := heap.Pop(&q).(pair)
		d, u := p.v, p.i
		for _, nxt := range g[u] {
			v, cnt := nxt.i, nxt.v
			if t := d + cnt; t < dist[v] {
				dist[v] = t
				heap.Push(&q, pair{t, v})
			}
		}
	}
	for _, d := range dist {
		if d <= maxMoves {
			ans++
		}
	}
	for _, e := range edges {
		u, v, cnt := e[0], e[1], e[2]
		a := min(cnt, max(0, maxMoves-dist[u]))
		b := min(cnt, max(0, maxMoves-dist[v]))
		ans += min(cnt, a+b)
	}
	return
}

type pair struct{ v, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}