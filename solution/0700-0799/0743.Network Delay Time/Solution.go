const Inf = 0x3f3f3f3f

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

func networkDelayTime(times [][]int, n int, k int) int {
	graph := make([]pairs, n)
	for _, time := range times {
		from, to, time := time[0]-1, time[1]-1, time[2]
		graph[from] = append(graph[from], pair{to, time})
	}

	dis := make([]int, n)
	for i := range dis {
		dis[i] = Inf
	}
	dis[k-1] = 0

	vis := make([]bool, n)
	h := make(pairs, 0)
	heap.Push(&h, pair{0, k - 1})
	for len(h) > 0 {
		from := heap.Pop(&h).(pair).second
		if vis[from] {
			continue
		}
		vis[from] = true
		for _, e := range graph[from] {
			to, d := e.first, dis[from]+e.second
			if d < dis[to] {
				dis[to] = d
				heap.Push(&h, pair{d, to})
			}
		}
	}

	ans := math.MinInt32
	for _, d := range dis {
		ans = max(ans, d)
	}
	if ans == Inf {
		return -1
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
