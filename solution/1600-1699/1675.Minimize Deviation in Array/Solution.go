func minimumDeviation(nums []int) int {
	q := hp{}
	mi := math.MaxInt32
	for _, v := range nums {
		if v%2 == 1 {
			v <<= 1
		}
		heap.Push(&q, v)
		mi = min(mi, v)
	}
	ans := q.IntSlice[0] - mi
	for q.IntSlice[0]%2 == 0 {
		x := heap.Pop(&q).(int) >> 1
		heap.Push(&q, x)
		mi = min(mi, x)
		ans = min(ans, q.IntSlice[0]-mi)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }