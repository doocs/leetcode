func maxSpending(values [][]int) (ans int64) {
	pq := hp{}
	n := len(values[0])
	for i, row := range values {
		heap.Push(&pq, tuple{row[n-1], i, n - 1})
	}
	for d := 1; len(pq) > 0; d++ {
		p := heap.Pop(&pq).(tuple)
		ans += int64(p.v * d)
		if p.j > 0 {
			heap.Push(&pq, tuple{values[p.i][p.j-1], p.i, p.j - 1})
		}
	}
	return
}

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }