func minInterval(intervals [][]int, queries []int) []int {
	n, m := len(intervals), len(queries)
	sort.Slice(intervals, func(i, j int) bool { return intervals[i][0] < intervals[j][0] })
	qs := make([][2]int, m)
	ans := make([]int, m)
	for i := range qs {
		qs[i] = [2]int{queries[i], i}
		ans[i] = -1
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i][0] < qs[j][0] })
	pq := hp{}
	i := 0
	for _, q := range qs {
		x, j := q[0], q[1]
		for i < n && intervals[i][0] <= x {
			a, b := intervals[i][0], intervals[i][1]
			heap.Push(&pq, pair{b - a + 1, b})
			i++
		}
		for len(pq) > 0 && pq[0].r < x {
			heap.Pop(&pq)
		}
		if len(pq) > 0 {
			ans[j] = pq[0].v
		}
	}
	return ans
}

type pair struct{ v, r int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }