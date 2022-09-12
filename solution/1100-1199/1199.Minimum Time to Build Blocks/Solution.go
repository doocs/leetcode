func minBuildTime(blocks []int, split int) int {
	q := hp{}
	for _, v := range blocks {
		heap.Push(&q, v)
	}
	for q.Len() > 1 {
		heap.Pop(&q)
		heap.Push(&q, heap.Pop(&q).(int)+split)
	}
	return q.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}