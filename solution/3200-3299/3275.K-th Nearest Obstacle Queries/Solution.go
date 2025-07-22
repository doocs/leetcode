func resultsArray(queries [][]int, k int) (ans []int) {
	pq := &hp{}
	for _, q := range queries {
		x := abs(q[0]) + abs(q[1])
		pq.push(x)
		if pq.Len() > k {
			pq.pop()
		}
		if pq.Len() == k {
			ans = append(ans, pq.IntSlice[0])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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
