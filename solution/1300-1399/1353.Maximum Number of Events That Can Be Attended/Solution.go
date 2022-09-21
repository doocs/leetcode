func maxEvents(events [][]int) int {
	d := map[int][]int{}
	i, j := math.MaxInt32, 0
	for _, v := range events {
		s, e := v[0], v[1]
		d[s] = append(d[s], e)
		i = min(i, s)
		j = max(j, e)
	}
	q := hp{}
	ans := 0
	for s := i; s <= j; s++ {
		for q.Len() > 0 && q.IntSlice[0] < s {
			heap.Pop(&q)
		}
		for _, e := range d[s] {
			heap.Push(&q, e)
		}
		if q.Len() > 0 {
			heap.Pop(&q)
			ans++
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }