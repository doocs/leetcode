func connectSticks(sticks []int) (ans int) {
	hp := &hp{sticks}
	heap.Init(hp)
	for hp.Len() > 1 {
		x, y := heap.Pop(hp).(int), heap.Pop(hp).(int)
		ans += x + y
		heap.Push(hp, x+y)
	}
	return
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