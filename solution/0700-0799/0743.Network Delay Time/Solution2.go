func networkDelayTime(times [][]int, n int, k int) int {
	g := make([][][2]int, n)
	for _, e := range times {
		u, v, w := e[0]-1, e[1]-1, e[2]
		g[u] = append(g[u], [2]int{v, w})
	}
	dist := make([]int, n)
	const inf int = 1 << 29
	for i := range dist {
		dist[i] = inf
	}
	dist[k-1] = 0
	pq := hp{{0, k - 1}}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		d, u := p.x, p.i
		if d > dist[u] {
			continue
		}
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if nd := d + w; nd < dist[v] {
				dist[v] = nd
				heap.Push(&pq, pair{nd, v})
			}
		}
	}
	if ans := slices.Max(dist); ans < inf {
		return ans
	}
	return -1

}

type pair struct{ x, i int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].x < h[j].x }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(pair)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
