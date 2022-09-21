func rearrangeBarcodes(barcodes []int) []int {
	cnt := map[int]int{}
	for _, v := range barcodes {
		cnt[v]++
	}
	pq := hp{}
	for k, v := range cnt {
		heap.Push(&pq, pair{v, k})
	}
	ans := []int{}
	q := []pair{}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		v, k := p.v, p.k
		ans = append(ans, k)
		q = append(q, pair{v - 1, k})
		for len(q) > 1 {
			p = q[0]
			q = q[1:]
			if p.v > 0 {
				heap.Push(&pq, p)
			}
		}
	}
	return ans
}

type pair struct {
	v int
	k int
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }