func maxAverageRatio(classes [][]int, extraStudents int) float64 {
	pq := hp{}
	for _, e := range classes {
		a, b := e[0], e[1]
		x := float64(a+1)/float64(b+1) - float64(a)/float64(b)
		heap.Push(&pq, tuple{x, a, b})
	}
	for i := 0; i < extraStudents; i++ {
		e := heap.Pop(&pq).(tuple)
		a, b := e.a+1, e.b+1
		x := float64(a+1)/float64(b+1) - float64(a)/float64(b)
		heap.Push(&pq, tuple{x, a, b})
	}
	var ans float64
	for len(pq) > 0 {
		e := heap.Pop(&pq).(tuple)
		ans += float64(e.a) / float64(e.b)
	}
	return ans / float64(len(classes))
}

type tuple struct {
	x float64
	a int
	b int
}

type hp []tuple

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.x > b.x
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }