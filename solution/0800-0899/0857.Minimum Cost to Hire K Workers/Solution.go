func mincostToHireWorkers(quality []int, wage []int, k int) float64 {
	t := []pair{}
	for i, q := range quality {
		t = append(t, pair{float64(wage[i]) / float64(q), q})
	}
	sort.Slice(t, func(i, j int) bool { return t[i].x < t[j].x })
	tot := 0
	var ans float64 = 1e9
	pq := hp{}
	for _, e := range t {
		tot += e.q
		heap.Push(&pq, e.q)
		if pq.Len() == k {
			ans = min(ans, float64(tot)*e.x)
			tot -= heap.Pop(&pq).(int)
		}
	}
	return ans
}

func min(a, b float64) float64 {
	if a < b {
		return a
	}
	return b
}

type pair struct {
	x float64
	q int
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