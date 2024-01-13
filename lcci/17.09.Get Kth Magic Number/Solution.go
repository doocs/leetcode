func getKthMagicNumber(k int) int {
	q := hp{[]int{1}}
	vis := map[int]bool{1: true}
	for i := 0; i < k-1; i++ {
		cur := heap.Pop(&q).(int)
		for _, f := range []int{3, 5, 7} {
			nxt := cur * f
			if !vis[nxt] {
				vis[nxt] = true
				heap.Push(&q, nxt)
			}
		}
	}
	return q.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}