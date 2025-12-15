func minCostExcludingMax(n int, edges [][]int) int64 {
	g := make([][]edge, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], edge{v, w})
		g[v] = append(g[v], edge{u, w})
	}

	inf := int64(math.MaxInt64 / 4)
	dist := make([][2]int64, n)
	for i := 0; i < n; i++ {
		dist[i][0] = inf
		dist[i][1] = inf
	}
	dist[0][0] = 0

	pq := hp{{0, 0, 0}}
	for len(pq) > 0 {
		t := heap.Pop(&pq).(state)
		cur, u, used := t.cur, t.u, t.used
		if cur > dist[u][used] {
			continue
		}
		if u == n-1 && used == 1 {
			return cur
		}
		for _, ed := range g[u] {
			v, w := ed.to, int64(ed.w)

			nxt := cur + w
			if nxt < dist[v][used] {
				dist[v][used] = nxt
				heap.Push(&pq, state{nxt, v, used})
			}

			if used == 0 {
				nxt = cur
				if nxt < dist[v][1] {
					dist[v][1] = nxt
					heap.Push(&pq, state{nxt, v, 1})
				}
			}
		}
	}
	return dist[n-1][1]
}

type edge struct {
	to int
	w  int
}

type state struct {
	cur  int64
	u    int
	used int
}

type hp []state

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cur < h[j].cur }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(state)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
