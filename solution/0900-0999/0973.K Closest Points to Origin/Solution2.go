func kClosest(points [][]int, k int) [][]int {
	maxQ := hp{}
	for i, p := range points {
		dist := math.Hypot(float64(p[0]), float64(p[1]))
		heap.Push(&maxQ, pair{dist, i})
		if len(maxQ) > k {
			heap.Pop(&maxQ)
		}
	}
	ans := make([][]int, k)
	for i, p := range maxQ {
		ans[i] = points[p.i]
	}
	return ans
}

type pair struct {
	dist float64
	i    int
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.dist > b.dist
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
