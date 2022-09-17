func maximumScore(a int, b int, c int) int {
	q := hp{[]int{a, b, c}}
	ans := 0
	for {
		a = q.IntSlice[0]
		heap.Pop(&q)
		b = q.IntSlice[0]
		heap.Pop(&q)
		if b == 0 {
			break
		}
		heap.Push(&q, a-1)
		heap.Push(&q, b-1)
		ans++
	}
	return ans
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