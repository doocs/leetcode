const inf = math.MaxInt32
const mod = 1e9 + 7

type pair struct {
	first  int
	second int
}

var _ heap.Interface = (*pairs)(nil)

type pairs []pair

func (a pairs) Len() int { return len(a) }
func (a pairs) Less(i int, j int) bool {
	return a[i].first < a[j].first || a[i].first == a[j].first && a[i].second < a[j].second
}
func (a pairs) Swap(i int, j int)   { a[i], a[j] = a[j], a[i] }
func (a *pairs) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *pairs) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

func countRestrictedPaths(n int, edges [][]int) int {
	g := make([]pairs, n+1)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], pair{v, w})
		g[v] = append(g[v], pair{u, w})
	}
	dist := make([]int, n+1)
	f := make([]int, n+1)
	for i := range dist {
		dist[i] = inf
		f[i] = -1
	}
	dist[n] = 0
	h := make(pairs, 0)
	heap.Push(&h, pair{0, n})
	for len(h) > 0 {
		u := heap.Pop(&h).(pair).second
		for _, ne := range g[u] {
			v, w := ne.first, ne.second
			if dist[v] > dist[u]+w {
				dist[v] = dist[u] + w
				heap.Push(&h, pair{dist[v], v})
			}
		}
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] != -1 {
			return f[i]
		}
		if i == n {
			return 1
		}
		ans := 0
		for _, ne := range g[i] {
			j := ne.first
			if dist[i] > dist[j] {
				ans = (ans + dfs(j)) % mod
			}
		}
		f[i] = ans
		return ans
	}
	return dfs(1)
}