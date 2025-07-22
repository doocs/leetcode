func minOperations(nums []int, k int) (ans int) {
	pq := &hp{nums}
	heap.Init(pq)
	for ; pq.Len() > 1 && pq.IntSlice[0] < k; ans++ {
		x, y := heap.Pop(pq).(int), heap.Pop(pq).(int)
		heap.Push(pq, x*2+y)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[0 : n-1]
	return x
}
func (h *hp) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}
