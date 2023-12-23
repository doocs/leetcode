func minStoneSum(piles []int, k int) (ans int) {
	pq := &hp{piles}
	heap.Init(pq)
	for ; k > 0; k-- {
		x := pq.pop()
		pq.push(x - x/2)
	}
	for pq.Len() > 0 {
		ans += pq.pop()
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }