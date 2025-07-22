func maxProbability(n int, edges [][]int, succProb []float64, start_node int, end_node int) float64 {
	g := make([][]pair, n)
	for i, e := range edges {
		a, b := e[0], e[1]
		p := succProb[i]
		g[a] = append(g[a], pair{p, b})
		g[b] = append(g[b], pair{p, a})
	}
	pq := hp{{1, start_node}}
	dist := make([]float64, n)
	dist[start_node] = 1
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		w, a := p.p, p.a
		if dist[a] > w {
			continue
		}
		for _, e := range g[a] {
			b, p := e.a, e.p
			if nw := w * p; nw > dist[b] {
				dist[b] = nw
				heap.Push(&pq, pair{nw, b})
			}
		}
	}
	return dist[end_node]
}

type pair struct {
	p float64
	a int
}
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].p > h[j].p }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(pair)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
