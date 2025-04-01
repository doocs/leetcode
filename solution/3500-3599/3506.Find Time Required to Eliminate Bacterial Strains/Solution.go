func minEliminationTime(timeReq []int, splitTime int) int64 {
	pq := hp{}
	for _, v := range timeReq {
		heap.Push(&pq, v)
	}
	for pq.Len() > 1 {
		heap.Pop(&pq)
		heap.Push(&pq, heap.Pop(&pq).(int)+splitTime)
	}
	return int64(pq.IntSlice[0])
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}