func minCost(n int, roads [][]int, appleCost []int, k int) []int64 {
	g := make([]pairs, n)
	for _, e := range roads {
		a, b, c := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, c})
		g[b] = append(g[b], pair{a, c})
	}
	const inf int = 0x3f3f3f3f
	dist := make([]int, n)
	dijkstra := func(u int) int64 {
		var ans int64 = math.MaxInt64
		for i := range dist {
			dist[i] = inf
		}
		dist[u] = 0
		q := make(pairs, 0)
		heap.Push(&q, pair{0, u})
		for len(q) > 0 {
			p := heap.Pop(&q).(pair)
			d := p.first
			u = p.second
			ans = min(ans, int64(appleCost[u]+d*(k+1)))
			for _, ne := range g[u] {
				v, w := ne.first, ne.second
				if dist[v] > dist[u]+w {
					dist[v] = dist[u] + w
					heap.Push(&q, pair{dist[v], v})
				}
			}
		}
		return ans
	}
	ans := make([]int64, n)
	for i := range ans {
		ans[i] = dijkstra(i)
	}
	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

type pair struct{ first, second int }

var _ heap.Interface = (*pairs)(nil)

type pairs []pair

func (a pairs) Len() int { return len(a) }
func (a pairs) Less(i int, j int) bool {
	return a[i].first < a[j].first || a[i].first == a[j].first && a[i].second < a[j].second
}
func (a pairs) Swap(i int, j int)   { a[i], a[j] = a[j], a[i] }
func (a *pairs) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *pairs) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }