func makePrefSumNonNegative(nums []int) (ans int) {
	pq := hp{}
	s := 0
	for _, x := range nums {
		s += x
		if x < 0 {
			heap.Push(&pq, x)
		}
		for s < 0 {
			s -= heap.Pop(&pq).(int)
			ans++
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}