func findMaximizedCapital(k int, w int, profits []int, capital []int) int {
	q1 := hp2{}
	for i, c := range capital {
		heap.Push(&q1, pair{c, profits[i]})
	}
	q2 := hp{}
	for k > 0 {
		for len(q1) > 0 && q1[0].c <= w {
			heap.Push(&q2, heap.Pop(&q1).(pair).p)
		}
		if q2.Len() == 0 {
			break
		}
		w += heap.Pop(&q2).(int)
		k--
	}
	return w
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ c, p int }
type hp2 []pair

func (h hp2) Len() int            { return len(h) }
func (h hp2) Less(i, j int) bool  { return h[i].c < h[j].c }
func (h hp2) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }