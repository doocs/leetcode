func getLeastNumbers(arr []int, k int) (ans []int) {
	q := hp{}
	for _, x := range arr {
		heap.Push(&q, x)
		if q.Len() > k {
			heap.Pop(&q)
		}
	}
	for i := 0; i < k; i++ {
		ans = append(ans, heap.Pop(&q).(int))
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