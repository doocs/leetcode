func findMaxValueOfEquation(points [][]int, k int) int {
	ans := -(1 << 30)
	hp := hp{}
	for _, p := range points {
		x, y := p[0], p[1]
		for hp.Len() > 0 && x-hp[0].x > k {
			heap.Pop(&hp)
		}
		if hp.Len() > 0 {
			ans = max(ans, x+y+hp[0].v)
		}
		heap.Push(&hp, pair{y - x, x})
	}
	return ans
}

type pair struct{ v, x int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }