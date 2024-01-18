func smallestK(arr []int, k int) []int {
	q := hp{}
	for _, v := range arr {
		heap.Push(&q, v)
		if q.Len() > k {
			heap.Pop(&q)
		}
	}
	ans := make([]int, k)
	for i := range ans {
		ans[i] = heap.Pop(&q).(int)
	}
	return ans
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