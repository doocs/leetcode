func connectSticks(sticks []int) int {
	h := IntHeap(sticks)
	heap.Init(&h)
	res := 0
	for h.Len() > 1 {
		val := heap.Pop(&h).(int)
		val += heap.Pop(&h).(int)
		res += val
		heap.Push(&h, val)
	}
	return res
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}