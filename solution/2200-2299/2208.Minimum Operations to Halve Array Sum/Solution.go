func halveArray(nums []int) (ans int) {
	var s float64
	q := hp{}
	for _, x := range nums {
		s += float64(x)
		heap.Push(&q, float64(x))
	}
	s /= 2
	for s > 0 {
		x := heap.Pop(&q).(float64)
		ans++
		s -= x / 2
		heap.Push(&q, x/2)
	}
	return
}

type hp struct{ sort.Float64Slice }

func (h hp) Less(i, j int) bool { return h.Float64Slice[i] > h.Float64Slice[j] }
func (h *hp) Push(v any)        { h.Float64Slice = append(h.Float64Slice, v.(float64)) }
func (h *hp) Pop() any {
	a := h.Float64Slice
	v := a[len(a)-1]
	h.Float64Slice = a[:len(a)-1]
	return v
}