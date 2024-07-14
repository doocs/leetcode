func findKthLargest(nums []int, k int) int {
	minQ := hp{}
	for _, x := range nums {
		heap.Push(&minQ, x)
		if minQ.Len() > k {
			heap.Pop(&minQ)
		}
	}
	return minQ.IntSlice[0]
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
