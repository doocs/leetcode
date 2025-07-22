func smallestChair(times [][]int, targetFriend int) int {
	idle := hp{}
	busy := hp2{}
	for i := range times {
		times[i] = append(times[i], i)
		heap.Push(&idle, i)
	}
	sort.Slice(times, func(i, j int) bool { return times[i][0] < times[j][0] })
	for _, e := range times {
		arrival, leaving, i := e[0], e[1], e[2]
		for len(busy) > 0 && busy[0].t <= arrival {
			heap.Push(&idle, heap.Pop(&busy).(pair).i)
		}
		j := heap.Pop(&idle).(int)
		if i == targetFriend {
			return j
		}
		heap.Push(&busy, pair{leaving, j})
	}
	return -1
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ t, i int }
type hp2 []pair

func (h hp2) Len() int           { return len(h) }
func (h hp2) Less(i, j int) bool { return h[i].t < h[j].t || (h[i].t == h[j].t && h[i].i < h[j].i) }
func (h hp2) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
