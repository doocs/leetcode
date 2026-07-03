type Item struct {
	d int
	u int
}

type H []Item

func (h H) Len() int            { return len(h) }
func (h H) Less(i, j int) bool  { return h[i].d < h[j].d }
func (h H) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *H) Push(x any)         { *h = append(*h, x.(Item)) }
func (h *H) Pop() any           { x := (*h)[len(*h)-1]; *h = (*h)[:len(*h)-1]; return x }

func findMaxPathScore(edges [][]int, online []bool, k int64) int {
	n := len(online)
	g := make([][][]int, n)

	l, r := int(^uint(0)>>1), 0

	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		if !online[u] || !online[v] {
			continue
		}
		g[u] = append(g[u], []int{v, w})
		if w < l {
			l = w
		}
		if w > r {
			r = w
		}
	}

	check := func(mid int) bool {
		const INF = int(^uint(0) >> 1)

		dist := make([]int, n)
		for i := range dist {
			dist[i] = INF
		}
		dist[0] = 0

		h := &H{}
		heap.Push(h, Item{0, 0})

		for h.Len() > 0 {
			cur := heap.Pop(h).(Item)
			d, u := cur.d, cur.u

			if int64(d) > k {
				return false
			}
			if u == n-1 {
				return true
			}
			if dist[u] < d {
				continue
			}

			for _, e := range g[u] {
				v, w := e[0], e[1]
				if w < mid {
					continue
				}
				nd := d + w
				if nd < dist[v] {
					dist[v] = nd
					heap.Push(h, Item{nd, v})
				}
			}
		}

		return false
	}

	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}

	if check(l) {
		return l
	}
	return -1
}