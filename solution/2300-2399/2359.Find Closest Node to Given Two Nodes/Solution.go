func closestMeetingNode(edges []int, node1 int, node2 int) int {
	n := len(edges)
	g := make([][]int, n)
	for i, j := range edges {
		if j != -1 {
			g[i] = append(g[i], j)
		}
	}
	const inf int = 1 << 30
	dijkstra := func(i int) []int {
		dist := make([]int, n)
		for j := range dist {
			dist[j] = inf
		}
		dist[i] = 0
		q := hp{}
		heap.Push(&q, pair{0, i})
		for len(q) > 0 {
			i := heap.Pop(&q).(pair).i
			for _, j := range g[i] {
				if dist[j] > dist[i]+1 {
					dist[j] = dist[i] + 1
					heap.Push(&q, pair{dist[j], j})
				}
			}
		}
		return dist
	}
	d1 := dijkstra(node1)
	d2 := dijkstra(node2)
	ans, d := -1, inf
	for i, a := range d1 {
		b := d2[i]
		t := max(a, b)
		if t < d {
			d = t
			ans = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type pair struct{ d, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].d < h[j].d }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }