func findAnswer(n int, edges [][]int) []bool {
	g := make([][][3]int, n)
	for i, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [3]int{b, w, i})
		g[b] = append(g[b], [3]int{a, w, i})
	}
	dist := make([]int, n)
	const inf int = 1 << 30
	for i := range dist {
		dist[i] = inf
	}
	dist[0] = 0
	pq := hp{{0, 0}}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		da, a := p.dis, p.u
		if da > dist[a] {
			continue
		}
		for _, e := range g[a] {
			b, w := e[0], e[1]
			if dist[b] > dist[a]+w {
				dist[b] = dist[a] + w
				heap.Push(&pq, pair{dist[b], b})
			}
		}
	}
	ans := make([]bool, len(edges))
	if dist[n-1] == inf {
		return ans
	}
	q := []int{n - 1}
	for len(q) > 0 {
		a := q[0]
		q = q[1:]
		for _, e := range g[a] {
			b, w, i := e[0], e[1], e[2]
			if dist[a] == dist[b]+w {
				ans[i] = true
				q = append(q, b)
			}
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