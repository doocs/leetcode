func maxEvents(events [][]int) (ans int) {
	g := map[int][]int{}
	l, r := math.MaxInt32, 0
	for _, event := range events {
		s, e := event[0], event[1]
		g[s] = append(g[s], e)
		l = min(l, s)
		r = max(r, e)
	}

	pq := &hp{}
	heap.Init(pq)
	for s := l; s <= r; s++ {
		for pq.Len() > 0 && pq.IntSlice[0] < s {
			heap.Pop(pq)
		}
		for _, e := range g[s] {
			heap.Push(pq, e)
		}
		if pq.Len() > 0 {
			heap.Pop(pq)
			ans++
		}
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	n := len(h.IntSlice)
	v := h.IntSlice[n-1]
	h.IntSlice = h.IntSlice[:n-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
