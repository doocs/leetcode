func assignTasks(servers []int, tasks []int) (ans []int) {
	idle := hp{}
	busy := hp2{}
	for i, x := range servers {
		heap.Push(&idle, pair{x, i})
	}
	for j, t := range tasks {
		for len(busy) > 0 && busy[0].w <= j {
			p := heap.Pop(&busy).(tuple)
			heap.Push(&idle, pair{p.s, p.i})
		}
		if idle.Len() > 0 {
			p := heap.Pop(&idle).(pair)
			ans = append(ans, p.i)
			heap.Push(&busy, tuple{j + t, p.s, p.i})
		} else {
			p := heap.Pop(&busy).(tuple)
			ans = append(ans, p.i)
			heap.Push(&busy, tuple{p.w + t, p.s, p.i})
		}
	}
	return
}

type pair struct {
	s int
	i int
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.s < b.s || a.s == b.s && a.i < b.i
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

type tuple struct {
	w int
	s int
	i int
}

type hp2 []tuple

func (h hp2) Len() int { return len(h) }
func (h hp2) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.w < b.w || a.w == b.w && (a.s < b.s || a.s == b.s && a.i < b.i)
}
func (h hp2) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)   { *h = append(*h, v.(tuple)) }
func (h *hp2) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
